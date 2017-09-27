package com.yd.common.function.admin.service.impl;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.common.cache.CIPCacheManager;
import com.yd.common.cache.CIPCacheService;
import com.yd.common.data.CIPPageInfo;
import com.yd.common.data.CIPReqCondition;
import com.yd.common.exception.CIPDaoException;
import com.yd.common.exception.CIPRuntimeException;
import com.yd.common.exception.CIPServiceException;
import com.yd.common.function.admin.dao.CIP_admin_op_logDao;
import com.yd.common.function.admin.dao.CIP_admin_textDao;
import com.yd.common.function.admin.data.CIP_admin_textData;
import com.yd.common.function.admin.data.po.CIP_admin_op_logPO;
import com.yd.common.function.admin.data.po.CIP_admin_textPO;
import com.yd.common.function.admin.data.vo.CIP_admin_textVO;
import com.yd.common.function.admin.service.CIP_admin_textService;
import com.yd.common.runtime.CIPErrorCode;
import com.yd.common.runtime.CIPRuntimeConfigure;
import com.yd.common.runtime.CIPRuntimeOperator;
import com.yd.common.utils.JSONUtils;

/**
 * <p>Service实现类</p>
 * <p>Class: CIP_admin_textService</p>
 *
 * @since 2015-08-18 02:49:22
 */

@Service(value="cipTextService")
public class CIP_admin_textServiceImpl implements CIP_admin_textService {
	
	@Autowired
	private CIP_admin_textDao textDao = null;
	@Autowired
	private CIP_admin_op_logDao opLogDao =null;
	
	/** 
	 * 修改数据
	 */
	@Override
	public void updateData(CIP_admin_textVO vo, CIPRuntimeOperator operateInf){
		
		CIP_admin_textPO po = vo.toPO();
		Object[] keys = po.getKeys();
		CIP_admin_textPO po0 = textDao.getSingle(keys);
		if( po0 == null ){
			throw new CIPServiceException(CIPErrorCode.ERROR_RECORD_NOT_EXISTS);
		}
		
		po.setUpdate_time(Timestamp.valueOf(operateInf.getOperate_tm()));
		po.setOperator(operateInf.getSubject_id());
		
		
		textDao.update(po);
		
	}
	
	/** 
	 * 添加数据
	 */
	@Override
	public void addData(CIP_admin_textVO vo, CIPRuntimeOperator operateInf){
		CIP_admin_textPO po = vo.toPO();
		Object[] keys = po.getKeys();
		CIP_admin_textPO po0 = textDao.getSingle(keys);
		if( po0!= null ){
			throw new CIPServiceException(CIPErrorCode.ERROR_DUMPLICATE_PRIMARY_KEY);
		}
		
		//TODO　添加记录基本判断
		po.setCreate_time(Timestamp.valueOf(operateInf.getOperate_tm()));
		po.setOperator(operateInf.getSubject_id());
		
		textDao.add(po);
	}
	
	/** 
	 * 删除数据
	 */
	@Override
	public void deleteData(Object[] keys, CIPRuntimeOperator operateInf){
		textDao.delete(keys);
		
	}
	
	/** 
	 * 获取数据
	 */		
	@Override
	public CIP_admin_textVO getData(Object[] keys ){
		CIP_admin_textPO  po = textDao.getSingle(keys);
		if(po == null)
			throw new CIPServiceException(CIPErrorCode.ERROR_RECORD_NOT_EXISTS);
			
		return po.toVO();
	}
	
	/** 
	 * 检索数据
	 */		
	@Override
	public List<CIP_admin_textData> searchData(CIPPageInfo page, CIPReqCondition[] conditions){
		
		List<CIP_admin_textData> datas = textDao.searchData(page, conditions);
		return datas;
	}

	
	public List<Map<String, Object>> exportEntities(CIPPageInfo page,CIPReqCondition[] conditions,
			CIPRuntimeOperator operateInf, boolean xFirst){
		if(xFirst){
			CIP_admin_op_logPO log = new CIP_admin_op_logPO();
			log.setOp_seq_no(System.currentTimeMillis());
			log.setOp_table_id("cip_admin_text");
			log.setOp_obj_id("");
			log.setOp_type("E");
			log.setRemark("批量导出数据");
			log.setOperator(operateInf.getSubject_id());
			log.setCreate_time(Timestamp.valueOf(operateInf.getOperate_tm()));
			opLogDao.add(log);
		}
		return textDao.exportEntities(page, conditions, xFirst);
	}

	@Override
	public String getText(Object[] keys) {
		CIP_admin_textPO textPO = null;
		try {
			textPO = textDao.getSingle(keys);
		} catch (CIPDaoException e) {
			return null;
		}
		if(textPO==null)
			return null;
		
		return textPO.getMsg_txt();
	}

	/**
	* TODO 将数据库中的文本文档存到redis缓存中.
	* @see com.yd.common.function.admin.service.CIP_admin_textService#save(java.lang.String, java.lang.String)
	*/
	@Override	
	public void save() {
		List<CIP_admin_textPO> vos = textDao.getAll();
		Map<String, String> map = new HashMap<String, String>();
		String sys_id = CIPRuntimeConfigure.cip_system_id;
		//拼接主键 系统id+标识串
		String key = sys_id+"@errorCode";
		for(int i=0;i<vos.size();i++){
			int no = vos.get(i).getMsg_no();
			String type = vos.get(i).getLang_type();//获取语言类型
			String code = type+String.valueOf(no);//拼接语言类型 + 文本id
			String name = vos.get(i).getMsg_txt();
			map.put(code, name);
		}		
		//-1表示不设置过期时间
		saved(key, map);
	}
	//将封装的数据以K-V的格式存放在缓存cache中
	private void saved(String key,Map<String, String> map) {		
		CIPCacheService	cacheService = CIPCacheManager.getCacheService();
		boolean setExpireTime = false;
		try {
			cacheService.set(key, JSONUtils.convertObject2Json(map).getBytes("utf-8"), setExpireTime);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new CIPRuntimeException(CIPErrorCode.ERROR_DATA_IS_MALFORM);
		}
		/*
		//连接远程缓存 redis
		 Jedis jedis = null;
		boolean isJedisConnectExceptionOccured = false;
		try {
			jedis = CIPRedisUtils.getJedisResource();
			jedis.set(key.getBytes("utf-8"), JSONUtils.convertObject2Json(map).getBytes("utf-8"));
			if (expireTime > 0) {
				jedis.expire(key.getBytes("utf-8"), expireTime);
			}
		} catch (JedisConnectionException e) {
			isJedisConnectExceptionOccured = false;
			throw new CIPRuntimeException(CIPErrorCode.ERROR_HTTP_REQUEST_IS_NOT_AVAILABLE);
		} catch (Exception e) {
			throw new CIPRuntimeException(CIPErrorCode.ERROR_TECHNICAL_ERROR);
		} finally {
			CIPRedisUtils.returnJedisResource(jedis,isJedisConnectExceptionOccured);
		}*/
	}
}