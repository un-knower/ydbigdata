function getChartOption (startValue, endValue, xAxis, dataActual, dataTarget, act) {
    var screenWidth = $(window).width();
    var yAxisTickLength = screenWidth * 14/100 ;
    var markLineX = [];
    var marginHei = act == "carline" ? -15 :17;
    $.each(xAxis, function(index, xAxisData) {
        var markLine = {xAxis:xAxisData};
        markLineX.push(markLine);
    });
    var option = {
        grid:{
            show:false,
            left: '1%',
            right: '0%',
            bottom: '10%',
            top:'0%',
            containLabel: true,
            backgroundColor:'#27272f'
        },
        tooltip:{
            show:true,
            trigger: 'axis',
        },
        xAxis:{
            type:'category',
            data:xAxis,
            axisLine: {
                show:false,
            },
            axisTick:{
                show:false,
            },
            boundaryGap:true,
            axisLabel:{
                formatter: function (value) {
                    if (act == 'day') {
                        var day = value.split('-') [2];
                        return day;
                    } else if (act == 'month'){
                        var month = value.split(' ') [1];
                        return month;
                    } else if (act == 'week'){
                        //var week = value.split('-') [1] +　'-' + value.split('-') [2]
                        return value;
                    }else if (act == 'carLine'){
                        return;
                    } else {
                        var quarter = value.split(' ') [1]
                        return quarter;
                    }
                },
                inside:false,
                margin:marginHei,
                show:'show',
                textStyle: {
                    color:'#525257',
                    fontSize:12
                },

            },
            splitLine:{
                show:false,
                lineStyle: {
                    color:'#2a2a31',
                }
            },
        },
        yAxis: {
            type:'value',
            scale:true,
            axisLine: {
                onZero:false,
                show:false,
            },
            axisTick:{
                show:true,
                length:yAxisTickLength,
                lineStyle :{
                    color:'#3a3a42',
                    type:'dash'
                }
            },
            boundaryGap:['10%','10%'],
            splitNumber:3,
            splitLine:{
                show:true,
                lineStyle: {
                    type : 'dashed',
                    color:'#3a3a42',
                },
                showMinLabel:false,
            },
            axisLabel : {
                formatter: function(value) {
                    if (act == 'carline') {
                        return value;
                    }else {
                        return value + "%"
                    }
                },
                inside:false,
                margin:0,
                showMinLabel:false,
                textStyle:{
                    baseline:'top',
                    color:'#737377',
                    fontSize:12
                }
            },

        },
        dataZoom: [
            {
                id: 'dataZoomX',
                type: 'inside',
                xAxisIndex: [0],
                filterMode: 'filter',
                startValue: startValue,
                endValue:endValue,
                show:false,
                maxValueSpan:7,
                minValueSpan:7,
                zoomLock:true
            },
        ],
        series:[
            {   type:'line',
                markLine : {
                    silent:true,
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
                            color:'#2a2a31',
                        }
                    },
                    animation:false,
                    data :markLineX
                },
                zlevel:0

            },
            {
                //smooth:true,
                type:'line',
                name:act == "carline" ? "新增车线" : "实际值",
                color:['#ab5256'],
                data:dataActual,
                symbol:'image:/img/actualLine.png',
                animation:false,
                symbolSize:'6',
                connectNulls:true,
                clipOverflow:true,
                zlevel:100
            },                    {
                //smooth:true,
                type:'line',
                name:act == "carline" ? "撤销车线" : "预期值",
                color:['#d3be5d'],
                data:dataTarget,
                symbol:'image:/img/targetLine.png',
                animation:false,
                symbolSize:'6',
                connectNulls:true,
                zlevel:100
            }],

    }
    return option;
}

