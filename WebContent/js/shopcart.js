var j;
var obj;
$(function(){
	var num=0;
	var sum=0;
		$.ajax({
			type:"post",
			url:"aa.fan",
			async:true,
			dataType:"json",
			success:function(data){
				obj=data;
				j=Object.keys(obj).length;
				var buy=JSON.stringify(obj);
				if(j!=0){
					$("#goods").show();
					var m="<table class="+"shop_goods"+">";
					m+="<tr><td>商品id</td><td>图片</td><td>简介</td><td>单价</td><td>数量</td><td>小计</td><td>操作</td></tr>";
					for(var i=0;i<j;i++){
						m+="<tr><td>"+obj[(i+1)].goods[0].cid+"</td><td><img src=\""+obj[(i+1)].goods[0].cimgurl+"\" /></td><td>"+obj[(i+1)].goods[0].cremark+"</td><td>"+obj[(i+1)].goods[0].cprice+"</td><td>"+obj[(i+1)].snum+"</td><td>"+(parseInt(+obj[(i+1)].snum)*parseInt(obj[(i+1)].goods[0].cprice))+"</td><td><a style=\"color: black;font-size: 18px;\" href=\"feng.yi?id="+obj[(i+1)].goods[0].cid+"\">删除</a></td></tr>";
						sum+=(parseInt(+obj[(i+1)].snum)*parseInt(obj[(i+1)].goods[0].cprice));
						num+=parseInt(obj[(i+1)].snum);
					}
					m+="</table>";
					m+="<div id=\"info\"><div id=\"num\"><p>你选择了"+num+"件商品</p></div><div id=\"money\"><p>共计"+sum+"元</p></div>";
					m+="<div id=\"bg\"><button id=\"dj\" onclick=\"tijiao()\">提交</button></div></div>";
					$("#goods").append(m);
				}else{
					$("#cart").show();
				}
				},
				error:function(){
					$("#cart").show();
				}
				
			});
});

function tijiao(){
	$.ajax({
		type:"get",
		url:"aa.fan",
		async:true,
		dataType:"json",
		success:function(data){
			if(data.success==1){
				location.href="Order.html";
			}
		},
		error:function(){
			alert("添加失败");
		}
		
	});
}

	
	
	
