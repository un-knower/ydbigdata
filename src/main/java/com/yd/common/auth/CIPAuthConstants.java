package com.yd.common.auth;

public class CIPAuthConstants {

	public final static String AUTH_OBJ_ID = "obj_id";
	public final static String AUTH_RESOURCE_ID = "resource_id";
	public final static String AUTH_ROOT_NODE_ID = "root_node_id";
	
	public final static String C_AUTH_OPRATE_TYPE_EXPORT_EXCEL = "E";//导出Excel
	public final static String C_AUTH_OPRATE_TYPE_IMPORT_EXCEL = "F";//导入Excel
	public final static String C_AUTH_OPRATE_TYPE_ADD = "I";//新增
	public final static String C_AUTH_OPRATE_TYPE_DELETE = "D";//删除
	
	public final static String C_AUTH_OPRATE_TYPE_LOCK = "L";//锁定
	public final static String C_AUTH_OPRATE_TYPE_UPDATE = "M";//修改
	public final static String C_AUTH_OPRATE_TYPE_QUERY = "Q";//查看
	public final static String C_AUTH_OPRATE_TYPE_AUDIT_REJECT = "R";//审核回退
	public final static String C_AUTH_OPRATE_TYPE_SEARCH = "S";//查询
	public final static String C_AUTH_OPRATE_TYPE_UNLOCK = "U";//解锁
	public final static String C_AUTH_OPRATE_TYPE_AUDIT_APPROVE = "A";//审核通过

	
	public final static String C_AUTH_ATTR_SRC_CONF = "C"; //配置
	public final static String C_AUTH_ATTR_SRC_ROLE = "R"; //角色
	public final static String C_AUTH_ATTR_SRC_USER = "U"; //用户
	public final static String C_AUTH_ATTR_SRC_USER_SETTING = "S"; //用户配置
	
	
	public final static String C_AUTH_INCL_SUB_FLAG_SELF = "S"; //自己
	public final static String C_AUTH_INCL_SUB_FLAG_DIRECT = "D"; //直接下级
	public final static String C_AUTH_INCL_SUB_FLAG_ALL = "A"; //所有下级
	
}
