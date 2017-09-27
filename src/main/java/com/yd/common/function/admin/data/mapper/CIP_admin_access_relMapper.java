package com.yd.common.function.admin.data.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

/**
 * <p>实体mapper类</p>
 * <p>Table: cip_admin_access_rel - 系统与资源关系配置</p>
 *
 * @since 2015-08-20 09:11:16
 */
public class CIP_admin_access_relMapper implements RowMapper<Map<String, Object>> {

	private static Map<String, String> titleMap = getExcelTitle();
	private static Map<String, String> fieldMap = getFieldTitle();

	public static Map<String, String> getExcelTitle() {
		if (titleMap == null)
			titleMap = new LinkedHashMap<String, String>();
		else
			return titleMap;
		
		    titleMap.put("resource_id", "资源id");
		    titleMap.put("sys_id", "系统id");
		    titleMap.put("create_time", "系统生成时间");
		    titleMap.put("operator", "操作人员");
		
		return titleMap;
		
	}
	
	public static Map<String, String> getFieldTitle() {
		if (fieldMap == null)
			fieldMap = new LinkedHashMap<String, String>();
		else
			return fieldMap;
		    fieldMap.put("资源id","resource_id");
		    fieldMap.put("系统id","sys_id");
		    fieldMap.put("系统生成时间","create_time");
		    fieldMap.put("操作人员","operator");
		
		return fieldMap;
	}
	
	@Override
	public Map<String, Object> mapRow(ResultSet rs, int i) throws SQLException {
		Map<String, Object> data = new LinkedHashMap<String, Object>();
		
			data.put("resource_id", rs.getString("resource_id"));
			data.put("sys_id", rs.getString("sys_id"));
			data.put("create_time", rs.getTimestamp("create_time"));
			data.put("operator", rs.getString("operator"));
		
		return data;
	}
}