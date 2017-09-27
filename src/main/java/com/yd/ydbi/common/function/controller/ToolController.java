package com.yd.ydbi.common.function.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yd.common.runtime.CIPErrorCode;
import com.yd.common.utils.DateUtils;
import com.yd.common.utils.HttpUtils;
import com.yd.ydbi.common.function.service.ToolService;
import com.yd.ydbi.common.response.CustomResponseMsg;
import com.yd.ydbi.utils.MD5;

//公共模块，如获取省市数据等
@RestController
@RequestMapping(value = "/actions/tool")
public class ToolController {
	private Logger logger = Logger.getLogger(ToolController.class);
	
	@Autowired
	ToolService toolService;
	
	//获取系统当前时间
	@RequestMapping(value="/nowDate")
	public CustomResponseMsg getNowDate(@RequestParam("mth") String mthStr,@RequestParam("day") String dayStr) {
		
		CustomResponseMsg msg = new CustomResponseMsg(); 
		try{
//			int mth =Integer.parseInt(request.getParameter("mth"));
//			int day = Integer.parseInt(request.getParameter("day"));
			int mth =Integer.parseInt(mthStr);
			int day = Integer.parseInt(dayStr);
			Date dt=new Date();
			Calendar calendar=Calendar.getInstance();
			calendar.setTime(dt);
			calendar.add(Calendar.MONTH, mth);//得到前一个月  
			calendar.add(Calendar.DATE, day);//得到前一天  
			msg.data = DateUtils.formatDate(calendar.getTime());
		}catch (RuntimeException e) {
			logger.error(e);
			msg.errorCode = CIPErrorCode.ERROR_TECHNICAL_ERROR.code;
			msg.msg = e.getMessage();
		}
		return msg;
	}
	
	//获取机构下拉框信息
	@RequestMapping(value="/getRegion")
	public CustomResponseMsg getRegion(@RequestParam("sel_cd") String sel_cd,@RequestParam("sel_level") String sel_level) {
//	public CustomResponseMsg getRegion(HttpServletRequest request) {
		Map<String, String> paramsMap = new HashMap<String, String>();
		CustomResponseMsg msg = new CustomResponseMsg();
		try{
//			paramsMap.put("sel_level", request.getParameter("sel_level"));
//			paramsMap.put("sel_cd", request.getParameter("sel_cd"));
			paramsMap.put("sel_level", sel_level);
			paramsMap.put("sel_cd", sel_cd);
			msg.data = toolService.getRegion(paramsMap);
		}catch (RuntimeException e) {
			logger.error(e);
			msg.errorCode = CIPErrorCode.ERROR_TECHNICAL_ERROR.code;
			msg.msg = e.getMessage();
		}
		return msg;
	}
	
	//获取机构下拉框信息
	@RequestMapping(value="/getRegionByRole")
	public CustomResponseMsg getRegionByRole(@RequestParam("user_role") String user_role) {
		Map<String, String> paramsMap = new HashMap<String, String>();
		CustomResponseMsg msg = new CustomResponseMsg();
		try{
			paramsMap.put("user_role", user_role);
			msg.data = toolService.getRegionByRole(paramsMap);
		}catch (RuntimeException e) {
			logger.error(e);
			msg.errorCode = CIPErrorCode.ERROR_TECHNICAL_ERROR.code;
			msg.msg = e.getMessage();
		}
		return msg;
	}
	
	//获取系统当前时间
	@RequestMapping(value="/getOrgInf")
	public CustomResponseMsg getOrgInf(@RequestParam("sel_cd") String sel_cd) {
		Map<String, String> paramsMap = new HashMap<String, String>();
		CustomResponseMsg msg = new CustomResponseMsg();
		try{
			paramsMap.put("sel_cd",sel_cd);
			msg.data = toolService.getOrgInf(paramsMap);
		}catch (RuntimeException e) {
			logger.error(e);
			msg.errorCode = CIPErrorCode.ERROR_TECHNICAL_ERROR.code;
			msg.msg = e.getMessage();
		}
		return msg;
	}
	
	
	
	//票单MD5加密
	@RequestMapping(value="/shipmentNoMD5")
	public CustomResponseMsg shipmentNoMD5(@RequestParam("shipment_no") String shipment_no) {
		
		CustomResponseMsg msg = new CustomResponseMsg();
		try{
			Map<String,String> shipment_no_map = new HashMap<String,String>();
			String url ="http://n1cx.yundasys.com/nbsw/gohh.php?act=geth&wen="+shipment_no;
			String shipment_no_md5 = MD5.MD5(shipment_no);
			String shipment_no_id = HttpUtils.get(url);
			shipment_no_map.put("shipment_no_md5", shipment_no_md5);
			shipment_no_map.put("shipment_no_id", shipment_no_id);
			msg.data = shipment_no_map;
		}catch (Exception e) {
			logger.error(e);
			msg.errorCode = CIPErrorCode.ERROR_TECHNICAL_ERROR.code;
			msg.msg = e.getMessage();
		}
		return msg;
	}
	
}
