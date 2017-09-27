package com.yd.ydbi.api.cvp.controller;

import com.yd.common.data.CIPPageInfo;
import com.yd.common.utils.JSONUtils;
import com.yd.ydbi.api.cvp.model.YdbiBusinessTarget;
import com.yd.ydbi.api.cvp.service.Ydbi_business_targetService;
import com.yd.ydbi.common.response.CustomReqParameter;
import com.yd.ydbi.common.response.CustomResponseMsg;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;


/**
 * <p>
 * 控制层实现类
 * </p>
 * <p>
 * Class: Ydbi_business_targetController
 * 要点-目标值
 * </p>
 *
 * @since 2017-07-12  14:07:00
 */
@RestController
@RequestMapping(value = "/action/ydbi_business_target")
public class Ydbi_business_targetController {

	private Logger logger = Logger.getLogger(Ydbi_business_targetController.class);

	@Autowired
	Ydbi_business_targetService ydbi_business_targetService;
	
	@RequestMapping(value = "/searchData")
	public CustomResponseMsg searchData(CustomReqParameter parameter) {
		CustomResponseMsg msg = new CustomResponseMsg();
		Map paramsMap = new HashMap();
		final String conditonStr = parameter.getSearch_condition();
		
		if (conditonStr != null) {
			Map reqCondtions = JSONUtils.convertJson2Object(conditonStr, HashMap.class);/*将json数据转成指定类型的对象*/
			Set<String> keys = reqCondtions.keySet(); //获取对应的key值集合
			String value;

			for (String key : keys) {
				value = (String) reqCondtions.get(key);
				if (value == null || value.trim().equals(""))
					continue;
					paramsMap.put(key, value);
			}
			if(!"".equals(paramsMap.get("page")) && !"".equals(paramsMap.get("rows")) && paramsMap.get("page")!=null && paramsMap.get("rows")!=null){
				CIPPageInfo page = new CIPPageInfo(Integer.parseInt((String) paramsMap.get("page")),Integer.parseInt((String) paramsMap.get("rows")));
				paramsMap.put("page1", page.getStartRecord());
				paramsMap.put("rows1", page.getRows());
				}
		}
		paramsMap.put("conditonStr", conditonStr);
		List<YdbiBusinessTarget> cvpBoardList = new ArrayList<YdbiBusinessTarget>();
		cvpBoardList = (List<YdbiBusinessTarget>) ydbi_business_targetService.searchData(paramsMap);
		msg.data = cvpBoardList;
		msg.errorCode = 0;
		msg.msg = "操作成功";
		return msg;
	}

}