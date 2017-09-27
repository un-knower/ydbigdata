package com.yd.ydbi.api.quality.dao;

import java.util.List;
import java.util.Map;

import com.yd.ydbi.api.quality.model.YdbiInterestRate;

public interface YdbiInterestRateMapper {
	
	// 延误率
	List<YdbiInterestRate> selectDelay(Map paramsMap);
	// 错发率
	List<YdbiInterestRate> selectWrong(Map paramsMap);
	// 错分率
	List<YdbiInterestRate> selectWrongpoints(Map paramsMap);
	// 集错包率
	List<YdbiInterestRate> selectWrongpackage(Map paramsMap);
	// 遗失率
	List<YdbiInterestRate> selectLoss(Map paramsMap);
	// 滞留率
	List<YdbiInterestRate> selectRetention(Map paramsMap);
	
	// 延误率-列表
	List<YdbiInterestRate> selectDelayline(Map paramsMap);
	// 错发率-列表
	List<YdbiInterestRate> selectWrongline(Map paramsMap);
	// 错分率-列表
	List<YdbiInterestRate> selectWrongpointsline(Map paramsMap);
	// 集错包率-列表
	List<YdbiInterestRate> selectWrongpackageline(Map paramsMap);
	// 遗失率-列表
	List<YdbiInterestRate> selectLossline(Map paramsMap);
	// 滞留率-列表
	List<YdbiInterestRate> selectRetentionline(Map paramsMap);
}