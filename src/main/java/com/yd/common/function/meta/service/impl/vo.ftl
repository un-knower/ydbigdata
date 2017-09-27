package ${basePackage}.${structure.str_module}.data.vo;

<#list javaClasses as javaClass>
import ${javaClass};
</#list>
import com.yd.common.utils.DateUtils;

import ${basePackage}.${structure.str_module}.data.po.${className}PO;

/**
 * <p>实体类</p>
 * <p>Table: ${structure.str_id} - ${structure.str_name}</p>
 *
 * @since ${.now}
 */
public class ${className}VO {

	public Object[] getKeys(){
		return new Object[]{ 
		<#list keyFields as key>
		<#if key.field_use_type == 4>
		new Date(DateUtils.parseDate(${key.field_id}).getTime())<#if key_has_next>,</#if>
		<#elseif field_use_typeType == 5>
		Timestamp.valueOf(${key.field_id})<#if key_has_next>,</#if>
		<#else>
		${key.field_id}<#if key_has_next>,</#if>
		</#if>
		</#list>};
	}

<#list fields as field>
<#if field.field_use_type != 8>
    /** 
    * ${field.field_id} - ${field.field_name} 
    */
    <#if field.field_use_type == 4>
    private String ${field.field_id};
    <#elseif field.field_use_type == 5>
    private String ${field.field_id};
    <#else>
    private ${field.java_type} ${field.field_id};
    </#if>
    
</#if>
</#list>

<#list fields as field>
	<#if field.field_use_type != 8>
	<#if field.field_use_type == 4>
	public String ${getMap[field.field_id]}(){
        return this.${field.field_id};
    }
    public void ${setMap[field.field_id]}(String ${field.field_id}){
        this.${field.field_id} = ${field.field_id};
    }
	<#elseif field.field_use_type == 5>
	public String ${getMap[field.field_id]}(){
        return this.${field.field_id};
    }
    public void ${setMap[field.field_id]}(String ${field.field_id}){
        this.${field.field_id} = ${field.field_id};
    }
	<#else>
	public ${field.java_type} ${getMap[field.field_id]}(){
        return this.${field.field_id};
    }
    public void ${setMap[field.field_id]}(${field.java_type} ${field.field_id}){
        this.${field.field_id} = ${field.field_id};
    }
	</#if>
    </#if>
</#list>

	public ${className}PO toPO(){
		${className}PO po = new ${className}PO();
		
		<#list fields as field>
		<#if field.field_use_type != 8>
		<#if field.field_use_type == 4>
		po.${setMap[field.field_id]}(new Date(DateUtils.parseDate(${field.field_id}).getTime()));
		<#elseif field.field_use_type == 5>
		po.${setMap[field.field_id]}(Timestamp.valueOf(${field.field_id}));
		<#else>
		po.${setMap[field.field_id]}(${field.field_id});
		</#if>
		</#if>
		</#list>
		
		return po;
	}
		
}