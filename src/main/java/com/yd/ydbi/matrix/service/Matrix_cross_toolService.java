package com.yd.ydbi.matrix.service;

import java.util.List;
import java.util.Map;

import com.yd.ydbi.matrix.model.Matrix_cross_comboxBean;

public interface Matrix_cross_toolService {
	
	public List<Matrix_cross_comboxBean> getShipTypName(Map<String, Object> paramsMap);
	public List<Matrix_cross_comboxBean> getAllShipTypName(Map<String, Object> paramsMap);
	
}
