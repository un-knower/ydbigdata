package com.yd.ydbi.api.cvp.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import com.yd.ydbi.api.cvp.dao.YdbiBusinessTargetWeekMapper;
import com.yd.ydbi.api.cvp.model.YdbiBusinessTarget;
import com.yd.ydbi.api.cvp.model.YdbiBusinessTargetWeek;
import com.yd.ydbi.api.cvp.service.Ydbi_business_targetService;
import com.yd.ydbi.api.cvp.service.Ydbi_business_target_weekService;
import com.yd.ydbi.utils.ListSortUtil;

/**
 * <p>
 * Service实现类
 * </p>
 * <p>
 * Class: Ydbi_business_targetService
 * </p>
 *
 * @since 2017-07-12 14:47:00
 */

@Service
public class Ydbi_business_target_weekServiceImpl implements Ydbi_business_target_weekService {

	@Autowired
	YdbiBusinessTargetWeekMapper ydbiBusinessTargetWeekMapper;

	@Override
	public List<YdbiBusinessTargetWeek> searchData(Map paramsMap) {
		String data = (String) paramsMap.get("data_date");
		if(!StringUtils.isEmpty(data)){
		int length =  8*7;
		String weekstart = null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		 try {
				java.util.Date date = format.parse(data);
				Calendar calendar = new GregorianCalendar();  
				calendar.setTime(date);  
				calendar.add(calendar.DATE,-length);
				date=calendar.getTime(); 
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
				 weekstart = formatter.format(date);  
			} catch (ParseException e) {
				e.printStackTrace();
			}
		 paramsMap.put("weekstart",weekstart);
		}
		List<YdbiBusinessTargetWeek> businesstargetList = new ArrayList<YdbiBusinessTargetWeek>();
		if ("3".equals(paramsMap.get("sel_type"))) {
			businesstargetList = ydbiBusinessTargetWeekMapper.selectAll(paramsMap);
		} else if ("4".equals(paramsMap.get("sel_type"))) {
			businesstargetList = ydbiBusinessTargetWeekMapper.selectFinish(paramsMap);
		} else if ("5".equals(paramsMap.get("sel_type"))) {
			businesstargetList = ydbiBusinessTargetWeekMapper.selectVolat(paramsMap);
		} 
		
		return businesstargetList;
	}

}