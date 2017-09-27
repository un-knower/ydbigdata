package com.yd.ydbi.api.cvp.dao;

import java.util.List;
import java.util.Map;

import com.yd.ydbi.api.cvp.model.YdbiBusinessTarget;
import com.yd.ydbi.api.cvp.model.YdbiBusinessTargetMonth;
import com.yd.ydbi.api.cvp.model.YdbiBusinessTargetQuarter;
import com.yd.ydbi.api.cvp.model.YdbiBusinessTargetWeek;

public interface YdbiBusinessTargetQuarterMapper {
	
	// 季度趋势
	List<YdbiBusinessTargetQuarter> selectAll(Map paramsMap);
	// 完成率排名
	List<YdbiBusinessTargetQuarter> selectFinish(Map paramsMap);
	// 波动率排名
	List<YdbiBusinessTargetQuarter> selectVolat(Map paramsMap);
}