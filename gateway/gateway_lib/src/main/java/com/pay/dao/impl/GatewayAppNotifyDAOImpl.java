package com.pay.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.pay.dao.GatewayAppNotifyDAO;
import com.pay.dao.entity.GatewayAppNotify;

public class GatewayAppNotifyDAOImpl implements GatewayAppNotifyDAO {
    /** 
     * GATEWAY_APP_NOTIFY
     */
    private SqlMapClientTemplate sqlMapClient;

    public GatewayAppNotifyDAOImpl() {
        super();
    }

    public void setSqlMapClient(SqlMapClientTemplate sqlMapClient) {
        this.sqlMapClient = sqlMapClient;
    }

    public void insert(GatewayAppNotify record) throws DataAccessException {
        sqlMapClient.insert("GATEWAY_APP_NOTIFY.insert", record);
    }

    public int updateById(GatewayAppNotify record) throws DataAccessException {
        int rows = sqlMapClient.update("GATEWAY_APP_NOTIFY.updateById", record);
        return rows;
    }

    public int updateByIdSelective(GatewayAppNotify record) throws DataAccessException {
        int rows = sqlMapClient.update("GATEWAY_APP_NOTIFY.updateByIdSelective", record);
        return rows;
    }

    public GatewayAppNotify selectById(Long id) throws DataAccessException {
        GatewayAppNotify key = new GatewayAppNotify();
        key.setId(id);
        GatewayAppNotify record = (GatewayAppNotify) sqlMapClient.queryForObject("GATEWAY_APP_NOTIFY.selectById", key);
        return record;
    }

    public int deleteById(Long id) throws DataAccessException {
        GatewayAppNotify key = new GatewayAppNotify();
        key.setId(id);
        int rows = sqlMapClient.delete("GATEWAY_APP_NOTIFY.deleteById", key);
        return rows;
    }

	@Override
	public GatewayAppNotify select(String orderNo, String status) {
		Map<String,String> params = new HashMap<String,String>();
		params.put("orderNo", orderNo);
		params.put("payStatus", status);
		GatewayAppNotify record = (GatewayAppNotify) sqlMapClient.queryForObject("GATEWAY_APP_NOTIFY.select", params);
        return record;
	}

	@Override
	public List<GatewayAppNotify> selectFailed(int maxSize) {
		// TODO Auto-generated method stub
		return ( List<GatewayAppNotify>) sqlMapClient.queryForList("GATEWAY_APP_NOTIFY.selectFailed",maxSize);
	}
}