package com.yd.common.function.admin.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.common.cache.CIPCacheManager;
import com.yd.common.cache.CIPCacheService;
import com.yd.common.data.CIPPageInfo;
import com.yd.common.data.CIPReqCondition;
import com.yd.common.exception.CIPServiceException;
import com.yd.common.function.admin.dao.CIP_admin_op_logDao;
import com.yd.common.function.admin.dao.CIP_admin_resourceDao;
import com.yd.common.function.admin.dao.CIP_admin_resource_2_resourceDao;
import com.yd.common.function.admin.data.CIP_admin_resource_2_resourceData;
import com.yd.common.function.admin.data.po.CIP_admin_op_logPO;
import com.yd.common.function.admin.data.po.CIP_admin_resourcePO;
import com.yd.common.function.admin.data.po.CIP_admin_resource_2_resourcePO;
import com.yd.common.function.admin.data.vo.CIP_admin_add_2_configResourceVO;
import com.yd.common.function.admin.data.vo.CIP_admin_resource_2_resourceVO;
import com.yd.common.function.admin.service.CIP_admin_resource_2_resourceService;
import com.yd.common.runtime.CIPErrorCode;
import com.yd.common.runtime.CIPRuntime;
import com.yd.common.runtime.CIPRuntimeConfigure;
import com.yd.common.runtime.CIPRuntimeConstants;
import com.yd.common.runtime.CIPRuntimeOperator;

/**
 * <p>Service实现类</p>
 * <p>Class: CIP_admin_resource_2_resourceService</p>
 *
 * @since 2015-07-27 02:40:52
 */

@Service
public class CIP_admin_resource_2_resourceServiceImpl implements CIP_admin_resource_2_resourceService {
	
	@Autowired
	private CIP_admin_resource_2_resourceDao dataDao = null;
	@Autowired
	private CIP_admin_resourceDao resDao = null;
	@Autowired
	private CIP_admin_op_logDao opLogDao =null;
	
	/** 
	 * 修改数据
	 */
	@Override
	public void updateData(CIP_admin_resource_2_resourceVO vo, CIPRuntimeOperator operateInf){
		//TODO
		CIP_admin_resource_2_resourcePO po = vo.toPO();
		Object[] keys = po.getKeys();
		CIP_admin_resource_2_resourcePO po0 = dataDao.getSingle(keys);
		if( po0 == null ){
			throw new CIPServiceException(CIPErrorCode.ERROR_RECORD_NOT_EXISTS);
		}
		
		po.setUpdate_time(Timestamp.valueOf(operateInf.getOperate_tm()));
		po.setOperator(operateInf.getSubject_id());
		
		
		dataDao.update(po);
		
	}
	
	/** 
	 * 添加数据
	 */
	@Override
	public void addData(CIP_admin_resource_2_resourceVO vo, CIPRuntimeOperator operateInf){
		CIP_admin_resource_2_resourcePO po = vo.toPO();
		Object[] keys = po.getKeys();
		CIP_admin_resource_2_resourcePO po0 = dataDao.getSingle(keys);
		if( po0!= null ){
			throw new CIPServiceException(CIPErrorCode.ERROR_DUMPLICATE_PRIMARY_KEY);
		}
		
		//TODO　添加记录基本判断
		po.setCreate_time(Timestamp.valueOf(operateInf.getOperate_tm()));
		po.setOperator(operateInf.getSubject_id());
		
		dataDao.add(po);
	}
	
	/** 
	 * 删除数据
	 */
	@Override
	public void deleteData(Object[] keys, CIPRuntimeOperator operateInf){
		dataDao.delete(keys);
		
	}
	
	/** 
	 * 获取数据
	 */		
	@Override
	public CIP_admin_resource_2_resourceVO getData(Object[] keys ){
		CIP_admin_resource_2_resourcePO  po = dataDao.getSingle(keys);
		if(po == null)
			throw new CIPServiceException(CIPErrorCode.ERROR_RECORD_NOT_EXISTS);
			
		return po.toVO();
	}
	
