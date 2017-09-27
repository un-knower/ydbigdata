<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="../../js/jquery-easyui/themes/orange/easyui.css"/>
	<link rel="stylesheet" type="text/css" href="../../js/jquery-easyui/themes/icon.css"/>
	<link rel="stylesheet" type="text/css" href="../../css/common.css"/>
	<script type="text/javascript" src="../../js/jquery-easyui/jquery.min.js"></script>
	<script type="text/javascript" src="../../js/jquery-easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="../../js/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="../../js/validate.js"></script>
	<script type="text/javascript" src="../../js/jcdf-1.0.js"></script>
	<script type="text/javascript">
	<#list keyFields as field>
	var id_${field.field_id}="";
	</#list>

	var appId = localStorage.getItem("appId");
	var pageId = '${structure.str_id}_form';
	var actionUrl;
	var actionId;
	var tableId;
	var mode = "";
	var callUrl;
	var getUrl = appId + "/actions/${structure.func_set_id}/getData.do?actionId=${structure.str_id}_getData";
	var formTemplate = "#form_${structure.str_id}";
	var listId = "${structure.str_id}_list";
	var ddicUrl = appId + '/actions/common/domain/getDomains.do?actionId=system_getDomains&domainId=';
	var editHistoryData = null;
	
	var urlResource = appId + "/actions/common/user/getResources.do?actionId=system_getResources&resource_id=";
	var urlActionForm = ""; //action form url
	var authActions = {}; //当前用户授权的行为项目
	var expellActions = {};
	
	
	$(function(){
		init();
		//保存按钮单击事件
		$("#btn_save").bind("click", function() {
			submitData();
		});
		//取消按钮单击事件
		$("#btn_cancel").bind("click", function(){
			$$.closeJcdfDialog();
		});
	});

	function init(){
		<#list keyFields as field>
		id_${field.field_id}=$$.QueryString("${field.field_id}");
		</#list>
		
		actionUrl = $$.QueryString("actionUrl");
		tableId = $$.QueryString("tableId");
		mode = $$.QueryString("mode");
		actionId = $$.QueryString("refActionId");
		<#if structure.str_id?ends_with("_dm")>
		<#else>
		<#assign line="\n">
		<#list fields as field><#if field.field_use_type == 1>loadCombobox('${field.field_id}','${field.field_id}');${line}</#if></#list>
		</#if>
		callUrl = actionUrl+"?actionId="+actionId;
		if(mode=="edit"){
			loadEditData();
			<#list keyFields as key>
			$('#${key.field_id}').attr({editable:false});
			</#list>
		}else if(mode=="display"){
			loadEditData();
			$('#btn_save').attr({ "disabled": "disabled" });
		}
		else if( mode == "add" ){
			<#if structure.str_id?ends_with("_dm")>
			<#else>
			<#list keyFields as key><#if key.field_use_type != 8><#if key.field_use_type == 1> $('#${key.field_id}').combobox('setValue', '${key.field_default}');${line}<#elseif key.field_use_type == 4> $('#${key.field_id}').datebox('setValue', getDate());${line}<#elseif key.field_use_type == 5> $('#${key.field_id}').datetimebox('setValue', getTimeStamp());${line}<#else>$('#${key.field_id}').val('${key.field_default}');${line}</#if></#if></#list>
			</#if>
			<#list fields as field>
			<#if field.field_use_type == 8>
			<#else>
			<#if field.field_use_type == 1> 
			$('#${field.field_id}').combobox('setValue', '${field.field_default}');
			<#elseif field.field_use_type == 4> 
			$('#${field.field_id}').datebox('setValue', getDate());
			<#elseif field.field_use_type == 5> 
			$('#${field.field_id}').datetimebox('setValue', getTimeStamp());
			<#else>$('#${field.field_id}').val('${field.field_default}');
			</#if>
			</#if>
			</#list>
		}

	};
	
	
	/**
	 * 加载历史数据，用于修改
	 */
	function loadEditData(){
		$$.openProcessingDialog();
		var jsonData = {
		<#list keyFields as field>
		"${field.field_id}":id_${field.field_id}<#if field_has_next>,</#if>
		</#list>
		};
		$.ajax({
		   type: "POST",
		   url: getUrl,
		   dataType:"json",
		   contentType: "application/json",
		   data: JSON.stringify(jsonData),
		   success: function(oData){
		   	 	$$.closeProcessingDialog();
				if (oData!=null && oData.errorCode == 0) {
					editHistoryData = oData.data;
					//填充修改记录的历史数据
					$(formTemplate).form('load', editHistoryData);					
				} else {
		 			$$.showJcdfMessager('提示消息',oData.msg,'warning');
				}
		   }
		});
	}
	
	function submitData(){
		if (!$(formTemplate).form('validate')) {
			return false;
		}
		var jsonData = $$.serializeToJson(formTemplate);
		if(!jsonData) return false;
		//如果数据验证通过(即数据合法)
		$$.openProcessingDialog();
	
		//ajax提交数据
		$.ajax({
			type : "POST",
			url : callUrl,
			dataType : "json",
			data : JSON.stringify(jsonData),
			contentType: "application/json",
			success : function(data) {
				$$.closeProcessingDialog();
				if (data && data.errorCode==0) {
					$$.showJcdfMessager('提示消息', '操作成功', 'info');
					$$.closeJcdfDialog();
					$$.refreshJcdfDatagrid(tableId);
				} else {
					$$.showJcdfMessager('提示消息', data.msg+'('+data.errorCode+')', 'warning');
				}
			}
		});
	};
	</script>
</head>
<body>
	<form id="form_${structure.str_id}" class="form">
		<table>
		<#list fields as field><#if field.key_flag == 1>
			<tr>
				<td class="right">
					<label for="${field.field_id}">${field.field_name}:</label>
				</td>
				<td class="left">
					<${field.ui_control} type="text" id="${field.field_id}" name="${field.field_id}" style="width:70%;" class="${field.ui_ctrl_clazz}" 
						data-options="required:true,validType:'length[0,${field.ui_length}]'" /> 
				</td>
			</tr>
		<#else><#if field.field_use_type != 8>
			<tr>
				<td class="right">
					<label for="${field.field_id}">${field.field_name}:</label>
				</td>	
				<td class="left">
					<${field.ui_control} type="text" id="${field.field_id}" name="${field.field_id}" style="width:70%;" class="${field.ui_ctrl_clazz}" 
						data-options="required:<#if field.null_flag == 1 >true<#else>false</#if>,validType:'length[0,${field.ui_length}]'" <#if field.ui_control == "textarea"> style="height:100px;"></textarea> <#else> /> </#if>
				</td>
			</tr>
		</#if></#if></#list>
			<tr>
				<td colspan="2" align="center" style="padding: 20px;">
					<a id="btn_save" href="#" class="easyui-linkbutton" iconCls="icon-ok">确定</a>
					<a id="btn_cancel" href="#" class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>