package com.yd.ydbi.api.cvp.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.ydbi.api.cvp.dao.YdbiBusinessTargetMonthMapper;

import com.yd.ydbi.api.cvp.model.YdbiBusinessTarget;
import com.yd.ydbi.api.cvp.model.YdbiBusinessTargetMonth;
import com.yd.ydbi.api.cvp.model.YdbiBusinessTargetWeek;
import com.yd.ydbi.api.cvp.service.Ydbi_business_targetService;
import com.yd.ydbi.api.cvp.service.Ydbi_business_target_monthService;
import com.yd.ydbi.api.cvp.service.Ydbi_business_target_weekService;
import com.yd.ydbi.utils.ListSortUtil;

/**
 * <p>
 * Service实现类
 * </p>
 * <p>
 * Class: Ydbi_business_target_monthServiceImpl
 * </p>
 *
 * @since 2017-07-12 14:47:00
 */

@Service
public class Ydbi_business_target_monthServiceImpl implements Ydbi_business_target_monthService {

	@Autowired
	YdbiBusinessTargetMonthMapper ydbiBusinessTargetMonthMapper;

	@Override
	public List<YdbiBusinessTargetMonth> searchData(Map paramsMap) {
		String data = (String) paramsMap.get("data_date");
		if(!StringUtils.isEmpty(data)){
		int length =  6;
		int month =Integer.parseInt((String) data.substring(5,7));
		int month_length = month-length;
		String monthend = null;
		String monthstart = null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		if(month_length < 0){
			String start  = (Integer.parseInt((String) data.substring(0,4))-1)+"-"+(12+month_length)+"-01";
			try {
				Date date = format.parse(start);
				monthstart =format.format(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
		}else{
			 
			String start = (Integer.parseInt((String) data.substring(0,4)))+"-"+month_length+"-01";	
			 try {
				Date date =  format.parse(start);
				monthstart = format.format(date); 
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if((Integer.parseInt(data.substring(5,7))+1)>12){
			monthend = (Integer.parseInt(data.substring(0,4))+1)+"-01-01";
		}else{
			String end = data.substring(0,4)+"-"+(Integer.parseInt(data.substring(5,7))+1)+"-01";
			try {
				Date date = format.parse(end);
				monthend =format.format(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}	
		}
		paramsMap.put("monthstart",monthstart);
		paramsMap.put("monthend", monthend);
		//完成率排名时间
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
		List<YdbiBusinessTargetMonth> businesstargetList = new ArrayList<YdbiBusinessTargetMonth>();
		if ("3".equals(paramsMap.get("sel_type"))) {
			businesstargetList = ydbiBusinessTargetMonthMapper.selectAll(paramsMap);
		} else if ("4".equals(paramsMap.get("sel_type"))) {
			businesstargetList = ydbiBusinessTargetMonthMapper.selectFinish(paramsMap);
		} else if ("5".equals(paramsMap.get("sel_type"))) {
			businesstargetList = ydbiBusinessTargetMonthMapper.selectVolat(paramsMap);
		}
		return businesstargetList;
	}

}