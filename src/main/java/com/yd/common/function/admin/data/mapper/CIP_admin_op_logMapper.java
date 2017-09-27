package com.yd.common.function.admin.data.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

/**
 * <p>实体mapper类</p>
 * <p>Table: cip_admin_op_log - 系统操作日志</p>
 *
 * @since 2015-07-27 02:40:51
 */
public class CIP_admin_op_logMapper implements RowMapper<Map<String, Object>> {

	private static Map<String, String> titleMap = getExcelTitle();
	private static Map<String, String> fieldMap = getFieldTitle();

	public static Map<String, String> getExcelTitle() {
		if (titleMap == null)
			titleMap = new LinkedHashMap<String, String>();
		else
			return titleMap;
		
		    titleMap.put("op_seq_no", "操作流水号");
		    titleMap.put("op_table_id", "操作对象类型");
		    titleMap.put("op_obj_id", "操作对象id");
		    titleMap.put("op_type", "操作类型");
		    titleMap.put("remark", "操作备注");
		    titleMap.put("create_time", "系统时间");
		    titleMap.put("operator", "操作人员");
		
		return titleMap;
		
	}
	
	public static Map<String, String> getFieldTitle() {
		if (fieldMap == null)
			fieldMap = new LinkedHashMap<String, String>();
		else
			return fieldMap;
		    fieldMap.put("操作流水号","op_seq_no");
		    fieldMap.put("操作对象类型","op_table_id");
		    fieldMap.put("操作对象id","op_obj_id");
		    fieldMap.put("操作类型","op_type");
		    fieldMap.put("操作备注","remark");
		    fieldMap.put("系统时间","create_time");
		    fieldMap.put("操作人员","operator");
		
		return fieldMap;
	}
	
	@Override
	public Map<String, Object> mapRow(ResultSet rs, int i) throws SQLException {
		Map<String, Object> data = new LinkedHashMap<String, Object>();
		
			data.put("op_seq_no", rs.getLong("op_seq_no"));
			data.put("op_table_id", rs.getString("op_table_id"));
			data.put("op_obj_id", rs.getString("op_obj_id"));
			data.put("op_type", rs.getString("op_type"));
			data.put("remark", rs.getString("remark"));
			data.put("create_time", rs.getTimestamp("create_time"));
			data.put("operator", rs.getString("operator"));
		
		return data;
	}
}