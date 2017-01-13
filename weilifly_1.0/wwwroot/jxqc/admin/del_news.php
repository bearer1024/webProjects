<?php
session_start();
if (!isset($_SESSION['username'])) {
	header("refresh:0;url=admin_login.php");
	}
include_once("connect.php");
if(isset($_GET['id'])){
	$id=$_GET['id'];
	$sql="DELETE FROM xh_news WHERE id='$id'";
	mysqli_query($con,$sql);
	echo "删除了一条记录！";
}
?>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.0/jquery.min.js">
</script>
<style type="text/css">
body{
	position: absolute;
	top: 0px;
	left: 0px;
	border:1px #003366 solid;
	height: 560px;
	width: 690px;
}
</style>
</head>
<body>
<div>
	<table>
	<tr>
		<td>ID</td><td>新闻标题</td><td>删除</td>
	</tr>
		<?php
			$pagesize=6;//每页显示信息
			// 获取当前页数
			if(isset($_GET['page'])){
			   $page = intval($_GET['page']);
			}
			else{
			   $page = 1;
			} 
			// 获取总数据量
			$sql = "SELECT count(*) AS amount FROM xh_news";
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
			   $page_string = '<a href=del_news.php?page=1>第一页</a>|<a href=del_news.php?page='.($page-1).'>上一页</a>|';
			}
			if( ($page == $page_count) || ($page_count == 0) ){
			   $page_string2= '下一页|尾页';
			}else{
			   $page_string2 = '<a href=del_news.php?page='.($page+1).'>下一页</a>|<a href=del_news.php?page='.$page_count.'>尾页</a>';
			}
			$sqlsalon="SELECT * FROM xh_news LIMIT ". ($page-1)*$pagesize .", $pagesize";
			$result = mysqli_query($con,$sqlsalon);
			While($row = mysqli_fetch_array($result))
			  {
			    echo "<tr>";
			    echo "<td>".$row['id']."</td>";
			    echo "<td>".$row['title']."</td>";
			    echo "<td><a href='del_news.php?id=".$row['id']."'>删除</a></td>";
			    echo "</tr>";
			  }
			mysqli_close($con);
			?>
	</table>
	<div>
		<?php
		echo $page_string;
 		echo $page_string2;
		?>
	</div>
	</div>
</body>
</html>