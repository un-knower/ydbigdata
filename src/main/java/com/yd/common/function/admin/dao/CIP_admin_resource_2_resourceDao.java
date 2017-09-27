package com.yd.common.function.admin.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.yd.common.dao.IDao;
import com.yd.common.data.CIPPageInfo;
import com.yd.common.data.CIPReqCondition;
import com.yd.common.function.admin.data.CIP_admin_resource_2_resourceData;
import com.yd.common.function.admin.data.CIP_admin_resource_treeData;
import com.yd.common.function.admin.data.po.CIP_admin_resource_2_resourcePO;

/**
 * <p>Dao类</p>
 * <p>Class: CIP_admin_resource_2_resource - 资源与资源关系</p>
 *
 * @since 2015-07-27 02:40:52
 */
 
@Repository
public interface CIP_admin_resource_2_resourceDao extends IDao<CIP_admin_resource_2_resourcePO> {
	
	public List<CIP_admin_resource_2_resourceData> searchData(CIPPageInfo page, CIPReqCondition[] conditions);
	
	public List<Map<String, Object>> exportEntities(CIPPageInfo page, CIPReqCondition[] conditions, boolean xFirst);

	public List<CIP_admin_resource_treeData> queryByRootNodeId(String root_node_id);

	public void batchDelete(String root_res_id);
	
	public int queryLevel(String root,String res_id);
	
	public int queryOrder(String root,String parent_id);
	
	public String getRoleId(String root);
	
	public List<CIP_admin_resource_2_resourceData> searchNodeId(String root);
	
	public int getMaxLevel(String root);
	
	
}
