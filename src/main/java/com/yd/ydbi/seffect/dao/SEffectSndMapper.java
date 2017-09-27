package com.yd.ydbi.seffect.dao;

import java.util.List;
import java.util.Map;

public interface SEffectSndMapper {
	Map<String, Object> getSeffectSnd(Map<String, Object> paramMap);

	Map<String, Object> selectDwmAvgAmount(Map<String, Object> paramMap);

	List<Map<String, Object>> seffectSndDTrend(Map<String, Object> paramMap);

	List<Map<String, Object>> seffectSndWTrend(Map<String, Object> paramMap);

	List<Map<String, Object>> seffectSndMTrend(Map<String, Object> paramMap);

	List<Map<String, Object>> seffectSndQTrend(Map<String, Object> paramMap);

	List<Map<String, Object>> seffectSndCp(Map<String, Object> paramMap);

	List<Map<String, Object>> seffectSndSort(Map<String, Object> paramMap);

	List<Map<String, Object>> seffectPicSlowest10City(Map<String, Object> paramMap);

	List<Map<String, Object>> seffectSndHighestOvertimeCp10City(Map<String, Object> paramMap);

	List<Map<String, Object>> seffectSndHighestOvertimeOder10City(Map<String, Object> paramMap);

	List<Map<String, Object>> dbctSortDay(Map<String, Object> paramMap);

	List<Map<String, Object>> dbctSortWeek(Map<String, Object> paramMap);

	List<Map<String, Object>> dbctSortMonth(Map<String, Object> paramMap);

}