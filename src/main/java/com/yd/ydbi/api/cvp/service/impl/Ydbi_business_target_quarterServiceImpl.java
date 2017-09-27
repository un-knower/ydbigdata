package com.yd.ydbi.api.cvp.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.ydbi.api.cvp.dao.YdbiBusinessTargetQuarterMapper;
import com.yd.ydbi.api.cvp.model.YdbiBusinessTargetQuarter;
import com.yd.ydbi.api.cvp.service.Ydbi_business_target_quarterService;
/**
 * <p>
 * Service实现类
 * </p>
 * <p>
 * Class: Ydbi_business_target_quarterServiceImpl
 * </p>
 *
 * @since 2017-07-12 14:47:00
 */

@Service
public class Ydbi_business_target_quarterServiceImpl implements Ydbi_business_target_quarterService {

	@Autowired
	YdbiBusinessTargetQuarterMapper ydbiBusinessTargetQuarterMapper;

	@Override
	public List<YdbiBusinessTargetQuarter> searchData(Map paramsMap) {
		String data = (String) paramsMap.get("data_date");
		if(!StringUtils.isEmpty(data)){
		int length =  4*3;
		int quarte = 0;
		if (data.substring(5,7) != null || data.substring(5,7) != "") {
			 quarte =Integer.parseInt((String) data.substring(5,7));
		}
		int quarte_length = quarte-length;
		String quarteend = null;
		String quartestart = null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		if(quarte_length < 0){
			String start = (Integer.parseInt((String) data.substring(0,4))-1)+"-"+(12+quarte_length)+"-01";
			 try {
				Date date =  format.parse(start);
				quartestart = format.format(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			 
			
			System.out.println(quartestart);
		}else{
			String start = (Integer.parseInt((String) data.substring(0,4)))+"-"+quarte_length+"-01";	
			
			 try {
				Date date =  format.parse(start);
				 quartestart = format.format(date);
				 
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
		}
		if((Integer.parseInt(data.substring(5,7))+1)>12){
			quarteend = (Integer.parseInt(data.substring(0,4))+1)+"-01-01";
		}else{
			String end = data.substring(0,4)+"-"+(Integer.parseInt(data.substring(5,7))+1)+"-01";
			try {
				Date date = format.parse(end);
				quarteend =format.format(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}	
		}
		paramsMap.put("quartestart",quartestart);
		paramsMap.put("quarteend", quarteend);
		}
		List<YdbiBusinessTargetQuarter> businesstargetList = new ArrayList<YdbiBusinessTargetQuarter>();
		if ("3".equals(paramsMap.get("sel_type"))) {	
			businesstargetList = ydbiBusinessTargetQuarterMapper.selectAll(paramsMap);
		} else if ("4".equals(paramsMap.get("sel_type"))) {
			businesstargetList = ydbiBusinessTargetQuarterMapper.selectFinish(paramsMap);
		} else if ("5".equals(paramsMap.get("sel_type"))) {
			businesstargetList = ydbiBusinessTargetQuarterMapper.selectVolat(paramsMap);
		}

		return businesstargetList;
	}
}