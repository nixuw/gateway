package com.pay.dao.entity;

import java.io.Serializable;
import java.util.Date;

public class GatewayAppAccount implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 17057668265801832L;
	
	public final static String STATUS_INVALID = "0"; // 停用状态 
	public final static String STATUS_VALID = "1"; // 启用状态

	/** 
     * GATEWAY_APP_ACCOUNT.ID
     */
    private Long id;

    /** 
     * GATEWAY_APP_ACCOUNT.ACCOUNT
     */
    private String account;

    /** 
     * GATEWAY_APP_ACCOUNT.ACCOUNT_KEY
     */
    private String accountKey;

    /** 
     * GATEWAY_APP_ACCOUNT.NOTIFY_URL
     */
    private String notifyUrl;

    /** 
     * GATEWAY_APP_ACCOUNT.STATUS
     */
    private String status;

    /** 
     * GATEWAY_APP_ACCOUNT.CREATE_BY
     */
    private Long createBy;

    /** 
     * GATEWAY_APP_ACCOUNT.CREATE_DATE
     */
    private Date createDate;

    /** 
     * GATEWAY_APP_ACCOUNT.UPDATE_BY
     */
    private Long updateBy;

    /** 
     * GATEWAY_APP_ACCOUNT.UPDATE_DATE
     */
    private Date updateDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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