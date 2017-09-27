package com.yd.common.function.meta.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.common.data.CIPPageInfo;
import com.yd.common.data.CIPReqCondition;
import com.yd.common.exception.CIPServiceException;
import com.yd.common.function.meta.dao.CIP_meta_field_logDao;
import com.yd.common.function.meta.data.CIP_meta_field_logData;
import com.yd.common.function.meta.data.po.CIP_meta_field_logPO;
import com.yd.common.function.meta.data.vo.CIP_meta_field_logVO;
import com.yd.common.function.meta.service.CIP_meta_field_logService;
import com.yd.common.runtime.CIPErrorCode;
import com.yd.common.runtime.CIPRuntimeOperator;

/**
 * <p>Service实现类</p>
 * <p>Class: CIP_meta_field_logService</p>
 *
 * @since 2015-10-08 09:03:37
 */

@Service(value="CIP_meta_field_logService")
public class CIP_meta_field_logServiceImpl implements CIP_meta_field_logService {
	
	@Autowired
	private CIP_meta_field_logDao dataDao = null;

	/** 
	 * 修改数据
	 */
	@Override
	public void updateData(CIP_meta_field_logVO vo, CIPRuntimeOperator operateInf){
		//TODO
		CIP_meta_field_logPO po = vo.toPO();
		Object[] keys = po.getKeys();
		CIP_meta_field_logPO po0 = dataDao.getSingle(keys);
		if( po0 == null ){
			throw new CIPServiceException(CIPErrorCode.ERROR_RECORD_NOT_EXISTS);
		}
		
		po.setOperator(operateInf.getSubject_id());
		
		
		dataDao.update(po);
		
	}
	
	/** 
	 * 添加数据
	 */
	@Override
	public void addData(CIP_meta_field_logVO vo, CIPRuntimeOperator operateInf){
		CIP_meta_field_logPO po = vo.toPO();
		Object[] keys = po.getKeys();
		CIP_meta_field_logPO po0 = dataDao.getSingle(keys);
		if( po0!= null ){
			throw new CIPServiceException(CIPErrorCode.ERROR_DUMPLICATE_PRIMARY_KEY);
		}
		
		//TODO　添加记录基本判断
		po.setOperator(operateInf.getSubject_id());
		
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
	public CIP_meta_field_logVO getData(Object[] keys ){
		CIP_meta_field_logPO  po = dataDao.getSingle(keys);
		if(po == null)
			throw new CIPServiceException(CIPErrorCode.ERROR_RECORD_NOT_EXISTS);
			
		return po.toVO();
	}
	
	/** 
	 * 检索数据
	 */		
	@Override
	public List<CIP_meta_field_logData> searchData(CIPPageInfo page, CIPReqCondition[] conditions){
		
		List<CIP_meta_field_logData> datas = dataDao.searchData(page, conditions);
		return datas;
	}

	
	public List<Map<String, Object>> exportEntities(CIPPageInfo page,CIPReqCondition[] conditions,
			CIPRuntimeOperator operateInf, boolean xFirst){
		if(xFirst){
			//do something 
		}
		return dataDao.exportEntities(page, conditions, xFirst);
	}

	@Override
	public List<CIP_meta_field_logPO> searchAll(CIPReqCondition[] conditions) {
		if(conditions==null){
			return dataDao.getAll();
		}else{
			Map<String,Object> map = new HashMap<String, Object>();
			for(int i =0;i<conditions.length;i++){
				map.put(conditions[i].getFieldName(), conditions[i].getLowValue());
			}		
			
			return dataDao.getByCondition(map);
		}
	}
}