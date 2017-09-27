package com.yd.ydbi.business.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yd.common.exception.CIPRuntimeException;
import com.yd.common.exception.CIPServiceException;
import com.yd.common.runtime.CIPErrorCode;
import com.yd.common.utils.JSONUtils;
import com.yd.ydbi.business.model.BasSBusiDelv;
import com.yd.ydbi.business.model.BasSBusiPick;
import com.yd.ydbi.business.model.MonthGrowthRate;
import com.yd.ydbi.business.model.MonthVolatility;
import com.yd.ydbi.business.model.SitSucRate;
import com.yd.ydbi.business.model.SuccessRateSingleDay;
import com.yd.ydbi.business.service.BasSBusiDelv3Service;
import com.yd.ydbi.business.service.BasSBusiDelvService;
import com.yd.ydbi.business.service.BasSBusiDelvTmp0Service;
import com.yd.ydbi.business.service.BasSBusiNodelv7Service;
import com.yd.ydbi.business.service.BasSBusiPickService;
import com.yd.ydbi.business.service.BasSBusiSndService;
import com.yd.ydbi.common.response.CustomReqParameter;
import com.yd.ydbi.common.response.CustomResponseMsg;
import com.yd.ydbi.utils.DateUtils;

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
@RequestMapping(value = "/action/bas_busi")
public class BasSBusiController {
	private Logger logger = Logger.getLogger(BasSBusiController.class);
	@Autowired
	BasSBusiDelvService basSBusiDelvService;

	@Autowired
	BasSBusiPickService basSBusiPickService;

	@Autowired
	BasSBusiSndService basSBusiSndService;

	@Autowired
	BasSBusiDelvTmp0Service basSBusiDelvTmp0Service;

	@Autowired
	BasSBusiDelv3Service basSBusiDelv3Service;

	@Autowired
	BasSBusiNodelv7Service basSBusiNodelv7Service;

	// 业务量-首页
	@RequestMapping("/getBusinessVolume")
	public CustomResponseMsg getDelvByModel(CustomReqParameter parameter) {
		CustomResponseMsg msg = new CustomResponseMsg();
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> paramMap = new HashMap<>();
		String conditonStr = parameter.getSearch_condition();
		paramMap.put("conditonStr", conditonStr);
		Map<String, Object> basSBusiPick = basSBusiPickService.selectTookAQuantity(paramMap);
		Map<String, Object> sendAQuantity = basSBusiSndService.selectSendAQuantity(paramMap);
		Map<String, Object> signAQuantity = basSBusiDelvService.selectSignAQuantity(paramMap);
		Map<String, Object> basSBusiDelvTmp0 = basSBusiDelvTmp0Service.selectSentSuccessRateinSingleDay(paramMap);
		Map<String, Object> basSBusiDelv3 = basSBusiDelv3Service.selectThreeDayToSignForRate(paramMap);
		Map<String, Object> basSBusiNoDelv7 = basSBusiNodelv7Service.selectSevenDaysWithoutReceiptRate(paramMap);

		map.put("basSBusiPick", basSBusiPick);
		map.put("sendAQuantity", sendAQuantity);
		map.put("signAQuantity", signAQuantity);
		map.put("basSBusiDelvTmp0", basSBusiDelvTmp0);
		map.put("basSBusiDelv3", basSBusiDelv3);
		map.put("basSBusiNoDelv7", basSBusiNoDelv7);

		msg.data = map;
		msg.errorCode = 0;
		msg.msg = "操作成功";
		logger.info(msg);
		return msg;
	}

	// 业务量-揽件量日环比周同比
	@RequestMapping("/getPickAndDayAndWeek")
	public CustomResponseMsg getPickAndDayAndWeek(CustomReqParameter parameter) {
		CustomResponseMsg msg = new CustomResponseMsg();
		Map<String, Object> paramMap = new HashMap<>();
		String conditonStr = parameter.getSearch_condition();
		paramMap.put("conditonStr", conditonStr);
		Map<String, Object> pickAndDayAndWeek = basSBusiPickService.selectTookAQuantity(paramMap);
		msg.data = pickAndDayAndWeek;
		msg.errorCode = 0;
		msg.msg = "操作成功";
		logger.info(msg);
		return msg;
	}

