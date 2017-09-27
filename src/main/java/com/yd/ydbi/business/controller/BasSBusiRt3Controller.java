package com.yd.ydbi.business.controller;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yd.ydbi.business.model.SitSucRate;
import com.yd.ydbi.business.service.BasSBusiDelv3Service;
import com.yd.ydbi.business.service.BasSBusiNodelv7Service;
import com.yd.ydbi.common.response.CustomReqParameter;
import com.yd.ydbi.common.response.CustomResponseMsg;

/**
 * <p>
 * 控制层实现类
 * </p>
 * <p>
 * Class: BasSBusiDelvController
 * </p>
 * 
 * @since 2017-07-12 15:25:57
 */
@Controller
@ResponseBody
@RequestMapping(value = "/action/bas_busi_rt3")
public class BasSBusiRt3Controller {
	private Logger logger = Logger.getLogger(BasSBusiRt3Controller.class);

	@Autowired
	BasSBusiDelv3Service basSBusiDelv3Service;

	@Autowired
	BasSBusiNodelv7Service basSBusiNodelv7Service;

	// 三日签收率日周月平均
	@RequestMapping("/getDwmAvgAmount")
	public CustomResponseMsg getDwmAvgAmount(CustomReqParameter parameter) {
		CustomResponseMsg msg = new CustomResponseMsg();
		Map<String, Object> paramMap = new HashMap<>();
		String conditonStr = parameter.getSearch_condition();
		paramMap.put("conditonStr", conditonStr);
		Map<String, Object> DwmAvgAmount = basSBusiDelv3Service.dwmAvgAmount(paramMap);
		msg.data = DwmAvgAmount;
		msg.errorCode = 0;
		msg.msg = "操作成功";
		logger.info(msg);
		return msg;
	}

	// 三日签收率趋势图(季度)
	@RequestMapping("/getQuarterlyTrend")
	public CustomResponseMsg getQuarterlyTrend(CustomReqParameter parameter) throws ParseException {
		CustomResponseMsg msg = new CustomResponseMsg();
		Map<String, Object> paramMap = new HashMap<>();
		String conditonStr = parameter.getSearch_condition();
		paramMap.put("conditonStr", conditonStr);
		List<Map<String, Object>> pickQTrend = basSBusiDelv3Service.cfmRcvRt3QTrend(paramMap);
		msg.data = pickQTrend;
		msg.errorCode = 0;
		msg.msg = "操作成功";
		logger.info(msg);
		return msg;
	}

	// 三日签收率趋势图(月)
	@RequestMapping("/getMonthTrend")
	public CustomResponseMsg getMonthTrend(CustomReqParameter parameter) throws ParseException {
		CustomResponseMsg msg = new CustomResponseMsg();
		Map<String, Object> paramMap = new HashMap<>();
		String conditonStr = parameter.getSearch_condition();
		paramMap.put("conditonStr", conditonStr);
		List<Map<String, Object>> pickMTrend = basSBusiDelv3Service.cfmRcvRt3MTrend(paramMap);
		msg.data = pickMTrend;
		msg.errorCode = 0;
		msg.msg = "操作成功";
		logger.info(msg);
		return msg;
	}

	// 三日签收率趋势图(周)
	@RequestMapping("/getWeekTrend")
	public CustomResponseMsg getWeekTrend(CustomReqParameter parameter) throws ParseException {
		CustomResponseMsg msg = new CustomResponseMsg();
		Map<String, Object> paramMap = new HashMap<>();
		String conditonStr = parameter.getSearch_condition();
		paramMap.put("conditonStr", conditonStr);
		List<Map<String, Object>> pickWTrend = basSBusiDelv3Service.cfmRcvRt3WTrend(paramMap);
		msg.data = pickWTrend;
		msg.errorCode = 0;
		msg.msg = "操作成功";
		logger.info(msg);
		return msg;
	}

	// 三日签收率趋势图(日)
	@RequestMapping("/getDayTrend")
	public CustomResponseMsg getDayTrend(CustomReqParameter parameter) throws ParseException {
		CustomResponseMsg msg = new CustomResponseMsg();
		Map<String, Object> paramMap = new HashMap<>();
		String conditonStr = parameter.getSearch_condition();
		paramMap.put("conditonStr", conditonStr);
		List<Map<String, Object>> pickDTrend = basSBusiDelv3Service.cfmRcvRt3DTrend(paramMap);
		msg.data = pickDTrend;
		msg.errorCode = 0;
		msg.msg = "操作成功";
		logger.info(msg);
		return msg;
	}

	// 3日签收率(机构和成功率)
	@RequestMapping("/getSiteAndSucRate")
	public CustomResponseMsg getSiteAndSucRate(CustomReqParameter parameter) {
		CustomResponseMsg msg = new CustomResponseMsg();
		Map<String, Object> paramMap = new HashMap<>();
		String conditonStr = parameter.getSearch_condition();
		paramMap.put("conditonStr", conditonStr);
		List<SitSucRate> siteAndSucRates = basSBusiDelv3Service.getSiteAndSucRate(paramMap);
		msg.data = siteAndSucRates;
		msg.errorCode = 0;
		msg.msg = "操作成功";
		logger.info(msg);
		return msg;
	}

	// 3日签收率达标网点数趋势
	@RequestMapping("/selectStandardNetworkTrend")
	public CustomResponseMsg selectStandardNetworkTrend(CustomReqParameter parameter) {
		CustomResponseMsg msg = new CustomResponseMsg();
		Map<String, Object> paramMap = new HashMap<>();
		String conditonStr = parameter.getSearch_condition();
		paramMap.put("conditonStr", conditonStr);

		List<Map<String, Object>> standardNetworkTrends = basSBusiDelv3Service.getStandardDbcdTrend(paramMap);
		msg.data = standardNetworkTrends;
		msg.errorCode = 0;
		msg.msg = "操作成功";
		logger.info(msg);
		return msg;
	}

}