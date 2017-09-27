package com.yd.common.function.test.service.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.common.data.CIPPageInfo;
import com.yd.common.data.CIPReqCondition;
import com.yd.common.exception.CIPServiceException;
import com.yd.common.function.test.dao.CIP_test_templateDao;
import com.yd.common.function.test.data.CIP_test_templateData;
import com.yd.common.function.test.data.po.CIP_test_templatePO;
import com.yd.common.function.test.data.vo.CIP_test_templateVO;
import com.yd.common.function.test.service.CIP_test_templateService;
import com.yd.common.runtime.CIPErrorCode;
import com.yd.common.runtime.CIPRuntimeOperator;

/**
 * <p>Service实现类</p>
 * <p>Class: CIP_test_templateService</p>
 *
 * @since 2015-11-24 04:16:55
 */

@Service(value="CIP_test_templateService")
public class CIP_test_templateServiceImpl implements CIP_test_templateService {
	
	@Autowired
	private CIP_test_templateDao dataDao = null;

	/** 
	 * 修改数据
	 */
	@Override
	public void updateData(CIP_test_templateVO vo, CIPRuntimeOperator operateInf){
		//TODO
		CIP_test_templatePO po = vo.toPO();
		Object[] keys = po.getKeys();
		CIP_test_templatePO po0 = dataDao.getSingle(keys);
		if( po0 == null ){
			throw new CIPServiceException(CIPErrorCode.ERROR_RECORD_NOT_EXISTS);
		}
		
		po.setUpdate_time(Timestamp.valueOf(operateInf.getOperate_tm()));
		po.setOperator(operateInf.getSubject_id());
		
		
		dataDao.update(po);
		
	}
	
	/** 
	 * 添加数据
	 */
	@Override
	public void addData(CIP_test_templateVO vo, CIPRuntimeOperator operateInf){
		CIP_test_templatePO po = vo.toPO();
		Object[] keys = po.getKeys();
		CIP_test_templatePO po0 = dataDao.getSingle(keys);
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
	public CIP_test_templateVO getData(Object[] keys ){
		CIP_test_templatePO  po = dataDao.getSingle(keys);
		if(po == null)
			throw new CIPServiceException(CIPErrorCode.ERROR_RECORD_NOT_EXISTS);
			
		return po.toVO();
	}
	
	/** 
	 * 检索数据
	 */		
	@Override
	public List<CIP_test_templateData> searchData(CIPPageInfo page, CIPReqCondition[] conditions){
		
		List<CIP_test_templateData> datas = dataDao.searchData(page, conditions);
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