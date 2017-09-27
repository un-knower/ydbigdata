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
import com.yd.common.function.admin.dao.CIP_admin_resource_2_resourceDao;
import com.yd.common.function.admin.data.CIP_admin_resource_2_resourceData;
import com.yd.common.function.admin.data.CIP_admin_resource_treeData;
import com.yd.common.function.admin.data.mapper.CIP_admin_resource_2_resourceMapper;
import com.yd.common.function.admin.data.po.CIP_admin_resource_2_resourcePO;
import com.yd.common.runtime.CIPErrorCode;
/**
 * <p>Dao实现类</p>
 * <p>Class: CIP_admin_resource_2_resource - 资源与资源关系</p>
 *
 * @since 2015-07-27 02:40:52
 */
 
@Repository
public class CIP_admin_resource_2_resourceDaoImpl extends IDaoImpl<CIP_admin_resource_2_resourcePO> 
						implements CIP_admin_resource_2_resourceDao {
	
	private final BeanPropertyRowMapper<CIP_admin_resource_2_resourceData> dataRM = BeanPropertyRowMapper.newInstance(CIP_admin_resource_2_resourceData.class);
	private Logger log = Logger.getLogger(getClass());
	
	public CIP_admin_resource_2_resourceDaoImpl() {
		super();
		this.tableName = "cip_admin_resource_2_resource";
		this.tableKeys = new String[]{
						   "res_node_id" ,
						   "res_node_sup" ,
						   "root_node_id" 
						};
	}
	
	@Override
	protected String generateWhere(Object... args) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<CIP_admin_resource_2_resourceData> searchData(CIPPageInfo page, CIPReqCondition[] conditions){
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
		StringBuffer countSql = new StringBuffer().append("SELECT COUNT(1) from cip_admin_res_2_res_v");
		StringBuffer querySql = new StringBuffer().append("SELECT * from cip_admin_res_2_res_v");
		String whereSql = generateWhere(conditions, params);
		querySql.append(whereSql);
		countSql.append(whereSql);
		
	
		int total = jdbcTemplate.queryForObject(countSql.toString(), params.toArray(), Integer.class);
		pageUtil.setTotal(total);

		querySql = getSqlPage(querySql, pageUtil);
		
		return querySql.toString();
	}
	
	private CIP_admin_resource_2_resourceMapper mapperRM = new CIP_admin_resource_2_resourceMapper();
	
	public List<Map<String, Object>> exportEntities(CIPPageInfo pageUtil, CIPReqCondition[] conditions, boolean xFirst){
		List<Object> params = new ArrayList<Object>();
		StringBuffer countSql = new StringBuffer().append("SELECT COUNT(1) from cip_admin_res_2_res_v");
		StringBuffer querySql = new StringBuffer().append("SELECT * from cip_admin_res_2_res_v");
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

	private final String sql = "SELECT t.res_node_id AS id,r.resource_name AS text,r.resource_type AS type,r.icon_id AS iconCls,t.res_node_sup AS parentId,t.res_level AS nodeLevel,t.node_order AS nodeOrder FROM cip_admin_resource_2_resource t LEFT JOIN cip_admin_resource r ON r.resource_id = t.res_node_id WHERE root_node_id = ? ORDER BY t.res_level ASC,t.node_order ASC";
	private final BeanPropertyRowMapper<CIP_admin_resource_treeData> poRM = BeanPropertyRowMapper.newInstance(CIP_admin_resource_treeData.class);

	@Override
	public List<CIP_admin_resource_treeData> queryByRootNodeId(
			String root_node_id) {
		try{
			return this.jdbcTemplate.query(sql, new Object[]{root_node_id}, poRM);
		}catch(DataAccessException e) {
			log.error(e);
			throw new CIPDaoException(CIPErrorCode.ERROR_DATABASE_TECH_EXCEPTION);
		}
	}

	private final String batchSql = "DELETE FROM cip_admin_resource_2_resource WHERE root_node_id=?";
	@Override
	public void batchDelete(String root_res_id) {
		jdbcTemplate.update(batchSql, root_res_id);
	}

	@Override
	public int queryLevel(String root, String res_id) {
		String sql="SELECT res_level FROM cip_admin_resource_2_resource where root_node_id = '"+root+"' and res_node_id = '"+res_id+"'";
//		return jdbcTemplate.queryForInt(sql);
		return jdbcTemplate.queryForObject(sql,Integer.class);
	}

	@Override
	public int queryOrder(String root, String parent_id) {
		String sql="SELECT MAX(node_order) FROM cip_admin_resource_2_resource where root_node_id = '"+root+"' and res_node_sup = '"+parent_id+"'";
//		return jdbcTemplate.queryForInt(sql);
		return jdbcTemplate.queryForObject(sql,Integer.class);
	}

	/**
	* TODO 简单描述该方法的实现功能（可选）.
	* @see com.yd.common.function.admin.dao.CIP_admin_resource_2_resourceDao#getRoleId(java.lang.String)
	*/
	@Override
	public String getRoleId(String root) {
		String sql = "SELECT role_id FROM cip_admin_roles WHERE root_resource_id = '"+root+"'";
		return jdbcTemplate.queryForObject(sql, String.class);
	}

	@Override
	public List<CIP_admin_resource_2_resourceData> searchNodeId(String root) {
		String sql = "SELECT * FROM cip_admin_resource_2_resource WHERE root_node_id = ?";
		try{
			List<CIP_admin_resource_2_resourceData> data=jdbcTemplate.query(sql, new Object[]{root}, dataRM);
			return data;
		}catch(DataAccessException e) {
			log.error(e);
			throw new CIPDaoException(CIPErrorCode.ERROR_DATABASE_TECH_EXCEPTION);
		}
	}

	@Override
	public int getMaxLevel(String root) {
		String sql = "select MAX(res_level) from cip_admin_resource_2_resource WHERE root_node_id = '"+root+"'";
		try{
//		int data=jdbcTemplate.queryForInt(sql);
		int data=jdbcTemplate.queryForObject(sql,Integer.class);
		return data;
		}catch(DataAccessException e) {
			log.error(e);
			throw new CIPDaoException(CIPErrorCode.ERROR_DATABASE_TECH_EXCEPTION);
		}
	}
}


