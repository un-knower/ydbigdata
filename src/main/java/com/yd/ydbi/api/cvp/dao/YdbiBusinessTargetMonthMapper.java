package com.yd.ydbi.api.cvp.dao;

import java.util.List;
import java.util.Map;

import com.yd.ydbi.api.cvp.model.YdbiBusinessTarget;
import com.yd.ydbi.api.cvp.model.YdbiBusinessTargetMonth;
import com.yd.ydbi.api.cvp.model.YdbiBusinessTargetWeek;

public interface YdbiBusinessTargetMonthMapper {
	
	// 月趋势
	List<YdbiBusinessTargetMonth> selectAll(Map paramsMap);
	// 完成率排名
	List<YdbiBusinessTargetMonth> selectFinish(Map paramsMap);
	// 波动率排名
	List<YdbiBusinessTargetMonth> selectVolat(Map paramsMap);
}