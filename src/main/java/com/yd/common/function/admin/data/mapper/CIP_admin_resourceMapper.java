package com.yd.common.function.admin.data.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

/**
 * <p>实体mapper类</p>
 * <p>Table: cip_admin_resource - 系统资源信息</p>
 *
 * @since 2015-07-27 02:40:52
 */
public class CIP_admin_resourceMapper implements RowMapper<Map<String, Object>> {

	private static Map<String, String> titleMap = getExcelTitle();
	private static Map<String, String> fieldMap = getFieldTitle();

	public static Map<String, String> getExcelTitle() {
		if (titleMap == null)
			titleMap = new LinkedHashMap<String, String>();
		else
			return titleMap;
		
		    titleMap.put("resource_id", "资源id");
		    titleMap.put("resource_name", "资源名称");
		    titleMap.put("resource_desc", "资源描述");
		    titleMap.put("sys_uri", "访问URI");
		    titleMap.put("resource_type", "资源类型");
		    titleMap.put("create_time", "系统生成时间");
		    titleMap.put("update_time", "修改时间");
		    titleMap.put("operator", "操作人员");
		    titleMap.put("icon_id", "图标id");
		
		return titleMap;
		
	}
	
	public static Map<String, String> getFieldTitle() {
		if (fieldMap == null)
			fieldMap = new LinkedHashMap<String, String>();
		else
			return fieldMap;
		    fieldMap.put("资源id","resource_id");
		    fieldMap.put("资源名称","resource_name");
		    fieldMap.put("资源描述","resource_desc");
		    fieldMap.put("访问URI","sys_uri");
		    fieldMap.put("资源类型","resource_type");
		    fieldMap.put("系统生成时间","create_time");
		    fieldMap.put("修改时间","update_time");
		    fieldMap.put("操作人员","operator");
		    fieldMap.put("图标id","icon_id");
		
		return fieldMap;
	}
	
	@Override
	public Map<String, Object> mapRow(ResultSet rs, int i) throws SQLException {
		Map<String, Object> data = new LinkedHashMap<String, Object>();
		
			data.put("resource_id", rs.getString("resource_id"));
			data.put("resource_name", rs.getString("resource_name"));
			data.put("resource_desc", rs.getString("resource_desc"));
			data.put("sys_uri", rs.getString("sys_uri"));
			data.put("resource_type", rs.getString("resource_type"));
			data.put("create_time", rs.getTimestamp("create_time"));
			data.put("update_time", rs.getTimestamp("update_time"));
			data.put("operator", rs.getString("operator"));
			data.put("icon_id", rs.getString("icon_id"));
		
		return data;
	}
}