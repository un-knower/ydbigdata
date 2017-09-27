package com.yd.common.auth.behavior;

import java.util.Map;

import com.yd.common.function.admin.data.po.CIP_admin_auth_attrPO;

public class CIPAuthObject {
	private String objectId;
	private String objectName;
	private Map<String,String> attributes;
	private Map<String,CIP_admin_auth_attrPO> valueTables;
	
	public CIP_admin_auth_attrPO getValueTable(String attrId) {
		return valueTables.get(attrId);
	}

	public void setValueTables(Map<String, CIP_admin_auth_attrPO> valueTables) {
		this.valueTables = valueTables;
	}

	public CIPAuthObject(String objectId, String objectName){
		this.objectId = objectId;
		this.objectName = objectName;
	}

	public String getObjectId() {
		return objectId;
	}

	public Map<String, String> getAttributes() {
		return attributes;
	}

	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	public void setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
	}
	
	public CIPAuthLock getLock(){
		CIPAuthLock lock = new CIPAuthLock(objectId,attributes);
//		int size = this.attributes.size();
//		Map<String,String> attributes = new LinkedHashMap<String,String>(size);
//		Set<String> keys = this.attributes.keySet();
//		for( String key:keys){
//			attributes.put(key, null);
//		}

		return lock;
	}
	
	public CIPAuthKey getKey(){
		CIPAuthKey authKey = new CIPAuthKey(objectId,attributes);
		return authKey;		
	}
	
//	public CIPAuthKey getKey(String userId, String resourceId){
//		//user --> roles
//		
//		//role --> root_node_id
//		
//		//root_node_id --> authObject 
//		return null;
//	}
	

}
