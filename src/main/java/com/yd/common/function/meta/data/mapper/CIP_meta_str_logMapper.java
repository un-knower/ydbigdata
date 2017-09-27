package com.yd.common.function.meta.data.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

/**
 * <p>实体mapper类</p>
 * <p>Table: cip_meta_str_log - 元数据结构变更</p>
 *
 * @since 2015-10-08 09:03:37
 */
public class CIP_meta_str_logMapper implements RowMapper<Map<String, Object>> {

	private static Map<String, String> titleMap = getExcelTitle();
	private static Map<String, String> fieldMap = getFieldTitle();

	public static Map<String, String> getExcelTitle() {
		if (titleMap == null)
			titleMap = new LinkedHashMap<String, String>();
		else
			return titleMap;
		
		    titleMap.put("op_field", "操作字段");
		    titleMap.put("oper_time", "操作时间");
		    titleMap.put("str_id", "数据结构id");
		    titleMap.put("op_type", "操作类型");
		    titleMap.put("old_value", "原始值");
		    titleMap.put("new_value", "新值");
		    titleMap.put("operator", "操作人");
		
		return titleMap;
		
	}
	
	public static Map<String, String> getFieldTitle() {
		if (fieldMap == null)
			fieldMap = new LinkedHashMap<String, String>();
		else
			return fieldMap;
		    fieldMap.put("操作字段","op_field");
		    fieldMap.put("操作时间","oper_time");
		    fieldMap.put("数据结构id","str_id");
		    fieldMap.put("操作类型","op_type");
		    fieldMap.put("原始值","old_value");
		    fieldMap.put("新值","new_value");
		    fieldMap.put("操作人","operator");
		
		return fieldMap;
	}
	
	@Override
	public Map<String, Object> mapRow(ResultSet rs, int i) throws SQLException {
		Map<String, Object> data = new LinkedHashMap<String, Object>();
		
			data.put("op_field", rs.getString("op_field"));
			data.put("oper_time", rs.getTimestamp("oper_time"));
			data.put("str_id", rs.getString("str_id"));
			data.put("op_type", rs.getString("op_type"));
			data.put("old_value", rs.getString("old_value"));
			data.put("new_value", rs.getString("new_value"));
			data.put("operator", rs.getString("operator"));
		
		return data;
	}
}