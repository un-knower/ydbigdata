package com.yd.common.utils;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yd.common.data.CIPResponseMsg;
import com.yd.common.exception.CIPDaoException;
import com.yd.common.exception.CIPRuntimeException;
import com.yd.common.exception.CIPServiceException;
import com.yd.common.runtime.CIPErrorCode;

/**
* ClassName: GlobalExceptionHandler
* Function: TODO 异常拦截处理.
* Reason: TODO ADD REASON(可选).
* date: 2016-11-22 下午4:20:25
*
* @author jh
* @version 
* @since JDK 1.7
*/
@ControllerAdvice
public class GlobalExceptionHandler {
	Logger log = Logger.getLogger(GlobalExceptionHandler.class);
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public CIPResponseMsg HandlerException(Exception e) {
		CIPResponseMsg msg = new CIPResponseMsg();
		if (e instanceof CIPServiceException) {
			log.error(e.getMessage(), e);
			CIPErrorCode error = ((CIPServiceException) e).getErrorCode();
			msg.errorCode = error.code;
			msg.msg = error.name;
		} else if (e instanceof CIPDaoException) {
			log.error(e.getMessage(), e);
			CIPErrorCode error = ((CIPDaoException) e).getErrorCode();
			msg.errorCode = error.code;
			msg.msg = error.name;
		} else if (e instanceof CIPRuntimeException) {
			log.error(e.getMessage(), e);
			CIPErrorCode error = ((CIPRuntimeException) e).getErrorCode();
			msg.errorCode = error.code;
			msg.msg = error.name;
		}else{
			log.error(e.getMessage(), e);
			CIPErrorCode error = new CIPErrorCode(1, "系统错误");
			msg.errorCode = error.code;
			msg.msg = error.name;
		}	
		return msg;
	}
}
