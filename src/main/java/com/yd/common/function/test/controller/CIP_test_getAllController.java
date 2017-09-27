package com.yd.common.function.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yd.common.data.CIPResponseMsg;
import com.yd.common.exception.CIPDaoException;
import com.yd.common.exception.CIPRuntimeException;
import com.yd.common.exception.CIPServiceException;
import com.yd.common.function.test.service.CIP_test_getAllService;
import com.yd.common.runtime.CIPErrorCode;

/**
 * <p>控制层实现类</p>
 * <p>Class: CIP_test_getAllController</p>
 *
 * @since 2015-11-19 9:52:52
 */

@RestController(value="CIP_test_getAllController")
@RequestMapping(value = "/outer")
public class CIP_test_getAllController {

	@Autowired
	private CIP_test_getAllService getAll = null;
	
	private static final String C_TEST_GET_OUTER = "cip_test_getOuter";
	/**
	* 新增系统测试用例
	*/
	@RequestMapping(value="/getTestContext")
	public CIPResponseMsg getTestContext( String system_id) {
		CIPResponseMsg msg = new CIPResponseMsg();
		try {
			boolean xAuth = true;
			// CIPAuthLock lock = CIPRuntime.authManager.getAuthLock("authObject");
			// lock.setAuthValue("authAtribute", String.valueOf(vo.getAttribute()));
			// xAuth = CIPRuntime.authManager.checkBehavior(C_ACTION_ADD_DATA, lock);
			if(xAuth){
				msg.data = getAll.getTestContext(system_id);
				msg.errorCode = CIPErrorCode.CALL_SUCCESS.code;
				msg.msg = CIPErrorCode.CALL_SUCCESS.name;
			}
			else{
				msg.errorCode = CIPErrorCode.ERROR_INVALID_AUTHORIZATION.code;
				msg.msg = CIPErrorCode.ERROR_INVALID_AUTHORIZATION.name;
			}
		} catch (CIPServiceException e) {
			CIPErrorCode error = e.getErrorCode();
			msg.errorCode = error.code;
			msg.msg = error.name;
		} catch (CIPDaoException e) {
			CIPErrorCode error = e.getErrorCode();
			msg.errorCode = error.code;
			msg.msg = error.name;
		} catch (CIPRuntimeException e) {
			CIPErrorCode error = e.getErrorCode();
			msg.errorCode = error.code;
			msg.msg = error.name;
		}

		return msg;
	}
	/**
	 * 接收测试结果
	 */
	@RequestMapping(value="/saveTestResult")
	public CIPResponseMsg saveTestResult( @RequestBody String result) {
		CIPResponseMsg msg = new CIPResponseMsg();
		
		try {
			
			boolean xAuth = true;
			// CIPAuthLock lock = CIPRuntime.authManager.getAuthLock("authObject");
			// lock.setAuthValue("authAtribute", String.valueOf(vo.getAttribute()));
			// xAuth = CIPRuntime.authManager.checkBehavior(C_ACTION_ADD_DATA, lock);
			if(xAuth){
				msg.data = getAll.saveTestResult(result);
				msg.errorCode = CIPErrorCode.CALL_SUCCESS.code;
				msg.msg = CIPErrorCode.CALL_SUCCESS.name;
			}
			else{
				msg.errorCode = CIPErrorCode.ERROR_INVALID_AUTHORIZATION.code;
				msg.msg = CIPErrorCode.ERROR_INVALID_AUTHORIZATION.name;
			}
		} catch (CIPServiceException e) {
			CIPErrorCode error = e.getErrorCode();
			msg.errorCode = error.code;
			msg.msg = error.name;
		} catch (CIPDaoException e) {
			CIPErrorCode error = e.getErrorCode();
			msg.errorCode = error.code;
			msg.msg = error.name;
		} catch (CIPRuntimeException e) {
			CIPErrorCode error = e.getErrorCode();
			msg.errorCode = error.code;
			msg.msg = error.name;
		}

		return msg;
	}
}
