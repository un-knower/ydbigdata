package com.yd.common.function.meta.controller;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
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
import com.yd.common.function.meta.dao.CIP_meta_field_logDao;
import com.yd.common.function.meta.dao.CIP_meta_str_logDao;
import com.yd.common.function.meta.data.CIPFileInfo;
import com.yd.common.function.meta.data.CIP_meta_changeData;
import com.yd.common.function.meta.data.CIP_meta_strData;
import com.yd.common.function.meta.data.mapper.CIP_meta_strMapper;
import com.yd.common.function.meta.data.po.CIP_meta_field_logPO;
import com.yd.common.function.meta.data.po.CIP_meta_str_logPO;
import com.yd.common.function.meta.data.vo.CIP_meta_strVO;
import com.yd.common.function.meta.service.CIP_meta_field_logService;
import com.yd.common.function.meta.service.CIP_meta_strService;
import com.yd.common.function.meta.service.CIP_meta_str_logService;
import com.yd.common.runtime.CIPErrorCode;
import com.yd.common.runtime.CIPRuntime;
import com.yd.common.runtime.CIPRuntimeConfigure;
import com.yd.common.utils.CompressUtils;
import com.yd.common.utils.DateUtils;
import com.yd.common.utils.ExcelSheetParser;
import com.yd.common.utils.JSONUtils;

/**
 * <p>控制层实现类</p>
 * <p>Class: CIP_meta_strController</p>
 *
 * @since 2015-10-08 09:03:37
 */
@RestController(value="CIP_meta_strController")
@RequestMapping(value = "/actions/cip_meta_str")
public class CIP_meta_strController {
	
