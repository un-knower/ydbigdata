package com.yd.ydbi.seffect.service.impl;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yd.ydbi.seffect.dao.SEffectDelvMapper;
import com.yd.ydbi.seffect.service.SeffectDelvService;

@Service
public class SeffectDelvServiceImpl implements SeffectDelvService {

	@Resource
	SEffectDelvMapper sEffectDelvMapper;

	@Override
	public Map<String, Object> getSeffectDelv(Map<String, Object> paramMap) {
		return sEffectDelvMapper.getSeffectDelv(paramMap);
	}

	@Override
	public Map<String, Object> selectDwmAvgAmount(Map<String, Object> paramMap) {
		return sEffectDelvMapper.selectDwmAvgAmount(paramMap);
	}

	@Override
	public List<Map<String, Object>> seffectDelvDTrend(Map<String, Object> paramMap) {
		return sEffectDelvMapper.seffectDelvDTrend(paramMap);
	}

	@Override
	public List<Map<String, Object>> seffectDelvWTrend(Map<String, Object> paramMap) {
		return sEffectDelvMapper.seffectDelvWTrend(paramMap);
	}

	@Override
	public List<Map<String, Object>> seffectDelvMTrend(Map<String, Object> paramMap) {
		return sEffectDelvMapper.seffectDelvMTrend(paramMap);
	}

	@Override
	public List<Map<String, Object>> seffectDelvQTrend(Map<String, Object> paramMap) {
		return sEffectDelvMapper.seffectDelvQTrend(paramMap);
	}

	@Override
	public List<Map<String, Object>> seffectDelvCp(Map<String, Object> paramMap) {
		return sEffectDelvMapper.seffectDelvCp(paramMap);
	}

	@Override
	public List<Map<String, Object>> seffectDelvSort(Map<String, Object> paramMap) {
		return sEffectDelvMapper.seffectDelvSort(paramMap);
	}

	@Override
	public List<Map<String, Object>> seffectPicSlowest10City(Map<String, Object> paramMap) {
		return sEffectDelvMapper.seffectPicSlowest10City(paramMap);
	}

	@Override
	public List<Map<String, Object>> seffectDelvHighestOvertimeCp10City(Map<String, Object> paramMap) {
		return sEffectDelvMapper.seffectDelvHighestOvertimeCp10City(paramMap);
	}

	@Override
	public List<Map<String, Object>> seffectDelvHighestOvertimeOder10City(Map<String, Object> paramMap) {
		return sEffectDelvMapper.seffectDelvHighestOvertimeOder10City(paramMap);
	}

}
