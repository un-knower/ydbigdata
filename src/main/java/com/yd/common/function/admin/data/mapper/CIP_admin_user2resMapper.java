package com.yd.common.function.admin.data.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;


/**
 * <p>实体mapper类</p>
 * <p>Table: cip_admin_user2res - 主页快速工具表</p>
 *
 * @since 2016-08-31 04:49:06
 */
public class CIP_admin_user2resMapper implements RowMapper<Map<String, Object>> {

	private static Map<String, String> titleMap = getExcelTitle();
	private static Map<String, String> fieldMap = getFieldTitle();

	public static Map<String, String> getExcelTitle() {
		if (titleMap == null)
			titleMap = new LinkedHashMap<String, String>();
		else
			return titleMap;
		
		    titleMap.put("resource_id", "资源id");
		    titleMap.put("user_id", "用户id");
		    titleMap.put("resource_name", "资源名称");
		    titleMap.put("resource_image", "资源背景图");
		
		return titleMap;
		
	}
	
	public static Map<String, String> getFieldTitle() {
		if (fieldMap == null)
			fieldMap = new LinkedHashMap<String, String>();
		else
			return fieldMap;
		    fieldMap.put("资源id","resource_id");
		    fieldMap.put("用户id","user_id");
		    fieldMap.put("资源名称","resource_name");
		    fieldMap.put("资源背景图","resource_image");
		
		return fieldMap;
	}
	
	@Override
	public Map<String, Object> mapRow(ResultSet rs, int i) throws SQLException {
		Map<String, Object> data = new LinkedHashMap<String, Object>();
		
			data.put("resource_id", rs.getString("resource_id"));
			data.put("user_id", rs.getString("user_id"));
			data.put("resource_name", rs.getString("resource_name"));
			data.put("resource_image", rs.getString("resource_image"));
		
		return data;
	}
}