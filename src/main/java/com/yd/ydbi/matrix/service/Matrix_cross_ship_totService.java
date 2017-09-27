package com.yd.ydbi.matrix.service;

import java.util.List;
import java.util.Map;

import com.yd.ydbi.matrix.model.Matrix_cross_ship_tot;

public interface Matrix_cross_ship_totService {
	public List<Matrix_cross_ship_tot> searchData(Map<String, Object> paramsMap);
	
	public List<Matrix_cross_ship_tot> searchFooterData(Map<String, Object> paramsMap);
}
