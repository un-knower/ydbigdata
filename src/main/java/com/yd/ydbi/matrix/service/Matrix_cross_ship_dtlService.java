package com.yd.ydbi.matrix.service;

import java.util.List;
import java.util.Map;

import com.yd.ydbi.matrix.model.Matrix_cross_ship_dtl;

public interface Matrix_cross_ship_dtlService {
	public List<Matrix_cross_ship_dtl> searchData(Map<String, Object> paramsMap);
	
	public List<Matrix_cross_ship_dtl> searchFooterData(Map<String, Object> paramsMap);
}
