package com.yd.ydbi.matrix.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.ydbi.matrix.dao.Matrix_cross_portId_rtMapper;
import com.yd.ydbi.matrix.model.Matrix_cross_oper_rt;
import com.yd.ydbi.matrix.model.Matrix_cross_portId_rt;
import com.yd.ydbi.matrix.service.Matrix_cross_porRtService;

@Service
public class Matrix_cross_porRtServiceImpl implements
	Matrix_cross_porRtService {
	
	@Autowired
	private Matrix_cross_portId_rtMapper Matrix_cross_portId_rtMapper;
	
	@Override
	public List<Matrix_cross_portId_rt> searchData(Map<String, Object> paramsMap) {
		List<Matrix_cross_portId_rt>  datas = Matrix_cross_portId_rtMapper.searchData(paramsMap);
		List<Matrix_cross_portId_rt>  allCountryData = Matrix_cross_portId_rtMapper.searchSumData(paramsMap);
		if (datas.size() > 0) {
			datas.add(0,allCountryData.get(0));//全国数据
		}
		return datas;
	}

	@Override
	public List<Matrix_cross_portId_rt> searchFooterData(
			Map<String, Object> paramsMap) {
		return Matrix_cross_portId_rtMapper.searchFooterData(paramsMap);
	}

}
