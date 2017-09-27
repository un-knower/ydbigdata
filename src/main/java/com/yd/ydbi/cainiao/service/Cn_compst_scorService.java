package com.yd.ydbi.cainiao.service;

import java.util.List;
import java.util.Map;

import com.yd.ydbi.cainiao.model.Cn_compst_scor;

/**
 * 菜鸟信息维护业务处理接口<br/>
 *
 * @Date: 2017-08-07 10:09:29
 * @author 康元佳
 * @version
 * @since JDK 1.7
 * @see
 */
public interface Cn_compst_scorService {

	public List<Cn_compst_scor> searchData(Map<String, Object> paramsMap);
	
	public List<Cn_compst_scor> searchFooterData(Map<String, Object> paramsMap);
	
	public int addData(Map<String, Object> paramsMap);
	
	public int updateData(Map<String, Object> paramsMap);
}
