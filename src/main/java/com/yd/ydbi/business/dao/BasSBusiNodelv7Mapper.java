package com.yd.ydbi.business.dao;

import java.util.List;
import java.util.Map;

import com.yd.ydbi.business.model.BasSBusiNodelv7;
import com.yd.ydbi.business.model.SitSucRate;

public interface BasSBusiNodelv7Mapper {

    BasSBusiNodelv7 selectByPrimaryKey(Integer id);
    
    Map<String, Object> sevenDaysWithoutReceiptRate(Map<String, Object> paramMap);

	Map<String, Object> dwmAvgAmount(Map<String, Object> paramMap);

	List<Map<String, Object>> cfmRcvRt7QTrend(Map<String, Object> paramMap);

	List<Map<String, Object>> cfmRcvRt7MTrend(Map<String, Object> paramMap);

	List<Map<String, Object>> cfmRcvRt7WTrend(Map<String, Object> paramMap);

	List<Map<String, Object>> cfmRcvRt7DTrend(Map<String, Object> paramMap);

	List<SitSucRate> getSiteAndSucRate(Map<String, Object> paramMap);

	List<Map<String, Object>> getStandardDbcdTrend(Map<String, Object> paramMap);

	List<SitSucRate> getSiteAndSucRateForNull(Map<String, Object> paramMap);

	Map<String, Object> sevenDaysWithoutReceiptRateForNull(Map<String, Object> paramMap);
}