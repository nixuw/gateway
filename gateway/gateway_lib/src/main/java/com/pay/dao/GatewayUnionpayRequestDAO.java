package com.pay.dao;

import com.pay.dao.entity.GatewayUnionpayRequest;
import org.springframework.dao.DataAccessException;

public interface GatewayUnionpayRequestDAO {
    void insert(GatewayUnionpayRequest record) throws DataAccessException;

    int updateById(GatewayUnionpayRequest record) throws DataAccessException;

    int updateByIdSelective(GatewayUnionpayRequest record) throws DataAccessException;

    GatewayUnionpayRequest selectById(Long id) throws DataAccessException;

    int deleteById(Long id) throws DataAccessException;
}