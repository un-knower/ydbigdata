package com.yd.common.function.test.dao.impl;

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
import com.yd.common.function.test.dao.CIP_test_template_formDao;
import com.yd.common.function.test.data.CIP_test_template_formData;
import com.yd.common.function.test.data.mapper.CIP_test_template_formMapper;
import com.yd.common.function.test.data.po.CIP_test_template_formPO;
import com.yd.common.log.CIPLogManager;
import com.yd.common.runtime.CIPErrorCode;
/**
 * <p>Dao实现类</p>
 * <p>Class: cip_test_template_form - 测试数据模板字段</p>
 *
 * @since 2015-11-24 04:16:55
 */
 
@Repository
public class CIP_test_template_formDaoImpl extends IDaoImpl<CIP_test_template_formPO> 
						implements CIP_test_template_formDao {
	
	private final BeanPropertyRowMapper<CIP_test_template_formData> dataRM = BeanPropertyRowMapper.newInstance(CIP_test_template_formData.class);
	
	public CIP_test_template_formDaoImpl() {
		super();
		this.tableName = "cip_test_template_form";
		this.tableKeys = new String[]{
						   "field_id" 
						};
	}
	
	@Override
	protected String generateWhere(Object... args) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<CIP_test_template_formData> searchData(CIPPageInfo page, CIPReqCondition[] conditions){
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
	
	private CIP_test_template_formMapper mapperRM = new CIP_test_template_formMapper();
	
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


