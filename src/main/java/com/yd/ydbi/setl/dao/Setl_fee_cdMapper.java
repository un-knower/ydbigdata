package com.yd.ydbi.setl.dao;

import java.util.List;
import java.util.Map;

import com.yd.ydbi.setl.model.Setl_fee_cd;

public interface Setl_fee_cdMapper {
	
	List<Setl_fee_cd> searchOneFeeData(Map<String, Object> paramsMap);

	List<Setl_fee_cd> searchTwoFeeData(Map<String, Object> paramsMap);

}