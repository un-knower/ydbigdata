package com.yd.common.function.admin.data.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;


/**
 * <p>实体mapper类</p>
 * <p>Table: cip_admin_auth_attr - 权限对象属性信息</p>
 *
 * @since 2015-07-27 02:40:51
 */
public class CIP_admin_auth_attrMapper implements RowMapper<Map<String, Object>> {

	private static Map<String, String> titleMap = getExcelTitle();
	private static Map<String, String> fieldMap = getFieldTitle();

	public static Map<String, String> getExcelTitle() {
		if (titleMap == null)
			titleMap = new LinkedHashMap<String, String>();
		else
			return titleMap;
		
		    titleMap.put("obj_attr_id", "权限对象属性id");
		    titleMap.put("obj_id", "权限对象id");
		    titleMap.put("obj_attr_name", "权限对象属性描述");
		    titleMap.put("attr_val_table", "属性取值表");
		    titleMap.put("sup_col_id", "上级字段id");
		    titleMap.put("sub_col_id", "下级字段id");
		    titleMap.put("rel_col_id", "层级关联字段id");
		
		return titleMap;
		
	}
	
	public static Map<String, String> getFieldTitle() {
		if (fieldMap == null)
			fieldMap = new LinkedHashMap<String, String>();
		else
			return fieldMap;
		    fieldMap.put("权限对象属性id","obj_attr_id");
		    fieldMap.put("权限对象id","obj_id");
		    fieldMap.put("权限对象属性描述","obj_attr_name");
		    fieldMap.put("属性取值表","attr_val_table");
		    fieldMap.put("上级字段id","sup_col_id");
		    fieldMap.put("下级字段id","sub_col_id");
		    fieldMap.put("层级关联字段id","rel_col_id");
		
		return fieldMap;
	}
	
	@Override
	public Map<String, Object> mapRow(ResultSet rs, int i) throws SQLException {
		Map<String, Object> data = new LinkedHashMap<String, Object>();
		
			data.put("obj_attr_id", rs.getString("obj_attr_id"));
			data.put("obj_id", rs.getString("obj_id"));
			data.put("obj_attr_name", rs.getString("obj_attr_name"));
			data.put("attr_val_table", rs.getString("attr_val_table"));
			data.put("sup_col_id", rs.getString("sup_col_id"));
			data.put("sub_col_id", rs.getString("sub_col_id"));
			data.put("rel_col_id", rs.getString("rel_col_id"));
		
		return data;
	}
}