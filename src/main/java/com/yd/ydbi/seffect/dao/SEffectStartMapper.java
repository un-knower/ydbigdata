package com.yd.ydbi.seffect.dao;

import java.util.List;
import java.util.Map;

public interface SEffectStartMapper {
	Map<String, Object> getSeffectStart(Map<String, Object> paramMap);

	Map<String, Object> selectDwmAvgAmount(Map<String, Object> paramMap);

	List<Map<String, Object>> seffectStartDTrend(Map<String, Object> paramMap);

	List<Map<String, Object>> seffectStartWTrend(Map<String, Object> paramMap);

	List<Map<String, Object>> seffectStartMTrend(Map<String, Object> paramMap);

	List<Map<String, Object>> seffectStartQTrend(Map<String, Object> paramMap);

	List<Map<String, Object>> seffectStartCp(Map<String, Object> paramMap);

	List<Map<String, Object>> seffectStartSort(Map<String, Object> paramMap);
	
	List<Map<String, Object>> seffectPicSlowest10City(Map<String, Object> paramMap);
	
	List<Map<String, Object>> seffectStartHighestOvertimeCp10City(Map<String, Object> paramMap);

	List<Map<String, Object>> seffectStartHighestOvertimeOder10City(Map<String, Object> paramMap);
}