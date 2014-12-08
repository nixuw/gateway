package com.pay.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.pay.dao.entity.GatewayAppAccount;
import com.pay.dao.vo.ValidPayChannel;

public interface GatewayAppAccountDAO {
    void insert(GatewayAppAccount record) throws DataAccessException;

    int updateById(GatewayAppAccount record) throws DataAccessException;

    int updateByIdSelective(GatewayAppAccount record) throws DataAccessException;

    GatewayAppAccount selectById(Long id) throws DataAccessException;

    int deleteById(Long id) throws DataAccessException;
    
    GatewayAppAccount selectByAccount(String account) throws DataAccessException;

    List<ValidPayChannel> selectValidPayChannel(Map  parmas);
}