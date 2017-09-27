package com.yd.common.function.admin.data.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

/**
 * <p>实体mapper类</p>
 * <p>Table: cip_admin_queue - 系统队列信息</p>
 *
 * @since 2015-08-18 05:02:08
 */
public class CIP_admin_queueMapper implements RowMapper<Map<String, Object>> {

	private static Map<String, String> titleMap = getExcelTitle();
	private static Map<String, String> fieldMap = getFieldTitle();

	public static Map<String, String> getExcelTitle() {
		if (titleMap == null)
			titleMap = new LinkedHashMap<String, String>();
		else
			return titleMap;
		
		    titleMap.put("queue_id", "队列流水号");
		    titleMap.put("queue_type", "队列类型");
		    titleMap.put("queue_data", "队列数据");
		    titleMap.put("queue_flag", "数据提取标识");
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
		    fieldMap.put("队列流水号","queue_id");
		    fieldMap.put("队列类型","queue_type");
		    fieldMap.put("队列数据","queue_data");
		    fieldMap.put("数据提取标识","queue_flag");
		    fieldMap.put("系统时间","create_time");
		    fieldMap.put("修改时间","update_time");
		    fieldMap.put("操作人员","operator");
		
		return fieldMap;
	}
	
	@Override
	public Map<String, Object> mapRow(ResultSet rs, int i) throws SQLException {
		Map<String, Object> data = new LinkedHashMap<String, Object>();
		
			data.put("queue_id", rs.getLong("queue_id"));
			data.put("queue_type", rs.getString("queue_type"));
			data.put("queue_data", rs.getString("queue_data"));
			data.put("queue_flag", rs.getString("queue_flag"));
			data.put("create_time", rs.getTimestamp("create_time"));
			data.put("update_time", rs.getTimestamp("update_time"));
			data.put("operator", rs.getString("operator"));
		
		return data;
	}
}