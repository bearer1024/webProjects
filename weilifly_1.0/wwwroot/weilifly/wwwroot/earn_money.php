<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
<link rel="stylesheet" type="text/css" href="css/reset.css" />
<link rel="stylesheet" type="text/css" href="css/transitions.css" />
<link rel="stylesheet" type="text/css" href="css/widget/tabs/tabs.css" />
<link rel="stylesheet" type="text/css" href="css/widget/tabs/tabs.default.css" />
<script type="text/javascript" src="js/zepto.js"></script>
<script type="text/javascript" src="js/mobileevent2pc.js"></script>		<!--此js为在PC端方便查看效果，用在手机上，可去掉此js-->
<script type="text/javascript" src="js/touch.js"></script>      <!--新版zepto合并版中不包括touch.js-->
<script type="text/javascript" src="js/zepto.extend.js"></script>
<script type="text/javascript" src="js/zepto.ui.js"></script>
<script type="text/javascript" src="js/zepto.highlight.js"></script>
<script type="text/javascript" src="js/widget/tabs.js"></script>
<style type="text/css">
a{
	text-decoration: none;
}
table{
	margin: 0 auto;
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
#container{
	width: 1005px;
	border-top:4px solid #66CC00;
}
#foot{
	width: 100%;
	height: 100px;
	background-color: #999999;
	padding-top: 20px;
}
#conten1{
	height: 400px;
}
#conten2{
	height: 400px;
}
#conten3{
	height: 400px;
}
#conten4{
	height: 400px;
}
#container{
	padding-top: 5px;
}
</style>
</head>
<body background="white">
	<table>
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
				<div class="section">
				    <div id="container">
				        <div id="tabs1">
				            <ul>
				                <li><a href="#conten1">WiFi</a></li>
				                <li><a href="#conten2">微信</a></li>
				                <li><a href="#conten3">企业APP</a></li>
				                <li><a href="#conten4">二维码</a></li>
				            </ul>
				            <div id="conten1">
								<?php
							$con = mysql_connect("localhost","weilifly","szwlkj2013");
							if (!$con)
							  {
							  die('Could not connect: ' . mysql_error());
							  }
							mysql_select_db("weilifly", $con);
							mysql_query("set names utf8"); 
							$pagesize=2;//每页显示信息
							// 获取当前页数
							if(isset($_GET['page'])){
							   $page = intval($_GET['page']);
							}
							else{
							   $page = 1;
							} 
							// 获取总数据量
							$sql = "SELECT count(*) AS amount FROM earn_money WHERE type='WiFi'";
							$res = mysql_query($sql);
							While($rows = mysql_fetch_array($res)){
								$amount=$rows['amount'];
							}
							// 记算总共有多少页
							if($amount){
							   if( $amount < $pagesize ){ $page_count = 1; }               //如果总数据量小于$PageSize，那么只有一页
							   if( $amount % $pagesize ){                                  //取总数据量除以每页数的余数
							       $page_count = (int)($amount / $pagesize) + 1;           //如果有余数，则页数等于总数据量除以每页数的结果取整再加一
							   }else{
							       $page_count = $amount / $pagesize;                      //如果没有余数，则页数等于总数据量除以每页数的结果
							   }
							}else{
							   $page_count = 0;
							}
							$page_string = '';
							if( $page == 1 ){
							   $page_string = '第一页|上一页|';
							}else{
							   $page_string = '<a href=earn_money.php?page=1>第一页</a>|<a href=earn_money.php?page='.($page-1).'>上一页</a>|';
							}
							if( ($page == $page_count) || ($page_count == 0) ){
							   $page_string2= '下一页|尾页';
							}else{
							   $page_string2 = '<a href=earn_money.php?page='.($page+1).'>下一页</a>|<a href=earn_money.php?page='.$page_count.'>尾页</a>';
							}
							$sqlsalon="SELECT * FROM earn_money WHERE type='WiFi' LIMIT ". ($page-1)*$pagesize .", $pagesize";
							$result = mysql_query($sqlsalon);
							While($row = mysql_fetch_array($result))
							  {
							  echo "赚钱方式：".$row['way']."";
							  echo "</br>";
							  echo "赚钱方式（详细）：".$row['detailed']."";
							  echo "</br>";
							  echo "备注：".$row['beizhu']."";
							  echo "</br>";
							  }
							mysql_close($con);
							?>
							<?php
						 	echo $page_string;
						 	echo $page_string2;
						 	?>
				            </div>
				            <div id="conten2">
							<?php
							$con = mysql_connect("localhost","weilifly","szwlkj2013");
							if (!$con)
							  {
							  die('Could not connect: ' . mysql_error());
							  }
							mysql_select_db("weilifly", $con);
							mysql_query("set names utf8"); 
							$pagesize=2;//每页显示信息
							// 获取当前页数
							if(isset($_GET['page'])){
							   $page = intval($_GET['page']);
							}
							else{
							   $page = 1;
							} 
							// 获取总数据量
							$sql = "SELECT count(*) AS amount FROM earn_money WHERE type='微信'";
							$res = mysql_query($sql);
							While($rows = mysql_fetch_array($res)){
								$amount=$rows['amount'];
							}
							// 记算总共有多少页
							if($amount){
							   if( $amount < $pagesize ){ $page_count = 1; }               //如果总数据量小于$PageSize，那么只有一页
							   if( $amount % $pagesize ){                                  //取总数据量除以每页数的余数
							       $page_count = (int)($amount / $pagesize) + 1;           //如果有余数，则页数等于总数据量除以每页数的结果取整再加一
							   }else{
							       $page_count = $amount / $pagesize;                      //如果没有余数，则页数等于总数据量除以每页数的结果
							   }
							}else{
							   $page_count = 0;
							}
							$page_string = '';
							if( $page == 1 ){
							   $page_string = '第一页|上一页|';
							}else{
							   $page_string = '<a href=earn_money.php?page=1>第一页</a>|<a href=earn_money.php?page='.($page-1).'>上一页</a>|';
							}
							if( ($page == $page_count) || ($page_count == 0) ){
							   $page_string2= '下一页|尾页';
							}else{
							   $page_string2 = '<a href=earn_money.php?page='.($page+1).'>下一页</a>|<a href=earn_money.php?page='.$page_count.'>尾页</a>';
							}
							$sqlsalon="SELECT * FROM earn_money WHERE type='微信' LIMIT ". ($page-1)*$pagesize .", $pagesize";
							$result = mysql_query($sqlsalon);
							While($row = mysql_fetch_array($result))
							  {
							  echo "赚钱方式：".$row['way']."";
							  echo "</br>";
							  echo "赚钱方式（详细）：".$row['detailed']."";
							  echo "</br>";
							  echo "备注：".$row['beizhu']."";
							  echo "</br>";
							  }
							mysql_close($con);
							?>
							<?php
						 	echo $page_string;
						 	echo $page_string2;
						 	?>
				            </div>
				            <div id="conten3">
							<?php
							$con = mysql_connect("localhost","weilifly","szwlkj2013");
							if (!$con)
							  {
							  die('Could not connect: ' . mysql_error());
							  }
							mysql_select_db("weilifly", $con);
							mysql_query("set names utf8"); 
							$pagesize=2;//每页显示信息
							// 获取当前页数
							if(isset($_GET['page'])){
							   $page = intval($_GET['page']);
							}
							else{
							   $page = 1;
							} 
							// 获取总数据量
							$sql = "SELECT count(*) AS amount FROM earn_money WHERE type='企业APP'";
							$res = mysql_query($sql);
							While($rows = mysql_fetch_array($res)){
								$amount=$rows['amount'];
							}
							// 记算总共有多少页
							if($amount){
							   if( $amount < $pagesize ){ $page_count = 1; }               //如果总数据量小于$PageSize，那么只有一页
							   if( $amount % $pagesize ){                                  //取总数据量除以每页数的余数
							       $page_count = (int)($amount / $pagesize) + 1;           //如果有余数，则页数等于总数据量除以每页数的结果取整再加一
							   }else{
							       $page_count = $amount / $pagesize;                      //如果没有余数，则页数等于总数据量除以每页数的结果
							   }
							}else{
							   $page_count = 0;
							}
							$page_string = '';
							if( $page == 1 ){
							   $page_string = '第一页|上一页|';
							}else{
							   $page_string = '<a href=earn_money.php?page=1>第一页</a>|<a href=earn_money.php?page='.($page-1).'>上一页</a>|';
							}
							if( ($page == $page_count) || ($page_count == 0) ){
							   $page_string2= '下一页|尾页';
							}else{
							   $page_string2 = '<a href=earn_money.php?page='.($page+1).'>下一页</a>|<a href=earn_money.php?page='.$page_count.'>尾页</a>';
							}
							$sqlsalon="SELECT * FROM earn_money WHERE type='企业APP' LIMIT ". ($page-1)*$pagesize .", $pagesize";
							$result = mysql_query($sqlsalon);
							While($row = mysql_fetch_array($result))
							  {
							  echo "赚钱方式：".$row['way']."";
							  echo "</br>";
							  echo "赚钱方式（详细）：".$row['detailed']."";
							  echo "</br>";
							  echo "备注：".$row['beizhu']."";
							  echo "</br>";
							  }
							mysql_close($con);
							?>
							<?php
						 	echo $page_string;
						 	echo $page_string2;
						 	?>
				            </div>
				            <div id="conten4">
							<?php
							$con = mysql_connect("localhost","weilifly","szwlkj2013");
							if (!$con)
							  {
							  die('Could not connect: ' . mysql_error());
							  }
							mysql_select_db("weilifly", $con);
							mysql_query("set names utf8"); 
							$pagesize=2;//每页显示信息
							// 获取当前页数
							if(isset($_GET['page'])){
							   $page = intval($_GET['page']);
							}
							else{
							   $page = 1;
							} 
							// 获取总数据量
							$sql = "SELECT count(*) AS amount FROM earn_money WHERE type='二维码'";
							$res = mysql_query($sql);
							While($rows = mysql_fetch_array($res)){
								$amount=$rows['amount'];
							}
							// 记算总共有多少页
							if($amount){
							   if( $amount < $pagesize ){ $page_count = 1; }               //如果总数据量小于$PageSize，那么只有一页
							   if( $amount % $pagesize ){                                  //取总数据量除以每页数的余数
							       $page_count = (int)($amount / $pagesize) + 1;           //如果有余数，则页数等于总数据量除以每页数的结果取整再加一
							   }else{
							       $page_count = $amount / $pagesize;                      //如果没有余数，则页数等于总数据量除以每页数的结果
							   }
							}else{
							   $page_count = 0;
							}
							$page_string = '';
							if( $page == 1 ){
							   $page_string = '第一页|上一页|';
							}else{
							   $page_string = '<a href=earn_money.php?page=1>第一页</a>|<a href=earn_money.php?page='.($page-1).'>上一页</a>|';
							}
							if( ($page == $page_count) || ($page_count == 0) ){
							   $page_string2= '下一页|尾页';
							}else{
							   $page_string2 = '<a href=earn_money.php?page='.($page+1).'>下一页</a>|<a href=earn_money.php?page='.$page_count.'>尾页</a>';
							}
							$sqlsalon="SELECT * FROM earn_money WHERE type='二维码' LIMIT ". ($page-1)*$pagesize .", $pagesize";
							$result = mysql_query($sqlsalon);
							While($row = mysql_fetch_array($result))
							  {
							  echo "赚钱方式：".$row['way']."";
							  echo "</br>";
							  echo "赚钱方式（详细）：".$row['detailed']."";
							  echo "</br>";
							  echo "备注：".$row['beizhu']."";
							  echo "</br>";
							  }
							mysql_close($con);
							?>
							<?php
						 	echo $page_string;
						 	echo $page_string2;
						 	?>
				            </div>
				        </div>
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
<script>
$('#tabs1').tabs();
$('#tabs1').on('beforeActivate', function(e){
    $('#input1').prop('checked') && e.preventDefault();
});
</script>
</body>
</html>