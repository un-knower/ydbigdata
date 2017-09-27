package com.yd.common.function.meta.data.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

/**
 * <p>实体mapper类</p>
 * <p>Table: cip_meta_job - 元数据作业</p>
 *
 * @since 2015-10-08 09:03:37
 */
public class CIP_meta_jobMapper implements RowMapper<Map<String, Object>> {

	private static Map<String, String> titleMap = getExcelTitle();
	private static Map<String, String> fieldMap = getFieldTitle();

	public static Map<String, String> getExcelTitle() {
		if (titleMap == null)
			titleMap = new LinkedHashMap<String, String>();
		else
			return titleMap;
		
		    titleMap.put("serail_no", "作业任务流水号");
		    titleMap.put("sys_id", "系统id");
		    titleMap.put("exec_time", "执行时间");
		    titleMap.put("meta_job_type", "作业类型");
		    titleMap.put("file_url", "生成文件url");
		    titleMap.put("op_time", "操作时间");
		    titleMap.put("operator", "操作人");
		
		return titleMap;
		
	}
	
	public static Map<String, String> getFieldTitle() {
		if (fieldMap == null)
			fieldMap = new LinkedHashMap<String, String>();
		else
			return fieldMap;
		    fieldMap.put("作业任务流水号","serail_no");
		    fieldMap.put("系统id","sys_id");
		    fieldMap.put("执行时间","exec_time");
		    fieldMap.put("作业类型","meta_job_type");
		    fieldMap.put("生成文件url","file_url");
		    fieldMap.put("操作时间","op_time");
		    fieldMap.put("操作人","operator");
		
		return fieldMap;
	}
	
	@Override
	public Map<String, Object> mapRow(ResultSet rs, int i) throws SQLException {
		Map<String, Object> data = new LinkedHashMap<String, Object>();
		
			data.put("serail_no", rs.getLong("serail_no"));
			data.put("sys_id", rs.getString("sys_id"));
			data.put("exec_time", rs.getTimestamp("exec_time"));
			data.put("meta_job_type", rs.getString("meta_job_type"));
			data.put("file_url", rs.getString("file_url"));
			data.put("op_time", rs.getTimestamp("op_time"));
			data.put("operator", rs.getString("operator"));
		
		return data;
	}
}