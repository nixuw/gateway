package com.pay.dao.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

public class GatewayAppRequest implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 7339878944760874251L;

	public final static BigDecimal ZERO = new BigDecimal(0);
	/** 
     * GATEWAY_APP_REQUEST.ID
     */
    private Long id;

    /** 
     * GATEWAY_APP_REQUEST.APP_ACCOUNT
     */
    private String appAccount;

    /** 
     * GATEWAY_APP_REQUEST.BIZ_CODE
     */
    private String bizCode;

    /** 
     * GATEWAY_APP_REQUEST.REQUEST_TOKEN
     */
    private String requestToken;

    /** 
     * GATEWAY_APP_REQUEST.BIZ_DESCRIPTION
     */
    private String bizDescription;

    /** 
     * GATEWAY_APP_REQUEST.USER_NAME
     */
    private String userName;

    /** 
     * GATEWAY_APP_REQUEST.AMOUNT
     */
    private BigDecimal amount;

    /** 
     * GATEWAY_APP_REQUEST.ECHO_CONTENT
     */
    private String echoContent;

    /** 
     * GATEWAY_APP_REQUEST.SIGN
     */
    private String sign;

    /** 
     * GATEWAY_APP_REQUEST.CREATE_DATE
     */
    private Date createDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getRequestToken() {
        return requestToken;
    }

    public void setRequestToken(String requestToken) {
        this.requestToken = requestToken;
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

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public boolean valide() {
		
		return StringUtils.isNotBlank(appAccount) 
				&& StringUtils.isNotBlank(bizCode) 
				&& StringUtils.isNotBlank(requestToken) 
				&& StringUtils.isNotBlank(bizDescription) 
				&& StringUtils.isNotBlank(sign) 
				&& amount != null && amount.compareTo(ZERO) > 0;
	}
    
    public String buildMd5Str(){
    	StringBuffer buf = new StringBuffer();
    	buf.append(appAccount).append("|")
    	.append(bizCode).append("|")
    	.append(requestToken).append("|").append(amount);
    	return buf.toString();
    }
    
	@Override
	public String toString() {
		return "应用系统支付请求: GatewayAppRequest [id=" + id + ", appAccount=" + appAccount
				+ ", bizCode=" + bizCode + ", requestToken=" + requestToken
				+ ", bizDescription=" + bizDescription + ", userName="
				+ userName + ", amount=" + amount + ", echoContent="
				+ echoContent + ", sign=" + sign + ", createDate=" + createDate
				+ "]";
	}

	
    
    
}