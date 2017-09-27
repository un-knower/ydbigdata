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
		var appId = localStorage.getItem("appId");
		var objectName = '${structure.str_name}';
		var listTemplate = '#${structure.str_id}_list';
		var listId = "${structure.str_id}_list";
		var formSearchTemplate = '#${structure.str_id}_searchForm';
		var searchFormId = "${structure.str_id}_searchForm";
		var gridMenuId = '#${structure.str_id}_list_menu';
		var addFormUrl = '${structure.str_module}/${structure.str_id}_form.html?actionId=${structure.str_id}_form&mode=add';
		var updateFormUrl = '${structure.str_module}/${structure.str_id}_form.html?actionId=${structure.str_id}_form&mode=edit';
		var getFormUrl = '${structure.str_module}/${structure.str_id}_form.html?actionId=${structure.str_id}_form&mode=display';
		var urlSearch = appId + '/actions/${structure.func_set_id}/searchData.do?actionId=${structure.str_id}_searchData';
		var urlExport= appId + '/actions/${structure.func_set_id}/exportEntities.do?actionId=${structure.str_id}_exportEntities';
		var ddicUrl = appId + '/actions/common/domain/getDomains.do?actionId=system_getDomains&domainId=';
		var pageId = '${structure.str_id}_list';
		
		var urlResource = appId + "/actions/common/user/getResources.do?actionId=system_getResources&resource_id=";
		var urlActionForm = ""; //action form url
		var authActions = {}; //当前用户授权的行为项目
		var expellActions = {
			"${structure.str_id}_searchData":urlSearch,
			"${structure.str_id}_exportEntities":urlExport
		}; //当前用户需要特殊处理的按钮，不在menubar上显示的 
		var isDatagridLoaded = false;
		var today = getToday();
		$(function(){
			//loadCompanyCombobox();
			//loadCombobox();
			getAuthButtons(pageId,gridMenuId,authActions,expellActions);
			loadDatagrid();
		});
		
		function doAction(pageId,buttonId,actionUrl){
			if(authActions.hasOwnProperty(buttonId)){
				if(buttonId.indexOf('addData')>0){
					addData(buttonId,appId+actionUrl);
				}
				else if(buttonId.indexOf('updateData')>0){
					updateData(buttonId,appId+actionUrl);
				}
				else if(buttonId.indexOf('deleteData')>0){
					deleteData(buttonId,appId+actionUrl);
				}
				else if( buttonId.indexOf('getData')>0){
					getData(buttonId,appId+actionUrl);
				}
				else if(buttonId.indexOf('searchData')>0){
					searchData(buttonId,appId+actionUrl);
				}
				else if(buttonId.indexOf('refresh')>0){
					refresh(buttonId,appId+actionUrl);
				}
				else {
					//默认操作
					var selectRow = $$.getSingleSelectRow(listId, "请选择你要操作的"+objectName);

			    	if(selectRow){
						var tempUrl = '';
		   				<#list keyFields as field>
   						tempUrl = tempUrl+'&${field.field_id}='+selectRow.${field.field_id};
						</#list>
		   				tempUrl += "&actionUrl="+appId+actionUrl+"&tableId="+listId+"&refActionId="+buttonId;
			    		$$.openJcdfDialog(urlActionForm+tempUrl, '操作'+objectName, 500, 600);
			    	}					
				}
			}
			else {
				alert('当前用户没有该操作授权!');
			}
		};
		
		function refresh(buttonId,actionUrl){
			$.ajax({
				   type: "POST",
				   url: actionUrl+"?actionId="+buttonId,
				   dataType:"json",
				   success: function(data){
				   	 	$$.closeProcessingDialog();
						if (data && data.errorCode == 0) {
							$$.showJcdfMessager('提示消息', '操作成功', 'info');
						} else {
							$$.showJcdfMessager('提示消息', data.msg, 'warning');
						}
				   }
			});
		};
		
		function searchData(buttonId,actionUrl){
			var params = $$.serializeToJson(formSearchTemplate);
			var json = { "search_condition" : JSON.stringify(params) };
			if(isDatagridLoaded){
				$(listTemplate).datagrid('load', json );
			}else{
				$(listTemplate).datagrid({
					queryParams: json,
					url : urlSearch
				});
				isDatagridLoaded = true;
			}	
		};
		
		function exportExcel(buttonId,actionUrl){
			var params = $$.serializeToJson(formSearchTemplate);
			$('#'+searchFormId).form('submit',{    
			    url:actionUrl,    
			    onSubmit: function(param){
			    	param.search_condition = JSON.stringify(params)
			    },
			    success: function(data){   
			    	var oData = JSON.parse(data);
			        if (oData&&oData.errorCode){   
			        	$$.showJcdfMessager('提示消息', oData.msg, 'warning');   
			        }    
			    } 
			}); 		
		};
		
		function searchReset(){
			$(formSearchTemplate).form('reset');
		};
		
		function addData(buttonId,actionUrl){
	   		$$.openJcdfDialog(addFormUrl+"&actionUrl="+actionUrl+"&tableId="+listId+"&refActionId="+buttonId, '新增'+objectName, 500, 600);
		};
		
		function getData(buttonId,actionUrl){
			var selectRow = $$.getSingleSelectRow(listId, "请选择你要查看的"+objectName);

	    	if(selectRow){
				var tempUrl = '';
				<#list keyFields as field>
   				tempUrl = tempUrl+'&${field.field_id}='+selectRow.${field.field_id};
				</#list>
   				tempUrl += "&actionUrl="+actionUrl+"&tableId="+listId+"&refActionId="+buttonId;
	    		$$.openJcdfDialog(getFormUrl+tempUrl, '查看'+objectName, 500, 600);
	    	}
		};
		
		function deleteData(buttonId,actionUrl){
			var selectRow = $$.getSingleSelectRow(listId, "请选择你要删除的"+objectName);
	    	if(selectRow){
	    		$$.showJcdfConfirm("确认", "确定删除所选的"+objectName+"?", "deleteNodeById('"+buttonId+"','"+actionUrl+"','"+<#list keyFields as key>selectRow.${key.field_id}<#if key_has_next>+"','"+</#if></#list>+"')");
	    		
	    	}
		};
		
		function deleteNodeById(buttonId,actionUrl,<#list keyFields as key>${key.field_id}<#if key_has_next>+"','"+</#if></#list>){
			$$.openProcessingDialog();
			var jsonData = {
						<#list keyFields as key>
   						"${key.field_id}":${key.field_id}<#if key_has_next>,</#if>
						</#list>
						};
			$.ajax({
			   type: "POST",
			   url: actionUrl+"?actionId="+buttonId,
			   dataType:"json",
			   data : JSON.stringify(jsonData),
			   contentType: "application/json",
			   success: function(data){
			   	 	$$.closeProcessingDialog();
					if (data && data.errorCode == 0) {
						$$.showJcdfMessager('提示消息', '操作成功', 'info');
						$$.refreshJcdfDatagrid(listId);
					} else {
						$$.showJcdfMessager('提示消息', data.msg, 'warning');
					}
			   }
			});
		};
		
		function updateData(buttonId,actionUrl){
	    	var selectRow = $$.getSingleSelectRow(listId, "请选择你要编辑的"+objectName);

	    	if(selectRow){
				var tempUrl = '';
				<#list keyFields as field>
   				tempUrl = tempUrl+'&${field.field_id}='+selectRow.${field.field_id};
				</#list>
   				tempUrl += "&actionUrl="+actionUrl+"&tableId="+listId+"&refActionId="+buttonId;
	    		$$.openJcdfDialog(updateFormUrl+tempUrl, '编辑'+objectName, 500, 600);
	    	}
		};
	
				
		/**
		 * 页面加载时初始化datagrid列表并加载列表数据进行显示
		 */
		function loadDatagrid() {
			$(listTemplate).datagrid({
				title:objectName+'列表',
				height:$$.getDatagridHeight(),
				width:$$.getDatagridWidth(),
				border: true,
				nowrap: true,
				striped: true,
				fitColumns: true,
				idField:'',
				columns:[[
				<#list fields as field>
					{field:'${field.field_id}',title:'${field.field_name}',width:$$.fillsize(0.1),align:'center'},
				</#list>
				<#list txtFields as txtColumn>
					{field:'${txtColumn.txtField}',title:'${txtColumn.txtFieldName}',width:$$.fillsize(0.1),align:'center'},
				</#list>
				]],
				onBeforeLoad:function(){$$.clearSelect(listId);},
				queryParams:$$.serializeToJson(searchFormId),
				pagination:true,
				rownumbers:true,
				singleSelect:true,
				pageSize : $$.pageSize,
				pageList : $$.pageList,
				toolbar: gridMenuId,
				loadFilter: function(data){
					if (data!=null && data.errorCode>0){
						if(data.msg!=null && data.msg!=""){
							$$.showJcdfMessager('提示消息', data.msg, 'warning');
						}
					}
					return data; 
				}
			});
		}
	</script>
</head> 
<body class="easyui-layout">
	<div data-options="region:'north', split:false, border:false" style="padding: 4px 0;">
		<fieldset style="padding: 5px; border: 1px solid #d4a375;">
			<legend>信息查询</legend>
			<form id="${structure.str_id}_searchForm" class="search-form" method="post">
				<table cellpadding="2" style="width: 100%">
					<tr>
					<#list fields as field><#if field.search_flag == 1>
						<td align="right">${field.field_name}：</td>
						<td>
							<${field.ui_control} type="text" id="${field.field_id}" name="${field.field_id}" style="width:70%;" class="${field.ui_ctrl_clazz}"
							data-options="<#if field.ui_ctrl_clazz == "easyui-combobox">onShowPanel:function (){loadCombobox('${field.field_id}','${field.field_id}')}</#if>" 
							<#if field.ui_control == "textarea"> style="height:100px;"></textarea> <#else> /> </#if>
						
						</td>
					</#if></#list>
					<tr>
						<td colspan="8" align="center" >
							<a class="easyui-linkbutton" iconCls="icon-search" style="margin-right: 20px;" onclick="searchData('${structure.str_id}_searchData',urlSearch)">查询</a>
							<a class="easyui-linkbutton" iconCls="icon-reload" style="margin-right: 20px;" onclick="searchReset()">重置</a>
							<a class="easyui-linkbutton" iconCls="icon-download" style="margin-right: 20px;" onclick="exportExcel('${structure.str_id}_exportEntities',urlExport)">导出</a>
						</td>
					</tr>
				</table>
			</form>
		</fieldset>
	</div>
	<div data-options="region:'center', split:false, border:false" style="padding: 0 2px;">
		<div id="${structure.str_id}_list_menu">
			<div style="padding: 5px;">
		<!--		<a id="button_add" href="#" onclick="addData()" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
				<a id="button_edit" href="#" onclick="editData()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">编辑</a>
				<a id="button_delete" href="#" onclick="delateData()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a> -->
			</div>
		</div>
		<table id="${structure.str_id}_list" fit="true"></table>
	</div>
</body>
</html>