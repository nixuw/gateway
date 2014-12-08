package com.pay.third;

import java.util.Map;

import com.pay.dao.entity.GatewayPayOrder;

public interface ThirdPayService {
    public Map<String,String> buildRequest(GatewayPayOrder order);
}
