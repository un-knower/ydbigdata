package com.yd.common.function.test.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yd.common.data.CIPPageInfo;
import com.yd.common.data.CIPReqCondition;
import com.yd.common.data.CIPReqParameter;
import com.yd.common.data.CIPResponseMsg;
import com.yd.common.data.CIPResponseQueryMsg;
import com.yd.common.exception.CIPDaoException;
import com.yd.common.exception.CIPRuntimeException;
import com.yd.common.exception.CIPServiceException;
import com.yd.common.function.test.data.CIP_test_scenarioData;
import com.yd.common.function.test.data.mapper.CIP_test_scenarioMapper;
import com.yd.common.function.test.data.vo.CIP_test_scenarioVO;
import com.yd.common.function.test.service.CIP_test_scenarioService;
import com.yd.common.runtime.CIPErrorCode;
import com.yd.common.runtime.CIPRuntime;
import com.yd.common.utils.DateUtils;
import com.yd.common.utils.ExcelSheetParser;
import com.yd.common.utils.JSONUtils;

/**
 * <p>控制层实现类</p>
 * <p>Class: CIP_test_scenarioController</p>
 *
 * @since 2015-10-10 02:52:52
 */
@RestController(value="CIP_test_scenarioController")
@RequestMapping(value = "/actions/test_scenario")
public class CIP_test_scenarioController {
	
	@Autowired
	private CIP_test_scenarioService dataService  = null;
	
	private static final String C_ACTION_ADD_DATA = "cip_test_scenario_addData";
	/**
	* 新增系统测试场景
	*/
	@RequestMapping(value="/addData")
	public CIPResponseMsg addData(@RequestBody CIP_test_scenarioVO vo) {
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

	private static final String C_ACTION_GET_DATA = "cip_test_scenario_getData";
	/**
	* 查看系统测试场景
	*/
	@RequestMapping(value = "/getData")
	public CIPResponseMsg getData(@RequestBody CIP_test_scenarioVO vo) {
		CIPResponseMsg msg = new CIPResponseMsg();
		try {
			boolean xAuth = true;
			// CIPAuthLock lock = CIPRuntime.authManager.getAuthLock("authObject");
			// lock.setAuthValue("authAtribute", String.valueOf(vo.getAttribute()));
			// xAuth = CIPRuntime.authManager.checkBehavior(C_ACTION_GET_DATA, lock);
			if(xAuth){
				Object[] keys = vo.getKeys();
				CIP_test_scenarioVO vo0 = dataService.getData(keys);
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
	
	private static final String C_ACTION_DELETE_DATA = "cip_test_scenario_deleteData";
	/**
	* 删除系统测试场景
	*/
	@RequestMapping(value = "/deleteData")
	public CIPResponseMsg deleteData(@RequestBody CIP_test_scenarioVO vo) {
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
	
	private static final String C_ACTION_UPDATE_DATA = "cip_test_scenario_updateData";
	/**
	* 更新系统测试场景
	*/
	@RequestMapping(value = "/updateData")
	public CIPResponseMsg updateData(@RequestBody CIP_test_scenarioVO vo) {
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
	
	
	private static final String C_ACTION_SEARCH_DATA = "cip_test_scenario_searchData";
	
	/**
	* 查询系统测试场景
	*/
	@RequestMapping(value = "/searchData")
	public CIPResponseQueryMsg searchData(CIPReqParameter parameter) {
		CIPResponseQueryMsg msg = new CIPResponseQueryMsg();
		try {
			CIPPageInfo page = new CIPPageInfo(parameter.getPage(),parameter.getRows());
			
			//用户特定的数据权限约束
//			CIPReqCondition[] otherConds = CIPRuntime.authManager.getAuthConditions(C_ACTION_SEARCH_DATA, "authOrg");
			int length = 0;
//			if(otherConds != null){
//				length = otherConds.length;
//			}
			
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
//					if(length>0)
//						System.arraycopy(otherConds, 0, conditions, i, length);
				}
				else {
//					conditions = otherConds;
				}
			}
			else {
//				conditions = otherConds;
			}
			
			List<CIP_test_scenarioData> vos = dataService.searchData(page,conditions);
			
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
	

	
	private static final String C_ACTION_EXPORT_EXCEL = "cip_test_scenario_exportEntities";
	
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
			String as = "导出系统测试场景_"+DateUtils.getDate(new Date());

			String fileName = new String(as.getBytes("GB2312"), "ISO_8859_1");
			response.setCharacterEncoding("utf-8");  
			response.setContentType("application/vnd.ms-excel");  
			response.setHeader("Content-Disposition", "attachment;fileName="+fileName+".xls");  
			String sheetName = "系统测试场景";
			CIPPageInfo page = new CIPPageInfo(1,10000);

			//获取订单，每次最多导出1万条记录
			List<Map<String, Object>> entities = dataService.exportEntities(page, conditions, CIPRuntime.getOperateSubject(), true);
			SXSSFWorkbook wb = ExcelSheetParser.createWorkBook(sheetName,
					CIP_test_scenarioMapper.getExcelTitle(), entities);
			
			int total = page.getTotal();
			int rows = page.getRows();
			if (total > rows) {
				int count = total / rows + 1;
				for (int i = 2; i <= count; i++) {
					page.setPage(i);
					entities = dataService.exportEntities(page, conditions, CIPRuntime.getOperateSubject(), false);
					ExcelSheetParser.appendWorkBook(wb, sheetName, CIP_test_scenarioMapper.getExcelTitle(), entities);
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