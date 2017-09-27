function loadCompanyCombobox(){
	$('#province').combobox({    
	    url:appId+'/actions/mdm/provinces.do?actionId=mdm_province_list',    
	    valueField:'provinceId',    
	    textField:'provinceName',
	    filter: function(q, row){
			var opts = $(this).combobox('options');
			return row[opts.textField].indexOf(q) > -1;
		},
	    onSelect:function(rec){
	    	$("#city").combobox('clear');
	    	$("#branch").combobox('clear');
    		var url = appId+"/actions/mdm/cities.do?actionId=mdm_city_list&provinceId=" + rec.provinceId;
	    	$("#city").combobox('reload', url);
	    	url = appId+"/actions/mdm/orgs.do?actionId=mdm_org_list&cityId=";
	    	$("#branch").combobox('reload', url);
	    	$("#orgCode").val("");
	    	$("#provinceName").val(rec.provinceName);
	    	$("#orgCode").blur();
	    },
	    loadFilter:function(oData){
	    	var data = oData.data;
	    	data.unshift({"provinceId":"","provinceName":"请选择"});
	    	return data;
	    }
	}); 
	
	$('#city').combobox({    
	    url:'',    
	    valueField:'cityId',    
	    textField:'cityName',
	    filter: function(q, row){
			var opts = $(this).combobox('options');
			return row[opts.textField].indexOf(q) > -1;
		},
		onSelect:function(rec){
			$("#branch").combobox('clear');
			var url = appId+"/actions/mdm/orgs.do?actionId=mdm_org_list&cityId=" + rec.cityId;
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