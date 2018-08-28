<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../layui/css/layui.css">
<link rel="stylesheet" href="../css/2.css">
<link rel="stylesheet" href="../layer/theme/default/layer.css">
<script type="text/javascript" src="../js/jquery-3.3.1.js"></script>
<script type="text/javascript" src="../layer/layer.js"></script>
</head>

<body>
<div id="fileimg" style="text-align:center;margin:0 auto;background:#82BDE1;height:400px;height:auto">

<form style="height:180px;position: absolute;top:50px;left:60px" id="form2" action="a.upload" method="post" enctype="multipart/form-data">
	<input style="width:180px;left:20px;height:36px"  type='file' name='pic' id="pic" value="点击浏览"><br />
	<button class="layui-btn" style="maegin:20px" type="submit" id="btn3">上传图片</button>
</form>
<div id="msg"></div>
<!-- 
<div class="layui-upload">
  <button type="button" class="layui-btn" id="test1">上传图</button>
  <div class="layui-upload-list">
    <img class="layui-upload-img" id="demo1">
    <p id="demoText"></p>
  </div>
</div>  
-->
</div>
<script type="text/javascript">
$(function(){
		$("form").submit(function(){
			alert("上传成功");
			var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
			parent.layer.close(index); //再执行关闭 
			$("body",parent.document).find("#pic").val("上传成功!");
		})
})
</script>

</body>
</html>