<html>
<head>
<meta http-equiv="Content-Type" content="text/php;charset=utf-8"/>
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
	width: 25%;
	height: 500px;
	float: left;
}
#right{
	width: 75%;
	float: left;
}
#left ul{
	margin:0px; 
	padding:0px;
}
#left li{
	list-style: none;
	height:30px;
	margin:0px; 
	padding:0px;
	font-size: 20px;
	text-align:left;
}
.news{
	margin:0px; 
	padding:0px;
}
.news li{
	list-style: none;
	width: 650px;
	height: 200px;
	border-bottom:2px solid #66CC00;
	padding: 5px;
}
.newscontent{
	float: left;
	width: 620px;
	height: 200px;
	padding-left: 15px;
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
						<h2>成功案例分类</h2>
						<ul>
							<li><a href="showcase.php?types=全国知名案例">全国知名案例</a></li>
							<li><a href="showcase.php?types=合作伙伴成功案例">合作伙伴成功案例</a></li>
							<li><a href="showcase.php?types=独家落地转化案例">独家落地转化案例</a></li>
						</ul>
					</div>
					<div id="right">
						<h2>成功案例简介</h2>
					    <ul class="news">
							<?php
							$con = mysql_connect("localhost","weilifly","szwlkj2013");
							if (!$con)
							  {
							  die('Could not connect: ' . mysql_error());
							  }
							mysql_select_db("weilifly", $con);
							mysql_query("set names utf8"); 
							$pagesize=6;//每页显示信息
							// 获取当前页数
							if(isset($_GET['page'])){
							   $page = intval($_GET['page']);
							}
							else{
							   $page = 1;
							} 
							if (isset($_GET['types'])) {
								$type=$_GET['types'];
								// 获取总数据量
								$sql = "SELECT count(*) AS amount FROM succeed_case WHERE type='$type'";
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
								   $page_string = '<a href=showcase.php?page=1>第一页</a>|<a href=showcase.php?page='.($page-1).'&types='.$type.'>上一页</a>|';
								}
								if( ($page == $page_count) || ($page_count == 0) ){
								   $page_string2= '下一页|尾页';
								}else{
								   $page_string2 = '<a href=showcase.php?page='.($page+1).'&types='.$type.'>下一页</a>|<a href=showcase.php?page='.$page_count.'&types='.$type.'>尾页</a>';
								}
								$sqlnews="SELECT * FROM succeed_case where type='$type' LIMIT ". ($page-1)*$pagesize .", $pagesize";

							}else{
								// 获取总数据量
								$sql = "SELECT count(*) AS amount FROM succeed_case WHERE type='全国知名案例'";
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
								   $page_string = '<a href=showcase.php?page=1>第一页</a>|<a href=showcase.php?page='.($page-1).'&types=趋势>上一页</a>|';
								}
								if( ($page == $page_count) || ($page_count == 0) ){
								   $page_string2= '下一页|尾页';
								}else{
								   $page_string2 = '<a href=showcase.php?page='.($page+1).'&types=趋势>下一页</a>|<a href=showcase.php?page='.$page_count.'&types=趋势>尾页</a>';
								}
								$sqlnews="SELECT * FROM succeed_case where type='全国知名案例' LIMIT ". ($page-1)*$pagesize .", $pagesize";
							}
							$result = mysql_query($sqlnews);
							While($row = mysql_fetch_array($result))
							  {
							  echo "<li>";
							  echo "<div class='newscontent'>";
							  echo "<a href='case_list.php?id=".$row['id']."' target='_blank'><h3>".$row['case_name']."</h3></a>";
							  echo $row['gongsi_name'];
							  echo mb_substr($row['case_introduce'], 0, 41,'utf-8');
							  echo "......";
							  echo "</div>";
							  echo "</li>";
							  }
							mysql_close($con);
							?>
						</ul>
					 	<?php
					 	echo $page_string;
					 	echo $page_string2;
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