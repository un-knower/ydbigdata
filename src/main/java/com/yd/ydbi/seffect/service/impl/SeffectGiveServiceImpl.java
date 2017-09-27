package com.yd.ydbi.seffect.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yd.ydbi.seffect.dao.SEffectGiveMapper;
import com.yd.ydbi.seffect.service.SeffectGiveService;

@Service
public class SeffectGiveServiceImpl implements SeffectGiveService {

	@Resource
	SEffectGiveMapper sEffectGiveMapper;
	
	

	@Override
	public Map<String, Object> getSeffectGive(Map<String, Object> paramMap) {
		Map<String, Object> map =  sEffectGiveMapper.getSeffectGive(paramMap);
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
		Map<String, Object> map = sEffectGiveMapper.selectDwmAvgAmount(paramMap);
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
			listEffect = sEffectGiveMapper.seffectGiveDTrend(paramMap);	
			listSort = sEffectGiveMapper.dbctSortDay(paramMap);
			map.put("listEffect", listEffect);
			map.put("listSort", listSort);
		}if("w".equals(paramMap.get("trendType"))){
			listEffect = sEffectGiveMapper.seffectGiveWTrend(paramMap);
			listSort = sEffectGiveMapper.dbctSortWeek(paramMap);
			map.put("listEffect", listEffect);
			map.put("listSort", listSort);
		}if("m".equals(paramMap.get("trendType"))){
			listEffect = sEffectGiveMapper.seffectGiveMTrend(paramMap);
			listSort = sEffectGiveMapper.dbctSortMonth(paramMap);
			map.put("listEffect", listEffect);
			map.put("listSort", listSort);
		}
		return map;
	}

	@Override
	public int getDTotalRows(Map<String, Object> paramMap) {
		return sEffectGiveMapper.getDTotalRows(paramMap);
	}
}
