package com.yd.ydbi.cainiao.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yd.common.data.CIPPageInfo;
import com.yd.common.runtime.CIPErrorCode;
import com.yd.common.runtime.CIPRuntime;
import com.yd.ydbi.cainiao.model.Cn_compst_scor;
import com.yd.ydbi.cainiao.service.impl.Cn_compst_scorServiceImpl;
import com.yd.ydbi.common.response.CustomReqParameter;
import com.yd.ydbi.common.response.ToolResponseQueryMsg;

/**
 * 
 * Function:菜鸟信息维护管理控制器. <br/>
 * 
 * @date:2017-08-07 10:09:29 
 * @author 康元佳
 * @version:
 * @since:JDK 1.7
 */
@RestController
@RequestMapping(value = "/actions/cainiao/cn_compst_scor")
public class Cn_compst_scorController{
	
	@Autowired
	private Cn_compst_scorServiceImpl cn_compst_scorService;
	
	@RequestMapping(value = "/searchData")
	public ToolResponseQueryMsg searchData(CustomReqParameter parameter) {
		ToolResponseQueryMsg msg = new ToolResponseQueryMsg();
		CIPPageInfo page = new CIPPageInfo(parameter.getPage(), parameter.getRows());
		Map<String, Object> paramsMap = new HashMap<String, Object>();

		String conditonStr = parameter.getSearch_condition();
		paramsMap.put("conditonStr", conditonStr);
		List<Cn_compst_scor> footer = cn_compst_scorService.searchFooterData(paramsMap);
		for (Cn_compst_scor data : footer) {
			page.setTotal(data.getIndustNm());
			data.setIndustNm(null);
//			data.setGrtrDistNm("合计:");
		}
		paramsMap.put("start_num", page.getStartRecord());
		paramsMap.put("row_num", page.getRows());
		List<Cn_compst_scor> rows = cn_compst_scorService.searchData(paramsMap);

		if (!rows.isEmpty()) {
			msg.footer = footer;
		} else {
			msg.footer = new ArrayList<>();
		}

		msg.errorCode = CIPErrorCode.CALL_SUCCESS.code;
		msg.msg = CIPErrorCode.CALL_SUCCESS.name;
		msg.rows = rows;
		msg.total = page.getTotal();

		return msg;
	}
	
	@RequestMapping(value = "/addData")
	public ToolResponseQueryMsg addData(CustomReqParameter parameter) {
		ToolResponseQueryMsg msg = new ToolResponseQueryMsg();
		Map<String, Object> paramsMap = new HashMap<String, Object>();

		String conditonStr = parameter.getSearch_condition();
		paramsMap.put("conditonStr", conditonStr);
		paramsMap.put("entr_pers_cd",  CIPRuntime.getOperateSubject().getSubject_id());
		int result = cn_compst_scorService.addData(paramsMap);
		if(result>0){
			msg.errorCode = CIPErrorCode.CALL_SUCCESS.code;
			msg.msg = CIPErrorCode.CALL_SUCCESS.name;
		}else{
			msg.errorCode = CIPErrorCode.ERROR_DATABASE_TECH_EXCEPTION.code;
			msg.msg = CIPErrorCode.ERROR_DATABASE_TECH_EXCEPTION.name;
		}
		return msg;
	}
	
	@RequestMapping(value = "/updateData")
	public ToolResponseQueryMsg updateData(CustomReqParameter parameter) {
		ToolResponseQueryMsg msg = new ToolResponseQueryMsg();
		Map<String, Object> paramsMap = new HashMap<String, Object>();

		String conditonStr = parameter.getSearch_condition();
		paramsMap.put("conditonStr", conditonStr);
		paramsMap.put("entr_pers_cd",  CIPRuntime.getOperateSubject().getSubject_id());
		int result = cn_compst_scorService.updateData(paramsMap);
		if(result>0){
			msg.errorCode = CIPErrorCode.CALL_SUCCESS.code;
			msg.msg = CIPErrorCode.CALL_SUCCESS.name;
		}else{
			msg.errorCode = CIPErrorCode.ERROR_DATABASE_TECH_EXCEPTION.code;
			msg.msg = CIPErrorCode.ERROR_DATABASE_TECH_EXCEPTION.name;
		}
		return msg;
	}

}
