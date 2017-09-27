package com.yd.ydbi.seffect.service.impl;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yd.ydbi.seffect.dao.SEffectEndMapper;
import com.yd.ydbi.seffect.service.SeffectEndService;

@Service
public class SeffectEndServiceImpl implements SeffectEndService {

	@Resource
	SEffectEndMapper sEffectEndMapper;

	@Override
	public Map<String, Object> getSeffectEnd(Map<String, Object> paramMap) {
		return sEffectEndMapper.getSeffectEnd(paramMap);
	}

	@Override
	public Map<String, Object> selectDwmAvgAmount(Map<String, Object> paramMap) {
		return sEffectEndMapper.selectDwmAvgAmount(paramMap);
	}

	@Override
	public List<Map<String, Object>> seffectEndDTrend(Map<String, Object> paramMap) {
		return sEffectEndMapper.seffectEndDTrend(paramMap);
	}

	@Override
	public List<Map<String, Object>> seffectEndWTrend(Map<String, Object> paramMap) {
		return sEffectEndMapper.seffectEndWTrend(paramMap);
	}

	@Override
	public List<Map<String, Object>> seffectEndMTrend(Map<String, Object> paramMap) {
		return sEffectEndMapper.seffectEndMTrend(paramMap);
	}

	@Override
	public List<Map<String, Object>> seffectEndQTrend(Map<String, Object> paramMap) {
		return sEffectEndMapper.seffectEndQTrend(paramMap);
	}

	@Override
	public List<Map<String, Object>> seffectEndCp(Map<String, Object> paramMap) {
		return sEffectEndMapper.seffectEndCp(paramMap);
	}

	@Override
	public List<Map<String, Object>> seffectEndSort(Map<String, Object> paramMap) {
		return sEffectEndMapper.seffectEndSort(paramMap);
	}

	@Override
	public List<Map<String, Object>> seffectPicSlowest10City(Map<String, Object> paramMap) {
		return sEffectEndMapper.seffectPicSlowest10City(paramMap);
	}

	@Override
	public List<Map<String, Object>> seffectEndHighestOvertimeCp10City(Map<String, Object> paramMap) {
		return sEffectEndMapper.seffectEndHighestOvertimeCp10City(paramMap);
	}

	@Override
	public List<Map<String, Object>> seffectEndHighestOvertimeOder10City(Map<String, Object> paramMap) {
		return sEffectEndMapper.seffectEndHighestOvertimeOder10City(paramMap);
	}

}