	@Autowired
	private CIP_meta_strService dataService  = null;
	@Autowired
	private CIP_meta_str_logService str_logService = null;
	@Autowired
	private CIP_meta_field_logService field_logService = null;
	@Autowired
	private CIP_meta_str_logDao str_logDao = null;
	@Autowired
	private CIP_meta_field_logDao field_logDao = null;
	
	
	private static final String C_ACTION_ADD_DATA = "cip_meta_str_addData";
	/**
	* 新增系统数据结构表
	*/
	@RequestMapping(value="/addData")
	public CIPResponseMsg addData(@RequestBody CIP_meta_strVO vo) {
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

	private static final String C_ACTION_GET_DATA = "cip_meta_str_getData";
	/**
	* 查看系统数据结构表
	*/
	@RequestMapping(value = "/getData")
	public CIPResponseMsg getData(@RequestBody CIP_meta_strVO vo) {
		CIPResponseMsg msg = new CIPResponseMsg();
		try {
			boolean xAuth = true;
			// CIPAuthLock lock = CIPRuntime.authManager.getAuthLock("authObject");
			// lock.setAuthValue("authAtribute", String.valueOf(vo.getAttribute()));
			// xAuth = CIPRuntime.authManager.checkBehavior(C_ACTION_GET_DATA, lock);
			if(xAuth){
				Object[] keys = vo.getKeys();
				CIP_meta_strVO vo0 = dataService.getData(keys);
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
	
	private static final String C_ACTION_DELETE_DATA = "cip_meta_str_deleteData";
	/**
	* 删除系统数据结构表
	*/
	@RequestMapping(value = "/deleteData")
	public CIPResponseMsg deleteData(@RequestBody CIP_meta_strVO vo) {
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
	
	private static final String C_ACTION_UPDATE_DATA = "cip_meta_str_updateData";
	/**
	* 更新系统数据结构表
	*/
	@RequestMapping(value = "/updateData")
	public CIPResponseMsg updateData(@RequestBody CIP_meta_strVO vo) {
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
	
	
private static final String C_ACTION_SEARCH_DATA = "cip_meta_str_searchData";
	
	/**
	* 查询系统数据结构表
	*/
	@RequestMapping(value = "/searchData")
	public CIPResponseQueryMsg searchData(CIPReqParameter parameter) {
		CIPResponseQueryMsg msg = new CIPResponseQueryMsg();
		try {
			CIPPageInfo page = new CIPPageInfo(parameter.getPage(),parameter.getRows());
			
/*			//用户特定的数据权限约束
			CIPReqCondition[] otherConds = CIPRuntime.authManager.getAuthConditions(C_ACTION_SEARCH_DATA, "authOrg");
			if(otherConds != null){
				length = otherConds.length;
			}*/
			CIPReqCondition[] otherConds = null;
			int length = 0;
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
			
			List<CIP_meta_strData> vos = dataService.searchData(page,conditions);
			
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
	
	//恢复变更
	private static final String C_ACTION_CHANGE_RECOVERY ="cip_meta_change_Recovery";
	@RequestMapping(value = "/changeRecovery")
	public CIPResponseMsg changeRecovery(@RequestBody String selectedRows){
		CIPResponseQueryMsg msg = new CIPResponseQueryMsg();
		try {
			
			dataService.changeRecovery(selectedRows);
			msg.errorCode = CIPErrorCode.CALL_SUCCESS.code;
			msg.msg = CIPErrorCode.CALL_SUCCESS.name;
		
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
	
	
	//推送变更
	private static final String C_ACTION_CHANGE_SUBMIT ="cip_meta_change_Submit";
	@RequestMapping(value = "/changeSubmit")
	public CIPResponseMsg changeSubmit(){
		CIPResponseMsg msg = new CIPResponseMsg();
		try {
			
				dataService.changeSubmit();
				msg.errorCode = CIPErrorCode.CALL_SUCCESS.code;
				msg.msg = CIPErrorCode.CALL_SUCCESS.name;
			
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
	
	//拒绝变更
	private static final String C_ACTION_CHANGE_REFUSE = "cip_meta_change_Refuse";
	@RequestMapping(value = "/changeRefuse")
	public CIPResponseMsg changeRefuse(@RequestBody String selectedRows){
		CIPResponseQueryMsg msg = new CIPResponseQueryMsg();
		String rows = selectedRows;
		
		try {
			
					dataService.changeRefuse(rows);
					msg.errorCode = CIPErrorCode.CALL_SUCCESS.code;
					msg.msg = CIPErrorCode.CALL_SUCCESS.name;
				
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
	
	private static final String C_ACTION_SEARCH_CHANGE = "cip_meta_str_searchChange";
	@RequestMapping(value = "/searchChange")
	public CIPResponseQueryMsg searchChange(CIPReqParameter parameter) {
		CIPResponseQueryMsg msg = new CIPResponseQueryMsg();
		try {
			CIPPageInfo page = new CIPPageInfo(parameter.getPage(),parameter.getRows());
			
			//用户特定的数据权限约束
			/*CIPReqCondition[] otherConds = CIPRuntime.authManager.getAuthConditions(C_ACTION_SEARCH_DATA, "authOrg");
			if(otherConds != null){
				length = otherConds.length;
			}*/
			CIPReqCondition[] otherConds = null;
			int length = 0;
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
			
		
			//查询strlog
			List<CIP_meta_str_logPO> strvo = str_logService.searchAll(conditions);
			//查询fieldslog
			List<CIP_meta_field_logPO> fieldvo = field_logService.searchAll(conditions);
			
			//放在自定义bean里
			List<CIP_meta_changeData> vos = new ArrayList<CIP_meta_changeData>();
			for (CIP_meta_str_logPO str_log:strvo) {
				CIP_meta_changeData changeData = new CIP_meta_changeData();
				changeData.setStr_id(str_log.getStr_id());
				changeData.setField_domain("T");
				changeData.setField_id(str_log.getOp_field());
				changeData.setNew_value(str_log.getNew_value());
				changeData.setOld_value(str_log.getOld_value());
				changeData.setOperate_field(str_log.getOp_field());
				changeData.setOperate_type(str_log.getOp_type());
				changeData.setOperate_time(str_log.getOper_time().toString());
				changeData.setOperator(str_log.getOperator());
				changeData.setOp_flag(str_log.getOp_flag());
				
				vos.add(changeData);
			}
			for (CIP_meta_field_logPO field_log:fieldvo) {
				CIP_meta_changeData Fchange = new CIP_meta_changeData();
				Fchange.setField_domain("C");
				Fchange.setField_id(field_log.getField_id());
				Fchange.setNew_value(field_log.getNew_value());
				Fchange.setOld_value(field_log.getOld_value());
				Fchange.setOperate_field(field_log.getOp_field());
				Fchange.setOperate_time(field_log.getOper_time().toString());
				Fchange.setOperate_type(field_log.getOp_type());
				Fchange.setOperator(field_log.getOperator());
				Fchange.setStr_id(field_log.getStr_id());
				Fchange.setOp_flag(field_log.getOp_flag());
				
				vos.add(Fchange);
			}
			
			
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
	
	private static final String C_ACTION_EXPORT_EXCEL = "cip_meta_str_exportEntities";
	
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
			String as = "导出系统数据结构表_"+DateUtils.getDate(new Date());

			String fileName = new String(as.getBytes("GB2312"), "ISO_8859_1");
			response.setCharacterEncoding("utf-8");  
			response.setContentType("application/vnd.ms-excel");  
			response.setHeader("Content-Disposition", "attachment;fileName="+fileName+".xls");  
			String sheetName = "系统数据结构表";
			CIPPageInfo page = new CIPPageInfo(1,10000);

			//获取订单，每次最多导出1万条记录
			List<Map<String, Object>> entities = dataService.exportEntities(page, conditions, CIPRuntime.getOperateSubject(), true);
			SXSSFWorkbook wb = ExcelSheetParser.createWorkBook(sheetName,
					CIP_meta_strMapper.getExcelTitle(), entities);
			
			int total = page.getTotal();
			int rows = page.getRows();
			if (total > rows) {
				int count = total / rows + 1;
				for (int i = 2; i <= count; i++) {
					page.setPage(i);
					entities = dataService.exportEntities(page, conditions, CIPRuntime.getOperateSubject(), false);
					ExcelSheetParser.appendWorkBook(wb, sheetName, CIP_meta_strMapper.getExcelTitle(), entities);
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
	
//	private static final String C_ACTION_REGENERATE = "cip_meta_str_regenerate";

	@RequestMapping(value = "/regenerate")
	public void regenerate(String str_id, String modules, HttpServletResponse response) {
		CIPResponseMsg msg = new CIPResponseMsg();
		BufferedInputStream br = null;
		ServletOutputStream out = null;
		PrintWriter wr = null;
		File srcFile = null;
		try{
			response.reset();
			response.addHeader("Content-Disposition", "attachment;filename=" + new String(CIPRuntimeConfigure.cip_system_id + ".zip"));
			response.setContentType("application/octet-stream");
			out = response.getOutputStream();
			wr = new PrintWriter(out);
			List<CIPFileInfo> fileInfos = dataService.regenerate(str_id, Arrays.asList(modules.split(",")));
			srcFile = new File(CIPRuntimeConfigure.cip_temp_file_path + File.separator + CIPRuntimeConfigure.cip_system_id);
			FileUtils.deleteQuietly(srcFile);
			srcFile.mkdir();
			BufferedWriter writer;
			for(CIPFileInfo fileInfo :fileInfos){
				File temp = new File(srcFile.getAbsolutePath() + File.separator + fileInfo.filePackage + File.separator + fileInfo.fileName);
				File directory = new File(srcFile.getAbsolutePath() + File.separator + fileInfo.filePackage);
				if (!directory.exists()) {
					directory.mkdirs();
				}
				writer = new BufferedWriter(new OutputStreamWriter(
						new FileOutputStream(temp), "UTF-8"));
				writer.write(fileInfo.fileContent);
				writer.flush();
				writer.close();
				System.out.println(fileInfo.filePackage);
				System.out.println("--------------------------------------------------------------");
				System.out.println(fileInfo.fileContent);
			}
			File destFile = new File(CIPRuntimeConfigure.cip_temp_file_path + File.separator + CIPRuntimeConfigure.cip_system_id + File.separator + CIPRuntimeConfigure.cip_system_id + ".zip");
			CompressUtils.compress(srcFile, destFile);
			br = new BufferedInputStream(new FileInputStream(destFile));
			byte[] b = new byte[1024];
			int len = 0;
			while((len = br.read(b))!=-1){
				out.write(b,0,len);
			}
		} catch (Exception e) {
			response.reset();
			response.setCharacterEncoding("UTF-8");
			try {
				wr = response.getWriter();
				wr.println("<script>alert('生成失败');</script>");
				wr.flush();
			} catch (Exception e1) {
				wr.println("<script>alert('生成失败');</script>");
			}finally{
				wr.close();
				
			}
			
			return;
		}finally{
			try {
				if(br!=null){
					br.close();
				}
			} catch (IOException e) {
				
			}
			try {
				if(out!=null){
					out.close();
				}
			} catch (IOException e) {

			}
			
		}
		
//		return msg;
	}

}