package com.yd.common.function.test.data.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

/**
 * <p>实体mapper类</p>
 * <p>Table: cip_test_clazz_med - 测试类方法</p>
 *
 * @since 2015-10-10 02:52:52
 */
public class CIP_test_clazz_medMapper implements RowMapper<Map<String, Object>> {

	private static Map<String, String> titleMap = getExcelTitle();
	private static Map<String, String> fieldMap = getFieldTitle();

	public static Map<String, String> getExcelTitle() {
		if (titleMap == null)
			titleMap = new LinkedHashMap<String, String>();
		else
			return titleMap;
		
			titleMap.put("sys_id", "系统Id");
		    titleMap.put("action_id", "行为id");
		    titleMap.put("action_name", "动作名称");
		    titleMap.put("create_time", "系统时间");
		    titleMap.put("in_data_clazz", "输入数据对象");
		    titleMap.put("operator", "操作人员");
		    titleMap.put("out_data_clazz", "输出数据对象");
		    titleMap.put("test_clazz", "测试类");
		    titleMap.put("test_uri", "测试uri");
		    titleMap.put("update_time", "修改时间");
		
		return titleMap;
		
	}
	
	public static Map<String, String> getFieldTitle() {
		if (fieldMap == null)
			fieldMap = new LinkedHashMap<String, String>();
		else
			return fieldMap;
		
			fieldMap.put("系统Id", "sys_id");
		    fieldMap.put("行为id","action_id");
		    fieldMap.put("动作名称","action_name");
		    fieldMap.put("系统时间","create_time");
		    fieldMap.put("输入数据对象","in_data_clazz");
		    fieldMap.put("操作人员","operator");
		    fieldMap.put("输出数据对象","out_data_clazz");
		    fieldMap.put("测试类","test_clazz");
		    fieldMap.put("测试uri","test_uri");
		    fieldMap.put("修改时间","update_time");
		
		return fieldMap;
	}
	
	@Override
	public Map<String, Object> mapRow(ResultSet rs, int i) throws SQLException {
		Map<String, Object> data = new LinkedHashMap<String, Object>();
		
			data.put("sys_id", rs.getString("sys_id"));
			data.put("action_id", rs.getString("action_id"));
			data.put("action_name", rs.getString("action_name"));
			data.put("create_time", rs.getTimestamp("create_time"));
			data.put("in_data_clazz", rs.getString("in_data_clazz"));
			data.put("operator", rs.getString("operator"));
			data.put("out_data_clazz", rs.getString("out_data_clazz"));
			data.put("test_clazz", rs.getString("test_clazz"));
			data.put("test_uri", rs.getString("test_uri"));
			data.put("update_time", rs.getTimestamp("update_time"));
		
		return data;
	}
}