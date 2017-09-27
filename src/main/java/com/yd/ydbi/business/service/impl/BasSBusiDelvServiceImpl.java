package com.yd.ydbi.business.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.yd.ydbi.business.dao.BasSBusiDelvMapper;
import com.yd.ydbi.business.model.BasSBusiDelv;
import com.yd.ydbi.business.model.MonthGrowthRate;
import com.yd.ydbi.business.model.MonthVolatility;
import com.yd.ydbi.business.service.BasSBusiDelvService;

@Service
public class BasSBusiDelvServiceImpl implements BasSBusiDelvService {
	@Resource
	BasSBusiDelvMapper basSBusiDelvMapper;

	@SuppressWarnings("rawtypes")
	@Override
	public BasSBusiDelv selectByPrimaryKey(Map id) {
		return basSBusiDelvMapper.selectBasSBusiDelvById(id);
	}

	@Override
	public BasSBusiDelv selectBasSBusiDelvByModel(BasSBusiDelv record) {
		return basSBusiDelvMapper.selectBasSBusiDelvByMode(record);
	}

	@Override
	public Map<String, Object> selectSignAQuantity(Map<String, Object> paramsMap) {
		Map<String, Object> map = basSBusiDelvMapper.signAQuantity(paramsMap);
		if(map==null){
			map = basSBusiDelvMapper.signAQuantityForNull(paramsMap);
		}
		return map;
	}

	@Override
	public Map<String, Object> selectDwmAvgAmountForSig(Map<String, Object> paramsMap) {
		Map<String, Object> map = basSBusiDelvMapper.selectDwmAvgAmountForSig(paramsMap);
		if(map == null){
			map = new HashMap<String, Object>(3);
			map.put("lastDayTheAVG", 0);
			map.put("theMonthTheAVG", 0);
			map.put("lastWeekTheAVG", 0);
		}
		return map;
	}

	@Override
	public Map<String, Object> selectSignTrend(Map<String, Object> paramsMap) {
		return basSBusiDelvMapper.selectSignTrend(paramsMap);
	}

	@Override
	public List<MonthGrowthRate> monthGrowthRateForSign(Map<String, Object> paramMap) {
		return basSBusiDelvMapper.monthGrowthRateForSign(paramMap);
	}

	@Override
	public List<MonthVolatility> monthVolatilityForSign(Map<String, Object> paramMap) {
		return basSBusiDelvMapper.monthVolatilityForSign(paramMap);
	}

	@Override
	public List<Map<String, Object>> delvQTrend(Map<String, Object> paramMap) {
		return basSBusiDelvMapper.delvQTrend(paramMap);
	}

	@Override
	public List<Map<String, Object>> delvMTrend(Map<String, Object> paramMap) {
		return basSBusiDelvMapper.delvMTrend(paramMap);
	}

	@Override
	public List<Map<String, Object>> delvWTrend(Map<String, Object> paramMap) {
		return basSBusiDelvMapper.delvWTrend(paramMap);
	}

	@Override
	public List<Map<String, Object>> delvDTrend(Map<String, Object> paramMap) {
		return basSBusiDelvMapper.delvDTrend(paramMap);
	}

}
