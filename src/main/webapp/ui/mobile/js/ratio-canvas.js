/**
 * Created by chen on 2016/7/16.
 */

    function roundCanvas(id,delR,linWid,value){
        var canvas = document.getElementById(id);
        var ctx = canvas.getContext("2d");
        var W = canvas.width;
        var H = canvas.height;
        var R = W/2-15;
        var deg=0,new_deg=0;

        function init(){
            ctx.clearRect(0,0,W,H);
            ctx.beginPath();
            ctx.strokeStyle="#555";
            ctx.lineWidth=linWid;
            ctx.lineCap="round";
            ctx.arc(W/2,H/2,R-delR,0-200*Math.PI/180,20*Math.PI/180,false);
            ctx.stroke();
            ctx.restore();

            var r = deg*Math.PI/180;
            ctx.beginPath();
            ctx.strokeStyle = "#dc2738";
            ctx.lineWidth=linWid;
            ctx.lineCap="round";
            ctx.arc(W/2,H/2,R-delR,0-200*Math.PI/180,r-200*Math.PI/180,false);
            ctx.stroke();
            ctx.restore();

            //ctx.clearRect(0,0,W,H);
            //ctx.beginPath();
            //ctx.strokeStyle="#f4f4f4";
            //ctx.lineWidth=2;
            //ctx.lineCap="round";
            //ctx.arc(W/2,H/2,R-20,0-200*Math.PI/180,20*Math.PI/180,false);
            //ctx.stroke();
            //ctx.restore();
            //
            //var r = deg*Math.PI/180;
            //ctx.beginPath();
            //ctx.strokeStyle = "#f3d133";
            //ctx.lineWidth = 2;
            //ctx.setLineDash([12,5]);
            //ctx.lineCap="round";
            //ctx.arc(W/2,H/2,R-10,0-200*Math.PI/180,r-200*Math.PI/180,false);
            //ctx.stroke();
            //ctx.restore();
            if(delR == 10) {
                ctx.setLineDash([1,18]);
            }

        }
        function unitTo(){
            new_deg=value;
            if(deg<new_deg){
                deg++;
            }
            init();
        }
        setInterval(unitTo);
    }






