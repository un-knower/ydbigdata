package com.yd.common.function.admin.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.yd.common.dao.IDao;
import com.yd.common.data.CIPPageInfo;
import com.yd.common.data.CIPReqCondition;
import com.yd.common.function.admin.data.CIP_admin_access_resData;
import com.yd.common.function.admin.data.po.CIP_admin_access_resPO;

/**
 * <p>Dao类</p>
 * <p>Class: CIP_admin_access_res - 系统访问资源配置</p>
 *
 * @since 2015-08-20 09:11:16
 */
 
@Repository
public interface CIP_admin_access_resDao extends IDao<CIP_admin_access_resPO> {
	
	public List<CIP_admin_access_resData> searchData(CIPPageInfo page, CIPReqCondition[] conditions);
	
	public List<Map<String, Object>> exportEntities(CIPPageInfo page, CIPReqCondition[] conditions, boolean xFirst);
}
