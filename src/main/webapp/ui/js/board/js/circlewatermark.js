var cotentid = '<b:write property="contentid"/>' ;
var contentname = '<b:write property="contentname"/>' ;
var content = '<b:write property="contentid"/>' + '--' + '<b:write property="contentname"/>';
//需要拖动的div 和 长宽
var startX  = '' ;
var startY  = '' ;
var endX    = '' ;
var endY    = '' ;
//是否需要移动
var needmove = false ;
$(function(){
	 
	document.getElementById("watermark").addEventListener("touchstart", selectBall) ;
	document.getElementById("watermark").addEventListener("touchmove", moveBall) ;
	document.getElementById("watermark").addEventListener("touchend", function(){
	    event.preventDefault();
	    needmove=false; 
	});
	
	$(window).resize(function() {
	 
	    $("#watermark").css({"position": "fixed","right": "10px","left": "auto","top": "10px"});
	});
});


function selectBall(e){
	e.preventDefault();
	needmove = true ;
	startX = parseInt(e.targetTouches[0].pageX) - parseInt($("#watermark").offset().left) ; 					//鼠标和DIV的相对坐标   
	startY = parseInt(e.targetTouches[0].pageY) - parseInt($("#watermark").offset().top);  
}

function moveBall(e){
	e.preventDefault();
	var clientW = $(window).width();
	var clientH = $(window).height();
	
	if(!needmove) {return;} 
	endX = parseInt(e.targetTouches[0].pageX) ;
	endY = parseInt(e.targetTouches[0].pageY) ;
	
	var x = endX - startX ;              
    var y = endY - startY ;      
    if(x<0) 
    	x = 0 ;
    if(y<0) 
    	y = 0 ;
    if(x > clientW - $("#watermark").width() - 20 ) 
    	x = clientW - $("#watermark").width() - 20;
	
	if(y > clientH - $("#watermark").height() - 20 ) 
    	y = clientH - $("#watermark").height() - 20;
	
    $("#watermark").css("left", x);              
    $("#watermark").css("top", y);  
}