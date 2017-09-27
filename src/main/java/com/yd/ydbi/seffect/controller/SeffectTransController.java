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
import com.yd.ydbi.seffect.service.SeffectTransService;

/**
 * <p>
 * 票件_中转时效控制层实现类
 * </p>
 * <p>
 * Class: SeffectController
 * </p>
 */
@Controller
@ResponseBody
@RequestMapping(value = "/action/bas_s_trans_bigdata")
public class SeffectTransController {
	private Logger logger = Logger.getLogger(SeffectTransController.class);
	@Autowired
	SeffectTransService seffectTransService;

	// 票件_中转时效
	@RequestMapping("/getSeffect")
	public CustomResponseMsg getSeffect(CustomReqParameter parameter) {
		CustomResponseMsg msg = new CustomResponseMsg();
		Map<String, Object> paramMap = new HashMap<>();
		String conditonStr = parameter.getSearch_condition();
		Map<String, Object> map = new HashMap<>();
		paramMap.put("conditonStr", conditonStr);
		Map<String, Object> seffect = seffectTransService.getSeffectTrans(paramMap);
		Map<String, Object> DwmAvgAmount = seffectTransService.selectDwmAvgAmount(paramMap);
		map.put("seffect_dayFrom_weekFrom", seffect);
		map.put("DwmAvg", DwmAvgAmount);
		msg.data = map;
		msg.errorCode = 0;
		msg.msg = "操作成功";
		logger.info(msg);
		return msg;
	}

	// 中转时效趋势图(日月周均)
	@RequestMapping("/getTrend")
	public CustomResponseMsg getDayTrend(CustomReqParameter parameter) throws ParseException {
		CustomResponseMsg msg = new CustomResponseMsg();
		Map<String, Object> paramMap = new HashMap<>();
		String conditonStr = parameter.getSearch_condition();
		paramMap.put("conditonStr", conditonStr);
		Map<String, Object> seffectTrend = seffectTransService.seffectTrend(paramMap);
		msg.data = seffectTrend;
		msg.errorCode = 0;
		msg.msg = "操作成功";
		logger.info(msg);
		return msg;
	}

	// 中转时效件量占比
	@RequestMapping("/getCntProportion")
	public CustomResponseMsg getCntProportion(CustomReqParameter parameter) {
		CustomResponseMsg msg = new CustomResponseMsg();
		Map<String, Object> paramMap = new HashMap<>();
		String conditonStr = parameter.getSearch_condition();
		paramMap.put("conditonStr", conditonStr);
		List<Map<String, Object>> cntProportion = seffectTransService.getCntProportion(paramMap);
		msg.data = cntProportion;
		msg.errorCode = 0;
		msg.msg = "操作成功";
		logger.info(msg);
		return msg;
	}

	// 中转时效最慢10省线
	@RequestMapping("/getSlowly10ProvLine")
	public CustomResponseMsg getSlowly10ProvLine(CustomReqParameter parameter) {
		CustomResponseMsg msg = new CustomResponseMsg();
		Map<String, Object> paramMap = new HashMap<>();
		String conditonStr = parameter.getSearch_condition();
		paramMap.put("conditonStr", conditonStr);
		List<Map<String, Object>> ProvLine = seffectTransService.getSlowly10ProvLine(paramMap);
		msg.data = ProvLine;
		msg.errorCode = 0;
		msg.msg = "操作成功";
		logger.info(msg);
		return msg;
	}

	// 中转时效最慢10市线
	@RequestMapping("/getSlowly10CityLine")
	public CustomResponseMsg getSlowly10CityLine(CustomReqParameter parameter) {
		CustomResponseMsg msg = new CustomResponseMsg();
		Map<String, Object> paramMap = new HashMap<>();
		String conditonStr = parameter.getSearch_condition();
		paramMap.put("conditonStr", conditonStr);
		List<Map<String, Object>> CityLine = seffectTransService.getSlowly10CityLine(paramMap);
		msg.data = CityLine;
		msg.errorCode = 0;
		msg.msg = "操作成功";
		logger.info(msg);
		return msg;
	}
}