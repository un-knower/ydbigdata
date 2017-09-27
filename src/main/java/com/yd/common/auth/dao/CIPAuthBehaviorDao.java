package com.yd.common.auth.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface CIPAuthBehaviorDao {
	
	public boolean isSubExist(String tableId, String supFieldId, String subFieldId, String supFeildVal, String subFieldVal);
	
	public String getSupRelation(String tableId, String subFieldId, String subFieldVal, String supRelFieldId);
	
	public List<String> getDirectSubs(String tableId, String supFieldId, String subFieldId, String supFeildVal);
	
	public List<String> getAllSubs(String tableId, String supFieldId, String supFeildVal, String subFieldId, String supRelFieldId);
	
}
