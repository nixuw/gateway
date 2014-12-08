package com.pay.third;

import java.util.Map;

public class ThirdPayServiceFactory {
	private Map<String, ThirdPayService> thirdPays;

	public void setThirdPays(Map<String, ThirdPayService> thirdPays) {
		this.thirdPays = thirdPays;
	}

	/**
	 * 根据支付渠道ID返回第三方支付服务
	 * @param channelId
	 * @return
	 */
	public ThirdPayService getThirdPayService(Long channelId) {

		return thirdPays.get(String.valueOf(channelId));
	}
}