function doChart(act) {
    var chartField = echarts.init(document.getElementById("chartDv"));
    var currentY;
    var currentTargetData;
    var currentX;
    var startValue;
    var endValue;
    if (act == 'week') {
        currentX = getWeekChartData() [0];
        currentY = getWeekChartData() [1];
        currentTargetData = getWeekChartData() [2];
        startValue = currentX [currentX.length -8];
        endValue = currentX [currentX.length -1];
    } else if (act == 'month') {
        currentX = getMonthChartData() [0];
        currentY = getMonthChartData() [1];
        currentTargetData = getMonthChartData() [2];
        startValue = currentX [currentX.length -6];
        endValue = currentX [currentX.length -1];
    } else if (act == 'quarter') {
        currentX = getQuarterChartData() [0];
        currentY = getQuarterChartData() [1];
        currentTargetData = getQuarterChartData() [2];
        startValue = currentX [currentX.length -4];
        endValue = currentX [currentX.length -1];
    }
    else if (act == 'carline') {
        currentX = getCarlineChartData() [0];
        currentY = getCarlineChartData() [1];
        currentTargetData = getCarlineChartData() [2];
        startValue = currentX [currentX.length -8];
        endValue = currentX [currentX.length -1];
    } else  {
        currentX = getDayChartData() [0];
        currentY = getDayChartData() [1];
        currentTargetData = getDayChartData() [2];
        startValue = currentX [currentX.length -15];
        endValue = currentX [currentX.length -1];
    }

    var xAxis = currentX;
    var dataActual = currentY;
    var dataTarget = currentTargetData;

    chartField.setOption(getChartOption(startValue, endValue, xAxis, dataActual, dataTarget, act)) ;

    var isPreviousExist = false;
    var isNextExist = false;
    var previousX = [];
    var previousY = [];
    var previousTargetData = [];
    var nextX = [];
    var nextY = [];
    var nextTargetData = [];

    chartField.on('datazoom', function() {
        var opt = chartField.getOption();
        var dz = opt.dataZoom[0];
        var newStart = dz.startValue;
        var newEnd = dz.endValue;
        if (xAxis[newStart] != startValue) {
            startValue = xAxis[newStart];
            endValue = xAxis[newEnd];

            /**较早的数据**/
            if (!isPreviousExist) {
                var previousData;
                if (act == 'week') {
                    previousData = getPreviousWeekData();
                } else if (act == 'month') {
                    previousData = getPreviousMonthData();
                } else if (act == 'quarter') {
                    previousData = getPreviousQuarterData();
                }else if (act == 'carline') {
                    previousData = getPreviousCarlineData();
                } else {
                    previousData = getPreviousDayData();
                }
                previousX = previousData[0];
                previousY = previousData[1];
                previousTargetData = previousData[2];
                isPreviousExist = true;
            }

            /**较晚的数据**/
            if (!isNextExist) {
                var nextData;
                if (act == 'week') {
                    nextData = getNextWeekData();
                } else if (act == 'month') {
                    nextData = getNextMonthData();
                } else if (act == 'quarter') {
                    nextData = getNextQuarterData();
                }else if (act == 'carline') {
                    nextData = getNextCarlineData();
                }  else {
                    nextData = getNextDayData();
                }
                nextX = nextData[0];
                nextY = nextData[1];
                nextTargetData = nextData[2];
                isNextExist = true;
            }

            if (xAxis[newStart] == currentX[0]) {
                if (previousX.length > 0 && previousY.length > 0 && previousTargetData.length > 0) {
                    xAxis = previousX.concat(currentX);
                    dataActual = previousY.concat(currentY);
                    dataTarget = previousTargetData.concat(currentTargetData);
                    chartField.setOption(getChartOption(startValue, endValue, xAxis, dataActual, dataTarget, act));
                }
            } else if (xAxis[newEnd] == currentX[currentX.length - 1]) {
                if (nextX.length > 0 && nextY.length > 0 && nextTargetData.length > 0) {
                    xAxis = currentX.concat(nextX);
                    dataActual = currentY.concat(nextY);
                    dataTarget = currentTargetData.concat(nextTargetData);
                    chartField.setOption(getChartOption(startValue, endValue, xAxis, dataActual, dataTarget, act));
                }
            } else if (xAxis[0] != currentX [0]) {
                if (newEnd < previousX.length - 1) {
                    nextX = currentX;
                    nextY = currentY;
                    nextTargetData = currentTargetData;
                    currentX = previousX;
                    currentY = previousY;
                    currentTargetData = previousTargetData;
                    previousX = [];
                    previousY = [];
                    previousTargetData = [];
                    xAxis = currentX;
                    dataActual = currentY;
                    dataTarget = currentTargetData;
                    chartField.setOption(getChartOption(startValue, endValue, xAxis, dataActual, dataTarget, act));
                    isPreviousExist = false;
                }
            } else if (xAxis[xAxis.length - 1] != currentX[currentX.length - 1]) {
                if (newStart > currentX.length + 1) {
                    previousX = currentX;
                    previousY = currentY;
                    previousTargetData = currentTargetData;
                    currentX = nextX;
                    currentY = nextY;
                    currentTargetData = nextTargetData;
                    nextX = [];
                    nextY = [];
                    nextTargetData = [];
                    xAxis = currentX;
                    dataActual = currentY;
                    dataTarget = currentTargetData;
                    chartField.setOption(getChartOption(startValue, endValue, xAxis, dataActual, dataTarget, act));
                    /***真实数据需取消注释***/
                    //isNextExist = false;
                }
            }
        }
    });
}

