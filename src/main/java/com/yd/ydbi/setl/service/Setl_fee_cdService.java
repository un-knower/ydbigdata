package com.yd.ydbi.setl.service;

import java.util.List;
import java.util.Map;

import com.yd.ydbi.setl.model.Setl_fee_cd;

/**
 * 费用信息业务处理接口<br/>
 *
 * @Date: 2017-06-30 12:48:07
 * @author 康元佳
 * @version
 * @since JDK 1.7
 * @see
 */
public interface Setl_fee_cdService {

	public List<Setl_fee_cd> searchData(Map<String, Object> paramsMap);
	
}
