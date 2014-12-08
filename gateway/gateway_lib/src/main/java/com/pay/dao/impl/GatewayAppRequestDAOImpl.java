package com.pay.dao.impl;

import com.pay.dao.GatewayAppRequestDAO;
import com.pay.dao.entity.GatewayAppRequest;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.SqlMapClientTemplate;

public class GatewayAppRequestDAOImpl implements GatewayAppRequestDAO {
    /** 
     * GATEWAY_APP_REQUEST
     */
    private SqlMapClientTemplate sqlMapClient;

    public GatewayAppRequestDAOImpl() {
        super();
    }

    public void setSqlMapClient(SqlMapClientTemplate sqlMapClient) {
        this.sqlMapClient = sqlMapClient;
    }

    public long insert(GatewayAppRequest record) throws DataAccessException {
        Long id = (Long)sqlMapClient.insert("GATEWAY_APP_REQUEST.insert", record);
        return id == null ? -1L:id.longValue();
       
    }

    public int updateById(GatewayAppRequest record) throws DataAccessException {
        int rows = sqlMapClient.update("GATEWAY_APP_REQUEST.updateById", record);
        return rows;
    }

    public int updateByIdSelective(GatewayAppRequest record) throws DataAccessException {
        int rows = sqlMapClient.update("GATEWAY_APP_REQUEST.updateByIdSelective", record);
        return rows;
    }

    public GatewayAppRequest selectById(Long id) throws DataAccessException {
        GatewayAppRequest key = new GatewayAppRequest();
        key.setId(id);
        GatewayAppRequest record = (GatewayAppRequest) sqlMapClient.queryForObject("GATEWAY_APP_REQUEST.selectById", key);
        return record;
    }

    public int deleteById(Long id) throws DataAccessException {
        GatewayAppRequest key = new GatewayAppRequest();
        key.setId(id);
        int rows = sqlMapClient.delete("GATEWAY_APP_REQUEST.deleteById", key);
        return rows;
    }
}