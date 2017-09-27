function loadCompanyCombobox(){
	$('#bprov').combobox({    
	    url:appId+'/actions/city/selectProv.do?actionId=selectProv',    
	    valueField:'provName',    
	    textField:'provName',
	    filter: function(q, row){
			var opts = $(this).combobox('options');
			return row[opts.textField].indexOf(q) > -1;
		},
	    onSelect:function(rec){
	    	$("#bcity").combobox('clear');	   
    		var url = appId+"/actions/city/selectCity.do?actionId=selectCity&provId=" + rec.provId;
	    	$("#bcity").combobox('reload', url);
	    	/*$("#provinceName").val(rec.provinceName);*/
	    },
	    loadFilter:function(oData){
	    	var data = oData.data;
	    	data.unshift({"provName":"","provName":"请选择"});
	    	return data;
	    }
	}); 
	
	$('#bcity').combobox({    
	    url:'',    
	    valueField:'cityName',    
	    textField:'cityName',
	    filter: function(q, row){
			var opts = $(this).combobox('options');
			return row[opts.textField].indexOf(q) > -1;
		},
	    loadFilter:function(oData){
	    	var data = oData.data;
	    	data.unshift({"cityName":"","cityName":"请选择"});
	    	return data;
	    }
	});
	
	$('#branch').combobox({
	    url:'',    
	    valueField:'orgCode',    
	    textField:'orgName',
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
	    		data[i].orgName = data[i].orgName + "(" + data[i].orgCode + ")";
	    	}
	    	data.unshift({"orgCode":"","orgName":"请选择"});
	    	return data;
	    }
	});

};

function checkIsEditable(obj){
	var province = $('#province').combobox('getValue');
	var city = $('#city').combobox('getValue');
	var branch = $('#branch').combobox('getValue');
	if(province=="" && city=="" && branch==""){
		$("#orgCode").removeAttr("readonly");
	}else{
		$("#orgCode").attr("readonly", true);
	}
};