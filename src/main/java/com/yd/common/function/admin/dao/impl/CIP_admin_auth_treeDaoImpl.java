package com.yd.common.function.admin.dao.impl;


import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.yd.common.dao.IDaoImpl;
import com.yd.common.data.CIPPageInfo;
import com.yd.common.exception.CIPDaoException;
import com.yd.common.function.admin.dao.CIP_admin_auth_treeDao;
import com.yd.common.function.admin.data.CIP_admin_auth_act2objData;
import com.yd.common.function.admin.data.CIP_admin_resource_2_resourceData;
import com.yd.common.function.admin.data.CIP_admin_resource_treeData;
import com.yd.common.function.admin.data.po.CIP_admin_auth_act2objPO;
import com.yd.common.runtime.CIPErrorCode;
/**
 * <p>Dao实现类</p>
 * <p>Class: CIP_admin_auth_act2obj - 权限对象授权配置</p>
 *
 * @since 2015-07-27 02:40:51
 */
 
@Repository
public class CIP_admin_auth_treeDaoImpl extends IDaoImpl<CIP_admin_auth_act2objPO> 
						implements CIP_admin_auth_treeDao {
	
	private final BeanPropertyRowMapper<CIP_admin_auth_act2objData> dataRM = BeanPropertyRowMapper.newInstance(CIP_admin_auth_act2objData.class);
	private final BeanPropertyRowMapper<CIP_admin_resource_2_resourceData> searchDataRM = BeanPropertyRowMapper.newInstance(CIP_admin_resource_2_resourceData.class);
	private Logger log = Logger.getLogger(getClass());
	
	public CIP_admin_auth_treeDaoImpl() {
		super();
		this.tableName = "cip_admin_auth_act2obj";
		this.tableKeys = new String[]{
						   "obj_attr_id" ,
						   "obj_id" ,
						   "resource_id" ,
						   "root_node_id" 
						};
	}

	/**
	* TODO 简单描述该方法的实现功能（可选）.
	* @see com.yd.common.dao.IDaoImpl#generateWhere(java.lang.Object[])
	*/
	@Override
	protected String generateWhere(Object... args) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	* TODO 查询指定根节点下已经配置过的数据资源.
	* @see com.yd.common.function.admin.dao.CIP_admin_auth_treeDao#queryByRootNodeId(java.lang.String)
	*/
	private final String querySql = "SELECT t.res_node_id AS id,r.resource_name AS text,r.resource_type AS type,r.icon_id AS iconCls,t.res_node_sup AS parentId,t.res_level AS nodeLevel,t.node_order AS nodeOrder FROM "
			+ " cip_admin_resource_2_resource t "
			+ " LEFT JOIN cip_admin_resource r ON r.resource_id = t.res_node_id "
			+ " WHERE root_node_id = ? AND (r.resource_id IN (SELECT resource_id FROM cip_admin_resource "
			+ " WHERE resource_id NOT IN (SELECT resource_id FROM cip_admin_resource WHERE resource_type='B')) "
			+ " OR r.resource_id IN (SELECT j.resource_id resource_id FROM cip_admin_auth_act2obj j "
			+ " LEFT JOIN cip_admin_resource k ON j.resource_id = k.resource_id WHERE "
			+ " j.root_node_id = ? AND j.obj_id = ? AND j.obj_attr_id = ?))";
	private final BeanPropertyRowMapper<CIP_admin_resource_treeData> poRM = BeanPropertyRowMapper.newInstance(CIP_admin_resource_treeData.class);

	@Override
	public List<CIP_admin_resource_treeData> queryByRootNodeId(
			String root_node_id,String obj_attr_id,String obj_id) {
		try{
			return this.jdbcTemplate.query(querySql, new Object[]{root_node_id,root_node_id,obj_id,obj_attr_id},poRM);
		}catch(DataAccessException e) {
			log.error(e);
			throw new CIPDaoException(CIPErrorCode.ERROR_DATABASE_TECH_EXCEPTION);
		}
	}
		
	
	/**
	* TODO 删除当前权限对象及根节点下所有已经存在的资源.
	* @see com.yd.common.function.admin.dao.CIP_admin_auth_treeDao#deleteAllAct2Obj(java.lang.String, java.lang.String, java.lang.String)
	*/
	@Override
	public void deleteAllAct2Obj(String root_resource_id, String obj_attr_id,String obj_id) {
		String querySql = "delete from cip_admin_auth_act2obj where root_node_id = '"+root_resource_id+"' and obj_attr_id = '"+obj_attr_id+"' and obj_id ='"+obj_id+"'";
		jdbcTemplate.update(querySql);	
	}

	/**
	* TODO 查询树节点是否是已选.
	* @see com.yd.common.function.admin.dao.CIP_admin_auth_treeDao#searchNodeId(java.lang.String, java.lang.String, java.lang.String)
	*/
	@Override
	public List<CIP_admin_auth_act2objData> searchNodeId(String root_resource_id, String obj_attr_id,
			String obj_id) {
		String querySql = "select * from cip_admin_auth_act2obj where root_node_id = '"+root_resource_id+"' and obj_attr_id = '"+obj_attr_id+"' and obj_id ='"+obj_id+"'";
		return jdbcTemplate.query(querySql, dataRM);
	}


	/**
	* TODO 为获得动作树(显示已选根节点下分配权限的所有'动作'树)查询数据.
	* @see com.yd.common.function.admin.dao.CIP_admin_auth_treeDao#querySearchByRootNodeId(java.lang.String)
	*/
	@Override
	public List<CIP_admin_resource_2_resourceData> querySearchByRootNodeId(String search_condition,String root_node_id) {
		String querySql = "select * from cip_admin_resource_2_resource t left join cip_admin_resource r ON r.resource_id = t.res_node_id where root_node_id = '"+root_node_id+"' and (case when resource_type='B' then resource_id end) NOT LIKE '%"+search_condition+"%'";
		return jdbcTemplate.query(querySql, searchDataRM);
	}

	
	/**
	* TODO 获得该角色下未分配的'动作'树..查询数据.
	* @see com.yd.common.function.admin.dao.CIP_admin_auth_treeDao#uncheckResTree(java.lang.String, java.lang.String, java.lang.String)
	*/
	@Override
	public List<CIP_admin_resource_treeData> uncheckResTree(String root_node_id,String obj_attr_id,String obj_id) {
		 String querySql = "SELECT t.res_node_id AS id,r.resource_name AS text,r.resource_type AS type,r.icon_id AS iconCls,t.res_node_sup AS parentId,t.res_level AS nodeLevel,t.node_order AS nodeOrder FROM "
		 		+ " cip_admin_resource_2_resource t LEFT JOIN cip_admin_resource r ON r.resource_id = t.res_node_id "
		 		+ " WHERE root_node_id = ? AND (r.resource_id IN (SELECT resource_id FROM "
		 		+ " cip_admin_resource WHERE resource_id NOT IN (SELECT resource_id FROM "
		 		+ " cip_admin_resource WHERE resource_type='B')) OR r.resource_id NOT IN "
		 		+ " (SELECT j.resource_id resource_id FROM cip_admin_auth_act2obj j "
		 		+ " LEFT JOIN cip_admin_resource k ON j.resource_id = k.resource_id "
		 		+ " WHERE j.root_node_id = ? AND j.obj_id = ? AND j.obj_attr_id = ?)) "
		 		+ " ORDER BY t.res_level ASC,t.node_order ASC";
		 try{
				return this.jdbcTemplate.query(querySql, new Object[]{root_node_id,root_node_id,obj_id,obj_attr_id}, poRM);
			}catch(DataAccessException e) {
				log.error(e);
				throw new CIPDaoException(CIPErrorCode.ERROR_DATABASE_TECH_EXCEPTION);
			}
	}

	/**
	* TODO 通过角色id 和 查询条件查询出过滤条件后的资源树.
	* @see com.yd.common.function.admin.dao.CIP_admin_auth_treeDao#getSearchTree(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	*/
	@Override
	public List<CIP_admin_resource_treeData> getSearchTree(String root_node_id,
			String resource_id, String resource_name, String obj_attr_id,
			String obj_id) {
		 String querySql = "SELECT T.res_node_id AS ID,r.resource_name AS text,r.resource_type AS TYPE,r.icon_id AS iconCls,T.res_node_sup AS parentId,T.res_level AS nodeLevel,T.node_order AS nodeOrder "
		 		+ " FROM cip_admin_resource_2_resource T LEFT JOIN cip_admin_resource r ON r.resource_id = T.res_node_id "
		 		+ " WHERE root_node_id = ? AND (r.resource_id IN (SELECT resource_id FROM cip_admin_resource WHERE "
		 		+ " resource_id NOT IN (SELECT resource_id FROM cip_admin_resource WHERE resource_type = 'B')) "
		 		+ " OR r.resource_id NOT IN (SELECT j.resource_id resource_id FROM cip_admin_auth_act2obj j "
		 		+ " LEFT JOIN cip_admin_resource K ON j.resource_id = K.resource_id WHERE j.root_node_id = ? "
		 		+ " AND j.obj_id = ? AND j.obj_attr_id = ?) AND resource_id LIKE '%"+resource_id+"%' "
		 		+ " AND resource_name LIKE '%"+resource_name+"%') ORDER BY T.res_level ASC,T.node_order ASC";
		 try{
				return this.jdbcTemplate.query(querySql, new Object[]{root_node_id,root_node_id,obj_id,obj_attr_id}, poRM);
			}catch(DataAccessException e) {
				log.error(e);
				throw new CIPDaoException(CIPErrorCode.ERROR_DATABASE_TECH_EXCEPTION);
			}
	}

	/**
	* TODO 查询已分配的数据权限资源.
	* @see com.yd.common.function.admin.dao.CIP_admin_auth_treeDao#getRourceByIds(java.lang.String, java.lang.String, java.lang.String)
	*/
	@Override
	public List<CIP_admin_auth_act2objData> getRourceByIds(CIPPageInfo page,String root_node_id,String obj_attr_id, String obj_id) {
		StringBuffer querySql = new StringBuffer().append("select * from cip_admin_auth_act2obj_v  where root_node_id = '"+root_node_id+"' and obj_attr_id = '"+obj_attr_id+"' and obj_id = '"+obj_id+"' ");
		StringBuffer countSql = new StringBuffer().append("select count(1) from cip_admin_auth_act2obj_v  where root_node_id = '"+root_node_id+"' and obj_attr_id = '"+obj_attr_id+"' and obj_id = '"+obj_id+"' ");
		int total = jdbcTemplate.queryForObject(countSql.toString(),Integer.class);
		page.setTotal(total);
		querySql = getSqlPage(querySql, page);
		 try{
			 List<CIP_admin_auth_act2objData> datas=jdbcTemplate.query(querySql.toString(),dataRM);
				return datas;
			}catch(DataAccessException e) {
				log.error(e);
				throw new CIPDaoException(CIPErrorCode.ERROR_DATABASE_TECH_EXCEPTION);
			}
	}

	/**
	* TODO 查询分配数据来源的权限对象.
	* @see com.yd.common.function.admin.dao.CIP_admin_auth_treeDao#searchSource(com.yd.common.data.CIPPageInfo, com.yd.common.data.CIPReqCondition[])
	*/
	@Override
	public List<CIP_admin_auth_act2objData> searchSource(CIPPageInfo page, String condition,String root_node_id,String obj_attr_id,String obj_id){
				StringBuffer querySql = new StringBuffer().append("select * from cip_admin_auth_act2obj_v  where root_node_id = '"+root_node_id+"' and obj_attr_id = '"+obj_attr_id+"' and obj_id = '"+obj_id+"' AND resource_id like '%"+condition+"%'");
				StringBuffer countSql = new StringBuffer().append("select count(1) from cip_admin_auth_act2obj_v  where root_node_id = '"+root_node_id+"' and obj_attr_id = '"+obj_attr_id+"' and obj_id = '"+obj_id+"' and resource_id like '%"+condition+"%'");
				int total = jdbcTemplate.queryForObject(countSql.toString(),Integer.class);
				page.setTotal(total);
				querySql = getSqlPage(querySql, page);
				List<CIP_admin_auth_act2objData> resultDatas=jdbcTemplate.query(querySql.toString(),dataRM);	
				return resultDatas;
	}
 
		
}


