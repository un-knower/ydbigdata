package ${basePackage}.${structure.str_module}.service.impl;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import com.yd.common.data.CIPPageInfo;
import com.yd.common.data.CIPReqCondition;
import com.yd.common.runtime.CIPRuntimeOperator;
import com.yd.common.runtime.CIPErrorCode;
import com.yd.common.exception.CIPServiceException;
import com.yd.common.utils.DateUtils;

import ${basePackage}.${structure.str_module}.service.${className}Service;
import ${basePackage}.${structure.str_module}.dao.${className}Dao;
import ${basePackage}.${structure.str_module}.data.po.${className}PO;
import ${basePackage}.${structure.str_module}.data.vo.${className}VO;
import ${basePackage}.${structure.str_module}.data.key.${className}Key;
import ${basePackage}.${structure.str_module}.data.${className}Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>Service实现类</p>
 * <p>Class: ${className}Service</p>
 *
 * @since ${.now}
 */

@Service(value="${className}Service")
public class ${className}ServiceImpl implements ${className}Service {
	
	@Autowired
	private ${className}Dao dataDao = null;

	/** 
	 * 修改数据
	 */
	@Override
	public void updateData(${className}VO vo, CIPRuntimeOperator operateInf){
		//TODO
		${className}PO po = vo.toPO();
		Object[] keys = po.getKeys();
		${className}PO po0 = dataDao.getSingle(keys);
		if( po0 == null ){
			throw new CIPServiceException(CIPErrorCode.ERROR_RECORD_NOT_EXISTS);
		}
		
		<#list fields as field><#if field.key_flag!=1><#if field.field_use_type == 8>
		<#if field.field_id == "operator">
			<#if field.java_type == "int">
		po.setOperator(Integer.parseInt(operateInf.getSubject_id()));	
			<#else>
		po.setOperator(operateInf.getSubject_id());
			</#if>
		
		<#elseif field.field_id == "op_time">
		po.setOp_time(Timestamp.valueOf(operateInf.getOperate_tm()));
		<#elseif field.field_id == "update_time">
		po.setUpdate_time(Timestamp.valueOf(operateInf.getOperate_tm()));
		<#elseif field.field_id == "ip">
		po.setIp(operateInf.getSubject_ip());
			</#if>
		</#if></#if></#list>
		
		dataDao.update(po);
		
	}
	
	/** 
	 * 添加数据
	 */
	@Override
	public void addData(${className}VO vo, CIPRuntimeOperator operateInf){
		${className}PO po = vo.toPO();
		Object[] keys = po.getKeys();
		${className}PO po0 = dataDao.getSingle(keys);
		if( po0!= null ){
			throw new CIPServiceException(CIPErrorCode.ERROR_DUMPLICATE_PRIMARY_KEY);
		}
		
		//TODO　添加记录基本判断
		<#list fields as field><#if field.key_flag==0><#if field.field_use_type == 8>
			<#if field.field_id == "operator">
			<#if field.java_type == "int">
		po.setOperator(Integer.parseInt(operateInf.getSubject_id()));	
			<#else>
		po.setOperator(operateInf.getSubject_id());
			</#if>
			<#elseif field.field_id == "create_user">
		po.setCreate_user(operateInf.getSubject_id());
			<#elseif field.field_id == "op_time">
		po.setOp_time(Timestamp.valueOf(operateInf.getOperate_tm()));
			<#elseif field.field_id == "create_time">
		po.setCreate_time(Timestamp.valueOf(operateInf.getOperate_tm()));
			<#elseif field.field_id == "ip">
		po.setIp(operateInf.getSubject_ip());
			</#if>
		</#if></#if></#list>
		
		dataDao.add(po);
	}
	
	/** 
	 * 删除数据
	 */
	@Override
	public void deleteData(Object[] keys, CIPRuntimeOperator operateInf){
		dataDao.delete(keys);
		
	}
	
	/** 
	 * 获取数据
	 */		
	@Override
	public ${className}VO getData(Object[] keys ){
		${className}PO  po = dataDao.getSingle(keys);
		if(po == null)
			throw new CIPServiceException(CIPErrorCode.ERROR_RECORD_NOT_EXISTS);
			
		return po.toVO();
	}
	
	/** 
	 * 检索数据
	 */		
	@Override
	public List<${className}Data> searchData(CIPPageInfo page, CIPReqCondition[] conditions){
		
		List<${className}Data> datas = dataDao.searchData(page, conditions);
		return datas;
	}

<#list fields as field>
<#if field.field_id == "ctrl_status" >
	/** 
	 * 锁定${structure.str_name}
	 */
	private static final String C_CONTROL_FIELD = "ctrl_status";
	private static final String C_CONTROL_LOCK = "L";
	private static final String C_CONTROL_UNLOCK = "R";
	
