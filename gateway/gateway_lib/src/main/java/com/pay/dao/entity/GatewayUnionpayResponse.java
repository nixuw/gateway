package com.pay.dao.entity;

import java.math.BigDecimal;
import java.util.Date;

public class GatewayUnionpayResponse {
    /** 
     * gateway_unionpay_response.ID
     */
    private Long id;

    /** 
     * gateway_unionpay_response.VERSION
     */
    private String version;

    /** 
     * gateway_unionpay_response.CHARSET
     */
    private String charset;

    /** 
     * gateway_unionpay_response.SIGN_METHOD
     */
    private String signMethod;

    /** 
     * gateway_unionpay_response.SIGNATURE
     */
    private String signature;

    /** 
     * gateway_unionpay_response.TRANS_TYPE
     */
    private String transType;

    /** 
     * gateway_unionpay_response.RESP_CODE
     */
    private String respCode;

    /** 
     * gateway_unionpay_response.RESP_MSG
     */
    private String respMsg;

    /** 
     * gateway_unionpay_response.MER_ABBR
     */
    private String merAbbr;

    /** 
     * gateway_unionpay_response.MER_ID
     */
    private String merId;

    /** 
     * gateway_unionpay_response.ORDER_NUMBER
     */
    private String orderNumber;

    /** 
     * gateway_unionpay_response.TRACE_NUMBER
     */
    private String traceNumber;

    /** 
     * gateway_unionpay_response.TRACE_TIME
     */
    private String traceTime;

    /** 
     * gateway_unionpay_response.QID
     */
    private String qid;

    /** 
     * gateway_unionpay_response.ORDER_AMOUNT
     */
    private BigDecimal orderAmount;

    /** 
     * gateway_unionpay_response.ORDER_CURRENCY
     */
    private String orderCurrency;

    /** 
     * gateway_unionpay_response.RESP_TIME
     */
    private Date respTime;

    /** 
     * gateway_unionpay_response.SETTLE_AMOUNT
     */
    private BigDecimal settleAmount;

    /** 
     * gateway_unionpay_response.SETTLE_CURRENCY
     */
    private String settleCurrency;

    /** 
     * gateway_unionpay_response.SETTLE_DATE
     */
    private String settleDate;

    /** 
     * gateway_unionpay_response.EXCHANGE_RATE
     */
    private String exchangeRate;

    /** 
     * gateway_unionpay_response.EXCHANGE_DATE
     */
    private String exchangeDate;

    /** 
     * gateway_unionpay_response.CUP_RESERVED
     */
    private String cupReserved;

    /** 
     * gateway_unionpay_response.CREATE_DATE
     */
    private Date createDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getSignMethod() {
        return signMethod;
    }

    public void setSignMethod(String signMethod) {
        this.signMethod = signMethod;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getTransType() {
        return transType;
    }

    public void setTransType(String transType) {
        this.transType = transType;
    }

    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public String getRespMsg() {
        return respMsg;
    }

    public void setRespMsg(String respMsg) {
        this.respMsg = respMsg;
    }

    public String getMerAbbr() {
        return merAbbr;
    }

    public void setMerAbbr(String merAbbr) {
        this.merAbbr = merAbbr;
    }

    public String getMerId() {
        return merId;
    }

    public void setMerId(String merId) {
        this.merId = merId;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getTraceNumber() {
        return traceNumber;
    }

    public void setTraceNumber(String traceNumber) {
        this.traceNumber = traceNumber;
    }

    public String getTraceTime() {
        return traceTime;
    }

    public void setTraceTime(String traceTime) {
        this.traceTime = traceTime;
    }

    public String getQid() {
        return qid;
    }

    public void setQid(String qid) {
        this.qid = qid;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getOrderCurrency() {
        return orderCurrency;
    }

    public void setOrderCurrency(String orderCurrency) {
        this.orderCurrency = orderCurrency;
    }

    public Date getRespTime() {
        return respTime;
    }

    public void setRespTime(Date respTime) {
        this.respTime = respTime;
    }

    public BigDecimal getSettleAmount() {
        return settleAmount;
    }

    public void setSettleAmount(BigDecimal settleAmount) {
        this.settleAmount = settleAmount;
    }

    public String getSettleCurrency() {
        return settleCurrency;
    }

    public void setSettleCurrency(String settleCurrency) {
        this.settleCurrency = settleCurrency;
    }

    public String getSettleDate() {
        return settleDate;
    }

    public void setSettleDate(String settleDate) {
        this.settleDate = settleDate;
    }

    public String getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(String exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public String getExchangeDate() {
        return exchangeDate;
    }

    public void setExchangeDate(String exchangeDate) {
        this.exchangeDate = exchangeDate;
    }

    public String getCupReserved() {
        return cupReserved;
    }

    public void setCupReserved(String cupReserved) {
        this.cupReserved = cupReserved;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}