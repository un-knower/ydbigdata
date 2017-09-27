package com.yd.ydbi.ys.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.yd.ydbi.ys.model.SendCar;
@Service
public interface YS_sendCar {
	/**
	 * 发车数量详情页查询
	 * @param paramsMap
	 * @return
	 */
	public SendCar searchDate(Map<String, String> paramsMap);
	/**
	 * 网点数量发车详情查询
	 * @param paramsMap
	 * @return
	 */
	public SendCar searchwdDate(Map<String, String> paramsMap);
}
