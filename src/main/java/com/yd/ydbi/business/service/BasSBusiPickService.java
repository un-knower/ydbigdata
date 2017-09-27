package com.yd.ydbi.business.service;

import java.util.List;
import java.util.Map;

import com.yd.ydbi.business.model.MonthGrowthRate;
import com.yd.ydbi.business.model.MonthVolatility;

public interface BasSBusiPickService {

	Map<String, Object> selectTookAQuantity(Map<String, Object> paramMap);

	Map<String, Object> selectDwmAvgAmount(Map<String, Object> paramMap);

	Map<String, Object> getQuarterlyTrend(Map<String, Object> paramMap);

	// 揽件量趋势(季度)
	List<Map<String, Object>> pickQTrend(Map<String, Object> paramMap);

	List<MonthGrowthRate> getMonthGrowthRate(Map<String, Object> paramMap);

	List<MonthVolatility> getMonthVolatility(Map<String, Object> paramMap);

	// 揽件量趋势(月度)
	List<Map<String, Object>> pickMTrend(Map<String, Object> paramMap);

	// 揽件量趋势(周)
	List<Map<String, Object>> pickWTrend(Map<String, Object> paramMap);

	// 揽件量趋势(日)
	List<Map<String, Object>> pickDTrend(Map<String, Object> paramMap);

	//揽件量(月同期值)
	List<Map<String, Object>> pickMTrendSameTime(Map<String, Object> paramMap);
}
