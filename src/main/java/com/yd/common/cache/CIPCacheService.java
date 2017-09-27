package com.yd.common.cache;



public interface CIPCacheService {
	
	public boolean exists(String key) ;
	
	public <T>T get(String key, Class<T> clazz) ;
	
	public void set(String key, Object data, boolean setExpireTime) ;
	
	public <T>T hashget(String key, String field, Class<T> clazz) ;
	
	public void hashset(String key, String field, Object data, boolean setExpireTime) ;
	
	public void remove(String key) ;
	
	public void clear() ;
	
	public void resetExpireTime(String key);
	
}	
