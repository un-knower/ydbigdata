package com.yd.ydbi.seffect.controller;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.yd.ydbi.common.response.CustomReqParameter;
import com.yd.ydbi.common.response.CustomResponseMsg;
import com.yd.ydbi.seffect.service.SeffectPickService;

/**
 * <p>
 * 票件_揽件时效控制层实现类
 * </p>
 * <p>
 * Class: SeffectController
 * </p>
 */
@Controller
@ResponseBody
@RequestMapping(value = "/action/bas_s_pick_bigdata")
public class SeffectPickController {
	private Logger logger = Logger.getLogger(SeffectPickController.class);
	@Autowired
	SeffectPickService seffectPickService;

	// 票件_揽件时效(包含日环比,周同比)
	@RequestMapping("/getSeffectPick")
	public CustomResponseMsg getSeffectPick(CustomReqParameter parameter) {
		CustomResponseMsg msg = new CustomResponseMsg();
		Map<String, Object> paramMap = new HashMap<>();
		String conditonStr = parameter.getSearch_condition();
		paramMap.put("conditonStr", conditonStr);
		Map<String, Object> seffectPick = seffectPickService.getSeffectPick(paramMap);
		msg.data = seffectPick;
		msg.errorCode = 0;
		msg.msg = "操作成功";
		logger.info(msg);
		return msg;
	}

	// 揽件时效日月周平均
	@RequestMapping("/getDwmAvgAmount")
	public CustomResponseMsg getDwmAvgAmount(CustomReqParameter parameter) {
		CustomResponseMsg msg = new CustomResponseMsg();
		Map<String, Object> paramMap = new HashMap<>();
		String conditonStr = parameter.getSearch_condition();
		paramMap.put("conditonStr", conditonStr);
		Map<String, Object> DwmAvgAmount = seffectPickService.selectDwmAvgAmount(paramMap);
		msg.data = DwmAvgAmount;
		msg.errorCode = 0;
		msg.msg = "操作成功";
		logger.info(msg);
		return msg;
	}
	
	// 揽件时效趋势图(日)
	@RequestMapping("/getDayTrend")
	public CustomResponseMsg getDayTrend(CustomReqParameter parameter) throws ParseException {
		CustomResponseMsg msg = new CustomResponseMsg();
		Map<String, Object> paramMap = new HashMap<>();
		String conditonStr = parameter.getSearch_condition();
		paramMap.put("conditonStr", conditonStr);
		List<Map<String, Object>> seffectPickDTrend = seffectPickService.seffectPickDTrend(paramMap);
		msg.data = seffectPickDTrend;
		msg.errorCode = 0;
		msg.msg = "操作成功";
		logger.info(msg);
		return msg;
	}
	
	// 揽件时效趋势图(周)

	@RequestMapping("/getWeekTrend")
	public CustomResponseMsg getWeekTrend(CustomReqParameter parameter) throws ParseException {
		CustomResponseMsg msg = new CustomResponseMsg();
		Map<String, Object> paramMap = new HashMap<>();
		String conditonStr = parameter.getSearch_condition();
		paramMap.put("conditonStr", conditonStr);
		List<Map<String, Object>> pickWTrend = seffectPickService.seffectPickWTrend(paramMap);
		msg.data = pickWTrend;
		msg.errorCode = 0;
		msg.msg = "操作成功";
		logger.info(msg);
		return msg;
	}
	
	
	// 揽件时效趋势图(月)
	@RequestMapping("/getMonthTrend")
	public CustomResponseMsg getMonthTrend(CustomReqParameter parameter) throws ParseException {
		CustomResponseMsg msg = new CustomResponseMsg();
		Map<String, Object> paramMap = new HashMap<>();
		String conditonStr = parameter.getSearch_condition();
		paramMap.put("conditonStr", conditonStr);
		List<Map<String, Object>> pickMTrend = seffectPickService.seffectPickMTrend(paramMap);
		msg.data = pickMTrend;
		msg.errorCode = 0;
		msg.msg = "操作成功";
		logger.info(msg);
		return msg;
	}
	
