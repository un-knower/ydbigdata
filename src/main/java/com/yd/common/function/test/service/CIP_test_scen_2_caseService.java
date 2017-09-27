package com.yd.common.function.test.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.yd.common.data.CIPPageInfo;
import com.yd.common.data.CIPReqCondition;
import com.yd.common.function.test.data.CIP_test_scen_2_caseData;
import com.yd.common.function.test.data.vo.CIP_test_scen_2_caseVO;
import com.yd.common.runtime.CIPRuntimeOperator;

/**
 * <p>Service类</p>
 * <p>Class: CIP_test_scen_2_caseService</p>
 *
 * @since 2015-10-10 02:52:52
 */

@Service
public interface CIP_test_scen_2_caseService {

	public void updateData(CIP_test_scen_2_caseVO d, CIPRuntimeOperator operateInf);
	
	public void addData(CIP_test_scen_2_caseVO d, CIPRuntimeOperator operateInf);
	
	public void deleteData(Object[] keys, CIPRuntimeOperator operateInf);
	
	public CIP_test_scen_2_caseVO getData(Object[] keys);
	
	public List<CIP_test_scen_2_caseData> searchData(CIPPageInfo page, CIPReqCondition[] conditions);


	/** 
	 * 导出测试场景用例关系
	 */
	public List<Map<String, Object>> exportEntities(CIPPageInfo page,CIPReqCondition[] conditions,
			CIPRuntimeOperator operateInf, boolean xFirst);
	
}