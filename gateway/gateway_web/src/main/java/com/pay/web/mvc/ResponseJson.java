package com.pay.web.mvc;

import java.util.Map;

public enum ResponseJson {
    SUCCESS ("0","成功"),
	ERROR_ACCOUNT("-1", "账户错误"),
    ERROR_PAY_PARAM("-10", "支付请求参数错误"),
    ERROR_SIGN("-11", "签名错误"),
    ERROR_SAVE_REQUEST("-12", "保存支付请求错误"),
    ERROR_INVALID_REQUEST("-13", "请求验证码错误"),
    ERROR_NOTEXITS_REQUEST("-13", "支付请求不存在"),
    ERROR_INVALID_CHANNEL("-14", "支付渠道不可用"),
    ERROR_SAVE_ORDER("-15", "保存支付订单失败"),
    
    
    ERROR_ALIPAY_CALLBACK_SIGN("-31", "支付宝回调验证失败"),
    ;
    
	private String code;
	private String msg;

	private ResponseJson(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

	public void build(Map map) {
		if (map == null) {
			return;
		}
		map.put("code", code);
		map.put("msg", msg);
	}

}