	// 业务量-派件量日环比周同比
	@RequestMapping("/getSndAndDayAndWeek")
	public CustomResponseMsg getSndAndDayAndWeek(CustomReqParameter parameter) {
		CustomResponseMsg msg = new CustomResponseMsg();
		Map<String, Object> paramMap = new HashMap<>();
		String conditonStr = parameter.getSearch_condition();
		paramMap.put("conditonStr", conditonStr);
		Map<String, Object> sndAndDayAndWeek = basSBusiSndService.selectSendAQuantity(paramMap);
		msg.data = sndAndDayAndWeek;
		msg.errorCode = 0;
		msg.msg = "操作成功";
		logger.info(msg);
		return msg;
	}

	// 业务量-签收量日环比周同比
	@RequestMapping("/getSignAndDayAndWeek")
	public CustomResponseMsg getSignAndDayAndWeek(CustomReqParameter parameter) {
		CustomResponseMsg msg = new CustomResponseMsg();
		Map<String, Object> paramMap = new HashMap<>();
		String conditonStr = parameter.getSearch_condition();
		paramMap.put("conditonStr", conditonStr);
		Map<String, Object> signAndDayAndWeek = basSBusiDelvService.selectSignAQuantity(paramMap);
		msg.data = signAndDayAndWeek;
		msg.errorCode = 0;
		msg.msg = "操作成功";
		logger.info(msg);
		return msg;
	}

	// 业务量-单日签收率日环比周同比
	@RequestMapping("/basSBusiDelvTmp0")
	public CustomResponseMsg basSBusiDelvTmp0(CustomReqParameter parameter) {
		CustomResponseMsg msg = new CustomResponseMsg();
		Map<String, Object> paramMap = new HashMap<>();
		String conditonStr = parameter.getSearch_condition();
		paramMap.put("conditonStr", conditonStr);
		Map<String, Object> basSBusiDelvTmp0 = basSBusiDelvTmp0Service.selectSentSuccessRateinSingleDay(paramMap);
		msg.data = basSBusiDelvTmp0;
		msg.errorCode = 0;
		msg.msg = "操作成功";
		logger.info(msg);
		return msg;
	}

	// 业务量-三日签收率日环比周同比
	@RequestMapping("/basSBusiDelv3")
	public CustomResponseMsg basSBusiDelv3(CustomReqParameter parameter) {
		CustomResponseMsg msg = new CustomResponseMsg();
		Map<String, Object> paramMap = new HashMap<>();
		String conditonStr = parameter.getSearch_condition();
		paramMap.put("conditonStr", conditonStr);
		Map<String, Object> basSBusiDelv3 = basSBusiDelv3Service.selectThreeDayToSignForRate(paramMap);
		msg.data = basSBusiDelv3;
		msg.errorCode = 0;
		msg.msg = "操作成功";
		logger.info(msg);
		return msg;
	}

	// 业务量-七日未签收率日环比周同比
	@RequestMapping("/basSBusiNoDelv7")
	public CustomResponseMsg basSBusiNoDelv7(CustomReqParameter parameter) {
		CustomResponseMsg msg = new CustomResponseMsg();
		Map<String, Object> paramMap = new HashMap<>();
		String conditonStr = parameter.getSearch_condition();
		paramMap.put("conditonStr", conditonStr);
		Map<String, Object> basSBusiNoDelv7 = basSBusiNodelv7Service.selectSevenDaysWithoutReceiptRate(paramMap);
		msg.data = basSBusiNoDelv7;
		msg.errorCode = 0;
		msg.msg = "操作成功";
		logger.info(msg);
		return msg;
	}

