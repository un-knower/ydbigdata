package com.yd.ydbi.setl.dao;

import java.util.List;
import java.util.Map;

import com.yd.ydbi.setl.model.Setl_ship_dtl;

public interface Setl_ship_dtlMapper {
	
	List<Setl_ship_dtl> searchData(Map<String, Object> paramsMap);

	List<Setl_ship_dtl> searchFooterData(Map<String, Object> paramsMap);

	List<Setl_ship_dtl> exportData(Map<String, Object> paramsMap);
}