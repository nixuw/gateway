package com.pay.dao;

import com.pay.dao.entity.GatewayUnionpayResponse;
import org.springframework.dao.DataAccessException;

public interface GatewayUnionpayResponseDAO {
    void insert(GatewayUnionpayResponse record) throws DataAccessException;

    int updateById(GatewayUnionpayResponse record) throws DataAccessException;

    int updateByIdSelective(GatewayUnionpayResponse record) throws DataAccessException;

    GatewayUnionpayResponse selectById(Long id) throws DataAccessException;

    int deleteById(Long id) throws DataAccessException;
}