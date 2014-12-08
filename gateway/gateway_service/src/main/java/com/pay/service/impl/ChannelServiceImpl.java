package com.pay.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pay.dao.GatewayChannelDAO;
import com.pay.dao.entity.GatewayChannel;
import com.pay.service.ChannelService;

@Service("channelService")
public class ChannelServiceImpl implements ChannelService{

	
	@Autowired
	private GatewayChannelDAO gatewayChannelDAO;

	@Override
	public GatewayChannel getChannel(long channelId) {
		return gatewayChannelDAO.selectById(channelId);
	}
	

	

}
