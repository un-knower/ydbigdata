package com.yd.common.function.meta.data.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

/**
 * <p>实体mapper类</p>
 * <p>Table: cip_meta_fields_his - 数据字段历史</p>
 *
 * @since 2015-10-08 09:03:37
 */
public class CIP_meta_fields_hisMapper implements RowMapper<Map<String, Object>> {

	private static Map<String, String> titleMap = getExcelTitle();
	private static Map<String, String> fieldMap = getFieldTitle();

	public static Map<String, String> getExcelTitle() {
		if (titleMap == null)
			titleMap = new LinkedHashMap<String, String>();
		else
			return titleMap;
		
		    titleMap.put("field_id", "字段id");
		    titleMap.put("str_id", "数据结构id");
		    titleMap.put("sys_type", "系统类型");
		    titleMap.put("version_date", "定稿日期");
		    titleMap.put("field_memo", "字段备注");
		    titleMap.put("field_name", "字段名称");
		    titleMap.put("search_flag", "查询位标识");
		    titleMap.put("domain_type", "数据域类型");
		    titleMap.put("java_type", "字段java类型");
		    titleMap.put("jdbc_type", "字段jdbc类型");
		    titleMap.put("field_length", "字段长度");
		    titleMap.put("fprecision", "字段浮点长");
		    titleMap.put("field_default", "默认值");
		    titleMap.put("key_flag", "id标识");
		    titleMap.put("null_flag", "空值标识");
		    titleMap.put("field_use_type", "字段应用类型");
		    titleMap.put("ui_control", "字段UI控件");
		    titleMap.put("ui_ctrl_clazz", "easy-ui控件");
		    titleMap.put("create_time", "创建时间");
		    titleMap.put("update_time", "修改时间");
		    titleMap.put("operator", "操作人员");
		
		return titleMap;
		
	}
	
	public static Map<String, String> getFieldTitle() {
		if (fieldMap == null)
			fieldMap = new LinkedHashMap<String, String>();
		else
			return fieldMap;
		    fieldMap.put("字段id","field_id");
		    fieldMap.put("数据结构id","str_id");
		    fieldMap.put("系统类型","sys_type");
		    fieldMap.put("定稿日期","version_date");
		    fieldMap.put("字段备注","field_memo");
		    fieldMap.put("字段名称","field_name");
		    fieldMap.put("查询位标识","search_flag");
		    fieldMap.put("数据域类型","domain_type");
		    fieldMap.put("字段java类型","java_type");
		    fieldMap.put("字段长度","field_length");
		    fieldMap.put("字段浮点长","fprecision");
		    fieldMap.put("默认值","field_default");
		    fieldMap.put("id标识","key_flag");
		    fieldMap.put("空值标识","null_flag");
		    fieldMap.put("字段应用类型","field_use_type");
		    fieldMap.put("字段UI控件","ui_control");
		    fieldMap.put("easy-ui控件","ui_ctrl_clazz");
		    fieldMap.put("创建时间","create_time");
		    fieldMap.put("修改时间","update_time");
		    fieldMap.put("操作人员","operator");
		
		return fieldMap;
	}
	
	@Override
	public Map<String, Object> mapRow(ResultSet rs, int i) throws SQLException {
		Map<String, Object> data = new LinkedHashMap<String, Object>();
		
			data.put("field_id", rs.getString("field_id"));
			data.put("str_id", rs.getString("str_id"));
			data.put("sys_type", rs.getString("sys_type"));
			data.put("version_date", rs.getDate("version_date"));
			data.put("field_memo", rs.getString("field_memo"));
			data.put("field_name", rs.getString("field_name"));
			data.put("search_flag", rs.getString("search_flag"));
			data.put("domain_type", rs.getString("domain_type"));
			data.put("java_type", rs.getString("java_type"));
			data.put("field_length", rs.getInt("field_length"));
			data.put("fprecision", rs.getInt("fprecision"));
			data.put("field_default", rs.getString("field_default"));
			data.put("key_flag", rs.getString("key_flag"));
			data.put("null_flag", rs.getString("null_flag"));
			data.put("field_use_type", rs.getString("field_use_type"));
			data.put("ui_control", rs.getString("ui_control"));
			data.put("ui_ctrl_clazz", rs.getString("ui_ctrl_clazz"));
			data.put("create_time", rs.getTimestamp("create_time"));
			data.put("update_time", rs.getTimestamp("update_time"));
			data.put("operator", rs.getString("operator"));
		
		return data;
	}
}