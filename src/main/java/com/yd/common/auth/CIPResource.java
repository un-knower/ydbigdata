package com.yd.common.auth;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.yd.common.function.meta.data.constant.CIPMetaConstants;
import com.yd.common.utils.DateUtils;

public class CIPResource {
	
	public String group="";
	public String type;
	public int level;
	public String id;
	public String url="";
	public String name;
	public String icon;
	public List<CIPResource> menus;
	public boolean xNotOutput = true;
	public String dbType;
	
	@Override
	public String toString() {
		if(xNotOutput)
			return null;
		
		String datetime = getDBTime();
		if(icon==null){
			icon="";
		}
		String str = "delete from cip_admin_resource where resource_id='"+id.trim()+"';\n";
		str += "insert into cip_admin_resource values('" + id.trim() + "','" + name.trim() + "','','"+url.trim()+"','"+type+"',"+datetime+", "+datetime+", 'admin','"+icon+"');";

		
		return  str;
	}
	
	public List<String> getResource2SubResource(){
		if(xNotOutput)
			return null;
		
		if( menus == null)
			return null;
		else {
			List<String> r2r= new ArrayList<String>();
			String datetime = getDBTime();
			String str;
			int index = 1;
			for( CIPResource menu:menus ){
				str = "delete from cip_admin_resource_2_resource where root_node_id='default' and res_node_id='"+menu.id.trim()+"' and res_node_sup='"+ menu.group.trim() + "';\n";
				str += "insert into cip_admin_resource_2_resource values('default','" + menu.id.trim() + "','" + menu.group.trim() + "',"+index+",0,"+ menu.level +",0,"+datetime+", "+datetime+", 'admin');";
				r2r.add(str);
			}
			return r2r;
		}
	}
	public String getSupResource2Resource(int index){
		if(xNotOutput)
			return null;
		
		String datetime = getDBTime();
		String str = "delete from cip_admin_resource_2_resource where root_node_id='default' and res_node_id='"+id.trim()+"' and res_node_sup='"+ group.trim() + "';\n";
		
		str += "insert into cip_admin_resource_2_resource values('default','" + id.trim() + "','" + group.trim() + "',"+index+","+(level==0? 1:0)+","+ level +",0,"+datetime+", "+datetime+", 'admin');";
		
		return str;
	}
	
	
	public String getDBTime(){
		String datetime = DateUtils.getDateTime(new Date());
		if(CIPMetaConstants.DB_ORACLE.equalsIgnoreCase(dbType)){
			datetime = "TO_TIMESTAMP('" + datetime + "', 'YYYY-MM-DD HH24:MI:SS')";
		}else if(CIPMetaConstants.DB_MYSQL.equalsIgnoreCase(dbType)){
			datetime = "'" + datetime + "'";
		}else{
			datetime = "''";
		}
		return datetime;
	}
	
	
}
