package com.yd.ydbi.api.cvp.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yd.common.data.CIPPageInfo;
import com.yd.common.utils.JSONUtils;
import com.yd.ydbi.common.response.CustomReqParameter;
import com.yd.ydbi.common.response.CustomResponseMsg;
import com.yd.ydbi.api.cvp.model.YdbiBusinessTarget;
import com.yd.ydbi.api.cvp.model.YdbiBusinessTargetMonth;
import com.yd.ydbi.api.cvp.model.YdbiBusinessTargetQuarter;
import com.yd.ydbi.api.cvp.model.YdbiBusinessTargetWeek;
import com.yd.ydbi.api.cvp.service.Ydbi_business_targetService;
import com.yd.ydbi.api.cvp.service.Ydbi_business_target_monthService;
import com.yd.ydbi.api.cvp.service.Ydbi_business_target_quarterService;
import com.yd.ydbi.api.cvp.service.Ydbi_business_target_weekService;

/**
 * <p>
 * 控制层实现类
 * </p>
 * <p>
 * Class: Ydbi_business_target_quarterController
 * 季度趋势图
 * </p>
 *
 * @since 2017-07-12  14:07:00
 */
@RestController
@RequestMapping(value = "/action/ydbi_business_target_quarter")
public class Ydbi_business_target_quarterController {

	private Logger logger = Logger.getLogger(Ydbi_business_target_quarterController.class);

	@Autowired
	Ydbi_business_target_quarterService ydbi_business_target_quarterService;
	
	@RequestMapping(value = "/searchData")
	public CustomResponseMsg searchData(CustomReqParameter parameter) {
		CustomResponseMsg msg = new CustomResponseMsg();
		Map paramsMap = new HashMap();
	    String conditonStr = parameter.getSearch_condition();
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
		List<YdbiBusinessTargetQuarter> cvpBoardList = new ArrayList<YdbiBusinessTargetQuarter>();
		/*CustomReqResCache cacheBean = mongoTemplate.findOne(query(where("search_condition").is(conditonStr)), CustomReqResCache.class);
		if(cacheBean == null){
			cacheBean = new CustomReqResCache();
			cvpBoardList = (List<CVPBoard>) anlys_cvp_boardService.searchData(paramsMap);
			cacheBean.setSearch_condition(conditonStr);
			cacheBean.setData(cvpBoardList);
			mongoTemplate.save(cacheBean);
		}
		else{
			 cvpBoardList =(List<CVPBoard>) cacheBean.getData();
		}*/
		cvpBoardList = (List<YdbiBusinessTargetQuarter>) ydbi_business_target_quarterService.searchData(paramsMap);
		msg.data = cvpBoardList;
		msg.errorCode = 0;
		msg.msg = "操作成功";
		return msg;
	}

}