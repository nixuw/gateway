package com.pay.dao.entity;

import java.math.BigDecimal;
import java.util.Date;

public class GatewayAlipayResponse {
    /** 
     * GATEWAY_ALIPAY_RESPONSE.ID
     */
    private Long id;

    /** 
     * GATEWAY_ALIPAY_RESPONSE.IS_SUCCESS
     */
    private String isSuccess;

    /** 
     * GATEWAY_ALIPAY_RESPONSE.SIGN
     */
    private String sign;

    /** 
     * GATEWAY_ALIPAY_RESPONSE.OUT_TRADE_NO
     */
    private String outTradeNo;

    /** 
     * GATEWAY_ALIPAY_RESPONSE.SUBJECT
     */
    private String subject;

    /** 
     * GATEWAY_ALIPAY_RESPONSE.PAYMENT_TYPE
     */
    private String paymentType;

    /** 
     * GATEWAY_ALIPAY_RESPONSE.EXTERFACE
     */
    private String exterface;

    /** 
     * GATEWAY_ALIPAY_RESPONSE.TRADE_NO
     */
    private String tradeNo;

    /** 
     * GATEWAY_ALIPAY_RESPONSE.TRADE_STATUS
     */
    private String tradeStatus;

    /** 
     * GATEWAY_ALIPAY_RESPONSE.NOTIFY_ID
     */
    private String notifyId;

    /** 
     * GATEWAY_ALIPAY_RESPONSE.NOTIFY_TIME
     */
    private Date notifyTime;

    /** 
     * GATEWAY_ALIPAY_RESPONSE.NOTIFY_TYPE
     */
    private String notifyType;

    /** 
     * GATEWAY_ALIPAY_RESPONSE.SELLER_EMAIL
     */
    private String sellerEmail;

    /** 
     * GATEWAY_ALIPAY_RESPONSE.BUYER_EMAIL
     */
    private String buyerEmail;

    /** 
     * GATEWAY_ALIPAY_RESPONSE.SELLER_ID
     */
    private String sellerId;

    /** 
     * GATEWAY_ALIPAY_RESPONSE.BUYER_ID
     */
    private String buyerId;

    /** 
     * GATEWAY_ALIPAY_RESPONSE.TOTAL_FEE
     */
    private BigDecimal totalFee;

    /** 
     * GATEWAY_ALIPAY_RESPONSE.BODY
     */
    private String body;

    /** 
     * GATEWAY_ALIPAY_RESPONSE.EXTRA_COMMON_PARAM
     */
    private String extraCommonParam;

    /** 
     * GATEWAY_ALIPAY_RESPONSE.GMT_CREATE
     */
    private Date gmtCreate;

    /** 
     * GATEWAY_ALIPAY_RESPONSE.GMT_PAYMENT
     */
    private Date gmtPayment;

    /** 
     * GATEWAY_ALIPAY_RESPONSE.GMT_CLOSE
     */
    private Date gmtClose;

    /** 
     * GATEWAY_ALIPAY_RESPONSE.REFUND_STATUS
     */
    private String refundStatus;

    /** 
     * GATEWAY_ALIPAY_RESPONSE.GMT_REFUND
     */
    private Date gmtRefund;

    /** 
     * GATEWAY_ALIPAY_RESPONSE.OUT_CHANNEL_AMOUNT
     */
    private String outChannelAmount;

    /** 
     * GATEWAY_ALIPAY_RESPONSE.BUSINESS_SCENE
     */
    private String businessScene;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(String isSuccess) {
        this.isSuccess = isSuccess;
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

    public String getExterface() {
        return exterface;
    }

    public void setExterface(String exterface) {
        this.exterface = exterface;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getTradeStatus() {
        return tradeStatus;
    }

    public void setTradeStatus(String tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    public String getNotifyId() {
        return notifyId;
    }

    public void setNotifyId(String notifyId) {
        this.notifyId = notifyId;
    }

    public Date getNotifyTime() {
        return notifyTime;
    }

    public void setNotifyTime(Date notifyTime) {
        this.notifyTime = notifyTime;
    }

    public String getNotifyType() {
        return notifyType;
    }

    public void setNotifyType(String notifyType) {
        this.notifyType = notifyType;
    }

    public String getSellerEmail() {
        return sellerEmail;
    }

    public void setSellerEmail(String sellerEmail) {
        this.sellerEmail = sellerEmail;
    }

    public String getBuyerEmail() {
        return buyerEmail;
    }

    public void setBuyerEmail(String buyerEmail) {
        this.buyerEmail = buyerEmail;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
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

    public String getExtraCommonParam() {
        return extraCommonParam;
    }

    public void setExtraCommonParam(String extraCommonParam) {
        this.extraCommonParam = extraCommonParam;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtPayment() {
        return gmtPayment;
    }

    public void setGmtPayment(Date gmtPayment) {
        this.gmtPayment = gmtPayment;
    }

    public Date getGmtClose() {
        return gmtClose;
    }

    public void setGmtClose(Date gmtClose) {
        this.gmtClose = gmtClose;
    }

    public String getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(String refundStatus) {
        this.refundStatus = refundStatus;
    }

    public Date getGmtRefund() {
        return gmtRefund;
    }

    public void setGmtRefund(Date gmtRefund) {
        this.gmtRefund = gmtRefund;
    }

    public String getOutChannelAmount() {
        return outChannelAmount;
    }

    public void setOutChannelAmount(String outChannelAmount) {
        this.outChannelAmount = outChannelAmount;
    }

    public String getBusinessScene() {
        return businessScene;
    }

    public void setBusinessScene(String businessScene) {
        this.businessScene = businessScene;
    }
}