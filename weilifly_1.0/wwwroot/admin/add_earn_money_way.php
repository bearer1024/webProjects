<?php
session_start();
if (!isset($_SESSION['username'])) {
	header("refresh:0;url=admin_login.php");
	}
if (isset($_POST['submit'])) {
	$connect = MYSQL_CONNECT( "localhost","weilifly","szwlkj2013") or die("Unable to connect to MySQL server");
	mysql_select_db( "weilifly") or die("Unable to select database");
	$sql="INSERT INTO earn_money (way,detailed,type,beizhu)
	VALUES
	('$_POST[way]','$_POST[detailed]','$_POST[type]','$_POST[beizhu]')";
	mysql_query("set names utf8"); 
	if (!mysql_query($sql,$connect))
	  {
	  die('Error: ' . mysql_error());
	  }
	echo "插入了一条记录！";
	MYSQL_CLOSE();
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
	left: 300px;
	border:1px #003366 solid;
	height: 500px;
	width: 700px;
}
</style>
</head>
<body>
<div id="myform">
	<form action="add_earn_money_way.php" method="post" enctype="multipart/form-data">
		<ul>
			<li><h3>添加赚钱方式</h3></li>
			<li>赚钱方法: <input type="text" name="way" /></li>
			<li>赚钱方式（详细）:<textarea cols="45" rows="15"  id="detailed" name="detailed"></textarea>
				<script type="text/javascript">     CKEDITOR.replace( 'detailed',     {     filebrowserBrowseUrl : 'ckeditor/ckfinder/ckfinder.html',     filebrowserImageBrowseUrl : 'ckeditor/ckfinder/ckfinder.html?Type=Images',     filebrowserFlashBrowseUrl : 'ckeditor/ckfinder/ckfinder.html?Type=Flash',     filebrowserUploadUrl : 'ckeditor/ckfinder/core/connector/php/connector.php?command=QuickUpload&type=Files',     filebrowserImageUploadUrl : 'ckeditor/ckfinder/core/connector/php/connector.php?command=QuickUpload&type=Images',     filebrowserFlashUploadUrl : 'ckeditor/ckfinder/core/connector/php/connector.php?command=QuickUpload&type=Flash'     });     </script>
						</li>
			<li>赚钱分类：
				<select name="type">
				  <option value ="WiFi">WiFi</option>
				  <option value ="微信">微信</option>
				  <option value="企业APP">企业APP</option>
				  <option value="二维码">二维码</option>
				</select>
			</li>
			<li>备注：<input type="text" name="beizhu"></li>
			<li><input type="submit" name="submit" value="添加按钮"></li>
		</ul>
	</form>
</div>
</body>
</html>