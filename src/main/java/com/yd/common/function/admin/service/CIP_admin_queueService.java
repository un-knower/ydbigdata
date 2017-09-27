package com.yd.common.function.admin.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.yd.common.data.CIPPageInfo;
import com.yd.common.data.CIPReqCondition;
import com.yd.common.function.admin.data.CIP_admin_queueData;
import com.yd.common.function.admin.data.vo.CIP_admin_queueVO;
import com.yd.common.runtime.CIPRuntimeOperator;

/**
 * <p>Service类</p>
 * <p>Class: CIP_admin_queueService</p>
 *
 * @since 2015-08-18 02:49:22
 */

@Service
public interface CIP_admin_queueService {

	public void updateData(CIP_admin_queueVO d, CIPRuntimeOperator operateInf);
	
	public void addData(CIP_admin_queueVO d, CIPRuntimeOperator operateInf);
	
	public void deleteData(Object[] keys, CIPRuntimeOperator operateInf);
	
	//提取队列数据
	public Object popQueue(String queueType);
	
	public List<Object> popQueue(String queueType, int count);
	
	//推送队列数据
	public void pushQueue(String queueType, Object queueEntry, CIPRuntimeOperator operateInf);
	
	//删除队列数据
	public void removePopEntries(String queueType, CIPRuntimeOperator operateInf);

	public void clearQueue(String queueType, CIPRuntimeOperator operateInf);
	
	public CIP_admin_queueVO getData(Object[] keys);
	
	public List<CIP_admin_queueData> searchData(CIPPageInfo page, CIPReqCondition[] conditions);


	/** 
	 * 导出系统队列信息
	 */
	public List<Map<String, Object>> exportEntities(CIPPageInfo page,CIPReqCondition[] conditions,
			CIPRuntimeOperator operateInf, boolean xFirst);


	
}