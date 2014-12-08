package com.pay.third.alipay;

import org.springframework.beans.factory.annotation.Autowired;

import com.pay.dao.GatewayAlipayRequestDAO;
import com.pay.dao.GatewayAlipayResponseDAO;
import com.pay.dao.GatewayChannelDAO;
import com.pay.dao.entity.GatewayAlipayRequest;
import com.pay.dao.entity.GatewayAlipayResponse;
import com.pay.dao.entity.GatewayChannel;

public class AlipayLogServiceImpl implements AlipayLogService{

	@Autowired
	private GatewayAlipayRequestDAO requestDao;
	
	@Autowired
	private GatewayAlipayResponseDAO responseDao;
	
	@Autowired
	private GatewayChannelDAO channelDao;
	
	private long channelId;
	public void setChannelId(long channelId) {
		this.channelId = channelId;
	}
	
	@Override
	public void saveRequest(GatewayAlipayRequest request) {
		requestDao.insert(request);
	}

	@Override
	public void saveResponse(GatewayAlipayResponse response) {
		responseDao.insert(response);		
	}

	@Override
	public GatewayChannel getAlipayChannel() {
		return channelDao.selectById(channelId);
	}

	

}
