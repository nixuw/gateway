package com.pay.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.SqlMapClientTemplate;

import com.pay.dao.GatewayAppAccountDAO;
import com.pay.dao.entity.GatewayAppAccount;
import com.pay.dao.vo.ValidPayChannel;

public class GatewayAppAccountDAOImpl implements GatewayAppAccountDAO {
    /** 
     * GATEWAY_APP_ACCOUNT
     */
    private SqlMapClientTemplate sqlMapClient;

    public GatewayAppAccountDAOImpl() {
        super();
    }

    public void setSqlMapClient(SqlMapClientTemplate sqlMapClient) {
        this.sqlMapClient = sqlMapClient;
    }

    public void insert(GatewayAppAccount record) throws DataAccessException {
        sqlMapClient.insert("GATEWAY_APP_ACCOUNT.insert", record);
    }

    public int updateById(GatewayAppAccount record) throws DataAccessException {
        int rows = sqlMapClient.update("GATEWAY_APP_ACCOUNT.updateById", record);
        return rows;
    }

    public int updateByIdSelective(GatewayAppAccount record) throws DataAccessException {
        int rows = sqlMapClient.update("GATEWAY_APP_ACCOUNT.updateByIdSelective", record);
        return rows;
    }

    public GatewayAppAccount selectById(Long id) throws DataAccessException {
        GatewayAppAccount key = new GatewayAppAccount();
        key.setId(id);
        GatewayAppAccount record = (GatewayAppAccount) sqlMapClient.queryForObject("GATEWAY_APP_ACCOUNT.selectById", key);
        return record;
    }

    public int deleteById(Long id) throws DataAccessException {
        GatewayAppAccount key = new GatewayAppAccount();
        key.setId(id);
        int rows = sqlMapClient.delete("GATEWAY_APP_ACCOUNT.deleteById", key);
        return rows;
    }

	@Override
	public GatewayAppAccount selectByAccount(String account)
			throws DataAccessException {
		if (StringUtils.isEmpty(account)){
			return null;
		}
		
	    GatewayAppAccount key = new GatewayAppAccount();
        key.setAccount(account);
        GatewayAppAccount record = (GatewayAppAccount) sqlMapClient.queryForObject("GATEWAY_APP_ACCOUNT.selectByAccount", key);
        return record;
	}

	@Override
	public List<ValidPayChannel> selectValidPayChannel(Map params) {
		
		List<ValidPayChannel> record = (List<ValidPayChannel>) sqlMapClient.queryForList("GATEWAY_APP_ACCOUNT.selectValidPayChannel", params);
	    return record;
	}
}