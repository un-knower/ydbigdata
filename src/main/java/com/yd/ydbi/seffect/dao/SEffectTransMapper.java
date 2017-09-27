package com.yd.ydbi.seffect.dao;

import java.util.List;
import java.util.Map;

public interface SEffectTransMapper {
	Map<String, Object> getSeffectTrans(Map<String, Object> paramMap);

	Map<String, Object> selectDwmAvgAmount(Map<String, Object> paramMap);

	List<Map<String, Object>> seffectTransDTrend(Map<String, Object> paramMap);

	List<Map<String, Object>> seffectTransWTrend(Map<String, Object> paramMap);

	List<Map<String, Object>> seffectTransMTrend(Map<String, Object> paramMap);

	List<Map<String, Object>> getCntProportion(Map<String, Object> paramMap);

	List<Map<String, Object>> getSlowly10ProvLine(Map<String, Object> paramMap);

	List<Map<String, Object>> getSlowly10CityLine(Map<String, Object> paramMap);
}