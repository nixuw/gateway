package com.pay.dao;

import java.util.List;

import com.pay.dao.entity.GatewayAppNotify;

import org.springframework.dao.DataAccessException;

public interface GatewayAppNotifyDAO {
    void insert(GatewayAppNotify record) throws DataAccessException;

    int updateById(GatewayAppNotify record) throws DataAccessException;

    int updateByIdSelective(GatewayAppNotify record) throws DataAccessException;

    GatewayAppNotify selectById(Long id) throws DataAccessException;

    int deleteById(Long id) throws DataAccessException;

	GatewayAppNotify select(String orderNo, String status);

	List<GatewayAppNotify> selectFailed(int maxSize);
}