	// 揽件量日周月平均
	@RequestMapping("/getDwmAvgAmount")
	public CustomResponseMsg getDwmAvgAmount(CustomReqParameter parameter) {
		CustomResponseMsg msg = new CustomResponseMsg();
		Map<String, Object> paramMap = new HashMap<>();
		String conditonStr = parameter.getSearch_condition();
		paramMap.put("conditonStr", conditonStr);
		Map<String, Object> DwmAvgAmount = basSBusiPickService.selectDwmAvgAmount(paramMap);
		msg.data = DwmAvgAmount;
		msg.errorCode = 0;
		msg.msg = "操作成功";
		logger.info(msg);
		return msg;
	}

	// 揽件量趋势图(季度)
	@RequestMapping("/getQuarterlyTrend")
	public CustomResponseMsg getQuarterlyTrend(CustomReqParameter parameter) throws ParseException {
		CustomResponseMsg msg = new CustomResponseMsg();
		Map<String, Object> paramMap = new HashMap<>();
		String conditonStr = parameter.getSearch_condition();
		paramMap.put("conditonStr", conditonStr);
		List<Map<String, Object>> pickQTrend = basSBusiPickService.pickQTrend(paramMap);
		msg.data = pickQTrend;
		msg.errorCode = 0;
		msg.msg = "操作成功";
		logger.info(msg);
		return msg;
	}

	// 揽件量趋势图(月)
	@RequestMapping("/getMonthTrend")
	public CustomResponseMsg getMonthTrend(CustomReqParameter parameter) throws ParseException {
		CustomResponseMsg msg = new CustomResponseMsg();
		Map<String, Object> paramMap = new HashMap<>();
		String conditonStr = parameter.getSearch_condition();
		paramMap.put("conditonStr", conditonStr);
		List<Map<String, Object>> pickMTrend = basSBusiPickService.pickMTrend(paramMap);
		msg.data = pickMTrend;
		msg.errorCode = 0;
		msg.msg = "操作成功";
		logger.info(msg);
		return msg;
	}

	// 揽件量趋势图(周)
	@RequestMapping("/getWeekTrend")
	public CustomResponseMsg getWeekTrend(CustomReqParameter parameter) throws ParseException {
		CustomResponseMsg msg = new CustomResponseMsg();
		Map<String, Object> paramMap = new HashMap<>();
		String conditonStr = parameter.getSearch_condition();
		paramMap.put("conditonStr", conditonStr);
		List<Map<String, Object>> pickWTrend = basSBusiPickService.pickWTrend(paramMap);
		msg.data = pickWTrend;
		msg.errorCode = 0;
		msg.msg = "操作成功";
		logger.info(msg);
		return msg;
	}

	// 揽件量趋势图(日)
	@RequestMapping("/getDayTrend")
	public CustomResponseMsg getDayTrend(CustomReqParameter parameter) throws ParseException {
		CustomResponseMsg msg = new CustomResponseMsg();
		Map<String, Object> paramMap = new HashMap<>();
		String conditonStr = parameter.getSearch_condition();
		paramMap.put("conditonStr", conditonStr);
		List<Map<String, Object>> pickDTrend = basSBusiPickService.pickDTrend(paramMap);
		msg.data = pickDTrend;
		msg.errorCode = 0;
		msg.msg = "操作成功";
		logger.info(msg);
		return msg;
	}

	// 派件量日周月平均
	@RequestMapping("/getDwmAvgAmountForSnd")
	public CustomResponseMsg getDwmAvgAmountForSnd(CustomReqParameter parameter) {
		CustomResponseMsg msg = new CustomResponseMsg();
		Map<String, Object> paramMap = new HashMap<>();
		String conditonStr = parameter.getSearch_condition();
		paramMap.put("conditonStr", conditonStr);
		Map<String, Object> dwmAvgAmountForSnd = basSBusiSndService.selectDwmAvgAmountForSnd(paramMap);
		msg.data = dwmAvgAmountForSnd;
		msg.errorCode = 0;
		msg.msg = "操作成功";
		logger.info(msg);
		return msg;
	}

