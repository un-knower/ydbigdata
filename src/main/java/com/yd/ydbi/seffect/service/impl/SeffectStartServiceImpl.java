package com.yd.ydbi.seffect.service.impl;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yd.ydbi.seffect.dao.SEffectStartMapper;
import com.yd.ydbi.seffect.service.SeffectStartService;

@Service
public class SeffectStartServiceImpl implements SeffectStartService {

	@Resource
	SEffectStartMapper sEffectStartMapper;

	@Override
	public Map<String, Object> getSeffectStart(Map<String, Object> paramMap) {
		return sEffectStartMapper.getSeffectStart(paramMap);
	}

	@Override
	public Map<String, Object> selectDwmAvgAmount(Map<String, Object> paramMap) {
		return sEffectStartMapper.selectDwmAvgAmount(paramMap);
	}

	@Override
	public List<Map<String, Object>> seffectStartDTrend(Map<String, Object> paramMap) {
		return sEffectStartMapper.seffectStartDTrend(paramMap);
	}

	@Override
	public List<Map<String, Object>> seffectStartWTrend(Map<String, Object> paramMap) {
		return sEffectStartMapper.seffectStartWTrend(paramMap);
	}

	@Override
	public List<Map<String, Object>> seffectStartMTrend(Map<String, Object> paramMap) {
		return sEffectStartMapper.seffectStartMTrend(paramMap);
	}

	@Override
	public List<Map<String, Object>> seffectStartQTrend(Map<String, Object> paramMap) {
		return sEffectStartMapper.seffectStartQTrend(paramMap);
	}

	@Override
	public List<Map<String, Object>> seffectStartCp(Map<String, Object> paramMap) {
		return sEffectStartMapper.seffectStartCp(paramMap);
	}

	@Override
	public List<Map<String, Object>> seffectStartSort(Map<String, Object> paramMap) {
		return sEffectStartMapper.seffectStartSort(paramMap);
	}

	@Override
	public List<Map<String, Object>> seffectPicSlowest10City(Map<String, Object> paramMap) {
		return sEffectStartMapper.seffectPicSlowest10City(paramMap);
	}

	@Override
	public List<Map<String, Object>> seffectStartHighestOvertimeCp10City(Map<String, Object> paramMap) {
		return sEffectStartMapper.seffectStartHighestOvertimeCp10City(paramMap);
	}

	@Override
	public List<Map<String, Object>> seffectStartHighestOvertimeOder10City(Map<String, Object> paramMap) {
		return sEffectStartMapper.seffectStartHighestOvertimeOder10City(paramMap);
	}

}
