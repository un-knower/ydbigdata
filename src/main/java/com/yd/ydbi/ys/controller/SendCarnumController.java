package com.yd.ydbi.ys.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yd.common.exception.CIPRuntimeException;
import com.yd.common.exception.CIPServiceException;
import com.yd.common.runtime.CIPErrorCode;
import com.yd.common.utils.JSONUtils;
import com.yd.ydbi.common.response.CustomReqParameter;
import com.yd.ydbi.common.response.CustomResponseMsg;
import com.yd.ydbi.ys.service.YS_sendCar;

@RestController
@RequestMapping(value = "/action/transport")
public class SendCarnumController {

	private Logger log = Logger.getLogger(SendCarnumController.class);
	@Autowired
	YS_sendCar yS_sendCar;

	/**
	 * 发车数量
	 * 
	 * @param parameter
	 * @return
	 */
	@RequestMapping(value = "/sendcar")
	public CustomResponseMsg index(CustomReqParameter parameter) {
		CustomResponseMsg msg = new CustomResponseMsg();
		try {
			Map<String, String> paramsMap = new HashMap<String, String>();
			final String conditonStr = parameter.getSearch_condition();

			if (conditonStr != null) {
				Map reqCondtions = JSONUtils.convertJson2Object(conditonStr, HashMap.class);
				Set<String> keys = reqCondtions.keySet();
				String value;

				for (String key : keys) {
					value = (String) reqCondtions.get(key);
					if (value == null || value.trim().equals(""))
						continue;
					if (true) {
						paramsMap.put(key, value);
					}
				}
			}
			msg.data = yS_sendCar.searchDate(paramsMap);
			msg.errorCode = 0;
			msg.msg = "操作成功";
		} catch (CIPRuntimeException e) {
			log.error(e);
			msg.errorCode = e.getErrorCode().code;
			msg.msg = e.getErrorCode().name;
		} catch (CIPServiceException e) {
			log.error(e);
			msg.errorCode = e.getErrorCode().code;
			msg.msg = e.getErrorCode().name;
		} catch (Exception e) {
			log.error(e);
			msg.errorCode = CIPErrorCode.ERROR_RESOURCE_IS_NOT_READY.code;
			msg.msg = e.getMessage();
		}

		return msg;
	}

	/**
	 * 网点发车数量
	 * 
	 * @param parameter
	 * @return
	 */
	@RequestMapping(value = "/wdsendcar")
	public CustomResponseMsg index2(CustomReqParameter parameter) {
		CustomResponseMsg msg = new CustomResponseMsg();
		try {
			Map<String, String> paramsMap = new HashMap<String, String>();
			final String conditonStr = parameter.getSearch_condition();

			if (conditonStr != null) {
				Map reqCondtions = JSONUtils.convertJson2Object(conditonStr, HashMap.class);
				Set<String> keys = reqCondtions.keySet();
				String value;

				for (String key : keys) {
					value = (String) reqCondtions.get(key);
					if (value == null || value.trim().equals(""))
						continue;
					if (true) {
						paramsMap.put(key, value);
					}
				}
			}
			msg.data = yS_sendCar.searchwdDate(paramsMap);
			msg.errorCode = 0;
			msg.msg = "操作成功";
		} catch (CIPRuntimeException e) {
			log.error(e);
			msg.errorCode = e.getErrorCode().code;
			msg.msg = e.getErrorCode().name;
		} catch (CIPServiceException e) {
			log.error(e);
			msg.errorCode = e.getErrorCode().code;
			msg.msg = e.getErrorCode().name;
		} catch (Exception e) {
			log.error(e);
			msg.errorCode = CIPErrorCode.ERROR_RESOURCE_IS_NOT_READY.code;
			msg.msg = e.getMessage();
		}

		return msg;
	}
}
