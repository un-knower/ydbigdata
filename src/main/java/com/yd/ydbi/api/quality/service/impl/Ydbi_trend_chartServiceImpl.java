package com.yd.ydbi.api.quality.service.impl;

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
import org.springframework.util.ObjectUtils;

import com.yd.ydbi.api.quality.dao.YdbiTrendChartMapper;
import com.yd.ydbi.api.quality.model.YdbiTrendChart;
import com.yd.ydbi.api.quality.service.Ydbi_trend_chartService;
import com.yd.ydbi.tools.AnyTrend;
import com.yd.ydbi.ys.model.SC_AloKbtrend;

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
public class Ydbi_trend_chartServiceImpl implements Ydbi_trend_chartService {

	@Autowired
	YdbiTrendChartMapper ydbiTrendChartMapper;
			
	@Override
	public List<YdbiTrendChart> searchData(Map paramsMap) {
		String data = (String) paramsMap.get("data_date");
		//周趋势日期 start
		int length =  56;
		String weekstart = null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		 try {
			 if(!StringUtils.isEmpty(data)){
				 Date date = format.parse(data);
					Calendar calendar = new GregorianCalendar();  
					calendar.setTime(date);  
					calendar.add(calendar.DATE,-length);
					date=calendar.getTime(); 
					 weekstart = format.format(date);  
			 }
			} catch (ParseException e) {
				e.printStackTrace();
			}
		 paramsMap.put("weekstart",weekstart);
		//周趋势日期 end
		//月趋势日期 start
		 if(!StringUtils.isEmpty(data)){
			 int m_length =  6;
				int month =Integer.parseInt((String) data.substring(5,7));
				int month_length = month-m_length;
				String monthend = null;
				String monthstart = null;
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
		 }
		 
		//月趋势日期 end
		//季度趋势日期 start
		 if(!StringUtils.isEmpty(data)){
			 int length_q =  4*3;
				int quarte = 0;
				if (data.substring(5,7) != null || data.substring(5,7) != "") {
					 quarte =Integer.parseInt((String) data.substring(5,7));
				}
				int quarte_length = quarte-length_q;
				String quarteend = null;
				String quartestart = null;
				String t_quartestart = null;
				String t_quarteend = null;
				if(quarte_length < 0){
					String start = (Integer.parseInt((String) data.substring(0,4))-1)+"-"+(12+quarte_length)+"-01";
					String t_start = (Integer.parseInt((String) data.substring(0,4))-2)+"-"+(12+quarte_length)+"-01";
					 try {
						Date date =  format.parse(start);
						quartestart = format.format(date);
						Date t_date =  format.parse(t_start);
						t_quartestart = format.format(t_date);
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}else{
					String start = (Integer.parseInt((String) data.substring(0,4)))+"-"+quarte_length+"-01";	
					String t_start = (Integer.parseInt((String) data.substring(0,4))-1)+"-"+quarte_length+"-01";
					 try {
						Date date =  format.parse(start);
						 quartestart = format.format(date);
						 Date t_date =  format.parse(t_start);
						 t_quartestart = format.format(t_date);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					
				}
				if((Integer.parseInt(data.substring(5,7))+1)>12){
					quarteend = (Integer.parseInt(data.substring(0,4))+1)+"-01-01";
					t_quarteend = (Integer.parseInt(data.substring(0,4)))+"-01-01";
				}else{
					String end = data.substring(0,4)+"-"+(Integer.parseInt(data.substring(5,7))+1)+"-01";
					String t_end = (Integer.parseInt(data.substring(0,4))-1)+"-"+(Integer.parseInt(data.substring(5,7))+1)+"-01";
					try {
						Date date = format.parse(end);
						quarteend =format.format(date);
						Date t_date = format.parse(t_end);
						t_quarteend =format.format(t_date);
					} catch (ParseException e) {
						e.printStackTrace();
					}	
				}
				 paramsMap.put("quartestart",quartestart);
				 paramsMap.put("quarteend", quarteend);
				 paramsMap.put("t_quartestart",t_quartestart);
				 paramsMap.put("t_quarteend", t_quarteend);
		 }
			
		//季度趋势日期 end
		List businesstargetList = new ArrayList();
		if ("1".equals(paramsMap.get("sel_type")) && "1".equals(paramsMap.get("sel_trend"))) {
			List<YdbiTrendChart> sctr=new ArrayList<YdbiTrendChart>();
			for (int i = 14; -1<i && i <= 14; i--) {
					paramsMap.put("daystart", AnyTrend.getDateDec1(data, i));
				sctr = ydbiTrendChartMapper.selectDelay(paramsMap);
				if (!ObjectUtils.isEmpty(sctr)) {
					businesstargetList.add(sctr);
				}
			}
			
		}else if ("2".equals(paramsMap.get("sel_type")) && "1".equals(paramsMap.get("sel_trend"))) {
			List<YdbiTrendChart> sctr=new ArrayList<YdbiTrendChart>();
			for (int i = 14; -1<i && i <= 14; i--) {
					paramsMap.put("daystart", AnyTrend.getDateDec1(data, i));
				sctr =ydbiTrendChartMapper.selectWrong(paramsMap);
				if (!ObjectUtils.isEmpty(sctr)) {
					businesstargetList.add( sctr);
				}
			}
		}else if ("3".equals(paramsMap.get("sel_type")) && "1".equals(paramsMap.get("sel_trend"))) {
			List<YdbiTrendChart> sctr=new ArrayList<YdbiTrendChart>();
			for (int i = 14; -1<i && i <= 14; i--) {
					paramsMap.put("daystart", AnyTrend.getDateDec1(data, i));
				sctr =ydbiTrendChartMapper.selectWrongpoints(paramsMap);
				if (!ObjectUtils.isEmpty(sctr)) {
					businesstargetList.add( sctr);
				}
			}	
		}else if ("4".equals(paramsMap.get("sel_type")) && "1".equals(paramsMap.get("sel_trend"))) {
			List<YdbiTrendChart> sctr=new ArrayList<YdbiTrendChart>();
			for (int i = 14; -1<i && i <= 14; i--) {
					paramsMap.put("daystart", AnyTrend.getDateDec1(data, i));
				sctr =ydbiTrendChartMapper.selectWrongpackage(paramsMap);
				if (!ObjectUtils.isEmpty(sctr)) {
					businesstargetList.add( sctr);
				}
			}
		}else if ("5".equals(paramsMap.get("sel_type")) && "1".equals(paramsMap.get("sel_trend"))) {
			List<YdbiTrendChart> sctr=new ArrayList<YdbiTrendChart>();
			for (int i = 14; -1<i && i <= 14; i--) {
					paramsMap.put("daystart", AnyTrend.getDateDec1(data, i));
				sctr =ydbiTrendChartMapper.selectLoss(paramsMap);
				if (!ObjectUtils.isEmpty(sctr)) {
					businesstargetList.add(sctr);
				}
			}
		}else if ("6".equals(paramsMap.get("sel_type")) && "1".equals(paramsMap.get("sel_trend"))) {
			List<YdbiTrendChart> sctr=new ArrayList<YdbiTrendChart>();
			for (int i = 14; -1<i && i <= 14; i--) {
					paramsMap.put("daystart", AnyTrend.getDateDec1(data, i));
				sctr =ydbiTrendChartMapper.selectRetention(paramsMap);
				if (!ObjectUtils.isEmpty(sctr)) {
					businesstargetList.add(sctr);
				}
			}
		}else if ("1".equals(paramsMap.get("sel_type")) && "2".equals(paramsMap.get("sel_trend"))) {
			businesstargetList = ydbiTrendChartMapper.selectWeekDelay(paramsMap);
		}else if ("2".equals(paramsMap.get("sel_type")) && "2".equals(paramsMap.get("sel_trend"))) {
			businesstargetList = ydbiTrendChartMapper.selectWeekWrong(paramsMap);
		}else if ("3".equals(paramsMap.get("sel_type")) && "2".equals(paramsMap.get("sel_trend"))) {
			businesstargetList = ydbiTrendChartMapper.selectWeekWrongpoints(paramsMap);
		}else if ("4".equals(paramsMap.get("sel_type")) && "2".equals(paramsMap.get("sel_trend"))) {
			businesstargetList = ydbiTrendChartMapper.selectWeekWrongpackage(paramsMap);
		}else if ("5".equals(paramsMap.get("sel_type")) && "2".equals(paramsMap.get("sel_trend"))) {
			businesstargetList = ydbiTrendChartMapper.selectWeekLoss(paramsMap);
		}else if ("6".equals(paramsMap.get("sel_type")) && "2".equals(paramsMap.get("sel_trend"))) {
			businesstargetList = ydbiTrendChartMapper.selectWeekRetention(paramsMap);
		}else if ("1".equals(paramsMap.get("sel_type")) && "3".equals(paramsMap.get("sel_trend"))) {
			businesstargetList = ydbiTrendChartMapper.selectMonthDelay(paramsMap);
		}else if ("2".equals(paramsMap.get("sel_type")) && "3".equals(paramsMap.get("sel_trend"))) {
			businesstargetList = ydbiTrendChartMapper.selectMonthWrong(paramsMap);
		}else if ("3".equals(paramsMap.get("sel_type")) && "3".equals(paramsMap.get("sel_trend"))) {
			businesstargetList = ydbiTrendChartMapper.selectMonthWrongpoints(paramsMap);
		}else if ("4".equals(paramsMap.get("sel_type")) && "3".equals(paramsMap.get("sel_trend"))) {
			businesstargetList = ydbiTrendChartMapper.selectMonthWrongpackage(paramsMap);
		}else if ("5".equals(paramsMap.get("sel_type")) && "3".equals(paramsMap.get("sel_trend"))) {
			businesstargetList = ydbiTrendChartMapper.selectMonthLoss(paramsMap);
		}else if ("6".equals(paramsMap.get("sel_type")) && "3".equals(paramsMap.get("sel_trend"))) {
			businesstargetList = ydbiTrendChartMapper.selectMonthRetention(paramsMap);
		}else if ("1".equals(paramsMap.get("sel_type")) && "4".equals(paramsMap.get("sel_trend"))) {
			businesstargetList = ydbiTrendChartMapper.selectQuarterDelay(paramsMap);
		}else if ("2".equals(paramsMap.get("sel_type")) && "4".equals(paramsMap.get("sel_trend"))) {
			businesstargetList = ydbiTrendChartMapper.selectQuarterWrong(paramsMap);
		}else if ("3".equals(paramsMap.get("sel_type")) && "4".equals(paramsMap.get("sel_trend"))) {
			businesstargetList = ydbiTrendChartMapper.selectQuarterWrongpoints(paramsMap);
		}else if ("4".equals(paramsMap.get("sel_type")) && "4".equals(paramsMap.get("sel_trend"))) {
			businesstargetList = ydbiTrendChartMapper.selectQuarterWrongpackage(paramsMap);
		}else if ("5".equals(paramsMap.get("sel_type")) && "4".equals(paramsMap.get("sel_trend"))) {
			businesstargetList = ydbiTrendChartMapper.selectQuarterLoss(paramsMap);
		}else if ("6".equals(paramsMap.get("sel_type")) && "4".equals(paramsMap.get("sel_trend"))) {
			businesstargetList = ydbiTrendChartMapper.selectQuarterRetention(paramsMap);
		}
		
		return businesstargetList;
	}

}