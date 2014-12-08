package com.pay.dao.impl;

import com.pay.dao.GatewayUnionpayResponseDAO;
import com.pay.dao.entity.GatewayUnionpayResponse;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.SqlMapClientTemplate;

public class GatewayUnionpayResponseDAOImpl implements GatewayUnionpayResponseDAO {
    /** 
     * gateway_unionpay_response
     */
    private SqlMapClientTemplate sqlMapClient;

    public GatewayUnionpayResponseDAOImpl() {
        super();
    }

    public void setSqlMapClient(SqlMapClientTemplate sqlMapClient) {
        this.sqlMapClient = sqlMapClient;
    }

    public void insert(GatewayUnionpayResponse record) throws DataAccessException {
        sqlMapClient.insert("gateway_unionpay_response.insert", record);
    }

    public int updateById(GatewayUnionpayResponse record) throws DataAccessException {
        int rows = sqlMapClient.update("gateway_unionpay_response.updateById", record);
        return rows;
    }

    public int updateByIdSelective(GatewayUnionpayResponse record) throws DataAccessException {
        int rows = sqlMapClient.update("gateway_unionpay_response.updateByIdSelective", record);
        return rows;
    }

    public GatewayUnionpayResponse selectById(Long id) throws DataAccessException {
        GatewayUnionpayResponse key = new GatewayUnionpayResponse();
        key.setId(id);
        GatewayUnionpayResponse record = (GatewayUnionpayResponse) sqlMapClient.queryForObject("gateway_unionpay_response.selectById", key);
        return record;
    }

    public int deleteById(Long id) throws DataAccessException {
        GatewayUnionpayResponse key = new GatewayUnionpayResponse();
        key.setId(id);
        int rows = sqlMapClient.delete("gateway_unionpay_response.deleteById", key);
        return rows;
    }
}