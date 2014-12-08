package com.pay.dao.entity;

import java.math.BigDecimal;
import java.util.Date;

public class GatewayUnionpayRequest {
    /** 
     * gateway_unionpay_request.ID
     */
    private Long id;

    /** 
     * gateway_unionpay_request.VERSION
     */
    private String version;

    /** 
     * gateway_unionpay_request.TRANS_TYPE
     */
    private String transType;

    /** 
     * gateway_unionpay_request.ORIGQ_ID
     */
    private String origqId;

    /** 
     * gateway_unionpay_request.COMMODITY_URL
     */
    private String commodityUrl;

    /** 
     * gateway_unionpay_request.COMMODITY_NAME
     */
    private String commodityName;

    /** 
     * gateway_unionpay_request.ORDER_NUMBER
     */
    private String orderNumber;

    /** 
     * gateway_unionpay_request.ORDER_AMOUNT
     */
    private BigDecimal orderAmount;

    /** 
     * gateway_unionpay_request.ORDER_CURRENCY
     */
    private String orderCurrency;

    /** 
     * gateway_unionpay_request.ORDER_TIME
     */
    private Date orderTime;

    /** 
     * gateway_unionpay_request.CUSTOMER_IP
     */
    private String customerIp;

    /** 
     * gateway_unionpay_request.CUSTOMER_NAME
     */
    private String customerName;

    /** 
     * gateway_unionpay_request.TRANS_TIMEOUT
     */
    private Long transTimeout;

    /** 
     * gateway_unionpay_request.SIGNATURE
     */
    private String signature;

    /** 
     * gateway_unionpay_request.CREATE_DATE
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

    public String getTransType() {
        return transType;
    }

    public void setTransType(String transType) {
        this.transType = transType;
    }

    public String getOrigqId() {
        return origqId;
    }

    public void setOrigqId(String origqId) {
        this.origqId = origqId;
    }

    public String getCommodityUrl() {
        return commodityUrl;
    }

    public void setCommodityUrl(String commodityUrl) {
        this.commodityUrl = commodityUrl;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
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

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public String getCustomerIp() {
        return customerIp;
    }

    public void setCustomerIp(String customerIp) {
        this.customerIp = customerIp;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Long getTransTimeout() {
        return transTimeout;
    }

    public void setTransTimeout(Long transTimeout) {
        this.transTimeout = transTimeout;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}