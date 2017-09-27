package com.yd.ydbi.setl.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yd.common.cache.CIPCacheService;
import com.alibaba.fastjson.JSON;
import com.yd.common.cache.CIPCacheManager;
import com.yd.common.data.CIPPageInfo;
import com.yd.common.exception.CIPDaoException;
import com.yd.common.exception.CIPRuntimeException;
import com.yd.common.exception.CIPServiceException;
import com.yd.common.runtime.CIPErrorCode;
import com.yd.common.runtime.CIPRuntime;
import com.yd.common.runtime.CIPRuntimeConfigure;
import com.yd.common.session.CIPHttpSession;
import com.yd.common.session.CIPSessionManager;
import com.yd.common.utils.DateUtils;
import com.yd.ydbi.common.response.CustomReqParameter;
import com.yd.ydbi.common.response.CustomResponseMsg;
import com.yd.ydbi.common.response.ToolResponseQueryMsg;
import com.yd.ydbi.setl.model.Setl_ship_dtl;
import com.yd.ydbi.setl.service.impl.Setl_ship_dtlServiceImpl;
import com.yd.ydbi.utils.excel.ExcelUtil;

/**
 * 
 * Function:结算明细信息管理控制器. <br/>
 * 
 * @date:2017-06-11 16:42:35 
 * @author 康元佳
 * @version:
 * @since:JDK 1.7
 */
@RestController
@RequestMapping(value = "/actions/setl/setl_ship_dtl")
public class Setl_ship_dtlController{
	
	@Autowired
	private Setl_ship_dtlServiceImpl dataService;
	
	CIPCacheService cache = CIPCacheManager.getCacheService();
	
	@RequestMapping(value = "/searchData")
	public ToolResponseQueryMsg searchData(CustomReqParameter parameter) {
		ToolResponseQueryMsg msg = new ToolResponseQueryMsg();
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		String key = "cacheData@" + CIPRuntime.getOperateSubject().getSubject_id() +"-" + this.getClass().getSimpleName() + "_" + Thread.currentThread().getStackTrace()[1].getMethodName()+"-"+parameter.getTimestamp();
		List<Setl_ship_dtl> list = null;
		CIPPageInfo page = new CIPPageInfo(parameter.getPage(), parameter.getRows());
		if(!cache.exists(key)){
			paramsMap.put("conditonStr", parameter.getSearch_condition());
			paramsMap.put("key", key);
			
			cache.set(key, JSON.toJSONString(list), true);
			
			list = dataService.searchData(paramsMap,page);
			msg.errorCode = -100;
			msg.msg = "正在执行";
			msg.total = 0;
			msg.rows = null;
		}else{
			list = JSON.parseArray(cache.get(key,String.class),Setl_ship_dtl.class);
			if(list != null && !list.isEmpty()){
				
				msg.errorCode = CIPErrorCode.CALL_SUCCESS.code;
				msg.msg = CIPErrorCode.CALL_SUCCESS.name;
				msg.total = list.get(list.size()-1).getSetlOrgCd();
				list.remove(list.size()-1);
				msg.rows = list;
				cache.remove(key);
			}else{
				msg.errorCode = -100;
				msg.msg = "正在执行";
				msg.total = 0;
				msg.rows = null;
			}
		}
		
		return msg;
	}

	/**
	 * 导出退款汇总
	 */
	@RequestMapping(value = "/exportEntities")
	public CustomResponseMsg exportData(CustomReqParameter parameter) {
		CustomResponseMsg msg = new CustomResponseMsg();
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		
//		String timeStr = String.valueOf(System.currentTimeMillis());
		String key = "cacheExport@" + CIPRuntime.getOperateSubject().getSubject_id() +"-" + this.getClass().getSimpleName() + "_" + Thread.currentThread().getStackTrace()[1].getMethodName()+"-"+parameter.getTimestamp();
		String conditonStr = parameter.getSearch_condition();
		
		if(!cache.exists(key)){
			cache.set(key, "0", true);
			paramsMap.put("conditonStr", conditonStr);
			paramsMap.put("key", key);
			paramsMap.put("timeStr", parameter.getTimestamp()+CIPRuntime.getOperateSubject().getSubject_id());
			dataService.exportData(paramsMap);
			msg.errorCode = -100;
			msg.msg = "正在执行";	
		}else{
			String flag = cache.get(key,String.class);
			if(flag != null && !flag.isEmpty() && "1".equals(flag)){
				
				msg.errorCode = CIPErrorCode.CALL_SUCCESS.code;
				msg.msg = CIPErrorCode.CALL_SUCCESS.name;
				cache.remove(key);
			}else{
				msg.errorCode = -100;
				msg.msg = "正在执行";
			}
		}
		return msg;
	}
	/**
	 * 导出退款汇总
	 */
	@RequestMapping(value = "/downFile")
	public void downFile(CustomReqParameter parameter, HttpServletRequest request, HttpServletResponse response) {
		String filePath =  CIPRuntimeConfigure.cip_temp_file_path;
		String fileName = "明细导出_" + DateUtils.getTime(new Date(), "yyyyMMddHHmmss") + ".zip";
		String realFileName = parameter.getTimestamp() + CIPRuntime.getOperateSubject().getSubject_id()+".zip";
		if (parameter.getTimestamp() != null) {
		    File file = new File(filePath, realFileName);
		    if (file.exists()) {
		    	String userAgent = request.getHeader("User-Agent");
				// 针对IE或者以IE为内核的浏览器：
				try {
					if (userAgent.contains("MSIE") || userAgent.contains("Trident")) {
						fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
					} else {
						// 非IE浏览器的处理：
						fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
					}
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				response.setCharacterEncoding("utf-8");
//				response.setContentType("application/zip");
				response.setHeader("Pragma", "No-cache");
				response.setHeader("Cache-Control", "No-cache");
				response.setDateHeader("Expires", 0);
		        response.setContentType("application/force-download");// 设置强制下载不打开
		        response.addHeader("Content-Disposition", "attachment;fileName=" + fileName );// 设置文件名
		        byte[] buffer = new byte[1024];
		        FileInputStream fis = null;
		        BufferedInputStream bis = null;
		        OutputStream os = null;
		        try {
		        	os = response.getOutputStream();
		            fis = new FileInputStream(file);
		            bis = new BufferedInputStream(fis);
		            int i = bis.read(buffer);
		            while (i != -1) {
		                os.write(buffer, 0, i);
		                i = bis.read(buffer);
		            }
		        } catch (Exception e) {
		            e.printStackTrace();
		        } finally {
		        	if (os != null) {
						try {
							os.flush();
							os.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
		            if (bis != null) {
		                try {
		                    bis.close();
		                } catch (IOException e) {
		                    e.printStackTrace();
		                }
		            }
		            if (fis != null) {
		                try {
		                    fis.close();
		                } catch (IOException e) {
		                    e.printStackTrace();
		                }
		            }
		            file.delete();
		        }
		    }
		}
	}

}
