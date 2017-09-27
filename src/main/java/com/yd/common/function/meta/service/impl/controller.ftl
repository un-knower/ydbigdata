package ${basePackage}.${structure.str_module}.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yd.common.data.CIPPageInfo;
import com.yd.common.data.CIPReqCondition;
import com.yd.common.data.CIPReqParameter;
import com.yd.common.data.CIPResponseMsg;
import com.yd.common.data.CIPResponseQueryMsg;
import com.yd.common.runtime.CIPRuntime;
import com.yd.common.runtime.CIPErrorCode;
import com.yd.common.utils.DateUtils;
import com.yd.common.utils.JSONUtils;
import com.yd.common.utils.ExcelSheetParser;
import com.yd.common.exception.CIPRuntimeException;
import com.yd.common.exception.CIPServiceException;
import com.yd.common.exception.CIPDaoException;
import ${basePackage}.${structure.str_module}.service.${className}Service;
import ${basePackage}.${structure.str_module}.data.vo.${className}VO;
import ${basePackage}.${structure.str_module}.data.key.${className}Key;
import ${basePackage}.${structure.str_module}.data.mapper.${className}Mapper;
import ${basePackage}.${structure.str_module}.data.${className}Data;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>控制层实现类</p>
 * <p>Class: ${className}Controller</p>
 *
 * @since ${.now}
 */
@RestController(value="${className}Controller")
@RequestMapping(value = "/actions/${structure.func_set_id}")
public class ${className}Controller {
	
	@Autowired
	private ${className}Service dataService  = null;
	
