package com.pay.dao.impl;

import com.pay.dao.GatewayUnionpayRequestDAO;
import com.pay.dao.entity.GatewayUnionpayRequest;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.SqlMapClientTemplate;

public class GatewayUnionpayRequestDAOImpl implements GatewayUnionpayRequestDAO {
    /** 
     * gateway_unionpay_request
     */
    private SqlMapClientTemplate sqlMapClient;

    public GatewayUnionpayRequestDAOImpl() {
        super();
    }

    public void setSqlMapClient(SqlMapClientTemplate sqlMapClient) {
        this.sqlMapClient = sqlMapClient;
    }

    public void insert(GatewayUnionpayRequest record) throws DataAccessException {
        sqlMapClient.insert("gateway_unionpay_request.insert", record);
    }

    public int updateById(GatewayUnionpayRequest record) throws DataAccessException {
        int rows = sqlMapClient.update("gateway_unionpay_request.updateById", record);
        return rows;
    }

    public int updateByIdSelective(GatewayUnionpayRequest record) throws DataAccessException {
        int rows = sqlMapClient.update("gateway_unionpay_request.updateByIdSelective", record);
        return rows;
    }

    public GatewayUnionpayRequest selectById(Long id) throws DataAccessException {
        GatewayUnionpayRequest key = new GatewayUnionpayRequest();
        key.setId(id);
        GatewayUnionpayRequest record = (GatewayUnionpayRequest) sqlMapClient.queryForObject("gateway_unionpay_request.selectById", key);
        return record;
    }

    public int deleteById(Long id) throws DataAccessException {
        GatewayUnionpayRequest key = new GatewayUnionpayRequest();
        key.setId(id);
        int rows = sqlMapClient.delete("gateway_unionpay_request.deleteById", key);
        return rows;
    }
}