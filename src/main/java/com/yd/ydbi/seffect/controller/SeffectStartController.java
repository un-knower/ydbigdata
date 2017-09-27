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
import com.yd.ydbi.seffect.service.SeffectStartService;

/**
 * <p>
 * 票件_始发操作时效控制层实现类
 * </p>
 * <p>
 * Class: SeffectController
 * </p>
 */
@Controller
@ResponseBody
@RequestMapping(value = "/action/bas_s_start_bigdata")
public class SeffectStartController {
	private Logger logger = Logger.getLogger(SeffectStartController.class);
	@Autowired
	SeffectStartService seffectStartService;

	// 票件_始发操作时效(包含日环比,周同比)
	@RequestMapping("/getSeffectStart")
	public CustomResponseMsg getSeffectStart(CustomReqParameter parameter) {
		CustomResponseMsg msg = new CustomResponseMsg();
		Map<String, Object> paramMap = new HashMap<>();
		String conditonStr = parameter.getSearch_condition();
		paramMap.put("conditonStr", conditonStr);
		Map<String, Object> seffectStart = seffectStartService.getSeffectStart(paramMap);
		msg.data = seffectStart;
		msg.errorCode = 0;
		msg.msg = "操作成功";
		logger.info(msg);
		return msg;
	}

	// 始发操作时效日月周平均
	@RequestMapping("/getDwmAvgAmount")
	public CustomResponseMsg getDwmAvgAmount(CustomReqParameter parameter) {
		CustomResponseMsg msg = new CustomResponseMsg();
		Map<String, Object> paramMap = new HashMap<>();
		String conditonStr = parameter.getSearch_condition();
		paramMap.put("conditonStr", conditonStr);
		Map<String, Object> DwmAvgAmount = seffectStartService.selectDwmAvgAmount(paramMap);
		msg.data = DwmAvgAmount;
		msg.errorCode = 0;
		msg.msg = "操作成功";
		logger.info(msg);
		return msg;
	}
	
	// 始发操作时效趋势图(日)
	@RequestMapping("/getDayTrend")
	public CustomResponseMsg getDayTrend(CustomReqParameter parameter) throws ParseException {
		CustomResponseMsg msg = new CustomResponseMsg();
		Map<String, Object> paramMap = new HashMap<>();
		String conditonStr = parameter.getSearch_condition();
		paramMap.put("conditonStr", conditonStr);
		List<Map<String, Object>> seffectStartDTrend = seffectStartService.seffectStartDTrend(paramMap);
		msg.data = seffectStartDTrend;
		msg.errorCode = 0;
		msg.msg = "操作成功";
		logger.info(msg);
		return msg;
	}
	
	// 始发操作时效趋势图(周)

	@RequestMapping("/getWeekTrend")
	public CustomResponseMsg getWeekTrend(CustomReqParameter parameter) throws ParseException {
		CustomResponseMsg msg = new CustomResponseMsg();
		Map<String, Object> paramMap = new HashMap<>();
		String conditonStr = parameter.getSearch_condition();
		paramMap.put("conditonStr", conditonStr);
		List<Map<String, Object>> StartWTrend = seffectStartService.seffectStartWTrend(paramMap);
		msg.data = StartWTrend;
		msg.errorCode = 0;
		msg.msg = "操作成功";
		logger.info(msg);
		return msg;
	}
	
	
	// 始发操作时效趋势图(月)
	@RequestMapping("/getMonthTrend")
	public CustomResponseMsg getMonthTrend(CustomReqParameter parameter) throws ParseException {
		CustomResponseMsg msg = new CustomResponseMsg();
		Map<String, Object> paramMap = new HashMap<>();
		String conditonStr = parameter.getSearch_condition();
		paramMap.put("conditonStr", conditonStr);
		List<Map<String, Object>> StartMTrend = seffectStartService.seffectStartMTrend(paramMap);
		msg.data = StartMTrend;
		msg.errorCode = 0;
		msg.msg = "操作成功";
		logger.info(msg);
		return msg;
	}
	