	private static final String C_ACTION_ADD_DATA = "${actionName}_addData";
	/**
	* 新增${structure.str_name}
	*/
	@RequestMapping(value="/addData")
	public CIPResponseMsg addData(@RequestBody ${className}VO vo) {
		CIPResponseMsg msg = new CIPResponseMsg();
		try {
			boolean xAuth = true;
			// CIPAuthLock lock = CIPRuntime.authManager.getAuthLock("authObject");
			// lock.setAuthValue("authAtribute", String.valueOf(vo.getAttribute()));
			// xAuth = CIPRuntime.authManager.checkBehavior(C_ACTION_ADD_DATA, lock);
			if(xAuth){
				dataService.addData(vo,CIPRuntime.getOperateSubject());
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

	private static final String C_ACTION_GET_DATA = "${actionName}_getData";
	/**
	* 查看${structure.str_name}
	*/
	@RequestMapping(value = "/getData")
	public CIPResponseMsg getData(@RequestBody ${className}VO vo) {
		CIPResponseMsg msg = new CIPResponseMsg();
		try {
			boolean xAuth = true;
			// CIPAuthLock lock = CIPRuntime.authManager.getAuthLock("authObject");
			// lock.setAuthValue("authAtribute", String.valueOf(vo.getAttribute()));
			// xAuth = CIPRuntime.authManager.checkBehavior(C_ACTION_GET_DATA, lock);
			if(xAuth){
				Object[] keys = vo.getKeys();
				${className}VO vo0 = dataService.getData(keys);
				msg.errorCode = CIPErrorCode.CALL_SUCCESS.code;
				msg.msg = CIPErrorCode.CALL_SUCCESS.name;
				msg.data = vo0;
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
	
	private static final String C_ACTION_DELETE_DATA = "${actionName}_deleteData";
	/**
	* 删除${structure.str_name}
	*/
	@RequestMapping(value = "/deleteData")
	public CIPResponseMsg deleteData(@RequestBody ${className}VO vo) {
		CIPResponseMsg msg = new CIPResponseMsg();
		try {
			boolean xAuth = true;
			// CIPAuthLock lock = CIPRuntime.authManager.getAuthLock("authObject");
			// lock.setAuthValue("authAtribute", String.valueOf(vo.getAttribute()));
			// xAuth = CIPRuntime.authManager.checkBehavior(C_ACTION_DELETE_DATA, lock);
			if(xAuth){
				Object[] keys = vo.getKeys();
				dataService.deleteData(keys,CIPRuntime.getOperateSubject());
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
	
	private static final String C_ACTION_UPDATE_DATA = "${actionName}_updateData";
	/**
	* 更新${structure.str_name}
	*/
	@RequestMapping(value = "/updateData")
	public CIPResponseMsg updateData(@RequestBody ${className}VO vo) {
		CIPResponseMsg msg = new CIPResponseMsg();
		try {
			boolean xAuth = true;
			// CIPAuthLock lock = CIPRuntime.authManager.getAuthLock("authObject");
			// lock.setAuthValue("authAtribute", String.valueOf(vo.getAttribute()));
			// xAuth = CIPRuntime.authManager.checkBehavior(C_ACTION_UPDATE_DATA, lock);
			if(xAuth){
				dataService.updateData(vo, CIPRuntime.getOperateSubject());
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
	
	
	private static final String C_ACTION_SEARCH_DATA = "${actionName}_searchData";
	
	/**
	* 查询${structure.str_name}
	*/
	@RequestMapping(value = "/searchData")
	public CIPResponseQueryMsg searchData(CIPReqParameter parameter) {
		CIPResponseQueryMsg msg = new CIPResponseQueryMsg();
		try {
			CIPPageInfo page = new CIPPageInfo(parameter.getPage(),parameter.getRows());
			
			//用户特定的数据权限约束
			CIPReqCondition[] otherConds = CIPRuntime.authManager.getAuthConditions(C_ACTION_SEARCH_DATA, "authOrg");
			int length = 0;
			if(otherConds != null){
				length = otherConds.length;
			}
			
			String conditonStr = parameter.getSearch_condition();
			CIPReqCondition[] conditions = null;
			if(conditonStr!=null){
				Map reqCondtions = JSONUtils.convertJson2Object(conditonStr, HashMap.class);
				//CIPConfigureData0 data = JSONUtils.convertJson2Object(msg.data.toString(), CIPConfigureData0.class);
				Set<String> keys = reqCondtions.keySet();
				CIPReqCondition req = null;
				CIPReqCondition[] tempConditions = new CIPReqCondition[keys.size()];
				int i = 0;
				String value;
				for( String key : keys ){
					value = (String) reqCondtions.get(key);
					if(value==null || value.trim().equals(""))
						continue;
					req = new CIPReqCondition();
					req.setFieldName(key);
					req.setLowValue(value);
					tempConditions[i++] = req;
				}
				if( i > 0 ){
					conditions = new CIPReqCondition[i+length];
					System.arraycopy(tempConditions, 0, conditions, 0, i);
					if(length>0)
						System.arraycopy(otherConds, 0, conditions, i, length);
				}
				else {
					conditions = otherConds;
				}
			}
			else {
				conditions = otherConds;
			}
			
			List<${className}Data> vos = dataService.searchData(page,conditions);
			
			msg.errorCode = CIPErrorCode.CALL_SUCCESS.code;
			msg.msg = CIPErrorCode.CALL_SUCCESS.name;
			msg.rows = vos;
			msg.total = page.getTotal();
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
	

	<#list table.baseColumns as column>
<#if column.javaProperty == "ctrl_status" >
		
	private static final String C_ACTION_LOCK_DATA = "${actionName}_lock";
	
	/** 
	 * 锁定${structure.str_name}
	 */
	@RequestMapping(value = "/lock")
	public CIPResponseMsg lock(@RequestBody ${className}Key key) {
		CIPResponseMsg msg = new CIPResponseMsg();
		try {
			boolean xAuth = true;
			// CIPAuthLock lock = CIPRuntime.authManager.getAuthLock("authObject");
			// lock.setAuthValue("authAtribute", String.valueOf(vo.getAttribute()));
			// xAuth = CIPRuntime.authManager.checkBehavior(C_ACTION_LOCK_DATA, lock);
			if(xAuth){
				dataService.lock(key, CIPRuntime.getOperateSubject());
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
	
	private static final String C_ACTION_UNLOCK_DATA = "${actionName}_unlock";
	
	/** 
	 * 解锁${structure.str_name}
	 */
	@RequestMapping(value = "/unlock")
	public CIPResponseMsg unlock(@RequestBody ${className}Key key) {
		CIPResponseMsg msg = new CIPResponseMsg();
		try {
			boolean xAuth = true;
			// CIPAuthLock lock = CIPRuntime.authManager.getAuthLock("authObject");
			// lock.setAuthValue("authAtribute", String.valueOf(vo.getAttribute()));
			// xAuth = CIPRuntime.authManager.checkBehavior(C_ACTION_UNLOCK_DATA, lock);
			if(xAuth){
				dataService.unlock(key, CIPRuntime.getOperateSubject());
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
<#elseif column.javaProperty == "audit_flag" >
		
	private static final String C_ACTION_AUDIT_APPROVE = "${actionName}_approve";
	
	/** 
	 * 审批${structure.str_name}
	 */
	@RequestMapping(value = "/approve")
	public CIPResponseMsg approve(@RequestBody ${className}Key key) {
		CIPResponseMsg msg = new CIPResponseMsg();
		try {
			boolean xAuth = true;
			// CIPAuthLock lock = CIPRuntime.authManager.getAuthLock("authObject");
			// lock.setAuthValue("authAtribute", String.valueOf(vo.getAttribute()));
			// xAuth = CIPRuntime.authManager.checkBehavior(C_ACTION_AUDIT_APPROVE, lock);
			if(xAuth){
				dataService.approve(key, CIPRuntime.getOperateSubject());
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
	
	private static final String C_ACTION_AUDIT_REJECT = "${actionName}_reject";
	
	/** 
	 * 回退${structure.str_name}
	 */
	@RequestMapping(value = "/reject")
	public CIPResponseMsg reject(@RequestBody ${className}Key key) {
		CIPResponseMsg msg = new CIPResponseMsg();
		try {
			boolean xAuth = true;
			// CIPAuthLock lock = CIPRuntime.authManager.getAuthLock("authObject");
			// lock.setAuthValue("authAtribute", String.valueOf(vo.getAttribute()));
			// xAuth = CIPRuntime.authManager.checkBehavior(C_ACTION_AUDIT_REJECT, lock);
			if(xAuth){
				dataService.reject(key, CIPRuntime.getOperateSubject());
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
</#if>
	</#list>
	
	private static final String C_ACTION_EXPORT_EXCEL = "${actionName}_exportEntities";
	
	/** 
	 * 导出Excel
	 */
	@RequestMapping(value="/exportEntities")
	public void exportEntities(CIPReqParameter parameter, HttpServletRequest request, HttpServletResponse response) throws Exception {
		OutputStream os = null;
		try {
			//用户特定的数据权限约束
			CIPReqCondition[] otherConds = CIPRuntime.authManager.getAuthConditions(C_ACTION_SEARCH_DATA, "authOrg");
			int length = 0;
			if(otherConds != null){
				length = otherConds.length;
			}
			
			//初始化检索条件
			String conditonStr = parameter.getSearch_condition();
			CIPReqCondition[] conditions = null;
			if(conditonStr!=null){
				Map reqCondtions = JSONUtils.convertJson2Object(conditonStr, HashMap.class);
				//CIPConfigureData0 data = JSONUtils.convertJson2Object(msg.data.toString(), CIPConfigureData0.class);
				Set<String> keys = reqCondtions.keySet();
				CIPReqCondition req = null;
				CIPReqCondition[] tempConditions = new CIPReqCondition[keys.size()];
				int i = 0;
				String value;
				for( String key : keys ){
					value = (String) reqCondtions.get(key);
					if(value==null || value.trim().equals(""))
						continue;
					req = new CIPReqCondition();
					req.setFieldName(key);
					req.setLowValue(value);
					tempConditions[i++] = req;
				}
				if( i > 0 ){
					conditions = new CIPReqCondition[i+length];
					System.arraycopy(tempConditions, 0, conditions, 0, i);
					if(length>0)
						System.arraycopy(otherConds, 0, conditions, i, length);
				}
				else {
					conditions = otherConds;
				}
			}
			else {
				conditions = otherConds;
			}
			
			//导出excel
			String as = "导出${structure.str_name}_"+DateUtils.getDate(new Date());

			String fileName = new String(as.getBytes("GB2312"), "ISO_8859_1");
			response.setCharacterEncoding("utf-8");  
			response.setContentType("application/vnd.ms-excel");  
			response.setHeader("Content-Disposition", "attachment;fileName="+fileName+".xls");  
			String sheetName = "${structure.str_name}";
			CIPPageInfo page = new CIPPageInfo(1,10000);

			//获取订单，每次最多导出1万条记录
			List<Map<String, Object>> entities = dataService.exportEntities(page, conditions, CIPRuntime.getOperateSubject(), true);
			SXSSFWorkbook wb = ExcelSheetParser.createWorkBook(sheetName,
					${className}Mapper.getExcelTitle(), entities);
			
			int total = page.getTotal();
			int rows = page.getRows();
			if (total > rows) {
				int count = total / rows + 1;
				for (int i = 2; i <= count; i++) {
					page.setPage(i);
					entities = dataService.exportEntities(page, conditions, CIPRuntime.getOperateSubject(), false);
					ExcelSheetParser.appendWorkBook(wb, sheetName, ${className}Mapper.getExcelTitle(), entities);
				}
			}
			os = response.getOutputStream();
			wb.write(os);
		}
		catch (Exception e) {
			throw e;
		}finally {
			try {
				if(os!=null){
					os.flush();
					os.close();
				}
			} catch (IOException e) {
				
			}				
		}
	}
}