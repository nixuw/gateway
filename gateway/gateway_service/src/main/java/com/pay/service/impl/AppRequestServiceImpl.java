package com.pay.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pay.dao.GatewayAppRequestDAO;
import com.pay.dao.entity.GatewayAppRequest;
import com.pay.service.AppRequestService;

@Service("appRequestService")
public class AppRequestServiceImpl implements AppRequestService{

	
	@Autowired
	private GatewayAppRequestDAO gatewayAppRequestDAO;

	@Override
	public long saveRequest(GatewayAppRequest appReq) {
		if (appReq == null){
			return -1L;
		}
		
		return gatewayAppRequestDAO.insert(appReq);
	}

	@Override
	public GatewayAppRequest getAppRequest(long appRequestId) {
		return gatewayAppRequestDAO.selectById(appRequestId);
	}
	
	
	

}
