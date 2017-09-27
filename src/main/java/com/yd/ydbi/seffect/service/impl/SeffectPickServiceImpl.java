package com.yd.ydbi.seffect.service.impl;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yd.ydbi.seffect.dao.SEffectPickMapper;
import com.yd.ydbi.seffect.service.SeffectPickService;

@Service
public class SeffectPickServiceImpl implements SeffectPickService {

	@Resource
	SEffectPickMapper sEffectPickMapper;

	@Override
	public Map<String, Object> getSeffectPick(Map<String, Object> paramMap) {
		return sEffectPickMapper.getSeffectPick(paramMap);
	}

	@Override
	public Map<String, Object> selectDwmAvgAmount(Map<String, Object> paramMap) {
		return sEffectPickMapper.selectDwmAvgAmount(paramMap);
	}

	@Override
	public List<Map<String, Object>> seffectPickDTrend(Map<String, Object> paramMap) {
		return sEffectPickMapper.seffectPickDTrend(paramMap);
	}

	@Override
	public List<Map<String, Object>> seffectPickWTrend(Map<String, Object> paramMap) {
		return sEffectPickMapper.seffectPickWTrend(paramMap);
	}

	@Override
	public List<Map<String, Object>> seffectPickMTrend(Map<String, Object> paramMap) {
		return sEffectPickMapper.seffectPickMTrend(paramMap);
	}

	@Override
	public List<Map<String, Object>> seffectPickQTrend(Map<String, Object> paramMap) {
		return sEffectPickMapper.seffectPickQTrend(paramMap);
	}

	@Override
	public List<Map<String, Object>> seffectPickCp(Map<String, Object> paramMap) {
		return sEffectPickMapper.seffectPickCp(paramMap);
	}

	@Override
	public List<Map<String, Object>> seffectPickSort(Map<String, Object> paramMap) {
		return sEffectPickMapper.seffectPickSort(paramMap);
	}

	@Override
	public List<Map<String, Object>> seffectPicSlowest10City(Map<String, Object> paramMap) {
		return sEffectPickMapper.seffectPicSlowest10City(paramMap);
	}

	@Override
	public List<Map<String, Object>> seffectPickHighestOvertimeCp10City(Map<String, Object> paramMap) {
		return sEffectPickMapper.seffectPickHighestOvertimeCp10City(paramMap);
	}

	@Override
	public List<Map<String, Object>> seffectPickHighestOvertimeOder10City(Map<String, Object> paramMap) {
		return sEffectPickMapper.seffectPickHighestOvertimeOder10City(paramMap);
	}

}
