package com.yd.ydbi.business.dao;

import java.util.List;
import java.util.Map;

import com.yd.ydbi.business.model.BasSBusiPick;
import com.yd.ydbi.business.model.MonthGrowthRate;
import com.yd.ydbi.business.model.MonthVolatility;

public interface BasSBusiPickMapper {

	Map<String, Object> selectTookAQuantity(Map<String, Object> paramMap);

	Map<String, Object> dwmAvgAmount(Map<String, Object> paramMap);

	Map<String, Object> quarterlyTrend(Map<String, Object> paramMap);
	//揽件量趋势(季度)
	List<Map<String, Object>> pickQTrend(Map<String, Object> paramMap);

	List<MonthGrowthRate> monthGrowthRate(Map<String, Object> paramMap);

	List<MonthVolatility> monthVolatility(Map<String, Object> paramMap);

	List<Map<String, Object>> pickMTrend(Map<String, Object> paramMap);

	List<Map<String, Object>> pickWTrend(Map<String, Object> paramMap);

	List<Map<String, Object>> pickDTrend(Map<String, Object> paramMap);

	//揽件量(月同期值)
	List<Map<String, Object>> pickMTrendSameTime(Map<String, Object> paramMap);

	Map<String, Object> selectTookAQuantityForNull(Map<String, Object> paramMap);
	
	
}