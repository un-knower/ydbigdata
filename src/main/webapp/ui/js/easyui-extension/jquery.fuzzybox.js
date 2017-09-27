(function($){
    $.parser.plugins.push("fuzzybox");//注册扩展组件
    $.fn.fuzzybox = function (options, param) {//定义扩展组件
        //当options为字符串时，说明执行的是该插件的方法。    	
        if (typeof options == 'string'){
			var method = $.fn.fuzzybox.methods[options];
			if (method){
				return method(this, param);
			} else {
				return this.combobox(options, param);
			}
		}
        
        options = options || {};
        //当该组件在一个页面出现多次时，this是一个集合，故需要通过each遍历。
        return this.each(function () {
            var state = $.data(this, 'fuzzybox');
			if (state){
				$.extend(state.options, options);
			}else{
                var opts = $.extend({},  $.fn.fuzzybox.defaults,$.fn.fuzzybox.parseOptions(this), options);                   
                $.data(this, 'fuzzybox', {
					options: opts
				}); 
                $.fn.combobox.call($(this),opts);       
            }			
			
        });
    };
    
    $.fn.fuzzybox.methods = {
    		options: function(jq){
    			return $.data(jq[0], 'fuzzybox').options;
    		},
    }
    $.fn.fuzzybox.parseOptions = function(target){
		var t = $(target);
		return $.extend({}, $.fn.combobox.parseOptions(target), $.parser.parseOptions(target,[
			{keyField:'string'}
		]));
	};
    $.fn.fuzzybox.defaults = $.extend({}, $.fn.combobox.defaults, {        
    	mode:"remote",
    	onBeforeLoad:function(param){
    		var options = $(this).fuzzybox('options');
    		param.q = $.trim(param.q);
    		param.k = options.keyField;
    	},
    	formatter: function(row){
    		var opts = $(this).combobox('options');
    		return row[opts.textField] +" ("+row[opts.valueField]+")";
    	},
    	loadFilter:function(data){
    			return (data.errorCode ==0 && data.data!=null)?data.data:[];    	
    	},
    	prompt:"请输入关键+空格进行搜索"
    });
})(jQuery);