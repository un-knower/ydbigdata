package com.yd.ydbi.api.quality.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.ydbi.api.quality.dao.YdbiQualityListMapper;
import com.yd.ydbi.api.quality.model.YdbiQualityList;
import com.yd.ydbi.api.quality.model.ListLine;
import com.yd.ydbi.api.quality.service.Ydbi_quality_listService;

/**
 * <p>
 * Service实现类
 * </p>
 * <p>
 * Class: Ydbi_quality_controlServiceImpl
 * </p>
 *
 * @since 2017-07-12 14:47:00
 */

@Service
public class Ydbi_quality_listServiceImpl implements Ydbi_quality_listService {

	@Autowired
	YdbiQualityListMapper  ydbiQualityListMapper;

	@Override
	public List<YdbiQualityList> searchData(Map paramsMap) {
		//完成率排名时间
		String data = (String) paramsMap.get("data_date");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		if(!StringUtils.isEmpty(data)){
		String f_monthend = null;
		String f_monthstart = null;
		f_monthstart = data.substring(0,8)+"01";
		if((Integer.parseInt(data.substring(5,7))+1)>12){
			f_monthend = (Integer.parseInt(data.substring(0,4))+1)+"-01-01";
		}else{
			String end = data.substring(0,4)+"-"+(Integer.parseInt(data.substring(5,7))+1)+"-01";
			try {
				Date date = format.parse(end);
				f_monthend =format.format(date);
				} catch (ParseException e) {
				e.printStackTrace();
				}	
		}
		paramsMap.put("f_monthstart",f_monthstart);
		paramsMap.put("f_monthend", f_monthend);
		}
		List businesstargetList = new ArrayList(); 
		if ("1".equals(paramsMap.get("sel_type")) && "2".equals(paramsMap.get("sel_trend")) && "2".equals(paramsMap.get("sel_line"))) {
			businesstargetList = ydbiQualityListMapper.selectDelayWeek(paramsMap);
		}else if ("2".equals(paramsMap.get("sel_type")) && "2".equals(paramsMap.get("sel_trend")) && "2".equals(paramsMap.get("sel_line"))) {
			businesstargetList = ydbiQualityListMapper.selectWrongWeek(paramsMap);
		}else if ("3".equals(paramsMap.get("sel_type")) && "2".equals(paramsMap.get("sel_trend")) && "2".equals(paramsMap.get("sel_line"))) {
			businesstargetList = ydbiQualityListMapper.selectWrongpointsWeek(paramsMap);
		}else if ("4".equals(paramsMap.get("sel_type")) && "2".equals(paramsMap.get("sel_trend")) && "2".equals(paramsMap.get("sel_line"))) {
			businesstargetList = ydbiQualityListMapper.selectWrongpackageWeek(paramsMap);
		}else if ("5".equals(paramsMap.get("sel_type")) && "2".equals(paramsMap.get("sel_trend")) && "2".equals(paramsMap.get("sel_line"))) {
			businesstargetList = ydbiQualityListMapper.selectLossWeek(paramsMap);
		}else if ("1".equals(paramsMap.get("sel_type")) && "3".equals(paramsMap.get("sel_trend")) && "3".equals(paramsMap.get("sel_line"))) {
			businesstargetList = ydbiQualityListMapper.selectDelayMonth(paramsMap);
		}else if ("2".equals(paramsMap.get("sel_type")) && "3".equals(paramsMap.get("sel_trend")) && "3".equals(paramsMap.get("sel_line"))) {
			businesstargetList = ydbiQualityListMapper.selectWrongMonth(paramsMap);
		}else if ("3".equals(paramsMap.get("sel_type")) && "3".equals(paramsMap.get("sel_trend")) && "3".equals(paramsMap.get("sel_line"))) {
			businesstargetList = ydbiQualityListMapper.selectWrongpointsMonth(paramsMap);
		}else if ("4".equals(paramsMap.get("sel_type")) && "3".equals(paramsMap.get("sel_trend")) && "3".equals(paramsMap.get("sel_line"))) {
			businesstargetList = ydbiQualityListMapper.selectWrongpackageMonth(paramsMap);
		}else if ("5".equals(paramsMap.get("sel_type")) && "3".equals(paramsMap.get("sel_trend")) && "3".equals(paramsMap.get("sel_line"))) {
			businesstargetList = ydbiQualityListMapper.selectLossMonth(paramsMap);
		}else if ("1".equals(paramsMap.get("sel_type")) && "4".equals(paramsMap.get("sel_trend")) && "4".equals(paramsMap.get("sel_line"))) {
			businesstargetList = ydbiQualityListMapper.selectDelayQuarter(paramsMap);
		}else if ("2".equals(paramsMap.get("sel_type")) && "4".equals(paramsMap.get("sel_trend")) && "4".equals(paramsMap.get("sel_line"))) {
			businesstargetList = ydbiQualityListMapper.selectWrongQuarter(paramsMap);
		}else if ("3".equals(paramsMap.get("sel_type")) && "4".equals(paramsMap.get("sel_trend")) && "4".equals(paramsMap.get("sel_line"))) {
			businesstargetList = ydbiQualityListMapper.selectWrongpointsQuarter(paramsMap);
		}else if ("4".equals(paramsMap.get("sel_type")) && "4".equals(paramsMap.get("sel_trend")) && "4".equals(paramsMap.get("sel_line"))) {
			businesstargetList = ydbiQualityListMapper.selectWrongpackageQuarter(paramsMap);
		}else if ("5".equals(paramsMap.get("sel_type")) && "4".equals(paramsMap.get("sel_trend")) && "4".equals(paramsMap.get("sel_line"))) {
			businesstargetList = ydbiQualityListMapper.selectLossQuarter(paramsMap);
		}else if ("6".equals(paramsMap.get("sel_type")) && "2".equals(paramsMap.get("sel_trend")) && "2".equals(paramsMap.get("sel_line"))) {
			businesstargetList = ydbiQualityListMapper.selectRetentionWeek(paramsMap);
		}else if ("6".equals(paramsMap.get("sel_type")) && "3".equals(paramsMap.get("sel_trend")) && "3".equals(paramsMap.get("sel_line"))) {
			businesstargetList = ydbiQualityListMapper.selectRetentionMonth(paramsMap);
		}else if ("6".equals(paramsMap.get("sel_type")) && "4".equals(paramsMap.get("sel_trend")) && "4".equals(paramsMap.get("sel_line"))) {
			businesstargetList = ydbiQualityListMapper.selectRetentionQuarter(paramsMap);
		}
		return businesstargetList;
	}
}