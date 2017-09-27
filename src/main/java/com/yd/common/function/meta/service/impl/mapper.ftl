package ${basePackage}.${structure.str_module}.data.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

<#list javaClasses as javaClass>
import ${javaClass};
</#list>

/**
 * <p>实体mapper类</p>
 * <p>Table: ${structure.str_id} - ${structure.str_name}</p>
 *
 * @since ${.now}
 */
public class ${className}Mapper implements RowMapper<Map<String, Object>> {

	private static Map<String, String> titleMap = getExcelTitle();
	private static Map<String, String> fieldMap = getFieldTitle();

	public static Map<String, String> getExcelTitle() {
		if (titleMap == null)
			titleMap = new LinkedHashMap<String, String>();
		else
			return titleMap;
		
		<#list fields as field>
		    titleMap.put("${field.field_id}", "${field.field_name}");
		</#list>
		
		return titleMap;
		
	}
	
	public static Map<String, String> getFieldTitle() {
		if (fieldMap == null)
			fieldMap = new LinkedHashMap<String, String>();
		else
			return fieldMap;
		<#list fields as field>
		    fieldMap.put("${field.field_name}","${field.field_id}");
		</#list>
		
		return fieldMap;
	}
	
	@Override
	public Map<String, Object> mapRow(ResultSet rs, int i) throws SQLException {
		Map<String, Object> data = new LinkedHashMap<String, Object>();
		
		<#list fields as field>
			<#if field.java_type == "int">
			data.put("${field.field_id}", rs.getInt("${field.field_id}"));
			<#elseif field.java_type == "long">
			data.put("${field.field_id}", rs.getLong("${field.field_id}"));
			<#elseif field.java_type == "double">
			data.put("${field.field_id}", rs.getDouble("${field.field_id}"));
			<#else>
			data.put("${field.field_id}", rs.get${field.java_type}("${field.field_id}"));
			</#if>
		</#list>
		
		return data;
	}
}