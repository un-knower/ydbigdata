//获取系统当前时间
function getNowDate(mthNum,dayNum){
	var dtStr= '';
	$.ajax({
		   type: "POST",
		   url: appId+"/actions/tool/nowDate.do?actionId=tool_nowDate",
		   data: "mth="+mthNum+"&day="+dayNum,
		   async: false,
		   success: function(oData){
			   dtStr = oData.data;
		   }
	});
	return dtStr;
}

//获取两个日期间隔天数
function getDateDiff(startDate,endDate)  
{  
    var startTime = new Date(Date.parse(startDate.replace(/-/g,   "/"))).getTime();     
    var endTime = new Date(Date.parse(endDate.replace(/-/g,   "/"))).getTime();     
    var dates = Math.abs((startTime - endTime))/(1000*60*60*24);     
    return  dates;    
}

//对数字四舍五入保留两位小数
function feeFormatter(value, rec) {
	if(value != null){
   		return String(value.toFixed(2));
	}else{
		return value;
	}
}

//快件查询
function queryShipmentDtl(shipment_no){
	var shipmentNoMD5 = "";
	var	shipmentNoId = "";
	$.ajax({
		type : "POST",
		url: appId+"/actions/tool/shipmentNoMD5.do?actionId=tool_shipmentNoMD5",
		data: {"shipment_no":shipment_no},
		cache : false,
		async: false,
		dataType : "json",
		success : function(data) {
			if (null != data && data.errorCode==0) {
				shipmentNoMD5 = data.data.shipment_no_md5;
				shipmentNoId = data.data.shipment_no_id;
			}  else {
				$$.showJcdfMessager('提示消息', data.msg, 'warning'); 
			}
		}
	});
	var href = "http://n1cx.yundasys.com/nbsw/gox.php?wen="+shipment_no+"&jmm="+shipmentNoMD5+"&hh="+shipmentNoId;
	window.parent.openNewTab2('快件查询',href);
}

