package com.yd.ydbi.business.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.yd.ydbi.business.dao.BasSBusiPickMapper;
import com.yd.ydbi.business.model.MonthGrowthRate;
import com.yd.ydbi.business.model.MonthVolatility;
import com.yd.ydbi.business.service.BasSBusiPickService;

@Service
public class BasSBusiPickServiceImpl implements BasSBusiPickService {
	@Resource
	BasSBusiPickMapper basSBusiPickMapper;

	@Override
	public Map<String, Object> selectTookAQuantity(Map<String, Object> paramMap) {
		Map<String, Object> map = basSBusiPickMapper.selectTookAQuantity(paramMap);
		if(map==null){
			map = basSBusiPickMapper.selectTookAQuantityForNull(paramMap);
		}
		return map;
	}

	@Override
	public Map<String, Object> selectDwmAvgAmount(Map<String, Object> paramMap) {
		Map<String, Object> map = basSBusiPickMapper.dwmAvgAmount(paramMap);
		if(map == null){
			map = new HashMap<String, Object>(3);
			map.put("lastDayTheAVG", 0);
			map.put("theMonthTheAVG", 0);
			map.put("lastWeekTheAVG", 0);
		}
		return map;
	}

	@Override
	public Map<String, Object> getQuarterlyTrend(Map<String, Object> paramMap) {
		return basSBusiPickMapper.quarterlyTrend(paramMap);
	}

	@Override
	public List<MonthGrowthRate> getMonthGrowthRate(Map<String, Object> paramMap) {
		return basSBusiPickMapper.monthGrowthRate(paramMap);
	}

	@Override
	public List<MonthVolatility> getMonthVolatility(Map<String, Object> paramMap) {
		return basSBusiPickMapper.monthVolatility(paramMap);
	}

	@Override
	public List<Map<String, Object>> pickQTrend(Map<String, Object> paramMap) {
		return basSBusiPickMapper.pickQTrend(paramMap);
	}

	@Override
	public List<Map<String, Object>> pickMTrend(Map<String, Object> paramMap) {
		return basSBusiPickMapper.pickMTrend(paramMap);
	}

	@Override
	public List<Map<String, Object>> pickWTrend(Map<String, Object> paramMap) {
		return basSBusiPickMapper.pickWTrend(paramMap);
	}

	@Override
	public List<Map<String, Object>> pickDTrend(Map<String, Object> paramMap) {
		return basSBusiPickMapper.pickDTrend(paramMap);
	}

	@Override
	public List<Map<String, Object>> pickMTrendSameTime(Map<String, Object> paramMap) {
		return basSBusiPickMapper.pickMTrendSameTime(paramMap);
	}

}
