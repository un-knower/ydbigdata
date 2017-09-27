package com.yd.common.function.test.service.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.common.data.CIPPageInfo;
import com.yd.common.data.CIPReqCondition;
import com.yd.common.exception.CIPServiceException;
import com.yd.common.function.test.dao.CIP_test_scenarioDao;
import com.yd.common.function.test.data.CIP_test_scenarioData;
import com.yd.common.function.test.data.po.CIP_test_scenarioPO;
import com.yd.common.function.test.data.vo.CIP_test_scenarioVO;
import com.yd.common.function.test.service.CIP_test_scenarioService;
import com.yd.common.runtime.CIPErrorCode;
import com.yd.common.runtime.CIPRuntimeOperator;

/**
 * <p>Service实现类</p>
 * <p>Class: CIP_test_scenarioService</p>
 *
 * @since 2015-10-10 02:52:52
 */

@Service(value="CIP_test_scenarioService")
public class CIP_test_scenarioServiceImpl implements CIP_test_scenarioService {
	
	@Autowired
	private CIP_test_scenarioDao dataDao = null;

	/** 
	 * 修改数据
	 */
	@Override
	public void updateData(CIP_test_scenarioVO vo, CIPRuntimeOperator operateInf){
		//TODO
		CIP_test_scenarioPO po = vo.toPO();
		Object[] keys = po.getKeys();
		CIP_test_scenarioPO po0 = dataDao.getSingle(keys);
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
	public void addData(CIP_test_scenarioVO vo, CIPRuntimeOperator operateInf){
		CIP_test_scenarioPO po = vo.toPO();
		Object[] keys = po.getKeys();
		CIP_test_scenarioPO po0 = dataDao.getSingle(keys);
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
	public CIP_test_scenarioVO getData(Object[] keys ){
		CIP_test_scenarioPO  po = dataDao.getSingle(keys);
		if(po == null)
			throw new CIPServiceException(CIPErrorCode.ERROR_RECORD_NOT_EXISTS);
			
		return po.toVO();
	}
	
	/** 
	 * 检索数据
	 */		
	@Override
	public List<CIP_test_scenarioData> searchData(CIPPageInfo page, CIPReqCondition[] conditions){
		
		List<CIP_test_scenarioData> datas = dataDao.searchData(page, conditions);
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