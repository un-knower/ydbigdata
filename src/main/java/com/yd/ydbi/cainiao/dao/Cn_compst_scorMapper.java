package com.yd.ydbi.cainiao.dao;

import java.util.List;
import java.util.Map;

import com.yd.ydbi.cainiao.model.Cn_compst_scor;

public interface Cn_compst_scorMapper {
	
	List<Cn_compst_scor> searchData(Map<String, Object> paramsMap);

	List<Cn_compst_scor> searchFooterData(Map<String, Object> paramsMap);

	int addData(Map<String, Object> paramsMap);
	
	int updateData(Map<String, Object> paramsMap);
}