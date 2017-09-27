package com.yd.ydbi.matrix.service;

import java.util.List;
import java.util.Map;

import com.yd.ydbi.matrix.model.Matrix_shipment_detail;

public interface Matrix_shipment_detailService {
public List<Matrix_shipment_detail> searchData(Map<String, Object> paramsMap);
	
	public List<Matrix_shipment_detail> searchFooterData(Map<String, Object> paramsMap);
}
