package com.pay.third.alipay;

import org.springframework.stereotype.Service;

import com.pay.dao.entity.GatewayPayOrder;

@Service("alipaySyncCallbackService")
public class AlipaySyncCallbackService extends AbstractAlipayCallbackService {

	@Override
	protected String getName() {
		return "支付宝同步";
	}

	@Override
	protected String updateOrderStatusFrom() {
		return GatewayPayOrder.STATUS_INIT;
	}

	@Override
	protected String updateOrderStatusTo() {
		return GatewayPayOrder.STATUS_PAYSUCCESS;
	}

}
