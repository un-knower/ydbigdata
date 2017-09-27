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
import com.yd.ydbi.seffect.service.SeffectEndService;

/**
 * <p>
 * 票件_到达操作时效控制层实现类
 * </p>
 * <p>
 * Class: SeffectController
 * </p>
 */
@Controller
@ResponseBody
@RequestMapping(value = "/action/bas_s_end_bigdata")
public class SeffectEndController {
	private Logger logger = Logger.getLogger(SeffectEndController.class);
	@Autowired
	SeffectEndService seffectEndService;

	// 票件_到达操作时效(包含日环比,周同比)
	@RequestMapping("/getSeffectEnd")
	public CustomResponseMsg getSeffectEnd(CustomReqParameter parameter) {
		CustomResponseMsg msg = new CustomResponseMsg();
		Map<String, Object> paramMap = new HashMap<>();
		String conditonStr = parameter.getSearch_condition();
		paramMap.put("conditonStr", conditonStr);
		Map<String, Object> seffectEnd = seffectEndService.getSeffectEnd(paramMap);
		msg.data = seffectEnd;
		msg.errorCode = 0;
		msg.msg = "操作成功";
		logger.info(msg);
		return msg;
	}

	// 到达操作时效日月周平均
	@RequestMapping("/getDwmAvgAmount")
	public CustomResponseMsg getDwmAvgAmount(CustomReqParameter parameter) {
		CustomResponseMsg msg = new CustomResponseMsg();
		Map<String, Object> paramMap = new HashMap<>();
		String conditonStr = parameter.getSearch_condition();
		paramMap.put("conditonStr", conditonStr);
		Map<String, Object> DwmAvgAmount = seffectEndService.selectDwmAvgAmount(paramMap);
		msg.data = DwmAvgAmount;
		msg.errorCode = 0;
		msg.msg = "操作成功";
		logger.info(msg);
		return msg;
	}
	
	// 到达操作时效趋势图(日)
	@RequestMapping("/getDayTrend")
	public CustomResponseMsg getDayTrend(CustomReqParameter parameter) throws ParseException {
		CustomResponseMsg msg = new CustomResponseMsg();
		Map<String, Object> paramMap = new HashMap<>();
		String conditonStr = parameter.getSearch_condition();
		paramMap.put("conditonStr", conditonStr);
		List<Map<String, Object>> seffectEndDTrend = seffectEndService.seffectEndDTrend(paramMap);
		msg.data = seffectEndDTrend;
		msg.errorCode = 0;
		msg.msg = "操作成功";
		logger.info(msg);
		return msg;
	}
	
	// 到达操作时效趋势图(周)

	@RequestMapping("/getWeekTrend")
	public CustomResponseMsg getWeekTrend(CustomReqParameter parameter) throws ParseException {
		CustomResponseMsg msg = new CustomResponseMsg();
		Map<String, Object> paramMap = new HashMap<>();
		String conditonStr = parameter.getSearch_condition();
		paramMap.put("conditonStr", conditonStr);
		List<Map<String, Object>> EndWTrend = seffectEndService.seffectEndWTrend(paramMap);
		msg.data = EndWTrend;
		msg.errorCode = 0;
		msg.msg = "操作成功";
		logger.info(msg);
		return msg;
	}
	
	
	// 到达操作时效趋势图(月)
	@RequestMapping("/getMonthTrend")
	public CustomResponseMsg getMonthTrend(CustomReqParameter parameter) throws ParseException {
		CustomResponseMsg msg = new CustomResponseMsg();
		Map<String, Object> paramMap = new HashMap<>();
		String conditonStr = parameter.getSearch_condition();
		paramMap.put("conditonStr", conditonStr);
		List<Map<String, Object>> EndMTrend = seffectEndService.seffectEndMTrend(paramMap);
		msg.data = EndMTrend;
		msg.errorCode = 0;
		msg.msg = "操作成功";
		logger.info(msg);
		return msg;
	}
	
