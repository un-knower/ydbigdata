/**
 * YDM-mobile.contants V0.1.0
 * Created by chen on 2016/11/8.
 */
YDMobile=function(){
    return {
        /**
         * [URL获取参数]
         * @param {[type]} val [description]
         */
        QueryString : function(val){
            var uri = window.location.search;
            var re = new RegExp("[?&]" +val+ "=([^&?]*)", "ig");
            return ((uri.match(re))?(uri.match(re)[0].substr(val.length+2)):null)
        },
        /**
         * 用于判断底部是否距低（一般用于底部标签）
         * @param contentHeight  页面内容的高度（不包括底部标签）
         * @param footerClass   底部标签的类名
         */
        bottomLogin:function(contentHeight,footerClass){     //用于判断底部是否距低
            $("."+footerClass).removeClass("weui_extra_area weui_text_area_myself");
            var height=document.documentElement.clientHeight;   //屏幕高度
            var content_height=$("."+contentHeight).outerHeight(true);   //获得content_daily_ul_new的高度
            if(height>=content_height+48){
                $("."+footerClass).addClass("weui_extra_area").removeClass("weui_text_area_myself");
            }
            else{$("."+footerClass).addClass("weui_text_area_myself").removeClass("weui_extra_area");}

        },

        /**
         * 用于判断顶部的距离（一般用于顶部tab）
         * @param topFixed  顶部固定的类名
         * @param className   顶部tab下面的类名
         */

        topFix :function(topFixed,className){
        var topFix=$("."+topFixed).outerHeight(true);
        $("."+className).css("margin-top",topFix);
    },

        /**
         * 用于弹出选择modal
         * @param titile     标题
         * @param msg        内容
         * @param  backName        内容
         * @param submitName   确定按钮信息
         */
        message : function(titile, msg, backName,submitName){
            $.modal({
                title: titile,
                text: msg,
                buttons: [
                    { text: backName, className: "default", onClick: function(){ } },
                    { text: submitName, onClick: function(){
                        dealMsgHandle();
                    }}
                ]
            });
        },

        /**
         * [成功消息]
         * @param  {[type]} title   [description]
         * @param  {[type]} content [description]
         * @param  {[type]} flag    [description]
         * @param  {[type]} url     [description]
         * @return {[type]}         [description]
         */
        messageAlert : function(content,title,flag,url){
            if(flag=="success"){
                $.alert(content,title,function(){
                    window.location.href=url;
                })
            }
            else{$.alert(content,title)}
        },

        /**
         * [数字正则校验]
         * @param  {[type]}  val [description]
         * @return {Boolean}     [description]
         */
        isNum : function(val){
            var reg = new RegExp("^[0-9]*$");
            if(!reg.test(val)){
                return false;
            }
            return true;
        },
        /**  用于判断值是否为空
         *   需要判断的值
         */

        isBlank : function(val) {
            if (val.trim() == "" || val == null || val.trim() == "defined") {
                return true;
            }
            return false;
        },

        /**
         * 用于表单动画
         */
        inputStyle:function(){
            var input_selector = 'input[type=text], input[type=password], input[type=email], input[type=url], input[type=tel], input[type=number], input[type=search], textarea';
            $(document).on("focus",input_selector,function(){
                $(this).siblings().addClass("active");
            });
            $(document).on("blur",input_selector,function(){
                if (YDM.isBlank($(this).val())) {
                    $(this).siblings().removeClass("active");
                }
            });
        },
        /**
         * 校验身份证号
         * @param IDClassName 身份证信息的class
         * @constructor
         */

        IDCheck:function(IDClassName) {
            var value=$("." + IDClassName).val();
            var isIDCard1=/^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$|^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/;
            if(!isIDCard1.test(value)){
                $.alert("请输入正确的身份证号码","提示");
                $("." + IDClassName).val("");
            }
        },

        /**
         * 校验手机号码
         * @param telNum 电话号码的class
         */

        checkTel:function(telNum) {
            var telNumVal =  $("." + telNum).val();
            var reg = /^0?1[3|4|5|7|8|9][0-9]\d{8}$/;
            if (!reg.test(telNumVal)) {
                $.alert("请输入正确的手机号码！","提示");
               $("." + telNum).val("");
            }
        },
		/**
         * 获取时间 格式为：format
         * format 参数为时间格式
         */

        getLocaleDateTime : function(format){
            var date = new Date();
            var getyear = date.getFullYear();
            var getmonth = date.getMonth()+1;
            var getday = date.getDate();
            var gethour = date.getHours();
            var getminute = date.getMinutes();
            var getsecond = date.getSeconds();
            var getMonth = YDM.changeTime(getmonth);
            var getDay =  YDM.changeTime(getday);
            var getMinute =  YDM.changeTime(getminute);
            var getSecond =  YDM.changeTime(getsecond);
            var gethours;
            if(gethour>12){
                gethours = gethour-12;
            } else {
                gethours = gethour;
            }
            switch (format){
                case "yyyy-MM-dd HH:mm" : return getyear + "-" + getMonth + "-" + getDay + " " + gethour + ":" + getMinute; break;
                case "yyyy-MM-dd HH:mm:ss" : return getyear + "-" + getMonth + "-" + getDay + " " + gethour + ":" + getMinute + ":" + getSecond; break;
                case "yyyy-MM-dd HH:m:s" : return getyear + "-" + getMonth + "-" + getDay + " " + gethour + ":" + getminute + ":" + getsecond; break;
                case "yyyy-MM-dd hh:mm:ss" : return getyear + "-" + getMonth + "-" + getDay + " " + gethours + ":" + getMinute + ":" + getSecond; break;
                case "yyyy-MM-dd hh:m:s" : return getyear + "-" + getMonth + "-" + getDay + " " + gethours + ":" + getminute + ":" + getsecond; break;
                case "yyyy-M-d HH:mm:ss" : return getyear + "-" + getmonth + "-" + getday + " " + gethour + ":" + getMinute + ":" + getSecond; break;
                case "yyyy-M-d HH:m:s" : return getyear + "-" + getmonth + "-" + getday + " " + gethour + ":" + getminute + ":" + getsecond; break;
                case "yyyy-M-d hh:mm:ss" : return getyear + "-" + getmonth + "-" + getday + " " + gethours + ":" + getMinute + ":" + getSecond; break;
                case "yyyy-M-d hh:m:s" : return getyear + "-" + getmonth + "-" + getday + " " + gethours + ":" + getminute + ":" + getsecond; break;
                case "yyyy/MM/dd HH:mm:ss" : return getyear + "/" + getMonth + "/" + getDay + " " + gethour + ":" + getMinute + ":" + getSecond; break;
                case "yyyy/MM/dd HH:m:s" : return getyear + "/" + getMonth + "/" + getDay + " " + gethour + ":" + getminute + ":" + getsecond; break;
                case "yyyy/MM/dd hh:mm:ss" : return getyear + "/" + getMonth + "/" + getDay + " " + gethours + ":" + getMinute + ":" + getSecond; break;
                case "yyyy/MM/dd hh:m:s" : return getyear + "/" + getMonth + "/" + getDay + " " + gethours + ":" + getminute + ":" + getsecond; break;
                case "yyyy/M/d HH:mm:ss" : return getyear + "/" + getmonth + "/" + getday + " " + gethour + ":" + getMinute + ":" + getSecond; break;
                case "yyyy/M/d HH:m:s" : return getyear + "/" + getmonth + "/" + getday + " " + gethour + ":" + getminute + ":" + getsecond; break;
                case "yyyy/M/d hh:mm:ss" : return getyear + "/" + getmonth + "/" + getday + " " + gethours + ":" + getMinute + ":" + getSecond; break;
                case "yyyy/M/d hh:m:s" : return getyear + "/" + getmonth + "/" + getday + " " + gethours + ":" + getminute + ":" + getsecond; break;
                case "yyyy/MM/dd": return getyear + "/" + getMonth + "/" + getDay; break;
                case "yyyy/M/d": return getyear + "/" + getmonth + "/" + getday ; break;
                case "yyyy-MM-dd": return getyear + "-" + getMonth + "-" + getDay; break;
                case "yyyy-M-d": return getyear + "-" + getmonth + "-" + getday; break;
            }
        },
        /**
         * 判断是否需要时间前面增加“0”
         * @param time 所或得到未处理的事件
         * @returns {*}
         */

        changeTime : function(time) {
            if(time<10){
                time = "0" + time;
            }
               return  time ;
        },



        /**
         * 用于成功页面
         * @param id   canvas的id
         * @param rectColor   外部圆圈的颜色
         * @param okColor       “对号”的颜色
         */
        unitCanvas : function(id,rectColor,okColor){
            var canvas = document.getElementById(id);
            var ctx = canvas.getContext("2d");
            var W = canvas.width;
            var H = canvas.height;
            var R = W/2-5;
            var deg=0,new_deg= 0,next_deg=0;
            var text,text_w=0;

            function init(){
                ctx.clearRect(0,0,W,H);
                ctx.beginPath();
                ctx.strokeStyle=rectColor;
                ctx.lineWidth=2;
                ctx.lineCap="round";
                ctx.arc(W/2,H/2,R,0,Math.PI*2,false);
                ctx.stroke();

                ctx.beginPath();
                ctx.strokeStyle = okColor;
                ctx.lineWidth=6;
                ctx.lineCap="round";
                ctx.moveTo(W/4,W/2);
                ctx.lineTo(W/4+5*W*text/28,W/2+W*text/6);
                if(deg>=new_deg&&deg<=next_deg){
                    ctx.lineTo(3*W/7+9*W*text_w/28,W*2/3-W*text_w/3);
                }
                ctx.stroke();
            }
            function unitTo(){
                new_deg=80;
                next_deg=110;
                if(deg<new_deg){
                    deg++;
                    text=deg/new_deg;
                }
                if(deg>=new_deg&&deg<next_deg){
                    deg++;
                    text_w=(deg-new_deg)/(next_deg-new_deg);
                }
                init();
            }
            setInterval(unitTo);
        },
        /** 用于隐藏数字，一般用于隐藏银行卡的中间位数
         * @param classnum     需要隐藏的数字
         *
         */

        hidenum :function(classnum){
                var cardnum =classnum.toString(10) ;
                var num = cardnum.length;
                var hidenum = cardnum.slice(4,num-4) ;
                var value = cardnum.replace(hidenum,"**** **** ****");
                return value;
        }
    }
};

