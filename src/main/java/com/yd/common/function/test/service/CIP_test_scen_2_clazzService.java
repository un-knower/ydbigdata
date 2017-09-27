package com.yd.common.function.test.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.yd.common.data.CIPPageInfo;
import com.yd.common.data.CIPReqCondition;
import com.yd.common.function.test.data.CIP_test_scen_2_clazzData;
import com.yd.common.function.test.data.vo.CIP_test_scen_2_clazzVO;
import com.yd.common.runtime.CIPRuntimeOperator;

/**
 * <p>Service类</p>
 * <p>Class: CIP_test_scen_2_clazzService</p>
 *
 * @since 2015-10-10 02:52:52
 */

@Service
public interface CIP_test_scen_2_clazzService {

	public void updateData(CIP_test_scen_2_clazzVO d, CIPRuntimeOperator operateInf);
	
	public void addData(CIP_test_scen_2_clazzVO d, CIPRuntimeOperator operateInf);
	
	public void deleteData(Object[] keys, CIPRuntimeOperator operateInf);
	
	public CIP_test_scen_2_clazzVO getData(Object[] keys);
	
	public List<CIP_test_scen_2_clazzData> searchData(CIPPageInfo page, CIPReqCondition[] conditions);


	/** 
	 * 导出测试类与场景关系
	 */
	public List<Map<String, Object>> exportEntities(CIPPageInfo page,CIPReqCondition[] conditions,
			CIPRuntimeOperator operateInf, boolean xFirst);
	
}