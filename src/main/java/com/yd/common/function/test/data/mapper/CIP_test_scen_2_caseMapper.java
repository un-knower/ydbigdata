package com.yd.common.function.test.data.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

/**
 * <p>实体mapper类</p>
 * <p>Table: cip_test_scen_2_case - 测试场景用例关系</p>
 *
 * @since 2015-10-10 02:52:52
 */
public class CIP_test_scen_2_caseMapper implements RowMapper<Map<String, Object>> {

	private static Map<String, String> titleMap = getExcelTitle();
	private static Map<String, String> fieldMap = getFieldTitle();

	public static Map<String, String> getExcelTitle() {
		if (titleMap == null)
			titleMap = new LinkedHashMap<String, String>();
		else
			return titleMap;
		
		    titleMap.put("case_id", "测试用例id");
		    titleMap.put("create_time", "系统时间");
		    titleMap.put("operator", "操作人员");
		    titleMap.put("scenario_id", "测试场景id");
		    titleMap.put("update_time", "修改时间");
		
		return titleMap;
		
	}
	
	public static Map<String, String> getFieldTitle() {
		if (fieldMap == null)
			fieldMap = new LinkedHashMap<String, String>();
		else
			return fieldMap;
		    fieldMap.put("测试用例id","case_id");
		    fieldMap.put("系统时间","create_time");
		    fieldMap.put("操作人员","operator");
		    fieldMap.put("测试场景id","scenario_id");
		    fieldMap.put("修改时间","update_time");
		
		return fieldMap;
	}
	
	@Override
	public Map<String, Object> mapRow(ResultSet rs, int i) throws SQLException {
		Map<String, Object> data = new LinkedHashMap<String, Object>();
		
			data.put("case_id", rs.getString("case_id"));
			data.put("create_time", rs.getTimestamp("create_time"));
			data.put("operator", rs.getString("operator"));
			data.put("scenario_id", rs.getString("scenario_id"));
			data.put("update_time", rs.getTimestamp("update_time"));
		
		return data;
	}
}