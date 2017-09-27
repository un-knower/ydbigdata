package com.yd.common.function.admin.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.yd.common.dao.IDao;
import com.yd.common.data.CIPPageInfo;
import com.yd.common.data.CIPReqCondition;
import com.yd.common.function.admin.data.CIP_admin_commonqueryData;
import com.yd.common.function.admin.data.po.CIP_admin_commonqueryPO;

/**
 * <p>Dao类</p>
 * <p>Class: CIP_cadmin_commonquery - 通用查询配置表</p>
 *
 * @since 2016-08-04 01:17:07
 */
 
@Repository
public interface CIP_admin_commonqueryDao extends IDao<CIP_admin_commonqueryPO> {
	
	public List<CIP_admin_commonqueryData> searchData(CIPPageInfo page, CIPReqCondition[] conditions);
	
	public List<Map<String, Object>> exportEntities(CIPPageInfo page, CIPReqCondition[] conditions, boolean xFirst);
	
	public List<CIP_admin_commonqueryData> getAllData();
}
