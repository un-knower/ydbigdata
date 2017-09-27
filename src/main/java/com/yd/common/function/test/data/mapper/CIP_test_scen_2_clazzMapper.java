package com.yd.common.function.test.data.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

/**
 * <p>实体mapper类</p>
 * <p>Table: cip_test_scen_2_clazz - 测试类与场景关系</p>
 *
 * @since 2015-10-10 02:52:52
 */
public class CIP_test_scen_2_clazzMapper implements RowMapper<Map<String, Object>> {

	private static Map<String, String> titleMap = getExcelTitle();
	private static Map<String, String> fieldMap = getFieldTitle();

	public static Map<String, String> getExcelTitle() {
		if (titleMap == null)
			titleMap = new LinkedHashMap<String, String>();
		else
			return titleMap;
		
		    titleMap.put("create_time", "系统时间");
		    titleMap.put("operator", "操作人员");
		    titleMap.put("scenario_id", "测试场景id");
		    titleMap.put("test_clazz", "测试类");
		    titleMap.put("update_time", "修改时间");
		
		return titleMap;
		
	}
	
	public static Map<String, String> getFieldTitle() {
		if (fieldMap == null)
			fieldMap = new LinkedHashMap<String, String>();
		else
			return fieldMap;
		    fieldMap.put("系统时间","create_time");
		    fieldMap.put("操作人员","operator");
		    fieldMap.put("测试场景id","scenario_id");
		    fieldMap.put("测试类","test_clazz");
		    fieldMap.put("修改时间","update_time");
		
		return fieldMap;
	}
	
	@Override
	public Map<String, Object> mapRow(ResultSet rs, int i) throws SQLException {
		Map<String, Object> data = new LinkedHashMap<String, Object>();
		
			data.put("create_time", rs.getTimestamp("create_time"));
			data.put("operator", rs.getString("operator"));
			data.put("scenario_id", rs.getString("scenario_id"));
			data.put("test_clazz", rs.getString("test_clazz"));
			data.put("update_time", rs.getTimestamp("update_time"));
		
		return data;
	}
}