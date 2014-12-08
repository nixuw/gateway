package com.pay.third.unionpay;

import org.springframework.beans.factory.annotation.Autowired;

import com.pay.dao.GatewayChannelDAO;
import com.pay.dao.GatewayUnionpayRequestDAO;
import com.pay.dao.GatewayUnionpayResponseDAO;
import com.pay.dao.entity.GatewayChannel;
import com.pay.dao.entity.GatewayUnionpayRequest;
import com.pay.dao.entity.GatewayUnionpayResponse;

public class UnionpayLogServiceImpl implements UnionpayLogService{

	@Autowired
	private GatewayUnionpayRequestDAO requestDao;
	
	@Autowired
	private GatewayUnionpayResponseDAO responseDao;
	
	@Autowired
	private GatewayChannelDAO channelDao;
	
	private long channelId;
	public void setChannelId(long channelId) {
		this.channelId = channelId;
	}
	
	@Override
	public void saveRequest(GatewayUnionpayRequest request) {
		// BigDecimal hundred = new BigDecimal("100");
		
		requestDao.insert(request);
	}

	@Override
	public void saveResponse(GatewayUnionpayResponse response) {
		responseDao.insert(response);		
	}

	@Override
	public GatewayChannel getUnionpayChannel() {
		return channelDao.selectById(channelId);
	}


	

}
