package com.yd.common.function.admin.dao.impl;

import java.sql.Date;
import java.sql.Timestamp;
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
import com.yd.common.function.admin.dao.CIP_admin_userDao;
import com.yd.common.function.admin.data.CIP_admin_userData;
import com.yd.common.function.admin.data.mapper.CIP_admin_userMapper;
import com.yd.common.function.admin.data.po.CIP_admin_userPO;
import com.yd.common.runtime.CIPErrorCode;
/**
 * <p>Dao实现类</p>
 * <p>Class: CIP_admin_user - 用户信息</p>
 *
 * @since 2015-07-27 02:40:52
 */
 
@Repository
public class CIP_admin_userDaoImpl extends IDaoImpl<CIP_admin_userPO> 
						implements CIP_admin_userDao {
	
	private final BeanPropertyRowMapper<CIP_admin_userData> dataRM = BeanPropertyRowMapper.newInstance(CIP_admin_userData.class);
	private Logger log = Logger.getLogger(getClass());
	
	public CIP_admin_userDaoImpl() {
		super();
		this.tableName = "cip_admin_user";
		this.tableKeys = new String[]{
						   "user_id" 
						};
	}
	
	@Override
	public CIP_admin_userPO getUserById(String user_id) {
		// TODO Auto-generated method stub
		CIP_admin_userPO po = new CIP_admin_userPO();
		Map<String,Object> map = jdbcTemplate.queryForMap("select * from cip_admin_user where user_id=?",user_id);
		po.setUser_id(map.get("user_id").toString());
		po.setUser_name(map.get("user_name").toString());
		po.setStart_date((Date)map.get("start_date"));
		po.setEnd_date((Date)map.get("end_date"));
		po.setUser_pwd(map.get("user_pwd").toString());
		po.setPwd_init_flag((int)map.get("pwd_init_flag"));
		po.setUser_his1_pwd(map.get("user_his1_pwd").toString());
		po.setUser_his2_pwd(map.get("user_his2_pwd").toString());
		po.setUser_his3_pwd(map.get("user_his3_pwd").toString());
		po.setPwd_set_time((Timestamp)map.get("pwd_set_time"));
		po.setPwd_reset_days((int)map.get("pwd_reset_days"));
		po.setPwd_reset_flag((int)map.get("pwd_reset_flag"));
		po.setUser_status((int)map.get("user_status"));
		po.setUser_type((int)map.get("user_type"));
		po.setPerson_id(map.get("person_id").toString());
		po.setOrg_id(map.get("org_id").toString());
		po.setPosition_id(map.get("position_id").toString());
		//po.setCreate_time((Timestamp)map.get("create_time"));
		//po.setUpdate_time((Timestamp)map.get("update_time"));
		po.setOperator(map.get("operator").toString());
		po.setLang(map.get("lang")==null?"ZH":map.get("lang").toString());
		return po;
	}

	@Override
	protected String generateWhere(Object... args) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<CIP_admin_userData> searchData(CIPPageInfo page, CIPReqCondition[] conditions){
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
	
	private CIP_admin_userMapper mapperRM = new CIP_admin_userMapper();
	
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


