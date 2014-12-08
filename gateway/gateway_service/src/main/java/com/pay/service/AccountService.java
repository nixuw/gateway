package com.pay.service;

import java.util.List;

import com.pay.dao.entity.GatewayAppAccount;
import com.pay.dao.vo.ValidPayChannel;

public interface AccountService {
    public GatewayAppAccount getAccount (String appAccount);

	public List<ValidPayChannel> getValidPayChannel(String appAccount);
	
	public ValidPayChannel getValidPayChannel(String appAccount,long channelId);
}
