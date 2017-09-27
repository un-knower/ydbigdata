package com.yd.common.function.meta.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.yd.common.data.CIPPageInfo;
import com.yd.common.data.CIPReqCondition;
import com.yd.common.function.meta.data.CIPFileInfo;
import com.yd.common.function.meta.data.CIP_meta_strData;
import com.yd.common.function.meta.data.vo.CIP_meta_strVO;
import com.yd.common.runtime.CIPRuntimeOperator;

/**
 * <p>Service类</p>
 * <p>Class: CIP_meta_strService</p>
 *
 * @since 2015-10-08 09:03:37
 */

@Service
public interface CIP_meta_strService {

	public void updateData(CIP_meta_strVO d, CIPRuntimeOperator operateInf);
	
	public void addData(CIP_meta_strVO d, CIPRuntimeOperator operateInf);
	
	public void deleteData(Object[] keys, CIPRuntimeOperator operateInf);
	
	public CIP_meta_strVO getData(Object[] keys);
	
	public List<CIP_meta_strData> searchData(CIPPageInfo page, CIPReqCondition[] conditions);
	
	public void changeSubmit();
	
	public void changeRefuse(String rows);
	
	public void changeRecovery(String row);
	


	/** 
	 * 导出系统数据结构表
	 */
	public List<Map<String, Object>> exportEntities(CIPPageInfo page,CIPReqCondition[] conditions,
			CIPRuntimeOperator operateInf, boolean xFirst);
	
	public void sync2Meta(List<CIP_meta_strVO> vos);

	public List<CIPFileInfo> regenerate(String str_id, List<String> modules) throws Exception;
	
}