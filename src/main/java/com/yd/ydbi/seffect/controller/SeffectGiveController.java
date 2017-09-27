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
import com.yd.ydbi.seffect.service.SeffectGiveService;

/**
 * <p>
 * 票件_交件时效控制层实现类
 * </p>
 * <p>
 * Class: SeffectController
 * </p>
 */
@Controller
@ResponseBody
@RequestMapping(value = "/action/bas_s_give_bigdata")
public class SeffectGiveController {
	private Logger logger = Logger.getLogger(SeffectGiveController.class);
	@Autowired
	SeffectGiveService seffectGiveService;

	// 票件_交件时效
	@RequestMapping("/getSeffect")
	public CustomResponseMsg getSeffect(CustomReqParameter parameter) {
		CustomResponseMsg msg = new CustomResponseMsg();
		Map<String, Object> paramMap = new HashMap<>();
		String conditonStr = parameter.getSearch_condition();
		Map<String, Object> map = new HashMap<>();
		paramMap.put("conditonStr", conditonStr);
		Map<String, Object> seffectGive = seffectGiveService.getSeffectGive(paramMap);
		Map<String, Object> DwmAvgAmount = seffectGiveService.selectDwmAvgAmount(paramMap);
		map.put("seffect_dayFrom_weekFrom", seffectGive);
		map.put("DwmAvg", DwmAvgAmount);
		msg.data = map;
		msg.errorCode = 0;
		msg.msg = "操作成功";
		logger.info(msg);
		return msg;
	}
	
	// 交件时效趋势图(日月周均)
	@RequestMapping("/getTrend")
	public CustomResponseMsg getDayTrend(CustomReqParameter parameter) throws ParseException {
		CustomResponseMsg msg = new CustomResponseMsg();
		Map<String, Object> paramMap = new HashMap<>();
		String conditonStr = parameter.getSearch_condition();
		paramMap.put("conditonStr", conditonStr);
		Map<String, Object> seffectTrend = seffectGiveService.seffectTrend(paramMap);
		msg.data = seffectTrend;
		msg.errorCode = 0;
		msg.msg = "操作成功";
		logger.info(msg);
		return msg;
	}
	
	// 交件时效_获取总记录数
	@RequestMapping("/getDTotalRows")
	public CustomResponseMsg getDTotalRows(CustomReqParameter parameter) throws ParseException {
		CustomResponseMsg msg = new CustomResponseMsg();
		Map<String, Object> paramMap = new HashMap<>();
		String conditonStr = parameter.getSearch_condition();
		paramMap.put("conditonStr", conditonStr);
		int DTotalRows = seffectGiveService.getDTotalRows(paramMap);
		msg.data = DTotalRows;
		msg.errorCode = 0;
		msg.msg = "操作成功";
		logger.info(msg);
		return msg;
	}
}