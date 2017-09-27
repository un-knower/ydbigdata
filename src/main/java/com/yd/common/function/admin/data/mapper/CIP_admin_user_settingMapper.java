package com.yd.common.function.admin.data.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

/**
 * <p>实体mapper类</p>
 * <p>Table: cip_admin_user_setting - 用户信息设置</p>
 *
 * @since 2015-07-27 02:40:52
 */
public class CIP_admin_user_settingMapper implements RowMapper<Map<String, Object>> {

	private static Map<String, String> titleMap = getExcelTitle();
	private static Map<String, String> fieldMap = getFieldTitle();

	public static Map<String, String> getExcelTitle() {
		if (titleMap == null)
			titleMap = new LinkedHashMap<String, String>();
		else
			return titleMap;
		
		    titleMap.put("user_id", "用户id");
		    titleMap.put("user_key", "属性id");
		    titleMap.put("user_value", "属性配置值");
		    titleMap.put("remark", "属性说明");
		    titleMap.put("create_time", "系统生成时间");
		    titleMap.put("update_time", "修改时间");
		    titleMap.put("operator", "操作人");
		
		return titleMap;
		
	}
	
	public static Map<String, String> getFieldTitle() {
		if (fieldMap == null)
			fieldMap = new LinkedHashMap<String, String>();
		else
			return fieldMap;
		    fieldMap.put("用户id","user_id");
		    fieldMap.put("属性id","user_key");
		    fieldMap.put("属性配置值","user_value");
		    fieldMap.put("属性说明","remark");
		    fieldMap.put("系统生成时间","create_time");
		    fieldMap.put("修改时间","update_time");
		    fieldMap.put("操作人","operator");
		
		return fieldMap;
	}
	
	@Override
	public Map<String, Object> mapRow(ResultSet rs, int i) throws SQLException {
		Map<String, Object> data = new LinkedHashMap<String, Object>();
		
			data.put("user_id", rs.getString("user_id"));
			data.put("user_key", rs.getString("user_key"));
			data.put("user_value", rs.getString("user_value"));
			data.put("remark", rs.getString("remark"));
			data.put("create_time", rs.getTimestamp("create_time"));
			data.put("update_time", rs.getTimestamp("update_time"));
			data.put("operator", rs.getString("operator"));
		
		return data;
	}
}