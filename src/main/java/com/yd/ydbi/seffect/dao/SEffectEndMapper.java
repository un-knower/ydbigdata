package com.yd.ydbi.seffect.dao;

import java.util.List;
import java.util.Map;

public interface SEffectEndMapper {
	Map<String, Object> getSeffectEnd(Map<String, Object> paramMap);

	Map<String, Object> selectDwmAvgAmount(Map<String, Object> paramMap);

	List<Map<String, Object>> seffectEndDTrend(Map<String, Object> paramMap);

	List<Map<String, Object>> seffectEndWTrend(Map<String, Object> paramMap);

	List<Map<String, Object>> seffectEndMTrend(Map<String, Object> paramMap);

	List<Map<String, Object>> seffectEndQTrend(Map<String, Object> paramMap);

	List<Map<String, Object>> seffectEndCp(Map<String, Object> paramMap);

	List<Map<String, Object>> seffectEndSort(Map<String, Object> paramMap);
	
	List<Map<String, Object>> seffectPicSlowest10City(Map<String, Object> paramMap);
	
	List<Map<String, Object>> seffectEndHighestOvertimeCp10City(Map<String, Object> paramMap);

	List<Map<String, Object>> seffectEndHighestOvertimeOder10City(Map<String, Object> paramMap);
}