	// 揽件时效趋势图(季度)
	@RequestMapping("/getQuarterlyTrend")
	public CustomResponseMsg getQuarterlyTrend(CustomReqParameter parameter) throws ParseException {
		CustomResponseMsg msg = new CustomResponseMsg();
		Map<String, Object> paramMap = new HashMap<>();
		String conditonStr = parameter.getSearch_condition();
		paramMap.put("conditonStr", conditonStr);
		List<Map<String, Object>> pickQTrend = seffectPickService.seffectPickQTrend(paramMap);
		msg.data = pickQTrend;
		msg.errorCode = 0;
		msg.msg = "操作成功";
		logger.info(msg);
		return msg;
	}
	
	
	// 揽件时效占比(订单揽件量占比)
	@RequestMapping("/getSeffectPickCp")
	public CustomResponseMsg getSeffectPickCp(CustomReqParameter parameter) {
		CustomResponseMsg msg = new CustomResponseMsg();
		Map<String, Object> paramMap = new HashMap<>();
		String conditonStr = parameter.getSearch_condition();
		paramMap.put("conditonStr", conditonStr);
		List<Map<String, Object>>  seffectPickCp = seffectPickService.seffectPickCp(paramMap);
		msg.data = seffectPickCp;
		msg.errorCode = 0;
		msg.msg = "操作成功";
		logger.info(msg);
		return msg;
	}
	
	// 揽件时效占比(订单揽件量占比)
	@RequestMapping("/getEffectPickSort")
	public CustomResponseMsg getEffectPickSort(CustomReqParameter parameter) {
		CustomResponseMsg msg = new CustomResponseMsg();
		Map<String, Object> paramMap = new HashMap<>();
		String conditonStr = parameter.getSearch_condition();
		paramMap.put("conditonStr", conditonStr);
		List<Map<String, Object>> seffectPickSort = seffectPickService.seffectPickSort(paramMap);
		msg.data = seffectPickSort;
		msg.errorCode = 0;
		msg.msg = "操作成功";
		logger.info(msg);
		return msg;
	}
	
	// 揽件时效占比(订单揽件量占比)
	@RequestMapping("/getSeffectPicSlowest10City")
	public CustomResponseMsg getSeffectPicSlowest10City(CustomReqParameter parameter) {
		CustomResponseMsg msg = new CustomResponseMsg();
		Map<String, Object> paramMap = new HashMap<>();
		String conditonStr = parameter.getSearch_condition();
		paramMap.put("conditonStr", conditonStr);
		List<Map<String, Object>> seffectPicSlowest10City = seffectPickService.seffectPicSlowest10City(paramMap);
		msg.data = seffectPicSlowest10City;
		msg.errorCode = 0;
		msg.msg = "操作成功";
		logger.info(msg);
		return msg;
	}
	
	// 揽件时效占比(超时占比最高10城市)
	@RequestMapping("/getSeffectPickHighestOvertimeCp10City")
	public CustomResponseMsg getSeffectPickHighestOvertimeCp10City(CustomReqParameter parameter) {
		CustomResponseMsg msg = new CustomResponseMsg();
		Map<String, Object> paramMap = new HashMap<>();
		String conditonStr = parameter.getSearch_condition();
		paramMap.put("conditonStr", conditonStr);
		List<Map<String, Object>> seffectPickHighestOvertimeCp10City = seffectPickService.seffectPickHighestOvertimeCp10City(paramMap);
		msg.data = seffectPickHighestOvertimeCp10City;
		msg.errorCode = 0;
		msg.msg = "操作成功";
		logger.info(msg);
		return msg;
	}
	
	// 揽件时效占比(超时单最高10城市)
	@RequestMapping("/getSeffectPickHighestOvertimeOder10City")
	public CustomResponseMsg getSeffectPickHighestOvertimeOder10City(CustomReqParameter parameter) {
		CustomResponseMsg msg = new CustomResponseMsg();
		Map<String, Object> paramMap = new HashMap<>();
		String conditonStr = parameter.getSearch_condition();
		paramMap.put("conditonStr", conditonStr);
		List<Map<String, Object>> seffectPickHighestOvertimeOder10City = seffectPickService.seffectPickHighestOvertimeOder10City(paramMap);
		msg.data = seffectPickHighestOvertimeOder10City;
		msg.errorCode = 0;
		msg.msg = "操作成功";
		logger.info(msg);
		return msg;
	}
}