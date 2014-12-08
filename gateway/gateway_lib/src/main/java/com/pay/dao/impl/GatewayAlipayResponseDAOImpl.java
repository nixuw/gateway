package com.pay.dao.impl;

import com.pay.dao.GatewayAlipayResponseDAO;
import com.pay.dao.entity.GatewayAlipayResponse;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.SqlMapClientTemplate;

public class GatewayAlipayResponseDAOImpl implements GatewayAlipayResponseDAO {
    /** 
     * GATEWAY_ALIPAY_RESPONSE
     */
    private SqlMapClientTemplate sqlMapClient;

    public GatewayAlipayResponseDAOImpl() {
        super();
    }

    public void setSqlMapClient(SqlMapClientTemplate sqlMapClient) {
        this.sqlMapClient = sqlMapClient;
    }

    public void insert(GatewayAlipayResponse record) throws DataAccessException {
        sqlMapClient.insert("GATEWAY_ALIPAY_RESPONSE.insert", record);
    }

    public int updateById(GatewayAlipayResponse record) throws DataAccessException {
        int rows = sqlMapClient.update("GATEWAY_ALIPAY_RESPONSE.updateById", record);
        return rows;
    }

    public int updateByIdSelective(GatewayAlipayResponse record) throws DataAccessException {
        int rows = sqlMapClient.update("GATEWAY_ALIPAY_RESPONSE.updateByIdSelective", record);
        return rows;
    }

    public GatewayAlipayResponse selectById(Long id) throws DataAccessException {
        GatewayAlipayResponse key = new GatewayAlipayResponse();
        key.setId(id);
        GatewayAlipayResponse record = (GatewayAlipayResponse) sqlMapClient.queryForObject("GATEWAY_ALIPAY_RESPONSE.selectById", key);
        return record;
    }

    public int deleteById(Long id) throws DataAccessException {
        GatewayAlipayResponse key = new GatewayAlipayResponse();
        key.setId(id);
        int rows = sqlMapClient.delete("GATEWAY_ALIPAY_RESPONSE.deleteById", key);
        return rows;
    }
}