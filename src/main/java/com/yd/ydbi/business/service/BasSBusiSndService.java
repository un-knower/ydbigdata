package com.yd.ydbi.business.service;

import java.util.List;
import java.util.Map;

import com.yd.ydbi.business.model.MonthGrowthRate;
import com.yd.ydbi.business.model.MonthVolatility;
import com.yd.ydbi.business.model.SitSucRate;
import com.yd.ydbi.business.model.SuccessRateSingleDay;

public interface BasSBusiSndService {

	Map<String, Object> selectSendAQuantity(Map<String, Object> paramMap);

	Map<String, Object> selectDwmAvgSucRate(Map<String, Object> paramMap);

	List<SitSucRate> getSiteAndSucRate(Map<String, Object> paramMap);

	List<Map<String, Object>> selectStandardNetworkTrend(Map<String, Object> paramsMap);

	Map<String, Object> selectDwmAvgAmountForSnd(Map<String, Object> paramMap);

	Map<String, Object> selectSndTrend(Map<String, Object> paramMap);

	List<MonthGrowthRate> monthGrowthRateForSnd(Map<String, Object> paramMap);

	List<MonthVolatility> monthVolatilityForSnd(Map<String, Object> paramMap);

	// 派件量(季度趋势)
	List<Map<String, Object>> delvQTrend(Map<String, Object> paramMap);

	// 派件量(月度趋势)
	List<Map<String, Object>> delvMTrend(Map<String, Object> paramMap);

	// 派件量(周度趋势)
	List<Map<String, Object>> delvWTrend(Map<String, Object> paramMap);

	// 派件量(日度趋势)
	List<Map<String, Object>> delvDTrend(Map<String, Object> paramMap);

}
