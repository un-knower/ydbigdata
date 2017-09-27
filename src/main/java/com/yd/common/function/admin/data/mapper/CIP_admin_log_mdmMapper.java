package com.yd.common.function.admin.data.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

/**
 * <p>实体mapper类</p>
 * <p>Table: cip_admin_log_mdm - 本地日志-主数据变更</p>
 *
 * @since 2015-08-26 08:45:49
 */
public class CIP_admin_log_mdmMapper implements RowMapper<Map<String, Object>> {

	private static Map<String, String> titleMap = getExcelTitle();
	private static Map<String, String> fieldMap = getFieldTitle();

	public static Map<String, String> getExcelTitle() {
		if (titleMap == null)
			titleMap = new LinkedHashMap<String, String>();
		else
			return titleMap;
		
		    titleMap.put("log_id", "日志id");
		    titleMap.put("table_id", "数据表id");
		    titleMap.put("record_id", "记录id");
		    titleMap.put("old_values", "原始值");
		    titleMap.put("new_values", "新设置值");
		    titleMap.put("user_id", "变更人");
		    titleMap.put("operate_type", "操作类型");
		    titleMap.put("occur_time", "记录时间");
		
		return titleMap;
		
	}
	
	public static Map<String, String> getFieldTitle() {
		if (fieldMap == null)
			fieldMap = new LinkedHashMap<String, String>();
		else
			return fieldMap;
		    fieldMap.put("日志id","log_id");
		    fieldMap.put("数据表id","table_id");
		    fieldMap.put("记录id","record_id");
		    fieldMap.put("原始值","old_values");
		    fieldMap.put("新设置值","new_values");
		    fieldMap.put("变更人","user_id");
		    fieldMap.put("操作类型","operate_type");
		    fieldMap.put("记录时间","occur_time");
		
		return fieldMap;
	}
	
	@Override
	public Map<String, Object> mapRow(ResultSet rs, int i) throws SQLException {
		Map<String, Object> data = new LinkedHashMap<String, Object>();
		
			data.put("log_id", rs.getLong("log_id"));
			data.put("table_id", rs.getString("table_id"));
			data.put("record_id", rs.getString("record_id"));
			data.put("old_values", rs.getString("old_values"));
			data.put("new_values", rs.getString("new_values"));
			data.put("user_id", rs.getString("user_id"));
			data.put("operate_type", rs.getInt("operate_type"));
			data.put("occur_time", rs.getTimestamp("occur_time"));
		
		return data;
	}
}