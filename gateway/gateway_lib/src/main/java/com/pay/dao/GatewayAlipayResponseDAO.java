package com.pay.dao;

import com.pay.dao.entity.GatewayAlipayResponse;
import org.springframework.dao.DataAccessException;

public interface GatewayAlipayResponseDAO {
    void insert(GatewayAlipayResponse record) throws DataAccessException;

    int updateById(GatewayAlipayResponse record) throws DataAccessException;

    int updateByIdSelective(GatewayAlipayResponse record) throws DataAccessException;

    GatewayAlipayResponse selectById(Long id) throws DataAccessException;

    int deleteById(Long id) throws DataAccessException;
}