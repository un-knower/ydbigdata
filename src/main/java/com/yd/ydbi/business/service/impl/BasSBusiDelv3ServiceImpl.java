package com.yd.ydbi.business.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yd.ydbi.business.dao.BasSBusiDelv3Mapper;
import com.yd.ydbi.business.model.SitSucRate;
import com.yd.ydbi.business.service.BasSBusiDelv3Service;

@Service
public class BasSBusiDelv3ServiceImpl implements BasSBusiDelv3Service {

	@Resource
	BasSBusiDelv3Mapper basSBusiDelv3Mapper;

	@Override
	public Map<String, Object> selectThreeDayToSignForRate(Map<String, Object> paramMap) {
		Map<String, Object> map = basSBusiDelv3Mapper.threeDayToSignForRate(paramMap);
		if(map == null){
			map = basSBusiDelv3Mapper.threeDayToSignForRateForNull(paramMap);
		}
		return map;
	}

	@Override
	public Map<String, Object> dwmAvgAmount(Map<String, Object> paramMap) {
		Map<String, Object> map = basSBusiDelv3Mapper.dwmAvgAmount(paramMap);
		if(map == null){
			map = new HashMap<String, Object>(3);
			map.put("lastDayTheAVG", 0);
			map.put("theMonthTheAVG", 0);
			map.put("lastWeekTheAVG", 0);
		}
		return map;
	}

	@Override
	public List<Map<String, Object>> cfmRcvRt3QTrend(Map<String, Object> paramMap) {
		return basSBusiDelv3Mapper.cfmRcvRt3QTrend(paramMap);
	}

	@Override
	public List<Map<String, Object>> cfmRcvRt3MTrend(Map<String, Object> paramMap) {
		return basSBusiDelv3Mapper.cfmRcvRt3MTrend(paramMap);
	}

	@Override
	public List<Map<String, Object>> cfmRcvRt3WTrend(Map<String, Object> paramMap) {
		return basSBusiDelv3Mapper.cfmRcvRt3WTrend(paramMap);
	}

	@Override
	public List<Map<String, Object>> cfmRcvRt3DTrend(Map<String, Object> paramMap) {
		return basSBusiDelv3Mapper.cfmRcvRt3DTrend(paramMap);
	}

	@Override
	public List<SitSucRate> getSiteAndSucRate(Map<String, Object> paramMap) {
		List<SitSucRate> sitSucRate = basSBusiDelv3Mapper.getSiteAndSucRate(paramMap);
		if(sitSucRate.isEmpty()){
			sitSucRate = basSBusiDelv3Mapper.getSiteAndSucRateForNull(paramMap);
		}
		return sitSucRate;
	}

	@Override
	public List<Map<String, Object>> getStandardDbcdTrend(Map<String, Object> paramMap) {
		return basSBusiDelv3Mapper.getStandardDbcdTrend(paramMap);
	}

}
