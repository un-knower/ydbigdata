package com.yd.ydbi.business.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.yd.ydbi.business.dao.BasSBusiNodelv7Mapper;
import com.yd.ydbi.business.model.SitSucRate;
import com.yd.ydbi.business.service.BasSBusiNodelv7Service;

@Service
public class BasSBusiNodelv7ServiceImpl implements BasSBusiNodelv7Service {

	@Resource
	BasSBusiNodelv7Mapper basSBusiNodelv7Mapper;

	@Override
	public Map<String, Object> selectSevenDaysWithoutReceiptRate(Map<String, Object> paramMap) {
		Map<String,Object> map = basSBusiNodelv7Mapper.sevenDaysWithoutReceiptRate(paramMap);
		if(map == null){
			map = basSBusiNodelv7Mapper.sevenDaysWithoutReceiptRateForNull(paramMap);
		}
		return map;
	}

	@Override
	public Map<String, Object> dwmAvgAmount(Map<String, Object> paramMap) {
		Map<String, Object> map = basSBusiNodelv7Mapper.dwmAvgAmount(paramMap);
		if(map == null){
			map = new HashMap<String, Object>(3);
			map.put("lastDayTheAVG", 0);
			map.put("theMonthTheAVG", 0);
			map.put("lastWeekTheAVG", 0);
		}
		return map;
	}

	@Override
	public List<Map<String, Object>> cfmRcvRt7QTrend(Map<String, Object> paramMap) {
		return basSBusiNodelv7Mapper.cfmRcvRt7QTrend(paramMap);
	}

	@Override
	public List<Map<String, Object>> cfmRcvRt7MTrend(Map<String, Object> paramMap) {
		return basSBusiNodelv7Mapper.cfmRcvRt7MTrend(paramMap);
	}

	@Override
	public List<Map<String, Object>> cfmRcvRt7WTrend(Map<String, Object> paramMap) {
		return basSBusiNodelv7Mapper.cfmRcvRt7WTrend(paramMap);
	}

	@Override
	public List<Map<String, Object>> cfmRcvRt7DTrend(Map<String, Object> paramMap) {
		return basSBusiNodelv7Mapper.cfmRcvRt7DTrend(paramMap);
	}

	@Override
	public List<SitSucRate> getSiteAndSucRate(Map<String, Object> paramMap) {
		List<SitSucRate> sitSucRate = basSBusiNodelv7Mapper.getSiteAndSucRate(paramMap);
		if(sitSucRate.isEmpty()){
			sitSucRate = basSBusiNodelv7Mapper.getSiteAndSucRateForNull(paramMap);
		}
		return sitSucRate;
	}

	@Override
	public List<Map<String, Object>> getStandardDbcdTrend(Map<String, Object> paramMap) {
		return basSBusiNodelv7Mapper.getStandardDbcdTrend(paramMap);
	}
}
