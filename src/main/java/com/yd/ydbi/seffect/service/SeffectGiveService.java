package com.yd.ydbi.seffect.service;

import java.util.Map;

public interface SeffectGiveService {

	Map<String, Object> getSeffectGive(Map<String, Object> paramMap);

	Map<String, Object> selectDwmAvgAmount(Map<String, Object> paramMap);

	Map<String, Object> seffectTrend(Map<String, Object> paramMap);
	
	int getDTotalRows(Map<String, Object> paramMap);

}
