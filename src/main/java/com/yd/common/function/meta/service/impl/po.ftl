package ${basePackage}.${structure.str_module}.data.po;

<#list javaClasses as javaClass>
import ${javaClass};
</#list>
import com.yd.common.utils.DateUtils;

import ${basePackage}.${structure.str_module}.data.vo.${className}VO;

/**
 * <p>实体类</p>
 * <p>Table: ${structure.str_id} - ${structure.str_name}</p>
 *
 * @since ${.now}
 */
public class ${className}PO {

	public Object[] getKeys(){
		return new Object[]{ 
		<#list keyFields as key>
			${key.field_id}<#if key_has_next>,</#if>
		</#list>};
	}

<#list fields as field>

    /** 
    * ${field.field_id} - ${field.field_name}
    */
    private ${field.java_type} ${field.field_id};
</#list>

<#list fields as field>
    public ${field.java_type} ${getMap[field.field_id]}(){
        return this.${field.field_id};
    }
    public void ${setMap[field.field_id]}(${field.java_type} ${field.field_id}){
        this.${field.field_id} = ${field.field_id};
    }
</#list>

	public ${className}VO toVO(){
		${className}VO vo = new ${className}VO();
		
		<#list fields as field>
		<#if field.field_use_type != 8>
		<#if field.field_use_type == 4>
		vo.${setMap[field.field_id]}(DateUtils.getDate(${field.field_id}));
		<#elseif field.field_use_type == 5>
		vo.${setMap[field.field_id]}(DateUtils.getDateTime(${field.field_id}));
		<#else>
		vo.${setMap[field.field_id]}(${field.field_id});
		</#if>
		</#if>
		</#list>
		
		return vo;
	}
		
}