/*
 * @弹出提示层 ( 加载动画(load), 提示动画(tip), 成功(success), 错误(error), )
 * @method  tipBox
 * @description 默认配置参数
 * @time    2014-12-19
 * @param {Number} width -宽度
 * @param {Number} height -高度
 * @param {String} str -默认文字
 * @param {Object} windowDom -载入窗口 默认当前窗口
 * @param {Number} setTime -定时消失(毫秒) 默认为0 不消失
 * @param {Boolean} hasMask -是否显示遮罩
 * @param {Boolean} hasMaskWhite -显示白色遮罩
 * @param {Boolean} clickDomCancel -点击空白取消
 * @param {Function} callBack -回调函数 (只在开启定时消失时才生效)
 * @param {Function} hasBtn -显示按钮
 * @param {String} type -动画类型 (加载,成功,失败,提示)
 * @example
 * new TipBox();
 * new TipBox({type:'load',setTime:1000,callBack:function(){ alert(..) }});
 */
function TipBox(cfg){
    this.config = {
        width          : 250,
        height         : 170,
        str            : '正在处理',
        windowDom      : window,
        setTime        : 0,
        hasMask        : true,
        hasMaskWhite   : false,
        clickDomCancel : false,
        callBack       : null,
        hasBtn         : false,
        type           : 'success'
    }
    $.extend(this.config,cfg);

    //存在就retrun
    if(TipBox.prototype.boundingBox) return;

    //初始化
    this.render(this.config.type);
    return this;
};

