package com.pay.dao;

import com.pay.dao.entity.GatewayAppChannel;
import org.springframework.dao.DataAccessException;

public interface GatewayAppChannelDAO {
    void insert(GatewayAppChannel record) throws DataAccessException;

    int updateById(GatewayAppChannel record) throws DataAccessException;

    int updateByIdSelective(GatewayAppChannel record) throws DataAccessException;

    GatewayAppChannel selectById(Long id) throws DataAccessException;

    int deleteById(Long id) throws DataAccessException;
}