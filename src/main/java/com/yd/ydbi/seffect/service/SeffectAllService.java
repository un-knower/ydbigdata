package com.yd.ydbi.seffect.service;

import java.util.List;
import java.util.Map;

import com.yd.ydbi.business.model.MonthGrowthRate;

public interface SeffectAllService {

	Map<String, Object> getSeffectAll(Map<String, Object> paramMap);

	Map<String, Object> selectDwmAvgAmount(Map<String, Object> paramMap);

	List<Map<String, Object>> getCntProportion(Map<String, Object> paramMap);

	Map<String, Object> seffectTrend(Map<String, Object> paramMap);

	List<Map<String, Object>> getSlowly10ProvLine(Map<String, Object> paramMap);

	List<Map<String, Object>> getSlowly10CityLine(Map<String, Object> paramMap);
}
