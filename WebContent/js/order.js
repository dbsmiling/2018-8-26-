window.onload=function(){
	$.ajax({
		type:"get",
		url:"feng.action",
		async:false,
		dataType:"json",
		success:function(data){
			var obj=data;
			var m="<div id=\"adress_1\"><span>收货信息</span></div>";
			m+="<div id=\"shoujian\"><span>收件人：</span><span id=\"one\">"+obj.sjr+"</span></div>";
			m+="<div id=\"phone\"><span>联系方式：</span><span id=\"two\">"+data.tel+"</span></div>";
			m+="<div id=\"address\"><span>收货地址：</span><span id=\"three\">"+data.ad+"</span></div>";
			$("#ty").append(m);
		},
		error:function(){
				alert("错了");
		}
			
	});
}
var ordermoney=0;
$(function(){
		$.ajax({
			type:"post",
			url:"feng.action",
			async:false,
			dataType:"json",
			success:function(data){
				var obj=data;
				var j=Object.keys(obj).length;
					var m="<table class="+"shop_goods"+">";
					m+="<tr><td colspan=\"2\">商品信息</td><td>单价</td><td>数量</td><td>小计</td><td>实付</td></tr>";
					for(var i=0;i<j;i++){
						m+="<tr><td><img src=\""+obj[(i+1)].goods[0].cimgurl+"\" /></td><td>"+obj[(i+1)].goods[0].cremark+"</td><td>"+obj[(i+1)].goods[0].cprice+"</td><td>"+obj[(i+1)].cnum+"</td><td>"+obj[(i+1)].cmoney+"</td><td>"+obj[(i+1)].cmoney+"</td></tr>";
						ordermoney+=parseInt(obj[(i+1)].cmoney);
					}
					m+="</table>";
					m+="<div id=\"info\"><div id=\"money\"><p>共计"+ordermoney+"元</p></div></div>";
					m+="<div id=\"bg\"><button id=\"dj\" onclick=\"tijiao()\">付款</button></div>";
					$("#orderinfo").append(m);
			},
			error:function(){
					alert("返回中");
			}
				
		});
});

function tijiao(){
	$.ajax({
		type:"get",
		url:"aa.wcdd",
		async:true,
		dataType:"json",
		success:function(data){
			alert("付款成功");
			if(data.success==1){
//				location.href="个人中心.html";
			}
		},
		error:function(){
			alert("添加失败");
		}
		
	});
}