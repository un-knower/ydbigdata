package com.yd.ydbi.cainiao.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.yd.common.data.CIPPageInfo;
import com.yd.common.data.CIPResponseMsg;
import com.yd.common.data.CIPResponseQueryMsg;
import com.yd.common.exception.CIPDaoException;
import com.yd.common.exception.CIPRuntimeException;
import com.yd.common.exception.CIPServiceException;
import com.yd.common.runtime.CIPErrorCode;
import com.yd.common.runtime.CIPRuntimeConfigure;
import com.yd.common.session.CIPHttpSession;
import com.yd.common.session.CIPSessionManager;
import com.yd.common.utils.DateUtils;
import com.yd.ydbi.cainiao.model.CaiNiao;
import com.yd.ydbi.cainiao.model.CaiNiaoDxl;
import com.yd.ydbi.cainiao.model.CaiNiaoWeek;
import com.yd.ydbi.cainiao.service.impl.CaiNiaoDtlServiceImpl;
import com.yd.ydbi.cainiao.service.impl.CaiNiaoServiceImpl;
import com.yd.ydbi.common.response.CustomReqParameter;
import com.yd.ydbi.common.response.CustomResponseMsg;
import com.yd.ydbi.common.response.ToolResponseQueryMsg;
import com.yd.ydbi.utils.FileUploadUtil;
import com.yd.ydbi.utils.excel.ExcelUtil;

@RestController
@RequestMapping(value = "/actions/cainiao")
public class CaiNiaoController {
	private static final Logger logger = LoggerFactory.getLogger(CaiNiaoController.class);
	
