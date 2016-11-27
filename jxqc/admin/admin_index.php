<?php
	session_start();
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"/>
<title>清后台管理页面</title>
<link href="css.css" rel="stylesheet" type="text/css" />
</head>
<body>

<div class="top_img" align="right">
  <table width="38%" border="0" cellpadding="0" cellspacing="0" style="margin-top:26px; color:#FF6E00;">
  </table>
  <div style="color:black;font-size:30px;margin:30px;">
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

<div class="mid">
	<div class="left2">
	  <div class="left2_1"><img src="images/left2_01.jpg" /></div>
	  <div class="left2_2">
	     <ul>
		   <li><a href="add_news.php" target="myframe">添加行业动态</a></li>
		   <li><a href="add_vip.php" target="myframe">添加会员信息</a></li>
		   <li><a href="add_tongji.php" target="myframe">添加统计信息</a></li>
		   <li><a href="add_peixun.php" target="myframe">添加培训信息</a></li>
		 </ul>

		  <ul>
		   <li><a href="del_news.php" target="myframe">删除行业动态</a></li>
		   <li><a href="del_vip.php" target="myframe">删除会员信息</a></li>
		   <li><a href="del_tongji.php" target="myframe">删除统计信息</a></li>
		   <li><a href="del_peixun.php" target="myframe">删除培训信息</a></li>
		 </ul>
		 <ul>
		   <li><a href="del_huiyuan.php" target="myframe">商城会员管理</a></li>
		   <li><a href="del_chanpin.php" target="myframe">商城产品管理</a></li>
		 </ul>
	  </div>
	  <div class="left2_1"><img src="images/left2_02.jpg" /></div>
	</div>
  </div>
  
  <div class="right">
    <iframe name="myframe" width="99%" height="99%" style="border:0px"/>
  </div>
</div>

<div style=" clear:both;"></div>
<div class="btt" align="center">
</div>
</body>
</html>
