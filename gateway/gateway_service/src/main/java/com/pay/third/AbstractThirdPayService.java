package com.pay.third;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.pay.dao.entity.GatewayChannel;
import com.pay.dao.entity.GatewayPayOrder;
import com.pay.service.ChannelService;

public abstract class AbstractThirdPayService<T> implements ThirdPayService{
   
	@Autowired
	private ChannelService channelService;
	
	@Override
	public Map<String, String>  buildRequest (GatewayPayOrder order) {
		if (order == null){
			return null;
		}
		
		// 查询渠道
		GatewayChannel channel = channelService.getChannel(order.getChannelId());
		if (channel == null){
			return null;
		}
		
		T t = create(order);
		
		// 计算签名
		Map<String, String> signMap = buildSignMap(t,channel); // 所有需要签名的参数
		String sign = sign (signMap,channel);
		signMap.put(getSignKey(), sign);
				
		// 保存支付请求
		save(t,sign);
		
		return signMap;
	}

	protected abstract String sign(Map<String, String> signMap, GatewayChannel channel);

	protected abstract String getSignKey() ;

	protected abstract T create(GatewayPayOrder order);
	
	protected abstract  Map<String, String> buildSignMap(T t, GatewayChannel channel) ;

	protected abstract void save(T t, String sign);

	



}
