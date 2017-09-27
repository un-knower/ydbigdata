package com.yd.ydbi.matrix.dao;

import java.util.List;
import java.util.Map;

import com.yd.ydbi.matrix.model.Matrix_shipment_efficiency;

public interface Matrix_shipment_efficiencyMapper {
	List<Matrix_shipment_efficiency> searchData(Map<String, Object> paramsMap);

	List<Matrix_shipment_efficiency> searchFooterData(Map<String, Object> paramsMap);
}
