package com.yd.ydbi.seffect.service;

import java.util.List;
import java.util.Map;

import com.yd.ydbi.business.model.MonthGrowthRate;

public interface SeffectTransOpService {

	Map<String, Object> getSeffectTransOp(Map<String, Object> paramMap);

	Map<String, Object> selectDwmAvgAmount(Map<String, Object> paramMap);

	Map<String, Object> seffectTrend(Map<String, Object> paramMap);


}