function authControl(){
	var userData = null;
	$.ajax({
		type : "POST",
		url : c_url_get_user_profile,
		cache : false,
		async: false,
		dataType : "json",
		success : function(data) {
			if (null != data && data.errorCode==0) {
				userData = data.data;
			} else {
				$$.showJcdfMessager('提示消息', data.msg, 'warning'); 
			}
		}
	});
	$.each( userData.user_roles, function(i, n){
             var roleVales = n.split("_");  
             if(roleVales[0] == "branch"){
            	 $('#prov').attr("disabled","disabled");
         		 $('#city').attr("disabled","disabled");
         		 $('#branch').attr("disabled","disabled");
         		 $('#orgCode').attr("disabled","disabled");
         		 $('#branch').val(userData.org_name);
         		 $('#orgCode').val(userData.org_id);
         		 return false;
             }else if(roleVales[0] == "dbct"){
            	 $("#dbct").val(roleVales[1])
            	 $('#prov').attr("disabled","disabled");
         		 $('#city').attr("disabled","disabled");
            	 $('#branch').combobox({
            		    url:appId+"/actions/tool/getRegion.do?actionId=tool_getRegion&sel_level=44&sel_cd=" + roleVales[1],    
            		    valueField:'id',    
            		    textField:'name',
            		    filter: function(q, row){
            				var opts = $(this).combobox('options');
            				return row[opts.textField].indexOf(q) >-1;
            			},
            			onSelect:function(rec){
            				$("#orgCode").val(rec.id);
            				$("#orgName").val(rec.name);
            		    },  
            		    loadFilter:function(oData){
            		    	var data = oData.data;
            		    	for(var i in data){
            		    		data[i].name = data[i].id + "(" + data[i].name + ")";
            		    	}
            		    	data.unshift({"id":"","name":"请选择"});
            		    	return data;
            		    }
            		});
            	 return false;
             }else if(roleVales[0] == "prov"){
            	 $('#prov').attr("disabled","disabled");
            	 $('#city').combobox({    
            		    url:appId+"/actions/tool/getRegion.do?actionId=tool_getRegion&sel_level=2&sel_cd=" + roleVales[1],      
            		    valueField:'id',    
            		    textField:'name',
            		    filter: function(q, row){
            				var opts = $(this).combobox('options');
            				return row[opts.textField].indexOf(q) > -1;
            			},
            			onSelect:function(rec){
            				$("#branch").combobox('clear');
            				var url = appId+"/actions/tool/getRegion.do?actionId=tool_getRegion&sel_level=24&sel_cd=" + rec.id;  
            				$("#branch").combobox('reload', url);
            		    },
            		    loadFilter:function(oData){
            		    	var data = oData.data;
            		    	data.unshift({"cityId":"","cityName":"请选择"});
            		    	return data;
            		    }
            		});
            		
            		$('#branch').combobox({
            		    url:'',    
            		    valueField:'id',    
            		    textField:'name',
            		    filter: function(q, row){
            				var opts = $(this).combobox('options');
            				return row[opts.textField].indexOf(q) >-1;
            			},
            			onSelect:function(rec){
            				$("#orgCode").val(rec.id);
            				$("#orgName").val(rec.name);
            		    },  
            		    loadFilter:function(oData){
            		    	var data = oData.data;
            		    	for(var i in data){
            		    		data[i].name = data[i].name + "(" + data[i].id + ")";
            		    	}
            		    	data.unshift({"id":"","name":"请选择"});
            		    	return data;
            		    }
            		});
            	 return false;
             }else if(roleVales[0] == "area"){
            	 $("#area").val(roleVales[1])
            	 $('#prov').combobox({    
					    url:appId+"/actions/tool/getRegion.do?actionId=tool_getRegion&sel_level=1&sel_cd=" + roleVales[1],        
					    valueField:'id',    
					    textField:'name',
					    filter: function(q, row){
							var opts = $(this).combobox('options');
							return row[opts.textField].indexOf(q) > -1;
						},
					    onSelect:function(rec){
					    	$("#city").combobox('clear');
					    	$("#branch").combobox('clear');
				    		var url = appId+"/actions/tool/getRegion.do?actionId=tool_getRegion&sel_level=2&sel_cd=" + rec.id;
					    	$("#city").combobox('reload', url);
					    	url = appId+"/actions/tool/getRegion.do?actionId=tool_getRegion&sel_level=24&sel_cd=";
					    	$("#branch").combobox('reload', url);
					    	$("#orgCode").val("");
					    	$("#provinceName").val(rec.name);
					    	$("#orgCode").blur();
					    },
					    loadFilter:function(oData){
					    	var data = oData.data;
					    	data.unshift({"id":"","name":"请选择"});
					    	return data;
					    }
					}); 
					
					$('#city').combobox({    
					    url:'',    
					    valueField:'id',    
					    textField:'name',
					    filter: function(q, row){
							var opts = $(this).combobox('options');
							return row[opts.textField].indexOf(q) > -1;
						},
						onSelect:function(rec){
							$("#branch").combobox('clear');
							var url = appId+"/actions/tool/getRegion.do?actionId=tool_getRegion&sel_level=24&sel_cd=" + rec.id;
							$("#branch").combobox('reload', url);
					    },
					    loadFilter:function(oData){
					    	var data = oData.data;
					    	data.unshift({"id":"","name":"请选择"});
					    	return data;
					    }
					});
					
					$('#branch').combobox({
					    url:'',    
					    valueField:'id',    
					    textField:'name',
					    filter: function(q, row){
							var opts = $(this).combobox('options');
							return row[opts.textField].indexOf(q) >-1;
						},
						onSelect:function(rec){
							/*$("#orgCode").textbox('setValue',rec.orgCode);*/
							$("#orgCode").val(rec.orgCode);
							$("#orgName").val(rec.orgName);
					    },  
					    loadFilter:function(oData){
					    	var data = oData.data;
					    	for(var i in data){
					    		data[i].name = data[i].name + "(" + data[i].id + ")";
					    	}
					    	data.unshift({"id":"","name":"请选择"});
					    	return data;
					    }
					});
            	 return false;
             }else if(roleVales[0] == "hq"){
            	 loadCombobox();
            	 return false;
             }
		});

	function loadCombobox(){
		$('#prov').combobox({    
		    url:appId+"/actions/tool/getRegion.do?actionId=tool_getRegion&sel_level=11&sel_cd=",    
		    valueField:'id',    
		    textField:'name',
		    filter: function(q, row){
				var opts = $(this).combobox('options');
				return row[opts.textField].indexOf(q) > -1;
			},
		    onSelect:function(rec){
		    	$("#city").combobox('clear');
		    	$("#branch").combobox('clear');
	    		var url = appId+"/actions/tool/getRegion.do?actionId=tool_getRegion&sel_level=2&sel_cd=" + rec.id;
		    	$("#city").combobox('reload', url);
		    	url = appId+"/actions/tool/getRegion.do?actionId=tool_getRegion&sel_level=24&sel_cd=";
		    	$("#branch").combobox('reload', url);
		    	$("#orgCode").val("");
		    	$("#orgCode").blur();
		    },
		    loadFilter:function(oData){
		    	var data = oData.data;
		    	data.unshift({"id":"","name":"请选择"});
		    	return data;
		    }
		}); 
		
		$('#city').combobox({    
		    url:'',    
		    valueField:'id',    
		    textField:'name',
		    filter: function(q, row){
				var opts = $(this).combobox('options');
				return row[opts.textField].indexOf(q) > -1;
			},
			onSelect:function(rec){
				$("#branch").combobox('clear');
				var url = appId+"/actions/tool/getRegion.do?actionId=tool_getRegion&sel_level=24&sel_cd=" + rec.id;
				$("#branch").combobox('reload', url);
		    },
		    loadFilter:function(oData){
		    	var data = {};
		    	if(oData.data){
		    		data = oData.data
		    		data.unshift({"id":"","name":"请选择"});
		    	}
		    	return data;
		    }
		});
		
		$('#branch').combobox({
		    url:'',    
		    valueField:'id',    
		    textField:'name',
		    filter: function(q, row){
				var opts = $(this).combobox('options');
				return row[opts.textField].indexOf(q) >-1;
			},
			onSelect:function(rec){
				/*$("#orgCode").textbox('setValue',rec.orgCode);*/
				$("#orgCode").val(rec.id);
				$("#orgName").val(rec.name);
		    },  
		    loadFilter:function(oData){
		    	var data = oData.data;
		    	for(var i in data){
		    		data[i].name = data[i].name + "(" + data[i].id + ")";
		    	}
		    	data.unshift({"id":"","name":"请选择"});
		    	return data;
		    }
		});
	
	};

	$("#orgCode").keyup(function() {
		var orgCode = $("#orgCode").val();
		if (orgCode.length == 6 || orgCode.length == 8) {
			var url = appId+"/actions/tool/getOrgInf.do?actionId=tool_getOrgInf&sel_cd=" + orgCode;
			$.ajax({
				   type: "POST",
				   url: url,
				   dataType:"json",
				   success: function(oData){
						if (oData!=null && oData.errorCode == "0") {
							var orgInfo = oData.data;
							if(orgInfo!=null){
								$('#branch').combobox('setValue',orgInfo.orgName + "("+ orgInfo.orgCd + ")");
								var provinceId = "";
								$('#prov').combobox('setValue',orgInfo.provName);
								var url = appId+"/actions/mdm/cities.do?actionId=mdm_city_list&privinceId=" + provinceId;
								$("#city").combobox('reload', url);
								$('#city').combobox('setValue',orgInfo.cityName);
							}else{
								$$.showJcdfMessager('提示消息', '编码输入错误', 'info');
						    	$('#prov').combobox('setValue',"");
								$("#city").combobox('clear');
						    	$("#branch").combobox('clear');
						    	$("#orgCode").val("");
							}
						}
				   }
				});
			}
	});
}

