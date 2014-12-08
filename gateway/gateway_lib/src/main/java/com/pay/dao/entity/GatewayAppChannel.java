package com.pay.dao.entity;

import java.io.Serializable;
import java.util.Date;

public class GatewayAppChannel implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -2770610511837828133L;

	/** 
     * GATEWAY_APP_CHANNEL.ID
     */
    private Long id;

    /** 
     * GATEWAY_APP_CHANNEL.CHANNEL_ID
     */
    private Long channelId;

    /** 
     * GATEWAY_APP_CHANNEL.APP_ACCOUNT
     */
    private String appAccount;

    /** 
     * GATEWAY_APP_CHANNEL.STATUS
     */
    private String status;

    /** 
     * GATEWAY_APP_CHANNEL.CREATE_BY
     */
    private Long createBy;

    /** 
     * GATEWAY_APP_CHANNEL.CREATE_DATE
     */
    private Date createDate;

    /** 
     * GATEWAY_APP_CHANNEL.UPDATE_BY
     */
    private Long updateBy;

    /** 
     * GATEWAY_APP_CHANNEL.UPDATE_DATE
     */
    private Date updateDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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