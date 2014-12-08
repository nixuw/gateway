package com.pay.third.unionpay;

import org.springframework.stereotype.Service;

import com.pay.dao.entity.GatewayPayOrder;

@Service("unionpaySyncCallbackService")
public class UnionpaySyncCallbackService extends
		AbstractUnionpayCallbackService {

	@Override
	protected String getName() {
		return "银联同步回调";
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
