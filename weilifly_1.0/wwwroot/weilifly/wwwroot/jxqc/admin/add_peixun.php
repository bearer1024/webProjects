<?php
session_start();
//if (!isset($_SESSION['username'])) {
	//header("refresh:0;url=admin_login.php");
	//}
if (isset($_POST['submit'])) {
	include_once("connect.php");
	$sql="INSERT INTO xh_peixun (title,neirong,author,time)
	VALUES
	('$_POST[name]','$_POST[content]','$_POST[author]','$_POST[time]')";
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
	<form action="add_peixun.php" method="post" enctype="multipart/form-data">
		<ul>
			<li><h3>添加培训信息</h3></li>
			<li>标题: <input type="text" name="name" /></li>
			
			<li>详细内容：<textarea cols="45" rows="15"  id="content" name="content"></textarea>
				<!--<script type="text/javascript">
                	CKEDITOR.replace( 'content' );
            	</script>-->
            	<script type="text/javascript">     CKEDITOR.replace( 'content',     {     filebrowserBrowseUrl : 'ckeditor/ckfinder/ckfinder.html',     filebrowserImageBrowseUrl : 'ckeditor/ckfinder/ckfinder.html?Type=Images',     filebrowserFlashBrowseUrl : 'ckeditor/ckfinder/ckfinder.html?Type=Flash',     filebrowserUploadUrl : 'ckeditor/ckfinder/core/connector/php/connector.php?command=QuickUpload&type=Files',     filebrowserImageUploadUrl : 'ckeditor/ckfinder/core/connector/php/connector.php?command=QuickUpload&type=Images',     filebrowserFlashUploadUrl : 'ckeditor/ckfinder/core/connector/php/connector.php?command=QuickUpload&type=Flash'     });     </script>
			</li>
			<li>作者: <input type="text" name="author" /></li>
			<li>添加日期: <input type="datetime-local" name="time" /></li>
			<li><input type="submit" name="submit" value="添加按钮"></li>
		</ul>
	</form>
</div>
</body>
</html>