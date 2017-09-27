package com.yd.common.function.admin.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.yd.common.dao.IDao;
import com.yd.common.data.CIPPageInfo;
import com.yd.common.data.CIPReqCondition;
import com.yd.common.function.admin.data.CIP_admin_domainData;
import com.yd.common.function.admin.data.po.CIP_admin_domainPO;

/**
 * <p>Dao类</p>
 * <p>Class: CIP_admin_domain - 数据域信息</p>
 *
 * @since 2015-07-27 02:40:51
 */
 
@Repository
public interface CIP_admin_domainDao extends IDao<CIP_admin_domainPO> {
	
	public List<CIP_admin_domainData> searchData(CIPPageInfo page, CIPReqCondition[] conditions);
	
	public List<Map<String, Object>> exportEntities(CIPPageInfo page, CIPReqCondition[] conditions, boolean xFirst);
	
	/**
	* getData:(这里用一句话描述这个方法的作用).
	*
	* @author jh
	* @return
	* @since JDK 1.7
	*/
	public List<CIP_admin_domainData> getData();
}
