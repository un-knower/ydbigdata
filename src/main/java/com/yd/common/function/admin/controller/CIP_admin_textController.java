package com.yd.common.function.admin.controller;

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
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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
import com.yd.common.function.admin.data.CIP_admin_textData;
import com.yd.common.function.admin.data.mapper.CIP_admin_textMapper;
import com.yd.common.function.admin.data.vo.CIP_admin_textVO;
import com.yd.common.function.admin.service.CIP_admin_textService;
import com.yd.common.runtime.CIPErrorCode;
import com.yd.common.runtime.CIPRuntime;
import com.yd.common.utils.DateUtils;
import com.yd.common.utils.ExcelSheetParser;
import com.yd.common.utils.JSONUtils;

/**
 * <p>控制层实现类</p>
 * <p>Class: CIP_admin_textController</p>
 *
 * @since 2015-08-18 02:49:22
 */
@RestController
@RequestMapping(value = "/actions/admin_text")
public class CIP_admin_textController {
	
	@Autowired
	private CIP_admin_textService dataService  = null;
	
	private static final String C_ACTION_ADD_DATA = "CIP_admin_text_addData";
	/**
	* 新增系统文本管理
	*/
	@RequestMapping(value="/addData")
	public CIPResponseMsg addData(@RequestBody CIP_admin_textVO vo) {		
		CIPResponseMsg msg = new CIPResponseMsg();		
		try {
			dataService.addData(vo,CIPRuntime.getOperateSubject());
			//新增文本的，重新加载更新缓存。
			dataService.save();
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

	private static final String C_ACTION_GET_DATA = "CIP_admin_text_getData";
	/**
	* 查看系统文本管理
	*/
	@RequestMapping(value = "/getData")
	public CIPResponseMsg getData(@RequestBody CIP_admin_textVO vo) {
		CIPResponseMsg msg = new CIPResponseMsg();
		try {
			Object[] keys = vo.getKeys();
			CIP_admin_textVO vo0 = dataService.getData(keys);
			msg.errorCode = CIPErrorCode.CALL_SUCCESS.code;
			msg.msg = CIPErrorCode.CALL_SUCCESS.name;
			msg.data = vo0;
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
	
	private static final String C_ACTION_DELETE_DATA = "CIP_admin_text_deleteData";
	/**
	* 删除系统文本管理
	*/
	@RequestMapping(value = "/deleteData")
	public CIPResponseMsg deleteData(@RequestBody CIP_admin_textVO vo) {
		CIPResponseMsg msg = new CIPResponseMsg();
		try {
			Object[] keys = vo.getKeys();
			dataService.deleteData(keys,CIPRuntime.getOperateSubject());
			//删除文本，重新加载更新缓存。
			dataService.save();
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
	
	private static final String C_ACTION_UPDATE_DATA = "CIP_admin_text_updateData";
	/**
	* 更新系统文本管理
	*/
	@RequestMapping(value = "/updateData")
	public CIPResponseMsg updateData(@RequestBody CIP_admin_textVO vo) {
		CIPResponseMsg msg = new CIPResponseMsg();
		try {
			dataService.updateData(vo, CIPRuntime.getOperateSubject());
			//更新文本，重新加载更新缓存。
			dataService.save();
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
	
	
	private static final String C_ACTION_SEARCH_DATA = "CIP_admin_text_searchData";
	
	/**
	* 查询系统文本管理
	*/
	@RequestMapping(value = "/searchData")
	public CIPResponseQueryMsg searchData(CIPReqParameter parameter) {
		CIPResponseQueryMsg msg = new CIPResponseQueryMsg();
		try {
			CIPPageInfo page = new CIPPageInfo(parameter.getPage(),parameter.getRows());
			
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
					conditions = new CIPReqCondition[i];
					System.arraycopy(tempConditions, 0, conditions, 0, i);
				}
			}
			
			List<CIP_admin_textData> vos = dataService.searchData(page,conditions);
			
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
	

	
	private static final String C_ACTION_EXPORT_EXCEL = "CIP_admin_text_exportEntities";
	
	/** 
	 * 导出Excel
	 */
	@RequestMapping(value="/exportEntities")
	public void exportEntities(CIPReqParameter parameter, HttpServletRequest request, HttpServletResponse response) throws Exception {
		OutputStream os = null;
		try {
			//初始化检索条件
			//CIPPageInfo page = new CIPPageInfo(parameter.getPage(),parameter.getRows());
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
					conditions = new CIPReqCondition[i];
					System.arraycopy(tempConditions, 0, conditions, 0, i);
				}
			}
			
			//导出excel
			String as = "导出系统文本管理_"+DateUtils.getDate(new Date());

			String fileName = new String(as.getBytes("GB2312"), "ISO_8859_1");
			response.setCharacterEncoding("utf-8");  
			response.setContentType("application/vnd.ms-excel");  
			response.setHeader("Content-Disposition", "attachment;fileName="+fileName+".xls");  
			String sheetName = "系统文本管理";
			CIPPageInfo page = new CIPPageInfo(1,10000);

			//获取订单，每次最多导出1万条记录
			List<Map<String, Object>> entities = dataService.exportEntities(page, conditions, CIPRuntime.getOperateSubject(), true);
			SXSSFWorkbook wb = ExcelSheetParser.createWorkBook(sheetName,
					CIP_admin_textMapper.getExcelTitle(), entities);
			
			int total = page.getTotal();
			int rows = page.getRows();
			if (total > rows) {
				int count = total / rows + 1;
				for (int i = 2; i <= count; i++) {
					page.setPage(i);
					entities = dataService.exportEntities(page, conditions, CIPRuntime.getOperateSubject(), false);
					ExcelSheetParser.appendWorkBook(wb, sheetName, CIP_admin_textMapper.getExcelTitle(), entities);
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