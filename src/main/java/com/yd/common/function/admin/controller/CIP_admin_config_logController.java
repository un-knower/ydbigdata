package com.yd.common.function.admin.controller;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yd.common.data.CIPResponseQueryMsg;
import com.yd.common.runtime.CIPErrorCode;

/**
 * <p>控制层实现类</p>
 * <p>Class: CIP_admin_op_logController</p>
 *
 * @since 2015-07-27 02:40:51
 */

@RestController
@RequestMapping(value = "/actions/admin_config_log")
public class CIP_admin_config_logController {
   
	private static final String C_ACTION_GET_LOG = "CIP_admin_config_log_leval";
	
	/**
	* configLogLeval:配置日志的输出等级
	*
	* @author jh
	* @param leval
	* @return
	* @throws Exception 
	* @since JDK 1.7
	*/
	@RequestMapping(value="/configLogLeval")
	public CIPResponseQueryMsg configLogLeval(String leval){
		CIPResponseQueryMsg msg = new CIPResponseQueryMsg();
		Logger logger = Logger.getRootLogger();// 获取日志的级别
		try {
			if ("ALL".equals(leval)) {
				logger.setLevel(Level.ALL);
			}
			if ("DEBUGE".equals(leval)) {
				logger.setLevel(Level.DEBUG);
			}
			if ("INFO".equals(leval)) {
				logger.setLevel(Level.INFO);
			}
			if ("WARN".equals(leval)) {
				logger.setLevel(Level.WARN);
			}
			if ("ERROR".equals(leval)) {
				logger.setLevel(Level.ERROR);
			}
			msg.errorCode = CIPErrorCode.CALL_SUCCESS.code;
			msg.msg = CIPErrorCode.CALL_SUCCESS.name;
		} catch (Exception e) {
			CIPErrorCode error = new CIPErrorCode(10011,"日志级别设定失败");
			msg.errorCode = error.code;
			msg.msg = error.name;
		}		
		return msg;
	}
	
}