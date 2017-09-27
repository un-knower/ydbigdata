package com.yd.ydbi.api.cvp.dao;

import java.util.List;
import java.util.Map;

import com.yd.ydbi.api.cvp.model.YdbiBusinessTarget;
import com.yd.ydbi.api.cvp.model.YdbiBusinessTargetWeek;

public interface YdbiBusinessTargetWeekMapper {
	
	// 周趋势
	List<YdbiBusinessTargetWeek> selectAll(Map paramsMap);
	// 完成率排名
	List<YdbiBusinessTargetWeek> selectFinish(Map paramsMap);
	// 波动率排名
	List<YdbiBusinessTargetWeek> selectVolat(Map paramsMap);
}