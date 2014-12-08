package com.pay.third.unionpay;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.pay.dao.entity.GatewayChannel;
import com.pay.dao.entity.GatewayPayOrder;
import com.pay.dao.entity.GatewayUnionpayRequest;
import com.pay.third.AbstractThirdPayService;
import com.pay.third.unionpay.util.UnionpayConfig;
import com.pay.third.unionpay.util.UnionpayCore;
import com.pay.utils.MD5;

public class UnionpayService extends AbstractThirdPayService<GatewayUnionpayRequest> {
    // private final static BigDecimal ZERO = new BigDecimal("0");
    private final static BigDecimal HUDRED = new BigDecimal("100");
    // private final static int ONE = 1;
    private final static String RMB = "156"; // 银联人民币代码
    private  String merchantNameInUnionpay; // 在银联的商户名称
    
	@Autowired
	private UnionpayLogService logService;
	
	private long transTimeout = 60*60L; // 1小时
	public void setTransTimeout(long transTimeout) {
		this.transTimeout = transTimeout;
	}

	public void setMerchantNameInUnionpay(String merchantNameInUnionpay) {
		this.merchantNameInUnionpay = merchantNameInUnionpay;
	}
	
	@Override
	protected void save(GatewayUnionpayRequest t, String sign) {
		t.setSignature(sign);
		logService.saveRequest(t);
	}


	@Override
	protected GatewayUnionpayRequest create(GatewayPayOrder order) {
		GatewayUnionpayRequest r = new GatewayUnionpayRequest();
		
	    r.setCommodityName(order.getBizDescription());
	    
	    r.setCustomerIp(order.getUserIp()); 
	    r.setCustomerName(order.getUserName());
	    
	    r.setOrderAmount(HUDRED.multiply(order.getAmount()));
	    r.setOrderCurrency(RMB);
	    r.setOrderNumber(order.getOrderNo());
	    r.setOrderTime(new Date());
	   
	    r.setTransType("01"); // 消费
	    r.setTransTimeout(transTimeout*1000); // 单位ms,防止钓鱼网站的问题
	    
	    r.setVersion("1.0.0");
	    
		return r;
	}

	
	protected Map<String, String> buildSignMap(GatewayUnionpayRequest t, GatewayChannel channel){
		
		Map<String, String> map = new HashMap<String, String>(); 
		map.put("version", t.getVersion());
		map.put("charset", "UTF-8");
		map.put("signMethod", "MD5");
		map.put("transType", t.getTransType());
		map.put("merAbbr",merchantNameInUnionpay); // 商户名称
		map.put("merId",channel.getAccount()); // 商户代码  
		
		map.put("backEndUrl",channel.getNotifyUrl() );
		map.put("frontEndUrl", channel.getReturnUrl());
		
		map.put("orderTime", new SimpleDateFormat("yyyyMMddHHmmss").format(t.getOrderTime()));
		map.put("orderNumber", t.getOrderNumber());
		
		map.put("commodityName", t.getCommodityName());
		if (t.getCommodityUrl() != null ){
			map.put("commodityUrl", t.getCommodityUrl());
		}
		
		
		map.put("orderAmount", t.getOrderAmount().longValue() + ""); // 单位分,不需要小数了
		map.put("orderCurrency", t.getOrderCurrency());
		map.put("customerName", t.getCustomerName());
		
		map.put("transTimeout",String.valueOf(t.getTransTimeout()));
		map.put("customerIp", t.getCustomerIp());
		
		return map;
	}


	@Override
	protected String sign(Map<String, String> signMap, GatewayChannel channel) {
		
		String signStr = UnionpayCore.createLinkString(UnionpayCore.paraFilter(signMap));
		
		// 对密钥本身先MD5
		String md5KeySelf = MD5.sign(channel.getAccountKey(), "", UnionpayConfig.input_charset);
		return MD5.sign(signStr, md5KeySelf, UnionpayConfig.input_charset);
	}


	@Override
	protected String getSignKey() {
		return "signature";
	}
	

}
