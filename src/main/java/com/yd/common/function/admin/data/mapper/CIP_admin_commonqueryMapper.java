package com.yd.common.function.admin.data.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;


/**
 * <p>实体mapper类</p>
 * <p>Table: cip_cadmin_commonquery - 通用查询配置表</p>
 *
 * @since 2016-08-04 01:17:07
 */
public class CIP_admin_commonqueryMapper implements RowMapper<Map<String, Object>> {

	private static Map<String, String> titleMap = getExcelTitle();
	private static Map<String, String> fieldMap = getFieldTitle();

	public static Map<String, String> getExcelTitle() {
		if (titleMap == null)
			titleMap = new LinkedHashMap<String, String>();
		else
			return titleMap;
		
		    titleMap.put("queryId", "查询id");
		    titleMap.put("description", "查询说明");
		    titleMap.put("paramlist", "查询参数列表");
		    titleMap.put("statement", "查询语句");
		    titleMap.put("count_statement", "统计语句");
		    titleMap.put("pagination", "是否分页");
		    titleMap.put("singleRec", "是否多条");
		
		return titleMap;
		
	}
	
	public static Map<String, String> getFieldTitle() {
		if (fieldMap == null)
			fieldMap = new LinkedHashMap<String, String>();
		else
			return fieldMap;
		    fieldMap.put("查询id","queryId");
		    fieldMap.put("查询说明","description");
		    fieldMap.put("查询参数列表","paramlist");
		    fieldMap.put("查询语句","statement");
		    fieldMap.put("统计语句","count_statement");
		    fieldMap.put("是否分页","pagination");
		    fieldMap.put("是否多条","singleRec");
		
		return fieldMap;
	}
	
	@Override
	public Map<String, Object> mapRow(ResultSet rs, int i) throws SQLException {
		Map<String, Object> data = new LinkedHashMap<String, Object>();
		
			data.put("queryId", rs.getString("queryId"));
			data.put("description", rs.getString("description"));
			data.put("paramlist", rs.getString("paramlist"));
			data.put("statement", rs.getString("statement"));
			data.put("count_statement", rs.getString("count_statement"));
			data.put("pagination", rs.getString("pagination"));
			data.put("singleRec", rs.getString("singleRec"));
		
		return data;
	}
}