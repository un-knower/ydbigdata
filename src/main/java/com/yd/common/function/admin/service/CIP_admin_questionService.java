package com.yd.common.function.admin.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.yd.common.data.CIPPageInfo;
import com.yd.common.data.CIPReqCondition;
import com.yd.common.function.admin.data.CIP_admin_questionData;
import com.yd.common.function.admin.data.vo.CIP_admin_questionVO;
import com.yd.common.runtime.CIPRuntimeOperator;

/**
 * <p>Service类</p>
 * <p>Class: CIP_admin_questionService</p>
 *
 * @since 2015-12-25 01:45:43
 */

@Service
public interface CIP_admin_questionService {

	public void updateData(CIP_admin_questionVO d, CIPRuntimeOperator operateInf);
	
	public void addData(CIP_admin_questionVO d, CIPRuntimeOperator operateInf);
	
	public void deleteData(Object[] keys, CIPRuntimeOperator operateInf);
	
	public CIP_admin_questionVO getData(Object[] keys);
	
	public List<CIP_admin_questionData> searchData(CIPPageInfo page, CIPReqCondition[] conditions);


	/** 
	 * 导出系统问题反馈
	 */
	public List<Map<String, Object>> exportEntities(CIPPageInfo page,CIPReqCondition[] conditions,
			CIPRuntimeOperator operateInf, boolean xFirst);

	public void handleData(CIP_admin_questionVO vo, CIPRuntimeOperator operateInf);
	
}