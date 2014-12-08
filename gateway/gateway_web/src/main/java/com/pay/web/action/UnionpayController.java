package com.pay.web.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pay.dao.entity.GatewayUnionpayResponse;
import com.pay.exception.VerifyException;
import com.pay.third.ThirdCallBackService;
import com.pay.web.mvc.ResponseJson;

@Controller
@RequestMapping("unionpay")
public class UnionpayController {

	@Autowired
	@Qualifier("unionpaySyncCallbackService")
	private ThirdCallBackService<GatewayUnionpayResponse> callback;
	
	@Autowired
	@Qualifier("unionpayASyncCallbackService")
	private ThirdCallBackService<GatewayUnionpayResponse> notify;
	
	
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(UnionpayController.class);

	@RequestMapping(value = "callback")
	public String callback(HttpServletRequest request, ModelMap model) {
		
		GatewayUnionpayResponse resp;
		try {
			resp = callback.handler(request);
		} catch (VerifyException e) {
			ResponseJson.ERROR_ALIPAY_CALLBACK_SIGN.build(model);
			logger.error("处理银联同步回调失败",e);
			return "error";
		}
		
		model.put("orderNo", resp.getOrderNumber());
		
		 // 显示支付成功页面
        ResponseJson.SUCCESS.build(model);
		return "pay_success";

	}

	@RequestMapping(value = "notify")
	@ResponseBody
	public String notify(HttpServletRequest request, ModelMap model) {
		try {
			notify.handler(request);
		} catch (VerifyException e) {
			logger.error("处理银联异步回调失败",e);
			return "error";
		}
		
		return "OK";
	}

}
