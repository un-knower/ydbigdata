package com.yd.ydbi.ys.dao;

import java.util.List;
import java.util.Map;

import com.yd.ydbi.ys.model.DB_SendCar;
import com.yd.ydbi.ys.model.SC_AloKbtrend;
import com.yd.ydbi.ys.model.SC_Detail;

public interface YS_sendCarMapper {
	//计算发车数量
	public List<DB_SendCar> findSendCars(Map<String, String> paramsMap);
	//计算各分拨中心发车详情
	public List<SC_Detail> findEachFB(Map<String, String> paramsMap);
	//日总趋势
	public SC_AloKbtrend finddaytrend(Map<String, String> paramsMap);
	//日卡班车趋势
	public SC_AloKbtrend findkbdaytrend(Map<String, String> paramsMap);
	//周趋势
	public SC_AloKbtrend findweektrend(Map<String, String> paramsMap);
	//月趋势
	public SC_AloKbtrend findmonthtrend(Map<String, String> paramsMap);
	//季度趋势
	public SC_AloKbtrend findseasontrend(Map<String, String> paramsMap);
}	
