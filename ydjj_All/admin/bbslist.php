<?php
	require '../inc/shell.php';
	require '../inc/config.php';
	//selectOnly($param,$tbname,$where,$order)
	//unset($_SESSION['wherebbs']);
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>栏目信息列表</title>
<link rel="stylesheet" type="text/css" href="css/css.css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/js.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		var thisPage = $("#page_SEL");
		thisPage.change(function(){
		location.href="bbslist.php?page="+thisPage.val();
		});//end page_SEL 		
	});
</script>
</head>

<body>
<?php
//删除数据
$action = $_GET['action'];
//删除单条数据
if($action == 'delete')
{
	if($bw->delete('bw_userbbs', 'id = '.$_GET['id']))
	{
		$bw->msg('删除成功!');	
	}else{
		$bw->msg('删除失败!');	
	}
}
//删除多条数据
if($action == 'deleteFrom')
{
	//print_r($_POST);
	$id = implode(',', $_POST['id']);
	if($bw->delete('bw_userbbs', "id IN (".$id.")"))
	{
		$bw->msg('删除成功!');	
	}else{
		$bw->msg('删除失败!');	
	}
}
?>
<table width="96%" cellpadding="0" cellspacing="0">
<tr>
<form name="searchForm" action="?action=search" method="post">
<td>关键字:<input name="keyword" id="keyword" />&nbsp;
  <select name="type" id="type">
  <option value="">选择类别</option>
  <option value="1">商海心得</option>
  <option value="2">行业取经</option>
  <option value="3">各抒己见</option>
  </select>
  &nbsp;<input type="submit" value="搜索" /></td>
</form>
</tr>
</table>

<table width="96%" cellpadding="0" cellspacing="0">
  <tr class="trTitle">
    <td width="44" align="center"><strong>ID</strong></td>
    <td width="542" align="center"><strong>标题</strong></td>
    <td width="153" align="center"><strong>所属分类</strong></td>
    <td width="181" align="center"><strong>加入时间</strong></td>
    <td width="136" align="center"><strong>操作</strong></td>
  </tr>
<form name="listForm" action="?action=deleteFrom" method="post">
<?php
  //查询
  //selectPage($param,$tbname,$where,$order,$limit)
  //requestPage($tbname,$limit) : array('totalRow'=>$totalRow,'totalPage'=>$totalPage,'page'=>$page,'pagePrev'=>$pagePrev,'pageNext'=>$pageNext);
  $pageSize = PAGESIZE;
  $tbName   = "bw_userbbs";
  $where    = '';
  //搜索
  if(!empty($_GET['action']) && $_GET['action'] == 'search')
  {
	  if(!empty($_POST['keyword']) && !empty($_POST['type']))
	  {
		$where = "title LIKE '%".$_POST['keyword']."%' AND type = ".$_POST['type'];
		$_SESSION['wherebbs'] = $where;
	  }else if(!empty($_POST['type'])){
		$where = "type = ".$_POST['type'];
		$_SESSION['wherebbs'] = $where;
	  }else{
		$where = "title LIKE '%".$_POST['keyword']."%'";  
		$_SESSION['wherebbs'] = $where;
	  }
  }
  $list = $bw->selectPage("*",$tbName,$_SESSION['wherebbs'],"`id` DESC",$pageSize);
  $pageArray = $bw->requestPage($tbName,$_SESSION['wherebbs'],$pageSize);
  $sum = count($list);
  for($i = 0; $i<$sum; $i++)
  {
?>
  <tr>
    <td align="center"><input type="checkbox" name="id[]" id="id[]" value="<?php echo $list[$i]['id']; ?>" /></td>
    <td align="center"><a href="bbsadd.php?id=<?php echo $list[$i]['id']; ?>"><?php echo $list[$i]['title']; ?></a></td>
    <td align="center">
    	<?php
		switch ($list[$i]['type'])
		{
			case 1:
			echo "商海心得";
			break;
			case 2:
			echo "行业取经";
			break;
			default:
			echo "各抒己见";
			break;
			}
		
		?>
    </td>
    <td align="center"><?php echo $list[$i]['subTime']; ?></td>
    <td align="center"><a href="bbsadd.php?id=<?php echo $list[$i]['id']; ?>"><img src="images/pen.gif" /></a>&nbsp;&nbsp;<a href="?action=delete&id=<?php echo $list[$i]['id']; ?>"><img src="images/delete.gif" /></a>&nbsp; <a href="bbspl.php?bbsid=<?php echo $list[$i]['id']; ?>">相关评论</a> </td>
  </tr>
<?php
	}//end loop
?>
  <tr>
  	<td colspan="5" align="center">
    	
    	<table width="100%" border="0" cellspacing="0" cellpadding="0">
    	  <tr>
    	    <td align="left"><input type="button" value="全选" id="selectAll" />&nbsp;<input type="button" value="反选" id="orderSelect" />&nbsp;<input type="submit" value="删除所选" id="deleteSelect" /></td>
    	    <td align="right">共:<?php echo $pageArray['totalRow']; ?>&nbsp;条信息&nbsp;当前:<span><?php echo $pageArray['page']; ?></span>/<?php echo $pageArray['totalPage']; ?>页&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="?page=1">第一页</a>&nbsp;&nbsp;
				<a href="?page=<?php echo $pageArray['pagePrev']; ?>">上一页</a>&nbsp;&nbsp;
				<a href="?page=<?php echo $pageArray['pageNext']; ?>">下一页</a>&nbsp;&nbsp;
				<a href="?page=<?php echo $pageArray['totalPage']; ?>">尾页</a>&nbsp;&nbsp;
				跳到
				<select name="page_SEL" id="page_SEL">
						<option value="">---</option>
						<?php
						  for($goPage = 1; $goPage <= $pageArray['totalPage']; $goPage++)
						  {
						?>
						<option value="<?php echo $goPage; ?>"><?php echo $goPage; ?></option>
						<?php
						 }
						?>
			  </select></td>
  	    </tr>
  	  </table></td>
  </tr>
</form>
</table>

</body>
</html>