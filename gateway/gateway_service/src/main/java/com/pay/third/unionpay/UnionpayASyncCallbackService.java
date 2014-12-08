package com.pay.third.unionpay;

import org.springframework.stereotype.Service;

import com.pay.dao.entity.GatewayPayOrder;

@Service("unionpayASyncCallbackService")
public class UnionpayASyncCallbackService extends
		AbstractUnionpayCallbackService {

	@Override
	protected String getName() {
		return "银联异步回调";
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
