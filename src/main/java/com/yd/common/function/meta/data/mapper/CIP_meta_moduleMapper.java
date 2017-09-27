package com.yd.common.function.meta.data.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

/**
 * <p>实体mapper类</p>
 * <p>Table: cip_meta_module - 应用模块信息</p>
 *
 * @since 2015-10-08 09:03:37
 */
public class CIP_meta_moduleMapper implements RowMapper<Map<String, Object>> {

	private static Map<String, String> titleMap = getExcelTitle();
	private static Map<String, String> fieldMap = getFieldTitle();

	public static Map<String, String> getExcelTitle() {
		if (titleMap == null)
			titleMap = new LinkedHashMap<String, String>();
		else
			return titleMap;
		
		    titleMap.put("module_id", "模块id");
		    titleMap.put("module_name", "模块名称");
		    titleMap.put("module_memo", "模块描述");
		    titleMap.put("update_time", "修改时间");
		    titleMap.put("create_time", "创建时间");
		    titleMap.put("operator", "操作人员");
		
		return titleMap;
		
	}
	
	public static Map<String, String> getFieldTitle() {
		if (fieldMap == null)
			fieldMap = new LinkedHashMap<String, String>();
		else
			return fieldMap;
		    fieldMap.put("模块id","module_id");
		    fieldMap.put("模块名称","module_name");
		    fieldMap.put("模块描述","module_memo");
		    fieldMap.put("修改时间","update_time");
		    fieldMap.put("创建时间","create_time");
		    fieldMap.put("操作人员","operator");
		
		return fieldMap;
	}
	
	@Override
	public Map<String, Object> mapRow(ResultSet rs, int i) throws SQLException {
		Map<String, Object> data = new LinkedHashMap<String, Object>();
		
			data.put("module_id", rs.getString("module_id"));
			data.put("module_name", rs.getString("module_name"));
			data.put("module_memo", rs.getString("module_memo"));
			data.put("update_time", rs.getTimestamp("update_time"));
			data.put("create_time", rs.getTimestamp("create_time"));
			data.put("operator", rs.getString("operator"));
		
		return data;
	}
}