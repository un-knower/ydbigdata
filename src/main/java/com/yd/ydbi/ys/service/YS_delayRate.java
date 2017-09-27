package com.yd.ydbi.ys.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.yd.ydbi.ys.model.CarLateRate;
import com.yd.ydbi.ys.model.CarLine;
@Service
public interface YS_delayRate {

	/**
	 * 延误率详细页面
	 * @param paramsMap
	 * @return
	 */
	public CarLateRate searchDate(Map<String, String> paramsMap);
	
	/**
	 * 网点延误率详细页面
	 * @param paramsMap
	 * @return
	 */
	public CarLateRate searchWDDate(Map<String, String> paramsMap);
}
