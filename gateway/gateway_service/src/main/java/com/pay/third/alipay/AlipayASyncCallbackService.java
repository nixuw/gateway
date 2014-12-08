package com.pay.third.alipay;

import org.springframework.stereotype.Service;

import com.pay.dao.entity.GatewayPayOrder;

@Service("alipayASyncCallbackService")
public class AlipayASyncCallbackService extends AbstractAlipayCallbackService {

	@Override
	protected String getName() {
		return "支付宝异步";
	}

	@Override
	protected String updateOrderStatusFrom() {
		return null;
	}

	@Override
	protected String updateOrderStatusTo() {
		return GatewayPayOrder.STATUS_CONFIRMSUCCESS;
	}

}
