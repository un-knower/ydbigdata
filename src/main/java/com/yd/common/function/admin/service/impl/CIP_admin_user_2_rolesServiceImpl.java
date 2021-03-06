package com.yd.common.function.admin.service.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.common.data.CIPPageInfo;
import com.yd.common.data.CIPReqCondition;
import com.yd.common.exception.CIPServiceException;
import com.yd.common.function.admin.dao.CIP_admin_op_logDao;
import com.yd.common.function.admin.dao.CIP_admin_user_2_rolesDao;
import com.yd.common.function.admin.data.CIP_admin_rolesData;
import com.yd.common.function.admin.data.CIP_admin_user_2_rolesData;
import com.yd.common.function.admin.data.po.CIP_admin_op_logPO;
import com.yd.common.function.admin.data.po.CIP_admin_user_2_rolesPO;
import com.yd.common.function.admin.data.vo.CIP_admin_user_2_rolesVO;
import com.yd.common.function.admin.service.CIP_admin_user_2_rolesService;
import com.yd.common.runtime.CIPErrorCode;
import com.yd.common.runtime.CIPRuntimeOperator;

/**
 * <p>Service实现类</p>
 * <p>Class: CIP_admin_user_2_rolesService</p>
 *
 * @since 2015-07-27 02:40:52
 */

@Service
public class CIP_admin_user_2_rolesServiceImpl implements CIP_admin_user_2_rolesService {
	
	@Autowired
	private CIP_admin_user_2_rolesDao dataDao = null;
	@Autowired
	private CIP_admin_op_logDao opLogDao =null;
	
	/** 
	 * 修改数据
	 */
	@Override
	public void updateData(CIP_admin_user_2_rolesVO vo, CIPRuntimeOperator operateInf){
		//TODO
		CIP_admin_user_2_rolesPO po = vo.toPO();
		Object[] keys = po.getKeys();
		CIP_admin_user_2_rolesPO po0 = dataDao.getSingle(keys);
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
	public void addData(CIP_admin_user_2_rolesVO vo, CIPRuntimeOperator operateInf){
		CIP_admin_user_2_rolesPO po = vo.toPO();
		Object[] keys = po.getKeys();
		CIP_admin_user_2_rolesPO po0 = dataDao.getSingle(keys);
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
	public CIP_admin_user_2_rolesVO getData(Object[] keys ){
		CIP_admin_user_2_rolesPO  po = dataDao.getSingle(keys);
		if(po == null)
			throw new CIPServiceException(CIPErrorCode.ERROR_RECORD_NOT_EXISTS);
			
		return po.toVO();
	}
	
	/** 
	 * 检索数据
	 */		
	@Override
	public List<CIP_admin_user_2_rolesData> searchData(CIPPageInfo page, CIPReqCondition[] conditions){
		
		List<CIP_admin_user_2_rolesData> datas = dataDao.searchData(page, conditions);
		return datas;
	}
	
	/** 
	 * 检索角色
	 */		
	@Override
	public List<CIP_admin_rolesData> searchroleData(CIPPageInfo page, CIPReqCondition[] conditions){
		
		List<CIP_admin_rolesData> datas = dataDao.searchroleData(page, conditions);
		return datas;
	}

	
	public List<Map<String, Object>> exportEntities(CIPPageInfo page,CIPReqCondition[] conditions,
			CIPRuntimeOperator operateInf, boolean xFirst){
		if(xFirst){
			CIP_admin_op_logPO log = new CIP_admin_op_logPO();
			log.setOp_seq_no(System.currentTimeMillis());
			log.setOp_table_id("cip_admin_user_2_roles");
			log.setOp_obj_id("");
			log.setOp_type("E");
			log.setRemark("批量导出数据");
			log.setOperator(operateInf.getSubject_id());
			log.setCreate_time(Timestamp.valueOf(operateInf.getOperate_tm()));
			opLogDao.add(log);
		}
		return dataDao.exportEntities(page, conditions, xFirst);
	}

	@Override
	public boolean checkrole(String userId) {
		boolean result=false;
		List<String> list=dataDao.getRoles(userId);
		for (int i = 0; i < list.size(); i++) {
			if (list!=null||!(list.get(i).equals(""))) {
				result=true;
			}
		}
		
		return result;
	}
	@Override
	public boolean checkuser(String roleId) {
		boolean result=false;
		List<String> list=dataDao.getUsers(roleId);
		for (int i = 0; i < list.size(); i++) {
			if (list!=null||!(list.get(i).equals(""))) {
				result=true;
			}
		}
		
		return result;
	}
	//单一条件删除用户角色关联
	@Override
	public void deletebyuser(String userId) {
		dataDao.deletebyuser(userId);
	}

	@Override
	public void deletebyrole(String roleId) {
		dataDao.deletebyrole(roleId);
	}
}