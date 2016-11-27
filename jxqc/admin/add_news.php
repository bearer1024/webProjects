<?php
session_start();
//if (!isset($_SESSION['username'])) {
	//header("refresh:0;url=admin_login.php");
	//}
if (isset($_POST['submit'])) {
	include_once("connect.php");
	$sql="INSERT INTO xh_news (title,neirong,time,laiyuan,author,type)
	VALUES
	('$_POST[title]','$_POST[content]','$_POST[time]','$_POST[laiyuan]','$_POST[author]','$_POST[type]')";
	mysqli_query($con,"set names utf8"); 
	if (!mysqli_query($con,$sql))
	  {
	  die('Error: ' . mysql_error());
	  }
	echo "插入了一条记录！";
	mysqli_close($con);
	} 
?>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.0/jquery.min.js">
</script>
<script type="text/javascript" src="ckeditor/ckeditor.js"></script>
<style type="text/css">
li{
	list-style: none;
}
body{
	position: absolute;
	top: 0px;
	left: 0px;
	border:1px #003366 solid;
	height: 560px;
	width: 690px;
}
</style>
</head>
<body>
<div id="myform">
	<form action="add_news.php" method="post" enctype="multipart/form-data">
		<ul>
			<li><h3>添加新闻</h3></li>
			<li>新闻标题: <input type="text" name="title" /></li>
			<li>新闻日期: <input type="datetime-local" name="time" /></li>
			<li>新闻内容：<textarea cols="45" rows="15"  id="content" name="content"></textarea>
				<!--<script type="text/javascript">
                	CKEDITOR.replace( 'content' );
            	</script>-->
            	<script type="text/javascript">     CKEDITOR.replace( 'content',     {     filebrowserBrowseUrl : 'ckeditor/ckfinder/ckfinder.html',     filebrowserImageBrowseUrl : 'ckeditor/ckfinder/ckfinder.html?Type=Images',     filebrowserFlashBrowseUrl : 'ckeditor/ckfinder/ckfinder.html?Type=Flash',     filebrowserUploadUrl : 'ckeditor/ckfinder/core/connector/php/connector.php?command=QuickUpload&type=Files',     filebrowserImageUploadUrl : 'ckeditor/ckfinder/core/connector/php/connector.php?command=QuickUpload&type=Images',     filebrowserFlashUploadUrl : 'ckeditor/ckfinder/core/connector/php/connector.php?command=QuickUpload&type=Flash'     });     </script>
			</li>
			<li>新闻分类：
				<select name="type">
				  <option value ="1">协会公告</option>
				  <option value ="2">政策发布</option>
				  <option value="3">行业资讯</option>
				  <option value="4">海外新闻</option>
				</select>
			</li>
			<li>新闻作者：<input type="text" name="author"></li>
			<li>新闻来源：<input type="text" name="laiyuan"></li>
			<li><input type="submit" name="submit" value="添加按钮"></li>
		</ul>
	</form>
</div>
</body>
</html>