package com.yd.ydbi.matrix.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.ydbi.matrix.dao.Matrix_cross_ship_dtlMapper;
import com.yd.ydbi.matrix.dao.Matrix_hei_portId_rtMapper;
import com.yd.ydbi.matrix.model.Matrix_cross_comboxBean;
import com.yd.ydbi.matrix.service.Matrix_cross_toolService;

@Service
public class Matrix_cross_toolServiceImpl implements Matrix_cross_toolService {
	
	@Autowired
	private Matrix_cross_ship_dtlMapper matrix_cross_ship_dtlMapper;
	@Autowired
	private Matrix_hei_portId_rtMapper  matrix_hei_portId_rtMapper;
	
	@Override
	public List<Matrix_cross_comboxBean> getShipTypName(Map<String, Object> paramsMap){
		List<Matrix_cross_comboxBean> shipTyps = matrix_cross_ship_dtlMapper.getShipTypName();
		return shipTyps;
	}
	
	@Override
	public List<Matrix_cross_comboxBean> getAllShipTypName(Map<String, Object> paramsMap) {
		List<Matrix_cross_comboxBean> shipTyps = matrix_hei_portId_rtMapper.getShipTypName();
		return shipTyps;
	}
	
}
