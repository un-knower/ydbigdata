package com.yd.ydbi.matrix.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.ydbi.matrix.dao.Matrix_cross_ship_totMapper;
import com.yd.ydbi.matrix.model.Matrix_cross_portId_rt;
import com.yd.ydbi.matrix.model.Matrix_cross_ship_tot;
import com.yd.ydbi.matrix.service.Matrix_cross_ship_totService;

@Service
public class Matrix_cross_ship_totServiceImpl implements
	Matrix_cross_ship_totService {
	
	@Autowired
	private Matrix_cross_ship_totMapper matrix_cross_ship_totMapper;
	
	@Override
	public List<Matrix_cross_ship_tot> searchData(Map<String, Object> paramsMap) {
		
		List<Matrix_cross_ship_tot>  datas = matrix_cross_ship_totMapper.searchData(paramsMap);
		List<Matrix_cross_ship_tot>  allCountryData = matrix_cross_ship_totMapper.searchSumData(paramsMap);
		if (datas.size() > 0) {
			datas.add(0,allCountryData.get(0));//全国数据
		}
		return datas;
	}

	@Override
	public List<Matrix_cross_ship_tot> searchFooterData(
			Map<String, Object> paramsMap) {
		return matrix_cross_ship_totMapper.searchFooterData(paramsMap);
	}

}
