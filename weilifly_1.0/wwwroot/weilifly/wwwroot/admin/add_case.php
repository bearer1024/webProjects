<?php
session_start();
if (!isset($_SESSION['username'])) {
	header("refresh:0;url=admin_login.php");
	}
if (isset($_POST['submit'])) {
	$connect = MYSQL_CONNECT( "localhost","weilifly","szwlkj2013") or die("Unable to connect to MySQL server");
	mysql_select_db( "weilifly") or die("Unable to select database");
	$sql="INSERT INTO succeed_case (case_name,gongsi_name,case_introduce,type)
	VALUES
	('$_POST[case_name]','$_POST[gongsi_name]','$_POST[case_introduce]','$_POST[type]')";
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
	<form action="add_case.php" method="post" enctype="multipart/form-data">
		<ul>
			<li><h3>添加成功案例</h3></li>
			<li>项目名称: <input type="text" name="case_name" /></li>
			<li>服务公司: <input type="text" name="gongsi_name"/></li>
			<li>项目简介: <textarea cols="45" rows="15"  id="case_introduce" name="case_introduce"></textarea>
				<script type="text/javascript">     CKEDITOR.replace( 'case_introduce',     {     filebrowserBrowseUrl : 'ckeditor/ckfinder/ckfinder.html',     filebrowserImageBrowseUrl : 'ckeditor/ckfinder/ckfinder.html?Type=Images',     filebrowserFlashBrowseUrl : 'ckeditor/ckfinder/ckfinder.html?Type=Flash',     filebrowserUploadUrl : 'ckeditor/ckfinder/core/connector/php/connector.php?command=QuickUpload&type=Files',     filebrowserImageUploadUrl : 'ckeditor/ckfinder/core/connector/php/connector.php?command=QuickUpload&type=Images',     filebrowserFlashUploadUrl : 'ckeditor/ckfinder/core/connector/php/connector.php?command=QuickUpload&type=Flash'     });</script>
			</li>
			<li>案例分类：
				<select name="type">
				  <option value ="全国知名案例">全国知名案例</option>
				  <option value ="合作伙伴成功案例">合作伙伴成功案例</option>
				  <option value="独家落地转化案例">独家落地转化案例</option>
				</select>
			</li>
			<li><input type="submit" name="submit" value="添加按钮"></li>
		</ul>
	</form>
</div>
</body>
</html>