	// 派件量趋势图(季度)
	@RequestMapping("/getQuarterlyTrendForSnd")
	public CustomResponseMsg getQuarterlyTrendForSnd(CustomReqParameter parameter) throws ParseException {
		CustomResponseMsg msg = new CustomResponseMsg();
		Map<String, Object> paramMap = new HashMap<>();
		String conditonStr = parameter.getSearch_condition();
		paramMap.put("conditonStr", conditonStr);
		List<Map<String, Object>> delvQTrend = basSBusiSndService.delvQTrend(paramMap);
		msg.data = delvQTrend;
		msg.errorCode = 0;
		msg.msg = "操作成功";
		logger.info(msg);
		return msg;
	}

	// 派件量趋势图(月)
	@RequestMapping("/getMonthTrendForSnd")
	public CustomResponseMsg getMonthTrendForSnd(CustomReqParameter parameter) throws ParseException {
		CustomResponseMsg msg = new CustomResponseMsg();
		Map<String, Object> paramMap = new HashMap<>();
		String conditonStr = parameter.getSearch_condition();
		paramMap.put("conditonStr", conditonStr);
		List<Map<String, Object>> delvMTrend = basSBusiSndService.delvMTrend(paramMap);
		msg.data = delvMTrend;
		msg.errorCode = 0;
		msg.msg = "操作成功";
		logger.info(msg);
		return msg;
	}

	// 派件量趋势图(周)
	@RequestMapping("/getWeekTrendForSnd")
	public CustomResponseMsg getWeekTrendForSnd(CustomReqParameter parameter) throws ParseException {
		CustomResponseMsg msg = new CustomResponseMsg();
		Map<String, Object> paramMap = new HashMap<>();
		String conditonStr = parameter.getSearch_condition();
		paramMap.put("conditonStr", conditonStr);
		List<Map<String, Object>> delvWTrend = basSBusiSndService.delvWTrend(paramMap);
		msg.data = delvWTrend;
		msg.errorCode = 0;
		msg.msg = "操作成功";
		logger.info(msg);
		return msg;
	}

	// 派件量趋势图(日)
	@RequestMapping("/getDayTrendForSnd")
	public CustomResponseMsg getDayTrendForSnd(CustomReqParameter parameter) {
		CustomResponseMsg msg = new CustomResponseMsg();
		Map<String, Object> paramMap = new HashMap<>();
		String conditonStr = parameter.getSearch_condition();
		paramMap.put("conditonStr", conditonStr);
		List<Map<String, Object>> delvDTrend = basSBusiSndService.delvDTrend(paramMap);
		msg.data = delvDTrend;
		msg.errorCode = 0;
		msg.msg = "操作成功";
		logger.info(msg);
		return msg;
	}

	// 签件量日周月平均
	@RequestMapping("/getDwmAvgAmountForSign")
	public CustomResponseMsg getDwmAvgAmountForSign(CustomReqParameter parameter) {
		CustomResponseMsg msg = new CustomResponseMsg();
		Map<String, Object> paramMap = new HashMap<>();
		String conditonStr = parameter.getSearch_condition();
		paramMap.put("conditonStr", conditonStr);
		Map<String, Object> dwmAvgAmountForSign = basSBusiDelvService.selectDwmAvgAmountForSig(paramMap);
		msg.data = dwmAvgAmountForSign;
		msg.errorCode = 0;
		msg.msg = "操作成功";
		logger.info(msg);
		return msg;
	}

	// 签件量趋势图(季度)
	@RequestMapping("/getQuarterlyTrendForSign")
	public CustomResponseMsg getQuarterlyTrendForSign(CustomReqParameter parameter) throws ParseException {
		CustomResponseMsg msg = new CustomResponseMsg();
		Map<String, Object> paramMap = new HashMap<>();
		String conditonStr = parameter.getSearch_condition();
		paramMap.put("conditonStr", conditonStr);
		List<Map<String, Object>> delvQTrend = basSBusiDelvService.delvQTrend(paramMap);
		msg.data = delvQTrend;
		msg.errorCode = 0;
		msg.msg = "操作成功";
		logger.info(msg);
		return msg;
	}

