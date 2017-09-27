package com.yd.ydbi.setl.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.yd.common.data.CIPPageInfo;
import com.yd.ydbi.setl.model.Setl_ship_dtl;

/**
 * 结算明细信息业务处理接口<br/>
 *
 * @Date: 2017-06-11 16:42:35
 * @author 康元佳
 * @version
 * @since JDK 1.7
 * @see
 */
@Service
public interface Setl_ship_dtlService {

	public List<Setl_ship_dtl> searchData(Map<String, Object> paramsMap);

	public List<Setl_ship_dtl> searchData(Map<String, Object> paramsMap, CIPPageInfo page);

	public List<Setl_ship_dtl> searchFooterData(Map<String, Object> paramsMap);

	public List<Setl_ship_dtl> exportData(Map<String, Object> paramsMap);
}
