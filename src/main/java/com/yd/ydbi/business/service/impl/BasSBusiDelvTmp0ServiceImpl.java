package com.yd.ydbi.business.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.yd.ydbi.business.dao.BasSBusiDelvTmp0Mapper;
import com.yd.ydbi.business.service.BasSBusiDelvTmp0Service;

@Service
public class BasSBusiDelvTmp0ServiceImpl implements BasSBusiDelvTmp0Service {

	@Resource
	BasSBusiDelvTmp0Mapper basSBusiDelvTmp0Mapper;

	@Override
	public Map<String, Object> selectSentSuccessRateinSingleDay(Map<String, Object> paramMap) {
		Map<String, Object> map = basSBusiDelvTmp0Mapper.sentSuccessRateinSingleDay(paramMap);
		if(map == null){
			map = basSBusiDelvTmp0Mapper.sentSuccessRateinSingleDayForNull(paramMap);
		}
		return map;
	}

	@Override
	public Map<String, Object> dwmAvgAmount(Map<String, Object> paramMap) {
		Map<String, Object> map = basSBusiDelvTmp0Mapper.dwmAvgAmount(paramMap);
		if(map == null){
			map = new HashMap<String, Object>(3);
			map.put("lastDayTheAVG", 0);
			map.put("theMonthTheAVG", 0);
			map.put("lastWeekTheAVG", 0);
		}
		return map;
	}
	
	
    
	
	              
	
	              
	

	@Override
	public List<Map<String, Object>> cfmRcvRtQTrend(Map<String, Object> paramMap) {
		return basSBusiDelvTmp0Mapper.cfmRcvRtQTrend(paramMap);
	}

	@Override
	public List<Map<String, Object>> cfmRcvRtMTrend(Map<String, Object> paramMap) {
		return basSBusiDelvTmp0Mapper.cfmRcvRtMTrend(paramMap);
	}

	@Override
	public List<Map<String, Object>> cfmRcvRtWTrend(Map<String, Object> paramMap) {
		return basSBusiDelvTmp0Mapper.cfmRcvRtWTrend(paramMap);
	}

	@Override
	public List<Map<String, Object>> cfmRcvRtDTrend(Map<String, Object> paramMap) {
		return basSBusiDelvTmp0Mapper.cfmRcvRtDTrend(paramMap);
	}

}
