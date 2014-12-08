package com.pay.dao.impl;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.pay.dao.GatewayAlipayRequestDAO;
import com.pay.dao.entity.GatewayAlipayRequest;

public class GatewayAlipayRequestDAOImpl implements GatewayAlipayRequestDAO {
    /** 
     * GATEWAY_ALIPAY_REQUEST
     */
    private SqlMapClientTemplate sqlMapClient;

    public GatewayAlipayRequestDAOImpl() {
        super();
    }

    public void setSqlMapClient(SqlMapClientTemplate sqlMapClient) {
        this.sqlMapClient = sqlMapClient;
    }

    public void insert(GatewayAlipayRequest record) throws DataAccessException {
        sqlMapClient.insert("GATEWAY_ALIPAY_REQUEST.insert", record);
    }

    public int updateById(GatewayAlipayRequest record) throws DataAccessException {
        int rows = sqlMapClient.update("GATEWAY_ALIPAY_REQUEST.updateById", record);
        return rows;
    }

    public int updateByIdSelective(GatewayAlipayRequest record) throws DataAccessException {
        int rows = sqlMapClient.update("GATEWAY_ALIPAY_REQUEST.updateByIdSelective", record);
        return rows;
    }

    public GatewayAlipayRequest selectById(Long id) throws DataAccessException {
        GatewayAlipayRequest key = new GatewayAlipayRequest();
        key.setId(id);
        GatewayAlipayRequest record = (GatewayAlipayRequest) sqlMapClient.queryForObject("GATEWAY_ALIPAY_REQUEST.selectById", key);
        return record;
    }

    public int deleteById(Long id) throws DataAccessException {
        GatewayAlipayRequest key = new GatewayAlipayRequest();
        key.setId(id);
        int rows = sqlMapClient.delete("GATEWAY_ALIPAY_REQUEST.deleteById", key);
        return rows;
    }
}