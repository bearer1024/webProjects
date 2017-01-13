<?php
session_start();
if (!isset($_SESSION['username'])) {
	header("refresh:0;url=admin_login.php");
	}
if (isset($_POST['submit'])) {
	$connect = MYSQL_CONNECT( "localhost","weilifly","szwlkj2013") or die("Unable to connect to MySQL server");
	mysql_select_db( "weilifly") or die("Unable to select database");
	//echo "mysqlPicture="$data;
	$sql="INSERT INTO out_concept (out_name,mean,source)
	VALUES
	('$_POST[title]','$_POST[mean]','$_POST[source]')";
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
	<form action="add_out_concept.php" method="post" enctype="multipart/form-data">
		<ul>
			<li><h3>添加不懂就落伍的概念</h3></li>
			<li>概念词条: <input type="text" name="title" /></li>
			<li>概念含义: <textarea cols="20" rows="5" name="mean"></textarea></li>
			<li>概念来源: <textarea cols="20" rows="5" name="source"></textarea></li>
			<li><input type="submit" name="submit" value="添加按钮"></li>
		</ul>
	</form>
</div>
</body>
</html>