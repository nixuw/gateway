package com.pay.dao.impl;

import com.pay.dao.GatewayPayOrderDAO;
import com.pay.dao.entity.GatewayPayOrder;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.SqlMapClientTemplate;

public class GatewayPayOrderDAOImpl implements GatewayPayOrderDAO {
    /** 
     * GATEWAY_PAY_ORDER
     */
    private SqlMapClientTemplate sqlMapClient;

    public GatewayPayOrderDAOImpl() {
        super();
    }

    public void setSqlMapClient(SqlMapClientTemplate sqlMapClient) {
        this.sqlMapClient = sqlMapClient;
    }

    public long insert(GatewayPayOrder record) throws DataAccessException {
    	 Long id = (Long) sqlMapClient.insert("GATEWAY_PAY_ORDER.insert", record);
    	 return id == null ? -1L:id.longValue(); 
    }

    public int updateById(GatewayPayOrder record) throws DataAccessException {
        int rows = sqlMapClient.update("GATEWAY_PAY_ORDER.updateById", record);
        return rows;
    }

    public int updateByIdSelective(GatewayPayOrder record) throws DataAccessException {
        int rows = sqlMapClient.update("GATEWAY_PAY_ORDER.updateByIdSelective", record);
        return rows;
    }

    public GatewayPayOrder selectById(Long id) throws DataAccessException {
        GatewayPayOrder key = new GatewayPayOrder();
        key.setId(id);
        GatewayPayOrder record = (GatewayPayOrder) sqlMapClient.queryForObject("GATEWAY_PAY_ORDER.selectById", key);
        return record;
    }

    public int deleteById(Long id) throws DataAccessException {
        GatewayPayOrder key = new GatewayPayOrder();
        key.setId(id);
        int rows = sqlMapClient.delete("GATEWAY_PAY_ORDER.deleteById", key);
        return rows;
    }

	@Override
	public GatewayPayOrder queryByOrderNo(String orderNo) {
		return (GatewayPayOrder)sqlMapClient.queryForObject("GATEWAY_PAY_ORDER.queryByOrderNo", orderNo);
	}
}