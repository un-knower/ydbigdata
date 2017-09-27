package com.yd.ydbi.api.quality.dao;

import java.util.List;
import java.util.Map;

import com.yd.ydbi.api.quality.model.YdbiInterestRate;
import com.yd.ydbi.api.quality.model.YdbiQualityList;

public interface YdbiQualityListMapper {
	
	// 延误率-周列表
	List<YdbiQualityList> selectDelayWeek(Map paramsMap);
	// 错发率-周列表
	List<YdbiQualityList> selectWrongWeek(Map paramsMap);
	// 错分率-周列表
	List<YdbiQualityList> selectWrongpointsWeek(Map paramsMap);
	// 集错包率-周列表
	List<YdbiQualityList> selectWrongpackageWeek(Map paramsMap);
	// 遗失率-周列表
	List<YdbiQualityList> selectLossWeek(Map paramsMap);
	// 疑似遗失率-周列表
	List<YdbiQualityList> selectRetentionWeek(Map paramsMap);
	
	// 延误率-月列表
	List<YdbiQualityList> selectDelayMonth(Map paramsMap);
	// 错发率-月列表
	List<YdbiQualityList> selectWrongMonth(Map paramsMap);
	// 错分率-月列表
	List<YdbiQualityList> selectWrongpointsMonth(Map paramsMap);
	// 集错包率-月列表
	List<YdbiQualityList> selectWrongpackageMonth(Map paramsMap);
	// 遗失率-月列表
	List<YdbiQualityList> selectLossMonth(Map paramsMap);
	// 疑似遗失率-月列表
	List<YdbiQualityList> selectRetentionMonth(Map paramsMap);
	
	// 延误率-季度列表
	List<YdbiQualityList> selectDelayQuarter(Map paramsMap);
	// 错发率-季度列表
	List<YdbiQualityList> selectWrongQuarter(Map paramsMap);
	// 错分率-季度列表
	List<YdbiQualityList> selectWrongpointsQuarter(Map paramsMap);
	// 集错包率-季度列表
	List<YdbiQualityList> selectWrongpackageQuarter(Map paramsMap);
	// 遗失率-季度列表
	List<YdbiQualityList> selectLossQuarter(Map paramsMap);
	// 疑似遗失率-季度列表
	List<YdbiQualityList> selectRetentionQuarter(Map paramsMap);
}