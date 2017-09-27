package com.yd.ydbi.business.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yd.ydbi.business.model.SitSucRate;
import com.yd.ydbi.business.service.BasSBusiNodelv7Service;
import com.yd.ydbi.common.response.CustomReqParameter;
import com.yd.ydbi.common.response.CustomResponseMsg;

/**
 * <p>
 * 控制层实现类
 */
@Controller
@ResponseBody
@RequestMapping(value = "/action/bas_busi_rt7")
public class BasSBusiRt7Controller {
	private Logger logger = Logger.getLogger(BasSBusiRt7Controller.class);

	@Autowired
	BasSBusiNodelv7Service basSBusiNodelv7Service;

	// 七日未签收率日周月平均
	@RequestMapping("/getDwmAvgAmount")
	public CustomResponseMsg getDwmAvgAmount(CustomReqParameter parameter) {
		CustomResponseMsg msg = new CustomResponseMsg();
		Map<String, Object> paramMap = new HashMap<>();
		String conditonStr = parameter.getSearch_condition();
		paramMap.put("conditonStr", conditonStr);
		Map<String, Object> DwmAvgAmount = basSBusiNodelv7Service.dwmAvgAmount(paramMap);
		msg.data = DwmAvgAmount;
		msg.errorCode = 0;
		msg.msg = "操作成功";
		logger.info(msg);
		return msg;
	}

	// 七日未签收率趋势图(季度)
	@RequestMapping("/getQuarterlyTrend")
	public CustomResponseMsg getQuarterlyTrend(CustomReqParameter parameter) throws ParseException {
		CustomResponseMsg msg = new CustomResponseMsg();
		Map<String, Object> paramMap = new HashMap<>();
		String conditonStr = parameter.getSearch_condition();
		paramMap.put("conditonStr", conditonStr);
		List<Map<String, Object>> pickQTrend = basSBusiNodelv7Service.cfmRcvRt7QTrend(paramMap);
		msg.data = pickQTrend;
		msg.errorCode = 0;
		msg.msg = "操作成功";
		logger.info(msg);
		return msg;
	}

	// 七日未签收率趋势图(月)
	@RequestMapping("/getMonthTrend")
	public CustomResponseMsg getMonthTrend(CustomReqParameter parameter) throws ParseException {
		CustomResponseMsg msg = new CustomResponseMsg();
		Map<String, Object> paramMap = new HashMap<>();
		String conditonStr = parameter.getSearch_condition();
		paramMap.put("conditonStr", conditonStr);
		List<Map<String, Object>> pickMTrend = basSBusiNodelv7Service.cfmRcvRt7MTrend(paramMap);
		msg.data = pickMTrend;
		msg.errorCode = 0;
		msg.msg = "操作成功";
		logger.info(msg);
		return msg;
	}

	// 七日未签收率趋势图(周)
	@RequestMapping("/getWeekTrend")
	public CustomResponseMsg getWeekTrend(CustomReqParameter parameter) throws ParseException {
		CustomResponseMsg msg = new CustomResponseMsg();
		Map<String, Object> paramMap = new HashMap<>();
		String conditonStr = parameter.getSearch_condition();
		paramMap.put("conditonStr", conditonStr);
		List<Map<String, Object>> pickWTrend = basSBusiNodelv7Service.cfmRcvRt7WTrend(paramMap);
		msg.data = pickWTrend;
		msg.errorCode = 0;
		msg.msg = "操作成功";
		logger.info(msg);
		return msg;
	}

	// 七日未签收率趋势图(日)
	@RequestMapping("/getDayTrend")
	public CustomResponseMsg getDayTrend(CustomReqParameter parameter) throws ParseException {
		CustomResponseMsg msg = new CustomResponseMsg();
		Map<String, Object> paramMap = new HashMap<>();
		String conditonStr = parameter.getSearch_condition();
		paramMap.put("conditonStr", conditonStr);
		List<Map<String, Object>> pickDTrend = basSBusiNodelv7Service.cfmRcvRt7DTrend(paramMap);
		msg.data = pickDTrend;
		msg.errorCode = 0;
		msg.msg = "操作成功";
		logger.info(msg);
		return msg;
	}

	// 7日签收率(机构和成功率)
	@RequestMapping("/getSiteAndSucRate")
	public CustomResponseMsg getSiteAndSucRate(CustomReqParameter parameter) {
		CustomResponseMsg msg = new CustomResponseMsg();
		Map<String, Object> paramMap = new HashMap<>();
		String conditonStr = parameter.getSearch_condition();
		paramMap.put("conditonStr", conditonStr);
		List<SitSucRate> siteAndSucRates = basSBusiNodelv7Service.getSiteAndSucRate(paramMap);
		msg.data = siteAndSucRates;
		msg.errorCode = 0;
		msg.msg = "操作成功";
		logger.info(msg);
		return msg;
	}

	// 7日签收率达标网点数趋势
	@RequestMapping("/selectStandardNetworkTrend")
	public CustomResponseMsg selectStandardNetworkTrend(CustomReqParameter parameter) {
		CustomResponseMsg msg = new CustomResponseMsg();
		Map<String, Object> paramMap = new HashMap<>();
		String conditonStr = parameter.getSearch_condition();
		paramMap.put("conditonStr", conditonStr);

		List<Map<String, Object>> standardNetworkTrends = basSBusiNodelv7Service.getStandardDbcdTrend(paramMap);
		msg.data = standardNetworkTrends;
		msg.errorCode = 0;
		msg.msg = "操作成功";
		logger.info(msg);
		return msg;
	}

}