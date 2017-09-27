(function($){
    $.parser.plugins.push("waitingbutton");//注册扩展组件
    $.fn.waitingbutton = function (options, param) {//定义扩展组件
        //当options为字符串时，说明执行的是该插件的方法。
        if (typeof options == "string") { return $.fn.linkbutton.apply(this, arguments);}
        options = options || {};
        //当该组件在一个页面出现多次时，this是一个集合，故需要通过each遍历。
        return this.each(function () {
            var state = $.data(this, 'waitingbutton');
			if (state){
				$.extend(state.options, options);
			}else{
                var jq = $(this);
                //$.fn.linkbutton.parseOptions(this)作用是获取页面中的data-options中的配置
                var opts = $.extend({},  $.fn.waitingbutton.defaults,$.fn.linkbutton.parseOptions(this), options);    
                $.fn.linkbutton.call(jq,opts);
                opts.timer = null;
                opts.count =0;
                opts.originText = jq.linkbutton('options').text;
                $.data(this, 'waitingbutton', {
					options: opts
				});
                jq.bind('click',function(){
                    var isDisbabled = jq.linkbutton('options').disabled;
                    if(!isDisbabled){
                        jq.linkbutton('disable');
                        opts.count = opts.delay;
                        jq.linkbutton({text:opts.originText + '('+ opts.count+'s)'})
                        if(opts.action)opts.action.call(window);
                        opts.timer = setInterval(function(){countdown(jq)},1000);
                    }
                });
            }
        });
    };
    function countdown(target){
        var opts = target.data('waitingbutton').options;        
        if(opts.count>0){
             opts.count--       
             target.linkbutton({text:opts.originText + '('+opts.count+'s)'});
        }else{
             clearInterval(opts.timer);
             target.linkbutton('enable');
             target.linkbutton({text:opts.originText});
        }       
    }
    $.fn.waitingbutton.defaults = $.extend({}, $.fn.linkbutton.defaults, {        
        delay:10
    });
})(jQuery);