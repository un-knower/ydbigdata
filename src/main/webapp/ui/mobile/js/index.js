/**
 * Created by chen on 2017-05-16
 */
var appId = "/ydbi";
var dataTime =  localStorage.getItem("dataTime");
var orgName =  JSON.parse(localStorage.getItem("orgName"));
var userInfoOp =  JSON.parse(localStorage.getItem("userInfo"));
//var userInfoOp =  {
//    userRoles:["prov_210000"]
//};
var waterRemark =  localStorage.getItem("waterRemark");
var myArray = [];
var orgInfo,showOrgName,showOrgCode,showOrgIndex,showOrgType,newVal,code,userRoles,areaType,areaInfo = [];
var areaInfoAll = [{
        areaName:"集团",
        areaCode:"",
        proviceInfo:[{
            proviceName:"无",
            proviceCode: "",
            branchInfo: ["无"],
            branchCode:[""]
        },{
            proviceName: "广东",
            proviceCode: "440000",
            branchInfo: ["无", "广州分拨中心", "东莞分拨中心","中山分拨中心","揭阳分拨中心","佛山分拨中心"],
            branchCode:["","510001","511702","528418","515201","528810"]
        },{
            proviceName: "广西",
            proviceCode: "450000",
            branchInfo: ["无", "南宁分拨中心"],
            branchCode:["","530001"]
        },{
            proviceName: "海南",
            proviceCode: "460000",
            branchInfo: ["无"],
            branchCode:""
        },{
            proviceName: "上海",
            proviceCode: "310000",
            branchInfo: ["无", "上海分拨中心", "奉贤分拨中心", "浦东分拨中心"],
            branchCode:["","200000","201418","202491"]
        },{
            proviceName: "陕西",
            proviceCode: "610000",
            branchInfo: ["无", "西安分拨中心"],
            branchCode:["","710088"]
        },{
            proviceName: "甘肃",
            proviceCode: "620000",
            branchInfo: ["无", "兰州分拨中心"],
            branchCode:["","731000"]
        },{
            proviceName: "宁夏",
            proviceCode: "640000",
            branchInfo: ["无"],
            branchCode:[""]
        },{
            proviceName: "新疆",
            proviceCode: "650000",
            branchInfo: ["无", "乌鲁木齐分拨中心"],
            branchCode:["","830088"]
        },{
            proviceName: "青海",
            proviceCode: "630000",
            branchInfo: ["无"],
            branchCode:[""]
        },{
            proviceName: "北京",
            proviceCode: "110000",
            branchInfo: ["无", "北京分拨中心"],
            branchCode:["","100088"]
        },{
            proviceName: "天津",
            proviceCode: "120000",
            branchInfo: ["无", "天津分拨中心"],
            branchCode:["","300000"]
        },{
            proviceName: "河北",
            proviceCode: "130000",
            branchInfo: ["无", "石家庄分拨中心"],
            branchCode:["","950095"]
        },{
            proviceName: "黑龙江",
            proviceCode: "230000",
            branchInfo: ["无", "哈尔滨分拨中心"],
            branchCode:["","150088"]
        },{
            proviceName: "吉林",
            proviceCode: "220000",
            branchInfo: ["无", "长春分拨中心"],
            branchCode:["","130088"]
        },{
            proviceName: "辽宁",
            proviceCode: "210000",
            branchInfo: ["无", "沈阳分拨中心", "大连分拨中心", "盘锦分拨中心"],
            branchCode:["","110000","116200","124751"]
        },{
            proviceName: "山西",
            proviceCode: "140000",
            branchInfo: ["无", "太原分拨中心"],
            branchCode:["","931000"]
        },{
            proviceName: "内蒙古",
            proviceCode: "150000",
            branchInfo: ["无", "呼和浩特分拨中心"],
            branchCode:["","910100"]
        },{
            proviceName: "江苏",
            proviceCode: "320000",
            branchInfo: ["无", "南京分拨中心","泰州分拨中心", "扬州分拨中心", "淮安分拨中心","盐城分拨中心","徐州分拨中心", "苏州分拨中心", "南通分拨中心","常州分拨中心"],
            branchCode:["","210000","225920","225050","223000","224800","221000","215120","226000","212000"]
        },{
            proviceName: "安徽",
            proviceCode: "340000",
            branchInfo: ["无", "合肥分拨中心","蚌埠分拨中心", "芜湖分拨中心"],
            branchCode:["","230001","233003","241001"]
        },{
            proviceName: "山东",
            proviceCode: "370000",
            branchInfo: ["无", "济南分拨中心","烟台分拨中心", "潍坊分拨中心","青岛分拨中心"],
            branchCode:["","250001","265800","261000","266088"]
        },{
            proviceName: "浙江",
            proviceCode: "330000",
            branchInfo: ["无", "义乌分拨中心","宁波分拨中心", "温州分拨中心", "台州分拨中心", "杭州分拨中心", "嘉兴分拨中心"],
            branchCode:["","322001","315000","325001","318001","310000","314001"]
        },{
            proviceName: "福建",
            proviceCode: "350000",
            branchInfo: ["无", "晋江分拨中心","福州分拨中心", "三明分拨中心"],
            branchCode:["","362201","350001","365500"]
        },{
            proviceName: "江西",
            proviceCode: "360000",
            branchInfo: ["无", "南昌分拨中心"],
            branchCode:["","330001"]
        },{
            proviceName: "湖北",
            proviceCode: "420000",
            branchInfo: ["无", "武汉分拨中心"],
            branchCode:["","430001"]
        },{
            proviceName: "湖南",
            proviceCode: "430000",
            branchInfo: ["无", "长沙分拨中心"],
            branchCode:["","410001"]
        },{
            proviceName: "河南",
            proviceCode: "410000",
            branchInfo: ["无", "郑州分拨中心","漯河分拨中心"],
            branchCode:["","450000","462449"]
        },{
            proviceName: "四川",
            proviceCode: "510000",
            branchInfo: ["无", "成都分拨中心"],
            branchCode:["","610001"]
        },{
            proviceName: "重庆",
            proviceCode: "500000",
            branchInfo: ["无", "重庆分拨中心"],
            branchCode:["","630001"]
        },{
            proviceName: "云南",
            proviceCode: "530000",
            branchInfo: ["无", "昆明分拨中心"],
            branchCode:["","650108"]
        },{
            proviceName: "贵州",
            proviceCode: "520000",
            branchInfo: ["无", "贵阳分拨中心"],
            branchCode:["","550088"]
        },{
            proviceName: "西藏",
            proviceCode: "540000",
            branchInfo: ["无"],
            branchCode:[""]
        }]
    },{
        areaName:"广东大区",
        areaCode:"1",
        proviceInfo:[{
            proviceName:"无",
            proviceCode: "",
            branchInfo: ["无"],
            branchCode:[""]
        },{
            proviceName: "广东",
            proviceCode: "440000",
            branchInfo: ["无", "广州分拨中心", "东莞分拨中心","中山分拨中心","揭阳分拨中心","佛山分拨中心"],
            branchCode:["","510001","511702","528418","515201","528810"]
        },{
            proviceName: "广西",
            proviceCode: "450000",
            branchInfo: ["无", "南宁分拨中心"],
            branchCode:["","530001"]
        },{
            proviceName: "海南",
            proviceCode: "460000",
            branchInfo: ["无"],
            branchCode:""
        }]
    },{
        areaName:"上海大区",
        areaCode:"2",
        proviceInfo:[{
            proviceName:"无",
            proviceCode: "",
            branchInfo:["无"],
            branchCode:[""]
        },{
            proviceName: "上海",
            proviceCode: "310000",
            branchInfo: ["无", "上海分拨中心", "奉贤分拨中心", "浦东分拨中心"],
            branchCode:["","200000","201418","202491"]
        },{
            proviceName: "陕西",
            proviceCode: "610000",
            branchInfo: ["无", "西安分拨中心"],
            branchCode:["","710088"]
        },{
            proviceName: "甘肃",
            proviceCode: "620000",
            branchInfo: ["无", "兰州分拨中心"],
            branchCode:["","731000"]
        },{
            proviceName: "宁夏",
            proviceCode: "640000",
            branchInfo: ["无"],
            branchCode:[""]
        },{
            proviceName: "新疆",
            proviceCode: "650000",
            branchInfo: ["无", "乌鲁木齐分拨中心"],
            branchCode:["","830088"]
        },{
            proviceName: "青海",
            proviceCode: "630000",
            branchInfo: ["无"],
            branchCode:[""]
        }]
    },{
        areaName:"北京大区",
        areaCode:"3",
        proviceInfo:[{
            proviceName:"无",
            proviceCode: "",
            branchInfo:["无"],
            branchCode:[""]
        },{
            proviceName: "北京",
            proviceCode: "110000",
            branchInfo: ["无", "北京分拨中心"],
            branchCode:["","100088"]
        },{
            proviceName: "天津",
            proviceCode: "120000",
            branchInfo: ["无", "天津分拨中心"],
            branchCode:["","300000"]
        },{
            proviceName: "河北",
            proviceCode: "130000",
            branchInfo: ["无", "石家庄分拨中心"],
            branchCode:["","950095"]
        },{
            proviceName: "黑龙江",
            proviceCode: "230000",
            branchInfo: ["无", "哈尔滨分拨中心"],
            branchCode:["","150088"]
        },{
            proviceName: "吉林",
            proviceCode: "220000",
            branchInfo: ["无", "长春分拨中心"],
            branchCode:["","130088"]
        },{
            proviceName: "辽宁",
            proviceCode: "210000",
            branchInfo: ["无", "沈阳分拨中心", "大连分拨中心", "盘锦分拨中心"],
            branchCode:["","110000","116200","124751"]
        },{
            proviceName: "山西",
            proviceCode: "140000",
            branchInfo: ["无", "太原分拨中心"],
            branchCode:["","931000"]
        },{
            proviceName: "内蒙古",
            proviceCode: "150000",
            branchInfo: ["无", "呼和浩特分拨中心"],
            branchCode:["","910100"]
        }]
    },{
        areaName:"江苏大区",
         areaCode:"4",
        proviceInfo:[{
            proviceName:"无",
            proviceCode: "",
            branchInfo:["无"],
            branchCode:[""]
        },{
            proviceName: "江苏",
            proviceCode: "320000",
            branchInfo: ["无", "南京分拨中心","泰州分拨中心", "扬州分拨中心", "淮安分拨中心","盐城分拨中心","徐州分拨中心", "苏州分拨中心", "南通分拨中心","常州分拨中心"],
            branchCode:["","210000","225920","225050","223000","224800","221000","215120","226000","212000"]
        },{
            proviceName: "安徽",
            proviceCode: "340000",
            branchInfo: ["无", "合肥分拨中心","蚌埠分拨中心", "芜湖分拨中心"],
            branchCode:["","230001","233003","241001"]
        },{
            proviceName: "山东",
            proviceCode: "370000",
            branchInfo: ["无", "济南分拨中心","烟台分拨中心", "潍坊分拨中心","青岛分拨中心"],
            branchCode:["","250001","265800","261000","266088"]
        }]
    },{
        areaName:"浙江大区",
        areaCode:"5",
        proviceInfo:[{
            proviceName:"无",
            proviceCode: "",
            branchInfo:["无"],
            branchCode:[""]
        },{
            proviceName: "浙江",
            proviceCode: "330000",
            branchInfo: ["无", "义乌分拨中心","宁波分拨中心", "温州分拨中心", "台州分拨中心", "杭州分拨中心", "嘉兴分拨中心"],
            branchCode:["","322001","315000","325001","318001","310000","314001"]
        },{
            proviceName: "福建",
            proviceCode: "350000",
            branchInfo: ["无", "晋江分拨中心","福州分拨中心", "三明分拨中心"],
            branchCode:["","362201","350001","365500"]
        },{
            proviceName: "江西",
            proviceCode: "360000",
            branchInfo: ["无", "南昌分拨中心"],
            branchCode:["","330001"]
        }]
    },{
        areaName:"华中西南大区",
        areaCode:"6",
        proviceInfo:[{
            proviceName:"无",
            proviceCode: "",
            branchInfo:["无"],
            branchCode:[""]
        },{
            proviceName: "湖北",
            proviceCode: "420000",
            branchInfo: ["无", "武汉分拨中心"],
            branchCode:["","430001"]
        },{
            proviceName: "湖南",
            proviceCode: "430000",
            branchInfo: ["无", "长沙分拨中心"],
            branchCode:["","410001"]
        },{
            proviceName: "河南",
            proviceCode: "410000",
            branchInfo: ["无", "郑州分拨中心","漯河分拨中心"],
            branchCode:["","450000","462449"]
        },{
            proviceName: "四川",
            proviceCode: "510000",
            branchInfo: ["无", "成都分拨中心"],
            branchCode:["","610001"]
        },{
            proviceName: "重庆",
            proviceCode: "500000",
            branchInfo: ["无", "重庆分拨中心"],
            branchCode:["","630001"]
        },{
            proviceName: "云南",
            proviceCode: "530000",
            branchInfo: ["无", "昆明分拨中心"],
            branchCode:["","650108"]
        },{
            proviceName: "贵州",
            proviceCode: "520000",
            branchInfo: ["无", "贵阳分拨中心"],
            branchCode:["","550088"]
        },{
            proviceName: "西藏",
            proviceCode: "540000",
            branchInfo: ["无"],
            branchCode:[""]
        }]
    }];
    var areaName = [];
    var proviceName = [];
    var branchName = [];
    var oldvalue;

    var cols = [
        {
            textAlign: 'center',
            values: areaName
        },
        {
            textAlign: 'center',
            values: proviceName
        },
        {
            textAlign: 'center',
            values: branchName
        }
    ];

