package com.pay.dao;

import com.pay.dao.entity.GatewayAppRequest;
import org.springframework.dao.DataAccessException;

public interface GatewayAppRequestDAO {
    long insert(GatewayAppRequest record) throws DataAccessException;

    int updateById(GatewayAppRequest record) throws DataAccessException;

    int updateByIdSelective(GatewayAppRequest record) throws DataAccessException;

    GatewayAppRequest selectById(Long id) throws DataAccessException;

    int deleteById(Long id) throws DataAccessException;
}