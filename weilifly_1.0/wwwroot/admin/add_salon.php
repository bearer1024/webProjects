<?php
session_start();
if (!isset($_SESSION['username'])) {
	header("refresh:0;url=admin_login.php");
}
if (isset($_POST['submit'])) {
	$form_data_type = $_FILES['form_data']['type'];
	$form_data = $_FILES['form_data']['tmp_name'];
	$connect = MYSQL_CONNECT( "localhost","weilifly","szwlkj2013") or die("Unable to connect to MySQL server");
	mysql_select_db( "weilifly") or die("Unable to select database");
	$data = addslashes(fread(fopen($form_data,"r"), filesize($form_data)));
	//echo "mysqlPicture="$data;
	$sql="INSERT INTO salon (picture,picture_type,salon_host,host_info,salon_topic,salon_jianjie,salon_time,endtime,salon_address,salon_type,phone)
	VALUES
	('$data','$form_data_type','$_POST[host]','$_POST[host_infoa]','$_POST[topic]','$_POST[topic_jianjie]','$_POST[time]','$_POST[endtime]','$_POST[address]','$_POST[type]','$_POST[phone]')";
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
	<form action="add_salon.php" method="post" enctype="multipart/form-data">
		<ul>
			<li><h3>添加沙龙预告</h3></li>
			<li>沙龙主题: <input type="text" name="topic" /></li>
			<li>沙龙简介: <textarea cols="20" rows="5" name="topic_jianjie"></textarea></li>
			<li>宣传海报: <input type="file" name="form_data" size="40"/><div>受服务器和空间限制，建议图片大小不要超过300KB！</div></li>
			<li>举行日期: <input type="datetime-local" name="time" /></li>
			<li>截止日期: <input type="datetime-local" name="endtime" /></li>
			<li>沙龙主讲人：<input type="text" name="host" /></li>
			<li>沙龙主讲人简介：<textarea cols="20" rows="5" name="host_info"></textarea></li>
			<li>沙龙分类：
				<select name="type">
				  <option value ="最近">最近</option>
				  <option value ="季度">季度</option>
				  <option value="年度">年度</option>
				</select>
			</li>
			<li>沙龙地址：<input type="text" name="address"></li>
			<li>联系方式：<input type="text" name="phone"></li>
			<li><input type="submit" name="submit" value="添加按钮"></li>
		</ul>
	</form>
</div>
</body>
</html>