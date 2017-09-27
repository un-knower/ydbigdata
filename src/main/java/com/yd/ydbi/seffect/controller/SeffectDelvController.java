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
import com.yd.ydbi.seffect.service.SeffectDelvService;

/**
 * <p>
 * 票件_签收时效控制层实现类
 * </p>
 * <p>
 * Class: SeffectController
 * </p>
 */
@Controller
@ResponseBody
@RequestMapping(value = "/action/bas_s_delv_bigdata")
public class SeffectDelvController {
	private Logger logger = Logger.getLogger(SeffectDelvController.class);
	@Autowired
	SeffectDelvService seffectDelvService;

	// 票件_签收时效(包含日环比,周同比)
	@RequestMapping("/getSeffectDelv")
	public CustomResponseMsg getSeffectDelv(CustomReqParameter parameter) {
		CustomResponseMsg msg = new CustomResponseMsg();
		Map<String, Object> paramMap = new HashMap<>();
		String conditonStr = parameter.getSearch_condition();
		paramMap.put("conditonStr", conditonStr);
		Map<String, Object> seffectDelv = seffectDelvService.getSeffectDelv(paramMap);
		msg.data = seffectDelv;
		msg.errorCode = 0;
		msg.msg = "操作成功";
		logger.info(msg);
		return msg;
	}

	// 签收时效日月周平均
	@RequestMapping("/getDwmAvgAmount")
	public CustomResponseMsg getDwmAvgAmount(CustomReqParameter parameter) {
		CustomResponseMsg msg = new CustomResponseMsg();
		Map<String, Object> paramMap = new HashMap<>();
		String conditonStr = parameter.getSearch_condition();
		paramMap.put("conditonStr", conditonStr);
		Map<String, Object> DwmAvgAmount = seffectDelvService.selectDwmAvgAmount(paramMap);
		msg.data = DwmAvgAmount;
		msg.errorCode = 0;
		msg.msg = "操作成功";
		logger.info(msg);
		return msg;
	}
	
	// 签收时效趋势图(日)
	@RequestMapping("/getDayTrend")
	public CustomResponseMsg getDayTrend(CustomReqParameter parameter) throws ParseException {
		CustomResponseMsg msg = new CustomResponseMsg();
		Map<String, Object> paramMap = new HashMap<>();
		String conditonStr = parameter.getSearch_condition();
		paramMap.put("conditonStr", conditonStr);
		List<Map<String, Object>> seffectDelvDTrend = seffectDelvService.seffectDelvDTrend(paramMap);
		msg.data = seffectDelvDTrend;
		msg.errorCode = 0;
		msg.msg = "操作成功";
		logger.info(msg);
		return msg;
	}
	
	// 签收时效趋势图(周)

	@RequestMapping("/getWeekTrend")
	public CustomResponseMsg getWeekTrend(CustomReqParameter parameter) throws ParseException {
		CustomResponseMsg msg = new CustomResponseMsg();
		Map<String, Object> paramMap = new HashMap<>();
		String conditonStr = parameter.getSearch_condition();
		paramMap.put("conditonStr", conditonStr);
		List<Map<String, Object>> DelvWTrend = seffectDelvService.seffectDelvWTrend(paramMap);
		msg.data = DelvWTrend;
		msg.errorCode = 0;
		msg.msg = "操作成功";
		logger.info(msg);
		return msg;
	}
	
	
	// 签收时效趋势图(月)
	@RequestMapping("/getMonthTrend")
	public CustomResponseMsg getMonthTrend(CustomReqParameter parameter) throws ParseException {
		CustomResponseMsg msg = new CustomResponseMsg();
		Map<String, Object> paramMap = new HashMap<>();
		String conditonStr = parameter.getSearch_condition();
		paramMap.put("conditonStr", conditonStr);
		List<Map<String, Object>> DelvMTrend = seffectDelvService.seffectDelvMTrend(paramMap);
		msg.data = DelvMTrend;
		msg.errorCode = 0;
		msg.msg = "操作成功";
		logger.info(msg);
		return msg;
	}
	
