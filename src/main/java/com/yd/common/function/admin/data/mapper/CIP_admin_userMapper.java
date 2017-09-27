package com.yd.common.function.admin.data.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

/**
 * <p>实体mapper类</p>
 * <p>Table: cip_admin_user - 用户信息</p>
 *
 * @since 2015-07-27 02:40:52
 */
public class CIP_admin_userMapper implements RowMapper<Map<String, Object>> {

	private static Map<String, String> titleMap = getExcelTitle();
	private static Map<String, String> fieldMap = getFieldTitle();

	public static Map<String, String> getExcelTitle() {
		if (titleMap == null)
			titleMap = new LinkedHashMap<String, String>();
		else
			return titleMap;
		
		    titleMap.put("user_id", "用户id");
		    titleMap.put("user_name", "用户名称");
		    titleMap.put("start_date", "开始时间");
		    titleMap.put("end_date", "结束时间");
		    titleMap.put("user_pwd", "密码");
		    titleMap.put("pwd_init_flag", "初始密码标识");
		    titleMap.put("user_his1_pwd", "历史密码1");
		    titleMap.put("user_his2_pwd", "历史密码2");
		    titleMap.put("user_his3_pwd", "历史密码3");
		    titleMap.put("pwd_set_time", "密码设置时间");
		    titleMap.put("pwd_reset_days", "密码重置天数");
		    titleMap.put("pwd_reset_flag", "重置密码标识");
		    titleMap.put("user_status", "用户状态");
		    titleMap.put("user_type", "用户类型");
		    titleMap.put("person_id", "使用人id");
		    titleMap.put("org_id", "使用机构id");
		    titleMap.put("position_id", "岗位id");
		    titleMap.put("create_time", "系统生成时间");
		    titleMap.put("update_time", "修改时间");
		    titleMap.put("operator", "操作人");
		    titleMap.put("lang", "用户语言");
		return titleMap;
		
	}
	
	public static Map<String, String> getFieldTitle() {
		if (fieldMap == null)
			fieldMap = new LinkedHashMap<String, String>();
		else
			return fieldMap;
		    fieldMap.put("用户id","user_id");
		    fieldMap.put("用户名称","user_name");
		    fieldMap.put("开始时间","start_date");
		    fieldMap.put("结束时间","end_date");
		    fieldMap.put("密码","user_pwd");
		    fieldMap.put("初始密码标识","pwd_init_flag");
		    fieldMap.put("历史密码1","user_his1_pwd");
		    fieldMap.put("历史密码2","user_his2_pwd");
		    fieldMap.put("历史密码3","user_his3_pwd");
		    fieldMap.put("密码设置时间","pwd_set_time");
		    fieldMap.put("密码重置天数","pwd_reset_days");
		    fieldMap.put("重置密码标识","pwd_reset_flag");
		    fieldMap.put("用户状态","user_status");
		    fieldMap.put("用户类型","user_type");
		    fieldMap.put("使用人id","person_id");
		    fieldMap.put("使用机构id","org_id");
		    fieldMap.put("岗位id","position_id");
		    fieldMap.put("系统生成时间","create_time");
		    fieldMap.put("修改时间","update_time");
		    fieldMap.put("操作人","operator");
		    fieldMap.put("用户语言","lang");
			
		return fieldMap;
	}
	
	@Override
	public Map<String, Object> mapRow(ResultSet rs, int i) throws SQLException {
		Map<String, Object> data = new LinkedHashMap<String, Object>();
		
			data.put("user_id", rs.getString("user_id"));
			data.put("user_name", rs.getString("user_name"));
			data.put("start_date", rs.getDate("start_date"));
			data.put("end_date", rs.getDate("end_date"));
			data.put("user_pwd", rs.getString("user_pwd"));
			data.put("pwd_init_flag", rs.getInt("pwd_init_flag"));
			data.put("user_his1_pwd", rs.getString("user_his1_pwd"));
			data.put("user_his2_pwd", rs.getString("user_his2_pwd"));
			data.put("user_his3_pwd", rs.getString("user_his3_pwd"));
			data.put("pwd_set_time", rs.getTimestamp("pwd_set_time"));
			data.put("pwd_reset_days", rs.getInt("pwd_reset_days"));
			data.put("pwd_reset_flag", rs.getInt("pwd_reset_flag"));
			data.put("user_status", rs.getInt("user_status"));
			data.put("user_type", rs.getInt("user_type"));
			data.put("person_id", rs.getString("person_id"));
			data.put("org_id", rs.getString("org_id"));
			data.put("position_id", rs.getString("position_id"));
			data.put("create_time", rs.getTimestamp("create_time"));
			data.put("update_time", rs.getTimestamp("update_time"));
			data.put("operator", rs.getString("operator"));
			data.put("lang", rs.getString("lang"));
		
		return data;
	}
}