<html>
<head>
<meta http-equiv="Content-type" content="text/html; charset=utf-8" />
<title>江西汽车流通行业协会</title>

<meta name="keywords" content="新浪" />
<meta name="description" content="新" />
<link href="css/head.css" rel="stylesheet" type="text/css">
<script src="jquery/jquery-1.10.2.js"></script>
<style type="text/css">
#content{
	width: 70%;
	height: 850px;
	margin: 10px auto;
}
#lanmu a{
	text-decoration: none;
	font-size: 18px;
	margin: 20px;
}
#left{
	width: 70%;
	height:780px;
	border-top: 2px solid red;
	margin-top: 10px;
	float: left;
}
#right{
	margin-top: 10px;
	float: left;
	width: 28%;
	height:780px;
}
#right_div{
	position: absolute;
	top: 310px;
	width: 240px;
	height:280px;
	border: 1px solid red;
	margin-left: 20px;
}
#right_div_log{
	color: red;
	font-size: 24px;
}
.newscontent{
	height: auto;
	border-bottom: 1px solid #CCCCFF;
}
a{
	text-decoration: none;
}
</style>

</head>
<body>
	<?php include_once("head.php");?>
	<div id="content">
		<div id="lanmu"><a href="hynews.php?types=1">协会公告</a><a href="hynews.php?types=2">政策发布</a><a href="hynews.php?types=3">行业资讯</a><a href="hynews.php?types=4">海外新闻</a></div>
		<div id="left">
				<?php
					include_once("connect.php");
					$pagesize=5;//每页显示信息
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
						$sql = "SELECT count(*) AS amount FROM xh_news WHERE type='$type'";
						$res = mysqli_query($con,$sql);
						While($rows = mysqli_fetch_array($res)){
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
						   $page_string = '<a href=hynews.php?page=1>第一页</a>|<a href=hynews.php?page='.($page-1).'&types='.$type.'>上一页</a>|';
						}
						if( ($page == $page_count) || ($page_count == 0) ){
						   $page_string2= '下一页|尾页';
						}else{
						   $page_string2 = '<a href=hynews.php?page='.($page+1).'&types='.$type.'>下一页</a>|<a href=hynews.php?page='.$page_count.'&types='.$type.'>尾页</a>';
						}
						$sqlnews="SELECT * FROM xh_news where type='$type' ORDER BY time DESC LIMIT ". ($page-1)*$pagesize .", $pagesize";

					}else{
						// 获取总数据量
						$sql = "SELECT count(*) AS amount FROM xh_news WHERE type=1 ";
						$res = mysqli_query($con,$sql);
						While($rows = mysqli_fetch_array($res)){
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
						   $page_string = '<a href=hynews.php?page=1>第一页</a>|<a href=hynews.php?page='.($page-1).'&types=趋势>上一页</a>|';
						}
						if( ($page == $page_count) || ($page_count == 0) ){
						   $page_string2= '下一页|尾页';
						}else{
						   $page_string2 = '<a href=hynews.php?page='.($page+1).'&types=趋势>下一页</a>|<a href=hynews.php?page='.$page_count.'&types=趋势>尾页</a>';
						}
						$sqlnews="SELECT * FROM xh_news where type=1 ORDER BY time DESC LIMIT ". ($page-1)*$pagesize .", $pagesize";
					}
					$result = mysqli_query($con,$sqlnews);
					While($row = mysqli_fetch_array($result))
					  {
					  echo "<div class='newscontent'>";
					  echo "<a href='news_list.php?id=".$row['id']."' target='_blank'><h3>".$row['title']."</h3></a>";	
					  echo "<div id='showtime'>".$row['time']."</div>";
					  echo "<div id='showtime'>".$row['author']."</div>";
					  echo mb_substr($row['neirong'], 0, 41,'utf-8');
					  echo "......";
					  echo "</div>";
					  }
					  echo $page_string;
					  echo $page_string2;
					//mysqli_close($con);
					?>
		</div>
		<div id="right">
			<div id="right_div">
				<div id="right_div_log">推荐内容</div>
				<?php 
					include_once("connect.php");
					$sqlnews="SELECT * FROM xh_news ORDER BY time DESC LIMIT 2";
					$result = mysqli_query($con,$sqlnews);
					While($row = mysqli_fetch_array($result))
					  {
					  echo "<a href='news_list.php?id=".$row['id']."' target='_blank'><h4>".$row['title']."</h4></a>";	
					  echo mb_substr($row['neirong'], 0, 25,'utf-8');
					  echo "......";
					  }
					mysqli_close($con);
				?>
			</div>
		</div>
	</div>
	<?php include_once("foot.php");?>
</body>
</html>