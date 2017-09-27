package com.yd.ydbi.ys.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.yd.ydbi.ys.model.YSS;

@Service
public interface YS_Summary {
	/**
	 * 即到即卸率详细页面
	 * @param paramsMap
	 * @return
	 */
	public YSS searchDate(Map<String, String> paramsMap);
}
