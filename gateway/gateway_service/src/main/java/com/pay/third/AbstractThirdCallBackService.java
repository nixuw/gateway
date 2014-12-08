package com.pay.third;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

import com.pay.constant.RedisConstants;
import com.pay.dao.entity.GatewayPayOrder;
import com.pay.exception.VerifyException;
import com.pay.service.OrderService;

public abstract class AbstractThirdCallBackService<T> implements ThirdCallBackService<T> {
	protected static final Logger logger = Logger.getLogger(AbstractThirdCallBackService.class);
	
	@Autowired
	private OrderService orderService;
	
	@Autowired  
	private RedisTemplate redisTemplate; 
	
	
     @Override
    public T handler(HttpServletRequest request) throws VerifyException {
    	 StringBuffer buf = new StringBuffer();
    	 Map<String,String> params = convertParamsToMap(request,buf);
    	 
    	 // 记录日志
		if (logger.isInfoEnabled()) {
			logger.info(getName() + "回调原始请求参数: " + buf.toString());
		}
		
		// 验签
		if (!verify(params)){
			logger.info(getName() + "验证失败: " + buf.toString());
			throw new VerifyException("验证失败");
		}
		
		// 保存回调请求
		T resp = convertParamsToObject(request);
		save(resp);
		
		// 创建桥接对象
		ThirdCallbackGWOrderBridge<T> bridge = createThirdCallbackGWOrderBridge(resp);
		
		// 更新支付状态
        updateGatewayOrder(bridge);
        
        // 通知应用系统
        try {
			notifyApp(bridge.getOrderNo());
		} catch (Exception e) {
			e.printStackTrace();
		}
        
    	return resp;
    }


	private int updateGatewayOrder(ThirdCallbackGWOrderBridge<T> bridge) throws VerifyException {
		GatewayPayOrder order = orderService.getOrder(bridge.getOrderNo());
		if (order == null){
			// 支付网关定单不存在?
			logger.error("支付网关定单不存在:" + bridge.getParterSerialNum());
			return -1;
		}
		
		// 检查支付金额一致
		if (bridge.getTotalFee().compareTo(order.getAmount()) != 0){
			logger.error(bridge.getParterSerialNum() + " 支付金额不一致,期望:" + order.getAmount() + " 实际："+ bridge.getTotalFee());
			throw new VerifyException("支付金额不一致");
		}
		
		order.setParterSerialNum(bridge.getParterSerialNum()); // 第三方流水号
		order.setStatus(bridge.toStatus());
		order.setAmount(bridge.getTotalFee()); // 不修改价格,用来验证金额是一致的
		if (bridge.exceptCurrentStatus() != null){
		   order.setFromStatus(bridge.exceptCurrentStatus());
		}
		
		if (order.getPaymentDate() == null){
			order.setPaymentDate(bridge.getPaymentDate()); // 支付完成时间
		}
		
		int count = orderService.update(order);
		 if (logger.isInfoEnabled()){
	        	logger.info("更新支付订单:" + bridge.getOrderNo() + "状态为"+ bridge.toStatus() +",影响 数量:" + count);
	     }
		 return count;
	}

	@SuppressWarnings("rawtypes")
	private Map<String, String> convertParamsToMap(HttpServletRequest request,
			StringBuffer buf) {
		Map<String,String> result = new HashMap<String,String>();
		Map params = request.getParameterMap();
		Iterator it = params.keySet().iterator();
		while (it.hasNext()) {
			String paramName = (String) it.next();
			String value = ((String[])params.get(paramName))[0];
			result.put(paramName, value);
			buf.append(paramName).append("=").append(value).append("&");
		}
		return result;
	}
	
	
	protected Date parse(String dateStr){
		   if (dateStr != null){
		      try {
				return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateStr);
			} catch (ParseException e) {
			}
		   }
		   return null;
	   }
	
	@SuppressWarnings("unchecked")
	private void notifyApp(final String orderNo) {
		// redisTemplate.opsForList().leftPush(RedisConstants.NOTIFY_APP_QUEUE_NAME, orderNo);
		redisTemplate.execute(new RedisCallback<Void>() {

			public Void doInRedis(RedisConnection connection)
					throws DataAccessException {
				connection.lPush( redisTemplate.getStringSerializer().serialize(RedisConstants.NOTIFY_APP_QUEUE_NAME),
						redisTemplate.getStringSerializer().serialize(orderNo));
				return null;
			}
		});
    	
	}
	
	protected abstract void save(T resp);

	protected abstract T convertParamsToObject(HttpServletRequest request) ;

	protected abstract boolean verify(Map<String, String> params) throws VerifyException;

	protected abstract  String getName() ;
	
	protected abstract ThirdCallbackGWOrderBridge<T> createThirdCallbackGWOrderBridge(T resp) ;
	
}
