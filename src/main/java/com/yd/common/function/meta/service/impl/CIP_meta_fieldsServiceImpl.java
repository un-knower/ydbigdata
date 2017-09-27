package com.yd.common.function.meta.service.impl;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.yd.common.constants.OperateMode;
import com.yd.common.data.CIPPageInfo;
import com.yd.common.data.CIPReqCondition;
import com.yd.common.exception.CIPServiceException;
import com.yd.common.function.meta.dao.CIP_meta_field_logDao;
import com.yd.common.function.meta.dao.CIP_meta_fieldsDao;
import com.yd.common.function.meta.data.CIP_meta_fieldsData;
import com.yd.common.function.meta.data.po.CIP_meta_field_logPO;
import com.yd.common.function.meta.data.po.CIP_meta_fieldsPO;
import com.yd.common.function.meta.data.vo.CIP_meta_fieldsVO;
import com.yd.common.function.meta.service.CIP_meta_fieldsService;
import com.yd.common.runtime.CIPErrorCode;
import com.yd.common.runtime.CIPRuntimeOperator;
import com.yd.common.utils.ReflectUtils;

/**
 * <p>Service实现类</p>
 * <p>Class: CIP_meta_fieldsService</p>
 *
 * @since 2015-10-08 09:03:37
 */

@Service(value="CIP_meta_fieldsService")
public class CIP_meta_fieldsServiceImpl implements CIP_meta_fieldsService {
	
	@Autowired
	private CIP_meta_fieldsDao dataDao = null;
	@Autowired
	private CIP_meta_field_logDao logDao = null;
	
	/** 
	 * 修改数据
	 */
	@Override
	public void updateData(CIP_meta_fieldsVO vo, CIPRuntimeOperator operateInf){
		//TODO
		CIP_meta_fieldsPO po = vo.toPO();
		Object[] keys = po.getKeys();
		CIP_meta_fieldsPO po0 = dataDao.getSingle(keys);
		if( po0 == null ){
			throw new CIPServiceException(CIPErrorCode.ERROR_RECORD_NOT_EXISTS);
		}
		po.setUpdate_time(Timestamp.valueOf(operateInf.getOperate_tm()));
		po.setOperator(operateInf.getSubject_id());
		po.setCreate_time(po0.getCreate_time());
		po0.setUpdate_time(Timestamp.valueOf(operateInf.getOperate_tm()));
		po0.setOperator(operateInf.getSubject_id());

		List<Field> diffField;
		try {
			diffField = ReflectUtils.contrastDiff(po, po0);
		} catch (Exception e) {
			e.printStackTrace();
			throw new CIPServiceException(new CIPErrorCode(-1, "获取字段变更失败"));
		}
		if(diffField!=null && diffField.size()>0){
			//修改记录前判断有无新增或修改记录，有新增记录则还为新增操作，有修改记录则为修改操作
			String str_id = po.getStr_id();
			String field_id = po.getField_id();
			Map<String, Object> conMap = new HashMap<String, Object>();
			conMap.put("str_id", str_id);
			conMap.put("field_id", field_id);
			conMap.put("op_type", OperateMode.CREATE.getCode());
			List<CIP_meta_field_logPO> logList = logDao.getByCondition(conMap);
			if(logList!=null && logList.size()>0){
				CIP_meta_field_logPO oldLog = logList.get(0);
				oldLog.setNew_value(JSON.toJSONString(po, false));
				logDao.update(oldLog);
			}else{
				List<CIP_meta_field_logPO> addLogs = new ArrayList<>();
				List<Object[]> delLogs = new ArrayList<>();
				for(Field field : diffField){
					String op_field = field.getName();
					String oldValue = null;
					String newValue = null;
					try {
						oldValue = field.get(po0)==null?"":field.get(po0).toString();
						newValue = field.get(po)==null?"": field.get(po).toString();
					} catch (Exception e) {
						e.printStackTrace();
						throw new CIPServiceException(new CIPErrorCode(-1, "获取字段变更值失败"));
					}
					conMap.put("op_field", op_field);
					conMap.put("op_type", OperateMode.UPDATE.getCode());
					logList = logDao.getByCondition(conMap);
					CIP_meta_field_logPO log = null;
					if(logList!=null && logList.size()>0){
						CIP_meta_field_logPO oldLog = logList.get(0);
						delLogs.add(oldLog.getKeys());
						if(oldLog.getOld_value().equals(newValue)){
							continue;
						}
						//始终以上次修改的旧值作为此次修改的旧值
						oldValue = oldLog.getOld_value();
					}
					log = new CIP_meta_field_logPO();
					log.setStr_id(str_id);
					log.setField_id(field_id);
					log.setOp_field(op_field);
					log.setOp_type(OperateMode.UPDATE.getCode());
					log.setOld_value(oldValue);
					log.setNew_value(newValue);
					log.setOper_time(Timestamp.valueOf(operateInf.getOperate_tm()));
					log.setOperator(operateInf.getSubject_id());
					log.setOp_flag("T");
					addLogs.add(log);
					
				}
				logDao.batchDelete(delLogs);
				logDao.batchAdd(addLogs);
				dataDao.update(po);
			}

		}

	}
	
