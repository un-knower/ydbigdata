package com.yd.common.function.meta.service.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.common.data.CIPPageInfo;
import com.yd.common.data.CIPReqCondition;
import com.yd.common.exception.CIPServiceException;
import com.yd.common.function.meta.dao.CIP_meta_jobDao;
import com.yd.common.function.meta.data.CIP_meta_jobData;
import com.yd.common.function.meta.data.po.CIP_meta_jobPO;
import com.yd.common.function.meta.data.vo.CIP_meta_jobVO;
import com.yd.common.function.meta.service.CIP_meta_jobService;
import com.yd.common.runtime.CIPErrorCode;
import com.yd.common.runtime.CIPRuntimeOperator;

/**
 * <p>Service实现类</p>
 * <p>Class: CIP_meta_jobService</p>
 *
 * @since 2015-10-08 09:03:37
 */

@Service(value="CIP_meta_jobService")
public class CIP_meta_jobServiceImpl implements CIP_meta_jobService {
	
	@Autowired
	private CIP_meta_jobDao dataDao = null;

	/** 
	 * 修改数据
	 */
	@Override
	public void updateData(CIP_meta_jobVO vo, CIPRuntimeOperator operateInf){
		//TODO
		CIP_meta_jobPO po = vo.toPO();
		Object[] keys = po.getKeys();
		CIP_meta_jobPO po0 = dataDao.getSingle(keys);
		if( po0 == null ){
			throw new CIPServiceException(CIPErrorCode.ERROR_RECORD_NOT_EXISTS);
		}
		
		po.setOp_time(Timestamp.valueOf(operateInf.getOperate_tm()));
		po.setOperator(operateInf.getSubject_id());
		
		
		dataDao.update(po);
		
	}
	
	/** 
	 * 添加数据
	 */
	@Override
	public void addData(CIP_meta_jobVO vo, CIPRuntimeOperator operateInf){
		CIP_meta_jobPO po = vo.toPO();
		Object[] keys = po.getKeys();
		CIP_meta_jobPO po0 = dataDao.getSingle(keys);
		if( po0!= null ){
			throw new CIPServiceException(CIPErrorCode.ERROR_DUMPLICATE_PRIMARY_KEY);
		}
		
		//TODO　添加记录基本判断
		po.setOp_time(Timestamp.valueOf(operateInf.getOperate_tm()));
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
	public CIP_meta_jobVO getData(Object[] keys ){
		CIP_meta_jobPO  po = dataDao.getSingle(keys);
		if(po == null)
			throw new CIPServiceException(CIPErrorCode.ERROR_RECORD_NOT_EXISTS);
			
		return po.toVO();
	}
	
	/** 
	 * 检索数据
	 */		
	@Override
	public List<CIP_meta_jobData> searchData(CIPPageInfo page, CIPReqCondition[] conditions){
		
		List<CIP_meta_jobData> datas = dataDao.searchData(page, conditions);
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