package com.pay.service;

import com.pay.dao.entity.GatewayPayOrder;

public interface OrderService {
    public long save (GatewayPayOrder order);

	/**
	 * 根据订单号查找支付订单
	 * @param orderNum
	 * @return
	 */
	public GatewayPayOrder getOrder(String orderNum);

	public int update(GatewayPayOrder order);

}
