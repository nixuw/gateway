package com.pay.third.unionpay;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.pay.dao.entity.GatewayUnionpayResponse;
import com.pay.exception.VerifyException;
import com.pay.third.AbstractThirdCallBackService;
import com.pay.third.ThirdCallbackGWOrderBridge;
import com.pay.third.alipay.AlipayVerifyService;
import com.pay.third.unionpay.util.UnionpayConfig;
import com.pay.third.unionpay.util.UnionpayCore;
import com.pay.utils.MD5;

public abstract class AbstractUnionpayCallbackService extends AbstractThirdCallBackService<GatewayUnionpayResponse>{
	@Autowired
	private UnionpayLogService logService;
	
	@Autowired
	private AlipayVerifyService alipayVerifyService;
	
    private final static BigDecimal HUDRED = new BigDecimal("100");
	
	@Override
	protected void save(GatewayUnionpayResponse resp) {
		  logService.saveResponse(resp);
	}


	@Override
	protected boolean verify(Map<String,String> params) throws VerifyException {
		// 对密钥本身先MD5
		String md5KeySelf = MD5.sign(logService.getUnionpayChannel().getAccountKey(), "", UnionpayConfig.input_charset);
				
		// 验签
        String signStr = UnionpayCore.createLinkString(UnionpayCore.paraFilter(params));
        String md5 = MD5.sign(signStr, md5KeySelf, UnionpayConfig.input_charset);
        if (!StringUtils.equalsIgnoreCase(params.get("signature"),md5)){
        	logger.error(params.get("orderNumber") + " 银联回调验签失败,期望:" + signStr + " 实际："+ md5);
        	throw new VerifyException("验签失败");
        }
       
        return true;
	}



	@Override
	protected  ThirdCallbackGWOrderBridge<GatewayUnionpayResponse> createThirdCallbackGWOrderBridge(
			GatewayUnionpayResponse resp) {
		return new ThirdCallbackGWOrderBridge<GatewayUnionpayResponse>(resp) {

			@Override
			public String getOrderNo() {
				return t.getOrderNumber();
			}

			@Override
			public String getParterSerialNum() {
				return t.getQid();
			}

			@Override
			public String exceptCurrentStatus() {
				return updateOrderStatusFrom();
			}

			@Override
			public String toStatus() {
				return updateOrderStatusTo();
			}

			@Override
			public BigDecimal getTotalFee() {
				return t.getOrderAmount().divide(HUDRED);
			}

			@Override
			public Date getPaymentDate() {
				return t.getRespTime();
			}
		};
	}
	
	@Override
	protected GatewayUnionpayResponse convertParamsToObject(HttpServletRequest request) {
		GatewayUnionpayResponse resp = new GatewayUnionpayResponse();
        resp.setVersion(request.getParameter("version"));
        resp.setCharset(request.getParameter("charset"));
        resp.setSignMethod(request.getParameter("signMethod"));
        resp.setSignature(request.getParameter("signature"));
        resp.setTransType(request.getParameter("transType"));
        resp.setRespCode(request.getParameter("respCode"));
        resp.setRespMsg(request.getParameter("respMsg"));
        resp.setMerAbbr(request.getParameter("merAbbr"));
        resp.setMerId(request.getParameter("merId"));
        resp.setOrderNumber(request.getParameter("orderNumber"));
        resp.setTraceNumber(request.getParameter("traceNumber"));
        resp.setTraceTime(request.getParameter("traceTime"));
        resp.setQid(request.getParameter("qid"));
        resp.setOrderAmount(new BigDecimal(request.getParameter("orderAmount")));
        resp.setOrderCurrency(request.getParameter("orderCurrency"));
        if (request.getParameter("respTime") != null){
        	try {
				resp.setRespTime(new SimpleDateFormat("yyyyMMddHHmmss").parse(request.getParameter("respTime")));
			} catch (ParseException e) {
			}
        }
        
        resp.setSettleAmount(new BigDecimal(request.getParameter("settleAmount")));
        resp.setSettleCurrency(request.getParameter("settleCurrency"));
        resp.setSettleDate(request.getParameter("settleDate"));
        resp.setExchangeRate(request.getParameter("exchangeRate"));
        resp.setExchangeDate(request.getParameter("exchangeDate"));
        resp.setCupReserved(request.getParameter("cupReserved"));
        
		return resp;
	}
	
	
	@Override
	protected abstract String getName() ;
	
	protected abstract String updateOrderStatusFrom() ;
	
	protected abstract String updateOrderStatusTo() ;

}
