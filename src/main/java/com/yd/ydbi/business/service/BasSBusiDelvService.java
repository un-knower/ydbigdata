package com.yd.ydbi.business.service;

import java.util.List;
import java.util.Map;

import com.yd.ydbi.business.model.BasSBusiDelv;
import com.yd.ydbi.business.model.MonthGrowthRate;
import com.yd.ydbi.business.model.MonthVolatility;

@SuppressWarnings("rawtypes")
public interface BasSBusiDelvService {

	BasSBusiDelv selectByPrimaryKey(Map id);

	BasSBusiDelv selectBasSBusiDelvByModel(BasSBusiDelv record);

	Map<String, Object> selectSignAQuantity(Map<String, Object> paramMap);

	Map<String, Object> selectDwmAvgAmountForSig(Map<String, Object> paramsMap);

	Map<String, Object> selectSignTrend(Map<String, Object> paramsMap);

	List<MonthGrowthRate> monthGrowthRateForSign(Map<String, Object> paramMap);

	List<MonthVolatility> monthVolatilityForSign(Map<String, Object> paramMap);

	// 签件量(季度趋势)
	List<Map<String, Object>> delvQTrend(Map<String, Object> paramMap);

	// 签件量(月度趋势)
	List<Map<String, Object>> delvMTrend(Map<String, Object> paramMap);

	// 签件量(周度趋势)
	List<Map<String, Object>> delvWTrend(Map<String, Object> paramMap);

	// 签件量(日度趋势)
	List<Map<String, Object>> delvDTrend(Map<String, Object> paramMap);
}
