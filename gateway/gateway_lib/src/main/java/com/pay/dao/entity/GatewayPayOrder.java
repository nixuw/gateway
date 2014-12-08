package com.pay.dao.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class GatewayPayOrder implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 5234492980292028917L;
	
	public static final String STATUS_INIT = "0"; // 待支付
	public static final String STATUS_PAYSUCCESS = "1"; // 支付成功待确认
	public static final String STATUS_CONFIRMSUCCESS = "2"; // 确认支付成功
	
	/** 
     * GATEWAY_PAY_ORDER.ID
     */
    private Long id;

    /** 
     * GATEWAY_PAY_ORDER.APP_REQUEST_ID
     */
    private String appRequestId;

    /** 
     * GATEWAY_PAY_ORDER.ORDER_NO
     */
    private String orderNo;

    /** 
     * GATEWAY_PAY_ORDER.CHANNEL_ID
     */
    private Long channelId;

    /** 
     * GATEWAY_PAY_ORDER.APP_ACCOUNT
     */
    private String appAccount;

    /** 
     * GATEWAY_PAY_ORDER.BIZ_CODE
     */
    private String bizCode;

    /** 
     * GATEWAY_PAY_ORDER.BIZ_DESCRIPTION
     */
    private String bizDescription;

    /** 
     * GATEWAY_PAY_ORDER.USER_NAME
     */
    private String userName;

    /** 
     * GATEWAY_PAY_ORDER.AMOUNT
     */
    private BigDecimal amount;

    /** 
     * GATEWAY_PAY_ORDER.ECHO_CONTENT
     */
    private String echoContent;

    /** 
     * GATEWAY_PAY_ORDER.PARTER_SERIAL_NUM
     */
    private String parterSerialNum;

    /** 
     * GATEWAY_PAY_ORDER.STATUS
     */
    private String status;

    /** 
     * GATEWAY_PAY_ORDER.CREATE_DATE
     */
    private Date createDate;

    /** 
     * GATEWAY_PAY_ORDER.UPDATE_DATE
     */
    private Date updateDate;
    
    private Date paymentDate;
    
    private String userIp;
  
	// 与数据库不对应的列
    private String fromStatus;
    
    public String getFromStatus() {
		return fromStatus;
	}
    public void setFromStatus(String fromStatus) {
		this.fromStatus = fromStatus;
	}
    
    
 	public String getUserIp() {
 		return userIp;
 	}
 	public void setUserIp(String userIp) {
 		this.userIp = userIp;
 	}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAppRequestId() {
        return appRequestId;
    }

    public void setAppRequestId(String appRequestId) {
        this.appRequestId = appRequestId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

    public String getAppAccount() {
        return appAccount;
    }

    public void setAppAccount(String appAccount) {
        this.appAccount = appAccount;
    }

    public String getBizCode() {
        return bizCode;
    }

    public void setBizCode(String bizCode) {
        this.bizCode = bizCode;
    }

    public String getBizDescription() {
        return bizDescription;
    }

    public void setBizDescription(String bizDescription) {
        this.bizDescription = bizDescription;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getEchoContent() {
        return echoContent;
    }

    public void setEchoContent(String echoContent) {
        this.echoContent = echoContent;
    }

    public String getParterSerialNum() {
        return parterSerialNum;
    }

    public void setParterSerialNum(String parterSerialNum) {
        this.parterSerialNum = parterSerialNum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
    

    public Date getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
}