/**
 * 公用方法
 */
function commonFun() {
    //获取水印
    $("body").css("background","url('"+ waterRemark +"')");
}

/**
 *
 * @param val 需要处理的值
 * @param area 用于判断处理值还是符号
 * @param percent  用于判断处理值是否增加“%”
 * @returns {*}
 */

function changeData(val,area,percent) {
    var parsenum = parseFloat(val);
    var stringVal = val + "";
    if(area == "abs" && val == null) {
        return "---"; //数值为空显示“0”
    }else if(area == "abs" && val != null) {
        var lastInfo = percent != undefined ? percent : '';
        if(parsenum < 0) {
            return (stringVal.substr(1) + lastInfo)
        }else {
            return (val + lastInfo)
        }
    }else if(area == "symbol" && parsenum > 0) {
        return "up"
    }else if(area == "symbol" && parsenum < 0 ) {
        return "down"
    }else if(area == "symbol" && (val == null || parsenum == 0)) {
        return "hide"; //数值为空显示“数字”
    }
}

function getNum() {
    $.each(userInfoOp.userRoles,function(index,val) {
        if(val.indexOf("menu") == -1) {
            userRoles = val.split("_");
            return false;
        }
    });
    if(userRoles[0] == "hq") {
        areaInfo = areaInfoAll;
        orgInfo = {
            "orgTitle": ["集团","无","无"],
            "orgIndex":[0,0,0]
        };
        showOrgCode = orgName == null ? "" : orgName.code;
        showOrgType = orgName == null ? "area_id" : orgName.areaType;
    }else if(userRoles[0] == "area"){
        areaInfo.push(areaInfoAll[userRoles[1]]);
        orgInfo = {
            "orgTitle": [areaInfoAll[userRoles[1]].areaName,"无","无"],
            "orgIndex":[0,0,0]
        };
        showOrgCode = orgName == null ? areaInfoAll[userRoles[1]].areaCode : orgName.code;
        showOrgType = orgName == null ? "area_id" : orgName.areaType;
    }else if(userRoles[0] == "prov"){
        areaInfoAll.shift();
        $.each(areaInfoAll,function(indexPar,valParent) {
            $.each(valParent.proviceInfo,function(indexChild,valChild) {
                if(valChild.proviceCode == userRoles[1]) {
                    var areaInfoPro = [];
                    areaInfo.push(areaInfoAll[indexPar]);
                    areaInfoPro = areaInfo[0].proviceInfo.splice(indexChild,1);
                    console.log(areaInfoPro);
                    areaInfo[0].proviceInfo = areaInfoPro;
                    orgInfo = {
                        "orgTitle": [areaInfoAll[indexPar].areaName,areaInfoPro[0].proviceName,"无"],
                        "orgIndex":[0,0,0]
                    };
                    showOrgCode = orgName == null ? areaInfoPro[0].proviceCode : orgName.code;
                    showOrgType = orgName == null ? "prov_id" : orgName.areaType;
                    return false

                }
            })
        });
    }
    showOrgName = orgName == null ? orgInfo.orgTitle : orgName.orgTitle;
    showOrgIndex = orgName == null ? orgInfo.orgIndex : orgName.orgIndex;
    oldvalue = {
        areaVal:"总部",
        proviceVal:"无",
        branchVal:"无"
    };
    if(orgName != null ) {
        oldvalue = {
            areaVal:showOrgName[0],
            proviceVal:showOrgName[1],
            branchVal:showOrgName[2]
        };
    }
    $.each(areaInfo,function(index,val) {
        areaName.push(val.areaName);
    });
    $.each(areaInfo[showOrgIndex[0]].proviceInfo,function(index,val) {
        proviceName.push(val.proviceName);
    });
    $.each(areaInfo[showOrgIndex[0]].proviceInfo[showOrgIndex[1]].branchInfo,function(index,val) {
        branchName.push(val);
    });
}

