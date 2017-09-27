package com.yd.common.function.admin.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.yd.common.dao.IDao;
import com.yd.common.data.CIPPageInfo;
import com.yd.common.data.CIPReqCondition;
import com.yd.common.function.admin.data.CIP_admin_resourceData;
import com.yd.common.function.admin.data.po.CIP_admin_resourcePO;

/**
 * <p>Dao类</p>
 * <p>Class: CIP_admin_resource - 系统资源信息</p>
 *
 * @since 2015-07-27 02:40:52
 */
 
@Repository
public interface CIP_admin_resourceDao extends IDao<CIP_admin_resourcePO> {
	
	public List<CIP_admin_resourceData> searchData(CIPPageInfo page, CIPReqCondition[] conditions);
	
	public List<Map<String, Object>> exportEntities(CIPPageInfo page, CIPReqCondition[] conditions, boolean xFirst);

	public List<CIP_admin_resourceData> queryRoleNotAssignresource(String rootResId, CIPReqCondition[] conditions);
	
	public CIP_admin_resourceData searchNameById(String ID);
}
