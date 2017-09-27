package com.yd.common.function.test.data.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

/**
 * <p>实体mapper类</p>
 * <p>Table: cip_test_result - 系统测试结果</p>
 *
 * @since 2015-10-10 02:52:52
 */
public class CIP_test_resultMapper implements RowMapper<Map<String, Object>> {

	private static Map<String, String> titleMap = getExcelTitle();
	private static Map<String, String> fieldMap = getFieldTitle();

	public static Map<String, String> getExcelTitle() {
		if (titleMap == null)
			titleMap = new LinkedHashMap<String, String>();
		else
			return titleMap;
			
			titleMap.put("sys_id", "系统id");
		    titleMap.put("case_id", "测试用例id");
		    titleMap.put("real_value", "测试实际值");
		    titleMap.put("scenario_id", "数据场景");
		    titleMap.put("test_acpt_flag", "测试通过标识");
		    titleMap.put("test_serial_no", "测试流水号");
		    titleMap.put("test_time", "测试时间");
		    titleMap.put("test_user", "测试用户");
		
		return titleMap;
		
	}
	
	public static Map<String, String> getFieldTitle() {
		if (fieldMap == null)
			fieldMap = new LinkedHashMap<String, String>();
		else
			return fieldMap;
			fieldMap.put("系统id", "sys_id");
		    fieldMap.put("测试用例id","case_id");
		    fieldMap.put("测试实际值","real_value");
		    fieldMap.put("数据场景","scenario_id");
		    fieldMap.put("测试通过标识","test_acpt_flag");
		    fieldMap.put("测试流水号","test_serial_no");
		    fieldMap.put("测试时间","test_time");
		    fieldMap.put("测试用户","test_user");
		
		return fieldMap;
	}
	
	@Override
	public Map<String, Object> mapRow(ResultSet rs, int i) throws SQLException {
		Map<String, Object> data = new LinkedHashMap<String, Object>();
			
			data.put("sys_id", rs.getString("sys_id"));
			data.put("case_id", rs.getString("case_id"));
			data.put("real_value", rs.getString("real_value"));
			data.put("scenario_id", rs.getString("scenario_id"));
			data.put("test_acpt_flag", rs.getString("test_acpt_flag"));
			data.put("test_serial_no", rs.getLong("test_serial_no"));
			data.put("test_time", rs.getTimestamp("test_time"));
			data.put("test_user", rs.getString("test_user"));
		
		return data;
	}
}