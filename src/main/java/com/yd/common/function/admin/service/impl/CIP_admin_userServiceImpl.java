package com.yd.common.function.admin.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.common.auth.CIPUserProfileData;
import com.yd.common.data.CIPPageInfo;
import com.yd.common.data.CIPReqCondition;
import com.yd.common.exception.CIPRuntimeException;
import com.yd.common.exception.CIPServiceException;
import com.yd.common.function.admin.constants.CIPUserConstants;
import com.yd.common.function.admin.dao.CIP_admin_op_logDao;
import com.yd.common.function.admin.dao.CIP_admin_userDao;
import com.yd.common.function.admin.data.CIP_admin_userData;
import com.yd.common.function.admin.data.po.CIP_admin_op_logPO;
import com.yd.common.function.admin.data.po.CIP_admin_userPO;
import com.yd.common.function.admin.data.vo.CIP_admin_userVO;
import com.yd.common.function.admin.service.CIP_admin_userService;
import com.yd.common.function.auth.data.CIPAuthResult;
import com.yd.common.runtime.CIPErrorCode;
import com.yd.common.runtime.CIPRuntime;
import com.yd.common.runtime.CIPRuntimeConfigure;
import com.yd.common.runtime.CIPRuntimeOperator;

/**
 * <p>Service实现类</p>
 * <p>Class: CIP_admin_userService</p>
 *
 * @since 2015-07-27 02:40:52
 */

@Service
public class CIP_admin_userServiceImpl implements CIP_admin_userService {
	
	@Autowired
	private CIP_admin_userDao dataDao = null;
	@Autowired
	private CIP_admin_op_logDao opLogDao =null;
	/** 
	 * 修改数据
	 */
	@Override
	public void updateData(CIP_admin_userVO vo, CIPRuntimeOperator operateInf){
		//TODO
		CIP_admin_userPO po = vo.toPO();
		Object[] keys = po.getKeys();
		CIP_admin_userPO po0 = dataDao.getSingle(keys);
		if( po0 == null ){
			throw new CIPServiceException(CIPErrorCode.ERROR_RECORD_NOT_EXISTS);
		}
		
		po.setUpdate_time(Timestamp.valueOf(operateInf.getOperate_tm()));
		po.setOperator(operateInf.getSubject_id());
		po.setUser_pwd(po0.getUser_pwd());
		po.setUser_his1_pwd(po0.getUser_his1_pwd());
		po.setUser_his2_pwd(po0.getUser_his2_pwd());
		po.setUser_his3_pwd(po0.getUser_his3_pwd());
		
		
		dataDao.update(po);
		
	}
	
	/** 
	 * 添加数据
	 */
	@Override
	public void addData(CIP_admin_userVO vo, CIPRuntimeOperator operateInf){
		CIP_admin_userPO po = vo.toPO();
		Object[] keys = po.getKeys();
		CIP_admin_userPO po0 = dataDao.getSingle(keys);
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
	public CIP_admin_userVO getData(Object[] keys ){
		CIP_admin_userPO  po = dataDao.getSingle(keys);
		if(po == null)
			throw new CIPServiceException(CIPErrorCode.ERROR_RECORD_NOT_EXISTS);
		po.setUser_his1_pwd("");
		po.setUser_his2_pwd("");
		po.setUser_his3_pwd("");
		po.setUser_pwd("");
		return po.toVO();
	}
	
	/** 
	 * 检索数据
	 */		
	@Override
	public List<CIP_admin_userData> searchData(CIPPageInfo page, CIPReqCondition[] conditions){
		
		List<CIP_admin_userData> datas = dataDao.searchData(page, conditions);
		return datas;
	}

	
	public List<Map<String, Object>> exportEntities(CIPPageInfo page,CIPReqCondition[] conditions,
			CIPRuntimeOperator operateInf, boolean xFirst){
		if(xFirst){
			CIP_admin_op_logPO log = new CIP_admin_op_logPO();
			log.setOp_seq_no(System.currentTimeMillis());
			log.setOp_table_id("cip_admin_user");
			log.setOp_obj_id("");
			log.setOp_type("E");
			log.setRemark("批量导出数据");
			log.setOperator(operateInf.getSubject_id());
			log.setCreate_time(Timestamp.valueOf(operateInf.getOperate_tm()));
			opLogDao.add(log);
		}
		return dataDao.exportEntities(page, conditions, xFirst);
	}
	
	
	//修改密碼

	@Override
	public CIPAuthResult resetPassword(String user_id, String oldP, String newP) {
		if(user_id==null || user_id.trim().equals("")){
			return CIPAuthResult.USER_IS_NULL;
		}
		if(oldP==null||newP==null||oldP.trim().equals("")||newP.trim().equals("")||oldP.equals(newP)){
			return CIPAuthResult.PASSWORD_IS_NULL;
		}
		if(CIPRuntimeConfigure.cip_user_xremote){
			//远程修改密码,不支持！！！
			throw new CIPRuntimeException(CIPErrorCode.ERROR_FUNCTION_NOT_SUPPORT);
		}
		else {
			CIP_admin_userPO userData = dataDao.getSingle(user_id);
			if(!userData.getUser_pwd().equals(oldP)){
				return CIPAuthResult.PASSWORD_MATCH;
			}
			else {
				String pwd1 = userData.getUser_his1_pwd();
				String pwd2 = userData.getUser_his2_pwd();
				String pwd3 = userData.getUser_his3_pwd();
				if(newP.equals(pwd1)||newP.equals(pwd2)
						||newP.equals(pwd3)){
					return CIPAuthResult.PASSWORD_IS_USED;
				}
				else {
					pwd3 = pwd2;
					pwd2 = pwd1;
					pwd1 = oldP;
				}
				Map<String, Object> params = new HashMap<String, Object>(7);
				params.put("user_pwd", newP);
				params.put("pwd_init_flag", CIPUserConstants.C_USER_PWD_INIT_NO);
				params.put("user_his1_pwd", pwd1);
				params.put("user_his2_pwd", pwd2);
				params.put("user_his3_pwd", pwd3);
				params.put("pwd_set_time", new Date());
				params.put("pwd_reset_flag", CIPUserConstants.C_USER_PWD_RESET_YES);

				dataDao.updateKV(params , user_id);
				resetUserProfile(user_id);
			}
		}
	
		return CIPAuthResult.SUCCESS;
	}

	private void resetUserProfile(String user_id){
		CIP_admin_userPO userData = dataDao.getSingle(user_id);
		CIPUserProfileData userProfileData = userData.toProfileData();
		CIPRuntime.authManager.setUserProfile(userProfileData);
	}
}