	// 签件量趋势图(月)
	@RequestMapping("/getMonthTrendForSign")
	public CustomResponseMsg getMonthTrendForSign(CustomReqParameter parameter) throws ParseException {
		CustomResponseMsg msg = new CustomResponseMsg();
		Map<String, Object> paramMap = new HashMap<>();
		String conditonStr = parameter.getSearch_condition();
		paramMap.put("conditonStr", conditonStr);
		List<Map<String, Object>> delvMTrend = basSBusiDelvService.delvMTrend(paramMap);
		msg.data = delvMTrend;
		msg.errorCode = 0;
		msg.msg = "操作成功";
		logger.info(msg);
		return msg;
	}

	// 签件量趋势图(周)
	@RequestMapping("/getWeekTrendForSign")
	public CustomResponseMsg getWeekTrendForSign(CustomReqParameter parameter) throws ParseException {
		CustomResponseMsg msg = new CustomResponseMsg();
		Map<String, Object> paramMap = new HashMap<>();
		String conditonStr = parameter.getSearch_condition();
		paramMap.put("conditonStr", conditonStr);
		List<Map<String, Object>> delvWTrend = basSBusiDelvService.delvWTrend(paramMap);
		msg.data = delvWTrend;
		msg.errorCode = 0;
		msg.msg = "操作成功";
		logger.info(msg);
		return msg;
	}

