package com.yd.common.function.admin.data.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;


/**
 * <p>实体mapper类</p>
 * <p>Table: cip_admin_queue_type_dm - 系统队列配置</p>
 *
 * @since 2015-08-18 02:49:22
 */
public class CIP_admin_queue_type_dmMapper implements RowMapper<Map<String, Object>> {

	private static Map<String, String> titleMap = getExcelTitle();
	private static Map<String, String> fieldMap = getFieldTitle();

	public static Map<String, String> getExcelTitle() {
		if (titleMap == null)
			titleMap = new LinkedHashMap<String, String>();
		else
			return titleMap;
		
		    titleMap.put("queue_type", "队列类型");
		    titleMap.put("queue_type_name", "队列名称");
		    titleMap.put("pojo_class", "队列类");
		
		return titleMap;
		
	}
	
	public static Map<String, String> getFieldTitle() {
		if (fieldMap == null)
			fieldMap = new LinkedHashMap<String, String>();
		else
			return fieldMap;
		    fieldMap.put("队列类型","queue_type");
		    fieldMap.put("队列名称","queue_type_name");
		    fieldMap.put("队列类","pojo_class");
		
		return fieldMap;
	}
	
	@Override
	public Map<String, Object> mapRow(ResultSet rs, int i) throws SQLException {
		Map<String, Object> data = new LinkedHashMap<String, Object>();
		
			data.put("queue_type", rs.getString("queue_type"));
			data.put("queue_type_name", rs.getString("queue_type_name"));
			data.put("pojo_class", rs.getString("pojo_class"));
		
		return data;
	}
}