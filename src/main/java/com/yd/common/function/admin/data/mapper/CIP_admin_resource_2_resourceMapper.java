package com.yd.common.function.admin.data.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

/**
 * <p>实体mapper类</p>
 * <p>Table: cip_admin_resource_2_resource - 资源与资源关系</p>
 *
 * @since 2015-07-27 02:40:52
 */
public class CIP_admin_resource_2_resourceMapper implements RowMapper<Map<String, Object>> {

	private static Map<String, String> titleMap = getExcelTitle();
	private static Map<String, String> fieldMap = getFieldTitle();

	public static Map<String, String> getExcelTitle() {
		if (titleMap == null)
			titleMap = new LinkedHashMap<String, String>();
		else
			return titleMap;
		
		    titleMap.put("res_node_id", "资源节点id");
		    titleMap.put("res_node_sup", "资源上级节点id");
		    titleMap.put("root_node_id", "根节点");
		    titleMap.put("node_order", "同级节点序列");
		    titleMap.put("root_flag", "根节点标识");
		    titleMap.put("res_level", "层级数");
		    titleMap.put("leaf_flag", "叶节点标识");
		    titleMap.put("create_time", "系统生成时间");
		    titleMap.put("update_time", "修改时间");
		    titleMap.put("operator", "操作人员");
		
		return titleMap;
		
	}
	
	public static Map<String, String> getFieldTitle() {
		if (fieldMap == null)
			fieldMap = new LinkedHashMap<String, String>();
		else
			return fieldMap;
		    fieldMap.put("资源节点id","res_node_id");
		    fieldMap.put("资源上级节点id","res_node_sup");
		    fieldMap.put("根节点","root_node_id");
		    fieldMap.put("同级节点序列","node_order");
		    fieldMap.put("根节点标识","root_flag");
		    fieldMap.put("层级数","res_level");
		    fieldMap.put("叶节点标识","leaf_flag");
		    fieldMap.put("系统生成时间","create_time");
		    fieldMap.put("修改时间","update_time");
		    fieldMap.put("操作人员","operator");
		
		return fieldMap;
	}
	
	@Override
	public Map<String, Object> mapRow(ResultSet rs, int i) throws SQLException {
		Map<String, Object> data = new LinkedHashMap<String, Object>();
		
			data.put("res_node_id", rs.getString("res_node_id"));
			data.put("res_node_sup", rs.getString("res_node_sup"));
			data.put("root_node_id", rs.getString("root_node_id"));
			data.put("node_order", rs.getInt("node_order"));
			data.put("root_flag", rs.getInt("root_flag"));
			data.put("res_level", rs.getInt("res_level"));
			data.put("leaf_flag", rs.getInt("leaf_flag"));
			data.put("create_time", rs.getTimestamp("create_time"));
			data.put("update_time", rs.getTimestamp("update_time"));
			data.put("operator", rs.getString("operator"));
		
		return data;
	}
}