package com.yd.common.function.test.data.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

/**
 * <p>实体mapper类</p>
 * <p>Table: cip_test_case - 系统测试用例</p>
 *
 * @since 2015-10-10 02:52:52
 */
public class CIP_test_caseMapper implements RowMapper<Map<String, Object>> {

	private static Map<String, String> titleMap = getExcelTitle();
	private static Map<String, String> fieldMap = getFieldTitle();

	public static Map<String, String> getExcelTitle() {
		if (titleMap == null)
			titleMap = new LinkedHashMap<String, String>();
		else
			return titleMap;
		
		 	titleMap.put("scenario_id", "所属场景Id");
		    titleMap.put("action_id", "测试行为id");
		    titleMap.put("case_id", "测试用例id");
		    titleMap.put("case_name", "测试用例名称");
		    titleMap.put("create_time", "系统时间");
		    titleMap.put("data_fmt_type", "测试数据格式");
		    titleMap.put("expect_type", "测试期望类型");
		    titleMap.put("expect_value", "测试期望值");
		    titleMap.put("operator", "操作人员");
		    titleMap.put("test_data", "测试数据");
		    titleMap.put("test_req_type", "必须测试标识");
		    titleMap.put("update_time", "修改时间");
		
		return titleMap;
		
	}
	
	public static Map<String, String> getFieldTitle() {
		if (fieldMap == null)
			fieldMap = new LinkedHashMap<String, String>();
		else
			return fieldMap;
			
			fieldMap.put("所属场景Id", "scenario_id");
		    fieldMap.put("测试行为id","action_id");
		    fieldMap.put("测试用例id","case_id");
		    fieldMap.put("测试用例名称","case_name");
		    fieldMap.put("系统时间","create_time");
		    fieldMap.put("测试数据格式","data_fmt_type");
		    fieldMap.put("测试期望类型","expect_type");
		    fieldMap.put("测试期望值","expect_value");
		    fieldMap.put("操作人员","operator");
		    fieldMap.put("测试数据","test_data");
		    fieldMap.put("必须测试标识","test_req_type");
		    fieldMap.put("修改时间","update_time");
		
		return fieldMap;
	}
	
	@Override
	public Map<String, Object> mapRow(ResultSet rs, int i) throws SQLException {
		Map<String, Object> data = new LinkedHashMap<String, Object>();
		
			data.put("scenario_id", rs.getString("scenario_id"));
			data.put("action_id", rs.getString("action_id"));
			data.put("case_id", rs.getString("case_id"));
			data.put("case_name", rs.getString("case_name"));
			data.put("create_time", rs.getTimestamp("create_time"));
			data.put("data_fmt_type", rs.getString("data_fmt_type"));
			data.put("expect_type", rs.getString("expect_type"));
			data.put("expect_value", rs.getString("expect_value"));
			data.put("operator", rs.getString("operator"));
			data.put("test_data", rs.getString("test_data"));
			data.put("test_req_type", rs.getString("test_req_type"));
			data.put("update_time", rs.getTimestamp("update_time"));
		
		return data;
	}
}