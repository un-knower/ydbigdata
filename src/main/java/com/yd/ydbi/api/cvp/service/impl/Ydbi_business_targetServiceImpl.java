package com.yd.ydbi.api.cvp.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.ydbi.api.cvp.dao.YdbiBusinessTargetMapper;
import com.yd.ydbi.api.cvp.model.YdbiBusinessTarget;
import com.yd.ydbi.api.cvp.service.Ydbi_business_targetService;

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
public class Ydbi_business_targetServiceImpl implements Ydbi_business_targetService {

	@Autowired
	YdbiBusinessTargetMapper ydbiBusinessTargetMapper;

	@Override
	public List<YdbiBusinessTarget> searchData(Map paramsMap) {
		String data = (String) paramsMap.get("data_date");
		int length =  15;
		String start = null;
		Date date = null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		 try {
			 if(!StringUtils.isEmpty(data)){
					date = format.parse(data);
					Calendar calendar = new GregorianCalendar();  
					calendar.setTime(date);  
					calendar.add(calendar.DATE,-length);
					date=calendar.getTime(); 
					 start = format.format(date);  
					paramsMap.put("start",start);					
					String dayone = data.substring(0,8)+"01";
					int daynum= Integer.parseInt(data.substring(8,10));
					paramsMap.put("dayone", dayone);
					paramsMap.put("daynum", daynum);
				 }  
			} catch (ParseException e) {
				e.printStackTrace();
			}
		List<YdbiBusinessTarget> businesstargetList = new ArrayList<YdbiBusinessTarget>();
		if ("1".equals(paramsMap.get("sel_type"))) {
			businesstargetList = ydbiBusinessTargetMapper.selectAll(paramsMap);
		} else if ("2".equals(paramsMap.get("sel_type"))) {
			businesstargetList = ydbiBusinessTargetMapper.selectLine(paramsMap);
		} else if ("3".equals(paramsMap.get("sel_type"))) {
			businesstargetList = ydbiBusinessTargetMapper.selectDay(paramsMap);
		} else if ("4".equals(paramsMap.get("sel_type"))) {
			System.out.println("sel_type=4");
			businesstargetList = ydbiBusinessTargetMapper.selectFinish(paramsMap);
		} else if ("5".equals(paramsMap.get("sel_type"))) {
			businesstargetList = ydbiBusinessTargetMapper.selectVolat(paramsMap);
		} else if ("X".equals(paramsMap.get("sel_type"))) {
			businesstargetList = ydbiBusinessTargetMapper.selectXQ(paramsMap);
		} 
		return businesstargetList;
	}

}