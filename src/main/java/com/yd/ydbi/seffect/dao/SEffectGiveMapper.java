package com.yd.ydbi.seffect.dao;

import java.util.List;
import java.util.Map;

public interface SEffectGiveMapper {
	Map<String, Object> getSeffectGive(Map<String, Object> paramMap);

	Map<String, Object> selectDwmAvgAmount(Map<String, Object> paramMap);

	List<Map<String, Object>> seffectGiveDTrend(Map<String, Object> paramMap);

	List<Map<String, Object>> seffectGiveWTrend(Map<String, Object> paramMap);

	List<Map<String, Object>> seffectGiveMTrend(Map<String, Object> paramMap);

	List<Map<String, Object>> seffectGiveQTrend(Map<String, Object> paramMap);

	List<Map<String, Object>> seffectGiveCp(Map<String, Object> paramMap);

	List<Map<String, Object>> seffectGiveSort(Map<String, Object> paramMap);

	List<Map<String, Object>> seffectPicSlowest10City(Map<String, Object> paramMap);

	List<Map<String, Object>> seffectGiveHighestOvertimeCp10City(Map<String, Object> paramMap);

	List<Map<String, Object>> seffectGiveHighestOvertimeOder10City(Map<String, Object> paramMap);

	List<Map<String, Object>> dbctSortDay(Map<String, Object> paramMap);

	int getDTotalRows(Map<String, Object> paramMap);

	List<Map<String, Object>> dbctSortWeek(Map<String, Object> paramMap);

	List<Map<String, Object>> dbctSortMonth(Map<String, Object> paramMap);

}