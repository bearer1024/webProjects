<?php
session_start();
?>
<html>
<head>
<meta http-equiv="Content-type" content="text/html; charset=utf-8" />
<title>江西汽车流通行业协会</title>

<meta name="keywords" content="新浪" />
<meta name="description" content="新" />
<link href="css/head.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="css/jquery-ui-1.10.4.css">
<script src="jquery/jquery-1.10.2.js"></script>
<script src="jquery/jquery-ui-1.10.4.js"></script>
<style type="text/css">
#content{
	width: 70%;
	height: auto;
	margin: 10px auto;
}
#lanmu{
	border-bottom:2px solid blue;
	height: 30px;
	padding-top:10px;  
}
#lanmu a{
	text-decoration: none;
	font-size: 18px;
	margin: 20px;
}
li{
	list-style-type: none;
}
#chanpin{
	margin-top: 10px;
}
.every_chanpin{
	height: 200px;
	width: 80%;
	border-bottom: 1px solid blue;
	padding-top: 5px;
}
.every_chanpin_tupian{
	height: 198px;
	width: 200px;
	float: left;
}
.every_chanpin_miaoshu{
	height: 198px;
	width: 300ps;
	float: left;
}
a{
	text-decoration: none;
}
</style>
<script type="text/javascript">
</script>
</head>
<body>
	
	<?php include_once("head.php");?>
	<div id="content">
		<div id="lanmu">
			<?php
				if (!isset($_SESSION['name'])) {
					//header("refresh:0;url=login.php");
					//如果没有登陆，评论发表将会受到限制，只能浏览
					//echo "  <button id='zhuce' >注册</button>";
					echo "<a href='#' id='zhuce'>会员注册</a>";
					echo "<a href='#' id='denglu'>会员登录</a>";
					if (isset($_GET['info'])) {
        			echo "<span style='color:red'>".$_GET['info']."</span>";
    				}
				}else{
				echo "欢迎你：".$_SESSION['name']."";
				echo "<a href='#' id='fabu'>产品发布</a>";
				echo "<a href='logout.php'>退出</a>";
				}
			?>
		</div>
<div id="dialog_zhuce" title="注册">
  <!--<iframe src="register.php" name="ifrmname" id="ifrmid" width="470px" height="320px" frameborder="no" border="0"></iframe>-->
	<div id="ad">欢迎你亲爱的会员</div>
 	<form action="vipreg.php" method="post">
 		<ul>
 			<li>名字: <input required type="text" name="name1" size=30></li> 
 			<li>密码: <input required type="password" name="password1" size=30></li>
 			<li>企业名称: <input required type="text" name="qiye" size=25></li>
 			<li><input type="submit" name="submit"></li>
 		</ul>
 	</form>
</div>
<script>
$( "#dialog_zhuce" ).dialog({ autoOpen: false,position: [400,150],modal:true,width:480,height:280,
	close: function(event, ui) {
		location.reload();
 } //这是关闭事件的回调函数,在这写你的逻辑
});
$( "#zhuce" ).click(function() {
  	$( "#dialog_zhuce" ).dialog( "open" );
});
</script>

<div id="dialog_denglu" title="登陆">
  <form action="viplogin.php" method="post">
	  <ul>
	    <li><input required type="text" name="name2" placeholder="用户名" size=25 /></li>
	    <li><input required type="password" name="password2" placeholder="密码" size=25 /></li>
		<li><input type="submit" name="submit"></li>
	  </ul>
  </form>
</div>
<script>
$( "#dialog_denglu" ).dialog({ autoOpen: false,position: [500,150],modal:true,width:330,height:240,
	close: function(event, ui) {
		location.reload();
 } 
});
$( "#denglu" ).click(function() {
  $( "#dialog_denglu" ).dialog( "open" );
});
</script>

<div id="dialog_fabu" title="发布产品">
  <form id="fabu_form" action="fabu.php" method="post" enctype="multipart/form-data">
  <ul>
  	<li>产品名称：<input name="title" type="text" id="title"> </li>
  	<li>产品单价：<input name="price" type="text" id="price"> </li>
  	<li>产品图片：<input name="file" type="file" value="浏览"></li>
  	<li>产品描述：<textarea  rows="6" cols="40" name="miaoshu" id="miaoshu"></textarea></li>
  	<input type="hidden" name="MAX_FILE_SIZE" value="2000000"> 
  	<li>联系电话：<input name="phone" type="text" id="phone"> </li>
    <li><input type="submit" value="上 传" name="submit"></li>
  </ul>
  </form>
</div>
<script>
$( "#dialog_fabu" ).dialog({ autoOpen: false,position: [400,150],modal:true,width:500,height:450,
	close: function(event, ui) {
		location.reload();
 } 
});
$( "#fabu" ).click(function() {
  $( "#dialog_fabu" ).dialog( "open" );
});
</script>
		<div id="chanpin">
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
					$sql = "SELECT count(*) AS amount FROM xh_shangcheng_chanpin";
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
					   $page_string = '<a href=hyshangcheng.php?page=1>第一页</a>|<a href=hyshangcheng.php?page='.($page-1).'>上一页</a>|';
					}
					if( ($page == $page_count) || ($page_count == 0) ){
					   $page_string2= '下一页|尾页';
					}else{
					   $page_string2 = '<a href=hyshangcheng.php?page='.($page+1).'>下一页</a>|<a href=hyshangcheng.php?page='.$page_count.'>尾页</a>';
					}
					$sqlnews="SELECT * FROM xh_shangcheng_chanpin ORDER BY time DESC LIMIT ". ($page-1)*$pagesize .", $pagesize";

					$result = mysqli_query($con,$sqlnews);
					While($row = mysqli_fetch_array($result))
					  {
					  echo "<div class='every_chanpin'>";	
					  echo "<div class='every_chanpin_tupian'><a href='chanpin_list.php?id=".$row['id']."' target='_blank'><img src='".$row['pic']."' width='190px' height='195px'></a></div>";
					  echo "<div class='every_chanpin_miaoshu'>";
					  echo "<a href='chanpin_list.php?id=".$row['id']."' target='_blank'><h3>产品名称：".$row['title']."</h3></a>";
					  echo "<p>产品单价：".$row['price']."</p>";	
					  echo mb_substr($row['miaoshu'], 0, 41,'utf-8');
					  echo "......";
					  echo "</div>";
					  echo "</div>";
					  }
					  echo $page_string;
					  echo $page_string2;
			?>
		</div>
	</div>
	<?php include_once("foot.php");?>
</body>
</html>