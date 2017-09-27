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
import com.yd.common.function.admin.dao.CIP_admin_commonqueryDao;
import com.yd.common.function.admin.data.CIP_admin_commonqueryData;
import com.yd.common.function.admin.data.mapper.CIP_admin_commonqueryMapper;
import com.yd.common.function.admin.data.po.CIP_admin_commonqueryPO;
import com.yd.common.log.CIPLogManager;
import com.yd.common.runtime.CIPErrorCode;

/**
 * <p>Dao实现类</p>
 * <p>Class: cip_cadmin_commonquery - 通用查询配置表</p>
 *
 * @since 2016-08-04 01:17:07
 */
 
@Repository
public class CIP_admin_commonqueryDaoImpl extends IDaoImpl<CIP_admin_commonqueryPO> 
						implements CIP_admin_commonqueryDao {
	
	private final BeanPropertyRowMapper<CIP_admin_commonqueryData> dataRM = BeanPropertyRowMapper.newInstance(CIP_admin_commonqueryData.class);
	
	public CIP_admin_commonqueryDaoImpl() {
		super();
		this.tableName = "cip_admin_commonquery";
		this.tableKeys = new String[]{
						   "queryId" 
						};
	}
	
	@Override
	protected String generateWhere(Object... args) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<CIP_admin_commonqueryData> searchData(CIPPageInfo page, CIPReqCondition[] conditions){
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
		pageUtil.setTotal(total);;
		
		querySql = getSqlPage(querySql, pageUtil);
	
		
		return querySql.toString();
	}
	
	private CIP_admin_commonqueryMapper mapperRM = new CIP_admin_commonqueryMapper();
	
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
			throw new CIPDaoException(CIPErrorCode.ERROR_DATABASE_TECH_EXCEPTION);
		}
	}

	/**
	* TODO 获取通用查询配置表的所有数据.
	* @see com.yd.common.function.admin.dao.CIP_admin_commonqueryDao#getAllData()
	*/
	@Override
	public List<CIP_admin_commonqueryData> getAllData() {
		String sql = "select * from cip_admin_commonquery_v";
		List<CIP_admin_commonqueryData> datas = jdbcTemplate.query(sql.toString(),dataRM);
		return datas;
	}
		
}


