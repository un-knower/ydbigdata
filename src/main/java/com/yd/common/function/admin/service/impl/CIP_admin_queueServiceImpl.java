package com.yd.common.function.admin.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.common.data.CIPPageInfo;
import com.yd.common.data.CIPReqCondition;
import com.yd.common.exception.CIPRuntimeException;
import com.yd.common.exception.CIPServiceException;
import com.yd.common.function.admin.constants.CIPAdminConstants;
import com.yd.common.function.admin.dao.CIP_admin_op_logDao;
import com.yd.common.function.admin.dao.CIP_admin_queueDao;
import com.yd.common.function.admin.dao.CIP_admin_queue_type_dmDao;
import com.yd.common.function.admin.data.CIP_admin_queueData;
import com.yd.common.function.admin.data.po.CIP_admin_op_logPO;
import com.yd.common.function.admin.data.po.CIP_admin_queuePO;
import com.yd.common.function.admin.data.po.CIP_admin_queue_type_dmPO;
import com.yd.common.function.admin.data.vo.CIP_admin_queueVO;
import com.yd.common.function.admin.service.CIP_admin_queueService;
import com.yd.common.runtime.CIPErrorCode;
import com.yd.common.runtime.CIPRuntimeOperator;
import com.yd.common.utils.DateUtils;
import com.yd.common.utils.JSONUtils;
import com.yd.common.utils.SerialNoUtils;

/**
 * <p>
 * Service实现类
 * </p>
 * <p>
 * Class: CIP_admin_queueService
 * </p>
 * 
 * @since 2015-08-18 05:02:08
 */

@Service(value = "cipQueueService")
public class CIP_admin_queueServiceImpl implements CIP_admin_queueService {

	@Autowired
	private CIP_admin_queueDao queueDao = null;
	@Autowired
	private CIP_admin_op_logDao opLogDao = null;
	@Autowired
	private CIP_admin_queue_type_dmDao queueTypeDao = null;

	/**
	 * 修改数据
	 */
	@Override
	public void updateData(CIP_admin_queueVO vo, CIPRuntimeOperator operateInf) {
		// TODO
		CIP_admin_queuePO po = vo.toPO();
		Object[] keys = po.getKeys();
		CIP_admin_queuePO po0 = queueDao.getSingle(keys);
		if (po0 == null) {
			throw new CIPServiceException(CIPErrorCode.ERROR_RECORD_NOT_EXISTS);
		}

		po.setUpdate_time(Timestamp.valueOf(operateInf.getOperate_tm()));
		po.setOperator(operateInf.getSubject_id());

		queueDao.update(po);

	}

	/**
	 * 添加数据
	 */
	@Override
	public void addData(CIP_admin_queueVO vo, CIPRuntimeOperator operateInf) {
		CIP_admin_queuePO po = vo.toPO();
		Object[] keys = po.getKeys();
		CIP_admin_queuePO po0 = queueDao.getSingle(keys);
		if (po0 != null) {
			throw new CIPServiceException(
					CIPErrorCode.ERROR_DUMPLICATE_PRIMARY_KEY);
		}

		// TODO　添加记录基本判断
		po.setCreate_time(Timestamp.valueOf(operateInf.getOperate_tm()));
		po.setOperator(operateInf.getSubject_id());

		queueDao.add(po);
	}

	/**
	 * 删除数据
	 */
	@Override
	public void deleteData(Object[] keys, CIPRuntimeOperator operateInf) {
		queueDao.delete(keys);

	}

	/**
	 * 获取数据
	 */
	@Override
	public CIP_admin_queueVO getData(Object[] keys) {
		CIP_admin_queuePO po = queueDao.getSingle(keys);
		if (po == null)
			throw new CIPServiceException(CIPErrorCode.ERROR_RECORD_NOT_EXISTS);

		return po.toVO();
	}

	/**
	 * 检索数据
	 */
	@Override
	public List<CIP_admin_queueData> searchData(CIPPageInfo page,
			CIPReqCondition[] conditions) {

		List<CIP_admin_queueData> datas = queueDao.searchData(page, conditions);
		return datas;
	}

