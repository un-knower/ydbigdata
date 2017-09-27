package com.yd.ydbi.business.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.yd.ydbi.business.dao.BasSBusiSndMapper;
import com.yd.ydbi.business.model.MonthGrowthRate;
import com.yd.ydbi.business.model.MonthVolatility;
import com.yd.ydbi.business.model.SitSucRate;
import com.yd.ydbi.business.service.BasSBusiSndService;

@Service
public class BasSBusiSndServiceImpl implements BasSBusiSndService {
	@Resource
	BasSBusiSndMapper basSBusiSndMapper;

	@Override
	public Map<String, Object> selectSendAQuantity(Map<String, Object> paramMap) {
		Map<String, Object> map = basSBusiSndMapper.sendAQuantity(paramMap);
		if(map == null){
			map = basSBusiSndMapper.sendAQuantityForNull(paramMap);
		}
		return map;
	}

	@Override
	public Map<String, Object> selectDwmAvgSucRate(Map<String, Object> paramMap) {
		Map<String, Object> map = basSBusiSndMapper.dwmAvgSucRate(paramMap);
		if(map == null){
			map = new HashMap<String, Object>(3);
			map.put("lastDayTheAVG", 0);
			map.put("theMonthTheAVG", 0);
			map.put("lastWeekTheAVG", 0);
		}
		return map;
	}

	@Override
	public List<SitSucRate> getSiteAndSucRate(Map<String, Object> paramMap) {
		List<SitSucRate> sitSucRate = basSBusiSndMapper.siteAndSucRate(paramMap);
		if(sitSucRate.isEmpty()){
			sitSucRate = basSBusiSndMapper.siteAndSucRateForNull(paramMap);
		}
		return sitSucRate;
	}

	@Override
	public List<Map<String, Object>> selectStandardNetworkTrend(Map<String, Object> paramsMap) {
		return basSBusiSndMapper.standardNetworkTrend(paramsMap);
	}

	@Override
	public Map<String, Object> selectDwmAvgAmountForSnd(Map<String, Object> paramMap) {
		Map<String, Object> map = basSBusiSndMapper.selectDwmAvgAmountForSnd(paramMap);
		if(map == null){
			map = new HashMap<String, Object>(3);
			map.put("lastDayTheAVG", 0);
			map.put("theMonthTheAVG", 0);
			map.put("lastWeekTheAVG", 0);
		}
		return map;
	}

	@Override
	public Map<String, Object> selectSndTrend(Map<String, Object> paramMap) {
		return basSBusiSndMapper.selectSndTrend(paramMap);
	}

	@Override
	public List<MonthGrowthRate> monthGrowthRateForSnd(Map<String, Object> paramMap) {
		return basSBusiSndMapper.monthGrowthRateForSnd(paramMap);
	}

	@Override
	public List<MonthVolatility> monthVolatilityForSnd(Map<String, Object> paramMap) {
		return basSBusiSndMapper.monthVolatilityForSnd(paramMap);
	}

	@Override
	public List<Map<String, Object>> delvQTrend(Map<String, Object> paramMap) {
		return basSBusiSndMapper.delvQTrend(paramMap);
	}

	@Override
	public List<Map<String, Object>> delvMTrend(Map<String, Object> paramMap) {
		return basSBusiSndMapper.delvMTrend(paramMap);
	}

	@Override
	public List<Map<String, Object>> delvWTrend(Map<String, Object> paramMap) {
		return basSBusiSndMapper.delvWTrend(paramMap);
	}

	@Override
	public List<Map<String, Object>> delvDTrend(Map<String, Object> paramMap) {
		return basSBusiSndMapper.delvDTrend(paramMap);
	}

}
