package com.yd.ydbi.business.dao;

import java.util.List;
import java.util.Map;

import com.yd.ydbi.business.model.BasSBusiSnd;
import com.yd.ydbi.business.model.MonthGrowthRate;
import com.yd.ydbi.business.model.MonthVolatility;
import com.yd.ydbi.business.model.SitSucRate;
import com.yd.ydbi.business.model.SuccessRateSingleDay;

public interface BasSBusiSndMapper {

	BasSBusiSnd selectByPrimaryKey(Integer id);

	Map<String, Object> sendAQuantity(Map<String, Object> paramMap);

	Map<String, Object> dwmAvgSucRate(Map<String, Object> paramMap);

	List<SuccessRateSingleDay> successRateTheDaySnd(Map<String, Object> paramsMap);

	List<SitSucRate> siteAndSucRate(Map<String, Object> paramMap);
	
	List<Map<String, Object>> standardNetworkTrend(Map<String, Object> paramsMap);

	Map<String, Object> selectDwmAvgAmountForSnd(Map<String, Object> paramMap);
	
	Map<String, Object> selectSndTrend(Map<String, Object> paramMap);

	List<MonthGrowthRate> monthGrowthRateForSnd(Map<String, Object> paramMap);

	List<MonthVolatility> monthVolatilityForSnd(Map<String, Object> paramMap);

	List<Map<String, Object>> delvQTrend(Map<String, Object> paramMap);

	List<Map<String, Object>> delvMTrend(Map<String, Object> paramMap);

	List<Map<String, Object>> delvWTrend(Map<String, Object> paramMap);

	List<Map<String, Object>> delvDTrend(Map<String, Object> paramMap);

	List<SitSucRate> siteAndSucRateForNull(Map<String, Object> paramMap);

	Map<String, Object> sendAQuantityForNull(Map<String, Object> paramMap);
	
}