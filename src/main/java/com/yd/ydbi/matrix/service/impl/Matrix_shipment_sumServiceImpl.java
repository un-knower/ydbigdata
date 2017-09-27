package com.yd.ydbi.matrix.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.ydbi.matrix.dao.Matrix_shipment_sumMapper;
import com.yd.ydbi.matrix.model.Matrix_shipment_sum;
import com.yd.ydbi.matrix.service.Matrix_shipment_sumService;

@Service
public class Matrix_shipment_sumServiceImpl implements
		Matrix_shipment_sumService {
	
	@Autowired
	private Matrix_shipment_sumMapper  matrix_shipment_sumMapper;
	
	@Override
	public List<Matrix_shipment_sum> searchData(Map<String, Object> paramsMap) {
		return matrix_shipment_sumMapper.searchData(paramsMap);
	}

	@Override
	public List<Matrix_shipment_sum> searchFooterData(Map<String, Object> paramsMap) {
		return matrix_shipment_sumMapper.searchFooterData(paramsMap);
	}

}
