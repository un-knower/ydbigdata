package com.yd.common.function.admin.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.yd.common.dao.IDaoImpl;
import com.yd.common.data.CIPPageInfo;
import com.yd.common.data.CIPReqCondition;
import com.yd.common.exception.CIPDaoException;
import com.yd.common.function.admin.dao.CIP_admin_user_settingDao;
import com.yd.common.function.admin.data.CIP_admin_user_settingData;
import com.yd.common.function.admin.data.mapper.CIP_admin_user_settingMapper;
import com.yd.common.function.admin.data.po.CIP_admin_user_settingPO;
import com.yd.common.runtime.CIPErrorCode;
/**
 * <p>Dao实现类</p>
 * <p>Class: CIP_admin_user_setting - 用户信息设置</p>
 *
 * @since 2015-07-27 02:40:52
 */
 
@Repository
public class CIP_admin_user_settingDaoImpl extends IDaoImpl<CIP_admin_user_settingPO> 
						implements CIP_admin_user_settingDao {
	
	private final BeanPropertyRowMapper<CIP_admin_user_settingData> dataRM = BeanPropertyRowMapper.newInstance(CIP_admin_user_settingData.class);
	private Logger log = Logger.getLogger(getClass());
	
	public CIP_admin_user_settingDaoImpl() {
		super();
		this.tableName = "cip_admin_user_setting";
		this.tableKeys = new String[]{
						   "user_id" ,
						   "user_key" 
						};
	}
	
	@Override
	protected String generateWhere(Object... args) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<CIP_admin_user_settingData> searchData(CIPPageInfo page, CIPReqCondition[] conditions){
		try{
			List<Object> params = new ArrayList<Object>();
			String querySql = generateQuerySql(page, conditions, params);
			return jdbcTemplate.query(querySql.toString(), params.toArray(), dataRM);
		}catch(DataAccessException e) {
			log.error(e);
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
	
	private CIP_admin_user_settingMapper mapperRM = new CIP_admin_user_settingMapper();
	
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
			log.error(e);
			throw new CIPDaoException(CIPErrorCode.ERROR_DATABASE_TECH_EXCEPTION);
		}
	}
}


