package com.yd.common.function.admin.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.yd.common.dao.IDaoImpl;
import com.yd.common.data.CIPPageInfo;
import com.yd.common.data.CIPReqCondition;
import com.yd.common.exception.CIPDaoException;
import com.yd.common.function.admin.dao.CIP_admin_queue_type_dmDao;
import com.yd.common.function.admin.data.CIP_admin_queue_type_dmData;
import com.yd.common.function.admin.data.mapper.CIP_admin_queue_type_dmMapper;
import com.yd.common.function.admin.data.po.CIP_admin_queue_type_dmPO;
import com.yd.common.runtime.CIPErrorCode;
/**
 * <p>Dao实现类</p>
 * <p>Class: CIP_admin_queue_type_dm - 系统队列配置</p>
 *
 * @since 2015-08-18 02:49:22
 */
 
@Repository
public class CIP_admin_queue_type_dmDaoImpl extends IDaoImpl<CIP_admin_queue_type_dmPO> 
						implements CIP_admin_queue_type_dmDao {
	
	private final BeanPropertyRowMapper<CIP_admin_queue_type_dmData> dataRM = BeanPropertyRowMapper.newInstance(CIP_admin_queue_type_dmData.class);
	
	public CIP_admin_queue_type_dmDaoImpl() {
		super();
		this.tableName = "cip_admin_queue_type_dm";
		this.tableKeys = new String[]{
						   "queue_type" 
						};
	}
	
	@Override
	protected String generateWhere(Object... args) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<CIP_admin_queue_type_dmData> searchData(CIPPageInfo page, CIPReqCondition[] conditions){
		try{
			List<Object> params = new ArrayList<Object>();
			String querySql = generateQuerySql(page, conditions, params);
			return jdbcTemplate.query(querySql.toString(), params.toArray(), dataRM);
		}catch(DataAccessException e) {
			throw new CIPDaoException(CIPErrorCode.ERROR_DATABASE_TECH_EXCEPTION);
		}
	}
	
	private String generateQuerySql(CIPPageInfo pageUtil, CIPReqCondition[] conditions, List<Object> params){
		StringBuffer countSql = new StringBuffer().append("SELECT COUNT(1) from ").append(tableName);
		StringBuffer querySql = new StringBuffer().append("SELECT * from ").append(tableName);
		String whereSql = generateWhere(conditions, params);
		querySql.append(whereSql);
		countSql.append(whereSql);
		
	
		int total = jdbcTemplate.queryForObject(countSql.toString(), params.toArray(), Integer.class);
		pageUtil.setTotal(total);

		querySql = getSqlPage(querySql, pageUtil);
		
		
		
		return querySql.toString();
	}
	
	private CIP_admin_queue_type_dmMapper mapperRM = new CIP_admin_queue_type_dmMapper();
	
	public List<Map<String, Object>> exportEntities(CIPPageInfo pageUtil, CIPReqCondition[] conditions, boolean xFirst){
		List<Object> params = new ArrayList<Object>();
		StringBuffer countSql = new StringBuffer().append("SELECT COUNT(1) from ").append(tableName);
		StringBuffer querySql = new StringBuffer().append("SELECT * from ").append(tableName);
		String whereSql = generateWhere(conditions, params);
		querySql.append(whereSql);
		countSql.append(whereSql);
		
		if(xFirst){
			int total = jdbcTemplate.queryForObject(countSql.toString(), params.toArray(), Integer.class);
			pageUtil.setTotal(total);
		}
		
		querySql = getSqlPage(querySql, pageUtil);
		
		try{
			return jdbcTemplate.query(querySql.toString(), params.toArray(), mapperRM);
		}catch(DataAccessException e) {
			throw new CIPDaoException(CIPErrorCode.ERROR_DATABASE_TECH_EXCEPTION);
		}
	}
}


