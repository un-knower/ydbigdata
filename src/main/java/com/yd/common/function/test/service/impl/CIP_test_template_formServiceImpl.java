package com.yd.common.function.test.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.common.data.CIPPageInfo;
import com.yd.common.data.CIPReqCondition;
import com.yd.common.exception.CIPServiceException;
import com.yd.common.function.test.dao.CIP_test_template_formDao;
import com.yd.common.function.test.data.CIP_test_template_formData;
import com.yd.common.function.test.data.po.CIP_test_template_formPO;
import com.yd.common.function.test.data.vo.CIP_test_template_formVO;
import com.yd.common.function.test.service.CIP_test_template_formService;
import com.yd.common.runtime.CIPErrorCode;
import com.yd.common.runtime.CIPRuntimeOperator;

/**
 * <p>Service实现类</p>
 * <p>Class: CIP_test_template_formService</p>
 *
 * @since 2015-11-24 04:16:55
 */

@Service(value="CIP_test_template_formService")
public class CIP_test_template_formServiceImpl implements CIP_test_template_formService {
	
	@Autowired
	private CIP_test_template_formDao dataDao = null;

	/** 
	 * 修改数据
	 */
	@Override
	public void updateData(CIP_test_template_formVO vo, CIPRuntimeOperator operateInf){
		CIP_test_template_formPO po = vo.toPO();
		Object[] keys = po.getKeys();
		CIP_test_template_formPO po0 = dataDao.getSingle(keys);
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
	public void addData(CIP_test_template_formVO vo, CIPRuntimeOperator operateInf){
		CIP_test_template_formPO po = vo.toPO();
		Object[] keys = po.getKeys();
		CIP_test_template_formPO po0 = dataDao.getSingle(keys);
		if( po0!= null ){
			throw new CIPServiceException(CIPErrorCode.ERROR_DUMPLICATE_PRIMARY_KEY);
		}
		
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
	public CIP_test_template_formVO getData(Object[] keys ){
		CIP_test_template_formPO  po = dataDao.getSingle(keys);
		if(po == null)
			throw new CIPServiceException(CIPErrorCode.ERROR_RECORD_NOT_EXISTS);
			
		return po.toVO();
	}
	
	/** 
	 * 检索数据
	 */		
	@Override
	public List<CIP_test_template_formData> searchData(CIPPageInfo page, CIPReqCondition[] conditions){
		
		List<CIP_test_template_formData> datas = dataDao.searchData(page, conditions);
		return datas;
	}

	
	public List<Map<String, Object>> exportEntities(CIPPageInfo page,CIPReqCondition[] conditions,
			CIPRuntimeOperator operateInf, boolean xFirst){
		if(xFirst){
			//do something 
		}
		return dataDao.exportEntities(page, conditions, xFirst);
	}

	/**
	 * 检索特定模板的数据
	 */
	@Override
	public List<CIP_test_template_formVO> searchTemplateData(String templateID) {
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("template_id",templateID);
		List<CIP_test_template_formPO> formPOs = new ArrayList<CIP_test_template_formPO>();
		formPOs = dataDao.getByCondition(condition);
		List<CIP_test_template_formVO> formVOs = new ArrayList<CIP_test_template_formVO>();
		for(CIP_test_template_formPO po:formPOs){
			formVOs.add(po.toVO());
		}
		return formVOs;
	}
}