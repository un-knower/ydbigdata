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
import com.yd.common.function.admin.dao.CIP_admin_user_2_rolesDao;
import com.yd.common.function.admin.data.CIP_admin_rolesData;
import com.yd.common.function.admin.data.CIP_admin_user_2_rolesData;
import com.yd.common.function.admin.data.mapper.CIP_admin_user_2_rolesMapper;
import com.yd.common.function.admin.data.po.CIP_admin_user_2_rolesPO;
import com.yd.common.runtime.CIPErrorCode;
/**
 * <p>Dao实现类</p>
 * <p>Class: CIP_admin_user_2_roles - 用户角色关联</p>
 *
 * @since 2015-07-27 02:40:52
 */
 
@Repository
public class CIP_admin_user_2_rolesDaoImpl extends IDaoImpl<CIP_admin_user_2_rolesPO> 
						implements CIP_admin_user_2_rolesDao {
	
	private final BeanPropertyRowMapper<CIP_admin_user_2_rolesData> dataRM = BeanPropertyRowMapper.newInstance(CIP_admin_user_2_rolesData.class);
	private final BeanPropertyRowMapper<CIP_admin_rolesData> roledataRM = BeanPropertyRowMapper.newInstance(CIP_admin_rolesData.class);
	private Logger log = Logger.getLogger(getClass());
	
	public CIP_admin_user_2_rolesDaoImpl() {
		super();
		this.tableName = "cip_admin_user_2_roles";
		this.tableKeys = new String[]{
						   "role_id" ,
						   "user_id" 
						};
	}
	
	@Override
	protected String generateWhere(Object... args) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<CIP_admin_user_2_rolesData> searchData(CIPPageInfo page, CIPReqCondition[] conditions){
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

		querySql = getSqlPage(querySql,pageUtil);
		
		return querySql.toString();
	}
	/**
	* 查询用户原有分配角色的角色名和角色id
	*/
	@Override
	public List<CIP_admin_rolesData> searchroleData(CIPPageInfo page, CIPReqCondition[] conditions){
		try{
			List<Object> params = new ArrayList<Object>();
			String querySql = generateroleQuerySql(page, conditions, params);
			return jdbcTemplate.query(querySql.toString(), params.toArray(), roledataRM);
		}catch(DataAccessException e) {
			log.error(e);
			throw new CIPDaoException(CIPErrorCode.ERROR_DATABASE_TECH_EXCEPTION);
		}
	}
	
	private String generateroleQuerySql(CIPPageInfo pageUtil, CIPReqCondition[] conditions, List<Object> params){
		StringBuffer countSql = new StringBuffer().append("SELECT COUNT(*) from ").append(tableName).append("_v");
		StringBuffer querySql = new StringBuffer().append("SELECT u.role_id as role_id,r.role_name as role_name FROM cip_admin_user_2_roles u LEFT JOIN cip_admin_roles r ON r.role_id=u.role_id");
		String whereSql = generateWhere(conditions, params);
		querySql.append(whereSql);
		countSql.append(whereSql);
		
	
		int total = jdbcTemplate.queryForObject(countSql.toString(), params.toArray(), Integer.class);
		pageUtil.setTotal(total);

		//querySql = getSqlPage(querySql,pageUtil);
		
		return querySql.toString();
	}
	
	private CIP_admin_user_2_rolesMapper mapperRM = new CIP_admin_user_2_rolesMapper();
	
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
	@Override
	public List<String> getRoles(String userId) {
		StringBuffer buString = new StringBuffer();
		buString.append("select role_id from  ").append(tableName).append(" where user_id=?");
		List<Map<String, Object>> user2roles = jdbcTemplate.queryForList(buString.toString(), userId);
		List<String> roleIds = new ArrayList<String>();
		for(Map<String, Object> user2role : user2roles){
			roleIds.add(user2role.get("role_id").toString());
		}
		return roleIds;
	}
	
	@Override
	public List<String> getUsers(String roleId) {
		StringBuffer buString = new StringBuffer();
		buString.append("select user_id from  ").append(tableName).append(" where role_id=?");
		List<Map<String, Object>> user2roles = jdbcTemplate.queryForList(buString.toString(), roleId);
		List<String> userIds = new ArrayList<String>();
		for(Map<String, Object> user2role : user2roles){
			userIds.add(user2role.get("user_id").toString());
		}
		return userIds;
	}
	//单一条件删除用户角色信息
	@Override
	public void deletebyuser(String userId){
		String querySql="delete from cip_admin_user_2_roles where user_id='"+userId+"'";
		jdbcTemplate.update(querySql);
	}
	@Override
	public void deletebyrole(String roleId){
		String querySql="delete from cip_admin_user_2_roles where role_id='"+roleId+"'";
		jdbcTemplate.update(querySql);
	}
}


