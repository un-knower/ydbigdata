package com.yd.common.function.meta.data.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

/**
 * <p>实体mapper类</p>
 * <p>Table: cip_meta_job_log - 元数据作业志</p>
 *
 * @since 2015-10-08 09:03:37
 */
public class CIP_meta_job_logMapper implements RowMapper<Map<String, Object>> {

	private static Map<String, String> titleMap = getExcelTitle();
	private static Map<String, String> fieldMap = getFieldTitle();

	public static Map<String, String> getExcelTitle() {
		if (titleMap == null)
			titleMap = new LinkedHashMap<String, String>();
		else
			return titleMap;
		
		    titleMap.put("item_no", "执行序号");
		    titleMap.put("serail_no", "作业任务流水号");
		    titleMap.put("msg_txt", "执行消息");
		    titleMap.put("op_time", "操作时间");
		    titleMap.put("error_code", "错误码");
		
		return titleMap;
		
	}
	
	public static Map<String, String> getFieldTitle() {
		if (fieldMap == null)
			fieldMap = new LinkedHashMap<String, String>();
		else
			return fieldMap;
		    fieldMap.put("执行序号","item_no");
		    fieldMap.put("作业任务流水号","serail_no");
		    fieldMap.put("执行消息","msg_txt");
		    fieldMap.put("操作时间","op_time");
		    fieldMap.put("错误码","error_code");
		
		return fieldMap;
	}
	
	@Override
	public Map<String, Object> mapRow(ResultSet rs, int i) throws SQLException {
		Map<String, Object> data = new LinkedHashMap<String, Object>();
		
			data.put("item_no", rs.getInt("item_no"));
			data.put("serail_no", rs.getLong("serail_no"));
			data.put("msg_txt", rs.getString("msg_txt"));
			data.put("op_time", rs.getTimestamp("op_time"));
			data.put("error_code", rs.getString("error_code"));
		
		return data;
	}
}