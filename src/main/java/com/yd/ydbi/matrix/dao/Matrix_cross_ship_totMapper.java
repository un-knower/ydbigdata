package com.yd.ydbi.matrix.dao;

import java.util.List;
import java.util.Map;

import com.yd.ydbi.matrix.model.Matrix_cross_ship_tot;

public interface Matrix_cross_ship_totMapper {
	List<Matrix_cross_ship_tot> searchData(Map<String, Object> paramsMap);

	List<Matrix_cross_ship_tot> searchFooterData(Map<String, Object> paramsMap);
	
	List<Matrix_cross_ship_tot> searchSumData(Map<String, Object> paramsMap);

}
