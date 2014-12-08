package com.pay.dao;

import com.pay.dao.entity.GatewayPayOrder;

import org.springframework.dao.DataAccessException;

public interface GatewayPayOrderDAO {
    long insert(GatewayPayOrder record) throws DataAccessException;

    int updateById(GatewayPayOrder record) throws DataAccessException;

    int updateByIdSelective(GatewayPayOrder record) throws DataAccessException;

    GatewayPayOrder selectById(Long id) throws DataAccessException;

    int deleteById(Long id) throws DataAccessException;

	GatewayPayOrder queryByOrderNo(String orderNo);
	
}