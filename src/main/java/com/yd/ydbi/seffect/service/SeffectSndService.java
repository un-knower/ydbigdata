package com.yd.ydbi.seffect.service;

import java.util.Map;

public interface SeffectSndService {

	Map<String, Object> getSeffectSnd(Map<String, Object> paramMap);

	Map<String, Object> selectDwmAvgAmount(Map<String, Object> paramMap);

	Map<String, Object> seffectTrend(Map<String, Object> paramMap);

}
