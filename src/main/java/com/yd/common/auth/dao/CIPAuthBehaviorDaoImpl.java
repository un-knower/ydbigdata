package com.yd.common.auth.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.yd.common.exception.CIPDaoException;
import com.yd.common.runtime.CIPErrorCode;

@Repository
public class CIPAuthBehaviorDaoImpl implements CIPAuthBehaviorDao {
	@Autowired
	protected JdbcTemplate jdbcTemplate;
	private Logger log = Logger.getLogger(getClass());
	
	@Override
	public boolean isSubExist(String tableId, String supFieldId,
			String subFieldId, String supFieldVal, String subFieldVal) {
		StringBuffer sb = new StringBuffer();
		sb.append("select count(1) from ").append(tableId).append(" where ").append(supFieldId).append(" = ? and ");
		sb.append(subFieldId).append(" = ?");
		try{
			int no = jdbcTemplate.queryForObject(sb.toString(), new Object[]{supFieldVal, subFieldVal}, Integer.class);
			if( no > 0 ){
				return true;
			}
			else {
				return false;
			}
		}catch(DataAccessException e) {
			return false;
			//throw new CIPDaoException(CIPErrorCode.ERROR_DATABASE_TECH_EXCEPTION);
		}
	}

	@Override
	public String getSupRelation(String tableId, String subFieldId,
			String subFieldVal, String supRelFieldId) {
		StringBuffer sb = new StringBuffer();
		sb.append("select ").append(supRelFieldId).append(" from ").append(tableId).append(" where ").append(subFieldId).append(" = ? ");
		try{
			String supRel = jdbcTemplate.queryForObject(sb.toString(), new Object[]{subFieldVal}, String.class);
			return supRel;
		}catch(DataAccessException e) {
			return null;
		}
	}

	@Override
	public List<String> getDirectSubs(String tableId, String supFieldId,
			String subFieldId, String supFeildVal) {
		StringBuffer sb = new StringBuffer();
		sb.append("select ").append(subFieldId).append(" from ").append(tableId).append(" where ").append(supFieldId).append(" = ? ");
		try{
			List<String> directSubs = jdbcTemplate.queryForList(sb.toString(), new Object[]{supFeildVal}, String.class);
			return directSubs;
		}catch(DataAccessException e) {
			log.error(e);
			throw new CIPDaoException(CIPErrorCode.ERROR_DATABASE_TECH_EXCEPTION);
		}
	}

	@Override
	public List<String> getAllSubs(String tableId, String supFieldId,
			String supFeildVal, String subFieldId, String supRelFieldId) {
		StringBuffer sb = new StringBuffer();
		sb.append("select ").append(subFieldId).append(" from ").append(tableId).append(" where ").append(supRelFieldId).append(" like '%").append(supFeildVal).append(",%' ");
		try{
			List<String> allSubs = jdbcTemplate.queryForList(sb.toString(), String.class);
			return allSubs;
		}catch(DataAccessException e) {
			log.error(e);
			throw new CIPDaoException(CIPErrorCode.ERROR_DATABASE_TECH_EXCEPTION);
		}
	}


}
