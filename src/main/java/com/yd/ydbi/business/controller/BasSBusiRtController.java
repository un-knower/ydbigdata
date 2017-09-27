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

import com.yd.ydbi.business.service.BasSBusiDelvTmp0Service;
import com.yd.ydbi.common.response.CustomReqParameter;
import com.yd.ydbi.common.response.CustomResponseMsg;

/**
 * <p>
 * 控制层实现类
 * @since 2017-07-12 15:25:57
 */
@Controller
@ResponseBody
@RequestMapping(value = "/action/bas_busi_rt")
public class BasSBusiRtController {
	private Logger logger = Logger.getLogger(BasSBusiRtController.class);

	@Autowired
	BasSBusiDelvTmp0Service basSBusiDelvTmp0Service;

	// 单日签收率日周月平均
	@RequestMapping("/getDwmAvgAmount")
	public CustomResponseMsg getDwmAvgAmount(CustomReqParameter parameter) {
		CustomResponseMsg msg = new CustomResponseMsg();
		Map<String, Object> paramMap = new HashMap<>();
		String conditonStr = parameter.getSearch_condition();
		paramMap.put("conditonStr", conditonStr);
		Map<String, Object> DwmAvgAmount = basSBusiDelvTmp0Service.dwmAvgAmount(paramMap);
		msg.data = DwmAvgAmount;
		msg.errorCode = 0;
		msg.msg = "操作成功";
		logger.info(msg);
		return msg;
	}

	// 单日签收率趋势图(季度)
	@RequestMapping("/getQuarterlyTrend")
	public CustomResponseMsg getQuarterlyTrend(CustomReqParameter parameter) throws ParseException {
		CustomResponseMsg msg = new CustomResponseMsg();
		Map<String, Object> paramMap = new HashMap<>();
		String conditonStr = parameter.getSearch_condition();
		paramMap.put("conditonStr", conditonStr);
		List<Map<String, Object>> pickQTrend = basSBusiDelvTmp0Service.cfmRcvRtQTrend(paramMap);
		msg.data = pickQTrend;
		msg.errorCode = 0;
		msg.msg = "操作成功";
		logger.info(msg);
		return msg;
	}

	// 单日签收率趋势图(月)
	@RequestMapping("/getMonthTrend")
	public CustomResponseMsg getMonthTrend(CustomReqParameter parameter) throws ParseException {
		CustomResponseMsg msg = new CustomResponseMsg();
		Map<String, Object> paramMap = new HashMap<>();
		String conditonStr = parameter.getSearch_condition();
		paramMap.put("conditonStr", conditonStr);
		List<Map<String, Object>> pickMTrend = basSBusiDelvTmp0Service.cfmRcvRtMTrend(paramMap);
		msg.data = pickMTrend;
		msg.errorCode = 0;
		msg.msg = "操作成功";
		logger.info(msg);
		return msg;
	}

	// 单日签收率趋势图(周)
	@RequestMapping("/getWeekTrend")
	public CustomResponseMsg getWeekTrend(CustomReqParameter parameter) throws ParseException {
		CustomResponseMsg msg = new CustomResponseMsg();
		Map<String, Object> paramMap = new HashMap<>();
		String conditonStr = parameter.getSearch_condition();
		paramMap.put("conditonStr", conditonStr);
		List<Map<String, Object>> pickWTrend = basSBusiDelvTmp0Service.cfmRcvRtWTrend(paramMap);
		msg.data = pickWTrend;
		msg.errorCode = 0;
		msg.msg = "操作成功";
		logger.info(msg);
		return msg;
	}

	// 单日签收率趋势图(日)
	@RequestMapping("/getDayTrend")
	public CustomResponseMsg getDayTrend(CustomReqParameter parameter) throws ParseException {
		CustomResponseMsg msg = new CustomResponseMsg();
		Map<String, Object> paramMap = new HashMap<>();
		String conditonStr = parameter.getSearch_condition();
		paramMap.put("conditonStr", conditonStr);
		List<Map<String, Object>> pickDTrend = basSBusiDelvTmp0Service.cfmRcvRtDTrend(paramMap);
		msg.data = pickDTrend;
		msg.errorCode = 0;
		msg.msg = "操作成功";
		logger.info(msg);
		return msg;
	}

}