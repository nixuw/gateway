package com.pay.third.unionpay;

import com.pay.dao.entity.GatewayChannel;
import com.pay.dao.entity.GatewayUnionpayRequest;
import com.pay.dao.entity.GatewayUnionpayResponse;

public interface UnionpayLogService {
	/**
	 * 保存支付宝支付请求
	 * @param request
	 */
	public void saveRequest(GatewayUnionpayRequest request);

	/**
	 * 保存支付宝回调请求
	 * @param response
	 */
    public void saveResponse(GatewayUnionpayResponse response);
	
	public GatewayChannel getUnionpayChannel();
}
