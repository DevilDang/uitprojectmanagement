var environment = {
   list_requirment: []
};

function drawLine(ctx, x1, y1, x2, y2, thickness) {    
  ctx.beginPath();
  ctx.moveTo(x1,y1);
  ctx.lineTo(x2,y2);
  ctx.lineWidth = thickness;
  ctx.strokeStyle = "black";
  ctx.stroke();
}

function drawOxy(ctx,array, deltaX, deltaY)
{
	// ve ten truc tung
	ctx.fillText("Process", 10 + deltaX, 10  + deltaY);
     // ve truc tung
	 drawLine(ctx, 50 + deltaX, 20 + deltaY, 50 + deltaX, 420+ deltaY, 1);
	 // ve c�c muc cua truc tung
	 for(var i = 0;i<10;i++)
	 {
		drawLine(ctx, 45 + deltaX, i*40 + 20+ deltaY, 55+ deltaX, i*40 + 20+ deltaY, 1);
		ctx.fillText((100 - i*10)+"%", 5+ deltaX, i*40 + 30+ deltaY);
	 }
	 
	 // ve truc hoanh
	 drawLine(ctx, 50+ deltaX, 420+ deltaY, 460 + deltaX, 420+ deltaY, 1);
	 // ve cac cot
	 for(var i = 0;i<array.length;i++)
	 {
		drawLine(ctx, i*50 + 90+ deltaX, 420 - array[i]*4+ deltaY, i*50 + 90+ deltaX, 420+ deltaY, 20);
		
		drawLine(ctx, i*50 + 90+ deltaX, 415+ deltaY, i*50 + 90+ deltaX, 425+ deltaY, 1);
		
		ctx.fillText("YC"+( i+ 1), i*50 + 75+ deltaX, 450+ deltaY);
	 }
	 
}


function drawchart(ctx)
{
	var canvas_bg = document.getElementById("bg");
	ctx = canvas_bg.getContext("2d");
	ctx.fillStyle = "black";
	
	ctx.font = '15px Arial';
    ctx.textAlign = "left";
     
     var array = new Array(50,60,70,80,90,50,60,70,80);
	 
	drawOxy(ctx,array,0,10);
}

$(function(){
	setInterval(drawchart,10);   
});