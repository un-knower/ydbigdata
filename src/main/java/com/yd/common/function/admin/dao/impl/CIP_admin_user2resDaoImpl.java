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
import com.yd.common.function.admin.dao.CIP_admin_user2resDao;
import com.yd.common.function.admin.data.CIP_admin_user2resData;
import com.yd.common.function.admin.data.CIP_admin_user_2_rolesData;
import com.yd.common.function.admin.data.mapper.CIP_admin_user2resMapper;
import com.yd.common.function.admin.data.po.CIP_admin_user2resPO;
import com.yd.common.log.CIPLogManager;
import com.yd.common.runtime.CIPErrorCode;
/**
 * <p>Dao实现类</p>
 * <p>Class: cip_admin_user2res - 主页快速工具表</p>
 *
 * @since 2016-08-31 04:49:06
 */
 
@Repository
public class CIP_admin_user2resDaoImpl extends IDaoImpl<CIP_admin_user2resPO> 
						implements CIP_admin_user2resDao {
	
	private final BeanPropertyRowMapper<CIP_admin_user2resData> dataRM = BeanPropertyRowMapper.newInstance(CIP_admin_user2resData.class);
	private final BeanPropertyRowMapper<CIP_admin_user_2_rolesData> user2roleRM = BeanPropertyRowMapper.newInstance(CIP_admin_user_2_rolesData.class);

	public CIP_admin_user2resDaoImpl() {
		super();
		this.tableName = "cip_admin_user2res";
		this.tableKeys = new String[]{
						   "resource_id" ,
						   "user_id" 
						};
	}
	
	@Override
	protected String generateWhere(Object... args) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<CIP_admin_user2resData> searchData(CIPPageInfo page, CIPReqCondition[] conditions){
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
	
	private CIP_admin_user2resMapper mapperRM = new CIP_admin_user2resMapper();
	
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
	* TODO 获取主页常用工具信息.
	* @see com.yd.CIP_admin_user2resDao.admin.dao.cip_admin_user2resDao#getToolData(java.lang.String)
	*/
	@Override
	public List<CIP_admin_user2resData> getToolData(String userId) {
		String sql = "SELECT * FROM cip_admin_user2res where user_id = '"+userId+"'";
		List<CIP_admin_user2resData> datas = jdbcTemplate.query(sql,dataRM);
		return datas;
	}

	/**
	* TODO 通过用户获取角色id
	* @see com.yd.common.function.admin.dao.CIP_admin_user2resDao#geRoleByUser(java.lang.String)
	*/
	@Override
	public List<CIP_admin_user_2_rolesData> geRoleByUser(String userId) {
		String sql = "SELECT role_id FROM cip_admin_user_2_roles where user_id = '"+userId+"'";
		List<CIP_admin_user_2_rolesData> datas = jdbcTemplate.query(sql, user2roleRM);
		return datas;
	}
}


