package com.yd.common.function.test.data.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

/**
 * <p>实体mapper类</p>
 * <p>Table: cip_test_scenario - 系统测试场景</p>
 *
 * @since 2015-10-10 02:52:52
 */
public class CIP_test_scenarioMapper implements RowMapper<Map<String, Object>> {

	private static Map<String, String> titleMap = getExcelTitle();
	private static Map<String, String> fieldMap = getFieldTitle();

	public static Map<String, String> getExcelTitle() {
		if (titleMap == null)
			titleMap = new LinkedHashMap<String, String>();
		else
			return titleMap;
		
		    titleMap.put("create_time", "系统时间");
		    titleMap.put("operator", "操作人员");
		    titleMap.put("scenario_id", "测试场景id");
		    titleMap.put("scenario_name", "测试场景名称");
		    titleMap.put("test_pwd", "测试用户密码");
		    titleMap.put("test_sys_id", "测试系统Id");
		    titleMap.put("test_user", "测试用户");
		    titleMap.put("update_time", "修改时间");
		
		return titleMap;
		
	}
	
	public static Map<String, String> getFieldTitle() {
		if (fieldMap == null)
			fieldMap = new LinkedHashMap<String, String>();
		else
			return fieldMap;
		    fieldMap.put("系统时间","create_time");
		    fieldMap.put("操作人员","operator");
		    fieldMap.put("测试场景id","scenario_id");
		    fieldMap.put("测试场景名称","scenario_name");
		    fieldMap.put("测试用户密码","test_pwd");
		    fieldMap.put("测试系统Id","test_sys_id");
		    fieldMap.put("测试用户","test_user");
		    fieldMap.put("修改时间","update_time");
		
		return fieldMap;
	}
	
	@Override
	public Map<String, Object> mapRow(ResultSet rs, int i) throws SQLException {
		Map<String, Object> data = new LinkedHashMap<String, Object>();
		
			data.put("create_time", rs.getTimestamp("create_time"));
			data.put("operator", rs.getString("operator"));
			data.put("scenario_id", rs.getString("scenario_id"));
			data.put("scenario_name", rs.getString("scenario_name"));
			data.put("test_pwd", rs.getString("test_pwd"));
			data.put("test_sys_id", rs.getString("test_sys_id"));
			data.put("test_user", rs.getString("test_user"));
			data.put("update_time", rs.getTimestamp("update_time"));
		
		return data;
	}
}