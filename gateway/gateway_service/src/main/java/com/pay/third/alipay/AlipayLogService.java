package com.pay.third.alipay;

import com.pay.dao.entity.GatewayAlipayRequest;
import com.pay.dao.entity.GatewayAlipayResponse;
import com.pay.dao.entity.GatewayChannel;

public interface AlipayLogService {
	/**
	 * 保存支付宝支付请求
	 * @param request
	 */
	public void saveRequest(GatewayAlipayRequest request);

	/**
	 * 保存支付宝回调请求
	 * @param response
	 */
	public void saveResponse(GatewayAlipayResponse response);
	
	public GatewayChannel getAlipayChannel();
}
