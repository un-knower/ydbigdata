package com.yd.common.function.meta.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.yd.common.data.CIPPageInfo;
import com.yd.common.data.CIPReqCondition;
import com.yd.common.function.meta.data.CIP_meta_fields_hisData;
import com.yd.common.function.meta.data.vo.CIP_meta_fields_hisVO;
import com.yd.common.runtime.CIPRuntimeOperator;

/**
 * <p>Service类</p>
 * <p>Class: CIP_meta_fields_hisService</p>
 *
 * @since 2015-10-08 09:03:37
 */

@Service
public interface CIP_meta_fields_hisService {

	public void updateData(CIP_meta_fields_hisVO d, CIPRuntimeOperator operateInf);
	
	public void addData(CIP_meta_fields_hisVO d, CIPRuntimeOperator operateInf);
	
	public void deleteData(Object[] keys, CIPRuntimeOperator operateInf);
	
	public CIP_meta_fields_hisVO getData(Object[] keys);
	
	public List<CIP_meta_fields_hisData> searchData(CIPPageInfo page, CIPReqCondition[] conditions);


	/** 
	 * 导出数据字段历史
	 */
	public List<Map<String, Object>> exportEntities(CIPPageInfo page,CIPReqCondition[] conditions,
			CIPRuntimeOperator operateInf, boolean xFirst);
	
}