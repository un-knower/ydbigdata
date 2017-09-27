package com.yd.ydbi.setl.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.ydbi.setl.dao.Setl_fee_cdMapper;
import com.yd.ydbi.setl.model.Setl_fee_cd;
import com.yd.ydbi.setl.service.Setl_fee_cdService;

/**
 * 
 * 费用信息业务处理实现类. <br/>
 *
 * @date:2017-06-30 12:48:07 <br/>
 * @author 康元佳
 * @version:
 * @since:JDK 1.7
 */
@Service
public class Setl_fee_cdServiceImpl implements Setl_fee_cdService {

	@Autowired
	private Setl_fee_cdMapper setl_fee_cdMapper;

	@Override
	public List<Setl_fee_cd> searchData(Map<String, Object> paramsMap) {
		if("1".equals(paramsMap.get("fee_type"))){
			return setl_fee_cdMapper.searchOneFeeData(paramsMap);
		}else{
			return setl_fee_cdMapper.searchTwoFeeData(paramsMap);
		}
	}


}