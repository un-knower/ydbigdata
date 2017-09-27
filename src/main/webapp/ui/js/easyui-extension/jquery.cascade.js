(function($){
    $.parser.plugins.push("cascade");//注册扩展组件
    $.fn.cascade = function (options, param) {//定义扩展组件
        //当options为字符串时，说明执行的是该插件的方法。    	
    	if (typeof options == "string") { return $.fn.cascade.apply(this, arguments);}        
        
        options = options || {};
        //当该组件在一个页面出现多次时，this是一个集合，故需要通过each遍历。
        return this.each(function () {
            var state = $.data(this, 'cascade');
			if (state){
				$.extend(state.options, options);
			}else{
                var opts = $.extend({},  $.fn.cascade.defaults,$.fn.cascade.parseOptions(this), options);                   
                $.data(this, 'cascade', {
					options: opts
				}); 
                
                //找到级联组件并初始化
               var components =   $(this).find("[cascadeType]").toArray(); 
                for(var i = 0 ; i< components.length;i++){
                	 var component = $(components [i]);
                	 var subComponent = (i< components.length-1) ?$(components [i+1]):null;
                	 var cascadeType = component.attr("cascadeType");
                	 switch(cascadeType){
                	 	case "combobox": initcombobox(component,subComponent);break;
                	 	case "tree": inittree(component,subComponent);break;
                	 	case "fuzzybox": initfuzzybox(component,subComponent);break;
                	 	case "datagrid": initdatagrid(component,subComponent);break;
                	 }
                }                
            }			
			
        });
    };
    function initdatagrid(component,subComponent){
    	component.datagrid({
    		singleSelect:true,
    		loadFilter:function(data){
    			return (data.errorCode ==0 && data.rows!=null)?data:[];    	
    		},
    		onSelect:function(){
    			if(subComponent){
    				var tableKey = component.datagrid('options').tableKey;
    				var refKey ="";
    				var selectedRow = component.datagrid('getSelected');
    				for(var i = 0;i<tableKey.length;i++){
    					var v = selectedRow[tableKey[i]];
    					refKey += v;
    					if(i<tableKey.length-1)refKey +=",";
    				}
    				resetSub(subComponent,component.datagrid('options').refKeyName,refKey);	    			
    			}
    		}
    	});
    	component.attr("originURL",component.datagrid('options').url);
    }
    function initcombobox(component,subComponent){  	
    	
    	component.combobox({
    		loadFilter:function(data){
    			return (data.errorCode ==0 && data.data!=null)?data.data:[];    	
    		},
    		onSelect:function(){
    			if(subComponent){
    				resetSub(subComponent,component.combobox('options').refKeyName,component.combobox('getValue'));	    			
    			}
    		},
    		onChange:function(){
    			if(subComponent){
	    			var type = subComponent.attr('cascadeType');
	    	    	if(type=="combobox" || type=="fuzzybox"){
	    	    		subComponent.combobox('clear');
	    	    	}
    			}
    	    }    		
    	});
    	component.attr("originURL",component.combobox('options').url);
    }
    function inittree(component,subComponent){
    	component.tree({
    		loadFilter:function(data){
    			return (data.errorCode ==0 && data.data!=null)?data.data:[];    	
    		},
    		onSelect:function(){
    			if(subComponent){
    				resetSub(subComponent,component.tree('options').refKeyName,component.tree('getSelected').id);	 
    			}
    		}
    	});
    	component.attr("originURL",component.tree('options').url);
    }
    function initfuzzybox(component,subComponent){
    	component.fuzzybox({
    		onSelect:function(){
    			if(subComponent){
    				resetSub(subComponent,component.fuzzybox('options').refKeyName,component.fuzzybox('getValue'));	 
    			}
    		},
    		onChange:function(){
    			if(subComponent){
	    			var type = subComponent.attr('cascadeType');
	    	    	if(type=="combobox" || type=="fuzzybox"){
	    	    		subComponent.combobox('clear');
	    	    	}
    			}
    		}
    	});
    	component.attr("originURL",component.fuzzybox('options').url);
    }
    
    function resetSub(subComponent,refKeyName,refKey){
    	refKeyName = refKeyName?refKeyName:'r';
    	var type = subComponent.attr('cascadeType');
    	if(type=="combobox"){
    		subComponent.combobox('clear');
    		var url = subComponent.attr("originURL");
    		subComponent.combobox('reload',url+(url.indexOf("?")!=-1?"&":"?")+refKeyName+"="+refKey);
    	}else if(type=="fuzzybox"){
    		subComponent.fuzzybox('clear');
    		var url = subComponent.attr("originURL");
    		subComponent.fuzzybox('reload',url+(url.indexOf("?")!=-1?"&":"?")+refKeyName+"="+refKey);
    	}else if(type=="tree"){
    		var url = subComponent.tree('options').url;
    		var url = subComponent.attr("originURL");
    		subComponent.tree({url:url+(url.indexOf("?")!=-1?"&":"?")+refKeyName+"="+refKey});
    	}else if(type=="datagrid"){
    		var url = subComponent.datagrid('options').url;
    		var url = subComponent.attr("originURL");
    		subComponent.datagrid({url:url+(url.indexOf("?")!=-1?"&":"?")+refKeyName+"="+refKey});
    	}
    }
    $.fn.cascade.methods = {
    		options: function(jq){
    			return $.data(jq[0], 'cascade').options;
    		},
    }
    $.fn.cascade.parseOptions = function(target){
		var t = $(target);
		return $.extend({}, $.parser.parseOptions(target,[
			
		]));
	};
    $.fn.cascade.defaults = $.extend({}, $.fn.combobox.defaults, {        
    	
    });
})(jQuery);