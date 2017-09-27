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
import com.yd.ydbi.seffect.service.SeffectSndService;

/**
 * <p>
 * 票件_派件时效控制层实现类
 * </p>
 * <p>
 * Class: SeffectController
 * </p>
 */
@Controller
@ResponseBody
@RequestMapping(value = "/action/bas_s_snd_bigdata")
public class SeffectSndController {
	private Logger logger = Logger.getLogger(SeffectSndController.class);
	@Autowired
	SeffectSndService seffectSndService;

	// 票件_派件时效
		@RequestMapping("/getSeffect")
		public CustomResponseMsg getSeffect(CustomReqParameter parameter) {
			CustomResponseMsg msg = new CustomResponseMsg();
			Map<String, Object> paramMap = new HashMap<>();
			String conditonStr = parameter.getSearch_condition();
			Map<String, Object> map = new HashMap<>();
			paramMap.put("conditonStr", conditonStr);
			Map<String, Object> seffect = seffectSndService.getSeffectSnd(paramMap);
			Map<String, Object> DwmAvgAmount = seffectSndService.selectDwmAvgAmount(paramMap);
			map.put("seffect_dayFrom_weekFrom", seffect);
			map.put("DwmAvg", DwmAvgAmount);
			msg.data = map;
			msg.errorCode = 0;
			msg.msg = "操作成功";
			logger.info(msg);
			return msg;
		}
		
		// 派件时效趋势图(日月周均)
		@RequestMapping("/getTrend")
		public CustomResponseMsg getDayTrend(CustomReqParameter parameter) throws ParseException {
			CustomResponseMsg msg = new CustomResponseMsg();
			Map<String, Object> paramMap = new HashMap<>();
			String conditonStr = parameter.getSearch_condition();
			paramMap.put("conditonStr", conditonStr);
			Map<String, Object> seffectTrend = seffectSndService.seffectTrend(paramMap);
			msg.data = seffectTrend;
			msg.errorCode = 0;
			msg.msg = "操作成功";
			logger.info(msg);
			return msg;
		}
}