	// 签收时效趋势图(季度)
	@RequestMapping("/getQuarterlyTrend")
	public CustomResponseMsg getQuarterlyTrend(CustomReqParameter parameter) throws ParseException {
		CustomResponseMsg msg = new CustomResponseMsg();
		Map<String, Object> paramMap = new HashMap<>();
		String conditonStr = parameter.getSearch_condition();
		paramMap.put("conditonStr", conditonStr);
		List<Map<String, Object>> DelvQTrend = seffectDelvService.seffectDelvQTrend(paramMap);
		msg.data = DelvQTrend;
		msg.errorCode = 0;
		msg.msg = "操作成功";
		logger.info(msg);
		return msg;
	}
	
	
	// 签收时效占比(订单签收量占比)
	@RequestMapping("/getSeffectDelvCp")
	public CustomResponseMsg getSeffectDelvCp(CustomReqParameter parameter) {
		CustomResponseMsg msg = new CustomResponseMsg();
		Map<String, Object> paramMap = new HashMap<>();
		String conditonStr = parameter.getSearch_condition();
		paramMap.put("conditonStr", conditonStr);
		List<Map<String, Object>>  seffectDelvCp = seffectDelvService.seffectDelvCp(paramMap);
		msg.data = seffectDelvCp;
		msg.errorCode = 0;
		msg.msg = "操作成功";
		logger.info(msg);
		return msg;
	}
	
	// 签收时效占比(订单签收量占比)
	@RequestMapping("/getEffectDelvSort")
	public CustomResponseMsg getEffectDelvSort(CustomReqParameter parameter) {
		CustomResponseMsg msg = new CustomResponseMsg();
		Map<String, Object> paramMap = new HashMap<>();
		String conditonStr = parameter.getSearch_condition();
		paramMap.put("conditonStr", conditonStr);
		List<Map<String, Object>> seffectDelvSort = seffectDelvService.seffectDelvSort(paramMap);
		msg.data = seffectDelvSort;
		msg.errorCode = 0;
		msg.msg = "操作成功";
		logger.info(msg);
		return msg;
	}
	
	// 签收时效占比(订单签收量占比)
	@RequestMapping("/getSeffectPicSlowest10City")
	public CustomResponseMsg getSeffectPicSlowest10City(CustomReqParameter parameter) {
		CustomResponseMsg msg = new CustomResponseMsg();
		Map<String, Object> paramMap = new HashMap<>();
		String conditonStr = parameter.getSearch_condition();
		paramMap.put("conditonStr", conditonStr);
		List<Map<String, Object>> seffectPicSlowest10City = seffectDelvService.seffectPicSlowest10City(paramMap);
		msg.data = seffectPicSlowest10City;
		msg.errorCode = 0;
		msg.msg = "操作成功";
		logger.info(msg);
		return msg;
	}
	
	// 签收时效占比(超时占比最高10城市)
	@RequestMapping("/getSeffectDelvHighestOvertimeCp10City")
	public CustomResponseMsg getSeffectDelvHighestOvertimeCp10City(CustomReqParameter parameter) {
		CustomResponseMsg msg = new CustomResponseMsg();
		Map<String, Object> paramMap = new HashMap<>();
		String conditonStr = parameter.getSearch_condition();
		paramMap.put("conditonStr", conditonStr);
		List<Map<String, Object>> seffectDelvHighestOvertimeCp10City = seffectDelvService.seffectDelvHighestOvertimeCp10City(paramMap);
		msg.data = seffectDelvHighestOvertimeCp10City;
		msg.errorCode = 0;
		msg.msg = "操作成功";
		logger.info(msg);
		return msg;
	}
	
	// 签收时效占比(超时单最高10城市)
	@RequestMapping("/getSeffectDelvHighestOvertimeOder10City")
	public CustomResponseMsg getSeffectDelvHighestOvertimeOder10City(CustomReqParameter parameter) {
		CustomResponseMsg msg = new CustomResponseMsg();
		Map<String, Object> paramMap = new HashMap<>();
		String conditonStr = parameter.getSearch_condition();
		paramMap.put("conditonStr", conditonStr);
		List<Map<String, Object>> seffectDelvHighestOvertimeOder10City = seffectDelvService.seffectDelvHighestOvertimeOder10City(paramMap);
		msg.data = seffectDelvHighestOvertimeOder10City;
		msg.errorCode = 0;
		msg.msg = "操作成功";
		logger.info(msg);
		return msg;
	}
}