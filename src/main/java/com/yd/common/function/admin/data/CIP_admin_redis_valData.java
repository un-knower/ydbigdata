package com.yd.common.function.admin.data;


/**
 * <p>Data实体类</p>
 * <p>View: cip_admin_redis_val - Redis数据明细</p>
 * 用于检索数据，建立在视图基础上
 * @since 2015-08-24 09:34:48
 */
public class CIP_admin_redis_valData {


    /** 
    * redis_key - 数据key 
    */
	private String redis_key;

    /** 
    * redis_dtype - 数据类型 
    */
	private String redis_dtype;

    /** 
    * redis_value - 数据值 
    */
	private String redis_value;

    /** 
    * redis_index - 数据Index 
    */
	private int redis_index;

    /** 
    * redis_hash - 数据Hash 
    */
	private String redis_hash;

    /** 
    * redis_score - 数据score 
    */
	private double redis_score;

    private String redis_field;
    
	public String getRedis_field() {
		return redis_field;
	}
	public void setRedis_field(String redis_field) {
		this.redis_field = redis_field;
	}

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
	public String getRedis_value(){
        return this.redis_value;
    }
    
    public void setRedis_value(String redis_value){
        this.redis_value = redis_value;
    }
	public int getRedis_index(){
        return this.redis_index;
    }
    
    public void setRedis_index(int redis_index){
        this.redis_index = redis_index;
    }
	public String getRedis_hash(){
        return this.redis_hash;
    }
    
    public void setRedis_hash(String redis_hash){
        this.redis_hash = redis_hash;
    }
	public double getRedis_score(){
        return this.redis_score;
    }
    
    public void setRedis_score(double redis_score){
        this.redis_score = redis_score;
    }

}