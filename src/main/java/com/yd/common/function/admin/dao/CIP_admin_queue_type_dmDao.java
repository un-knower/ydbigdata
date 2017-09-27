package com.yd.common.function.admin.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.yd.common.dao.IDao;
import com.yd.common.data.CIPPageInfo;
import com.yd.common.data.CIPReqCondition;
import com.yd.common.function.admin.data.CIP_admin_queue_type_dmData;
import com.yd.common.function.admin.data.po.CIP_admin_queue_type_dmPO;

/**
 * <p>Dao类</p>
 * <p>Class: CIP_admin_queue_type_dm - 系统队列配置</p>
 *
 * @since 2015-08-18 02:49:22
 */
 
@Repository
public interface CIP_admin_queue_type_dmDao extends IDao<CIP_admin_queue_type_dmPO> {
	
	public List<CIP_admin_queue_type_dmData> searchData(CIPPageInfo page, CIPReqCondition[] conditions);
	
	public List<Map<String, Object>> exportEntities(CIPPageInfo page, CIPReqCondition[] conditions, boolean xFirst);
}
