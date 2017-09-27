package com.yd.ydbi.matrix.service;

import java.util.List;
import java.util.Map;

import com.yd.ydbi.matrix.model.Matrix_shipment_sum;


public interface Matrix_shipment_sumService {
	public List<Matrix_shipment_sum> searchData(Map<String, Object> paramsMap);
	
	public List<Matrix_shipment_sum> searchFooterData(Map<String, Object> paramsMap);
}
