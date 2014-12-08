package com.pay.third.alipay;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.pay.dao.entity.GatewayAlipayRequest;
import com.pay.dao.entity.GatewayChannel;
import com.pay.dao.entity.GatewayPayOrder;
import com.pay.third.AbstractThirdPayService;
import com.pay.third.alipay.util.AlipayConfig;
import com.pay.third.alipay.util.AlipayCore;
import com.pay.utils.MD5;

public class AlipayService extends AbstractThirdPayService<GatewayAlipayRequest> {

	@Autowired
	private AlipayLogService logService;
	

	@Override
	protected void save(GatewayAlipayRequest t, String sign) {
		t.setSign(sign);
		logService.saveRequest(t);
	}


	@Override
	protected GatewayAlipayRequest create(GatewayPayOrder order) {
		GatewayAlipayRequest request = new GatewayAlipayRequest();
		request.setBody(order.getBizDescription());
		request.setExtraCommonParam(order.getEchoContent());
		request.setOutTradeNo(order.getOrderNo());
		request.setPaymentType("1");
		request.setService("create_direct_pay_by_user");
		request.setSubject(order.getBizDescription());
		request.setTotalFee(order.getAmount());
		
		return request;
	}

	
	protected Map<String, String> buildSignMap(GatewayAlipayRequest t, GatewayChannel channel){
		
		Map<String, String> map = new HashMap<String, String>(); 
		map.put("service", t.getService());
		map.put("partner", channel.getAccount());
		map.put("_input_charset", "UTF-8");
		map.put("sign_type", "MD5");
		map.put("notify_url", channel.getNotifyUrl());
		map.put("return_url", channel.getReturnUrl());
		map.put("out_trade_no", t.getOutTradeNo());
		map.put("subject", t.getSubject());
		map.put("payment_type", t.getPaymentType());
		map.put("total_fee", t.getTotalFee().toString());
		map.put("seller_id", channel.getAccount());
		map.put("body", t.getBody());
		map.put("show_url", t.getShowUrl());
		// map.put("extra_common_param", t.getExtraCommonParam());
		return AlipayCore.paraFilter(map);
	}


	@Override
	protected String sign(Map<String, String> signMap, GatewayChannel channel) {
		
		String signStr = AlipayCore.createLinkString(signMap);
		return MD5.sign(signStr, channel.getAccountKey(), AlipayConfig.input_charset);
	}


	@Override
	protected String getSignKey() {
		return "sign";
	}
	

}
