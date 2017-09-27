package com.yd.ydbi.common.cache;

import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.ibatis.cache.Cache;
import org.apache.log4j.Logger;

import com.yd.common.cache.CIPRedisUtils;
import com.yd.ydbi.utils.SerializeUtil;

import redis.clients.jedis.Jedis;

/**
 * @author kangyuanjia
 * @createTime 2017-03-27 09:29:57
 * @describe Mybatis整合Redis缓存
 */
public class MyBatisRedisCache implements Cache {
	private Logger logger = Logger.getLogger(MyBatisRedisCache.class);
	private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
	private String id;
    private static final int DB_INDEX = 0;
    private final String COMMON_CACHE_KEY = "COM:";
    private static final String UTF8 = "utf-8";
    
    /**
     * 按照一定规则标识key
     */
    private String getKey(Object key) {
        StringBuilder accum = new StringBuilder();
        accum.append(COMMON_CACHE_KEY);
        accum.append(this.id).append(":");
        accum.append(DigestUtils.md5Hex(String.valueOf(key)));
        return accum.toString();
    }

    /**
     * redis key规则前缀
     */
    private String getKeys() {
        return COMMON_CACHE_KEY + this.id + ":*";
    }

	public MyBatisRedisCache(final String id) {
		if (id == null) {
			throw new IllegalArgumentException("必须传入ID");
		}
		logger.debug("MybatisRedisCache:id=" + id);
		this.id = id;
	}

	@Override
	public void clear() {
		Jedis jedis = null;
        boolean borrowOrOprSuccess = true;
        try {
            jedis = CIPRedisUtils.getJedisResource();
            jedis.select(DB_INDEX);
            // 如果有删除操作，会影响到整个表中的数据，因此要清空一个mapper的缓存（一个mapper的不同数据操作对应不同的key）
            Set<byte[]> keys = jedis.keys(getKeys().getBytes(UTF8));
            logger.debug("出现CUD操作，清空对应Mapper缓存======>" + keys.size());
            for (byte[] key : keys) {
                jedis.del(key);
            }
            // 下面是网上流传的方法，极大的降低系统性能，没起到加入缓存应有的作用，这是不可取的。
            // jedis.flushDB();
            // jedis.flushAll();
        } catch (Exception e) {
            borrowOrOprSuccess = false;
            if (jedis != null)
            	CIPRedisUtils.returnJedisResource(jedis,true);
        } finally {
            if (borrowOrOprSuccess)
            	CIPRedisUtils.returnJedisResource(jedis,false);
        }
	}

	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public Object getObject(Object key) {
		Jedis jedis = null;
        Object value = null;
        boolean borrowOrOprSuccess = true;
        try {
            jedis = CIPRedisUtils.getJedisResource();
            jedis.select(DB_INDEX);
            value = SerializeUtil.unserialize(jedis.get(getKey(key).getBytes(UTF8)));
            logger.debug("从缓存中获取-----" + this.id);
        } catch (Exception e) {
            borrowOrOprSuccess = false;
            if (jedis != null)
            	if (jedis != null)
            		CIPRedisUtils.returnJedisResource(jedis,true);
            } finally {
                if (borrowOrOprSuccess)
                	CIPRedisUtils.returnJedisResource(jedis,false);
        }
        return value;
	}

	@Override
	public ReadWriteLock getReadWriteLock() {
		return readWriteLock;
	}

	@Override
	public int getSize() {
		Jedis jedis = null;
        int result = 0;
        boolean borrowOrOprSuccess = true;
        try {
            jedis = CIPRedisUtils.getJedisResource();
            jedis.select(DB_INDEX);
            Set<byte[]> keys = jedis.keys(getKeys().getBytes(UTF8));
            if (null != keys && !keys.isEmpty()) {
                result = keys.size();
            }
            logger.debug(this.id + "---->>>>总缓存数:" + result);
        } catch (Exception e) {
            borrowOrOprSuccess = false;
            if (jedis != null)
            	CIPRedisUtils.returnJedisResource(jedis,true);
        } finally {
            if (borrowOrOprSuccess)
            	CIPRedisUtils.returnJedisResource(jedis,false);
        }
        return result;
	}

	@Override
	public void putObject(Object key, Object value) {
		Jedis jedis = null;
        boolean borrowOrOprSuccess = true;
        try {
            jedis = CIPRedisUtils.getJedisResource();
            jedis.select(DB_INDEX);

            byte[] keys = getKey(key).getBytes(UTF8);
            jedis.set(keys, SerializeUtil.serialize(value));
            if(CIPRedisUtils.expireTime >0) {//设置过期时间
				jedis.expire(keys, CIPRedisUtils.expireTime);
			}
            logger.debug("添加缓存--------" + this.id);
            // getSize();
        } catch (Exception e) {
            borrowOrOprSuccess = false;
            if (jedis != null)
            	CIPRedisUtils.returnJedisResource(jedis,true);
        } finally {
            if (borrowOrOprSuccess)
            	CIPRedisUtils.returnJedisResource(jedis,false);
        }

	}

	@Override
	public Object removeObject(Object key) {
		Jedis jedis = null;
        Object value = null;
        boolean borrowOrOprSuccess = true;
        try {
            jedis = CIPRedisUtils.getJedisResource();
            jedis.select(DB_INDEX);
            value = jedis.del(getKey(key).getBytes(UTF8));
            logger.debug("LRU算法从缓存中移除-----" + this.id);
            // getSize();
        } catch (Exception e) {
            borrowOrOprSuccess = false;
            if (jedis != null)
            	CIPRedisUtils.returnJedisResource(jedis,true);
        } finally {
            if (borrowOrOprSuccess)
            	CIPRedisUtils.returnJedisResource(jedis,false);
        }
        return value;
	}

}
