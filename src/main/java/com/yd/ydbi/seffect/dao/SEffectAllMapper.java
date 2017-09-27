package com.yd.ydbi.seffect.dao;

import java.util.List;
import java.util.Map;

public interface SEffectAllMapper {
	Map<String, Object> getSeffectAll(Map<String, Object> paramMap);

	Map<String, Object> selectDwmAvgAmount(Map<String, Object> paramMap);

	List<Map<String, Object>> seffectAllDTrend(Map<String, Object> paramMap);

	List<Map<String, Object>> seffectAllWTrend(Map<String, Object> paramMap);

	List<Map<String, Object>> seffectAllMTrend(Map<String, Object> paramMap);

	List<Map<String, Object>> getCntProportion(Map<String, Object> paramMap);

	List<Map<String, Object>> getSlowly10ProvLine(Map<String, Object> paramMap);

	List<Map<String, Object>> getSlowly10CityLine(Map<String, Object> paramMap);
}