function refreshCtrl() {
    var str = "<div class='flex-hide-loading'></div>";
    $("body").append(str);
    $(document.body).pullToRefresh().on("pull-to-refresh", function() {
        $(".flex-hide-loading").addClass("flex-show-loading");
        refreshInfo();
        $(document.body).pullToRefreshDone();
        setTimeout(function() {
            $(".flex-hide-loading").removeClass("flex-show-loading");
        },2000)
    });
}

function linkTab() {
    $(".tab-list").on("click",function() {
        var index = $(this).index();
        switch (index) {
            case 0 : location.replace('data-home.html');break;
            case 1 : location.replace('data-index.html');break;
            case 2 : location.replace('point-index.html');break;
            case 3 : location.replace('traffic-index.html');break;
            case 4 : location.replace('transport-index.html');break;
            case 5 : location.replace('aging-index.html');break;
            case 6 : location.replace('QC-index.html');break;
        }
    })
}


function setView() {
    // tab浮动的高度
    var tabHei = $(".top-tab-area").outerHeight(true);
    $(".org-area").css("padding-top",tabHei);
    $(".tab-search-input").css("height",tabHei);
    $(".search-btn").css("height",tabHei-8);
    if($(".tab-list-active").index() != 0) {
        var lefePos = $(".tab-list-active").prev().position().left;
        $(".tab-all").scrollLeft(lefePos);
    }
    $(".org-name-detail,.date-name-detail").on("click",function() {
        $("#search-info-area").blur();
    });
    //账户搜索
    $("#search-info-area").on("keyup",function() {
        var value = $(this).val().trim();
        if(value != "") {
            $(".search-close").css("display","inline-block");
        }else {
            $(".search-close").trigger("click")
        }
    });
    $(".search-close").on("click",function() {
        $("#search-info-area").val("");
        $(this).hide();
    });

    function changeTime(time) {
        if(time<10){
            time = "0" + time;
        }
        return  time ;
    };

    //选择日历
    var date = new Date(new Date().getTime() - 86400000);
    var getyear = date.getFullYear();
    var getmonth = changeTime(date.getMonth()+1);
    var getday = changeTime(date.getDate());
    var getTime = getyear + "-" + getmonth + "-" + getday;
    var CalData = dataTime == null ? getTime : dataTime;
    $("#calender-select").val(CalData).calendar({
        value:[CalData],
        maxDate :getTime ,
        onClose:function() {
            localStorage.setItem("dataTime",$("#calender-select").val());
        	getBaskInfo();
        }
    });
    dataTime == null ? localStorage.setItem("dataTime",getTime):  	//首次加载的时候进行缓存时间
    //选择公司
        console.log("ll");
    $("#picker-pos").picker({
        title: "请选择机构",
        value:showOrgName,
        cols: cols,
        onChange:function() {
            newVal = {
                areaVal : $(".picker-items-col:first-child").find(".picker-selected").attr("data-picker-value"),
                areaValIndex : $(".picker-items-col:first-child").find(".picker-selected").index(),
                proviceVal : $(".picker-items-col:nth-child(2)").find(".picker-selected").attr("data-picker-value"),
                proviceValIndex : $(".picker-items-col:nth-child(2)").find(".picker-selected").index(),
                branchVal : $(".picker-items-col:nth-child(3)").find(".picker-selected").attr("data-picker-value")
            };
           if(oldvalue.areaVal != newVal.areaVal) {
               console.log(oldvalue);
               proviceName = [];
               $.each(areaInfo[newVal.areaValIndex].proviceInfo,function(index,val) {
                   proviceName.push(val.proviceName);
               });
               cols[1].replaceValues(proviceName,"");
               branchName = [];
               $.each(areaInfo[0].proviceInfo[0].branchInfo,function(index,val) {
                   branchName.push(val);
               });
               cols[2].replaceValues(branchName,"");
               oldvalue.areaVal = newVal.areaVal
           }else if(oldvalue.proviceVal != newVal.proviceVal) {
               branchName = [];
               $.each(areaInfo[newVal.areaValIndex].proviceInfo[newVal.proviceValIndex].branchInfo,function(index,val) {
                   branchName.push(val);
               });
               cols[2].replaceValues(branchName,"");
               oldvalue.proviceVal = newVal.proviceVal
           }
            orgInfo = {
                "orgTitle": [],
                "orgIndex":[]
            };
            $(".picker-selected").each(function(index,value) {
                var val = $(this).attr("data-picker-value");
                var num = $(this).index();
                orgInfo.orgTitle.push(val);
                orgInfo.orgIndex.push(num);
                if(val != "无") {
                    backVal = val;
                    if(index == 0) {
                        code = areaInfo[newVal.areaValIndex].areaCode;
                        areaType = "area_id";
                    }else if(index == 1) {
                        code = areaInfo[newVal.areaValIndex].proviceInfo[newVal.proviceValIndex].proviceCode;
                        areaType = "prov_id";
                    }else if(index == 2) {
                        code = areaInfo[newVal.areaValIndex].proviceInfo[newVal.proviceValIndex].branchCode[num];
                        areaType = "com_id";
                    }
                }
            });
            setTimeout(function(){
                $("#picker-pos").attr({"data-value":code,"data-num":areaType}).val(backVal);
                orgInfo["code"] = code;
                orgInfo["areaType"] = areaType;
                console.log(orgInfo);
            },1);
        },
        onClose:function() {
            getBaskInfo();
            localStorage.setItem("orgName",JSON.stringify(orgInfo));
        }
    });
    //设置搜索
    $(".search-btn").on("click",function() {
        $(this).animate({"right": "-100%"}, 100, function () {
            $(".tab-search-input").animate({"right": 0}, 200)
        });
    });
    $(".search-cancel").on("click",function() {
        $(".tab-search-input").animate({"right":"-100%"},100,function() {
            $(".search-btn").animate({"right":"0"},200)
        })
    })
}

