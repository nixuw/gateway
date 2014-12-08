package com.pay.dao;

import com.pay.dao.entity.GatewayAlipayRequest;
import org.springframework.dao.DataAccessException;

public interface GatewayAlipayRequestDAO {
    void insert(GatewayAlipayRequest record) throws DataAccessException;

    int updateById(GatewayAlipayRequest record) throws DataAccessException;

    int updateByIdSelective(GatewayAlipayRequest record) throws DataAccessException;

    GatewayAlipayRequest selectById(Long id) throws DataAccessException;

    int deleteById(Long id) throws DataAccessException;
}