package com.yd.common.function.admin.service.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.common.data.CIPPageInfo;
import com.yd.common.data.CIPReqCondition;
import com.yd.common.exception.CIPServiceException;
import com.yd.common.function.admin.dao.CIP_admin_questionDao;
import com.yd.common.function.admin.data.CIP_admin_questionData;
import com.yd.common.function.admin.data.po.CIP_admin_questionPO;
import com.yd.common.function.admin.data.vo.CIP_admin_questionVO;
import com.yd.common.function.admin.service.CIP_admin_questionService;
import com.yd.common.runtime.CIPErrorCode;
import com.yd.common.runtime.CIPRuntimeOperator;
import com.yd.common.utils.SerialNoUtils;

/**
 * <p>Service实现类</p>
 * <p>Class: CIP_admin_questionService</p>
 *
 * @since 2015-12-25 01:45:43
 */

@Service(value="CIP_admin_questionService")
public class CIP_admin_questionServiceImpl implements CIP_admin_questionService {
	
	@Autowired
	private CIP_admin_questionDao dataDao = null;

	/** 
	 * 修改数据
	 */
	@Override
	public void updateData(CIP_admin_questionVO vo, CIPRuntimeOperator operateInf){
		//TODO
		CIP_admin_questionPO po = vo.toPO();
		Object[] keys = po.getKeys();
		CIP_admin_questionPO po0 = dataDao.getSingle(keys);
		if( po0 == null ){
			throw new CIPServiceException(CIPErrorCode.ERROR_RECORD_NOT_EXISTS);
		}
		
		po.setUpdate_time(Timestamp.valueOf(operateInf.getOperate_tm()));
		po.setOperater(operateInf.getSubject_id());
		
		dataDao.update(po);
		
	}
	
	/** 
	 * 添加数据
	 */
	@Override
	public void addData(CIP_admin_questionVO vo, CIPRuntimeOperator operateInf){
		vo.setQuestion_id(String.valueOf(SerialNoUtils.getTimeSerialNo()));
		CIP_admin_questionPO po = vo.toPO();
		Object[] keys = po.getKeys();
		CIP_admin_questionPO po0 = dataDao.getSingle(keys);
		if( po0!= null ){
			throw new CIPServiceException(CIPErrorCode.ERROR_DUMPLICATE_PRIMARY_KEY);
		}
		//TODO　添加记录基本判断
		po.setCreate_time(Timestamp.valueOf(operateInf.getOperate_tm()));
		po.setCreater(operateInf.getSubject_id());
		
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
	public CIP_admin_questionVO getData(Object[] keys ){
		CIP_admin_questionPO  po = dataDao.getSingle(keys);
		if(po == null)
			throw new CIPServiceException(CIPErrorCode.ERROR_RECORD_NOT_EXISTS);
			
		return po.toVO();
	}
	
	/** 
	 * 检索数据
	 */		
	@Override
	public List<CIP_admin_questionData> searchData(CIPPageInfo page, CIPReqCondition[] conditions){
		
		List<CIP_admin_questionData> datas = dataDao.searchData(page, conditions);
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
	public void handleData(CIP_admin_questionVO vo,
			CIPRuntimeOperator operateInf) {
		CIP_admin_questionPO po = vo.toPO();
		Object[] keys = po.getKeys();
		CIP_admin_questionPO po0 = dataDao.getSingle(keys);
		if( po0 == null ){
			throw new CIPServiceException(CIPErrorCode.ERROR_RECORD_NOT_EXISTS);
		}
		if("1".equals(po0.getHandle_flag())){
			throw new CIPServiceException(new CIPErrorCode(-1, "该问题已解决"));
		}
		
		po0.setQuestion_reply(po.getQuestion_reply());
		po0.setHandle_flag("1");
		po0.setUpdate_time(Timestamp.valueOf(operateInf.getOperate_tm()));
		po0.setOperater(operateInf.getSubject_id());
		
		dataDao.update(po0);
		
	}
}