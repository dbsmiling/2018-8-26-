
var i=0;
var Timer;
$(function(){
	$(".picImg").eq(0).show().siblings().hide();
	//自动轮播
	TimerBanner();
	
	//点击红圈
	
	$(".tabs li").hover(function(){  //鼠标移动上去
		clearInterval(Timer);
		i=$(this).index();
		showPic();
	},function(){  //鼠标离开
		TimerBanner();
	});
	
	//点击左右按钮
	$(".btn1").click(function(){
		clearInterval(Timer);
		i--;
		if(i==-1){
			i=4;
		}
		showPic();
		TimerBanner();
	});
	$(".btn2").click(function(){
		clearInterval(Timer);
		i++;
		if(i==5){
			i=0;
		}
		showPic();
		TimerBanner();
	});
});
function TimerBanner(){
	Timer = setInterval(function(){
		i++;
		if(i==5){
			i=0;
		}
		showPic()
	},1000);
}
function showPic(){
	$(".picImg").eq(i).show().siblings().hide();
	$(".tabs li").eq(i).addClass("bg").siblings().removeClass("bg");
}