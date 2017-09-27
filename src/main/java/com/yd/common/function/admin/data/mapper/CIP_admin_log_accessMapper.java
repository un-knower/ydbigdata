package com.yd.common.function.admin.data.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

/**
 * <p>实体mapper类</p>
 * <p>Table: cip_admin_log_access - 本地日志-系统访问</p>
 *
 * @since 2015-08-26 08:45:49
 */
public class CIP_admin_log_accessMapper implements RowMapper<Map<String, Object>> {

	private static Map<String, String> titleMap = getExcelTitle();
	private static Map<String, String> fieldMap = getFieldTitle();

	public static Map<String, String> getExcelTitle() {
		if (titleMap == null)
			titleMap = new LinkedHashMap<String, String>();
		else
			return titleMap;
		
		    titleMap.put("log_id", "日志id");
		    titleMap.put("resource_id", "系统资源id");
		    titleMap.put("visitor_id", "访问对象id");
		    titleMap.put("visitor_type", "访问对象类型");
		    titleMap.put("occur_time", "记录时间");
		    titleMap.put("ip", "ip");
		    titleMap.put("remark", "访问备注");
		
		return titleMap;
		
	}
	
	public static Map<String, String> getFieldTitle() {
		if (fieldMap == null)
			fieldMap = new LinkedHashMap<String, String>();
		else
			return fieldMap;
		    fieldMap.put("日志id","log_id");
		    fieldMap.put("系统资源id","resource_id");
		    fieldMap.put("访问对象id","visitor_id");
		    fieldMap.put("访问对象类型","visitor_type");
		    fieldMap.put("记录时间","occur_time");
		    fieldMap.put("ip","ip");
		    fieldMap.put("访问备注","remark");
		
		return fieldMap;
	}
	
	@Override
	public Map<String, Object> mapRow(ResultSet rs, int i) throws SQLException {
		Map<String, Object> data = new LinkedHashMap<String, Object>();
		
			data.put("log_id", rs.getLong("log_id"));
			data.put("resource_id", rs.getString("resource_id"));
			data.put("visitor_id", rs.getString("visitor_id"));
			data.put("visitor_type", rs.getString("visitor_type"));
			data.put("occur_time", rs.getTimestamp("occur_time"));
			data.put("ip", rs.getString("ip"));
			data.put("remark", rs.getString("remark"));
		
		return data;
	}
}