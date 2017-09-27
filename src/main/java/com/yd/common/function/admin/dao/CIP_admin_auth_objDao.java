package com.yd.common.function.admin.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.yd.common.dao.IDao;
import com.yd.common.data.CIPPageInfo;
import com.yd.common.data.CIPReqCondition;
import com.yd.common.function.admin.data.CIP_admin_auth_objData;
import com.yd.common.function.admin.data.po.CIP_admin_auth_objPO;

/**
 * <p>Dao类</p>
 * <p>Class: CIP_admin_auth_obj - 权限对象信息</p>
 *
 * @since 2015-07-27 02:40:51
 */
 
@Repository
public interface CIP_admin_auth_objDao extends IDao<CIP_admin_auth_objPO> {
	
	public List<CIP_admin_auth_objData> searchData(CIPPageInfo page, CIPReqCondition[] conditions);
	
	public List<Map<String, Object>> exportEntities(CIPPageInfo page, CIPReqCondition[] conditions, boolean xFirst);
}
