package com.yd.common.function.admin.data.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

/**
 * <p>实体mapper类</p>
 * <p>Table: cip_admin_question - 系统问题反馈</p>
 *
 * @since 2015-12-25 01:45:43
 */
public class CIP_admin_questionMapper implements RowMapper<Map<String, Object>> {

	private static Map<String, String> titleMap = getExcelTitle();
	private static Map<String, String> fieldMap = getFieldTitle();

	public static Map<String, String> getExcelTitle() {
		if (titleMap == null)
			titleMap = new LinkedHashMap<String, String>();
		else
			return titleMap;
		
		    titleMap.put("question_id", "问题ID");
		    titleMap.put("question_title", "问题标题");
		    titleMap.put("queston_type", "问题类型");
		    titleMap.put("question_desc", "问题描述");
		    titleMap.put("creater", "提出人");
		    titleMap.put("question_reply", "问题答复");
		    titleMap.put("handle_flag", "处理标志");
		    titleMap.put("update_time", "操作时间");
		    titleMap.put("operater", "操作人");
		    titleMap.put("create_time", "生成时间");
		
		return titleMap;
		
	}
	
	public static Map<String, String> getFieldTitle() {
		if (fieldMap == null)
			fieldMap = new LinkedHashMap<String, String>();
		else
			return fieldMap;
		    fieldMap.put("问题ID","question_id");
		    fieldMap.put("问题标题","question_title");
		    fieldMap.put("问题类型","queston_type");
		    fieldMap.put("问题描述","question_desc");
		    fieldMap.put("提出人","creater");
		    fieldMap.put("问题答复","question_reply");
		    fieldMap.put("处理标志","handle_flag");
		    fieldMap.put("操作时间","update_time");
		    fieldMap.put("操作人","operater");
		    fieldMap.put("生成时间","create_time");
		
		return fieldMap;
	}
	
	@Override
	public Map<String, Object> mapRow(ResultSet rs, int i) throws SQLException {
		Map<String, Object> data = new LinkedHashMap<String, Object>();
		
			data.put("question_id", rs.getString("question_id"));
			data.put("question_title", rs.getString("question_title"));
			data.put("queston_type", rs.getString("queston_type"));
			data.put("question_desc", rs.getString("question_desc"));
			data.put("creater", rs.getString("creater"));
			data.put("question_reply", rs.getString("question_reply"));
			data.put("handle_flag", rs.getString("handle_flag"));
			data.put("update_time", rs.getTimestamp("update_time"));
			data.put("operater", rs.getString("operater"));
			data.put("create_time", rs.getTimestamp("create_time"));
		
		return data;
	}
}