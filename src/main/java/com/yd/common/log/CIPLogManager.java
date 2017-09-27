package com.yd.common.log;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.yd.common.function.admin.data.vo.CIP_admin_log_accessVO;
import com.yd.common.function.admin.data.vo.CIP_admin_log_jobVO;
import com.yd.common.function.admin.data.vo.CIP_admin_log_mdmVO;
import com.yd.common.function.admin.data.vo.CIP_admin_op_logVO;
import com.yd.common.function.admin.service.CIP_admin_log_accessService;
import com.yd.common.function.admin.service.CIP_admin_log_jobService;
import com.yd.common.function.admin.service.CIP_admin_log_mdmService;
import com.yd.common.function.admin.service.CIP_admin_op_logService;
import com.yd.common.utils.DateUtils;
import com.yd.common.utils.SerialNoUtils;

public class CIPLogManager {
	
	private static Logger log = Logger.getLogger(CIPLogManager.class);
	
	private static CIPLogManager logManager;
	private CIP_admin_log_jobService jobLogService;
	private CIP_admin_log_mdmService mdmLogService;
	private CIP_admin_op_logService opLogService;
	private CIP_admin_log_accessService accessLogService;
	
	public static CIPLogManager getLogManager(){
		if(logManager == null){
			logManager = new CIPLogManager();
			WebApplicationContext wc = ContextLoader.getCurrentWebApplicationContext();  
			logManager.jobLogService = (CIP_admin_log_jobService) wc.getBean("cipJobLogService");
			logManager.mdmLogService = (CIP_admin_log_mdmService) wc.getBean("cipMdmLogService");
			logManager.opLogService = (CIP_admin_op_logService) wc.getBean("cipOpLogService");
			logManager.accessLogService = (CIP_admin_log_accessService) wc.getBean("cipAccessLogService");
		}
		
		return logManager;
	}
	
	public static void info(String message){
		log.info(message);
	}
	
	public static void info(String message, Throwable t){
		log.info(message, t);
	}
	
	public static void warn(String message){
		log.warn(message);
	}
	
	public static void warn(String message, Throwable t){
		log.warn(message, t);
	}
	
	public static void error(String message){
		log.error(message);
	}
	
	public static void error(String message, Throwable t){
		log.error(message, t);
	}
	
	// 系统任务执行日志
	public void logJob(String taskId, int stepId, String message,
			int errorCode) {
		CIP_admin_log_jobVO log = new CIP_admin_log_jobVO();
		
		log.setLog_id(SerialNoUtils.getTimeSerialNo());
		log.setError_code(errorCode);
		log.setStep_id(stepId);
		log.setStep_msg(message);
		log.setTask_id(taskId);
		log.setOccur_time(DateUtils.getDateTime(new Date()));
		
		jobLogService.logJob(log);
	}


	// 系统数据修改日志（基础数据）==》审计及数据恢复
	public void logMasterDataChange(String tableId, String recordId,
			String oldValue, String newValue, String userId, String operateType) {
		CIP_admin_log_mdmVO log = new CIP_admin_log_mdmVO();
		log.setNew_values(newValue);
		log.setOld_values(oldValue);
		log.setLog_id(SerialNoUtils.getTimeSerialNo());
		log.setOccur_time(DateUtils.getDateTime(new Date()));
		log.setOperate_type(operateType);
		log.setRecord_id(recordId);
		log.setTable_id(tableId);
		log.setUser_id(userId);
		
		mdmLogService.logMasterDataChange(log);
	}
	
	// 系统使用审计日志==》系统功能访问日志 
	public void logSystemAccess(String resourceId, String visitorId, String vistorType, String ip, String remark){
		CIP_admin_log_accessVO log = new CIP_admin_log_accessVO();
		log.setLog_id(SerialNoUtils.getTimeSerialNo());
		log.setRemark(remark);
		log.setResource_id(resourceId);
		log.setVisitor_id(visitorId);
		log.setVisitor_type(vistorType);
		log.setOccur_time(DateUtils.getDateTime(new Date()));
		log.setIp(ip);
		
		accessLogService.logSystemAccess(log);
	}
	
	// 系统功能点执行异常日志 ==》技术运维支持
	public void logError(String tableId, String objId, String opType, String errorMsg, String userId){
		CIP_admin_op_logVO log = new CIP_admin_op_logVO();
		log.setOp_seq_no(System.currentTimeMillis());
		log.setOp_table_id(tableId);
		log.setOp_obj_id(objId);
		log.setOp_type(opType);
		log.setRemark(errorMsg);
		log.setOperator(userId);
		
		opLogService.logOp(log);
	}
	
}