	// 始发操作时效趋势图(季度)
	@RequestMapping("/getQuarterlyTrend")
	public CustomResponseMsg getQuarterlyTrend(CustomReqParameter parameter) throws ParseException {
		CustomResponseMsg msg = new CustomResponseMsg();
		Map<String, Object> paramMap = new HashMap<>();
		String conditonStr = parameter.getSearch_condition();
		paramMap.put("conditonStr", conditonStr);
		List<Map<String, Object>> StartQTrend = seffectStartService.seffectStartQTrend(paramMap);
		msg.data = StartQTrend;
		msg.errorCode = 0;
		msg.msg = "操作成功";
		logger.info(msg);
		return msg;
	}
	
	
	// 始发操作时效占比(订单始发操作量占比)
	@RequestMapping("/getSeffectStartCp")
	public CustomResponseMsg getSeffectStartCp(CustomReqParameter parameter) {
		CustomResponseMsg msg = new CustomResponseMsg();
		Map<String, Object> paramMap = new HashMap<>();
		String conditonStr = parameter.getSearch_condition();
		paramMap.put("conditonStr", conditonStr);
		List<Map<String, Object>>  seffectStartCp = seffectStartService.seffectStartCp(paramMap);
		msg.data = seffectStartCp;
		msg.errorCode = 0;
		msg.msg = "操作成功";
		logger.info(msg);
		return msg;
	}
	
	// 始发操作时效占比(订单始发操作量占比)
	@RequestMapping("/getEffectStartSort")
	public CustomResponseMsg getEffectStartSort(CustomReqParameter parameter) {
		CustomResponseMsg msg = new CustomResponseMsg();
		Map<String, Object> paramMap = new HashMap<>();
		String conditonStr = parameter.getSearch_condition();
		paramMap.put("conditonStr", conditonStr);
		List<Map<String, Object>> seffectStartSort = seffectStartService.seffectStartSort(paramMap);
		msg.data = seffectStartSort;
		msg.errorCode = 0;
		msg.msg = "操作成功";
		logger.info(msg);
		return msg;
	}
	
	// 始发操作时效占比(订单始发操作量占比)
	@RequestMapping("/getSeffectPicSlowest10City")
	public CustomResponseMsg getSeffectPicSlowest10City(CustomReqParameter parameter) {
		CustomResponseMsg msg = new CustomResponseMsg();
		Map<String, Object> paramMap = new HashMap<>();
		String conditonStr = parameter.getSearch_condition();
		paramMap.put("conditonStr", conditonStr);
		List<Map<String, Object>> seffectPicSlowest10City = seffectStartService.seffectPicSlowest10City(paramMap);
		msg.data = seffectPicSlowest10City;
		msg.errorCode = 0;
		msg.msg = "操作成功";
		logger.info(msg);
		return msg;
	}
	
	// 始发操作时效占比(超时占比最高10城市)
	@RequestMapping("/getSeffectStartHighestOvertimeCp10City")
	public CustomResponseMsg getSeffectStartHighestOvertimeCp10City(CustomReqParameter parameter) {
		CustomResponseMsg msg = new CustomResponseMsg();
		Map<String, Object> paramMap = new HashMap<>();
		String conditonStr = parameter.getSearch_condition();
		paramMap.put("conditonStr", conditonStr);
		List<Map<String, Object>> seffectStartHighestOvertimeCp10City = seffectStartService.seffectStartHighestOvertimeCp10City(paramMap);
		msg.data = seffectStartHighestOvertimeCp10City;
		msg.errorCode = 0;
		msg.msg = "操作成功";
		logger.info(msg);
		return msg;
	}
	
	// 始发操作时效占比(超时单最高10城市)
	@RequestMapping("/getSeffectStartHighestOvertimeOder10City")
	public CustomResponseMsg getSeffectStartHighestOvertimeOder10City(CustomReqParameter parameter) {
		CustomResponseMsg msg = new CustomResponseMsg();
		Map<String, Object> paramMap = new HashMap<>();
		String conditonStr = parameter.getSearch_condition();
		paramMap.put("conditonStr", conditonStr);
		List<Map<String, Object>> seffectStartHighestOvertimeOder10City = seffectStartService.seffectStartHighestOvertimeOder10City(paramMap);
		msg.data = seffectStartHighestOvertimeOder10City;
		msg.errorCode = 0;
		msg.msg = "操作成功";
		logger.info(msg);
		return msg;
	}
}