	/** 
	 * 添加数据
	 */
	@Override
	public void addData(CIP_meta_fieldsVO vo, CIPRuntimeOperator operateInf){
		CIP_meta_fieldsPO po = vo.toPO();
		Object[] keys = po.getKeys();
		CIP_meta_fieldsPO po0 = dataDao.getSingle(keys);
		if( po0!= null ){
			throw new CIPServiceException(CIPErrorCode.ERROR_DUMPLICATE_PRIMARY_KEY);
		}
		//TODO　添加记录基本判断
		po.setCreate_time(Timestamp.valueOf(operateInf.getOperate_tm()));
		po.setOperator(operateInf.getSubject_id());
		dataDao.add(po);
		
		//获取删除变更记录
		Map<String, Object> conMap = new HashMap<String, Object>();
		conMap.put("str_id", po.getStr_id());
		conMap.put("field_id", po.getField_id());
		conMap.put("op_type", OperateMode.DELETE.getCode());
		List<CIP_meta_field_logPO> logList = logDao.getByCondition(conMap);
		
		if(logList==null||logList.size()==0){
			CIP_meta_field_logPO log = new CIP_meta_field_logPO();
			log.setStr_id(po.getStr_id());
			log.setField_id(po.getField_id());
			log.setOp_field("");
			log.setOp_type(OperateMode.CREATE.getCode());
			log.setOld_value("");
			log.setNew_value(JSON.toJSONString(po, false));
			log.setOper_time(Timestamp.valueOf(operateInf.getOperate_tm()));
			log.setOperator(operateInf.getSubject_id());
			log.setOp_flag("T");
			logDao.add(log);
		}else{
			CIP_meta_field_logPO delLogPO = logList.get(0);
			CIP_meta_fieldsPO fieldPO =JSON.parseObject(delLogPO.getOld_value(),CIP_meta_fieldsPO.class);
			po.setCreate_time(fieldPO.getCreate_time());
			po.setOperator(fieldPO.getOperator());
			po.setUpdate_time(fieldPO.getUpdate_time());
			List<Field> diffField;
			try {
				diffField = ReflectUtils.contrastDiff(fieldPO, po);
			} catch (Exception e) {
				throw new CIPServiceException(new CIPErrorCode(-1, "获取字段变更失败"));
			}
			List<CIP_meta_field_logPO> addLogs = new ArrayList<>();
			for(Field field : diffField){
				String op_field = field.getName();
				String oldValue = null;
				String newValue = null;
				try {
					oldValue = field.get(fieldPO)!=null?field.get(fieldPO).toString():"";
					newValue = field.get(po)!=null?field.get(po).toString():"";
				} catch (Exception e) {
					throw new CIPServiceException(new CIPErrorCode(-1, "获取字段变更值失败"));
				}
				CIP_meta_field_logPO log = new CIP_meta_field_logPO();
				log.setStr_id(po.getStr_id());
				log.setField_id(po.getField_id());
				log.setOp_field(op_field);
				log.setOp_type(OperateMode.UPDATE.getCode());
				log.setOld_value(oldValue);
				log.setNew_value(newValue);
				log.setOper_time(Timestamp.valueOf(operateInf.getOperate_tm()));
				log.setOperator(operateInf.getSubject_id());
				log.setOp_flag("T");
				addLogs.add(log);
			}
			logDao.delete(delLogPO.getKeys());
			logDao.batchAdd(addLogs);
		}
	}
	
