package com.yd.common.function.admin.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.yd.common.dao.IDao;
import com.yd.common.data.CIPPageInfo;
import com.yd.common.data.CIPReqCondition;
import com.yd.common.function.admin.data.CIP_admin_op_logData;
import com.yd.common.function.admin.data.po.CIP_admin_op_logPO;

/**
 * <p>Dao类</p>
 * <p>Class: CIP_admin_op_log - 系统操作日志</p>
 *
 * @since 2015-07-27 02:40:51
 */
 
@Repository
public interface CIP_admin_op_logDao extends IDao<CIP_admin_op_logPO> {
	
	public List<CIP_admin_op_logData> searchData(CIPPageInfo page, CIPReqCondition[] conditions);
	
	public List<Map<String, Object>> exportEntities(CIPPageInfo page, CIPReqCondition[] conditions, boolean xFirst);
}
