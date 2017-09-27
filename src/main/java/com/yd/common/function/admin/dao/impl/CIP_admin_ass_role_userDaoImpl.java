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
import com.yd.common.function.admin.dao.CIP_admin_ass_role_userDao;
import com.yd.common.function.admin.data.CIP_admin_userData;
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
public class CIP_admin_ass_role_userDaoImpl extends IDaoImpl<CIP_admin_user_2_rolesPO> 
						implements CIP_admin_ass_role_userDao {
	
	private final BeanPropertyRowMapper<CIP_admin_user_2_rolesData> dataRM = BeanPropertyRowMapper.newInstance(CIP_admin_user_2_rolesData.class);
	private final BeanPropertyRowMapper<CIP_admin_userData> dataRMs = BeanPropertyRowMapper.newInstance(CIP_admin_userData.class);
	private Logger log = Logger.getLogger(getClass());
	
	public CIP_admin_ass_role_userDaoImpl() {
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

	/**
	* TODO 初始化查询未分配当前角色的用户.
	* @see com.yd.common.function.admin.dao.CIP_admin_ass_role_userDao#searchUser()
	*/
	@Override
	public List<CIP_admin_userData> searchUser(CIPPageInfo page,String id) {
		//String querySql = "select * from cip_admin_user where user_id not in( select user_id from cip_admin_user_2_roles)";
		StringBuffer querySql = new StringBuffer().append("select * from cip_admin_user where user_id not in( select user_id from cip_admin_user_2_roles where role_id = '"+id+"')");
	//	String countSql = "select count(1) from cip_admin_user where user_id not in( select user_id from cip_admin_user_2_roles)";
		StringBuffer countSql = new StringBuffer().append("select count(1) from cip_admin_user where user_id not in( select user_id from cip_admin_user_2_roles where role_id = '"+id+"')");
		int total = jdbcTemplate.queryForObject(countSql.toString(),Integer.class);
		page.setTotal(total);
		querySql = getSqlPage(querySql, page);
		List<CIP_admin_userData> resultDatas=jdbcTemplate.query(querySql.toString(),dataRMs);	
		return resultDatas;
	}

	/**
	* TODO 初始化查询已经分配过角色的用户.
	* @see com.yd.common.function.admin.dao.CIP_admin_ass_role_userDao#searchData(com.yd.common.data.CIPPageInfo, java.lang.String)
	*/
	@Override
	public List<CIP_admin_userData> searchData(CIPPageInfo page, String id) {
		//String querySql = "select r.user_id,u.user_name from cip_admin_user_2_roles r left join cip_admin_user u on u.user_id=r.user_id where role_id = \""+id+"\"";
		StringBuffer querySql = new StringBuffer().append("select r.user_id,u.user_name from cip_admin_user_2_roles r left join cip_admin_user u on u.user_id=r.user_id where role_id = '"+id+"'");
		StringBuffer countSql = new StringBuffer().append("select count(1) from cip_admin_user_2_roles r left join cip_admin_user u on u.user_id=r.user_id where role_id = '"+id+"'");
		int total = jdbcTemplate.queryForObject(countSql.toString(),Integer.class);
		page.setTotal(total);
		querySql = getSqlPage(querySql, page);	
		List<CIP_admin_userData> resultDatas=jdbcTemplate.query(querySql.toString(),dataRMs);
		return resultDatas;
	}

	/**
	* TODO 移除已经赋予角色的用户.
	* @see com.yd.common.function.admin.dao.CIP_admin_ass_role_userDao#moveUser(java.lang.String, java.lang.String)
	*/
	@Override
	public void moveUser(String user_id, String role_id) {
		String sql ="delete from cip_admin_user_2_roles where user_id='"+user_id+"' and role_id ='"+role_id+"'";
		jdbcTemplate.update(sql);
		
	}
}


