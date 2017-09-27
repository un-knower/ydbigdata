(function($){
    $.parser.plugins.push("pcc");//注册扩展组件
    $.fn.pcc = function (options, param) {//定义扩展组件
        //当options为字符串时，说明执行的是该插件的方法。    	
    	if (typeof options == "string") { return $.fn.pcc.apply(this, arguments);}        
        
        options = options || {};
        //当该组件在一个页面出现多次时，this是一个集合，故需要通过each遍历。
        return this.each(function () {
            var state = $.data(this, 'pcc');
			if (state){
				$.extend(state.options, options);
			}else{
                var opts = $.extend({},  $.fn.pcc.defaults,$.fn.pcc.parseOptions(this), options);                   
                $.data(this, 'pcc', {
					options: opts
				}); 
              
            }			
			create(this);
        });
    };
    var provinceStr = ' 省份 <input name="provinceId" data-options="url:\'actions/mdm/provinces.do?actionId=cip_mdm_provinces_getProvinces\',valueField:\'provinceId\',textField:\'provinceName\',refKeyName:\'provinceId\'" cascadeType="combobox"/>';
    var provinceByAreaStr = ' 省份 <input name="provinceId" data-options="url:\'actions/mdm/bigarea/provinces.do?actionId=cip_mdm_bigarea_provinces_getProvsByBigarea\',valueField:\'provinceId\',textField:\'provinceName\',refKeyName:\'provinceId\'" cascadeType="combobox"/>';
    var cityStr = ' 城市 <input name="cityId" data-options="url:\'actions/mdm/cities.do?actionId=cip_mdm_cities_getCities\',valueField:\'cityId\',textField:\'cityName\',refKeyName:\'cityId\'"  cascadeType="combobox"/>';
    var countyStr = ' 区县 <input name="countyId" data-options="url:\'actions/mdm/counties.do?actionId=cip_mdm_counties_getCounties\',valueField:\'countyId\',textField:\'countyName\'" cascadeType="combobox"/>';
    var bigareaStr = ' 大区 <input name="bigareaId" data-options="url:\'actions/mdm/bigareas.do?actionId=cip_mdm_bigareas_getBigareas\',valueField:\'areaId\',textField:\'areaName\',refKeyName:\'bigareaId\'" cascadeType="combobox"/>';
    var orgStr = ' 机构 <input name="orgId" data-options="url:\'actions/mdm/orgs.do?actionId=cip_mdm_orgs_getOrgs\',valueField:\'orgId\',textField:\'orgName\'" cascadeType="combobox"/>';
    var orgByProvinceStr = ' 机构 <input name="orgId" data-options="url:\'actions/mdm/province/orgs.do?actionId=cip_mdm_province_orgs_getOrgsByProv\',valueField:\'orgId\',textField:\'orgName\'" cascadeType="combobox"/>';
    function create(target){
	   var opts = $.data(target, 'pcc').options;
	   var pccStr = "";
       switch(opts.showType){
       		case "a": pccStr = bigareaStr;break;//全部大区
       		case "p": pccStr = provinceStr;break;//全部省
       		case "pcc": pccStr=provinceStr+cityStr+countyStr;break;//省市区联动
       		case "pc": pccStr = provinceStr+cityStr;break;//省市联动
       		case "pco": pccStr = provinceStr+cityStr+orgStr;break;//省市机构联动
       		case "po": pccStr = provinceStr+orgByProvinceStr;break;//省机构联动
       		case "ap": pccStr = bigareaStr+provinceByAreaStr;break;//大区省联动
       		case "apc": pccStr = bigareaStr+provinceByAreaStr+cityStr;break;//大区省市联动
       		case "apcc": pccStr = bigareaStr+provinceByAreaStr+cityStr+countyStr;break;//大区省市区联动
       		case "apco": pccStr = bigareaStr+provinceByAreaStr+cityStr+orgStr;break;//大区省市机构联动
       }
       
       $(target).html('<div class="easyui-cascade" >'+pccStr+'</div>');
       $.parser.parse( $(target));
   }
    $.fn.pcc.methods = {
    		options: function(jq){
    			return $.data(jq[0], 'pcc').options;
    		},
    }
    $.fn.pcc.parseOptions = function(target){
		var t = $(target);
		return $.extend({}, $.parser.parseOptions(target,[
			
		]));
	};
    $.fn.pcc.defaults = $.extend({}, $.fn.combobox.defaults, {        
    	showType:'pcc'
    });
})(jQuery);