function formatNumberRgx(num) {     //用于将数字用分隔符隔开
    var parts = num.toString().split(".");
    parts[0] = parts[0].replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    return parts.join(".");
};


function showToken(token,ratio) {
    $("#data-detail-num").empty().removeClass('ratio-down-num ratio-up-num').addClass("ratio-" + changeData(ratio,'symbol') +"-num");
    var minNum = 0;
    if(null == token) {
        $("#data-detail-num").html("---");
        return false;
    }
    var strLength= formatNumberRgx(token);
    for(var i=0;i<strLength.length;i++){
        myArray[i]=strLength.substr(i,1);
        if( myArray[i] != ",") {
            $("#data-detail-num").append("<span>0</span>");
        }else {
            $("#data-detail-num").append("<span>,</span>");
        }


    }
    var time = setInterval(function() {
        for(var m = 0; m < myArray.length; m++){
            if(minNum <= parseInt(myArray[m])){
                $("#data-detail-num span").eq(m).html(minNum);
            }
        }
        minNum++;
    },100);
    setTimeout(function() {
        clearTimeout(time)
    },2000)
};

function getWeekNumber(date) {
	var now = new Date(date),
	year = now.getFullYear(),
	month = now.getMonth(),
	days = now.getDate();
    //那一天是那一年中的第多少天
	for (var i = 0; i < month; i++) {
		days += getMonthDays(year, i);
	}
	//那一年第一天是星期几
	var yearFirstDay = new Date(year, 0, 1).getDay() || 7;

	var week = null;
	if (yearFirstDay == 1) {
		week = Math.ceil(days / yearFirstDay);
	} else {
		days -= (7 - yearFirstDay + 1);
		week = Math.ceil(days / 7) + 1;
	}
	return week-1;
}

function isLeapYear(year) {
	return (year % 400 == 0) || (year % 4 == 0 && year % 100 != 0);
}

function getMonthDays(year, month) {
	return [31, null, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31][month] || (isLeapYear(year) ? 29 : 28);
}

