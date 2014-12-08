package com.pay.dao.impl;

import com.pay.dao.GatewayChannelDAO;
import com.pay.dao.entity.GatewayChannel;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.SqlMapClientTemplate;

public class GatewayChannelDAOImpl implements GatewayChannelDAO {
    /** 
     * GATEWAY_CHANNEL
     */
    private SqlMapClientTemplate sqlMapClient;

    public GatewayChannelDAOImpl() {
        super();
    }

    public void setSqlMapClient(SqlMapClientTemplate sqlMapClient) {
        this.sqlMapClient = sqlMapClient;
    }

    public void insert(GatewayChannel record) throws DataAccessException {
        sqlMapClient.insert("GATEWAY_CHANNEL.insert", record);
    }

    public int updateById(GatewayChannel record) throws DataAccessException {
        int rows = sqlMapClient.update("GATEWAY_CHANNEL.updateById", record);
        return rows;
    }

    public int updateByIdSelective(GatewayChannel record) throws DataAccessException {
        int rows = sqlMapClient.update("GATEWAY_CHANNEL.updateByIdSelective", record);
        return rows;
    }

    public GatewayChannel selectById(Long id) throws DataAccessException {
        GatewayChannel key = new GatewayChannel();
        key.setId(id);
        GatewayChannel record = (GatewayChannel) sqlMapClient.queryForObject("GATEWAY_CHANNEL.selectById", key);
        return record;
    }

    public int deleteById(Long id) throws DataAccessException {
        GatewayChannel key = new GatewayChannel();
        key.setId(id);
        int rows = sqlMapClient.delete("GATEWAY_CHANNEL.deleteById", key);
        return rows;
    }
}