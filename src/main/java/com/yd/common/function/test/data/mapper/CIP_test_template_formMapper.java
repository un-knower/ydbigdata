package com.yd.common.function.test.data.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

/**
 * <p>实体mapper类</p>
 * <p>Table: cip_test_template_form - 测试数据模板字段</p>
 *
 * @since 2015-11-24 04:16:55
 */
public class CIP_test_template_formMapper implements RowMapper<Map<String, Object>> {

	private static Map<String, String> titleMap = getExcelTitle();
	private static Map<String, String> fieldMap = getFieldTitle();

	public static Map<String, String> getExcelTitle() {
		if (titleMap == null)
			titleMap = new LinkedHashMap<String, String>();
		else
			return titleMap;
		
		    titleMap.put("field_id", "字段ID");
		    titleMap.put("template_id", "所属模板ID");
		    titleMap.put("field_name", "字段名称");
		    titleMap.put("isMandatory", "是否必输");
		    titleMap.put("field_ui", "字段UI");
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
		    fieldMap.put("字段ID","field_id");
		    fieldMap.put("所属模板ID","template_id");
		    fieldMap.put("字段名称","field_name");
		    fieldMap.put("是否必输","isMandatory");
		    fieldMap.put("字段UI","field_ui");
		    fieldMap.put("系统时间","create_time");
		    fieldMap.put("修改时间","update_time");
		    fieldMap.put("操作人员","operator");
		
		return fieldMap;
	}
	
	@Override
	public Map<String, Object> mapRow(ResultSet rs, int i) throws SQLException {
		Map<String, Object> data = new LinkedHashMap<String, Object>();
		
			data.put("field_id", rs.getString("field_id"));
			data.put("template_id", rs.getString("template_id"));
			data.put("field_name", rs.getString("field_name"));
			data.put("isMandatory", rs.getString("isMandatory"));
			data.put("field_ui", rs.getString("field_ui"));
			data.put("create_time", rs.getTimestamp("create_time"));
			data.put("update_time", rs.getTimestamp("update_time"));
			data.put("operator", rs.getString("operator"));
		
		return data;
	}
}