//外层box
TipBox.prototype.boundingBox = null;

//渲染
TipBox.prototype.render = function(tipType,container){
    this.renderUI(tipType);

    //绑定事件
    this.bindUI();

    //初始化UI
    this.syncUI();
    $(container || this.config.windowDom.document.body).append(TipBox.prototype.boundingBox);
};

//渲染UI
TipBox.prototype.renderUI = function(tipType){
    TipBox.prototype.boundingBox = $("<div id='animationTipBox'></div>");
    tipType == 'load'    && this.loadRenderUI();
    tipType == 'success' && this.successRenderUI();
    tipType == 'error'   && this.errorRenderUI();
    tipType == 'tip'     && this.tipRenderUI();
    TipBox.prototype.boundingBox.appendTo(this.config.windowDom.document.body);

    //是否显示遮罩
    if(this.config.hasMask){
        this.config.hasMaskWhite ? this._mask = $("<div class='mask_white'></div>") : this._mask = $("<div class='mask'></div>");
        this._mask.appendTo(this.config.windowDom.document.body);
    }
    // 是否显示按钮
    if(this.config.hasBtn){
        this.config.height = 206;
        $('#animationTipBox').css("margin-top","103px");
        switch(this.config.type){
            case 'success':$(".success").after("<button class='okoButton'>ok</button>");
                break;
            case 'error':$(".lose").after("<button class='okoButton redOkoButton'>ok</button>");
                break;
            case 'tip':$(".tip").after("<button class='okoButton'>ok</button>");
                break;
            default: break;
        }
        $('button.okoButton').on('click',function(){_this.close();});
    }
    //定时消失
    _this = this;
    !this.config.setTime && typeof this.config.callBack === "function" && (this.config.setTime = 1);
    this.config.setTime && setTimeout( function(){ _this.close(); }, _this.config.setTime );
};

