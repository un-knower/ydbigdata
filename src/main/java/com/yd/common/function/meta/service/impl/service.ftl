package ${basePackage}.${structure.str_module}.service;

import ${basePackage}.${structure.str_module}.data.po.${className}PO;
import ${basePackage}.${structure.str_module}.data.vo.${className}VO;
import ${basePackage}.${structure.str_module}.data.key.${className}Key;
import ${basePackage}.${structure.str_module}.data.mapper.${className}Mapper;
import ${basePackage}.${structure.str_module}.data.${className}Data;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.yd.common.data.CIPPageInfo;
import com.yd.common.data.CIPReqCondition;
import com.yd.common.runtime.CIPRuntimeOperator;

/**
 * <p>Service类</p>
 * <p>Class: ${className}Service</p>
 *
 * @since ${.now}
 */

@Service
public interface ${className}Service {

	public void updateData(${className}VO d, CIPRuntimeOperator operateInf);
	
	public void addData(${className}VO d, CIPRuntimeOperator operateInf);
	
	public void deleteData(Object[] keys, CIPRuntimeOperator operateInf);
	
	public ${className}VO getData(Object[] keys);
	
	public List<${className}Data> searchData(CIPPageInfo page, CIPReqCondition[] conditions);

	<#list fields as field>
<#if field.field_id == "ctrl_status" >
	/** 
	 * 锁定${structure.str_name}
	 */
	public void lock(${className}Key key, CIPRuntimeOperator operateInf);
	
	/** 
	 * 解锁${structure.str_name}
	 */
	public void unlock(${className}Key key, CIPRuntimeOperator operateInf);
<#elseif field.field_id == "audit_flag" >
		
	/** 
	 * 审批${structure.str_name}
	 */
	public void approve(${className}Key key, CIPRuntimeOperator operateInf);
	
	/** 
	 * 回退${structure.str_name}
	 */
	public void reject(${className}Key key, CIPRuntimeOperator operateInf);
</#if>
</#list>

	/** 
	 * 导出${structure.str_name}
	 */
	public List<Map<String, Object>> exportEntities(CIPPageInfo page,CIPReqCondition[] conditions,
			CIPRuntimeOperator operateInf, boolean xFirst);
	
}