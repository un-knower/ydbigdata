package com.yd.common.function.test.data.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

/**
 * <p>实体mapper类</p>
 * <p>Table: cip_test_clazz - 系统测试类</p>
 *
 * @since 2015-10-10 02:52:52
 */
public class CIP_test_clazzMapper implements RowMapper<Map<String, Object>> {

	private static Map<String, String> titleMap = getExcelTitle();
	private static Map<String, String> fieldMap = getFieldTitle();

	public static Map<String, String> getExcelTitle() {
		if (titleMap == null)
			titleMap = new LinkedHashMap<String, String>();
		else
			return titleMap;
		
		    titleMap.put("clazz_name", "测试类名称");
		    titleMap.put("create_time", "系统时间");
		    titleMap.put("operator", "操作人员");
		    titleMap.put("test_clazz", "测试类");
		    titleMap.put("update_time", "修改时间");
		
		return titleMap;
		
	}
	
	public static Map<String, String> getFieldTitle() {
		if (fieldMap == null)
			fieldMap = new LinkedHashMap<String, String>();
		else
			return fieldMap;
		    fieldMap.put("测试类名称","clazz_name");
		    fieldMap.put("系统时间","create_time");
		    fieldMap.put("操作人员","operator");
		    fieldMap.put("测试类","test_clazz");
		    fieldMap.put("修改时间","update_time");
		
		return fieldMap;
	}
	
	@Override
	public Map<String, Object> mapRow(ResultSet rs, int i) throws SQLException {
		Map<String, Object> data = new LinkedHashMap<String, Object>();
		
			data.put("clazz_name", rs.getString("clazz_name"));
			data.put("create_time", rs.getTimestamp("create_time"));
			data.put("operator", rs.getString("operator"));
			data.put("test_clazz", rs.getString("test_clazz"));
			data.put("update_time", rs.getTimestamp("update_time"));
		
		return data;
	}
}