	// 签件量趋势图(日)
	@RequestMapping("/getDayTrendForSign")
	public CustomResponseMsg getDayTrendForSign(CustomReqParameter parameter) throws ParseException {
		CustomResponseMsg msg = new CustomResponseMsg();
		Map<String, Object> paramMap = new HashMap<>();
		String conditonStr = parameter.getSearch_condition();
		paramMap.put("conditonStr", conditonStr);
		List<Map<String, Object>> delvDTrend = basSBusiDelvService.delvDTrend(paramMap);
		msg.data = delvDTrend;
		msg.errorCode = 0;
		msg.msg = "操作成功";
		logger.info(msg);
		return msg;
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////

	// 揽件量月成功率(完成率排名)
	@RequestMapping("/getMonthGrowthRate")
	public CustomResponseMsg getMonthGrowthRate(CustomReqParameter parameter) {
		CustomResponseMsg msg = new CustomResponseMsg();
		Map<String, Object> paramMap = new HashMap<>();
		String conditonStr = parameter.getSearch_condition();
		paramMap.put("conditonStr", conditonStr);
		List<MonthGrowthRate> monthGrowthRates = basSBusiPickService.getMonthGrowthRate(paramMap);
		msg.data = monthGrowthRates;
		msg.errorCode = 0;
		msg.msg = "操作成功";
		logger.info(msg);
		return msg;
	}

	// 揽件量月波动率(波动率排名)
	@RequestMapping("/getMonthVolatility")
	public CustomResponseMsg getMonthVolatility(CustomReqParameter parameter) {
		CustomResponseMsg msg = new CustomResponseMsg();
		Map<String, Object> paramMap = new HashMap<>();
		String conditonStr = parameter.getSearch_condition();
		paramMap.put("conditonStr", conditonStr);
		List<MonthVolatility> monthGrowthRates = basSBusiPickService.getMonthVolatility(paramMap);
		msg.data = monthGrowthRates;
		msg.errorCode = 0;
		msg.msg = "操作成功";
		logger.info(msg);
		return msg;
	}

	// 派件量月成功率(完成率排名)
	@RequestMapping("/getMonthGrowthRateForSnd")
	public CustomResponseMsg getMonthGrowthRateForSnd(CustomReqParameter parameter) {
		CustomResponseMsg msg = new CustomResponseMsg();
		Map<String, Object> paramMap = new HashMap<>();
		String conditonStr = parameter.getSearch_condition();
		paramMap.put("conditonStr", conditonStr);
		List<MonthGrowthRate> monthGrowthRates = basSBusiSndService.monthGrowthRateForSnd(paramMap);
		msg.data = monthGrowthRates;
		msg.errorCode = 0;
		msg.msg = "操作成功";
		logger.info(msg);
		return msg;
	}

	// 派件量月波动率(波动率排名)
	@RequestMapping("/getMonthVolatilityForSnd")
	public CustomResponseMsg getMonthVolatilityForSnd(CustomReqParameter parameter) {
		CustomResponseMsg msg = new CustomResponseMsg();
		Map<String, Object> paramMap = new HashMap<>();
		String conditonStr = parameter.getSearch_condition();
		paramMap.put("conditonStr", conditonStr);
		List<MonthVolatility> monthGrowthRates = basSBusiSndService.monthVolatilityForSnd(paramMap);
		msg.data = monthGrowthRates;
		msg.errorCode = 0;
		msg.msg = "操作成功";
		logger.info(msg);
		return msg;
	}

	// 签件量月成功率(完成率排名)
	@RequestMapping("/getMonthGrowthRateForSign")
	public CustomResponseMsg getMonthGrowthRateForSign(CustomReqParameter parameter) {
		CustomResponseMsg msg = new CustomResponseMsg();
		Map<String, Object> paramMap = new HashMap<>();
		String conditonStr = parameter.getSearch_condition();
		paramMap.put("conditonStr", conditonStr);
		List<MonthGrowthRate> monthGrowthRates = basSBusiDelvService.monthGrowthRateForSign(paramMap);
		msg.data = monthGrowthRates;
		msg.errorCode = 0;
		msg.msg = "操作成功";
		logger.info(msg);
		return msg;
	}

	// 签件量月波动率(波动率排名)
	@RequestMapping("/getMonthVolatilityForSign")
	public CustomResponseMsg getMonthVolatilityForSign(CustomReqParameter parameter) {
		CustomResponseMsg msg = new CustomResponseMsg();
		Map<String, Object> paramMap = new HashMap<>();
		String conditonStr = parameter.getSearch_condition();
		paramMap.put("conditonStr", conditonStr);
		List<MonthVolatility> monthGrowthRates = basSBusiDelvService.monthVolatilityForSign(paramMap);
		msg.data = monthGrowthRates;
		msg.errorCode = 0;
		msg.msg = "操作成功";
		logger.info(msg);
		return msg;
	}

	// 单日签收率(机构和成功率)
	@RequestMapping("/getSiteAndSucRate")
	public CustomResponseMsg getSiteAndSucRate(CustomReqParameter parameter) {
		CustomResponseMsg msg = new CustomResponseMsg();
		Map<String, Object> paramMap = new HashMap<>();
		String conditonStr = parameter.getSearch_condition();
		paramMap.put("conditonStr", conditonStr);
		List<SitSucRate> siteAndSucRates = basSBusiSndService.getSiteAndSucRate(paramMap);
		msg.data = siteAndSucRates;
		msg.errorCode = 0;
		msg.msg = "操作成功";
		logger.info(msg);
		return msg;
	}

	// 单日签收率达标网点数趋势
	@RequestMapping("/selectStandardNetworkTrend")
	public CustomResponseMsg selectStandardNetworkTrend(CustomReqParameter parameter) {
		CustomResponseMsg msg = new CustomResponseMsg();
		Map<String, Object> paramMap = new HashMap<>();
		String conditonStr = parameter.getSearch_condition();
		paramMap.put("conditonStr", conditonStr);

		List<Map<String, Object>> standardNetworkTrends = basSBusiSndService.selectStandardNetworkTrend(paramMap);
		msg.data = standardNetworkTrends;
		msg.errorCode = 0;
		msg.msg = "操作成功";
		logger.info(msg);
		return msg;
	}
}