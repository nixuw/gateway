package com.pay.dao.impl;

import com.pay.dao.GatewayAppChannelDAO;
import com.pay.dao.entity.GatewayAppChannel;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.SqlMapClientTemplate;

public class GatewayAppChannelDAOImpl implements GatewayAppChannelDAO {
    /** 
     * GATEWAY_APP_CHANNEL
     */
    private SqlMapClientTemplate sqlMapClient;

    public GatewayAppChannelDAOImpl() {
        super();
    }

    public void setSqlMapClient(SqlMapClientTemplate sqlMapClient) {
        this.sqlMapClient = sqlMapClient;
    }

    public void insert(GatewayAppChannel record) throws DataAccessException {
        sqlMapClient.insert("GATEWAY_APP_CHANNEL.insert", record);
    }

    public int updateById(GatewayAppChannel record) throws DataAccessException {
        int rows = sqlMapClient.update("GATEWAY_APP_CHANNEL.updateById", record);
        return rows;
    }

    public int updateByIdSelective(GatewayAppChannel record) throws DataAccessException {
        int rows = sqlMapClient.update("GATEWAY_APP_CHANNEL.updateByIdSelective", record);
        return rows;
    }

    public GatewayAppChannel selectById(Long id) throws DataAccessException {
        GatewayAppChannel key = new GatewayAppChannel();
        key.setId(id);
        GatewayAppChannel record = (GatewayAppChannel) sqlMapClient.queryForObject("GATEWAY_APP_CHANNEL.selectById", key);
        return record;
    }

    public int deleteById(Long id) throws DataAccessException {
        GatewayAppChannel key = new GatewayAppChannel();
        key.setId(id);
        int rows = sqlMapClient.delete("GATEWAY_APP_CHANNEL.deleteById", key);
        return rows;
    }
}