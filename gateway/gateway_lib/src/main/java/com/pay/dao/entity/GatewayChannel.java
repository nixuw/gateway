package com.pay.dao.entity;

import java.io.Serializable;
import java.util.Date;

public class GatewayChannel implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 4547109027806382579L;

	/** 
     * GATEWAY_CHANNEL.ID
     */
    private Long id;

    /** 
     * GATEWAY_CHANNEL.CHANNEL_NAME
     */
    private String channelName;

    /** 
     * GATEWAY_CHANNEL.CHANNEL_CODE
     */
    private String channelCode;

    /** 
     * GATEWAY_CHANNEL.ACCOUNT
     */
    private String account;

    /** 
     * GATEWAY_CHANNEL.ACCOUNT_KEY
     */
    private String accountKey;

    /** 
     * GATEWAY_CHANNEL.PAY_URL
     */
    private String payUrl;

    /** 
     * GATEWAY_CHANNEL.RETURN_URL
     */
    private String returnUrl;

    /** 
     * GATEWAY_CHANNEL.NOTIFY_URL
     */
    private String notifyUrl;

    /** 
     * GATEWAY_CHANNEL.STATUS
     */
    private String status;

    /** 
     * GATEWAY_CHANNEL.CREATE_BY
     */
    private Long createBy;

    /** 
     * GATEWAY_CHANNEL.CREATE_DATE
     */
    private Date createDate;

    /** 
     * GATEWAY_CHANNEL.UPDATE_BY
     */
    private Long updateBy;

    /** 
     * GATEWAY_CHANNEL.UPDATE_DATE
     */
    private Date updateDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAccountKey() {
        return accountKey;
    }

    public void setAccountKey(String accountKey) {
        this.accountKey = accountKey;
    }

    public String getPayUrl() {
        return payUrl;
    }

    public void setPayUrl(String payUrl) {
        this.payUrl = payUrl;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Long getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}