package com.yd.common.cache;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import com.yd.common.conf.CIPConfigureManager;

/**
 * Redis工具类
 * @author Rong.zhu
 *
 */
public class CIPRedisUtils {
	private static Logger log = Logger.getLogger(CIPRedisUtils.class);
	
	private static JedisPool jedisPool = null; //Jedis连接池
	private static JedisPoolConfig poolConfig = null;//Jedis连接池配置
	
	private static String redisHost;//主机
	private static int redisPort;//端口
	
	public static int expireTime;//key过期时间(过期后被自动删除)
	
	/**
	 * 获取redis连接
	 * @return
	 * @throws Exception
	 */
	public static Jedis getJedisResource() throws Exception {
		if(poolConfig == null) {
			loadConfig();
		}
		
		if(jedisPool == null) {
			jedisPool = new JedisPool(poolConfig, redisHost, redisPort);
		}
		try{
			return jedisPool.getResource();
		}catch(Exception e) {
			jedisPool = null;
			throw new Exception("Redis服务["+redisHost+":"+redisPort+"]无法正常使用!");
		}
		
	}
	
	/**
	 * 释放redis连接
	 * @param jedis
	 * @param isJedisConnectExeptionOccured
	 */
	public static void returnJedisResource(Jedis jedis, boolean isJedisConnectExeptionOccured) {
		if(jedis != null) {
			if(isJedisConnectExeptionOccured) {
				//如果jedis连接出错必须调用此方法释放jedis到池中（释放之前清空缓冲区,否则可能出现脏数据）
				jedisPool.returnBrokenResource(jedis);
			}else {
				jedisPool.returnResource(jedis);
			}
		}
		
	}
	
	/**
	 * 重置redis连接池配置
	 */
	public static void resetJedisPool() {
		if(jedisPool != null)
			jedisPool.destroy();
		jedisPool = null;
		poolConfig= null;
	}
	
	private static void loadConfig() {
		//加载配置文件
		InputStream stream = null;
		try {
			log.info("开始加载配置文件redis.properties...");
			Properties rootProps = CIPConfigureManager.getRootConf();
			String confFile = rootProps.getProperty("cip.conf.redis.file").trim();
			Properties props = CIPConfigureManager.getConfigures(confFile);
			
			redisHost = props.getProperty("redis.host").trim();
			redisPort = Integer.parseInt(props.getProperty("redis.port").trim());
			
			poolConfig = new JedisPoolConfig();
			poolConfig.setMaxIdle(Integer.parseInt(props.getProperty("redis.pool.maxIdle").trim()));
			poolConfig.setMaxTotal(Integer.parseInt(props.getProperty("redis.pool.maxActive").trim()));
			poolConfig.setMaxWaitMillis(Long.parseLong(props.getProperty("redis.pool.maxWait").trim()));
			poolConfig.setMinEvictableIdleTimeMillis(Long.parseLong(props.getProperty("redis.pool.minEvictableIdleTimeMillis").trim()));
			poolConfig.setTimeBetweenEvictionRunsMillis(Long.parseLong(props.getProperty("redis.pool.timeBetweenEvictableRunsMillis").trim()));
			poolConfig.setTestOnBorrow(Boolean.parseBoolean(props.getProperty("redis.pool.testOnBorrow").trim()));
			poolConfig.setTestOnReturn(Boolean.parseBoolean(props.getProperty("redis.pool.testOnReturn").trim()));
			poolConfig.setTestWhileIdle(Boolean.parseBoolean(props.getProperty("redis.pool.testWhileIdle").trim()));
			
			expireTime = Integer.parseInt(props.getProperty("redis.expire.seconds").trim());
					
			log.info("配置文件redis.properties加载成功！");
		} catch (Exception e) {
			log.info("配置文件redis.properties加载失败！");
			log.error(e.getMessage());
		} finally{
			if(stream != null) {
				try {
					stream.close();
				} catch (IOException e) {
					log.error(e.getMessage());
				}
				stream = null;
			}
		}
	}
}
