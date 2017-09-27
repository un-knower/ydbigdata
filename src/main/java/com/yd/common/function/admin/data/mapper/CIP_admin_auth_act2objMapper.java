package com.yd.common.function.admin.data.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;


/**
 * <p>实体mapper类</p>
 * <p>Table: cip_admin_auth_act2obj - 权限对象授权配置</p>
 *
 * @since 2015-07-27 02:40:51
 */
public class CIP_admin_auth_act2objMapper implements RowMapper<Map<String, Object>> {

	private static Map<String, String> titleMap = getExcelTitle();
	private static Map<String, String> fieldMap = getFieldTitle();

	public static Map<String, String> getExcelTitle() {
		if (titleMap == null)
			titleMap = new LinkedHashMap<String, String>();
		else
			return titleMap;
		
		    titleMap.put("obj_attr_id", "权限对象属性id");
		    titleMap.put("obj_id", "权限对象id");
		    titleMap.put("resource_id", "行为资源id");
		    titleMap.put("field_id", "表字段id");
		    titleMap.put("root_node_id", "资源根节点");
		    titleMap.put("attr_value", "权限对象属性值");
		    titleMap.put("val_src_type", "属性值来源");
		    titleMap.put("incl_sub_flag", "包含属性下级标识");
		
		return titleMap;
		
	}
	
	public static Map<String, String> getFieldTitle() {
		if (fieldMap == null)
			fieldMap = new LinkedHashMap<String, String>();
		else
			return fieldMap;
		    fieldMap.put("权限对象属性id","obj_attr_id");
		    fieldMap.put("权限对象id","obj_id");
		    fieldMap.put("行为资源id","resource_id");
		    fieldMap.put("表字段id", "field_id");
		    fieldMap.put("资源根节点","root_node_id");
		    fieldMap.put("权限对象属性值","attr_value");
		    fieldMap.put("属性值来源","val_src_type");
		    fieldMap.put("包含属性下级标识","incl_sub_flag");
		
		return fieldMap;
	}
	
	@Override
	public Map<String, Object> mapRow(ResultSet rs, int i) throws SQLException {
		Map<String, Object> data = new LinkedHashMap<String, Object>();
		
			data.put("obj_attr_id", rs.getString("obj_attr_id"));
			data.put("obj_id", rs.getString("obj_id"));
			data.put("resource_id", rs.getString("resource_id"));
			data.put("field_id", rs.getString("field_id"));
			data.put("root_node_id", rs.getString("root_node_id"));
			data.put("attr_value", rs.getString("attr_value"));
			data.put("val_src_type", rs.getString("val_src_type"));
			data.put("incl_sub_flag", rs.getString("incl_sub_flag"));
		
		return data;
	}
}