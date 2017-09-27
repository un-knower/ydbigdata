package com.yd.common.function.admin.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;

import com.yd.common.cache.CIPRedisUtils;
import com.yd.common.data.CIPPageInfo;
import com.yd.common.data.CIPReqCondition;
import com.yd.common.exception.CIPServiceException;
import com.yd.common.function.admin.dao.CIP_admin_op_logDao;
import com.yd.common.function.admin.data.CIP_admin_redis_valData;
import com.yd.common.function.admin.data.vo.CIP_admin_redis_valVO;
import com.yd.common.function.admin.service.CIP_admin_redis_valService;
import com.yd.common.runtime.CIPRuntimeOperator;

/**
 * <p>
 * Service实现类
 * </p>
 * <p>
 * Class: CIP_admin_redis_valService
 * </p>
 * 
 * @since 2015-08-24 09:34:48
 */

@Service
public class CIP_admin_redis_valServiceImpl implements
		CIP_admin_redis_valService {

	@Autowired
	private CIP_admin_op_logDao opLogDao = null;

	/**
	 * 修改数据
	 */
	@Override
	public void updateData(CIP_admin_redis_valVO vo,
			CIPRuntimeOperator operateInf) {
		Jedis jedis = null;
		try {
			jedis = CIPRedisUtils.getJedisResource();
			String type = vo.getRedis_dtype();
			if (type.equals("string")) {
				jedis.set(vo.getRedis_key(), vo.getRedis_value());
			} else if (type.equals("hash")) {
				jedis.hset(vo.getRedis_key(), vo.getRedis_field(),
						vo.getRedis_value());
			} else if (type.equals("list")) {
				jedis.lset(vo.getRedis_key(), vo.getRedis_index(),
						vo.getRedis_value());
			} else if (type.equals("set")) {

			} else if (type.equals("zset")) {

			}

		} catch (Exception e) {
			throw new CIPServiceException(e);
		}

	}

	/**
	 * 添加数据
	 */
	@Override
	public void addData(CIP_admin_redis_valVO vo, CIPRuntimeOperator operateInf) {
		Jedis jedis = null;
		try {
			jedis = CIPRedisUtils.getJedisResource();
			String type = vo.getRedis_dtype();
			if (type.equals("string")) {
				jedis.set(vo.getRedis_key(), vo.getRedis_value());
			} else if (type.equals("hash")) {
				jedis.hset(vo.getRedis_key(), vo.getRedis_field(),
						vo.getRedis_value());
			} else if (type.equals("list")) {
				jedis.lpush(vo.getRedis_key(), vo.getRedis_value());
			} else if (type.equals("set")) {
				jedis.sadd(vo.getRedis_key(), vo.getRedis_value().split(","));
			} else if (type.equals("zset")) {
				jedis.zadd(vo.getRedis_key(), vo.getRedis_score(),
						vo.getRedis_value());
			}

		} catch (Exception e) {
			throw new CIPServiceException(e);
		}
	}

	/**
	 * 删除数据
	 */
	@Override
	public void deleteData(String key, String param, CIPRuntimeOperator operateInf) {
		Jedis jedis = null;
		try {
			jedis = CIPRedisUtils.getJedisResource();
			String type = jedis.type(key);
			if (type.equals("none")) {
				return;
			}
			
			if (type.equals("string")) {
				jedis.del(key);
			} else if (type.equals("hash")) {
				jedis.hdel(key, param);
			} else if (type.equals("list")) {
				long index = Long.parseLong(param);
				String value = jedis.lindex(key, index);
				jedis.lrem(key, index, value);
			} else if (type.equals("set")) {
				jedis.srem(key, param);
			} else if (type.equals("zset")) {
				jedis.zrem(key, param);
			}

		} catch (Exception e) {
			throw new CIPServiceException(e);
		}
	}

	/**
	 * 获取数据
	 */
	@Override
	public CIP_admin_redis_valVO getData(String key, String param) {
		Jedis jedis = null;
		try {
			jedis = CIPRedisUtils.getJedisResource();
			String type = jedis.type(key);
			if (type.equals("none")) {
				return null;
			}
			CIP_admin_redis_valVO vo = new CIP_admin_redis_valVO();
			vo.setRedis_dtype(type);
			vo.setRedis_key(key);

			if (type.equals("string")) {
				vo.setRedis_value(jedis.get(key));
			} else if (type.equals("hash")) {
				String value = jedis.hget(key, param);
				vo.setRedis_field(param);
				vo.setRedis_value(value);
			} else if (type.equals("list")) {
				int index = Integer.parseInt(param);
				String value = jedis.lindex(key, index);
				vo.setRedis_index(index);
				vo.setRedis_value(value);

			} else if (type.equals("set")) {
				
				vo.setRedis_value(param);

			} else if (type.equals("zset")) {
				vo.setRedis_value(param);
				
				vo.setRedis_score(jedis.zscore(key, param));

			}

			return vo;
		} catch (Exception e) {
			throw new CIPServiceException(e);
		}

	}

	/**
	 * 检索数据
	 */
	@Override
	public List<CIP_admin_redis_valData> searchData(CIPPageInfo page,
			CIPReqCondition[] conditions) {
		Jedis jedis = null;
		try {
			if(conditions==null||conditions.length==0)
				return null;
			
			jedis = CIPRedisUtils.getJedisResource();
			String key = conditions[0].getLowValue();
			
			String type = jedis.type(key);
			List<CIP_admin_redis_valData> datas = new ArrayList<CIP_admin_redis_valData>();

			if (type.equals("none")) {
				return null;
			}
			if (type.equals("string")) {
				CIP_admin_redis_valData vo = new CIP_admin_redis_valData();
				vo.setRedis_dtype(type);
				vo.setRedis_key(key);
				vo.setRedis_value(jedis.get(key));
				datas.add(vo);
			} else if (type.equals("hash")) {
				Map<String, String> values = jedis.hgetAll(key);
				Set<String> keys = values.keySet();
				CIP_admin_redis_valData vo;
				for (String k : keys) {
					vo = new CIP_admin_redis_valData();
					vo.setRedis_dtype(type);
					vo.setRedis_key(key);
					vo.setRedis_field(k);
					vo.setRedis_value(values.get(k));
					datas.add(vo);
				}
			} else if (type.equals("list")) {
				List<String> values = jedis.lrange(key, 0, -1);
				CIP_admin_redis_valData vo;
				int index=0;
				for (String v : values) {
					vo = new CIP_admin_redis_valData();
					vo.setRedis_dtype(type);
					vo.setRedis_key(key);
					vo.setRedis_index(index++);
					vo.setRedis_value(v);
					datas.add(vo);
				}
			} else if (type.equals("set")) {
				Set<String> values = jedis.smembers(key);
				CIP_admin_redis_valData vo;
				for (String v : values) {
					vo = new CIP_admin_redis_valData();
					vo.setRedis_dtype(type);
					vo.setRedis_key(key);
					vo.setRedis_value(v);
					datas.add(vo);
				}

			} else if (type.equals("zset")) {
				Set<String> values = jedis.zrange(key, 0, -1);
				CIP_admin_redis_valData vo;
				for (String v : values) {
					vo = new CIP_admin_redis_valData();
					vo.setRedis_dtype(type);
					vo.setRedis_key(key);
					vo.setRedis_value(v);
					vo.setRedis_score(jedis.zscore(key, v));
					datas.add(vo);
				}

			}

			return datas;
		} catch (Exception e) {
			throw new CIPServiceException(e);
		}

	}

	public List<Map<String, Object>> exportEntities(CIPPageInfo page,
			CIPReqCondition[] conditions, CIPRuntimeOperator operateInf,
			boolean xFirst) {
		// if(xFirst){
		// CIP_admin_op_logPO log = new CIP_admin_op_logPO();
		// log.setOp_seq_no(System.currentTimeMillis());
		// log.setOp_table_id("cip_admin_redis_val");
		// log.setOp_obj_id("");
		// log.setOp_type("E");
		// log.setRemark("批量导出数据");
		// log.setOperator(operateInf.getSubject_id());
		// log.setCreate_time(Timestamp.valueOf(operateInf.getOperate_tm()));
		// opLogDao.add(log);
		// }
		// return dataDao.exportEntities(page, conditions, xFirst);
		return null;
	}
}