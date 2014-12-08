package com.pay.web.action;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pay.dao.entity.GatewayAlipayResponse;
import com.pay.exception.VerifyException;
import com.pay.third.ThirdCallBackService;
import com.pay.web.mvc.ResponseJson;

@Controller
@RequestMapping("notify")
public class NotifyTestController {
	@RequestMapping(value = "test")
	@ResponseBody
	public String test(HttpServletRequest request, ModelMap model) {
		StringBuffer buf = new StringBuffer();
		convertParamsToMap(request,buf);
		System.out.println(buf.toString());
		return "ok";
	}
	
	
	@SuppressWarnings("rawtypes")
	private Map<String, String> convertParamsToMap(HttpServletRequest request,
			StringBuffer buf) {
		Map<String,String> result = new HashMap<String,String>();
		Map params = request.getParameterMap();
		Iterator it = params.keySet().iterator();
		while (it.hasNext()) {
			String paramName = (String) it.next();
			String value = ((String[])params.get(paramName))[0];
			result.put(paramName, value);
			buf.append(paramName).append("=").append(value).append("&");
		}
		return result;
	}
	
	
}
