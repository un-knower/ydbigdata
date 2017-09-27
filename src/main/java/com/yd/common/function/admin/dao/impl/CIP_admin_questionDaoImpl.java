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
import com.yd.common.function.admin.dao.CIP_admin_questionDao;
import com.yd.common.function.admin.data.CIP_admin_questionData;
import com.yd.common.function.admin.data.mapper.CIP_admin_questionMapper;
import com.yd.common.function.admin.data.po.CIP_admin_questionPO;
import com.yd.common.log.CIPLogManager;
import com.yd.common.runtime.CIPErrorCode;
/**
 * <p>Dao实现类</p>
 * <p>Class: cip_admin_question - 系统问题反馈</p>
 *
 * @since 2015-12-25 01:45:43
 */
 
@Repository
public class CIP_admin_questionDaoImpl extends IDaoImpl<CIP_admin_questionPO> 
						implements CIP_admin_questionDao {
	
	private final BeanPropertyRowMapper<CIP_admin_questionData> dataRM = BeanPropertyRowMapper.newInstance(CIP_admin_questionData.class);
	
	public CIP_admin_questionDaoImpl() {
		super();
		this.tableName = "cip_admin_question";
		this.tableKeys = new String[]{
						   "question_id" 
						};
	}
	
	@Override
	protected String generateWhere(Object... args) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<CIP_admin_questionData> searchData(CIPPageInfo page, CIPReqCondition[] conditions){
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
	
	private CIP_admin_questionMapper mapperRM = new CIP_admin_questionMapper();
	
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

	@Override
	public int getAllDealThingsCount() {
		try{
		String sql="select count(1) from cip_admin_question where (handle_flag ='0' or handle_flag='' or handle_flag is NULL)";
//		int count=jdbcTemplate.queryForInt(sql);
		int count=jdbcTemplate.queryForObject(sql,Integer.class);
		return count;
		}catch(DataAccessException e) {
			CIPLogManager.error(e.getMessage(), e);
			throw new CIPDaoException(CIPErrorCode.ERROR_DATABASE_TECH_EXCEPTION);
		}
	}
	
	@Override
	public int getDealThingCount(String user_id) {
		try{
			String sql="select count(1) from cip_admin_question where (handle_flag ='0' or handle_flag='' or handle_flag is NULL) and creater='"+user_id+"'";
//			int count=jdbcTemplate.queryForInt(sql);
			int count=jdbcTemplate.queryForObject(sql,Integer.class);
			return count;
			}catch(DataAccessException e) {
				CIPLogManager.error(e.getMessage(), e);
				throw new CIPDaoException(CIPErrorCode.ERROR_DATABASE_TECH_EXCEPTION);
			}
	}
}


