package com.yd.common.function.test.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.yd.common.data.CIPPageInfo;
import com.yd.common.data.CIPReqCondition;
import com.yd.common.function.test.data.CIP_test_templateData;
import com.yd.common.function.test.data.vo.CIP_test_templateVO;
import com.yd.common.runtime.CIPRuntimeOperator;

/**
 * <p>Service类</p>
 * <p>Class: CIP_test_templateService</p>
 *
 * @since 2015-11-24 04:16:55
 */

@Service
public interface CIP_test_templateService {

	public void updateData(CIP_test_templateVO d, CIPRuntimeOperator operateInf);
	
	public void addData(CIP_test_templateVO d, CIPRuntimeOperator operateInf);
	
	public void deleteData(Object[] keys, CIPRuntimeOperator operateInf);
	
	public CIP_test_templateVO getData(Object[] keys);
	
	public List<CIP_test_templateData> searchData(CIPPageInfo page, CIPReqCondition[] conditions);


	/** 
	 * 导出测试数据模板
	 */
	public List<Map<String, Object>> exportEntities(CIPPageInfo page,CIPReqCondition[] conditions,
			CIPRuntimeOperator operateInf, boolean xFirst);
	
}