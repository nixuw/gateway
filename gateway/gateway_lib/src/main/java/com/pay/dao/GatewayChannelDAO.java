package com.pay.dao;

import com.pay.dao.entity.GatewayChannel;
import org.springframework.dao.DataAccessException;

public interface GatewayChannelDAO {
    void insert(GatewayChannel record) throws DataAccessException;

    int updateById(GatewayChannel record) throws DataAccessException;

    int updateByIdSelective(GatewayChannel record) throws DataAccessException;

    GatewayChannel selectById(Long id) throws DataAccessException;

    int deleteById(Long id) throws DataAccessException;
}