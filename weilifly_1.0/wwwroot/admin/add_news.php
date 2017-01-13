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
	$sql="INSERT INTO news (title,picture,picture_type,time, news_content,news_type,author)
	VALUES
	('$_POST[title]','$data','$form_data_type','$_POST[time]','$_POST[content]','$_POST[type]','$_POST[author]')";
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
	<form action="add_news.php" method="post" enctype="multipart/form-data">
		<ul>
			<li><h3>添加新闻</h3></li>
			<li>新闻标题: <input type="text" name="title" /></li>
			<li>新闻图片: <input type="file" name="form_data" size="40"/><div>受服务器和空间限制，建议图片大小不要超过300KB！</div></li>
			<li>新闻日期: <input type="datetime-local" name="time" /></li>
			<li>新闻内容：<textarea cols="45" rows="15"  id="content" name="content"></textarea>
				<!--<script type="text/javascript">
                	CKEDITOR.replace( 'content' );
            	</script>-->
            	<script type="text/javascript">     CKEDITOR.replace( 'content',     {     filebrowserBrowseUrl : 'ckeditor/ckfinder/ckfinder.html',     filebrowserImageBrowseUrl : 'ckeditor/ckfinder/ckfinder.html?Type=Images',     filebrowserFlashBrowseUrl : 'ckeditor/ckfinder/ckfinder.html?Type=Flash',     filebrowserUploadUrl : 'ckeditor/ckfinder/core/connector/php/connector.php?command=QuickUpload&type=Files',     filebrowserImageUploadUrl : 'ckeditor/ckfinder/core/connector/php/connector.php?command=QuickUpload&type=Images',     filebrowserFlashUploadUrl : 'ckeditor/ckfinder/core/connector/php/connector.php?command=QuickUpload&type=Flash'     });     </script>
			</li>
			<li>新闻分类：
				<select name="type">
				  <option value ="微力">微力</option>
				  <option value ="趋势">趋势</option>
				  <option value="江西移动">江西移动</option>
				</select>
			</li>
			<li>新闻作者：<input type="text" name="author"></li>
			<li><input type="submit" name="submit" value="添加按钮"></li>
		</ul>
	</form>
</div>
</body>
</html>