package com.yd.ydbi.seffect.dao;

import java.util.List;
import java.util.Map;

public interface SEffectTransOpMapper {
	Map<String, Object> getSeffectTransOp(Map<String, Object> paramMap);

	Map<String, Object> selectDwmAvgAmount(Map<String, Object> paramMap);

	List<Map<String, Object>> seffectTransOpDTrend(Map<String, Object> paramMap);

	List<Map<String, Object>> seffectTransOpWTrend(Map<String, Object> paramMap);

	List<Map<String, Object>> seffectTransOpMTrendForActive(Map<String, Object> paramMap);

	List<Map<String, Object>> seffectTransOpMTrendForSameTime(Map<String, Object> paramMap);

	List<Map<String, Object>> dbctSortDay(Map<String, Object> paramMap);

	List<Map<String, Object>> dbctSortWeek(Map<String, Object> paramMap);

	List<Map<String, Object>> dbctSortMonth(Map<String, Object> paramMap);

}