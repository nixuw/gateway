package com.pay.dao.entity;

import java.io.Serializable;
import java.util.Date;

public class GatewayAppNotify implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 5934554187184631163L;

	public static final String STATUS_WAIT = "0";
	public static final String STATUS_SUCESS = "1";
	public static final String STATUS_FAIL = "2";
	/** 
     * GATEWAY_APP_NOTIFY.ID
     */
    private Long id;

    /** 
     * GATEWAY_APP_NOTIFY.GATEWAY_ORDER_NO
     */
    private String gatewayOrderNo;

    /** 
     * GATEWAY_APP_NOTIFY.RETRY_COUNT
     */
    private Short retryCount;

    /** 
     * GATEWAY_APP_NOTIFY.LAST_RETRY_DATE
     */
    private Date lastRetryDate;

    /** 
     * GATEWAY_APP_NOTIFY.STATUS
     */
    private String status;

    /** 
     * GATEWAY_APP_NOTIFY.GATEWAY_ORDER_STATUS
     */
    private String gatewayOrderStatus;

    /** 
     * GATEWAY_APP_NOTIFY.SIGN
     */
    private String sign;

    /** 
     * GATEWAY_APP_NOTIFY.CREATE_DATE
     */
    private Date createDate;

    /** 
     * GATEWAY_APP_NOTIFY.UPDATE_DATE
     */
    private Date updateDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGatewayOrderNo() {
        return gatewayOrderNo;
    }

    public void setGatewayOrderNo(String gatewayOrderNo) {
        this.gatewayOrderNo = gatewayOrderNo;
    }

    public Short getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(Short retryCount) {
        this.retryCount = retryCount;
    }

    public Date getLastRetryDate() {
        return lastRetryDate;
    }

    public void setLastRetryDate(Date lastRetryDate) {
        this.lastRetryDate = lastRetryDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGatewayOrderStatus() {
        return gatewayOrderStatus;
    }

    public void setGatewayOrderStatus(String gatewayOrderStatus) {
        this.gatewayOrderStatus = gatewayOrderStatus;
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

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}