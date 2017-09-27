package com.yd.ydbi.ys.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.yd.ydbi.ys.model.CarLateRate;
import com.yd.ydbi.ys.model.CarLine;
@Service
public interface YS_RURate {

	/**
	 * 即到即卸率详细页面
	 * @param paramsMap
	 * @return
	 */
	public CarLateRate searchDate(Map<String, String> paramsMap);
}
