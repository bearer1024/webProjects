<?php
session_start();
?>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>后台管理系统</title>
<script language="javascript" src="jquery/jquery.js"></script>
<style type="text/css">
<!--
* { 
	margin:0px; 
	padding:0px;
}
html, body{	
	height:100%;
	overflow: hidden;

}
html>body{		/*-- for !IE6.0 --*/
	width: auto;
	height: auto;
	position: absolute;
	top: 0px;
	left: 0px;
	right: 0px;
	bottom: 0px;	
}
body {
	border:8px solid #ffffff;
	background-color: #ffffff;min-width:230px;
}
#mainDiv {
	width: 100%;
	height: 100%;
    padding:60px 0px 25px 0px;;
}
#centerDiv{
	width: 100%;
	height: 100%;
	background-color:#00CCFF;
	padding-left:158px;
}
#mainDiv>#centerDiv{		/*-- for !IE6.0 --*/	
	width: auto;
	height: auto;
	position: absolute;
	top: 70px;
	left: 0px;
	right: 0px;
	bottom: 20px;
	
}
#left{
width:200px;
height:100%;
background:url(images/slide.jpg) repeat-y;
position:absolute;
left:0px;
}
#lhead{
background:url(images/left-head.jpg) left top repeat-x;
height:25px;
width:158px;
	font-size:14px;
	color:#FF9933;
    text-align:center;
	line-height:25px;
}
#right{
width:100%;
height:100%;
background:#ffffff;
position:absolute;
overflow-y:auto;
border:1px #003366 solid;
padding:20px 0 0 10px;
font-size:12px;
font-family:"宋体";
}
#centerDiv>#right{
width:auto;
height:auto;
position:absolute;
top:0px;
right:0px;
left:158px;
bottom:0px;
}
#topDiv{
	width:100%;
	height:70px;
	background-color:#FF6666;
	position:absolute;
	top:0px;
	overflow:hidden;
}
#bottomDiv{
	width:100%;
	height:20px;
	background:url(images/bottom.jpg) repeat-x;
	position:absolute;
	bottom:0px;
	bottom:-1px;		 /*-- for IE6.0 --*/
}
#left ul{
list-style:none;
font-size:12px;
margin:20px 0 0 8px;
}
#left ul li a{
text-decoration: none;
display:block;
width:140px;
height:25px;
line-height:25px;
background:url(images/menu-bg.jpg) repeat-x;
color:#FFFFFF;
direction:none;
text-align:center;
border-bottom:1px #000066 solid;
border:1px #06597D solid;
}
#left ul li a:hover{
background:url(images/menu-bg.jpg) 0px 25px;
color:#99FFCC;
direction:none;
text-align:center;
border-bottom:1px #000066 solid;
}
h2{
color: #000000;
}
-->
</style>
</head>
<body>
<div id="mainDiv">
	<div id="topDiv">
		<h2 align="center">深圳微力科技有限公司网站后台管理页面</h2>
		<div align="right">
			<?php
				if (!isset($_SESSION['username'])) {
					header("refresh:0;url=admin_login.php");
				}else{
				echo "欢迎您:".$_SESSION['username']."";
				echo "  <a href='admin_logout.php'>退出</a>";
				}
			?>
		</div>
	</div>
	<div id="centerDiv">
		<div id="left">
			<div id="lhead">管理菜单</div>
			<ul>
			<li><a href="add_news.php" target="myframe">发布新闻动态</a></li>
			<li><a href="add_salon.php" target="myframe">发布沙龙预告</a></li>
			<li><a href="add_out_concept.php" target="myframe">干货管理-不懂就落伍概念</a></li>
			<li><a href="add_weixin_course.php" target="myframe">干货管理-微信营销教程</a></li>
			<li><a href="add_ganhuo_guo.php" target="myframe">干货管理-郭雄辉原创</a></li>
			<li><a href="add_earn_money_way.php" target="myframe">赚钱利器</a></li>
			<li><a href="add_fuwu.php" target="myframe">我们的服务</a></li>
			<li><a href="add_case.php" target="myframe">添加成功案例</a></li>
			<li><a href="add_luodi.php" target="myframe">添加落地转化案例</a></li>
			<li><a href="del_news.php" target="myframe">删除新闻</a></li>
			<li><a href="del_salon.php" target="myframe">删除沙龙</a></li>
			<li><a href="del_out_concept.php" target="myframe">删除不懂就落伍概念</a></li>
			<li><a href="del_weixin_course.php" target="myframe">删除微信营销教程</a></li>
			<li><a href="del_ganhuo_guo.php" target="myframe">删除郭雄辉原创文章</a></li>
			<li><a href="del_earn_money_way.php" target="myframe">删除赚钱利器</a></li>
			<li><a href="del_fuwu.php" target="myframe">删除我们的服务</a></li>
			<li><a href="del_case.php" target="myframe">删除成功案例</a></li>
			<li><a href="del_luodi.php" target="myframe">删除落地转化案例</a></li>
			</ul>
		</div>
		<div id="right"> 
			 <iframe name="myframe" width="99%" height="99%" />
		</div>
	</div>
	<div id="bottomDiv">
	</div>
</div>
</body>
</html>