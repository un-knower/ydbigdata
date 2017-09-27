package com.yd.ydbi.seffect.dao;

import java.util.List;
import java.util.Map;

public interface SEffectDelvMapper {
	Map<String, Object> getSeffectDelv(Map<String, Object> paramMap);

	Map<String, Object> selectDwmAvgAmount(Map<String, Object> paramMap);

	List<Map<String, Object>> seffectDelvDTrend(Map<String, Object> paramMap);

	List<Map<String, Object>> seffectDelvWTrend(Map<String, Object> paramMap);

	List<Map<String, Object>> seffectDelvMTrend(Map<String, Object> paramMap);

	List<Map<String, Object>> seffectDelvQTrend(Map<String, Object> paramMap);

	List<Map<String, Object>> seffectDelvCp(Map<String, Object> paramMap);

	List<Map<String, Object>> seffectDelvSort(Map<String, Object> paramMap);
	
	List<Map<String, Object>> seffectPicSlowest10City(Map<String, Object> paramMap);
	
	List<Map<String, Object>> seffectDelvHighestOvertimeCp10City(Map<String, Object> paramMap);

	List<Map<String, Object>> seffectDelvHighestOvertimeOder10City(Map<String, Object> paramMap);
}