function authControl_area(){
	var userData = null;
	var jsonStr = sessionStorage.getItem("userData");
	if(jsonStr == null || jsonStr==""){
		$.ajax({
			type : "POST",
			url : c_url_get_user_profile,
			cache : false,
			async: false,
			dataType : "json",
			success : function(data) {
				if (null != data && data.errorCode==0) {
					userData = data.data;
					sessionStorage.setItem("userData", JSON.stringify(userData)); 
				} else {
					$$.showJcdfMessager('提示消息', data.msg, 'warning'); 
				}
			}
		});
	}else{
		userData = JSON.parse(jsonStr) ;
	}
	$.each( userData.user_roles, function(i, n){
             var roleVales = n.split("_");  
             if(roleVales[0] == "branch"){
            	 $('#prov').attr("disabled","disabled");
         		 $('#city').attr("disabled","disabled");
         		 $('#branch').attr("disabled","disabled");
         		 $('#orgCode').attr("disabled","disabled");
         		 $('#branch').val(userData.org_name);
         		 $('#orgCode').val(userData.org_id);
         		 return false;
             }else if(roleVales[0] == "dbct"){
            	 $("#dbct").val(roleVales[1])
            	 $('#prov').attr("disabled","disabled");
         		 $('#city').attr("disabled","disabled");
            	 $('#branch').combobox({
            		    url:appId+"/actions/tool/getRegion.do?actionId=tool_getRegion&sel_level=44&sel_cd=" + roleVales[1],    
            		    valueField:'id',    
            		    textField:'name',
            		    filter: function(q, row){
            				var opts = $(this).combobox('options');
            				return row[opts.textField].indexOf(q) >-1;
            			},
            			onSelect:function(rec){
            				$("#orgCode").val(rec.id);
            				$("#orgName").val(rec.name);
            		    },  
            		    loadFilter:function(oData){
            		    	var data = oData.data;
            		    	for(var i in data){
            		    		data[i].name = data[i].id + "(" + data[i].name + ")";
            		    	}
            		    	data.unshift({"id":"","name":"请选择"});
            		    	return data;
            		    }
            		});
            	 return false;
             }else if(roleVales[0] == "prov"){
            	 $('#prov').attr("disabled","disabled");
            	 $('#city').combobox({    
            		    url:appId+"/actions/tool/getRegion.do?actionId=tool_getRegion&sel_level=2&sel_cd=" + roleVales[1],      
            		    valueField:'id',    
            		    textField:'name',
            		    filter: function(q, row){
            				var opts = $(this).combobox('options');
            				return row[opts.textField].indexOf(q) > -1;
            			},
            			onSelect:function(rec){
            				$("#branch").combobox('clear');
            				var url = appId+"/actions/tool/getRegion.do?actionId=tool_getRegion&sel_level=24&sel_cd=" + rec.id;  
            				$("#branch").combobox('reload', url);
            		    },
            		    loadFilter:function(oData){
            		    	var data = oData.data;
            		    	data.unshift({"cityId":"","cityName":"请选择"});
            		    	return data;
            		    }
            		});
            		
            		$('#branch').combobox({
            		    url:'',    
            		    valueField:'id',    
            		    textField:'name',
            		    filter: function(q, row){
            				var opts = $(this).combobox('options');
            				return row[opts.textField].indexOf(q) >-1;
            			},
            			onSelect:function(rec){
            				$("#orgCode").val(rec.id);
            				$("#orgName").val(rec.name);
            		    },  
            		    loadFilter:function(oData){
            		    	var data = oData.data;
            		    	for(var i in data){
            		    		data[i].name = data[i].name + "(" + data[i].id + ")";
            		    	}
            		    	data.unshift({"id":"","name":"请选择"});
            		    	return data;
            		    }
            		});
            	 return false;
             }else if(roleVales[0] == "area"){
            	 // $("#area").val(roleVales[1]);
            	 $('#area').attr("disabled","disabled");
            	 $('#prov').combobox({    
					    url:appId+"/actions/tool/getRegion.do?actionId=tool_getRegion&sel_level=1&sel_cd=" + roleVales[1],        
					    valueField:'id',    
					    textField:'name',
					    filter: function(q, row){
							var opts = $(this).combobox('options');
							return row[opts.textField].indexOf(q) > -1;
						},
					    onSelect:function(rec){
					    	$("#city").combobox('clear');
					    	$("#branch").combobox('clear');
				    		var url = appId+"/actions/tool/getRegion.do?actionId=tool_getRegion&sel_level=2&sel_cd=" + rec.id;
					    	$("#city").combobox('reload', url);
					    	url = appId+"/actions/tool/getRegion.do?actionId=tool_getRegion&sel_level=24&sel_cd=";
					    	$("#branch").combobox('reload', url);
					    	$("#orgCode").val("");
					    	$("#provinceName").val(rec.name);
					    	$("#orgCode").blur();
					    },
					    loadFilter:function(oData){
					    	var data = oData.data;
					    	data.unshift({"id":"","name":"请选择"});
					    	return data;
					    }
					}); 
					
					$('#city').combobox({    
					    url:'',    
					    valueField:'id',    
					    textField:'name',
					    filter: function(q, row){
							var opts = $(this).combobox('options');
							return row[opts.textField].indexOf(q) > -1;
						},
						onSelect:function(rec){
							$("#branch").combobox('clear');
							var url = appId+"/actions/tool/getRegion.do?actionId=tool_getRegion&sel_level=24&sel_cd=" + rec.id;
							$("#branch").combobox('reload', url);
					    },
					    loadFilter:function(oData){
					    	var data = oData.data;
					    	data.unshift({"id":"","name":"请选择"});
					    	return data;
					    }
					});
					
					$('#branch').combobox({
					    url:'',    
					    valueField:'id',    
					    textField:'name',
					    filter: function(q, row){
							var opts = $(this).combobox('options');
							return row[opts.textField].indexOf(q) >-1;
						},
						onSelect:function(rec){
							/*$("#orgCode").textbox('setValue',rec.orgCode);*/
							$("#orgCode").val(rec.orgCode);
							$("#orgName").val(rec.orgName);
					    },  
					    loadFilter:function(oData){
					    	var data = oData.data;
					    	for(var i in data){
					    		data[i].name = data[i].name + "(" + data[i].id + ")";
					    	}
					    	data.unshift({"id":"","name":"请选择"});
					    	return data;
					    }
					});
            	 return false;
             }else if(roleVales[0] == "hq"){
            	 loadCombobox_area();
            	 return false;
             }
		});
	
		$("#orgCode").keyup(function() {
		var orgCode = $("#orgCode").val();
		if (orgCode.length == 6 || orgCode.length == 8) {
			var url = appId+"/actions/tool/getOrgInf.do?actionId=tool_getOrgInf&sel_cd=" + orgCode;
			$.ajax({
				   type: "POST",
				   url: url,
				   dataType:"json",
				   success: function(oData){
						if (oData!=null && oData.errorCode == "0") {
							var orgInfo = oData.data;
							if(orgInfo!=null){
								var url = appId+"/actions/tool/getRegion.do?actionId=tool_getRegion&sel_level=0&sel_cd=";
								$("#area").combobox('reload', url);
								$('#area').combobox('setValue',orgInfo.areaName);
								url = appId+"/actions/tool/getRegion.do?actionId=tool_getRegion&sel_level=11&sel_cd="+orgInfo.areaId;
								$("#prov").combobox('reload', url);
								$('#prov').combobox('setValue',orgInfo.provName);
								url = appId+"/actions/tool/getRegion.do?actionId=tool_getRegion&sel_level=2&sel_cd="+orgInfo.provId;
								$("#city").combobox('reload', url);
								$('#city').combobox('setValue',orgInfo.cityName);
								$('#branch').combobox('setValue',orgInfo.orgName + "("+ orgInfo.orgCd + ")");
							}else{
								$$.showJcdfMessager('提示消息', '编码输入错误', 'info');
						    	$('#area').combobox('setValue',"");
								$("#prov").combobox('clear');
								$("#city").combobox('clear');
						    	$("#branch").combobox('clear');
						    	$("#orgCode").val("");
							}
						}
				   }
				});
			}
	});
	}

	function loadCombobox_area(){
		$('#area').combobox({    
		    url:appId+"/actions/tool/getRegion.do?actionId=tool_getRegion&sel_level=0&sel_cd=0",    
		    valueField:'id',    
		    textField:'name',
		    filter: function(q, row){
				var opts = $(this).combobox('options');
				return row[opts.textField].indexOf(q) > -1;
			},
		    onSelect:function(rec){
		    	$("#prov").combobox('clear');
		    	$("#city").combobox('clear');
		    	$("#branch").combobox('clear');
		    	url = appId+"/actions/tool/getRegion.do?actionId=tool_getRegion&sel_level=1&sel_cd=" + rec.id;
		    	$("#prov").combobox('reload', url);
		    	$("#orgCode").val("");
		    	$("#orgCode").blur();
		    },
		    loadFilter:function(oData){
		    	var data = oData.data;
		    	data.unshift({"id":"","name":"请选择"});
		    	return data;
		    }
		});
		$('#prov').combobox({    
		    url:appId+"/actions/tool/getRegion.do?actionId=tool_getRegion&sel_level=11&sel_cd=",    
		    valueField:'id',    
		    textField:'name',
		    filter: function(q, row){
				var opts = $(this).combobox('options');
				return row[opts.textField].indexOf(q) > -1;
			},
		    onSelect:function(rec){
		    	$("#city").combobox('clear');
		    	$("#branch").combobox('clear');
	    		var url = appId+"/actions/tool/getRegion.do?actionId=tool_getRegion&sel_level=2&sel_cd=" + rec.id;
		    	$("#city").combobox('reload', url);
		    	url = appId+"/actions/tool/getRegion.do?actionId=tool_getRegion&sel_level=24&sel_cd=";
		    	$("#branch").combobox('reload', url);
		    	$("#orgCode").val("");
		    	$("#orgCode").blur();
		    },
		    loadFilter:function(oData){
		    	var data = oData.data;
		    	data.unshift({"id":"","name":"请选择"});
		    	return data;
		    }
		}); 
		
		$('#city').combobox({    
		    url:'',    
		    valueField:'id',    
		    textField:'name',
		    filter: function(q, row){
				var opts = $(this).combobox('options');
				return row[opts.textField].indexOf(q) > -1;
			},
			onSelect:function(rec){
				$("#branch").combobox('clear');
				var url = appId+"/actions/tool/getRegion.do?actionId=tool_getRegion&sel_level=24&sel_cd=" + rec.id;
				$("#branch").combobox('reload', url);
		    },
		    loadFilter:function(oData){
		    	var data = {};
		    	if(oData.data){
		    		data = oData.data
		    		data.unshift({"id":"","name":"请选择"});
		    	}
		    	return data;
		    }
		});
		
		$('#branch').combobox({
			    url:'',    
			    valueField:'id',    
			    textField:'name',
			    filter: function(q, row){
					var opts = $(this).combobox('options');
					return row[opts.textField].indexOf(q) >-1;
				},
				onSelect:function(rec){
					/*$("#orgCode").textbox('setValue',rec.orgCode);*/
					$("#orgCode").val(rec.id);
					$("#orgName").val(rec.name);
			    },  
			    loadFilter:function(oData){
			    	var data = oData.data;
			    	for(var i in data){
			    		data[i].name = data[i].name + "(" + data[i].id + ")";
			    	}
			    	data.unshift({"id":"","name":"请选择"});
			    	return data;
			    }
			});
	
	}

//合并
function mergeCells(tableID, colList) {
    var colArray = colList.split(",");
    var tTable = $("#" + tableID);
    var tableRows = tTable.datagrid("getRows");
    var tableRowCnts = tableRows.length;
    var rowspan;
    var preTxt = "";
    var curTxt = "";
    for (j = colArray.length - 1; j >= 0; j--) {
    	preTxt = "";
    	rowspan = 1;
        for (i = 0; i <= tableRowCnts; i++) {
            if (i == tableRowCnts) {
                curTxt = "";
            } else {
                curTxt = tableRows[i][colArray[j]];
            }
            if (preTxt == curTxt) {
            	rowspan += 1;
            } else {
                tTable.datagrid("mergeCells", {
                    index: i - rowspan,
                    field: colArray[j],//合并字段
                    rowspan: rowspan,
                    colspan: null
                });
                rowspan = 1;
            }
            preTxt = curTxt;
        }
    }
}	