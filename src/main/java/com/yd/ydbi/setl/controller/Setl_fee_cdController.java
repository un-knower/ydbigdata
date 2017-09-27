package com.yd.ydbi.setl.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yd.common.data.CIPPageInfo;
import com.yd.common.runtime.CIPErrorCode;
import com.yd.ydbi.common.response.CustomReqParameter;
import com.yd.ydbi.common.response.ToolResponseQueryMsg;
import com.yd.ydbi.setl.model.Setl_fee_cd;
import com.yd.ydbi.setl.service.impl.Setl_fee_cdServiceImpl;

/**
 * 
 * Function:费用信息管理控制器. <br/>
 * 
 * @date:2017-06-30 12:48:07 
 * @author 康元佳
 * @version:
 * @since:JDK 1.7
 */
@RestController
@RequestMapping(value = "/actions/setl/setl_fee_cd")
public class Setl_fee_cdController{
	
	@Autowired
	private Setl_fee_cdServiceImpl dataService;
	
	@RequestMapping(value = "/searchData")
	public ToolResponseQueryMsg searchData(CustomReqParameter parameter) {
		ToolResponseQueryMsg msg = new ToolResponseQueryMsg();
		CIPPageInfo page = new CIPPageInfo(parameter.getPage(), parameter.getRows());
		Map<String, Object> paramsMap = new HashMap<String, Object>();

		String conditonStr = parameter.getSearch_condition();
		paramsMap.put("conditonStr", conditonStr);
		
		List<Setl_fee_cd> rows = dataService.searchData(paramsMap);

		msg.errorCode = CIPErrorCode.CALL_SUCCESS.code;
		msg.msg = CIPErrorCode.CALL_SUCCESS.name;
		msg.rows = rows;
		msg.total = page.getTotal();

		return msg;
	}
	
}
