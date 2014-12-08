package com.pay.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.pay.dao.GatewayAppNotifyDAO;
import com.pay.dao.entity.GatewayAppAccount;
import com.pay.dao.entity.GatewayAppNotify;
import com.pay.dao.entity.GatewayAppRequest;
import com.pay.dao.entity.GatewayPayOrder;
import com.pay.exception.NotifyAppException;
import com.pay.httpclient.OCCHttpClient;
import com.pay.httpclient.OCCHttpException;
import com.pay.utils.Md5Utility;

@Service("appNotifyService")
public class AppNotifyService {

	@Autowired
	private OrderService orderService;

	@Autowired
	private AccountService accoutService;
	
	@Autowired
	private AppRequestService requestService;
	
	@Autowired
	private OCCHttpClient httpClient;
	
	@Autowired
	private GatewayAppNotifyDAO  notifyDAO;

	public void notify(String orderNo) throws NotifyAppException {
		// 检查是否存在
		GatewayPayOrder order = orderService.getOrder(orderNo);
		if (order == null) {
			throw new NotifyAppException("支付订单:" + orderNo + "不存在");
		}
		
		GatewayAppRequest request = requestService.getAppRequest(Long.parseLong(order.getAppRequestId()));
        if (request == null){
        	throw new NotifyAppException("支付订单:" + orderNo + "对应的appreqeust不存在");
        }
        
        GatewayAppAccount account = accoutService.getAccount(order.getAppAccount());
        if (account == null || account.getNotifyUrl() == null){
        	throw new NotifyAppException("支付订单:" + orderNo + "对应的account不存在或通知地址不存在");
        }
		// 签名
        String signString = buildSignStr(order,request.getRequestToken(),account.getAccountKey());
        String sign = Md5Utility.getMD5String(signString);

		// 通知应用
        String appurl = account.getNotifyUrl() ;
        
        Map<String,String> params = new HashMap<String,String>();
        params.put("sign", sign);
        params.put("echoContent", order.getEchoContent() );
        params.put("status", order.getStatus() );
        params.put("requestId", order.getAppRequestId()+"");
        params.put("orderNo", order.getOrderNo());
        params.put("amount",  order.getAmount().toString());
        params.put("requestToken", request.getRequestToken());
        params.put("bizCode", order.getBizCode());
        
        
        String notifyResult = null;
        try {
        	notifyResult = httpClient.post(appurl,params);
		} catch (OCCHttpException e) {
			e.printStackTrace();
			// throw new NotifyAppException("通知应用系统出错" + e.getMessage());
		}

		// 保存或更新通知表
        GatewayAppNotify old = notifyDAO.select(order.getOrderNo(),order.getStatus());
        
        if (old == null){
        	GatewayAppNotify notify = new GatewayAppNotify();
        	notify.setGatewayOrderNo(order.getOrderNo());
        	notify.setGatewayOrderStatus(order.getStatus());
        	notify.setRetryCount((short)0);
        	notify.setSign(sign);
        	if ("ok".equalsIgnoreCase(notifyResult)){
        		notify.setStatus(GatewayAppNotify.STATUS_SUCESS);
        	}else{
        		notify.setStatus(GatewayAppNotify.STATUS_FAIL);
        	}
        	
        	try {
				notifyDAO.insert(notify);
			} catch (DataAccessException e) {
				 update(old,order, notifyResult);
			}
        	
        }else{
        	old.setSign(sign);
            update(old,order, notifyResult);
        }
	}

	private void update(GatewayAppNotify old,GatewayPayOrder order, String notifyResult) {
		
		if (old == null){
			old = notifyDAO.select(order.getOrderNo(), order.getStatus());
		}
		
		if ("ok".equalsIgnoreCase(notifyResult)) {
			old.setStatus(GatewayAppNotify.STATUS_SUCESS);
		} else {
			old.setStatus(GatewayAppNotify.STATUS_FAIL);
		}
		notifyDAO.updateByIdSelective(old);
	}

	/**
	 * 支付订单编号|支付请求ID|支付状态
	 * @param order
	 * @param key
	 * @return
	 */
	private String buildSignStr(GatewayPayOrder order,String requestToken,String key) {
		
		return order.getOrderNo() + "|" 
		+            requestToken + "|" 
		      + order.getStatus() + "|" 
		     + order.getBizCode() + "|" 
		      + order.getAmount() + "|"
		      + key ;
		      
	}

	public List<GatewayAppNotify> queryFailed(int maxSize) {
		return notifyDAO.selectFailed(maxSize);
	}
}