	public List<Map<String, Object>> exportEntities(CIPPageInfo page,
			CIPReqCondition[] conditions, CIPRuntimeOperator operateInf,
			boolean xFirst) {
		if (xFirst) {
			CIP_admin_op_logPO log = new CIP_admin_op_logPO();
			log.setOp_seq_no(System.currentTimeMillis());
			log.setOp_table_id("cip_admin_queue");
			log.setOp_obj_id("");
			log.setOp_type("E");
			log.setRemark("批量导出数据");
			log.setOperator(operateInf.getSubject_id());
			log.setCreate_time(Timestamp.valueOf(operateInf.getOperate_tm()));
			opLogDao.add(log);
		}
		return queueDao.exportEntities(page, conditions, xFirst);
	}

	
	@Override
	public Object popQueue(String queueType) {
		CIP_admin_queue_type_dmPO queueTypePO = queueTypeDao.getSingle(queueType);
		Class<?> pojo;
		try {
			pojo = Class.forName(queueTypePO.getPojo_class());
		} catch (ClassNotFoundException e) {
			return null;
		}
		
		CIP_admin_queuePO queueEntry = queueDao.popQueueEntry(queueType);
		if(queueEntry!=null&&queueEntry.getQueue_data()!=null){
			Object data = JSONUtils.convertDataJson2Object(queueEntry.getQueue_data(), pojo);
			
			Map<String,Object> params = new HashMap<String,Object>(2);
			params.put(CIPAdminConstants.QUEUE_FIELD_QUEUE_FLAG, CIPAdminConstants.QUEUE_QUEUE_FLAG_POP);
			params.put(CIPAdminConstants.ADMIN_FIELD_UPDATE_TIME, DateUtils.getDateTime(new Date()));
			queueDao.updateKV(params, queueEntry.getKeys());
			
			return data;
		}
		
		return null;
	}

	@Override
	public List<Object> popQueue(String queueType, int count) {
		CIP_admin_queue_type_dmPO queueTypePO = queueTypeDao.getSingle(queueType);
		Class<?> pojo;
		try {
			pojo = Class.forName(queueTypePO.getPojo_class());
		} catch (ClassNotFoundException e) {
			return null;
		}
		
		List<CIP_admin_queuePO> queueEntries = queueDao.popQueueEntries(queueType, count);
		List<Object> queues = new ArrayList<Object>(queueEntries.size());
		
		if(queueEntries!=null){
			Object data;
			Map<String,Object> params = new HashMap<String,Object>(2);
			for(CIP_admin_queuePO queueEntry:queueEntries){
				data = JSONUtils.convertDataJson2Object(queueEntry.getQueue_data(), pojo);
				params.put(CIPAdminConstants.QUEUE_FIELD_QUEUE_FLAG, CIPAdminConstants.QUEUE_QUEUE_FLAG_POP);
				params.put(CIPAdminConstants.ADMIN_FIELD_UPDATE_TIME, DateUtils.getDateTime(new Date()));
				queueDao.updateKV(params, queueEntry.getKeys());
				
				queues.add(data);
			}
		}
		
		return queues;
	}

	@Override
	public void pushQueue(String queueType, Object queueEntry, CIPRuntimeOperator operateInf) {
		CIP_admin_queue_type_dmPO queueTypePO = queueTypeDao.getSingle(queueType);
		Class<?> pojo = queueEntry.getClass();
		String clazz = pojo.getName();
		if(queueTypePO == null || !clazz.equals(queueTypePO.getPojo_class())){
			throw new CIPRuntimeException(new CIPErrorCode(1000,"队列类型对应的数据对象不正确"));
		}
		
		String json = JSONUtils.convertObject2Json(queueEntry);
		CIP_admin_queuePO queue = new CIP_admin_queuePO();
		long time = System.currentTimeMillis();
		queue.setQueue_type(queueType);
		queue.setQueue_data(json);
		queue.setQueue_flag(CIPAdminConstants.QUEUE_QUEUE_FLAG_UNPOP);
		queue.setCreate_time(new Timestamp(time));
		queue.setQueue_id(SerialNoUtils.getTimeSerialNo());
		queue.setOperator(operateInf.getSubject_id());
		
		queueDao.add(queue);
	}

	@Override
	public void removePopEntries(String queueType, CIPRuntimeOperator operateInf) {
		queueDao.deleteEntries(queueType, CIPAdminConstants.QUEUE_QUEUE_FLAG_POP);
	}

	@Override
	public void clearQueue(String queueType, CIPRuntimeOperator operateInf) {
		queueDao.deleteEntries(queueType);
	}
}