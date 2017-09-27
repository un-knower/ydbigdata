package com.yd.ydbi.matrix.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.ydbi.matrix.dao.Matrix_shipment_efficiencyMapper;
import com.yd.ydbi.matrix.model.Matrix_shipment_efficiency;
import com.yd.ydbi.matrix.service.Matrix_shipment_efficiencyService;

@Service
public class Matrix_shipment_efficiencyServiceImpl implements
		Matrix_shipment_efficiencyService {
	
	@Autowired
	private Matrix_shipment_efficiencyMapper matrix_shipment_efficiency;
	
	@Override
	public List<Matrix_shipment_efficiency> searchData(
			Map<String, Object> paramsMap) {
		int index = paramsMap.get("start_dt").toString().indexOf(":");
		String start_dt = paramsMap.get("start_dt").toString().substring(0, index)+":00:00";
		paramsMap.put("start_dt", start_dt);
		String end_dt = paramsMap.get("end_dt").toString().substring(0, index)+":00:00";
		paramsMap.put("end_dt", end_dt);
		return matrix_shipment_efficiency.searchData(paramsMap);
	}

	@Override
	public List<Matrix_shipment_efficiency> searchFooterData(
			Map<String, Object> paramsMap) {
		int index = paramsMap.get("start_dt").toString().indexOf(":");
		String start_dt = paramsMap.get("start_dt").toString().substring(0, index)+":00:00";
		paramsMap.put("start_dt", start_dt);
		String end_dt = paramsMap.get("end_dt").toString().substring(0, index)+":00:00";
		paramsMap.put("end_dt", end_dt);
		return matrix_shipment_efficiency.searchFooterData(paramsMap);
	}

}
