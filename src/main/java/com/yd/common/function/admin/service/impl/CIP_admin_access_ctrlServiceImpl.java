package com.yd.common.function.admin.service.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.common.cipher.CIPDesUtils;
import com.yd.common.data.CIPPageInfo;
import com.yd.common.data.CIPReqCondition;
import com.yd.common.exception.CIPServiceException;
import com.yd.common.function.admin.dao.CIP_admin_access_ctrlDao;
import com.yd.common.function.admin.dao.CIP_admin_op_logDao;
import com.yd.common.function.admin.data.CIP_admin_access_ctrlData;
import com.yd.common.function.admin.data.po.CIP_admin_access_ctrlPO;
import com.yd.common.function.admin.data.vo.CIP_admin_access_ctrlVO;
import com.yd.common.function.admin.service.CIP_admin_access_ctrlService;
import com.yd.common.runtime.CIPErrorCode;
import com.yd.common.runtime.CIPRuntimeOperator;

/**
 * <p>Service实现类</p>
 * <p>Class: CIP_admin_access_ctrlService</p>
 *
 * @since 2015-08-18 02:49:22
 */

@Service
public class CIP_admin_access_ctrlServiceImpl implements CIP_admin_access_ctrlService {
	
	@Autowired
	private CIP_admin_access_ctrlDao dataDao = null;
	@Autowired
	private CIP_admin_op_logDao opLogDao =null;
	
	/** 
	 * 修改数据
	 */
	@Override
	public void updateData(CIP_admin_access_ctrlVO vo, CIPRuntimeOperator operateInf){
		//TODO
		CIP_admin_access_ctrlPO po = vo.toPO();
		Object[] keys = po.getKeys();
		CIP_admin_access_ctrlPO po0 = dataDao.getSingle(keys);
		if( po0 == null ){
			throw new CIPServiceException(CIPErrorCode.ERROR_RECORD_NOT_EXISTS);
		}
		//加密authCode，远程系统用加密后的代码
		String authCode = CIPDesUtils.encryptInternal(vo.getAuth_code()); 
		po.setAuth_code(authCode);
		
		po.setUpdate_time(Timestamp.valueOf(operateInf.getOperate_tm()));
		po.setOperator(operateInf.getSubject_id());
		
		
		dataDao.update(po);
		
	}
	
	/** 
	 * 添加数据
	 */
	@Override
	public void addData(CIP_admin_access_ctrlVO vo, CIPRuntimeOperator operateInf){
		CIP_admin_access_ctrlPO po = vo.toPO();
		Object[] keys = po.getKeys();
		CIP_admin_access_ctrlPO po0 = dataDao.getSingle(keys);
		if( po0!= null ){
			throw new CIPServiceException(CIPErrorCode.ERROR_DUMPLICATE_PRIMARY_KEY);
		}
		//加密authCode，远程系统用加密后的代码
		String authCode = CIPDesUtils.encryptInternal(vo.getAuth_code()); 
		po.setAuth_code(authCode);

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
	public CIP_admin_access_ctrlVO getData(Object[] keys ){
		CIP_admin_access_ctrlPO  po = dataDao.getSingle(keys);
		if(po == null)
			throw new CIPServiceException(CIPErrorCode.ERROR_RECORD_NOT_EXISTS);
		
		String authCode = CIPDesUtils.decryptInternal(po.getAuth_code()); 
		po.setAuth_code(authCode);
		
		return po.toVO();
	}
	
	/** 
	 * 检索数据
	 */		
	@Override
	public List<CIP_admin_access_ctrlData> searchData(CIPPageInfo page, CIPReqCondition[] conditions){
		
		List<CIP_admin_access_ctrlData> datas = dataDao.searchData(page, conditions);
		return datas;
	}

	
	public List<Map<String, Object>> exportEntities(CIPPageInfo page,CIPReqCondition[] conditions,
			CIPRuntimeOperator operateInf, boolean xFirst){
//		if(xFirst){
//			CIP_admin_op_logPO log = new CIP_admin_op_logPO();
//			log.setOp_seq_no(System.currentTimeMillis());
//			log.setOp_table_id("cip_admin_access_ctrl");
//			log.setOp_obj_id("");
//			log.setOp_type("E");
//			log.setRemark("批量导出数据");
//			log.setOperator(operateInf.getSubject_id());
//			log.setCreate_time(Timestamp.valueOf(operateInf.getOperate_tm()));
//			opLogDao.add(log);
//		}
//		return dataDao.exportEntities(page, conditions, xFirst);
		return null;
	}
}