	public void lock(${className}Key key, CIPRuntimeOperator operateInf){
		Map<String, Object> entityFields = new HashMap<String, Object>(3);
		Object[] keys = key.getKeys();
		${className}PO entity = dataDao.getSingle(keys);
		if(entity != null && !entity.getCtrl_status().equals(C_CONTROL_LOCK)){
			entityFields.put(C_CONTROL_FIELD, C_CONTROL_LOCK);
			<#list fields as field><#if field.key_flag==0><#if field.field_use_type == 8>
			<#if field.field_id == "operator">
				<#if field.java_type == "int">
			entityFields.put("operator", Integer.parseInt(operateInf.getSubject_id()));	
				<#else>
			entityFields.put("operator", operateInf.getSubject_id());
				</#if>
				<#elseif field.field_id == "op_time">
			entityFields.put("op_time", Timestamp.valueOf(operateInf.getOperate_tm()));
				<#elseif field.field_id == "update_time">
			entityFields.put("update_time", Timestamp.valueOf(operateInf.getOperate_tm()));
				<#elseif field.field_id == "ip">
			po.setIp(operateInf.getSubject_ip());
				</#if>
			</#if></#if></#list>
			dataDao.updateKV(entityFields, keys);
			
		}
	}
	
	/** 
	 * 解锁${structure.str_name}
	 */
	public void unlock(${className}Key key, CIPRuntimeOperator operateInf){
		Map<String, Object> entityFields = new HashMap<String, Object>(3);
		Object[] keys = key.getKeys();
		${className}PO entity = dataDao.getSingle(keys);
		if(entity != null && !entity.getCtrl_status().equals(C_CONTROL_UNLOCK)){
			entityFields.put(C_CONTROL_FIELD, C_CONTROL_UNLOCK);
			<#list fields as field><#if field.key_flag==0><#if field.field_use_type == 8>
			<#if field.field_id == "operator">
				<#if field.java_type == "int">
			entityFields.put("operator", Integer.parseInt(operateInf.getSubject_id()));	
				<#else>
			entityFields.put("operator", operateInf.getSubject_id());
				</#if>
				<#elseif field.field_id == "op_time">
			entityFields.put("op_time", Timestamp.valueOf(operateInf.getOperate_tm()));
				<#elseif field.field_id == "update_time">
			entityFields.put("update_time", Timestamp.valueOf(operateInf.getOperate_tm()));
				<#elseif field.field_id == "ip">
			po.setIp(operateInf.getSubject_ip());
				</#if>
			</#if></#if></#list>
			dataDao.updateKV(entityFields, keys);
			
			
		}
	}
<#elseif field.field_id == "audit_flag" >
		
	private static final String C_AUDIT_FIELD = "audit_flag";
	private static final String C_AUDIT_APPROVE = "A";
	private static final String C_AUDIT_REJECT = "R";
	
	/** 
	 * 审批${structure.str_name}
	 */
	public void approve(${className}Key key, CIPRuntimeOperator operateInf){
		Map<String, Object> entityFields = new HashMap<String, Object>(3);
		Object[] keys = key.getKeys();
		${className}PO entity = dataDao.getSingle(keys);
		if(entity != null && !entity.getAudit_flag().equals(C_AUDIT_APPROVE)){
			entityFields.put(C_AUDIT_FIELD, C_AUDIT_APPROVE);
			<#list fields as field><#if field.key_flag==0><#if field.field_use_type == 8>
			<#if field.field_id == "operator">
				<#if field.java_type == "int">
			entityFields.put("operator", Integer.parseInt(operateInf.getSubject_id()));	
				<#else>
			entityFields.put("operator", operateInf.getSubject_id());
				</#if>
				<#elseif field.field_id == "op_time">
			entityFields.put("op_time", Timestamp.valueOf(operateInf.getOperate_tm()));
				<#elseif field.field_id == "update_time">
			entityFields.put("update_time", Timestamp.valueOf(operateInf.getOperate_tm()));
				<#elseif field.field_id == "ip">
			po.setIp(operateInf.getSubject_ip());
				</#if>
			</#if></#if></#list>
			dataDao.updateKV(entityFields, keys);
			
		}
	}
	
	/** 
	 * 回退${structure.str_name}
	 */
	public void reject(${className}Key key, CIPRuntimeOperator operateInf){
		Map<String, Object> entityFields = new HashMap<String, Object>(3);
		Object[] keys = key.getKeys();
		${className}PO entity = dataDao.getSingle(keys);
		if(entity != null && !entity.getAudit_flag().equals(C_AUDIT_REJECT)){
			entityFields.put(C_AUDIT_FIELD, C_AUDIT_REJECT);
			<#list fields as field><#if field.key_flag==0><#if field.field_use_type == 8>
			<#if field.field_id == "operator">
				<#if field.java_type == "int">
			entityFields.put("operator", Integer.parseInt(operateInf.getSubject_id()));	
				<#else>
			entityFields.put("operator", operateInf.getSubject_id());
				</#if>
				<#elseif field.field_id == "op_time">
			entityFields.put("op_time", Timestamp.valueOf(operateInf.getOperate_tm()));
				<#elseif field.field_id == "update_time">
			entityFields.put("update_time", Timestamp.valueOf(operateInf.getOperate_tm()));
				<#elseif field.field_id == "ip">
			po.setIp(operateInf.getSubject_ip());
				</#if>
			</#if></#if></#list>
			dataDao.updateKV(entityFields, keys);
		}
	}
</#if>
</#list>
	
	public List<Map<String, Object>> exportEntities(CIPPageInfo page,CIPReqCondition[] conditions,
			CIPRuntimeOperator operateInf, boolean xFirst){
		if(xFirst){
			//do something 
		}
		return dataDao.exportEntities(page, conditions, xFirst);
	}
}