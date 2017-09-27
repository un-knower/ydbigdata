package ${basePackage}.${structure.str_module}.data;

<#list javaClasses as javaClass>
import ${javaClass};
</#list>

/**
 * <p>Data实体类</p>
 * <p>View: ${structure.str_id} - ${structure.str_name}</p>
 * 用于检索数据，建立在视图基础上
 * @since ${.now}
 */
public class ${className}Data {

<#list fields as field>
    /** 
    * ${field.field_id} - ${field.field_name} 
    */
	<#if field.field_use_type == 4>
	private String ${field.field_id};
	<#elseif field.field_use_type == 5>
	private String ${field.field_id};
	<#elseif field.field_use_type == 8>
	private String ${field.field_id};
	<#else>
	private ${field.java_type} ${field.field_id};
	</#if>
</#list>

<#list txtFields as txt>
	/**
	* 文本描述信息
	*/
	private String ${txt.txtField};
</#list>

<#list fields as field>
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
    <#elseif field.field_use_type == 8>
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
</#list>

<#list txtFields as txt>
	public String ${txt.getMethod}(){
        return this.${txt.txtField};
    }
    
    public void ${txt.setMethod}(String ${txt.txtField}){
        this.${txt.txtField} = ${txt.txtField};
    }
</#list>
}