package com.yd.ydbi.seffect.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yd.ydbi.seffect.dao.SEffectAllMapper;
import com.yd.ydbi.seffect.service.SeffectAllService;

@Service
public class SeffectAllServiceImpl implements SeffectAllService {

	@Resource
	SEffectAllMapper sEffectAllMapper;

	@Override
	public Map<String, Object> getSeffectAll(Map<String, Object> paramMap) {
		Map<String, Object> map = sEffectAllMapper.getSeffectAll(paramMap);
		if (map == null) {
			map = new HashMap<String, Object>(3);
			map.put("effect", 0);
			map.put("dayFrom", 0);
			map.put("weekFrom", 0);
		}
		return map;
	}

	@Override
	public Map<String, Object> selectDwmAvgAmount(Map<String, Object> paramMap) {
		Map<String, Object> map = sEffectAllMapper.selectDwmAvgAmount(paramMap);
		if (map == null) {
			map = new HashMap<String, Object>(3);
			map.put("last_day", 0);
			map.put("week_avg", 0);
			map.put("month_avg", 0);
		}
		return map;
	}

	@Override
	public List<Map<String, Object>> getCntProportion(Map<String, Object> paramMap) {
		return sEffectAllMapper.getCntProportion(paramMap);
	}

	@Override
	public Map<String, Object> seffectTrend(Map<String, Object> paramMap) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> listEffect = new ArrayList<Map<String, Object>>();
		if ("d".equals(paramMap.get("trendType"))) {
			listEffect = sEffectAllMapper.seffectAllDTrend(paramMap);
			map.put("listEffect", listEffect);
		}
		if ("w".equals(paramMap.get("trendType"))) {
			listEffect = sEffectAllMapper.seffectAllWTrend(paramMap);
			map.put("listEffect", listEffect);
		}
		if ("m".equals(paramMap.get("trendType"))) {
			listEffect = sEffectAllMapper.seffectAllMTrend(paramMap);
			map.put("listEffect", listEffect);
		}
		return map;
	}

	@Override
	public List<Map<String, Object>> getSlowly10ProvLine(Map<String, Object> paramMap) {
		return sEffectAllMapper.getSlowly10ProvLine(paramMap);
	}

	@Override
	public List<Map<String, Object>> getSlowly10CityLine(Map<String, Object> paramMap) {
		return sEffectAllMapper.getSlowly10CityLine(paramMap);
	}

}
