package com.pay.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pay.dao.GatewayAppAccountDAO;
import com.pay.dao.entity.GatewayAppAccount;
import com.pay.dao.vo.ValidPayChannel;
import com.pay.service.AccountService;

@Service("accountService")
public class AccountServiceImpl implements AccountService{

	
	@Autowired
	private GatewayAppAccountDAO gatewayAppAccountDAO;
	
	@Override
	public GatewayAppAccount getAccount(String appAccount) {
		if (StringUtils.isEmpty(appAccount)){
			return null;
		}
		
		return gatewayAppAccountDAO.selectByAccount(appAccount);
		
	}

	@Override
	public List<ValidPayChannel> getValidPayChannel(String appAccount) {
		if (StringUtils.isEmpty(appAccount)){
			return null;
		}
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("account", appAccount);
		
		return gatewayAppAccountDAO.selectValidPayChannel(params);
	}

	@Override
	public ValidPayChannel getValidPayChannel(String appAccount, long channelId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("account", appAccount);
		params.put("channelId", channelId + "");
		List<ValidPayChannel>  channelList = gatewayAppAccountDAO.selectValidPayChannel(params);
		
		if (channelList == null || channelList.size() == 0){
			return null;
		}
		
		return channelList.get(0);
	}

}
