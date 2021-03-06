package com.yd.common.function.admin.data.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

/**
 * <p>实体mapper类</p>
 * <p>Table: cip_admin_domain - 数据域信息</p>
 *
 * @since 2015-07-27 02:40:51
 */
public class CIP_admin_domainMapper implements RowMapper<Map<String, Object>> {

	private static Map<String, String> titleMap = getExcelTitle();
	private static Map<String, String> fieldMap = getFieldTitle();

	public static Map<String, String> getExcelTitle() {
		if (titleMap == null)
			titleMap = new LinkedHashMap<String, String>();
		else
			return titleMap;
		
		    titleMap.put("domain_id", "数据域id");
		    titleMap.put("domain_name", "数据域名称");
		    titleMap.put("domain_type", "数据域类型");
		    titleMap.put("is_null_flag", "允许为空标识");
		    titleMap.put("data_length", "数据长度");
		    titleMap.put("default_value", "默认值");
		    titleMap.put("float_length", "小数位长度");
		    titleMap.put("data_type", "数据类型");
		    titleMap.put("create_time", "创建时间");
		    titleMap.put("update_time", "修改时间");
		    titleMap.put("operator", "操作人员");
		    titleMap.put("resp_id", "责任人");
		    titleMap.put("domain_desc", "数据域描述");
		    titleMap.put("ref_table_id", "参考引用数据表");
		    titleMap.put("ref_domain_id", "引用数据域id");
		
		return titleMap;
		
	}
	
	public static Map<String, String> getFieldTitle() {
		if (fieldMap == null)
			fieldMap = new LinkedHashMap<String, String>();
		else
			return fieldMap;
		    fieldMap.put("数据域id","domain_id");
		    fieldMap.put("数据域名称","domain_name");
		    fieldMap.put("数据域类型","domain_type");
		    fieldMap.put("允许为空标识","is_null_flag");
		    fieldMap.put("数据长度","data_length");
		    fieldMap.put("默认值","default_value");
		    fieldMap.put("小数位长度","float_length");
		    fieldMap.put("数据类型","data_type");
		    fieldMap.put("创建时间","create_time");
		    fieldMap.put("修改时间","update_time");
		    fieldMap.put("操作人员","operator");
		    fieldMap.put("责任人","resp_id");
		    fieldMap.put("数据域描述","domain_desc");
		    fieldMap.put("参考引用数据表","ref_table_id");
		    fieldMap.put("引用数据域id","ref_domain_id");
		
		return fieldMap;
	}
	
	@Override
	public Map<String, Object> mapRow(ResultSet rs, int i) throws SQLException {
		Map<String, Object> data = new LinkedHashMap<String, Object>();
		
			data.put("domain_id", rs.getString("domain_id"));
			data.put("domain_name", rs.getString("domain_name"));
			data.put("domain_type", rs.getInt("domain_type"));
			data.put("is_null_flag", rs.getInt("is_null_flag"));
			data.put("data_length", rs.getInt("data_length"));
			data.put("default_value", rs.getString("default_value"));
			data.put("float_length", rs.getInt("float_length"));
			data.put("data_type", rs.getString("data_type"));
			data.put("create_time", rs.getTimestamp("create_time"));
			data.put("update_time", rs.getTimestamp("update_time"));
			data.put("operator", rs.getString("operator"));
			data.put("resp_id", rs.getString("resp_id"));
			data.put("domain_desc", rs.getString("domain_desc"));
			data.put("ref_table_id", rs.getString("ref_table_id"));
			data.put("ref_domain_id", rs.getString("ref_domain_id"));
		
		return data;
	}
}