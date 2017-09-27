package com.yd.ydbi.business.dao;

import java.util.List;
import java.util.Map;

import com.yd.ydbi.business.model.BasSBusiDelv;
import com.yd.ydbi.business.model.MonthGrowthRate;
import com.yd.ydbi.business.model.MonthVolatility;

@SuppressWarnings("rawtypes")
public interface BasSBusiDelvMapper {

	BasSBusiDelv selectBasSBusiDelvById(Map id);

	BasSBusiDelv selectBasSBusiDelvByMode(BasSBusiDelv basSBusiDelv);

	Map<String, Object> signAQuantity(Map<String, Object> paramsMap);

	Map<String, Object> selectDwmAvgAmountForSig(Map<String, Object> paramsMap);

	Map<String, Object> selectSignTrend(Map<String, Object> paramsMap);

	List<MonthGrowthRate> monthGrowthRateForSign(Map<String, Object> paramMap);

	List<MonthVolatility> monthVolatilityForSign(Map<String, Object> paramMap);

	List<Map<String, Object>> delvQTrend(Map<String, Object> paramMap);

	List<Map<String, Object>> delvMTrend(Map<String, Object> paramMap);

	List<Map<String, Object>> delvWTrend(Map<String, Object> paramMap);

	List<Map<String, Object>> delvDTrend(Map<String, Object> paramMap);

	Map<String, Object> signAQuantityForNull(Map<String, Object> paramsMap);
}
