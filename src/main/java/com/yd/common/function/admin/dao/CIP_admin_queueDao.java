package com.yd.common.function.admin.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.yd.common.dao.IDao;
import com.yd.common.data.CIPPageInfo;
import com.yd.common.data.CIPReqCondition;
import com.yd.common.function.admin.data.CIP_admin_queueData;
import com.yd.common.function.admin.data.po.CIP_admin_queuePO;

/**
 * <p>Dao类</p>
 * <p>Class: CIP_admin_queue - 系统队列信息</p>
 *
 * @since 2015-08-18 05:02:08
 */
 
@Repository
public interface CIP_admin_queueDao extends IDao<CIP_admin_queuePO> {
	
	public List<CIP_admin_queueData> searchData(CIPPageInfo page, CIPReqCondition[] conditions);
	
	public List<Map<String, Object>> exportEntities(CIPPageInfo page, CIPReqCondition[] conditions, boolean xFirst);

	public CIP_admin_queuePO popQueueEntry(String queueType);

	public List<CIP_admin_queuePO> popQueueEntries(String queueType, int count);

	public void deleteEntries(String queueType, String queuePop);

	public void deleteEntries(String queueType);
}
