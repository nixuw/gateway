package com.pay.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pay.dao.GatewayPayOrderDAO;
import com.pay.dao.entity.GatewayPayOrder;
import com.pay.service.OrderService;

@Service("orderService")
public class OrderServiceImpl implements OrderService{

	
	@Autowired
	private GatewayPayOrderDAO gatewayPayOrderDAO;
	

	@Override
	public long save(GatewayPayOrder order) {
		if (order == null){
			return -1;
		}
		
		return gatewayPayOrderDAO.insert(order);
	}


	@Override
	public GatewayPayOrder getOrder(String orderNo) {
		return gatewayPayOrderDAO.queryByOrderNo(orderNo);
	}


	@Override
	public int update(GatewayPayOrder order) {
		return gatewayPayOrderDAO.updateByIdSelective(order);
	}


}
