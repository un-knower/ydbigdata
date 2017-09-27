package com.yd.ydbi.seffect.controller;

import java.text.ParseException;
import java.util.ArrayList;
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
import com.yd.ydbi.seffect.service.SeffectAllService;
import com.yd.ydbi.seffect.service.SeffectGiveService;
import com.yd.ydbi.seffect.service.SeffectSndService;
import com.yd.ydbi.seffect.service.SeffectTransOpService;
import com.yd.ydbi.seffect.service.SeffectTransService;

/**
 * <p>
 * 票件_时效控制层实现类
 * </p>
 * <p>
 * Class: SeffectController
 * </p>
 */
@Controller
@ResponseBody
@RequestMapping(value = "/action/bas_s_bigdata")
public class SeffectController {
	private Logger logger = Logger.getLogger(SeffectController.class);
	@Autowired
	SeffectGiveService seffectGiveService;

	@Autowired
	SeffectTransOpService seffectTansOpService;

	@Autowired
	SeffectTransService seffectTransService;

	@Autowired
	SeffectSndService seffectSndService;

	@Autowired
	SeffectAllService seffectAllService;

	// 票件_时效(包含日环比,周同比)
	@RequestMapping("/getSeffect")
	public CustomResponseMsg getSeffectGive(CustomReqParameter parameter) {
		CustomResponseMsg msg = new CustomResponseMsg();
		Map<String, Object> paramMap = new HashMap<>();
		String conditonStr = parameter.getSearch_condition();
		paramMap.put("conditonStr", conditonStr);
		Map<String, Object> seffectGive = seffectGiveService.getSeffectGive(paramMap);
		Map<String, Object> seffectTansOp = seffectTansOpService.getSeffectTransOp(paramMap);
		Map<String, Object> seffectTrans = seffectTransService.getSeffectTrans(paramMap);
		Map<String, Object> seffectSnd = seffectSndService.getSeffectSnd(paramMap);
		Map<String, Object> seffectAll = seffectAllService.getSeffectAll(paramMap);
		Map<String, Object> map = new HashMap<>();
		map.put("seffectGive", seffectGive);
		map.put("seffectTansOp", seffectTansOp);
		map.put("seffectTrans", seffectTrans);
		map.put("seffectSnd", seffectSnd);
		map.put("seffectAll", seffectAll);
		msg.data = map;
		msg.errorCode = 0;
		msg.msg = "操作成功";
		logger.info(msg);
		return msg;
	}
}