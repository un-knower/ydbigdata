package com.yd.common.function.common.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.yd.common.dao.IDaoImpl;
import com.yd.common.data.CIPPageInfo;
import com.yd.common.exception.CIPRuntimeException;
import com.yd.common.function.common.dao.CIPCommonQueryDao;
import com.yd.common.runtime.CIPErrorCode;

/**
 * ClassName:CIPCommonQueryDaoImp Function: TODO ADD FUNCTION. Reason: TODO ADD
 * REASON. Date: 2016年8月4日 下午1:12:05
 * 
 * @author lyn
 * @version
 * @since JDK 1.7
 * @see
 */
@Repository
public class CIPCommonQueryDaoImpl extends IDaoImpl<Map<String,Object>> implements CIPCommonQueryDao  {
	
	@Override
	protected String generateWhere(Object... args) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Map<String,Object>> doQuery(String querySql,String countSql,Map<String, String> paramMap,CIPPageInfo page) {
		try{
		NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		if (!countSql.equals("")) {
			int count = namedParameterJdbcTemplate.queryForObject(countSql, paramMap, Integer.class);
				page.setTotal(count);
		}
			if(querySql.endsWith(";")){
				querySql=querySql.replace(";", "");
			}
			StringBuffer Sql = new StringBuffer().append(querySql);
			if (page.getRows()!=0 && page.getPage()!=0) {
				Sql = getSqlPage(Sql, page);
			}
				list=namedParameterJdbcTemplate.queryForList(Sql.toString(), paramMap);
				return list;
		}catch(Exception e){
			throw new CIPRuntimeException(
					CIPErrorCode.ERROR_DATABASE_TECH_EXCEPTION);
		}	
	}
	
	@Override
	public int doQueryCount(String countSql,Map<String, String> paramMap) {
		try{
		NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
		int count = namedParameterJdbcTemplate.queryForObject(countSql, paramMap, Integer.class);
		return count;
		}catch(Exception e){
			throw new CIPRuntimeException(
					CIPErrorCode.ERROR_DATABASE_TECH_EXCEPTION);
		}	
	}

}
