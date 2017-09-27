package com.yd.ydbi.api.quality.dao;

import java.util.List;
import java.util.Map;

import com.yd.ydbi.api.quality.model.YdbiTrendChart;

public interface YdbiTrendChartMapper {
	
	// 日趋势-延误
	List<YdbiTrendChart> selectDelay(Map paramsMap);
	// 日趋势-错发
	List<YdbiTrendChart> selectWrong(Map paramsMap);
	// 日趋势-错分
	List<YdbiTrendChart> selectWrongpoints(Map paramsMap);
	// 日趋势-集错包
	List<YdbiTrendChart> selectWrongpackage(Map paramsMap);
	// 日趋势-遗失
	List<YdbiTrendChart> selectLoss(Map paramsMap);
	// 日趋势-滞留
	List<YdbiTrendChart> selectRetention(Map paramsMap);
	
	// 周趋势-延误
	List<YdbiTrendChart> selectWeekDelay(Map paramsMap);
	// 周趋势-错发
	List<YdbiTrendChart> selectWeekWrong(Map paramsMap);
	// 周趋势-错分
	List<YdbiTrendChart> selectWeekWrongpoints(Map paramsMap);
	// 周趋势-集错包
	List<YdbiTrendChart> selectWeekWrongpackage(Map paramsMap);
	// 周趋势-遗失
	List<YdbiTrendChart> selectWeekLoss(Map paramsMap);
	// 周趋势-滞留
	List<YdbiTrendChart> selectWeekRetention(Map paramsMap);
	
	// 月趋势-延误
	List<YdbiTrendChart> selectMonthDelay(Map paramsMap);
	// 月趋势-错发
	List<YdbiTrendChart> selectMonthWrong(Map paramsMap);
	// 月趋势-错分
	List<YdbiTrendChart> selectMonthWrongpoints(Map paramsMap);
	// 月趋势-集错包
	List<YdbiTrendChart> selectMonthWrongpackage(Map paramsMap);
	// 月趋势-遗失
	List<YdbiTrendChart> selectMonthLoss(Map paramsMap);
	// 月趋势-滞留
	List<YdbiTrendChart> selectMonthRetention(Map paramsMap);
	
	// 季度趋势-延误
	List<YdbiTrendChart> selectQuarterDelay(Map paramsMap);
	// 季度趋势-错发
	List<YdbiTrendChart> selectQuarterWrong(Map paramsMap);
	// 季度趋势-错分
	List<YdbiTrendChart> selectQuarterWrongpoints(Map paramsMap);
	// 季度趋势-集错包
	List<YdbiTrendChart> selectQuarterWrongpackage(Map paramsMap);
	// 季度趋势-遗失
	List<YdbiTrendChart> selectQuarterLoss(Map paramsMap);
	// 季度趋势-滞留
	List<YdbiTrendChart> selectQuarterRetention(Map paramsMap);
}