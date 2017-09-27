package com.yd.common.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.yd.common.data.CIPReqCondition;
import com.yd.common.runtime.CIPRuntime;

public class CIPUtil {

	public static CIPReqCondition[] getReqCondition(String conditonStr, String actionId, String authObjectId){
		//用户特定的数据权限约束
		CIPReqCondition[] otherConds = CIPRuntime.authManager.getAuthConditions(actionId, authObjectId);
		int length = 0;
		if(otherConds != null){
			length = otherConds.length;
		}
		
		CIPReqCondition[] conditions = null;
		if(conditonStr!=null){
			Map reqCondtions = JSONUtils.convertJson2Object(conditonStr, HashMap.class);
			//CIPConfigureData0 data = JSONUtils.convertJson2Object(msg.data.toString(), CIPConfigureData0.class);
			Set<String> keys = reqCondtions.keySet();
			CIPReqCondition req = null;
			CIPReqCondition[] tempConditions = new CIPReqCondition[keys.size()];
			int i = 0;
			String value;
			for( String key : keys ){
				value = (String) reqCondtions.get(key);
				if(value==null || value.trim().equals(""))
					continue;
				req = new CIPReqCondition();
				req.setFieldName(key);
				req.setLowValue(value);
				tempConditions[i++] = req;
			}
			if( i > 0 ){
				conditions = new CIPReqCondition[i+length];
				System.arraycopy(tempConditions, 0, conditions, 0, i);
				if(length>0)
					System.arraycopy(otherConds, 0, conditions, i, length);
			} else {
				conditions = otherConds;
			}
		} else {
			conditions = otherConds;
		}
		return conditions;
	}
}