	/** 
	 * 检索数据
	 */		
	@Override
	public List<CIP_admin_resource_2_resourceData> searchData(CIPPageInfo page, CIPReqCondition[] conditions){
		
		List<CIP_admin_resource_2_resourceData> datas = dataDao.searchData(page, conditions);
		return datas;
	}

	
	public List<Map<String, Object>> exportEntities(CIPPageInfo page,CIPReqCondition[] conditions,
			CIPRuntimeOperator operateInf, boolean xFirst){
		if(xFirst){
			CIP_admin_op_logPO log = new CIP_admin_op_logPO();
			log.setOp_seq_no(System.currentTimeMillis());
			log.setOp_table_id("cip_admin_resource_2_resource");
			log.setOp_obj_id("");
			log.setOp_type("E");
			log.setRemark("批量导出数据");
			log.setOperator(operateInf.getSubject_id());
			log.setCreate_time(Timestamp.valueOf(operateInf.getOperate_tm()));
			opLogDao.add(log);
		}
		return dataDao.exportEntities(page, conditions, xFirst);
	}

	@Override
	public void addAndConfigRes(CIP_admin_add_2_configResourceVO vo,CIPRuntimeOperator operateInf) {
		CIP_admin_resourcePO po = new CIP_admin_resourcePO();
		po.setResource_id(vo.getResource_id());
		po.setIcon_id(vo.getIcon_id());
		po.setResource_desc(vo.getResource_desc());
		po.setCreate_time(Timestamp.valueOf(operateInf.getOperate_tm()));
		po.setOperator(operateInf.getSubject_id());
		po.setResource_name(vo.getResource_name());
		po.setResource_type(vo.getResource_type());
		po.setSys_uri(vo.getSys_uri());
		resDao.add(po);
		int Level= dataDao.queryLevel(vo.getRoot_node_id(), vo.getRes_node_sup());
		int Order=dataDao.queryOrder(vo.getRoot_node_id(), vo.getRes_node_sup());
		CIP_admin_resource_2_resourcePO data=new CIP_admin_resource_2_resourcePO();
		data.setLeaf_flag(0);
		data.setRes_level(Level+1);
		data.setNode_order(Order+1);
		data.setRes_node_id(vo.getResource_id());
		data.setRes_node_sup(vo.getRes_node_sup());
		data.setRoot_flag(0);
		data.setRoot_node_id(vo.getRoot_node_id());
		dataDao.add(data);
		
		String roleId = dataDao.getRoleId(vo.getRoot_node_id());
		// 刷新角色资源缓存
		CIPRuntime.authManager.reloadResources(roleId);
		// 清空缓存服务中角色资源，解决角色资源树重新保存时用户资源树使用之前的缓存
		CIPCacheService cache = CIPCacheManager.getCacheService();
		cache.remove(CIPRuntimeConfigure.cip_system_id + "_"+ CIPRuntimeConstants.CIP_MENU_ID + roleId);
		
	}

	/**
	* TODO 添加未分配的资源.
	* @see com.yd.common.function.admin.service.CIP_admin_resource_2_resourceService#addResources(java.util.List, com.yd.common.runtime.CIPRuntimeOperator)
	*/
	@Override
	public void addResources(List<CIP_admin_resource_2_resourceVO> vos,CIPRuntimeOperator operateInf) {
		List<CIP_admin_resource_2_resourcePO> pos = new ArrayList<CIP_admin_resource_2_resourcePO>();
		int Level= dataDao.queryLevel(vos.get(0).getRoot_node_id(), vos.get(0).getRes_node_sup());
		int Order=dataDao.queryOrder(vos.get(0).getRoot_node_id(), vos.get(0).getRes_node_sup());
		for (int i = 0; i < vos.size(); i++) {
			CIP_admin_resource_2_resourcePO po = new CIP_admin_resource_2_resourcePO();
			po.setRoot_node_id(vos.get(i).getRoot_node_id());
			po.setRes_node_id(vos.get(i).getRes_node_id());
			po.setRes_node_sup(vos.get(i).getRes_node_sup());
			po.setNode_order(Order+1);
			po.setRoot_flag(0);
			po.setRes_level(Level+1);
			po.setLeaf_flag(0);
			po.setCreate_time(Timestamp.valueOf(operateInf.getOperate_tm()));
			po.setOperator(operateInf.getSubject_id());
			pos.add(po);
		}
		dataDao.batchAdd(pos);
	}

	@Override
	public int getMaxLevel(String root) {
		int data = dataDao.getMaxLevel(root);
		return data;
	}
	
}