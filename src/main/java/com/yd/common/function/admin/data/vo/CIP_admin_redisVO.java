package com.yd.common.function.admin.data.vo;


/**
 * <p>实体类</p>
 * <p>Table: cip_admin_redis - Redis数据概要</p>
 *
 * @since 2015-08-24 01:32:29
 */
public class CIP_admin_redisVO {

	public String getKeys(){
		return redis_key;
	}

    /** 
    * redis_key - 数据key 
    */
    private String redis_key;
    
    /** 
    * redis_dtype - 数据类型 
    */
    private String redis_dtype;
    
    /** 
    * redis_ttl - 超时设置 
    */
    private long redis_ttl;
    
    /** 
    * redis_encode - 编码方式 
    */
    private String redis_encode;
    
    /** 
    * redis_size - 数据个数 
    */
    private long redis_size;
    

	public String getRedis_key(){
        return this.redis_key;
    }
    public void setRedis_key(String redis_key){
        this.redis_key = redis_key;
    }
	public String getRedis_dtype(){
        return this.redis_dtype;
    }
    public void setRedis_dtype(String redis_dtype){
        this.redis_dtype = redis_dtype;
    }
	public long getRedis_ttl(){
        return this.redis_ttl;
    }
    public void setRedis_ttl(long redis_ttl){
        this.redis_ttl = redis_ttl;
    }
	public String getRedis_encode(){
        return this.redis_encode;
    }
    public void setRedis_encode(String redis_encode){
        this.redis_encode = redis_encode;
    }
	public long getRedis_size(){
        return this.redis_size;
    }
    public void setRedis_size(long redis_size){
        this.redis_size = redis_size;
    }

	
}