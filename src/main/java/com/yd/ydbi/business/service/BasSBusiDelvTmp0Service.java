package com.yd.ydbi.business.service;

import java.util.List;
import java.util.Map;

public interface BasSBusiDelvTmp0Service {

	Map<String, Object> selectSentSuccessRateinSingleDay(Map<String, Object> paramMap);

	Map<String, Object> dwmAvgAmount(Map<String, Object> paramMap);

	List<Map<String, Object>> cfmRcvRtQTrend(Map<String, Object> paramMap);

	List<Map<String, Object>> cfmRcvRtMTrend(Map<String, Object> paramMap);

	List<Map<String, Object>> cfmRcvRtWTrend(Map<String, Object> paramMap);

	List<Map<String, Object>> cfmRcvRtDTrend(Map<String, Object> paramMap);
}
