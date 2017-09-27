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
import com.yd.common.function.admin.dao.CIP_admin_rolesDao;
import com.yd.common.function.admin.data.CIP_admin_resourceData;
import com.yd.common.function.admin.data.CIP_admin_rolesData;
import com.yd.common.function.admin.data.mapper.CIP_admin_rolesMapper;
import com.yd.common.function.admin.data.po.CIP_admin_rolesPO;
import com.yd.common.runtime.CIPErrorCode;
/**
 * <p>Dao实现类</p>
 * <p>Class: CIP_admin_roles - 角色信息</p>
 *
 * @since 2015-07-27 02:40:52
 */
 
@Repository
public class CIP_admin_rolesDaoImpl extends IDaoImpl<CIP_admin_rolesPO> 
						implements CIP_admin_rolesDao {
	
	private final BeanPropertyRowMapper<CIP_admin_rolesData> dataRM = BeanPropertyRowMapper.newInstance(CIP_admin_rolesData.class);
	private final BeanPropertyRowMapper<CIP_admin_resourceData> resRM = BeanPropertyRowMapper.newInstance(CIP_admin_resourceData.class);

	private Logger log = Logger.getLogger(getClass());
	
	public CIP_admin_rolesDaoImpl() {
		super();
		this.tableName = "cip_admin_roles";
		this.tableKeys = new String[]{
						   "role_id" 
						};
	}
	
	@Override
	protected String generateWhere(Object... args) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<CIP_admin_rolesData> searchData(CIPPageInfo page, CIPReqCondition[] conditions){
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
	//查询用户未分配角色
	@Override
	public List<CIP_admin_rolesData> searchroleData(CIPPageInfo page, CIPReqCondition[] conditions){
		try{
			List<Object> params = new ArrayList<Object>();
			String querySql = generateroleQuerySql(page, conditions, params);
			return jdbcTemplate.query(querySql.toString(), params.toArray(), dataRM);
		}catch(DataAccessException e) {
			log.error(e);
			throw new CIPDaoException(CIPErrorCode.ERROR_DATABASE_TECH_EXCEPTION);
		}
	}
	
	private String generateroleQuerySql(CIPPageInfo pageUtil, CIPReqCondition[] conditions, List<Object> params){
		StringBuffer countSql = new StringBuffer().append("SELECT COUNT(*) from cip_admin_roles where role_id not in(");
		StringBuffer countcheckSql = new StringBuffer("SELECT role_id FROM cip_admin_user_2_roles");
		StringBuffer querySql = new StringBuffer().append("SELECT * FROM cip_admin_roles where role_id not in(");
		StringBuffer querycheckSql = new StringBuffer().append("SELECT role_id FROM cip_admin_user_2_roles");
		String whereSql = generateWhere(conditions, params)+")";
		querycheckSql.append(whereSql);
		querySql.append(querycheckSql);
		countcheckSql.append(whereSql);
		countSql.append(countcheckSql);
		
	
		int total = jdbcTemplate.queryForObject(countSql.toString(), params.toArray(), Integer.class);
		pageUtil.setTotal(total);

		querySql = getSqlPage(querySql, pageUtil);
		
		
		
		return querySql.toString();
	}
	
	private CIP_admin_rolesMapper mapperRM = new CIP_admin_rolesMapper();
	
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

	/**
	* TODO 简单描述该方法的实现功能（可选）.
	* @see com.yd.common.function.admin.dao.CIP_admin_rolesDao#getBtnAndPage(java.lang.String, java.lang.String)
	*/
	@Override
	public List<CIP_admin_resourceData> getBtnAndPage(String parentId,String root) {
		String sql = "SELECT * FROM cip_admin_resource_v t LEFT JOIN cip_admin_resource_2_resource r ON t.resource_id = r.res_node_id WHERE res_node_sup = '"+parentId+"' and root_node_id = '"+root+"'";
		List<CIP_admin_resourceData> datas = jdbcTemplate.query(sql.toString(),resRM);
		return datas;
	}
}


