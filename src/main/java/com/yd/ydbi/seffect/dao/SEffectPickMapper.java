package com.yd.ydbi.seffect.dao;

import java.util.List;
import java.util.Map;

public interface SEffectPickMapper {

	Map<String, Object> getSeffectPick(Map<String, Object> paramMap);

	Map<String, Object> selectDwmAvgAmount(Map<String, Object> paramMap);

	List<Map<String, Object>> seffectPickDTrend(Map<String, Object> paramMap);

	List<Map<String, Object>> seffectPickWTrend(Map<String, Object> paramMap);

	List<Map<String, Object>> seffectPickMTrend(Map<String, Object> paramMap);

	List<Map<String, Object>> seffectPickQTrend(Map<String, Object> paramMap);

	List<Map<String, Object>> seffectPickCp(Map<String, Object> paramMap);

	List<Map<String, Object>> seffectPickSort(Map<String, Object> paramMap);
	
	List<Map<String, Object>> seffectPicSlowest10City(Map<String, Object> paramMap);
	
	List<Map<String, Object>> seffectPickHighestOvertimeCp10City(Map<String, Object> paramMap);

	List<Map<String, Object>> seffectPickHighestOvertimeOder10City(Map<String, Object> paramMap);
	
	

}