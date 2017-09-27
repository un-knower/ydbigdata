package com.yd.ydbi.matrix.controller;


import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yd.common.data.CIPPageInfo;
import com.yd.common.exception.CIPDaoException;
import com.yd.common.exception.CIPRuntimeException;
import com.yd.common.exception.CIPServiceException;
import com.yd.common.runtime.CIPErrorCode;
import com.yd.common.session.CIPHttpSession;
import com.yd.common.session.CIPSessionManager;
import com.yd.common.utils.DateUtils;
import com.yd.ydbi.common.response.CustomReqParameter;
import com.yd.ydbi.common.response.CustomResponseMsg;
import com.yd.ydbi.common.response.ToolResponseQueryMsg;
import com.yd.ydbi.matrix.model.Matrix_cross_portId_rt;
import com.yd.ydbi.matrix.service.impl.Matrix_cross_porRtServiceImpl;
import com.yd.ydbi.utils.excel.ExcelUtil;

@RestController
@RequestMapping(value = "/actions/matrixCross/porRt")
public class Matrix_cross_porRtController {
	
private static final Logger logger = LoggerFactory.getLogger(Matrix_cross_porRtController.class);
	
	@Autowired
	private Matrix_cross_porRtServiceImpl dataService ;
	
	@RequestMapping(value="/searchData")
	public ToolResponseQueryMsg searchData(CustomReqParameter parameter){
		
		ToolResponseQueryMsg msg = new ToolResponseQueryMsg();
		CIPPageInfo page = new CIPPageInfo(parameter.getPage(), parameter.getRows());
		Map<String, Object> paramsMap = new HashMap<String, Object>();

		String conditonStr = parameter.getSearch_condition();
		paramsMap.put("conditonStr", conditonStr);
		List<Matrix_cross_portId_rt> footer = dataService.searchFooterData(paramsMap);
		for (Matrix_cross_portId_rt data : footer) {
			page.setTotal(Integer.parseInt(data.getDbctCd()));
//			data.setEqptId(null);
//			data.setLnId(null);
//			data.setShipId("合计：");
		}
		paramsMap.put("start_num", page.getStartRecord());
		paramsMap.put("row_num", page.getRows());
		List<Matrix_cross_portId_rt> rows = dataService.searchData(paramsMap);

		if (!rows.isEmpty()) {
			msg.footer = footer;
		} else {
			msg.footer = new ArrayList<>();
		}
		//msg.footer = new ArrayList<>();
		msg.errorCode = CIPErrorCode.CALL_SUCCESS.code;
		msg.msg = CIPErrorCode.CALL_SUCCESS.name;
		msg.rows = rows;
		msg.total = page.getTotal();
		return msg;
	}
	
	/**
	 * 导出
	 * 
	 * @param parameter
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/exportEntities")
	public void exportEntities(CustomReqParameter parameter, HttpServletRequest request, HttpServletResponse response) {
		CustomResponseMsg msg = new CustomResponseMsg();
		String as = "交叉带式分拣操作件量统计表_" + DateUtils.getDate(new Date());
		String export_key = this.getClass().getSimpleName() + "_"
				+ Thread.currentThread().getStackTrace()[1].getMethodName();
		try {
			CIPHttpSession session = CIPSessionManager.getSession(request, response);
			if (!"1".equals(session.getAttribute(export_key, String.class))) {
				OutputStream os = null;
				session.setAttribute(export_key, "1");
				String fileName;
				String userAgent = request.getHeader("User-Agent");
				// 针对IE或者以IE为内核的浏览器：
				if (userAgent.contains("MSIE") || userAgent.contains("Trident")) {
					fileName = java.net.URLEncoder.encode(as, "UTF-8");
				} else {
					// 非IE浏览器的处理：
					fileName = new String(as.getBytes("UTF-8"), "ISO-8859-1");
				}

				response.setCharacterEncoding("utf-8");
				response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
				response.setHeader("Pragma", "No-cache");
				response.setHeader("Cache-Control", "No-cache");
				response.setDateHeader("Expires", 0);
				response.setHeader("Content-Disposition", "attachment;fileName=" + fileName + ".xlsx");
				
				String sheetName = "汇总列表";
				try {
					Map<String, Object> paramsMap = new HashMap<String, Object>();
					String conditonStr = parameter.getSearch_condition();
					paramsMap.put("conditonStr", conditonStr);

					CIPPageInfo page = new CIPPageInfo(1, 10000);
					paramsMap.put("start_num", page.getStartRecord());
					paramsMap.put("row_num", page.getRows());
					List<Matrix_cross_portId_rt> entities = dataService.searchData(paramsMap);
					if(!entities.isEmpty()){
						List<Matrix_cross_portId_rt> footer = dataService.searchFooterData(paramsMap);
						for (Matrix_cross_portId_rt data : footer) {
						//	page.setTotal(Integer.parseInt(data.getShipId()));
						}
					}
					//获取订单，每次最多导出1万条记录
					ExcelUtil<Matrix_cross_portId_rt> excelUtil = new ExcelUtil<>();
					SXSSFWorkbook wb = excelUtil.createWorkBook(sheetName, entities, Matrix_cross_portId_rt.class);
					
					int total = page.getTotal();
					int rows = page.getRows();
					if (total > rows) {
						int count = total / rows + 1;
						for (int i = 2; i <= count; i++) {
							page.setPage(i);
							paramsMap.put("start_num",page.getStartRecord());
							entities = dataService.searchData(paramsMap);
							excelUtil.appendWorkBook(wb,sheetName, entities, Matrix_cross_portId_rt.class);
						}
					}
					os = response.getOutputStream();
					wb.write(os);
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
				} catch (Exception e) {
					msg.errorCode = -1;
					msg.msg = e.getLocalizedMessage();
				} finally {
					try {
						session.removeAttribute(export_key);
						if (os != null) {
							os.flush();
							os.close();
						}
					} catch (IOException e) {

					}
				}
			}
		} catch (Exception e) {

		}
	}
}
