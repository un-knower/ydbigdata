package com.yd.ydbi.hp.controller;

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
import com.yd.ydbi.hp.model.HomePage;
import com.yd.ydbi.hp.model.PerCapitaEffectFoot;
import com.yd.ydbi.hp.service.Home_pageService;

/**
 * 
 * 大数据平台首页控制层
 *
 */
@RestController
@RequestMapping(value = "/action/homepage")
public class Home_pageController {

	private Logger log = Logger.getLogger(Home_pageController.class);
	@Autowired
	Home_pageService home_pageService;

	/**
	 * 首页
	 * @param parameter
	 * @return
	 */
	@RequestMapping(value = "/index")
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
			HomePage homePage = (HomePage) home_pageService.searchData(paramsMap);
			msg.data = homePage;

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
	 * 人均效能趋势
	 * @param parameter
	 * @return
	 */
	@RequestMapping(value = "/pcetrend")
	public CustomResponseMsg perCapitaEffect2(CustomReqParameter parameter) {
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
			msg.data =  home_pageService.getPCETrend(paramsMap);

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
	 * 人均效能topn
	 * @param parameter
	 * @return
	 */
	/**
	 * 传入pcetype 1为人均件量 0 为周环比排名
	 * @param parameter
	 * @return
	 */
	@RequestMapping(value = "/pcetop5")
	public CustomResponseMsg perCapitaEffect3(CustomReqParameter parameter) {
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
			PerCapitaEffectFoot pceFoot = home_pageService.getPCEFoot(paramsMap);
			msg.data = pceFoot;

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
	 * 人均效能head
	 * @param parameter
	 * @return
	 */
	@RequestMapping(value = "/pceheader")
	public CustomResponseMsg perCapitaEffect(CustomReqParameter parameter) {
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
			msg.data = home_pageService.getPCEHeader(paramsMap);

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
	 * 人均效能周环比涨幅排名
	 * @param parameter
	 * @return
	 */
	@RequestMapping(value = "/pcewtopn")
	public CustomResponseMsg perCapitaEffect4(CustomReqParameter parameter) {
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
			HomePage homePage = (HomePage) home_pageService.searchData(paramsMap);
			msg.data = homePage;

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