	/** 
	 * 删除数据
	 */
	@Override
	public void deleteData(Object[] keys, CIPRuntimeOperator operateInf){
		CIP_meta_fieldsPO  po = dataDao.getSingle(keys);
		if(po == null)
			throw new CIPServiceException(CIPErrorCode.ERROR_RECORD_NOT_EXISTS);
		dataDao.delete(keys);
		
		//获取新增变更记录
		Map<String, Object> conMap = new HashMap<String, Object>();
		conMap.put("str_id", po.getStr_id());
		conMap.put("field_id", po.getField_id());
		conMap.put("op_type", OperateMode.CREATE.getCode());
		List<CIP_meta_field_logPO> logList = logDao.getByCondition(conMap);
		
		if(logList==null||logList.size()==0){
			//获取修改变更记录,如果有修改记录则删除记录旧值为修改前旧值
			conMap.put("op_type", OperateMode.UPDATE.getCode());
			logList = logDao.getByCondition(conMap);
			if(logList!=null&&logList.size()>0){
				List<Object[]> keysArr = new ArrayList<>();
				for(CIP_meta_field_logPO updateLog : logList){
					String field = updateLog.getOp_field();
					String oldValue = updateLog.getOld_value();
					ReflectUtils.setValue(po, field, oldValue);
					keysArr.add(updateLog.getKeys());
				}
				logDao.batchDelete(keysArr);
			}
			String oldValue = JSON.toJSONString(po, false);
			
			CIP_meta_field_logPO log = new CIP_meta_field_logPO();
			log.setStr_id(po.getStr_id());
			log.setField_id(po.getField_id());
			log.setOp_field("");
			log.setOp_type(OperateMode.DELETE.getCode());
			log.setOld_value(oldValue);
			log.setNew_value("");
			log.setOper_time(Timestamp.valueOf(operateInf.getOperate_tm()));
			log.setOperator(operateInf.getSubject_id());
			log.setOp_flag("T");
			logDao.add(log);
		}else{
			//删除变更记录
			CIP_meta_field_logPO addLogPO = logList.get(0);
			logDao.delete(addLogPO.getKeys());
			//删除相关变更记录
			logDao.deleteByStrIdAndFieldId(po.getStr_id(),po.getField_id());
		}
	}
	
	/** 
	 * 获取数据
	 */		
	@Override
	public CIP_meta_fieldsVO getData(Object[] keys ){
		CIP_meta_fieldsPO  po = dataDao.getSingle(keys);
		if(po == null)
			throw new CIPServiceException(CIPErrorCode.ERROR_RECORD_NOT_EXISTS);
			
		return po.toVO();
	}
	
	/** 
	 * 检索数据
	 */		
	@Override
	public List<CIP_meta_fieldsData> searchData(CIPPageInfo page, CIPReqCondition[] conditions){
		
		List<CIP_meta_fieldsData> datas = dataDao.searchData(page, conditions);
		return datas;
	}

	
	public List<Map<String, Object>> exportEntities(CIPPageInfo page,CIPReqCondition[] conditions,
			CIPRuntimeOperator operateInf, boolean xFirst){
		if(xFirst){
			//do something 
		}
		return dataDao.exportEntities(page, conditions, xFirst);
	}
}