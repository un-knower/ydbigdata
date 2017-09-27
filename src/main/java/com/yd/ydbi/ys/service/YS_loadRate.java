package com.yd.ydbi.ys.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.yd.ydbi.ys.model.LoadRate;

@Service
public interface YS_loadRate {
	/**
	 * 装载率查询
	 * @param paramsMap
	 * @return
	 */
	public LoadRate searchDate(Map<String, String> paramsMap);
}
