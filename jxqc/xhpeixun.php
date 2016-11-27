<html>
<head>
<meta http-equiv="Content-type" content="text/html; charset=utf-8" />
<title>江西汽车流通行业协会</title>

<meta name="keywords" content="新浪" />
<meta name="description" content="新" />
<link href="css/head.css" rel="stylesheet" type="text/css">
<script src="jquery/jquery-1.10.2.js"></script>
<style type="text/css">
#vip{
	margin:0 auto;
	width: 70%;
}
#vip_top{
	width: 100%;
	height: 40px;
	border-bottom: 2px solid blue;
	font-size: 22px;
	padding-top: 10px;
}
.every_vip{
	height: auto;
	width: 70%;
	border-bottom: 1px solid red;
}
a{
	text-decoration: none;
}
</style>

</head>
<body>
	<?php include_once("head.php");?>
	<div id="vip">
		<div id="vip_top">培训信息列表</div>
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
					// 获取总数据量
					$sql = "SELECT count(*) AS amount FROM xh_peixun";
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
					   $page_string = '<a href=xhpeixun.php?page=1>第一页</a>|<a href=xhpeixun.php?page='.($page-1).'>上一页</a>|';
					}
					if( ($page == $page_count) || ($page_count == 0) ){
					   $page_string2= '下一页|尾页';
					}else{
					   $page_string2 = '<a href=xhpeixun.php?page='.($page+1).'>下一页</a>|<a href=xhpeixun.php?page='.$page_count.'>尾页</a>';
					}
					$sqlnews="SELECT * FROM xh_peixun ORDER BY time DESC LIMIT ". ($page-1)*$pagesize .", $pagesize";

					$result = mysqli_query($con,$sqlnews);
					While($row = mysqli_fetch_array($result))
					  {
					  echo "<div class='every_vip'>";	
					  echo "<a href='peixun_list.php?id=".$row['id']."' target='_blank'><h3>".$row['title']."</h3></a>";
					  echo "<p>时间:".$row['time']."</p>";	
					  echo "<p>作者:".$row['author']."</p>";
					  echo mb_substr($row['neirong'], 0, 100,'utf-8');
					  echo "......";
					  echo "</div>";
					  }
					  echo $page_string;
					  echo $page_string2;
			?>
		</div>
	<?php include_once("foot.php");?>
</body>
</html>