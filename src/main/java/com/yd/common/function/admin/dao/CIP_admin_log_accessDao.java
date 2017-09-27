package com.yd.common.function.admin.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.yd.common.dao.IDao;
import com.yd.common.data.CIPPageInfo;
import com.yd.common.data.CIPReqCondition;
import com.yd.common.function.admin.data.CIP_admin_log_accessData;
import com.yd.common.function.admin.data.po.CIP_admin_log_accessPO;

/**
 * <p>Dao类</p>
 * <p>Class: CIP_admin_log_access - 本地日志-系统访问</p>
 *
 * @since 2015-08-26 08:45:49
 */
 
@Repository
public interface CIP_admin_log_accessDao extends IDao<CIP_admin_log_accessPO> {
	
	public List<CIP_admin_log_accessData> searchData(CIPPageInfo page, CIPReqCondition[] conditions);
	
	public List<Map<String, Object>> exportEntities(CIPPageInfo page, CIPReqCondition[] conditions, boolean xFirst);
}
