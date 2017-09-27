package com.yd.common.function.admin.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;

import com.yd.common.cache.CIPRedisUtils;
import com.yd.common.data.CIPPageInfo;
import com.yd.common.data.CIPReqCondition;
import com.yd.common.exception.CIPServiceException;
import com.yd.common.function.admin.data.CIP_admin_redisData;
import com.yd.common.function.admin.data.vo.CIP_admin_redisVO;
import com.yd.common.function.admin.service.CIP_admin_redisService;
import com.yd.common.runtime.CIPErrorCode;
import com.yd.common.runtime.CIPRuntimeOperator;

/**
 * <p>
 * Service实现类
 * </p>
 * <p>
 * Class: CIP_admin_redisService
 * </p>
 * 
 * @since 2015-08-24 01:32:29
 */

@Service
public class CIP_admin_redisServiceImpl implements CIP_admin_redisService {

	/**
	 * 修改数据
	 */
	@Override
	public void updateData(CIP_admin_redisVO vo, CIPRuntimeOperator operateInf) {
		Jedis jedis = null;
		try {
			jedis = CIPRedisUtils.getJedisResource();
			if(vo.getRedis_ttl()==-1)
				jedis.persist(vo.getKeys());
			else
				jedis.expire(vo.getKeys(), (int) vo.getRedis_ttl());
			
		} catch (Exception e) {
			throw new CIPServiceException(e);
		}
	}

	/**
	 * 添加数据
	 */
	@Override
	public void addData(CIP_admin_redisVO vo, CIPRuntimeOperator operateInf) {
		throw new CIPServiceException(CIPErrorCode.ERROR_FUNCTION_NOT_SUPPORT);
	}

	/**
	 * 删除数据
	 */
	@Override
	public void deleteData(String key, CIPRuntimeOperator operateInf) {
		Jedis jedis = null;
		try {
			jedis = CIPRedisUtils.getJedisResource();
			jedis.del(key);
		} catch (Exception e) {
			throw new CIPServiceException(e);
		}
	}

	/**
	 * 获取数据
	 */
	@Override
	public CIP_admin_redisVO getData(String key) {
		Jedis jedis = null;
		try {
			jedis = CIPRedisUtils.getJedisResource();
			
			CIP_admin_redisVO data = new CIP_admin_redisVO();
			data.setRedis_key(key);
			String type = jedis.type(key);
			data.setRedis_dtype(type);
			if (type.equals("none")) {
				return null;
			} else if (type.equals("string")) {
				data.setRedis_size(1);
			} else if (type.equals("hash")) {
				data.setRedis_size(jedis.hlen(key));
			} else if (type.equals("list")) {
				data.setRedis_size(jedis.llen(key));
			} else if (type.equals("set")) {
				data.setRedis_size(jedis.scard(key));
			} else if (type.equals("zset")) {
				data.setRedis_size(jedis.zcard(key));
			}

			data.setRedis_ttl(jedis.ttl(key));

			return data;
		} catch (Exception e) {
			throw new CIPServiceException(e);
		}
	}

	/**
	 * 检索数据
	 */
	@Override
	public List<CIP_admin_redisData> searchData(CIPPageInfo page,
			CIPReqCondition[] conditions) {

		Jedis jedis = null;
		try {
			jedis = CIPRedisUtils.getJedisResource();
			Set<String> keys;
			if (conditions == null)
				keys = jedis.keys("*");
			else
				keys = jedis.keys(conditions[0].getLowValue());

			if (keys == null || keys.size() == 0)
				return null;

			CIP_admin_redisData data = null;
			List<CIP_admin_redisData> datas = new ArrayList<CIP_admin_redisData>(
					keys.size());
			String type;
			for (String key : keys) {
				data = new CIP_admin_redisData();
				data.setRedis_key(key);
				type = jedis.type(key);
				data.setRedis_dtype(type);
				if (type.equals("none")) {
					continue;
				} else if (type.equals("string")) {
					data.setRedis_size(1);
				} else if (type.equals("hash")) {
					data.setRedis_size(jedis.hlen(key));
				} else if (type.equals("list")) {
					data.setRedis_size(jedis.llen(key));
				} else if (type.equals("set")) {
					data.setRedis_size(jedis.scard(key));
				} else if (type.equals("zset")) {
					data.setRedis_size(jedis.zcard(key));
				}

				data.setRedis_ttl(jedis.ttl(key));
				datas.add(data);
			}

			return datas;
		} catch (Exception e) {
			throw new CIPServiceException(e);
		}
	}

	public List<Map<String, Object>> exportEntities(CIPPageInfo page,
			CIPReqCondition[] conditions, CIPRuntimeOperator operateInf,
			boolean xFirst) {
		return null;
		// if(xFirst){
		// CIP_admin_op_logPO log = new CIP_admin_op_logPO();
		// log.setOp_seq_no(System.currentTimeMillis());
		// log.setOp_table_id("cip_admin_redis");
		// log.setOp_obj_id("");
		// log.setOp_type("E");
		// log.setRemark("批量导出数据");
		// log.setOperator(operateInf.getSubject_id());
		// log.setCreate_time(Timestamp.valueOf(operateInf.getOperate_tm()));
		// opLogDao.add(log);
		// }
		// return dataDao.exportEntities(page, conditions, xFirst);
	}

	public static void main(String args[]) {
		Jedis jedis = null;
		try {
			jedis = CIPRedisUtils.getJedisResource();
			// List<Object> values = jedis.getClient().getAll(0);
			// for(Object o : values){
			// System.out.println(o);
			// }
			//

			System.out.println(jedis.lrange("a", 0, -1));
			Set<String> keys = jedis.keys("*");

			for (String key : keys) {
				System.out.println(key);
				System.out.println(jedis.type(key));
			}
			// System.out.println(jedis.getClient().get);
			// System.out.println(jedis.get("meta3_cip_menu@superAdmin"));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	* TODO 简单描述该方法的实现功能（可选）.
	* @see com.yd.common.function.admin.service.CIP_admin_redisService#reloadData()
	*/
	@Override
	public void reloadData() {
		try {
			CIPRedisUtils.getJedisResource();
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
	}

}