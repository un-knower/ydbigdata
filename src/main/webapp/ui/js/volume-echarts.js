var arrData = [];           //周时间数组
arrData[0]="2017-11-15";
arrData[7]="2017-11-15";
arrData[14]="2017-11-15";

var arrDataMon = [];           //月时间数组
arrDataMon[0]="2016-02";
arrDataMon[7]="2016-09";
arrDataMon[14]="2017-04";

/*揽件量日趋势图横坐标数组*/
var arrDataDay = ['1','2','3','4','5','6','7','8','9','10','11','12','13','14','15'];

/*获取揽件量周趋势显示图的横坐标数组，隔6周显示一次*/
var arrDataWeek = [];
getDataInterval(arrData,arrDataWeek);

/*获取揽件量月趋势显示图的横坐标数组，隔6月显示一次*/
var arrDataMonth = [];
getDataInterval(arrDataMon,arrDataMonth);

/*揽件量季度趋势图横坐标数组*/
var arrDataQuar=['2016三季度','2016四季度','2017一季度','2017二季度'];

var arrDataVolumeActual = [0.1, -0.2, 0.22, 0.15, -0.23, 0.2, 0,0.16,-0.21, -0.2, 0.2, 0.15, 0.13, 0.2, 0];         //揽件量日趋势图实际值值
var arrDataVolumePeriod = [0.11, -0.11, 0.15, 0.13, -0.12, 0.23, 0.10,-0.16,0.11, -0.11, 0.15, 0.13, -0.12, 0.23, 0.10];            //揽件量日趋势图同周期值
var arrDataVolumeWeek = [5, 20, 36, 10, 20,30,55,41, 20, 36, 10, 20,30,55,41];          //揽件量周趋势图y值

var arrDataVolumeQuarActual = [0.1, -0.2, 0.22, 0.15];         //揽件量季度趋势图实际值值
var arrDataVolumeQuarPeriod = [-0.11, -0.11, 0.15, -0.13];            //揽件量季度趋势图同周期值


function getDataInterval(x,y){
    for(var i=0;i<16;i++){
        if (i%7 == 0){
            y[i]=x[i];
        }
    }
    y.splice(0,1, {value:y[0], textStyle:{align:'left'}});
    y.splice(y.length-1,1, {value:y[y.length-1], textStyle:{align:'right'}});
}

function echaDay(id){
    var myChart = echarts.init(document.getElementById(id));
    var option = {	            
        grid:{
            left:0,
            top:0,
            right:5,
            bottom:40,
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data:['同周期','实际值'],
            bottom: 0,
            right: 5
        },
        xAxis:  {
            type:'category' ,
            axisLine:{
            	onZero:false,
            	lineStyle:{
            		color:'#ccc',
            	}
            },
            axisLabel:{
            	textStyle:{
            		color:'#333',
            	}
            },
            boundaryGap: false,
            data: arrDataDay,
            splitLine:{
            	show:true
            }
        },
        yAxis: {
            type: 'value', 
            show: false,
            min : -1,
            max : 1,
            splitLine:{
            	show:false
            }
        },
        series: [
            {
            	markLine : {
                	label:{
                		normal:{
                			show:false,
                		}
                	},
                	symbol:'none',
                    lineStyle: {
                        normal: {
                        	width:1,
                            type:'solid',
                            color:'#ccc',			                        
                        }
                	},
                	animation:false,
                	data : [
                    	{ yAxis: 0 },
                    	{ yAxis: 1 }
        			]
    			},

                name:'同周期',
                type:'line',
                color:['#75B18D'],
                lineStyle : {
                	normal : {
                		width:1,			        
                	}
                	
                },
                symbol:'circle',
                data:arrDataVolumePeriod,
            },
            {
                name:'实际值',
                type:'line',
                color:['#A349A4'],
                symbol:'circle',
                data:arrDataVolumeActual,
            }
    	]
    }
    myChart.setOption(option);
}


