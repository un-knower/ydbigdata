package com.yd.ydbi.matrix.controller;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yd.common.runtime.CIPErrorCode;
import com.yd.ydbi.common.response.CustomResponseMsg;
import com.yd.ydbi.matrix.service.impl.Matrix_cross_toolServiceImpl;

@RestController
@RequestMapping(value = "/actions/matrixCross/tool")
public class Matrix_cross_toolController {
	
private static final Logger logger = LoggerFactory.getLogger(Matrix_cross_toolController.class);
	
	@Autowired
	private Matrix_cross_toolServiceImpl toolService ;
	
	/**
	 * 面单类型下拉框（交叉带格口识别率）
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/getShipTypName")
	public CustomResponseMsg getShipTypName(HttpServletRequest request) {
		
		CustomResponseMsg msg = new CustomResponseMsg();
		Map<String, Object> paramsMap =new HashMap<>();
		try{
			msg.data = toolService.getShipTypName(paramsMap);
		}catch (RuntimeException e) {
			e.printStackTrace();
			msg.errorCode = CIPErrorCode.ERROR_TECHNICAL_ERROR.code;
			msg.msg = e.getMessage();
		}
		return msg;
	}
	
	/**
	 * 面单类型下拉框（高拍矩阵格口识别率）
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/getAllShipTypName")
	public CustomResponseMsg getAllShipTypName(HttpServletRequest request) {
		
		CustomResponseMsg msg = new CustomResponseMsg();
		Map<String, Object> paramsMap =new HashMap<>();
		try{
			msg.data = toolService.getAllShipTypName(paramsMap);
		}catch (RuntimeException e) {
			e.printStackTrace();
			msg.errorCode = CIPErrorCode.ERROR_TECHNICAL_ERROR.code;
			msg.msg = e.getMessage();
		}
		return msg;
	}
	
}
