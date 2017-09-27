package com.yd.ydbi.api.cvp.dao;

import java.util.List;
import java.util.Map;

import com.yd.ydbi.api.cvp.model.YdbiBusinessTarget;

public interface YdbiBusinessTargetMapper {
	
	// 目标值
	List<YdbiBusinessTarget> selectAll(Map paramsMap);
	// 获取列表
	List<YdbiBusinessTarget> selectLine(Map paramsMap);
	// 获取列表
	List<YdbiBusinessTarget> selectXQ(Map paramsMap);
	// 获取日趋势
	List<YdbiBusinessTarget> selectDay(Map paramsMap);
	// 完成率排名
	List<YdbiBusinessTarget> selectFinish(Map paramsMap);
	// 波动率排名
	List<YdbiBusinessTarget> selectVolat(Map paramsMap);
}