function getDayChartData　() {
    var currentY = [10,11,13,12,14,15,16,17,16,18,19,20,18,17,19,20,22,23,25,24,23,24,22,23,22,19,22,21,22,23];
    var currentTargetData = [24,22,23,22,19,22,21,22,23,10,11,13,12,14,15,16,17,16,18,19,20,18,17,19,20,22,23,25,24,23,24,22,23,22,19,22,21,22,23];
    var currentX = ['2017-5-1', '2017-5-2', '2017-5-3','2017-5-4', '2017-5-5', '2017-5-6','2017-5-7', '2017-5-8', '2017-5-9','2017-5-10', '2017-5-11', '2017-5-12','2017-5-13', '2017-5-14', '2017-5-15',
        '2017-5-16', '2017-5-17', '2017-5-18','2017-5-19', '2017-5-20', '2017-5-21','2017-5-22', '2017-5-23', '2017-5-24','2017-5-25', '2017-5-26', '2017-5-27','2017-5-28', '2017-5-29', '2017-5-30', '2017-5-31'];
    return [currentX, currentY, currentTargetData];

}

function getWeekChartData () {
    var currentY = [19,20,18,17,19,20,22,23,25,24,23];
    var currentTargetData = [22,23,10,11,13,12,14,15,16,17,16];
    var currentX = ['11周', '12周', '13周','14周', '15周', '16周','17周', '18周', '19周','20周']
    return  [currentX, currentY, currentTargetData];
}

function getMonthChartData () {
    var currentY = [19,20,18,17,19,20,22,23,25,24,23];
    var currentTargetData = [22,23,10,11,13,12,14,15,16,17,16];
    var currentX = ['2015 九月','2015 十月', '2015 十一月','2015 十二月', '2016 一月', '2016 二月', '2016 三月', '2016 四月','2016 五月', '2016 六月', '2016 七月'];
    return  [currentX, currentY, currentTargetData];
}

function getCarlineChartData () {
    var currentY = [5,6,7,8,9,9,9,10,9,8,7];
    var currentTargetData = [22,23,10,11,13,12,14,15,16,17,16];
    var currentX = ['1','2', '3','4', '5', '6', '7', '8','9', '10', '11'];
    return  [currentX, currentY, currentTargetData];
}

function getQuarterChartData () {
    var currentY = [19,20,18,17,19,20,22,23,25,24,23];
    var currentTargetData = [22,23,10,11,13,12,14,15,16,17,16];
    var currentX = ['2012 一季度', '2012 二季度','2012 三季度', '2012 四季度', '2013 一季度', '2013 二季度', '2013 三季度', '2013 四季度','2014 一季度', '2014 二季度', '2014 三季度'];
    return  [currentX, currentY, currentTargetData];

}

function getNextDayData() {
    var nextY = [10,11,13,12,14,15,16,17,16,18,19,20,18,17,19,20,22,23,25,24,23,24,22,23,22,19,22,21,22,23];
    var nextX = ['2017-6-1', '2017-6-2', '2017-6-3','2017-6-4', '2017-6-5', '2017-6-6','2017-6-7', '2017-6-8', '2017-6-9','2017-6-10', '2017-6-11', '2017-6-12','2017-6-13', '2017-6-14', '2017-6-15',
        '2017-6-16', '2017-6-17', '2017-6-18','2017-6-19', '2017-6-20', '2017-6-21','2017-6-22', '2017-6-23', '2017-6-24','2017-6-25', '2017-6-26', '2017-6-27','2017-6-28', '2017-6-29', '2017-6-30'];
    var nextTargetData = [24,22,23,22,19,22,21,22,23,10,11,13,12,14,15,16,17,16,18,19,20,18,17,19,20,22,23,25,24,23,24,22,23,22,19,22,21,22,23];
    return [nextX, nextY, nextTargetData];
}

