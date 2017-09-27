package com.yd.common.function.meta.data.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

/**
 * <p>实体mapper类</p>
 * <p>Table: cip_meta_str - 系统数据结构表</p>
 *
 * @since 2015-10-08 09:03:37
 */
public class CIP_meta_strMapper implements RowMapper<Map<String, Object>> {

	private static Map<String, String> titleMap = getExcelTitle();
	private static Map<String, String> fieldMap = getFieldTitle();

	public static Map<String, String> getExcelTitle() {
		if (titleMap == null)
			titleMap = new LinkedHashMap<String, String>();
		else
			return titleMap;
		
		    titleMap.put("str_id", "数据结构id");
		    titleMap.put("str_name", "数据结构名称");
		    titleMap.put("str_memo", "数据结构备注");
		    titleMap.put("str_type", "数据结构类型");
		    titleMap.put("str_prefix", "结构前缀");
		    titleMap.put("str_module", "结构模块id");
		    titleMap.put("func_set_id", "功能集id");
		    titleMap.put("ref_str_id", "参考结构id");
		    titleMap.put("str_data_type", "存储数据类型");
		    titleMap.put("prd_date", "落地时间");
		    titleMap.put("form_template", "表单模板");
		    titleMap.put("list_template", "列表模板");
		    titleMap.put("src_sys_id", "源系统id");
		    titleMap.put("resp_id", "责任人");
		    titleMap.put("resp_org", "责任部门id");
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
		    fieldMap.put("数据结构id","str_id");
		    fieldMap.put("数据结构名称","str_name");
		    fieldMap.put("数据结构备注","str_memo");
		    fieldMap.put("数据结构类型","str_type");
		    fieldMap.put("结构前缀","str_prefix");
		    fieldMap.put("结构模块id","str_module");
		    fieldMap.put("功能集id","func_set_id");
		    fieldMap.put("参考结构id","ref_str_id");
		    fieldMap.put("存储数据类型","str_data_type");
		    fieldMap.put("落地时间","prd_date");
		    fieldMap.put("表单模板","form_template");
		    fieldMap.put("列表模板","list_template");
		    fieldMap.put("源系统id","src_sys_id");
		    fieldMap.put("责任人","resp_id");
		    fieldMap.put("责任部门id","resp_org");
		    fieldMap.put("修改时间","update_time");
		    fieldMap.put("创建时间","create_time");
		    fieldMap.put("操作人员","operator");
		
		return fieldMap;
	}
	
	@Override
	public Map<String, Object> mapRow(ResultSet rs, int i) throws SQLException {
		Map<String, Object> data = new LinkedHashMap<String, Object>();
		
			data.put("str_id", rs.getString("str_id"));
			data.put("str_name", rs.getString("str_name"));
			data.put("str_memo", rs.getString("str_memo"));
			data.put("str_type", rs.getString("str_type"));
			data.put("str_prefix", rs.getString("str_prefix"));
			data.put("str_module", rs.getString("str_module"));
			data.put("func_set_id", rs.getString("func_set_id"));
			data.put("ref_str_id", rs.getString("ref_str_id"));
			data.put("str_data_type", rs.getString("str_data_type"));
			data.put("prd_date", rs.getDate("prd_date"));
			data.put("form_template", rs.getString("form_template"));
			data.put("list_template", rs.getString("list_template"));
			data.put("src_sys_id", rs.getString("src_sys_id"));
			data.put("resp_id", rs.getString("resp_id"));
			data.put("resp_org", rs.getString("resp_org"));
			data.put("update_time", rs.getTimestamp("update_time"));
			data.put("create_time", rs.getTimestamp("create_time"));
			data.put("operator", rs.getString("operator"));
		
		return data;
	}
}