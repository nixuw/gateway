package com.pay.third.alipay;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.pay.dao.entity.GatewayAlipayResponse;
import com.pay.exception.VerifyException;
import com.pay.third.AbstractThirdCallBackService;
import com.pay.third.ThirdCallbackGWOrderBridge;
import com.pay.third.alipay.util.AlipayConfig;
import com.pay.third.alipay.util.AlipayCore;
import com.pay.utils.MD5;

public abstract class AbstractAlipayCallbackService extends AbstractThirdCallBackService<GatewayAlipayResponse>{
	@Autowired
	private AlipayLogService logService;
	
	@Autowired
	private AlipayVerifyService alipayVerifyService;
	
	@Override
	protected void save(GatewayAlipayResponse resp) {
		  logService.saveResponse(resp);
	}


	@Override
	protected boolean verify(Map<String,String> params) throws VerifyException {
		// 验签
        String signStr = AlipayCore.createLinkString(AlipayCore.paraFilter(params));
        String md5 = MD5.sign(signStr, logService.getAlipayChannel().getAccountKey(), AlipayConfig.input_charset);
        if (!StringUtils.equalsIgnoreCase(params.get("sign"),md5)){
        	logger.error(params.get("out_trade_no") + " 支付宝回调验签失败,期望:" + signStr + " 实际："+ md5);
        	throw new VerifyException("验签失败");
        }
        
        // 验证是否是支付宝的请求
        if (!alipayVerifyService.isValid(params.get("notify_id"))){
        	logger.error(params.get("out_trade_no") + " 支付宝回调验签验证notifyId失败,notifyId=" + params.get("notify_id") );
        	throw new VerifyException("验证回调来源失败");
        }
        return true;
	}



	@Override
	protected  ThirdCallbackGWOrderBridge<GatewayAlipayResponse> createThirdCallbackGWOrderBridge(
			GatewayAlipayResponse resp) {
		return new ThirdCallbackGWOrderBridge<GatewayAlipayResponse>(resp) {

			@Override
			public String getOrderNo() {
				return t.getOutTradeNo();
			}

			@Override
			public String getParterSerialNum() {
				return t.getTradeNo();
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
				return t.getTotalFee();
			}

			@Override
			public Date getPaymentDate() {
				return t.getGmtPayment();
			}
		};
	}
	
	@Override
	protected GatewayAlipayResponse convertParamsToObject(HttpServletRequest request) {
		GatewayAlipayResponse resp = new GatewayAlipayResponse();
        resp.setIsSuccess(request.getParameter("is_success"));
        resp.setSign(request.getParameter("sign"));
        resp.setOutTradeNo(request.getParameter("out_trade_no"));
        resp.setSubject(request.getParameter("subject"));
        resp.setPaymentType(request.getParameter("payment_type"));
        resp.setExterface(request.getParameter("exterface"));
        resp.setTradeNo(request.getParameter("trade_no"));
        resp.setTradeStatus(request.getParameter("trade_status"));
        resp.setNotifyId(request.getParameter("notify_id"));
    	 resp.setNotifyTime(parse(request.getParameter("notify_time")));
        resp.setNotifyType(request.getParameter("notify_type"));
        resp.setSellerEmail(request.getParameter("seller_email"));
        resp.setBuyerEmail(request.getParameter("buyer_email"));
        resp.setSellerId(request.getParameter("seller_id"));
        resp.setBuyerId(request.getParameter("buyer_id"));
        resp.setTotalFee(new BigDecimal(request.getParameter("total_fee")));
        resp.setBody(request.getParameter("body"));
        resp.setExtraCommonParam(request.getParameter("extra_common_param"));
        
        // 以下是异步回调独有的参数
        resp.setGmtCreate(parse(request.getParameter("gmt_create")));
        resp.setGmtPayment(parse(request.getParameter("gmt_payment")));
        resp.setGmtClose(parse(request.getParameter("gmt_close")));
        resp.setGmtRefund(parse(request.getParameter("gmt_refund")));
        resp.setRefundStatus(request.getParameter("refund_status"));
        resp.setOutChannelAmount(request.getParameter("out_channel_amount"));
        resp.setBusinessScene(request.getParameter("business_scene"));
        
		return resp;
	}
	
	
	@Override
	protected abstract String getName() ;
	
	protected abstract String updateOrderStatusFrom() ;
	
	protected abstract String updateOrderStatusTo() ;

}
