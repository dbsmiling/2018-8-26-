<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>修改</title>
<link rel="stylesheet" href="../layer/theme/default/layer.css">
<script type="text/javascript"  src="../layer/mobile/layer.js"></script>
<link rel="stylesheet" href="../layui/css/layui.css">
<script type="text/javascript" src="../js/jquery-3.3.1.js"></script>
</head>
<% 
String id = request.getParameter("cid");
String name = request.getParameter("cname");
String pwd = request.getParameter("cpwd");
String sex = request.getParameter("csex");
String bir = request.getParameter("cbir");
String email = request.getParameter("cemail");
String phone = request.getParameter("cphone");
String total = request.getParameter("ctotal");

%>
<body style="text-align:center;margin:0 auto;width:280px;height:150px;">
<form>
<div class="layui-form-item">
		<label class="layui-form-label">姓名：</label>
	    <div class="layui-input-block">
	      <input value="<%=name %>" type="text" name="name" placeholder="请输入姓名" autocomplete="off" class="layui-input">
	    </div>
    </div>
	<div class="layui-form-item">
		<label class="layui-form-label">密码：</label>
	    <div class="layui-input-block">
	      <input value="<%=pwd %>" type="text" name="pwd" placeholder="输入密码" autocomplete="off" class="layui-input">
	    </div>
    </div>
	<div class="layui-form-item">
		<label class="layui-form-label">性别：</label>
	    <div class="layui-input-block">
	      <input value="<%=sex %>" type="text" name="sex"  placeholder="(男/女)" autocomplete="off" class="layui-input">
	    </div>
    </div>
	<div class="layui-form-item">
		<label class="layui-form-label">生日：</label>
	    <div class="layui-input-block">
	      <input value="<%=bir %>" type="text" name="bir"  placeholder="(yyyy/mm/ss)" autocomplete="off" class="layui-input">
	    </div>
    </div>
	<div class="layui-form-item">
		<label class="layui-form-label">电子邮箱：</label>
	    <div class="layui-input-block">
	      <input value="<%=email %>" type="text" name="email"  placeholder="email" autocomplete="off" class="layui-input">
	    </div>
    </div>
    	<div class="layui-form-item">
		<label class="layui-form-label">手机号：</label>
	    <div class="layui-input-block">
	      <input value="<%=phone %>" type="text" name="phone"  placeholder="1**********" autocomplete="off" class="layui-input">
	    </div>
    </div>
    </div>
    	<div class="layui-form-item">
		<label class="layui-form-label">积分：</label>
	    <div class="layui-input-block">
	      <input value="<%=total %>" type="text" name="total"  placeholder="修改积分" autocomplete="off" class="layui-input">
	    </div>
    </div>
	<input type="hidden" name="id" value="<%=id%>"/>
</form>
<button class="layui-btn" id="btn">提交</button>
<script type="text/javascript">
	$(function(){
		
		$("button#btn").click(function(){
			$.post("a.userupd",$("form").serialize(),function(data){
				var da = JSON.parse(data);
				if(da.code=="1"){
					alert("修改成功");
					parent.location.reload();
				}else{
					alert("修改未成功");
				}
			})
		})
	})

</script>
</body>
</html>