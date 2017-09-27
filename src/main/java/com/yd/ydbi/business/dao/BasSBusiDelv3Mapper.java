package com.yd.ydbi.business.dao;

import java.util.List;
import java.util.Map;

import com.yd.ydbi.business.model.BasSBusiDelv3;
import com.yd.ydbi.business.model.SitSucRate;

public interface BasSBusiDelv3Mapper {

	BasSBusiDelv3 selectByPrimaryKey(Integer id);

	Map<String, Object> threeDayToSignForRate(Map<String, Object> params);

	Map<String, Object> dwmAvgAmount(Map<String, Object> paramMap);

	List<Map<String, Object>> cfmRcvRt3QTrend(Map<String, Object> paramMap);

	List<Map<String, Object>> cfmRcvRt3MTrend(Map<String, Object> paramMap);

	List<Map<String, Object>> cfmRcvRt3WTrend(Map<String, Object> paramMap);

	List<Map<String, Object>> cfmRcvRt3DTrend(Map<String, Object> paramMap);

	List<SitSucRate> getSiteAndSucRate(Map<String, Object> paramMap);

	List<Map<String, Object>> getStandardDbcdTrend(Map<String, Object> paramMap);

	List<SitSucRate> getSiteAndSucRateForNull(Map<String, Object> paramMap);

	Map<String, Object> threeDayToSignForRateForNull(Map<String, Object> paramMap);
}