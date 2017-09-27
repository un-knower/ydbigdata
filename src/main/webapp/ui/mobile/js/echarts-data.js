/**
 * Created by whg on 2017/7/25.
 */
(function(){
    var Big_Data_Chart = {
        xDataNum:4,
        getNextData:null,
        getPreviousData:null,
        getXFormatter: null,
        getYFormatter: null,
        trendType:'',
        isXShow: true,
        xMargin:20,
        yMargin:0,
        gridBottom:'10%',
        weekRequestDate:'',
        yAxisTickLength:0,
        data: {
            xAxis: [],
            yAxis: [],            
        },
        previous: {
            previousX:[],
            previousY: [],
        },
        next: {
            nextX:[],
            nextY: [],
        },
        current :{
            currentX: [],
            currentY: [],
        },
        isPrevious :{
            isPreviousExist: false,
            isPreviousAjax:false
        },
        isNext :{
            isNextExist: false,
            isNextAjax: false
        },
        series:{
            seriesColor: [],
            seriesName: [],
            seriesSymbol: [],
        },
    }
    var Chart_Series_Default =  {
        seriesColor: ['#ab5256', '#d3be5d', '#14b8d4'],
        seriesName: ['实际值', '预期值'],
        seriesSymbol: ['image://../img/actualLine.png', 'image://../img/targetLine.png', 'image://../img/scanLine.png'],
    }
    var getXFormatterDefault = function (value) {
    	return value;
    }
    var getYFormatterDefault = function (value) {
    	return value + 'w';
    }
    /** 准备曲线的初始值*/
    var doChart = function (actOptions) {
        if (!actOptions.chartFieldId) {
            return;
        }
        initBigDataChart ();
        var chartField = echarts.init(document.getElementById(actOptions.chartFieldId));
        Big_Data_Chart.xDataNum = actOptions.xDataNum || 4;
        Big_Data_Chart.getNextData = actOptions.getNextData || null;
        Big_Data_Chart.getPreviousData = actOptions.getPreviousData || null;
        Big_Data_Chart.getXFormatter = actOptions.getXFormatter || getXFormatterDefault || null;
        Big_Data_Chart.getYFormatter = actOptions.getYFormatter || getYFormatterDefault || null;
        Big_Data_Chart.chartField = chartField;
        Big_Data_Chart.series.seriesColor = actOptions.seriesColor || Chart_Series_Default.seriesColor ||　[];
        Big_Data_Chart.series.seriesName = actOptions.seriesName || Chart_Series_Default.seriesName || [];
        Big_Data_Chart.series.seriesSymbol = actOptions.seriesSymbol || Chart_Series_Default.seriesSymbol || []; 
        Big_Data_Chart.yAxisTickLength = actOptions.yAxisTickLength || $(window).width() * 14/100 ;
        Big_Data_Chart.isXShow = actOptions.isXShow == 'notShow' ? false : true;
        Big_Data_Chart.xMargin = actOptions.xMargin || 20;
        Big_Data_Chart.yMargin = actOptions.yMargin || 0;
        Big_Data_Chart.gridBottom = actOptions.gridBottom || '10%';
        Big_Data_Chart.trendType = actOptions.trendType || '';
//        actOptions.getCurrentData();
    }
    /** 初始化曲线参数*/
    function initBigDataChart () {
        Big_Data_Chart = {
            xDataNum:4,
            getNextData:null,
            getPreviousData:null,
            getXFormatter: null,
            getYFormatter: null,
            isXShow: true,
            trendType:'',
            xMargin:20,
            yMargin:0,
            gridBottom:'10%',
            yAxisTickLength:0,
            data: {
                xAxis: [],
                yAxis: [],
            },
            previous: {
                previousX:[],
                previousY: [],
            },
            next: {
                nextX:[],
                nextY: [],
            },
            current :{
                currentX: [],
                currentY: [],
            },
            isPrevious :{
                isPreviousExist: false,
                isPreviousAjax:false
            },
            isNext :{
                isNextExist: false,
                isNextAjax: false
            },
            series:{
                seriesColor: [],
                seriesName: [],
                seriesSymbol: [],
            },
        }
    }

    var  prepareCurrentData = function (dataX, dataY, weekRequestDate) {
    	 
        if (!dataX || !dataY) {
            return;
        }
        var startValue = dataX [dataX.length - Big_Data_Chart.xDataNum];
        var endValue = dataX [dataX.length -1];
        Big_Data_Chart.startValue = startValue;
        Big_Data_Chart.endValue = endValue;
        Big_Data_Chart.data.xAxis = dataX || [];
        Big_Data_Chart.data.yAxis = dataY || [];
        Big_Data_Chart.weekRequestDate = weekRequestDate;
        Big_Data_Chart.chartField.setOption(getChartOption());
//        var requestDate = Big_Data_Chart.data.xAxis[0];
//        if (Big_Data_Chart.trendType == 'week') {
//        	requestDate = weekRequestDate;
//        }
        
        var requestDate = getDate(Big_Data_Chart.data.xAxis[0],1);
         
        if (Big_Data_Chart.trendType == 'week' || Big_Data_Chart.trendType == 'QC' || Big_Data_Chart.trendType == 'target') {
            requestDate = getDate(Big_Data_Chart.weekRequestDate, 1);
        } else if (Big_Data_Chart.trendType == 'tarffic-pick') {
      	   requestDate = Big_Data_Chart.weekRequestDate;
        } 
         
        Big_Data_Chart.getPreviousData(requestDate);	//获取先前的数据；
        Big_Data_Chart.isPrevious.isPreviousAjax = true;
        //Big_Data_Chart.getNextData(getDate(Big_Data_Chart.data.xAxis[Big_Data_Chart.data.xAxis.length -1],Big_Data_Chart.xDataNum,true));  //获取较后的数据
        //Big_Data_Chart.isNext.isNextAjax = true;
        changeChartData();
    }

    function changeChartData() {
        var lastData = Big_Data_Chart.endValue;
        Big_Data_Chart.current.currentX = Big_Data_Chart.data.xAxis;
        Big_Data_Chart.current.currentY = Big_Data_Chart.data.yAxis;   
        prepareRequestConditionNow(lastData)
         //prepareRequestCondition(lastData);
    } 
    
    /** prepareRequestConditionNow*/ 
    function prepareRequestConditionNow (newStartIndex, newEndIndex) {
    	var isPreviousContact = false;
    	var isNextContact = false;
    	Big_Data_Chart.chartField.on('datazoom', function(res) {
            var opt = Big_Data_Chart.chartField.getOption();
            var dz = opt.dataZoom[0];
            var newStartIndex = dz.startValue;
            var newEndIndex = dz.endValue;
            var requestDate = '';
            var loadingOption = {
                text: '',
                color: '#fff',
                textColor: '#000',
                maskColor: 'rgba(0, 0, 0, 0)',
                zlevel: 0
            };
	    	Big_Data_Chart.startValue = Big_Data_Chart.data.xAxis[newStartIndex];
	        Big_Data_Chart.endValue = Big_Data_Chart.data.xAxis[newEndIndex];
	        if (Big_Data_Chart.previous.previousX && Big_Data_Chart.previous.previousX.length > 0 ) {
	        	if (!isPreviousContact && Big_Data_Chart.data.xAxis[0] != Big_Data_Chart.previous.previousX[0]) {
	        		
	        		Big_Data_Chart.data.xAxis = Big_Data_Chart.previous.previousX.concat(Big_Data_Chart.current.currentX);
	                $.each(Big_Data_Chart.previous.previousY, function (index, previousYData) {
	                    Big_Data_Chart.data.yAxis[index] = previousYData.concat(Big_Data_Chart.current.currentY[index])
	                })
	                Big_Data_Chart.chartField.setOption(getChartOption());
	                isPreviousContact = true;
	                
	        	}  
	        	/**小于50%时获取先前的数据*/
	        	if (res.batch[0].start<50) {
                	
 	                Big_Data_Chart.current.currentX = Big_Data_Chart.data.xAxis;
 	                Big_Data_Chart.previous.previousX = [];
 	               
 	                $.each(Big_Data_Chart.current.currentY, function (index,currentYData) {
 	                    Big_Data_Chart.current.currentY[index] = Big_Data_Chart.data.yAxis[index];	 	
 	                    Big_Data_Chart.previous.previousY[index] = [];
 	                })
 	                isPreviousContact = false;
 	                requestDate = getDate(Big_Data_Chart.data.xAxis[0],1);
 	               
	  	            if (Big_Data_Chart.trendType == 'week' || Big_Data_Chart.trendType == 'QC' || Big_Data_Chart.trendType == 'target') {
	  	                requestDate = getDate(Big_Data_Chart.weekRequestDate, 1);
	  	            } else if (Big_Data_Chart.trendType == 'tarffic-pick') {
	  	            	requestDate = Big_Data_Chart.weekRequestDate;
	  	            } 
	  	           
 	                Big_Data_Chart.getPreviousData(requestDate);
 	                Big_Data_Chart.isPrevious.isPreviousAjax = true;
                }
	        } else {	        	
	        	/**拉动过快时给出loading图标*/
	        	
	        	if (res.batch[0].start === 0 && Big_Data_Chart.isPrevious.isPreviousAjax) {   
	                Big_Data_Chart.chartField.showLoading(loadingOption);
	                return; 
	            }
	        } 
//	        if (Big_Data_Chart.next.nextX && Big_Data_Chart.next.nextX.length > 0) {
//	        	if (!isNextContact && Big_Data_Chart.data.xAxis[Big_Data_Chart.data.xAxis.length - 1] != Big_Data_Chart.next.nextX[Big_Data_Chart.next.nextX.length - 1]) {
//	        		Big_Data_Chart.data.xAxis = Big_Data_Chart.current.currentX.concat(Big_Data_Chart.next.nextX);
//		            $.each(Big_Data_Chart.next.nextY, function (index, nextYData) {
//		                Big_Data_Chart.data.yAxis[index] = Big_Data_Chart.current.currentY[index].concat(nextYData);
//		            })
//		            Big_Data_Chart.chartField.setOption(getChartOption());
//		            isNextContact = true;
//	        	}
//	        	/**大于50%时获取较后的数据*/
//	        	if (res.batch[0].end > 50) {	        	
//		             Big_Data_Chart.current.currentX = Big_Data_Chart.data.xAxis;
//		             Big_Data_Chart.next.nextX = [];
//		             $.each(Big_Data_Chart.current.currentY, function (index, currentYData){
//		                 Big_Data_Chart.current.currentY[index] = Big_Data_Chart.data.yAxis[index]; 
//		                 Big_Data_Chart.next.nextY[index] = [];
//		             });
//		             isNextContact = false;
//		             Big_Data_Chart.getNextData(getDate(Big_Data_Chart.data.xAxis[Big_Data_Chart.data.xAxis.length -1],Big_Data_Chart.xDataNum,true));
//		             Big_Data_Chart.isNext.isNextAjax = true;
//	        	}
//	        } else {
//	        	/**拉动过快时给出loading图标*/
//	        	if (res.batch[0].end === 100 && Big_Data_Chart.isNext.isNextAjax) {
//	        		Big_Data_Chart.chartField.showLoading(loadingOption);
//	                return;
//	        	}
//	        }
    	})
    }
    /**准备下一个数据*/
    var prepareNextData = function (dataX, dataY) {
        Big_Data_Chart.next.nextX = dataX;
        $.each(dataY, function (index, nextDataY) {
            Big_Data_Chart.next.nextY[index] = nextDataY;
        })
        Big_Data_Chart.isNext.isNextExist = true;
        Big_Data_Chart.isNext.isNextAjax = false;
        Big_Data_Chart.chartField.hideLoading();
    }
    /**准备先前的数据*/
    var preparePreviousData = function preparePreviousData(dataX, dataY, weekRequestDate) {
        Big_Data_Chart.previous.previousX = dataX;
        $.each(dataY, function (index, previousDataY) {
            Big_Data_Chart.previous.previousY[index] = previousDataY;
        })
       
        if (Big_Data_Chart.trendType == 'week' || Big_Data_Chart.trendType == 'tarffic-pick' || Big_Data_Chart.trendType == 'QC' || Big_Data_Chart.trendType == 'target') {
        	Big_Data_Chart.weekRequestDate = weekRequestDate;
        }
        Big_Data_Chart.isPrevious.isPreviousExist = true;
        Big_Data_Chart.isPrevious.isPreviousAjax = false;
        Big_Data_Chart.chartField.hideLoading();
    }
    /**设置曲线参数*/
    function formatterSeries (series, data) {
        var markLineX = [];
        $.each(data.xAxis, function(index, xAxisData) {
            var markLine = {xAxis:xAxisData};
            markLineX.push(markLine);
        });
        var seriesData = [];
        seriesData.push({
            type:'line',
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

        });
        $.each(data.yAxis, function (index, yAxisData) {

            seriesData.push({
                //smooth:true,
                type:'line',
                name:series.seriesName[index],
                color:[series.seriesColor[index]],
                data:yAxisData,
                symbol:series.seriesSymbol[index],
                animation:false,
                symbolSize:'7',
                connectNulls:true,
                zlevel:1
            })
        });
        return seriesData;
    }
    /**设置chart参数*/
    function getChartOption () {
        var yAxisTickLength = $(window).width() * 14/100 ;
        var seriesData = formatterSeries(Big_Data_Chart.series, Big_Data_Chart.data);
        var option = {
            grid:{
                show:false,
                left: '1%',
                right: '5%',
                bottom: Big_Data_Chart.gridBottom,
                top:'0%',
                containLabel: true,
                backgroundColor:'#27272f'
            },
            tooltip:{
                show:true,
                trigger: 'axis',

            },
            xAxis:{
                show:Big_Data_Chart.isXShow,
                type:'category',
                data:Big_Data_Chart.data.xAxis,
                axisLine: {
                    show:false,
                },
                axisTick:{
                    show:false,
                },
                boundaryGap:true,
                axisLabel:{
                    formatter: Big_Data_Chart.getXFormatter,
                    inside:false,
                    margin:Big_Data_Chart.xMargin,
                    show:'show',
                    textStyle: {
                        color:'#525257'
                    },
                    fontSize:'0.6em'

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
                    length:Big_Data_Chart.yAxisTickLength,
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
                    formatter: Big_Data_Chart.getYFormatter,
                    inside:false,
                    margin:Big_Data_Chart.yMargin,
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
                    startValue: Big_Data_Chart.startValue,
                    endValue:Big_Data_Chart.endValue,
                    show:false,
                    maxValueSpan:7,
                    minValueSpan:7,

                },
            ],
            series: seriesData,
        }
        return option;
    }
    /**获取时间ajax时间*/
    function getDate(dateNow, interval, isEnd){
    	if (!dateNow) {
    		return;
    	}
        //dateNow = dateNow.replace(/-/g,"/");
        var dateNowArr = dateNow.split('-');
    	var yearDate = dateNowArr[0] ? dateNowArr[0] : new Date().getFullYear();
        var monthDate = dateNowArr[1] ? dateNowArr[1] : '01';
        var dayDate = dateNowArr[2] ? dateNowArr[2] : '01';
        var date = new Date(yearDate,monthDate*1 -1,dayDate ); 
        if (isEnd) {
        	var lastWeekDate = new Date(date.getTime() + interval * 24 * 3600 * 1000);
        } else {
        	var lastWeekDate = new Date(date.getTime() - interval * 24 * 3600 * 1000);
        }
        if (lastWeekDate.getTime() > new Date().getTime()) {
        	lastWeekDate = new Date( new Date().getTime() - 1 * 24 * 3600 * 1000);
        }
        var oYear = lastWeekDate.getFullYear();
        var oMonth = lastWeekDate.getMonth() + 1;
        var oDay = lastWeekDate.getDate();
        var oHour = lastWeekDate.getHours();
        
        return oYear + '-' + zero(oMonth) + '-' + zero(oDay);
    }
    /**小于10的时间前+0*/
    function zero(n){
        return n<10 ? '0' + n : n;
    }
    /**设置曲线的default值*/
    var setDefaultSeries  = function (defaultSeries) {
    	if (!defaultSeries || !defaultSeries.seriesColor ||　!defaultSeries.eriesName　|| !defaultSeries.seriesSymbol) {
    		return;
    	}
    	Chart_Series_Default.seriesColor =  defaultSeries.seriesColor;
    	Chart_Series_Default.eriesName =  defaultSeries.eriesName;
    	Chart_Series_Default.seriesSymbol =  defaultSeries.seriesSymbol;
    }
    
    return BDC = {
        doChart: doChart,
        preparePreviousData:preparePreviousData,
        prepareNextData:prepareNextData,
        prepareCurrentData:prepareCurrentData,
        setDefaultSeries: setDefaultSeries
    }
})()