	// 到达操作时效趋势图(季度)
	@RequestMapping("/getQuarterlyTrend")
	public CustomResponseMsg getQuarterlyTrend(CustomReqParameter parameter) throws ParseException {
		CustomResponseMsg msg = new CustomResponseMsg();
		Map<String, Object> paramMap = new HashMap<>();
		String conditonStr = parameter.getSearch_condition();
		paramMap.put("conditonStr", conditonStr);
		List<Map<String, Object>> EndQTrend = seffectEndService.seffectEndQTrend(paramMap);
		msg.data = EndQTrend;
		msg.errorCode = 0;
		msg.msg = "操作成功";
		logger.info(msg);
		return msg;
	}
	
	
	// 到达操作时效占比(订单到达操作量占比)
	@RequestMapping("/getSeffectEndCp")
	public CustomResponseMsg getSeffectEndCp(CustomReqParameter parameter) {
		CustomResponseMsg msg = new CustomResponseMsg();
		Map<String, Object> paramMap = new HashMap<>();
		String conditonStr = parameter.getSearch_condition();
		paramMap.put("conditonStr", conditonStr);
		List<Map<String, Object>>  seffectEndCp = seffectEndService.seffectEndCp(paramMap);
		msg.data = seffectEndCp;
		msg.errorCode = 0;
		msg.msg = "操作成功";
		logger.info(msg);
		return msg;
	}
	
	// 到达操作时效占比(订单到达操作量占比)
	@RequestMapping("/getEffectEndSort")
	public CustomResponseMsg getEffectEndSort(CustomReqParameter parameter) {
		CustomResponseMsg msg = new CustomResponseMsg();
		Map<String, Object> paramMap = new HashMap<>();
		String conditonStr = parameter.getSearch_condition();
		paramMap.put("conditonStr", conditonStr);
		List<Map<String, Object>> seffectEndSort = seffectEndService.seffectEndSort(paramMap);
		msg.data = seffectEndSort;
		msg.errorCode = 0;
		msg.msg = "操作成功";
		logger.info(msg);
		return msg;
	}
	
	// 到达操作时效占比(订单到达操作量占比)
	@RequestMapping("/getSeffectPicSlowest10City")
	public CustomResponseMsg getSeffectPicSlowest10City(CustomReqParameter parameter) {
		CustomResponseMsg msg = new CustomResponseMsg();
		Map<String, Object> paramMap = new HashMap<>();
		String conditonStr = parameter.getSearch_condition();
		paramMap.put("conditonStr", conditonStr);
		List<Map<String, Object>> seffectPicSlowest10City = seffectEndService.seffectPicSlowest10City(paramMap);
		msg.data = seffectPicSlowest10City;
		msg.errorCode = 0;
		msg.msg = "操作成功";
		logger.info(msg);
		return msg;
	}
	
	// 到达操作时效占比(超时占比最高10城市)
	@RequestMapping("/getSeffectEndHighestOvertimeCp10City")
	public CustomResponseMsg getSeffectEndHighestOvertimeCp10City(CustomReqParameter parameter) {
		CustomResponseMsg msg = new CustomResponseMsg();
		Map<String, Object> paramMap = new HashMap<>();
		String conditonStr = parameter.getSearch_condition();
		paramMap.put("conditonStr", conditonStr);
		List<Map<String, Object>> seffectEndHighestOvertimeCp10City = seffectEndService.seffectEndHighestOvertimeCp10City(paramMap);
		msg.data = seffectEndHighestOvertimeCp10City;
		msg.errorCode = 0;
		msg.msg = "操作成功";
		logger.info(msg);
		return msg;
	}
	
	// 到达操作时效占比(超时单最高10城市)
	@RequestMapping("/getSeffectEndHighestOvertimeOder10City")
	public CustomResponseMsg getSeffectEndHighestOvertimeOder10City(CustomReqParameter parameter) {
		CustomResponseMsg msg = new CustomResponseMsg();
		Map<String, Object> paramMap = new HashMap<>();
		String conditonStr = parameter.getSearch_condition();
		paramMap.put("conditonStr", conditonStr);
		List<Map<String, Object>> seffectEndHighestOvertimeOder10City = seffectEndService.seffectEndHighestOvertimeOder10City(paramMap);
		msg.data = seffectEndHighestOvertimeOder10City;
		msg.errorCode = 0;
		msg.msg = "操作成功";
		logger.info(msg);
		return msg;
	}
}