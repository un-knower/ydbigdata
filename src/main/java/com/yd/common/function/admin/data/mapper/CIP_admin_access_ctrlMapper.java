package com.yd.common.function.admin.data.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

/**
 * <p>实体mapper类</p>
 * <p>Table: cip_admin_access_ctrl - 系统访问控制</p>
 *
 * @since 2015-08-18 02:49:22
 */
public class CIP_admin_access_ctrlMapper implements RowMapper<Map<String, Object>> {

	private static Map<String, String> titleMap = getExcelTitle();
	private static Map<String, String> fieldMap = getFieldTitle();

	public static Map<String, String> getExcelTitle() {
		if (titleMap == null)
			titleMap = new LinkedHashMap<String, String>();
		else
			return titleMap;
		
		    titleMap.put("access_ip", "访问ip");
		    titleMap.put("sys_id", "系统id");
		    titleMap.put("auth_code", "访问授权码");
		    titleMap.put("other_params", "额外访问参数");
		    titleMap.put("remark", "操作备注");
		    titleMap.put("create_time", "系统时间");
		    titleMap.put("update_time", "修改时间");
		    titleMap.put("operator", "操作人员");
		
		return titleMap;
		
	}
	
	public static Map<String, String> getFieldTitle() {
		if (fieldMap == null)
			fieldMap = new LinkedHashMap<String, String>();
		else
			return fieldMap;
		    fieldMap.put("访问ip","access_ip");
		    fieldMap.put("系统id","sys_id");
		    fieldMap.put("访问授权码","auth_code");
		    fieldMap.put("额外访问参数","other_params");
		    fieldMap.put("操作备注","remark");
		    fieldMap.put("系统时间","create_time");
		    fieldMap.put("修改时间","update_time");
		    fieldMap.put("操作人员","operator");
		
		return fieldMap;
	}
	
	@Override
	public Map<String, Object> mapRow(ResultSet rs, int i) throws SQLException {
		Map<String, Object> data = new LinkedHashMap<String, Object>();
		
			data.put("access_ip", rs.getString("access_ip"));
			data.put("sys_id", rs.getString("sys_id"));
			data.put("auth_code", rs.getString("auth_code"));
			data.put("other_params", rs.getString("other_params"));
			data.put("remark", rs.getString("remark"));
			data.put("create_time", rs.getTimestamp("create_time"));
			data.put("update_time", rs.getTimestamp("update_time"));
			data.put("operator", rs.getString("operator"));
		
		return data;
	}
}