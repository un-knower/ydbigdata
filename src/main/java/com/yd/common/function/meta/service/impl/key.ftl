package ${basePackage}.${structure.str_module}.data.key;

<#list javaClasses as javaClass>
import ${javaClass};
</#list>
import com.yd.common.utils.DateUtils;

/**
 * <p>实体key类</p>
 * <p>Table: ${structure.str_id} - ${structure.str_name}</p>
 *
 * @since ${.now}
 */
public class ${className}Key {	
	
	public Object[] getKeys(){
		return new Object[]{ 
		<#list keyFields as key>
		<#if key.field_use_type == 4>
		new Date(DateUtils.parseDate(${key.field_id}).getTime())<#if key_has_next>,</#if>
		<#elseif key.field_use_type == 5>
		Timestamp.valueOf(${key.field_id})<#if key_has_next>,</#if>
		<#else>
		${key.field_id}<#if key_has_next>,</#if>
		</#if>
		</#list>};
	}
	
	public Object[] setKeys(<#list keyFields as key>${key.java_type} ${key.field_id}<#if key_has_next>,</#if></#list>){
		return new Object[]{ 
		<#list keyFields as key>
			${key.field_id}<#if key_has_next>,</#if>
		</#list>};
	}

<#list keyFields as key>

    /** 
    * ${key.field_id} - ${key.field_name} 
    */
	<#if key.field_use_type == 4>
	private String ${key.field_id};
	<#elseif key.field_use_type == 5>
	private String ${key.field_id};
	<#else>
	private ${key.java_type} ${key.field_id};
	</#if>
</#list>

	private String remark;
	
	private String operateCode;
	
<#list keyFields as key>
	<#if key.field_use_type == 4>
	public String ${getMap[key.field_id]}(){
        return this.${key.field_id};
    }
    
    public void ${setMap[key.field_id]}(String ${key.field_id}){
        this.${key.field_id} = ${key.field_id};
    }
	<#elseif key.field_use_type == 5>
	public String ${getMap[key.field_id]}(){
        return this.${key.field_id};
    }
    
    public void ${setMap[key.field_id]}(String ${key.field_id}){
        this.${key.field_id} = ${key.field_id};
    }
	<#else>
	public ${key.java_type} ${getMap[key.field_id]}(){
        return this.${key.field_id};
    }
    
    public void ${setMap[key.field_id]}(${key.java_type} ${key.field_id}){
        this.${key.field_id} = ${key.field_id};
    }
	</#if>
</#list>

	public void setOperateCode(String operateCode){
		this.operateCode = operateCode;
	}
	
	public String getOperateCode(){
		return operateCode;
	}

	public void setRemark(String remark){
		this.remark = remark;
	}
	
	public String getRemark(){
		return remark;
	}
		
}