TipBox.prototype.bindUI = function(){
    _this = this;

    //点击空白立即取消
    this.config.clickDomCancel && this._mask && this._mask.click(function(){_this.close();});
};
TipBox.prototype.syncUI = function(){
    TipBox.prototype.boundingBox.css({
        width       : this.config.width+'px',
        height      : this.config.height+'px',
        marginLeft  : "-"+(this.config.width/2)+'px',
        marginTop   : "-"+(this.config.height/2)+'px'
    });
};

//提示效果UI
TipBox.prototype.tipRenderUI = function(){
    var tip = "<div class='tip'>";
    tip +="     <div class='icon'>i</div>";
    tip +="     <div class='dec_txt'>"+this.config.str+"</div>";
    tip += "</div>";
    TipBox.prototype.boundingBox.append(tip);
};

//成功效果UI
TipBox.prototype.successRenderUI = function(){
    var suc = "<div class='success'>";
    suc +=" <div class='icon'>";
    suc +=      "<div class='line_short'></div>";
    suc +=      "<div class='line_long'></div>  ";
    suc +=  "</div>";
    suc +=" <div class='dec_txt'>"+this.config.str+"</div>";
    suc += "</div>";
    TipBox.prototype.boundingBox.append(suc);
};

//错误效果UI
TipBox.prototype.errorRenderUI = function(){
    var err  = "<div class='lose'>";
    err +=  "   <div class='icon'>";
    err +=  "       <div class='icon_box'>";
    err +=  "           <div class='line_left'></div>";
    err +=  "           <div class='line_right'></div>";
    err +=  "       </div>";
    err +=  "   </div>";
    err +=  "<div class='dec_txt'>"+this.config.str+"</div>";
    err +=  "</div>";
    TipBox.prototype.boundingBox.append(err);
};

//加载动画load UI
TipBox.prototype.loadRenderUI = function(){
    var load = "<div id='container'>";
    load += "<canvas id='canvas' width='160' height='160'>对不起，你的浏览器不支持Canvas标签！</canvas>";
    load += "<canvas id='progress' width='160' height='160'></canvas>";

    load += "<div id='player'>";
    load +=     "<div class='cover'>";
    load +=     "<div class='controls'>";
    load +=     "<div class='play_pause' id='play' title='Play'></div>";
    load +=     "<div class='play_pause' id='replay'></div>";
    load += "</div>";
    load += "</div>";
    load += "</div>";
    load += "</div>";
    TipBox.prototype.boundingBox.append(load);
    setTimeout(canvas1,100);
};

//关闭
TipBox.prototype.close = function(){
    TipBox.prototype.destroy();
    this.destroy();
    this.config.setTime && typeof this.config.callBack === "function" && this.config.callBack();
};

//销毁
TipBox.prototype.destroy = function(){
    this._mask && this._mask.remove();
    TipBox.prototype.boundingBox && TipBox.prototype.boundingBox.remove();
    TipBox.prototype.boundingBox = null;
};

var time;
var loadTime=155;

function canvas1(){
    var canvas=document.getElementById('canvas');
        var ctx=canvas.getContext("2d");
        ctx.beginPath();
        ctx.strokeStyle='darkgreen';
        ctx.lineWidth=15;
        ctx.arc(80,80,70,Math.PI*25/180,Math.PI*155/180,false);
        ctx.stroke();
        ctx.beginPath();
        ctx.strokeStyle = "yellow";
        ctx.lineWidth = 15;
        ctx.moveTo(40,80);
        ctx.lineTo(120,80);
        ctx.stroke();
        ctx.beginPath();
        ctx.strokeStyle='yellow';
        ctx.lineWidth = 15;
        ctx.moveTo(80,40);
        ctx.lineTo(80,80);
        ctx.lineTo(50,120);
        /* ctx.quadraticCurveTo(80,100,60,120); */
        ctx.stroke();
        ctx.beginPath();
        ctx.strokeStyle='yellow';
        ctx.lineWidth = 15;
        ctx.moveTo(80,80);
        ctx.lineTo(110,120);
        /* ctx.quadraticCurveTo(80,100,100,120); */
        ctx.stroke();

    time =	setInterval(addLoad,50);
}


function addLoad(){
    if(loadTime<=155&&loadTime>25){
        --loadTime;
        YDMLoading();
    }
    if(loadTime==25){
        clearInterval(time);
        loadTime=155;
    }

}

function YDMLoading(){
    var progress=document.getElementById('progress');
    var pro=progress.getContext("2d");
    pro.beginPath();
    pro.strokeStyle='yellow';
    pro.lineWidth=15;
    pro.arc(80,80,70,Math.PI*155/180,Math.PI*loadTime/180,true);
    pro.stroke();
}

var Wxmobile = new YDMobile();
var YDM = Wxmobile;