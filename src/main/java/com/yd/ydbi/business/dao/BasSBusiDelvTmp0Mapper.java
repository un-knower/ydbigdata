package com.yd.ydbi.business.dao;

import java.util.List;
import java.util.Map;

import com.yd.ydbi.business.model.BasSBusiDelvTmp0;

public interface BasSBusiDelvTmp0Mapper {

    BasSBusiDelvTmp0 selectByPrimaryKey(Integer id);
    
    Map<String, Object> sentSuccessRateinSingleDay(Map<String, Object> paramMap);

	Map<String, Object> dwmAvgAmount(Map<String, Object> paramMap);

	List<Map<String, Object>> cfmRcvRtQTrend(Map<String, Object> paramMap);

	List<Map<String, Object>> cfmRcvRtMTrend(Map<String, Object> paramMap);

	List<Map<String, Object>> cfmRcvRtWTrend(Map<String, Object> paramMap);

	List<Map<String, Object>> cfmRcvRtDTrend(Map<String, Object> paramMap);

	Map<String, Object> sentSuccessRateinSingleDayForNull(Map<String, Object> paramMap);
}