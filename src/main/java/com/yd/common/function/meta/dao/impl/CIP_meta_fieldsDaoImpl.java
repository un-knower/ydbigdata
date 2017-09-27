package com.yd.common.function.meta.dao.impl;

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
import com.yd.common.function.meta.dao.CIP_meta_fieldsDao;
import com.yd.common.function.meta.data.CIP_meta_fieldsData;
import com.yd.common.function.meta.data.mapper.CIP_meta_fieldsMapper;
import com.yd.common.function.meta.data.po.CIP_meta_fieldsPO;
import com.yd.common.log.CIPLogManager;
import com.yd.common.runtime.CIPErrorCode;
/**
 * <p>Dao实现类</p>
 * <p>Class: CIP_meta_fields - 数据字段定义</p>
 *
 * @since 2015-10-08 09:03:37
 */
 
@Repository
public class CIP_meta_fieldsDaoImpl extends IDaoImpl<CIP_meta_fieldsPO> 
						implements CIP_meta_fieldsDao {
	
	private final BeanPropertyRowMapper<CIP_meta_fieldsData> dataRM = BeanPropertyRowMapper.newInstance(CIP_meta_fieldsData.class);
	
	public CIP_meta_fieldsDaoImpl() {
		super();
		this.tableName = "cip_meta_fields";
		this.tableKeys = new String[]{
						   "field_id" ,
						   "str_id" 
						};
	}
	
	@Override
	protected String generateWhere(Object... args) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void deleteByStrId(String str_id){
		String sql = "delete from cip_meta_fields where str_id = '" + str_id + "'";
		try{
			jdbcTemplate.execute(sql);			
		}catch(DataAccessException e) {
			throw new CIPDaoException(CIPErrorCode.ERROR_DATABASE_TECH_EXCEPTION);
		}
	}
	@Override
	public List<CIP_meta_fieldsData> searchData(CIPPageInfo page, CIPReqCondition[] conditions){
		try{
			List<Object> params = new ArrayList<Object>();
			String querySql = generateQuerySql(page, conditions, params);
			return jdbcTemplate.query(querySql.toString(), params.toArray(), dataRM);
		}catch(DataAccessException e) {
			CIPLogManager.error(e.getMessage(), e);
			throw new CIPDaoException(CIPErrorCode.ERROR_DATABASE_TECH_EXCEPTION);
		}
	}
	
	private String generateQuerySql(CIPPageInfo pageUtil, CIPReqCondition[] conditions, List<Object> params){
		StringBuffer countSql = new StringBuffer().append("SELECT COUNT(1) from ").append(tableName).append("_v");
		StringBuffer querySql = new StringBuffer().append("SELECT * from ").append(tableName).append("_v");
		String whereSql = generateWhere(conditions, params);
		querySql.append(whereSql);
		countSql.append(whereSql);
		
	
		int total = jdbcTemplate.queryForObject(countSql.toString(), params.toArray(), Integer.class);
		pageUtil.setTotal(total);

		querySql = getSqlPage(querySql, pageUtil);
		
		return querySql.toString();
	}
	
	private CIP_meta_fieldsMapper mapperRM = new CIP_meta_fieldsMapper();
	
	public List<Map<String, Object>> exportEntities(CIPPageInfo pageUtil, CIPReqCondition[] conditions, boolean xFirst){
		List<Object> params = new ArrayList<Object>();
		StringBuffer countSql = new StringBuffer().append("SELECT COUNT(1) from ").append(tableName).append("_v");
		StringBuffer querySql = new StringBuffer().append("SELECT * from ").append(tableName).append("_v");
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
			CIPLogManager.error(e.getMessage(), e);
			throw new CIPDaoException(CIPErrorCode.ERROR_DATABASE_TECH_EXCEPTION);
		}
	}
}


