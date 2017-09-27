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

import com.yd.ydbi.api.quality.dao.YdbiInterestRateMapper;
import com.yd.ydbi.api.quality.model.YdbiInterestRate;
import com.yd.ydbi.api.quality.service.Ydbi_interest_rateService;

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
public class Ydbi_interest_rateServiceImpl implements Ydbi_interest_rateService {

	@Autowired
	YdbiInterestRateMapper ydbiInterestRateMapper;

	@Override
	public List<YdbiInterestRate> searchData(Map paramsMap) {
		String data = (String) paramsMap.get("data_date");
		 if(!StringUtils.isEmpty(data)){
			 String daystart = data.substring(0,4)+"-01-01";
				int end = Integer.parseInt(data.substring(0,4))+1;
				String dayend = end+"-01-01";
				paramsMap.put("daystart", daystart);
				paramsMap.put("dayend", dayend);
		 }
		 //利率月日时间 start
		 if(!StringUtils.isEmpty(data)){
				String monthstart = null;
				Date sdate=null;
			    Date eDate=null;
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				monthstart = data.substring(0,8)+"01";
				try {
		             sdate=format.parse(monthstart);
		             eDate=format.parse(data);
		        } catch (Exception e) {
		              e.printStackTrace();
		        }

		   int betweendays=(int) ((eDate.getTime()-sdate.getTime())/(1000 * 60 * 60 *24)+0.5)+1;//天数间隔
				paramsMap.put("betweendays",betweendays);
				paramsMap.put("monthstart",monthstart);
			}
		 // 利率月日时间 end
		
		List<YdbiInterestRate> businesstargetList = new ArrayList<YdbiInterestRate>();
		if ("1".equals(paramsMap.get("sel_type")) && "".equals(paramsMap.get("sel_trend"))) {
			businesstargetList = ydbiInterestRateMapper.selectDelay(paramsMap);
		}else if ("2".equals(paramsMap.get("sel_type")) && "".equals(paramsMap.get("sel_trend"))) {
			businesstargetList = ydbiInterestRateMapper.selectWrong(paramsMap);
		}else if ("3".equals(paramsMap.get("sel_type")) && "".equals(paramsMap.get("sel_trend"))) {
			businesstargetList = ydbiInterestRateMapper.selectWrongpoints(paramsMap);
		}else if ("4".equals(paramsMap.get("sel_type")) && "".equals(paramsMap.get("sel_trend"))) {
			businesstargetList = ydbiInterestRateMapper.selectWrongpackage(paramsMap);
		}else if ("5".equals(paramsMap.get("sel_type")) && "".equals(paramsMap.get("sel_trend"))) {
			businesstargetList = ydbiInterestRateMapper.selectLoss(paramsMap);
		}else if ("6".equals(paramsMap.get("sel_type")) && "".equals(paramsMap.get("sel_trend"))) {
			businesstargetList = ydbiInterestRateMapper.selectRetention(paramsMap);
		}else if ("1".equals(paramsMap.get("sel_type")) && "1".equals(paramsMap.get("sel_trend")) && "1".equals(paramsMap.get("sel_line"))) {
			businesstargetList = ydbiInterestRateMapper.selectDelayline(paramsMap);
		}else if ("2".equals(paramsMap.get("sel_type")) && "1".equals(paramsMap.get("sel_trend")) && "1".equals(paramsMap.get("sel_line"))) {
			businesstargetList = ydbiInterestRateMapper.selectWrongline(paramsMap);
		}else if ("3".equals(paramsMap.get("sel_type")) && "1".equals(paramsMap.get("sel_trend")) && "1".equals(paramsMap.get("sel_line"))) {
			businesstargetList = ydbiInterestRateMapper.selectWrongpointsline(paramsMap);
		}else if ("4".equals(paramsMap.get("sel_type")) && "1".equals(paramsMap.get("sel_trend")) && "1".equals(paramsMap.get("sel_line"))) {
			businesstargetList = ydbiInterestRateMapper.selectWrongpackageline(paramsMap);
		}else if ("5".equals(paramsMap.get("sel_type")) && "1".equals(paramsMap.get("sel_trend")) && "1".equals(paramsMap.get("sel_line"))) {
			businesstargetList = ydbiInterestRateMapper.selectLossline(paramsMap);
		}else if ("6".equals(paramsMap.get("sel_type")) && "1".equals(paramsMap.get("sel_trend")) && "1".equals(paramsMap.get("sel_line"))) {
			businesstargetList = ydbiInterestRateMapper.selectRetentionline(paramsMap);
		}
		return businesstargetList;
	}

}