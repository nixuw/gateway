package com.pay.third.alipay;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.pay.dao.GatewayChannelDAO;
import com.pay.httpclient.OCCHttpClient;
import com.pay.httpclient.OCCHttpException;


public class AlipayVerifyService implements InitializingBean{
	
	@Autowired
	private OCCHttpClient httpClient;
	
	@Autowired
	private GatewayChannelDAO channelDao;
	
	private String partner;
	
	private String isVerify;
	public void setIsVerify(String isVerify) {
		this.isVerify = isVerify;
	}
	
	private long channelId;
	public void setChannelId(long channelId) {
		this.channelId = channelId;
	}
	
	private String verifyUrl;
	public void setVerifyUrl(String verifyUrl) {
		this.verifyUrl = verifyUrl;
	}
	
	
    public boolean isValid(String notifyId){
    	
    	if (!"true".equalsIgnoreCase(isVerify)){
    		// 不需要验证
    		return true;
    	}
    	
    	try {
			String content = httpClient.get(verifyUrl +  "&partner=" + partner + "&notify_id=" + notifyId );
			return "true".equalsIgnoreCase(content);
		} catch (OCCHttpException e) {
			e.printStackTrace();
		}
    	return false;
    }


	@Override
	public void afterPropertiesSet() throws Exception {
		partner = channelDao.selectById(channelId).getAccount();
	}
}
