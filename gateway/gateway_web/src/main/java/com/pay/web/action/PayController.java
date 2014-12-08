
package com.pay.web.action;

import static com.pay.web.cons.Constants.MD5_SPLIT;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pay.dao.entity.GatewayAppAccount;
import com.pay.dao.entity.GatewayAppRequest;
import com.pay.dao.entity.GatewayPayOrder;
import com.pay.dao.vo.ValidPayChannel;
import com.pay.service.AccountService;
import com.pay.service.AppRequestService;
import com.pay.service.OrderService;
import com.pay.third.ThirdPayService;
import com.pay.third.ThirdPayServiceFactory;
import com.pay.utils.IPUtils;
import com.pay.utils.Md5Utility;
import com.pay.web.mvc.ResponseJson;
import com.pay.web.util.SerialNumUtils;

@Controller
@SuppressWarnings("rawtypes")
@RequestMapping("pay")
public class PayController {
	
	@Autowired  
	private RedisTemplate redisTemplate; 
	
	@Autowired
	private AccountService accoutService;
	
	@Autowired
	private AppRequestService appRequestService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private ThirdPayServiceFactory thirdPayServiceFactory;
	
	
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(PayController.class);

	

	/**
	 * 选择支付渠道
	 * @param appReq
	 * @param model
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "channel")
    public String selectChannel(final GatewayAppRequest appReq, ModelMap model,HttpServletRequest r)  {
    	if (logger.isInfoEnabled()){
    		logger.info(appReq);
    	}
    	
    	if (appReq == null || !appReq.valide()){
    		ResponseJson.ERROR_PAY_PARAM.build(model);
    		return "error";
    	}
    	
    	// 检查账户是否有效
    	GatewayAppAccount account = accoutService.getAccount(appReq.getAppAccount());
    	if (account == null){
    		ResponseJson.ERROR_ACCOUNT.build(model);
    		return "error";
    	}
    	
    	// 验签
    	if (!validate(appReq,account.getAccountKey())){
    		ResponseJson.ERROR_SIGN.build(model);
    		return "error";
    	}
    	
    	
    	// 保存请求记录
    	final long requestId = appRequestService.saveRequest(appReq);
    	if (requestId == -1){
    		ResponseJson.ERROR_SIGN.build(model);
    		return "error";
    	}
    	
    	appReq.setId(requestId);
    	
    	// 返回可用的支付渠道
    	List<ValidPayChannel> payChannel = accoutService.getValidPayChannel(appReq.getAppAccount());

    	model.put("appRequest", appReq);
    	model.put("payChannel", payChannel);
    	
    	// 防刷
    	final long ramdon = SerialNumUtils.ramdon();
    	model.put("ramdon",ramdon);
    	
    	redisTemplate.execute(new RedisCallback<String>() {

			public String doInRedis(RedisConnection connection)
					throws DataAccessException {
				connection.setEx( redisTemplate.getStringSerializer().serialize(buildRamdonStr(requestId)), 60*60*2, redisTemplate.getStringSerializer().serialize(ramdon+""));
				return buildRamdonStr(requestId);
			}
		});
    	
    	
    	return "pay_channel_select";
    	
    }
	
	/**
	 * 重新选择支付渠道
	 * @param appReq
	 * @param model
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "re_channel")
    public String reSelectChannel( GatewayAppRequest appReq, ModelMap model) {
		// 检查appRequest存在
		if (logger.isInfoEnabled()){
    		logger.info(appReq);
    	}
    	
    	if (appReq == null || !appReq.valide()){
    		ResponseJson.ERROR_PAY_PARAM.build(model);
    		return "error";
    	}
    	
    	appReq = appRequestService.getAppRequest(appReq.getId());
    	if (appReq == null){
    		ResponseJson.ERROR_PAY_PARAM.build(model);
    		return "error";
    	}
    	
		// 返回可用的支付渠道
    	List<ValidPayChannel> payChannel = accoutService.getValidPayChannel(appReq.getAppAccount());

    	model.put("appRequest", appReq);
    	model.put("payChannel", payChannel);
    	
    	// 防刷
    	final long ramdon = SerialNumUtils.ramdon();
    	model.put("ramdon",ramdon);
    	
    	final long requestId = appReq.getId();
    	redisTemplate.execute(new RedisCallback<String>() {

			public String doInRedis(RedisConnection connection)
					throws DataAccessException {
				connection.setEx( redisTemplate.getStringSerializer().serialize(buildRamdonStr(requestId)), 60*60*2, redisTemplate.getStringSerializer().serialize(ramdon+""));
				return buildRamdonStr(requestId);
			}
		});
    	
    	
    	return "pay_channel_select";
	}
	
	/**
	 * 向第三方支付提交支付请求
	 * @param appRequestId
	 * @param ramdon
	 * @param selectChannel
	 * @param model
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "thridpay",method=RequestMethod.POST)
    public String pay(final long appRequestId, long ramdon, long selectChannel, HttpServletRequest httpRequest,ModelMap model)  {
		// 验证防刷串是否存在
		String except = (String)redisTemplate.execute(new RedisCallback<String>() {

			public String doInRedis(RedisConnection connection)
					throws DataAccessException {
				byte[] key = redisTemplate.getStringSerializer().serialize(buildRamdonStr(appRequestId)); 
				byte[] b = connection.get(key);
				connection.del(key);
				return (String)redisTemplate.getStringSerializer()  
                        .deserialize(b);
				
			}
		});
    	
		if (!StringUtils.equals(except, ramdon+"")){
			ResponseJson.ERROR_INVALID_REQUEST.build(model);
    		return "error";
		}
		
		
		GatewayAppRequest appRequest = appRequestService.getAppRequest(appRequestId);
		if (appRequest == null){
			ResponseJson.ERROR_NOTEXITS_REQUEST.build(model);
			return "error";
		}
		
		// 检查channel是否可用
		ValidPayChannel channel = accoutService.getValidPayChannel(appRequest.getAppAccount(), selectChannel);
		if (channel == null){
			ResponseJson.ERROR_NOTEXITS_REQUEST.build(model);
			return "error";
		}
		
		// 创建支付订单
		GatewayPayOrder order = createGatewayPayOrder(appRequest,selectChannel,httpRequest);
		long orderId = orderService.save(order);
		if (orderId == -1){
			ResponseJson.ERROR_NOTEXITS_REQUEST.build(model);
			return "error";
		}
		
		// 构造第三方支付请求参数
		ThirdPayService payService = thirdPayServiceFactory.getThirdPayService(order.getChannelId());
		Map<String,String> req = payService.buildRequest(order);
		
		model.put("paramMap", req);
		model.put("paramKeys", new ArrayList<String>(req.keySet()));
		model.put("url", channel.getPayUrl());
		return "pay_goto_thirdpay";
	}

	private GatewayPayOrder createGatewayPayOrder(GatewayAppRequest appRequest,
			long selectChannel, HttpServletRequest httpRequest) {
		GatewayPayOrder order = new GatewayPayOrder();
		order.setAmount(appRequest.getAmount());
		order.setAppAccount(appRequest.getAppAccount());
		order.setAppRequestId(appRequest.getId() + "");
		order.setBizCode(appRequest.getBizCode());
		order.setBizDescription(appRequest.getBizDescription());
		order.setChannelId(selectChannel);
		order.setEchoContent(appRequest.getEchoContent());
		order.setOrderNo(SerialNumUtils.createSerialNum());
		order.setStatus(GatewayPayOrder.STATUS_INIT);
		order.setUserName(appRequest.getUserName());
		order.setUserIp(IPUtils.getIpAddr(httpRequest));
		return order;
	}

	private String buildRamdonStr(long requestId){
		return "RAMDON_" + requestId;
	}


	private boolean validate(GatewayAppRequest appReq,String secKey) {

		return StringUtils.equalsIgnoreCase(appReq.getSign(),
				Md5Utility.getMD5String(appReq.buildMd5Str() + MD5_SPLIT + secKey));
	}
    
	
    
    
   
}
