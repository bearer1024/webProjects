<?php
session_start();
if (!isset($_SESSION['username'])) {
	header("refresh:0;url=admin_login.php");
	}
$con = mysql_connect("localhost","weilifly","szwlkj2013");
if (!$con)
  {
  die('Could not connect: ' . mysql_error());
  }
mysql_select_db("weilifly", $con);
mysql_query("set names utf8"); 
if(isset($_GET['id'])){
	$id=$_GET['id'];
	$sql="DELETE FROM out_concept WHERE out_id='$id'";
	mysql_query($sql);
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
	left: 300px;
	border:1px #003366 solid;
	height: 600px;
	width: 800px;
}
</style>
</head>
<body>
<div>
	<table>
	<tr>
		<td>ID</td><td>概念名称</td><td>删除</td>
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
			$sql = "SELECT count(*) AS amount FROM out_concept";
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
			   $page_string = '<a href=del_out_concept.php?page=1>第一页</a>|<a href=del_out_concept.php?page='.($page-1).'>上一页</a>|';
			}
			if( ($page == $page_count) || ($page_count == 0) ){
			   $page_string2= '下一页|尾页';
			}else{
			   $page_string2 = '<a href=del_out_concept.php?page='.($page+1).'>下一页</a>|<a href=del_out_concept.php?page='.$page_count.'>尾页</a>';
			}
			$sqlsalon="SELECT * FROM out_concept LIMIT ". ($page-1)*$pagesize .", $pagesize";
			$result = mysql_query($sqlsalon);
			While($row = mysql_fetch_array($result))
			  {
			    echo "<tr>";
			    echo "<td>".$row['out_id']."</td>";
			    echo "<td>".$row['out_name']."</td>";
			    echo "<td><a href='del_out_concept.php?id=".$row['out_id']."'>删除</a></td>";
			    echo "</tr>";
			  }
			mysql_close($con);
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