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
import com.yd.ydbi.seffect.service.SeffectTransOpService;

/**
 * <p>
 * 票件_中转操作时效控制层实现类
 * </p>
 * <p>
 * Class: SeffectController
 * </p>
 */
@Controller
@ResponseBody
@RequestMapping(value = "/action/bas_s_transop_bigdata")
public class SeffectTransOpController {
	private Logger logger = Logger.getLogger(SeffectTransOpController.class);
	@Autowired
	SeffectTransOpService seffectTransOpService;

	// 票件_中转操作时效
		@RequestMapping("/getSeffect")
		public CustomResponseMsg getSeffect(CustomReqParameter parameter) {
			CustomResponseMsg msg = new CustomResponseMsg();
			Map<String, Object> paramMap = new HashMap<>();
			String conditonStr = parameter.getSearch_condition();
			Map<String, Object> map = new HashMap<>();
			paramMap.put("conditonStr", conditonStr);
			Map<String, Object> seffectGive = seffectTransOpService.getSeffectTransOp(paramMap);
			Map<String, Object> DwmAvgAmount = seffectTransOpService.selectDwmAvgAmount(paramMap);
			map.put("seffect_dayFrom_weekFrom", seffectGive);
			map.put("DwmAvg", DwmAvgAmount);
			msg.data = map;
			msg.errorCode = 0;
			msg.msg = "操作成功";
			logger.info(msg);
			return msg;
		}
		
		// 中转操作时效趋势图(日月周均)
		@RequestMapping("/getTrend")
		public CustomResponseMsg getDayTrend(CustomReqParameter parameter) throws ParseException {
			CustomResponseMsg msg = new CustomResponseMsg();
			Map<String, Object> paramMap = new HashMap<>();
			String conditonStr = parameter.getSearch_condition();
			paramMap.put("conditonStr", conditonStr);
			Map<String, Object> seffectTrend = seffectTransOpService.seffectTrend(paramMap);
			msg.data = seffectTrend;
			msg.errorCode = 0;
			msg.msg = "操作成功";
			logger.info(msg);
			return msg;
		}
}