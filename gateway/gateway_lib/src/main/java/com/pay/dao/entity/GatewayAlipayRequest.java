package com.pay.dao.entity;

import java.math.BigDecimal;

public class GatewayAlipayRequest {
    /** 
     * GATEWAY_ALIPAY_REQUEST.ID
     */
    private Long id;

    /** 
     * GATEWAY_ALIPAY_REQUEST.SERVICE
     */
    private String service;

    /** 
     * GATEWAY_ALIPAY_REQUEST.SIGN
     */
    private String sign;

    /** 
     * GATEWAY_ALIPAY_REQUEST.OUT_TRADE_NO
     */
    private String outTradeNo;

    /** 
     * GATEWAY_ALIPAY_REQUEST.SUBJECT
     */
    private String subject;

    /** 
     * GATEWAY_ALIPAY_REQUEST.PAYMENT_TYPE
     */
    private String paymentType;

    /** 
     * GATEWAY_ALIPAY_REQUEST.TOTAL_FEE
     */
    private BigDecimal totalFee;

    /** 
     * GATEWAY_ALIPAY_REQUEST.BODY
     */
    private String body;

    /** 
     * GATEWAY_ALIPAY_REQUEST.SHOW_URL
     */
    private String showUrl;

    /** 
     * GATEWAY_ALIPAY_REQUEST.EXTRA_COMMON_PARAM
     */
    private String extraCommonParam;

    /** 
     * GATEWAY_ALIPAY_REQUEST.QR_PAY_MODE
     */
    private String qrPayMode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public BigDecimal getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(BigDecimal totalFee) {
        this.totalFee = totalFee;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getShowUrl() {
        return showUrl;
    }

    public void setShowUrl(String showUrl) {
        this.showUrl = showUrl;
    }

    public String getExtraCommonParam() {
        return extraCommonParam;
    }

    public void setExtraCommonParam(String extraCommonParam) {
        this.extraCommonParam = extraCommonParam;
    }

    public String getQrPayMode() {
        return qrPayMode;
    }

    public void setQrPayMode(String qrPayMode) {
        this.qrPayMode = qrPayMode;
    }
}