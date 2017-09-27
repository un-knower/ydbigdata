package com.yd.ydbi.seffect.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mchange.v1.util.CollectionUtils;
import com.yd.ydbi.seffect.dao.SEffectTransOpMapper;
import com.yd.ydbi.seffect.service.SeffectTransOpService;

@Service
public class SeffectTransOpServiceImpl implements SeffectTransOpService {

	@Resource
	SEffectTransOpMapper sEffectTransOpMapper;

	@Override
	public Map<String, Object> getSeffectTransOp(Map<String, Object> paramMap) {
		Map<String, Object> map = sEffectTransOpMapper.getSeffectTransOp(paramMap);
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
		Map<String, Object> map = sEffectTransOpMapper.selectDwmAvgAmount(paramMap);
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
		if ("d".equals(paramMap.get("trendType"))) {
			listEffect = sEffectTransOpMapper.seffectTransOpDTrend(paramMap);
			listSort = sEffectTransOpMapper.dbctSortDay(paramMap);
			map.put("listEffect", listEffect);
			map.put("listSort", listSort);
		}
		if ("w".equals(paramMap.get("trendType"))) {
			listEffect = sEffectTransOpMapper.seffectTransOpWTrend(paramMap);
			listSort = sEffectTransOpMapper.dbctSortWeek(paramMap);
			map.put("listEffect", listEffect);
			map.put("listSort", listSort);
		}
		if ("m".equals(paramMap.get("trendType"))) {
			List<Map<String, Object>> mapActiveList = sEffectTransOpMapper.seffectTransOpMTrendForActive(paramMap);
			List<Map<String, Object>> mapSameTimeList = sEffectTransOpMapper.seffectTransOpMTrendForSameTime(paramMap);
			if(!mapActiveList.isEmpty()){
				listEffect.addAll(mapActiveList);
			}
			if(!mapSameTimeList.isEmpty()){
				listEffect.addAll(mapSameTimeList);
			}
			if (!mapActiveList.isEmpty() && !mapSameTimeList.isEmpty()) {
				for (int i = 0; i < mapActiveList.size(); i++) {
					for (i = 0; i < mapSameTimeList.size(); i++) {
						mapActiveList.get(i).putAll(mapSameTimeList.get(i));
					}
				}
				listEffect.addAll(mapActiveList);
			}
			listSort = sEffectTransOpMapper.dbctSortMonth(paramMap);
			map.put("listEffect", listEffect);
			map.put("listSort", listSort);
		}
		return map;
	}

}
