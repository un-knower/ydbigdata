package com.yd.ydbi.seffect.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yd.ydbi.seffect.dao.SEffectSndMapper;
import com.yd.ydbi.seffect.service.SeffectSndService;

@Service
public class SeffectSndServiceImpl implements SeffectSndService {

	@Resource
	SEffectSndMapper sEffectSndMapper;

	@Override
	public Map<String, Object> getSeffectSnd(Map<String, Object> paramMap) {
		Map<String, Object> map = sEffectSndMapper.getSeffectSnd(paramMap);
		if(map == null){
			map = new HashMap<String, Object>(3);
			map.put("effect", 0);
			map.put("dayFrom", 0);
			map.put("weekFrom", 0);
		}
		return map;
	}

	@Override
	public Map<String, Object> selectDwmAvgAmount(Map<String, Object> paramMap) {
		Map<String, Object> map = sEffectSndMapper.selectDwmAvgAmount(paramMap);
		if (map == null) {
			map = new HashMap<String, Object>(3);
			map.put("last_day", 0);
			map.put("week_avg", 0);
			map.put("month_avg", 0);
		}
		return map;
	}
	@Override
	public Map<String, Object> seffectTrend(Map<String, Object> paramMap) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> listEffect = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> listSort = new ArrayList<Map<String, Object>>();
		if("d".equals(paramMap.get("trendType"))){
			listEffect = sEffectSndMapper.seffectSndDTrend(paramMap);	
			listSort = sEffectSndMapper.dbctSortDay(paramMap);
			map.put("listEffect", listEffect);
			map.put("listSort", listSort);
		}if("w".equals(paramMap.get("trendType"))){
			listEffect = sEffectSndMapper.seffectSndWTrend(paramMap);
			listSort = sEffectSndMapper.dbctSortWeek(paramMap);
			map.put("listEffect", listEffect);
			map.put("listSort", listSort);
		}if("m".equals(paramMap.get("trendType"))){
			listEffect = sEffectSndMapper.seffectSndMTrend(paramMap);
			listSort = sEffectSndMapper.dbctSortMonth(paramMap);
			map.put("listEffect", listEffect);
			map.put("listSort", listSort);
		}
		return map;
	}
}
