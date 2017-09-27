package com.yd.common.function.admin.data.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

/**
 * <p>实体mapper类</p>
 * <p>Table: cip_admin_log_job - 本地日志-作业日志</p>
 *
 * @since 2015-08-26 08:45:48
 */
public class CIP_admin_log_jobMapper implements RowMapper<Map<String, Object>> {

	private static Map<String, String> titleMap = getExcelTitle();
	private static Map<String, String> fieldMap = getFieldTitle();

	public static Map<String, String> getExcelTitle() {
		if (titleMap == null)
			titleMap = new LinkedHashMap<String, String>();
		else
			return titleMap;
		
		    titleMap.put("log_id", "日志id");
		    titleMap.put("task_id", "批量任务id");
		    titleMap.put("step_id", "步骤id");
		    titleMap.put("step_msg", "步骤信息");
		    titleMap.put("error_code", "错误代码");
		    titleMap.put("occur_time", "记录时间");
		
		return titleMap;
		
	}
	
	public static Map<String, String> getFieldTitle() {
		if (fieldMap == null)
			fieldMap = new LinkedHashMap<String, String>();
		else
			return fieldMap;
		    fieldMap.put("日志id","log_id");
		    fieldMap.put("批量任务id","task_id");
		    fieldMap.put("步骤id","step_id");
		    fieldMap.put("步骤信息","step_msg");
		    fieldMap.put("错误代码","error_code");
		    fieldMap.put("记录时间","occur_time");
		
		return fieldMap;
	}
	
	@Override
	public Map<String, Object> mapRow(ResultSet rs, int i) throws SQLException {
		Map<String, Object> data = new LinkedHashMap<String, Object>();
		
			data.put("log_id", rs.getLong("log_id"));
			data.put("task_id", rs.getString("task_id"));
			data.put("step_id", rs.getInt("step_id"));
			data.put("step_msg", rs.getString("step_msg"));
			data.put("error_code", rs.getInt("error_code"));
			data.put("occur_time", rs.getTimestamp("occur_time"));
		
		return data;
	}
}