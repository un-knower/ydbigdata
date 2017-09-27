package com.yd.ydbi.ys.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.yd.ydbi.ys.model.CarLine;
@Service
public interface YS_carLine {
	/**
	 * 车线数量详情查询
	 * @param paramsMap
	 * @return
	 */
	public CarLine searchDate(Map<String, String> paramsMap);
}
