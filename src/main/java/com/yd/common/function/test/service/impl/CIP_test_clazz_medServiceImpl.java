package com.yd.common.function.test.service.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.common.data.CIPPageInfo;
import com.yd.common.data.CIPReqCondition;
import com.yd.common.exception.CIPServiceException;
import com.yd.common.function.test.dao.CIP_test_clazz_medDao;
import com.yd.common.function.test.data.CIP_test_clazz_medData;
import com.yd.common.function.test.data.po.CIP_test_clazz_medPO;
import com.yd.common.function.test.data.vo.CIP_test_clazz_medVO;
import com.yd.common.function.test.service.CIP_test_clazz_medService;
import com.yd.common.runtime.CIPErrorCode;
import com.yd.common.runtime.CIPRuntimeOperator;

/**
 * <p>Service实现类</p>
 * <p>Class: CIP_test_clazz_medService</p>
 *
 * @since 2015-10-10 02:52:52
 */

@Service(value="CIP_test_clazz_medService")
public class CIP_test_clazz_medServiceImpl implements CIP_test_clazz_medService {
	
	@Autowired
	private CIP_test_clazz_medDao dataDao = null;

	/** 
	 * 修改数据
	 */
	@Override
	public void updateData(CIP_test_clazz_medVO vo, CIPRuntimeOperator operateInf){
		//TODO
		CIP_test_clazz_medPO po = vo.toPO();
		Object[] keys = po.getKeys();
		CIP_test_clazz_medPO po0 = dataDao.getSingle(keys);
		if( po0 == null ){
			throw new CIPServiceException(CIPErrorCode.ERROR_RECORD_NOT_EXISTS);
		}
		
		po.setOperator(operateInf.getSubject_id());
		
		po.setUpdate_time(Timestamp.valueOf(operateInf.getOperate_tm()));
		
		dataDao.update(po);
		
	}
	
	/** 
	 * 添加数据
	 */
	@Override
	public void addData(CIP_test_clazz_medVO vo, CIPRuntimeOperator operateInf){
		CIP_test_clazz_medPO po = vo.toPO();
		Object[] keys = po.getKeys();
		CIP_test_clazz_medPO po0 = dataDao.getSingle(keys);
		if( po0!= null ){
			throw new CIPServiceException(CIPErrorCode.ERROR_DUMPLICATE_PRIMARY_KEY);
		}
		
		//TODO　添加记录基本判断
		po.setCreate_time(Timestamp.valueOf(operateInf.getOperate_tm()));
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
	public CIP_test_clazz_medVO getData(Object[] keys ){
		CIP_test_clazz_medPO  po = dataDao.getSingle(keys);
		if(po == null)
			throw new CIPServiceException(CIPErrorCode.ERROR_RECORD_NOT_EXISTS);
			
		return po.toVO();
	}
	
	/** 
	 * 检索数据
	 */		
	@Override
	public List<CIP_test_clazz_medData> searchData(CIPPageInfo page, CIPReqCondition[] conditions){
		
		List<CIP_test_clazz_medData> datas = dataDao.searchData(page, conditions);
		return datas;
	}

	/**
	 * 依据sysid检索数据
	 */
	@Override
	public List<CIP_test_clazz_medPO> searchAllData(String sysid){
		Map<String, Object> arg0 = new HashMap<String, Object>();
		arg0.put("sys_id", sysid);
		List<CIP_test_clazz_medPO> datas = dataDao.getByCondition(arg0);
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