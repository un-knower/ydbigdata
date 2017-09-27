package com.yd.common.function.test.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.common.data.CIPPageInfo;
import com.yd.common.data.CIPReqCondition;
import com.yd.common.exception.CIPServiceException;
import com.yd.common.function.test.dao.CIP_test_resultDao;
import com.yd.common.function.test.data.CIP_test_resultData;
import com.yd.common.function.test.data.po.CIP_test_resultPO;
import com.yd.common.function.test.data.vo.CIP_test_resultVO;
import com.yd.common.function.test.service.CIP_test_resultService;
import com.yd.common.runtime.CIPErrorCode;
import com.yd.common.runtime.CIPRuntimeOperator;

/**
 * <p>Service实现类</p>
 * <p>Class: CIP_test_resultService</p>
 *
 * @since 2015-10-10 02:52:52
 */

@Service(value="CIP_test_resultService")
public class CIP_test_resultServiceImpl implements CIP_test_resultService {
	
	@Autowired
	private CIP_test_resultDao dataDao = null;

	/** 
	 * 修改数据
	 */
	@Override
	public void updateData(CIP_test_resultVO vo, CIPRuntimeOperator operateInf){
		//TODO
		CIP_test_resultPO po = vo.toPO();
		Object[] keys = po.getKeys();
		CIP_test_resultPO po0 = dataDao.getSingle(keys);
		if( po0 == null ){
			throw new CIPServiceException(CIPErrorCode.ERROR_RECORD_NOT_EXISTS);
		}
		
		
		dataDao.update(po);
		
	}
	
	/** 
	 * 添加数据
	 */
	@Override
	public void addData(CIP_test_resultVO vo, CIPRuntimeOperator operateInf){
		CIP_test_resultPO po = vo.toPO();
		Object[] keys = po.getKeys();
		CIP_test_resultPO po0 = dataDao.getSingle(keys);
		if( po0!= null ){
			throw new CIPServiceException(CIPErrorCode.ERROR_DUMPLICATE_PRIMARY_KEY);
		}
		
		//TODO　添加记录基本判断
		
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
	public CIP_test_resultVO getData(Object[] keys ){
		CIP_test_resultPO  po = dataDao.getSingle(keys);
		if(po == null)
			throw new CIPServiceException(CIPErrorCode.ERROR_RECORD_NOT_EXISTS);
			
		return po.toVO();
	}
	
	/** 
	 * 检索数据
	 */		
	@Override
	public List<CIP_test_resultData> searchData(CIPPageInfo page, CIPReqCondition[] conditions){
		
		List<CIP_test_resultData> datas = dataDao.searchData(page, conditions);
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