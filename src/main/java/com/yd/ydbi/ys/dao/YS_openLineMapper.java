package com.yd.ydbi.ys.dao;

import java.util.List;
import java.util.Map;

import com.yd.ydbi.ys.model.CL_iocTrend;
import com.yd.ydbi.ys.model.DB_CarLine;

public interface YS_openLineMapper {
	//按日期查询
	public List<DB_CarLine> findCarLines(Map<String, String> paramsMap);
	//查询趋势
	public CL_iocTrend findCarTrend(Map<String, String> paramsMap);
	//开通线路
	public List<com.yd.ydbi.ys.model.LR_2> findlrhead(Map<String, String> paramsMap);
}	