function getPreviousDayData() {
    var previousY = [10,11,13,12,14,15,16,17,16,18,19,20,18,17,19,20,22,23,25,24,23,24,22,23,22,19,22,21,22,23];
    var previousX = ['2017-4-1', '2017-4-2', '2017-4-3','2017-4-4', '2017-4-5', '2017-4-6','2017-4-7', '2017-4-8', '2017-4-9','2017-4-10', '2017-4-11', '2017-4-12','2017-4-13', '2017-4-14', '2017-4-15',
        '2017-4-16', '2017-4-17', '2017-4-18','2017-4-19', '2017-4-20', '2017-4-21','2017-4-22', '2017-4-23', '2017-4-24','2017-4-25', '2017-4-26', '2017-4-27','2017-4-28', '2017-4-29', '2017-4-30'];
    var previousTargetData = [24,22,23,22,19,22,21,22,23,10,11,13,12,14,15,16,17,16,18,19,20,18,17,19,20,22,23,25,24,23,24,22,23,22,19,22,21,22,23];
    return [previousX, previousY, previousTargetData];
}

function getNextWeekData() {
    var nextY = [5,6,7,8,9,9,9,10,9,8,7];
    var nextX = ['21周', '22周', '23周','24周', '25周', '26周','27周', '28周', '29周','30周'];
    var nextTargetData = [22,23,10,11,13,12,14,15,16,17,16];
    return [nextX, nextY, nextTargetData];
}

function getPreviousWeekData() {
    var previousY = [5,6,7,8,9,9,9,10,9,8,7];
    var previousX = ['1周', '2周', '3周','4周', '5周', '6周','7周', '8周', '9周','10周'];
    var previousTargetData = [22,23,10,11,13,12,14,15,16,17,16];
    return [previousX, previousY, previousTargetData];
}

function getNextMonthData() {
    var nextY = [5,6,7,8,9,9,9,10,9,8,7];
    var nextX = ['2016 八月', '2016 九月', '2016 十月','2016 十一月', '2016 十二月', '2017 一月','2017 二月', '2017 三月', '2017 四月', '2017 五月'];
    var nextTargetData = [22,23,10,11,13,12,14,15,16,17,16];
    return [nextX, nextY, nextTargetData];
}

function getPreviousMonthData() {
    var previousY = [5,6,7,8,9,9,9,10,9,8,7];
    var previousX = ['2014 十一月','2014 十二月', '2015 一月', '2015 二月','2015 三月', '2015 四月', '2015 五月','2015 六月', '2015 七月', '2015 八月'];
    var previousTargetData = [22,23,10,11,13,12,14,15,16,17,16];
    return [previousX, previousY, previousTargetData];
}

function getNextQuarterData() {
    var nextY = [5,6,7,8,9,9,9,10,9,8,7];
    var nextX = ['2014 四季度', '2015 一季度', '2015 二季度','2015 三季度', '2015 四季度', '2016 一季度','2016 二季度', '2016 三季度', '2016 四季度', '2017 一季度'];
    var nextTargetData = [22,23,10,11,13,12,14,15,16,17,16];
    return [nextX, nextY, nextTargetData];
}
function getNextCarlineData() {
    var nextY = [10,11,9,11,8,4,10,7,9,10];
    var nextX = ['22', '23', '24','25', '26', '27','28', '29', '30', '31'];
    var nextTargetData = [5,8,9,7,13,4,8,4,5,8];
    return [nextX, nextY, nextTargetData];
}
function getPreviousCarlineData() {
    var previousY = [5,6,5,4,5,6,5,6,7,8];
    var previousX = ['12', '13', '14','15', '16', '17','18', '19', '20', '21'];
    var previousTargetData = [8,8,7,8,9,9,10,9,8,7];
    return [previousX, previousY, previousTargetData];
}
function getPreviousQuarterData() {
    var previousY = [5,6,7,8,9,9,9,10,9,8,7];
    var previousX = ['2009 三季度', '2009 四季度', '2010 一季度','2010 二季度', '2010 三季度', '2010 四季度','2011 一季度', '2011 二季度', '2011 三季度', '2011 四季度'];
    var previousTargetData = [22,23,10,11,13,12,14,15,16,17,16];
    return [previousX, previousY, previousTargetData];
}