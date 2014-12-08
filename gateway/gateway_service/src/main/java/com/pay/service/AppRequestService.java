package com.pay.service;

import com.pay.dao.entity.GatewayAppRequest;

public interface AppRequestService {
    /**
     * @param appReq
     * @return ID
     */
    public long saveRequest (GatewayAppRequest appReq);
    
    public GatewayAppRequest getAppRequest(long appRequestId);
}