function echaWeek(id){
	// 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById(id));
    // 指定图表的配置项和数据
    var option = {	  
    	grid:{
            left:2,
            top:2,
            right:10,
            bottom:40,
            show:true,
            borderWidth:0.4,
            borderColor:'#777',
        },          
        color:['#99D9EA'],
        xAxis: {
        	type:'category',
        	axisLine:{
            	lineStyle:{
            		color:'#ccc',
            	}
            },
            axisLabel:{
        		interval:0,
            	textStyle:{
            		color:'#333',

            	},
            },
            axisTick:{
            	interval:6,
            },
            boundaryGap: false,
            splitLine:{
            	show:true,
            },
            data:arrDataWeek,

        },
        yAxis: {
        	type:'value',
        	splitLine:{
            	show:false,
            },
            show:false,
        },
        series: [{
            // name: '销量',
            type: 'bar',
            barWidth:'40%',
            data: arrDataVolumeWeek,
        }]
    };

     // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
}
function echaMonth(id){
    var myChart = echarts.init(document.getElementById(id));
    var option = {              
        grid:{
            left:0,
            top:2,
            right:10,
            bottom:40,
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data:['同周期','实际值'],
            bottom: 0,
            right: 5
        },
        xAxis:  {
            type:'category' ,
            axisLine:{
                onZero:false,
                lineStyle:{
                    color:'#ccc',
                }
            },
            axisLabel:{
                interval:0,
                textStyle:{
                    color:'#333',
                }
            },
            axisTick:{
                interval:6,
            },
            boundaryGap: false,
            data: arrDataMonth,
            splitLine:{
                show:true
            }
        },
        yAxis: {
            type: 'value', 
            show: false,
            min : -1,
            max : 1,
            splitLine:{
                show:false
            }
        },
        series: [
            {
                markLine : {
                    label:{
                        normal:{
                            show:false,
                        }
                    },
                    symbol:'none',
                    lineStyle: {
                        normal: {
                            width:1,
                            type:'solid',
                            color:'#ccc',                                   
                        }
                    },
                    animation:false,
                    data : [
                        { yAxis: 0 },
                        { yAxis: 1 }
                    ]
                },

                name:'同周期',
                type:'line',
                color:['#75B18D'],
                lineStyle : {
                    normal : {
                        width:1,                    
                    }
                    
                },
                symbol:'circle',
                data:arrDataVolumePeriod,
            },
            {
                name:'实际值',
                type:'line',
                color:['#A349A4'],
                symbol:'circle',
                data:arrDataVolumeActual,
            }
        ]
    }
    myChart.setOption(option);
}

function echaQuarter(id){
    var myChart = echarts.init(document.getElementById(id));
    var option = { 
            grid:{
            left:0,
            top:2,
            right:10,
            bottom:40,
        },            
        legend: {
            data:['季度同期','实际值'],
            bottom: 0,
            right: 5
        },
        xAxis:  {
            type:'category' ,
            axisLine:{
                onZero:false,
                lineStyle:{
                    color:'#ccc',
                }
            },
            axisLabel:{
                textStyle:{
                    color:'#333',
                }
            },
            boundaryGap: true,
            data: arrDataQuar,
            splitLine:{
                show:false
            }
        },
        yAxis: {
            type: 'value', 
            show: false,
            min : -1,
            max : 1,
            splitLine:{
                show:true
            }
        },
        series: [
            {
                markLine : {
                    label:{
                        normal:{
                            show:false,
                        }
                    },
                    symbol:'none',
                    lineStyle: {
                        normal: {
                            width:1,
                            type:'solid',
                            color:'#ccc',                                   
                        }
                    },
                    animation:false,
                    data : [
                        { yAxis: 0 },
                        { yAxis: 1 },
                        { xAxis: 0 },
                        { xAxis: 1 },
                        { xAxis: 2 },
                        { xAxis: 3 },
                    ]
                },

                name:'季度同期',
                type:'line',
                color:['#75B18D'],
                lineStyle : {
                    normal : {
                        width:1,                    
                    }
                    
                },
                symbol:'circle',
                data:arrDataVolumeQuarPeriod,
            },
            {
                name:'实际值',
                type:'line',
                color:['#A349A4'],
                symbol:'circle',
                data:arrDataVolumeQuarActual,
            }
        ]
    }
    myChart.setOption(option);

}