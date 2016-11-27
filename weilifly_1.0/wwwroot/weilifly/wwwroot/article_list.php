<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.0/jquery.min.js">
</script>
<style type="text/css">
a{
	text-decoration: none;
}
.login-register{
float:right;
margin-top:20px;
}
.login-register form{
	font-size:12px;
}
.login-register input{
	font-size:12px;
}
.login-register a{
font-family: Arial,'宋体';font-size:15px;
color:blue;
}
#reg{
	float: right;
}
.banner{
margin-top:4px;
}
.banner td{
width:110px;
height:57px;
}
.banner img{
margin:2px;
padding:4px;
width:100px;
height:50px;
}
.banner a{
font-size: 200px;
}
.banner a:hover{
	background-color: #66CC00;
}
#content{
	width: 1005px;
	border-top:4px solid #66CC00;
}
#left{
	position: fixed;
	top:170px;
	width: 250px;
	height: 300px;
	float: left;
	font-size: 14px;
}
#right{
	width: 700px;
	float: right;
}
#right img{
	height: 300px;
	width: 400px;
}
#tupian{
	padding-top: 20px;
}
#foot{
	width: 100%;
	height: 100px;
	background-color: #999999;
	padding-top: 20px;
}
</style>
</head>
<body background="white">
	<table align="center">
		<tr>
			<td>
				<a href="index.php"><img src="images/logo.jpg" style="margin-top:10px;margin-bottom:-5px;vertical-align:bottom"/></a>
			</td>
		</tr>
		<tr>
			<td align="left">
				<table class="banner" cellpadding="0px" cellspacing="0px">
				<tr>
						<td><a href="news.php"><img src="./images/wenzi_01.jpg"/></a></td>
						<td><a href="ganhuo.php"><img src="./images/wenzi_02.jpg"/></a></td>
						<td><a href="salon.php"><img src="./images/wenzi_03.jpg"/></a></td>
						<td><a href="earn_money.php"><img src="./images/wenzi_04.jpg"/></a></td>
						<td><a href="luodi.php"><img src="./images/wenzi_05.jpg"/></a></td>
						<td><a href="fuwu.php"><img src="./images/wenzi_06.jpg"/></a></td>
						<td><a href="zixun.php"><img src="./images/wenzi_07.jpg"/></a></td>
						<td><a href="showcase.php"><img src="./images/wenzi_08.jpg"/></a></td>
						<td><a href="bbs"><img src="./images/wenzi_09.jpg"/></a></td>
				</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<div id="content">
					<div id="left">
						微信公众平台：搜索“深圳微力科技”或扫描以下二维码
						<img src="images/weili_erweima.jpg" width="200px" height="200px">
					</div>
					<div id="right">
						<?php
							$con = mysql_connect("localhost","weilifly","szwlkj2013");
							if (!$con)
							  {
							  die('Could not connect: ' . mysql_error());
							  }
							mysql_select_db("weilifly", $con);
							mysql_query("set names utf8"); 
							if(isset($_GET['id'])){
								$id=$_GET['id'];
								$sql="SELECT head,content,time FROM original_article where article_id='$id'";
							}	
							$result = mysql_query($sql);
							While($row = mysql_fetch_array($result))
							  {
							  echo "<h1>".$row['head']."</h1>";	
							  echo $row['content'];
							  echo "</br>";
							  echo "时间：".$row['time']."";
							  }
							mysql_close($con);
							?>
					</div>
				</div>
			</td>
		</tr>
		<tr>
			<td>
				<div id="foot">
					<p align="center">深圳微力科技有限公司</p>
				</div>
			</td>
		</tr>
	</table>

</body>
</html>