	@Autowired
	private CaiNiaoServiceImpl cnService;
	@Autowired
	private CaiNiaoDtlServiceImpl cnDtlService;

	
	/**
	 * 条件查询
	 * 
	 * @param parameter
	 * @return
	 */
	@RequestMapping(value="/searchData")
	public ToolResponseQueryMsg searchData(CustomReqParameter parameter){		
		ToolResponseQueryMsg msg = new ToolResponseQueryMsg();
		CIPPageInfo page = new CIPPageInfo(parameter.getPage(), parameter.getRows());
		Map<String, Object> paramsMap = new HashMap<String, Object>();

		String conditonStr = parameter.getSearch_condition();
		paramsMap.put("conditonStr", conditonStr);
		List<CaiNiao> footer = cnService.searchFooterData(paramsMap);
		for (CaiNiao data : footer) {
			page.setTotal(data.getFullPathNm());
		}
		
		paramsMap.put("start_num", page.getStartRecord());
		paramsMap.put("row_num", page.getRows());
		List<CaiNiao> rows = cnService.searchData(paramsMap);
		if (!rows.isEmpty()) {
			msg.footer = footer;
		} else {
			msg.footer = new ArrayList<>();
		}
		
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
		String as = "菜鸟数据录入信息_" + DateUtils.getDate(new Date());
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
					List<CaiNiao> entities = cnService.searchData(paramsMap);
					if(!entities.isEmpty()){
						List<CaiNiao> footer = cnService.searchFooterData(paramsMap);
						for (CaiNiao data : footer) {
						//	page.setTotal(Integer.parseInt(data.getShipId()));
						}
					}
					//获取订单，每次最多导出1万条记录
					ExcelUtil<CaiNiao> excelUtil = new ExcelUtil<>();
					SXSSFWorkbook wb = excelUtil.createWorkBook(sheetName, entities, CaiNiao.class);
					
					int total = page.getTotal();
					int rows = page.getRows();
					if (total > rows) {
						int count = total / rows + 1;
						for (int i = 2; i <= count; i++) {
							page.setPage(i);
							paramsMap.put("start_num",page.getStartRecord());
							entities = cnService.searchData(paramsMap);
							excelUtil.appendWorkBook(wb,sheetName, entities, CaiNiao.class);
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
	
	/**
	* 根据日期查询
	*/
	@RequestMapping(value = "/getDataByStatsDt")
	public CIPResponseMsg getDataByStatsDt(@RequestBody CaiNiao cn, HttpServletRequest request) {
		CIPResponseMsg msg = new CIPResponseMsg();
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		String conditonStr = cn.getStatsDt();
		paramsMap.put("statsDt",conditonStr);
		try {
			//String keys = cn.getStatsDt();
			CaiNiao cndata = cnService.getCaiNiaoBystatsDt(paramsMap);
			msg.errorCode = CIPErrorCode.CALL_SUCCESS.code;
			msg.msg = CIPErrorCode.CALL_SUCCESS.name;
			msg.data = cndata;
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
	 * 通过Excel导入其他费用成本数据
	 * @param files
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/importData", produces="text/html;charset=utf-8")
	public String importData(@RequestParam(value = "importFile") MultipartFile file,@RequestParam(value = "importFileShi") MultipartFile fileOfShi , HttpServletRequest request){
		CIPResponseQueryMsg msg = new CIPResponseQueryMsg();
		
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		Map<String, List<CaiNiaoDxl>> dataListMap = new HashMap<String, List<CaiNiaoDxl>>();
		String fileName = file.getOriginalFilename();
		String fileShiName = fileOfShi.getOriginalFilename();
	    String conditonStr = request.getParameter("search_condition");
	    String statsDt = request.getParameter("statsDt");
	    paramsMap.put("conditonStr", conditonStr);
	    
		//重复录入校验
		CaiNiao cainiao = cnService.getCaiNiaoBystatsDt(paramsMap);
		if(cainiao!=null)
		{
			msg.errorCode = -1;
			msg.msg = "该日期已有数据，请重试";
		//文件格式校验，后缀名必须为xlsx
		}else if(!"xlsx".equalsIgnoreCase(fileName.substring(fileName.lastIndexOf(".") + 1))&&!"xls".equalsIgnoreCase(fileName.substring(fileName.lastIndexOf(".") + 1))) 
		{
			msg.errorCode = -1;
			msg.msg = "省-省文件，请上传xlsx格式或xls格式的Excel文件！";
		//文件格式校验，后缀名必须为xlsx
		}else if(!"xlsx".equalsIgnoreCase(fileShiName.substring(fileShiName.lastIndexOf(".") + 1))&&!"xls".equalsIgnoreCase(fileShiName.substring(fileShiName.lastIndexOf(".") + 1))) 
		{
			msg.errorCode = -1;
			msg.msg = "市-市文件，请上传xlsx格式或xls格式的Excel文件！";
		//excel文件解析
		}  else {
			File targetDir = new File(CIPRuntimeConfigure.cip_actual_file_path);
			if (!targetDir.exists() || !targetDir.isDirectory()) {
				targetDir.mkdirs();
			}
			
			List<CaiNiaoDxl> dataList = null;
			List<CaiNiaoDxl> dataShiList = null;
			
			String  path=targetDir.getPath() + "/" ;
			try {
				if(file != null) {
					String localFilePath = FileUploadUtil.saveFile(file, path);
					dataList = getVolistFromLocalFile(localFilePath,statsDt,1);
				}
				if(fileOfShi != null) {
					String localFileShiPath = FileUploadUtil.saveFile(fileOfShi, path);
					dataShiList = getVolistFromLocalFile(localFileShiPath,statsDt,2);
				}
				//excel文件数据入库
				if(!(dataList==null)) {
					
					dataListMap.put("dataList", dataList);
					cnDtlService.importData(dataListMap);

					if(!(dataShiList==null)) {
						/*dataListMap.put("dataList", dataShiList);
						cnDtlService.importData(dataListMap);*/
						
						//分批次导入
						int size = dataShiList.size();
						int unitNum = 1000;
						int startIndex = 0;
						int endIndex = 0;
						while (size > 0){
							if(size > unitNum){
								endIndex = startIndex+unitNum;
							}else {
								endIndex = startIndex+size;
							}
							List<CaiNiaoDxl> insertList = dataShiList.subList(startIndex,endIndex);
							dataListMap.put("dataList", insertList);
							cnDtlService.importData(dataListMap);
							size = size - unitNum;
							startIndex = endIndex;
						}
					}
					//当前操作用户即为录入人
					cnService.insertCn(paramsMap);
					System.out.println("=============="+new Date());
					
					msg.errorCode = CIPErrorCode.CALL_SUCCESS.code;
					msg.msg = CIPErrorCode.CALL_SUCCESS.name;
				}else{
					msg.errorCode = -1;
					msg.msg = "Excel文件上传处理失败";
				}
			} catch (CIPServiceException e) {
				CIPErrorCode error = e.getErrorCode();
				msg.errorCode = error.code;
				msg.msg = error.name;
			} catch (CIPDaoException e) {
				msg.errorCode = -1;
				msg.msg = "导入失败";
			} catch (CIPRuntimeException e) {
				CIPErrorCode error = e.getErrorCode();
				msg.errorCode = error.code;
				msg.msg = error.name;
			} catch (Exception e) {
				e.printStackTrace();
				msg.errorCode = -1;
				msg.msg = e.getLocalizedMessage();
			}
		}
		
		String resultStr = JSON.toJSONString(msg);
		return resultStr;
	}
	//Excel模板导入列标题
	public static final String[] excelTitle = 
			{ "线路名称", "去程单量", "返程单量", "去返比", "去程时效", "去程行业排名", "去程行业均值", "返程时效", "返程行业排名", "返程行业均值" };
	
	/**
	 * 根据server文件路径从excel中获取CaiNiaoDxl list
	 * 该方法可以包含常规数据校验
	 * 
	 * @param localFilePath
	 * @return
	 */
	private List<CaiNiaoDxl> getVolistFromLocalFile(String localFilePath,String statsDt,int lineType) {
		FileInputStream fis = null;
		
		//记录处理的行号和单元格编号，以便精确报错提示
		int rowIndex = -1;
		int cellIndex = -1;
		try {
			fis = new FileInputStream(new File(localFilePath));
			Workbook wb = new XSSFWorkbook(fis);
			//Workbook wb = createWorkbook(fis);
			Sheet sheet = wb.getSheetAt(0);
			
			Row row;
			List<CaiNiaoDxl> vos = new ArrayList<>();
			CaiNiaoDxl vo = null;
			
			for(rowIndex = 2; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
				row = sheet.getRow(rowIndex);
				vo = new CaiNiaoDxl();
				
				//增加空行验证：空行跳过
				//判别空行标准：主键字段fb_id,item_code,account_dt均为空
				if(row.getCell(0) == null && row.getCell(1) == null&& row.getCell(2) == null&& row.getCell(3) == null&& row.getCell(4) == null
						&& row.getCell(5) == null && row.getCell(6) == null && row.getCell(7) == null
						&& row.getCell(8) == null && row.getCell(9) == null) {
					continue;    // 中断本次循环开始下一次。
				}
				
				for(cellIndex = 0; cellIndex < excelTitle.length; cellIndex++) {
					Cell cell = row.getCell(cellIndex);			
					int cellType;
					if(cell==null){
						cellType=3;
					}else if("".equals(cell.toString().trim())){
						cellType=3;
					}else{
						cellType=cell.getCellType();
					}
					switch (cellIndex) {
					case 0:
						String lnNm = cell.getStringCellValue();
						String[] lnNms = lnNm.split("-");
						String startPlace = lnNms[0];
						String endPlace = lnNms[1];
						boolean isMatch=false;
						if(startPlace!=null&&endPlace!=null){
							String regex = "^[\u4e00-\u9fa5]+$";//汉字校验
							isMatch = Pattern.matches(regex, startPlace);
							if(isMatch){
								isMatch = Pattern.matches(regex, endPlace);
								if(isMatch){
									vo.setStartPlace(startPlace);//始发地
									vo.setEndPlace(endPlace);	//目的地
									vo.setLnNm(lnNm);			//线路名称
									vo.setStartDt(statsDt);//统计日期
									vo.setLineType(lineType);	//线路类型
									break;
								}else{
									String errMsg = String.format("Excel文件格式错误:第(%1$d)行,字段(%2$s)数据异常！", rowIndex+1, excelTitle[cellIndex]);
									throw new CIPServiceException(new CIPErrorCode(-1, errMsg));
								}
							}else{
								String errMsg = String.format("Excel文件格式错误:第(%1$d)行,字段(%2$s)数据异常！", rowIndex+1, excelTitle[cellIndex]);
								throw new CIPServiceException(new CIPErrorCode(-1, errMsg));
							}
						}
						
					case 1://去程单量
						Integer levUnitCnt=0;
						if(cellType==3){
							break;
						}else{
							levUnitCnt= (int) cell.getNumericCellValue();
						}
						vo.setLevUnitCnt(levUnitCnt);
						break;
					case 2://返程单量
						Integer rtnUnitCnt=0;
						if(cellType==3){
							break;
						}else{
							rtnUnitCnt= (int) cell.getNumericCellValue();
						}
						vo.setRtnUnitCnt(rtnUnitCnt);
						break;
					case 3://去返比
						BigDecimal levRtnRate;
						if(cellType==3){
							break;
						}else {
							levRtnRate = new BigDecimal(cell.getNumericCellValue());
						}
						vo.setLevRtnRate(levRtnRate);
						break;
					case 4://去程时效
						BigDecimal levTlns;
						if(cellType==3){
							break;
						}else {
							levTlns = new BigDecimal( cell.getNumericCellValue());
						}
						vo.setLevTlns(levTlns);
						break;
					case 5://去程行业排名
						Integer levIndustNm=0;
						if(cellType==3){
							break;
						}else{
							levIndustNm = (int) cell.getNumericCellValue();
						}
						vo.setLevIndustNm(levIndustNm);
						break;
					case 6://去程行业均值
						BigDecimal levIndustVal;
						if(cellType==3){
							break;
						}else {
							levIndustVal = new BigDecimal(cell.getNumericCellValue());
						}
						vo.setLevIndustVal(levIndustVal);
						break;
					case 7://返程时效
						BigDecimal rtnTlns;
						if(cellType==3){
							break;
						}else {
							rtnTlns = new BigDecimal(cell.getNumericCellValue());
						}
						vo.setRtnTlns(rtnTlns);
						break;
					case 8://返程行业排名
						Integer rtnIndustNm=0;
						if(cellType==3){
							break;
						}else{
							rtnIndustNm = (int) cell.getNumericCellValue();
						}
						vo.setRtnIndustNm(rtnIndustNm);
						break;
					case 9://返程行业均值
						BigDecimal rtnIndustVal;
						if(cellType==3){
							break;
						}else {
							rtnIndustVal = new BigDecimal(cell.getNumericCellValue());
						}
						vo.setRtnIndustVal(rtnIndustVal);
						break;
					default:
						break;
					}
				}
				//list添加
				vos.add(vo);
			}
			fis.close();
			System.out.println("vos size=" + vos.size());
			return vos;
		} catch (Exception e) {
			e.printStackTrace();
			//格式化报错信息 "Excel文件解析错误:第(xx)行,字段(xxx)格式异常！"
			String errMsg = String.format("Excel文件格式错误:第(%1$d)行,字段(%2$s)格式异常！", rowIndex+1, excelTitle[cellIndex]);
			throw new CIPServiceException(new CIPErrorCode(-1, errMsg));
		} finally {
			if(fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	  
	/**
	* 更新
	*/
	@RequestMapping(value = "/updateData")
	public CIPResponseMsg updateData(HttpServletRequest request) {
		CIPResponseQueryMsg msg = new CIPResponseQueryMsg();
		
		Map<String, Object> paramsMap = new HashMap<String, Object>();
	    String conditonStr = request.getParameter("search_condition");
	    paramsMap.put("conditonStr", conditonStr);
	    
		try {
			cnService.updateData(paramsMap);
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
	  
	/**
	* 批量删除
	*/
	@RequestMapping(value = "/deleteData")
	public CIPResponseMsg deleteData(HttpServletRequest request) {
		CIPResponseMsg msg = new CIPResponseMsg();
		Map<String, Object> paramsMap = new HashMap<String, Object>();
	    String conditonStr = request.getParameter("strIds");
	    String[] strs = conditonStr.split(",");
	    List<String> searchList = new ArrayList<String>();	
	    if(conditonStr.contains(",")){
	    	for (int i = 0; i < strs.length; i++) {
	    		searchList.add(strs[i]);
			}
	    }else{
	    	searchList.add(strs[0]);
	    }
	    paramsMap.put("strIds", searchList);
	    
		try {
			//明细表删除数据
			cnDtlService.deleteData(paramsMap);
			//信息表删除数据
			cnService.deleteData(paramsMap);
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
  

	
	/*线路日明细报表*/
	 @RequestMapping(value="/searchDxlData")
	public ToolResponseQueryMsg searchDxlData(CustomReqParameter parameter){		
		ToolResponseQueryMsg msg = new ToolResponseQueryMsg();
		CIPPageInfo page = new CIPPageInfo(parameter.getPage(), parameter.getRows());
		Map<String,Object> paramsMap = new HashMap<String,Object>();

		String conditonStr = parameter.getSearch_condition();
		paramsMap.put("conditonStr", conditonStr);
		List<CaiNiaoDxl> footer = cnDtlService.searchFooterData(paramsMap);
		for (CaiNiaoDxl data : footer) {
			page.setTotal(data.getLevUnitCnt());
		}
		
		
		paramsMap.put("start_num", page.getStartRecord());
		paramsMap.put("row_num", page.getRows());
		List<CaiNiaoDxl> rows = cnDtlService.searchDxlData(paramsMap);
		
		String sdate="";
		List<CaiNiao> totData = new ArrayList<CaiNiao>();
		if (!rows.isEmpty()) {
			for (int i = 0; i < rows.size(); i++) {
				sdate = rows.get(0).getStartDt();
			}
			paramsMap.put("statsDt1", sdate);
			paramsMap.put("statsDt2", sdate);
			//当日全链路时效查询
			totData = cnService.searchData(paramsMap);
			msg.footer = footer;
		} else {
			msg.footer = new ArrayList<>();
		}
		
		msg.errorCode = CIPErrorCode.CALL_SUCCESS.code;
		msg.msg = CIPErrorCode.CALL_SUCCESS.name;
		msg.rows = rows;
		msg.total = page.getTotal();
		msg.data = totData;
		return msg;
	}
	
	 
	 /**
	 * 导出
	 * 
	 * @param parameter
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/exportDxlEntities")
	public void exportDxlEntities(CustomReqParameter parameter, HttpServletRequest request, HttpServletResponse response) {
		CustomResponseMsg msg = new CustomResponseMsg();
		String as = "线路日明细报表_" + DateUtils.getDate(new Date());
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
					List<CaiNiaoDxl> entities = cnDtlService.searchDxlData(paramsMap);
					if(!entities.isEmpty()){
						List<CaiNiaoDxl> footer = cnDtlService.searchFooterData(paramsMap);
						for (CaiNiaoDxl data : footer) {
						//	page.setTotal(Integer.parseInt(data.getShipId()));
						}
					}
					//获取订单，每次最多导出1万条记录
					ExcelUtil<CaiNiaoDxl> excelUtil = new ExcelUtil<>();
					SXSSFWorkbook wb = excelUtil.createWorkBook(sheetName, entities, CaiNiaoDxl.class);
					
					int total = page.getTotal();
					int rows = page.getRows();
					if (total > rows) {
						int count = total / rows + 1;
						for (int i = 2; i <= count; i++) {
							page.setPage(i);
							paramsMap.put("start_num",page.getStartRecord());
							entities = cnDtlService.searchDxlData(paramsMap);
							excelUtil.appendWorkBook(wb,sheetName, entities, CaiNiaoWeek.class);
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
	
	/**
	 * 线路周报表
	 * 
	 * @param parameter
	 * @return
	 */
	@RequestMapping(value="/searchWeekData")
	public ToolResponseQueryMsg searchWeekData(CustomReqParameter parameter){		
		ToolResponseQueryMsg msg = new ToolResponseQueryMsg();
		CIPPageInfo page = new CIPPageInfo(parameter.getPage(), parameter.getRows());
		Map<String,Object> paramsMap = new HashMap<String,Object>();
		
		String conditonStr = parameter.getSearch_condition();
		paramsMap.put("conditonStr", conditonStr);
		List<CaiNiaoWeek> footer = cnDtlService.searchWeekFooterData(paramsMap);
		for (CaiNiaoWeek data : footer) {
			page.setTotal(data.getLineQuantity());
		}
		
		
		paramsMap.put("start_num", page.getStartRecord());
		paramsMap.put("row_num", page.getRows());
		List<CaiNiaoWeek> rows = cnDtlService.searchWeekData(paramsMap);
		
		if (!rows.isEmpty()) {
			msg.footer = footer;
		} else {
			msg.footer = new ArrayList<>();
		}
		
		msg.errorCode = CIPErrorCode.CALL_SUCCESS.code;
		msg.msg = CIPErrorCode.CALL_SUCCESS.name;
		msg.rows = rows;
		msg.total = page.getTotal();
		return msg;
	}
	
	
	/**
	 * 线路周报表导出
	 * 
	 * @param parameter
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/exportWeekEntities")
	public void exportWeekEntities(CustomReqParameter parameter, HttpServletRequest request, HttpServletResponse response) {
		CustomResponseMsg msg = new CustomResponseMsg();
		String as = "线路周报表_" + DateUtils.getDate(new Date());
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
					List<CaiNiaoWeek> entities = cnDtlService.searchWeekData(paramsMap);
					if(!entities.isEmpty()){
						List<CaiNiaoWeek> footer = cnDtlService.searchWeekFooterData(paramsMap);
						for (CaiNiaoWeek data : footer) {
							//	page.setTotal(Integer.parseInt(data.getShipId()));
						}
					}
					//获取订单，每次最多导出1万条记录
					ExcelUtil<CaiNiaoWeek> excelUtil = new ExcelUtil<>();
					SXSSFWorkbook wb = excelUtil.createWorkBook(sheetName, entities, CaiNiaoWeek.class);
					
					int total = page.getTotal();
					int rows = page.getRows();
					if (total > rows) {
						int count = total / rows + 1;
						for (int i = 2; i <= count; i++) {
							page.setPage(i);
							paramsMap.put("start_num",page.getStartRecord());
							entities = cnDtlService.searchWeekData(paramsMap);
							excelUtil.appendWorkBook(wb,sheetName, entities, CaiNiaoWeek.class);
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
	
	/**
	 * 
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/getRegion")
	public CustomResponseMsg getRegion(HttpServletRequest request) {
		Map<String, String> paramsMap = new HashMap<String, String>();
		CustomResponseMsg msg = new CustomResponseMsg();
		try{
			paramsMap.put("startPlace", request.getParameter("startPlace"));
			paramsMap.put("endPlace", request.getParameter("endPlace"));
			msg.data = cnDtlService.getLineRegion(paramsMap);
		}catch (RuntimeException e) {
			//logger.error(e);
			msg.errorCode = CIPErrorCode.ERROR_TECHNICAL_ERROR.code;
			msg.msg = e.getMessage();
		}
		return msg;
	}
}
