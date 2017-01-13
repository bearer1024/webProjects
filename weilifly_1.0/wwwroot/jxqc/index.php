<html>
<head>
<meta http-equiv="Content-type" content="text/html; charset=utf-8" />
<title>江西汽车流通行业协会</title>

<meta name="keywords" content="江西汽车流通" />
<meta name="description" content="江西汽车" />
<link href="css/head.css" rel="stylesheet" type="text/css">
<script src="jquery/jquery-1.10.2.js"></script>
<style type="text/css">
#content{
	width: 70%;
	height: 660px;
	margin: 10px auto;
}
#one{
	height: 300px;
	margin: 0 auto;
}
#two{
	height: 300px;
	margin: 0 auto;
}
#one_left{
	width: 50%;
	height: 300px;
	float: left;
	padding: 10px;
	border-bottom: 1px solid blue;
}
#one_right{
	width: 45%;
	height: 300px;
	float: right;
	padding: 10px;
	border-bottom: 1px solid blue;
}
#two_left{
	width: 50%;
	height: 300px;
	float: left;
	padding: 10px;
	border-bottom: 1px solid blue;
}
#two_right{
	width: 45%;
	height: 300px;
	float: left;
	padding: 10px;
	border-bottom: 1px solid blue;
}
a{
	text-decoration: none;
}
</style>
<script language="javascript">
var i = 2;
function ChangePic(id)
{
	$("#background").hide();
	document.getElementById("background").src="images/"+id+".jpg";
	$("#background").fadeIn(500);
	
	for(j=0;j<$(".dot").length;j++)
	{
		if(j == id-1)
		{
			$(".dot")[j].src="images/point2.png";
		}
		else
		{
			$(".dot")[j].src="images/point.png";
		}
	}
}
function AutoChangePic()
{
	ChangePic(i);
	i++;
	if( i > 5)
	{
		i = 1;
	}
}
$(document).ready(function()
{
	ChangePic(1);
	setInterval("AutoChangePic()",8000);
});
</script>
<script type="text/javascript">
$().ready(function(){

});
</script>
</head>
<body>
	<?php include_once("head.php");?>
	<div id="content">
		<div id="one">
			<div id="one_left">
				<div align="center" style="position:relative">
					<img id="background" src="images/1.jpg" style="z-index:-1;width:100%;height:95%"></img>
					<table class="dot_table">
						<tr>
							<td><img class="dot" src="images/point.png"/></td>
							<td><img class="dot" src="images/point.png"/></td>
							<td><img class="dot" src="images/point.png"/></td>
							<td><img class="dot" src="images/point.png"/></td>
							<td><img class="dot" src="images/point.png"/></td>
						</tr>
					</table>
				</div>
			</div>
			<div id="one_right">
				<h2>协会公告</h2>
				<?php
					include_once("connect.php");
					$sqlnews="SELECT * FROM xh_news where type=1 ORDER BY time DESC LIMIT 2";
					$result = mysqli_query($con,$sqlnews);
					While($row = mysqli_fetch_array($result))
					  {
					  echo "<a href='news_list.php?id=".$row['id']."' target='_blank'><h3>标题：".$row['title']."</h3></a>";	
					  echo mb_substr($row['neirong'], 0, 35,'utf-8');
					  echo "......";
					  }
				?>
				
			</div>
		</div>
		<div id="two">
			<div id="two_left">
				<h2>行业资讯</h2>
				<?php
					include_once("connect.php");
					$sqlnews="SELECT * FROM xh_news where type=3 ORDER BY time DESC LIMIT 2";
					$result = mysqli_query($con,$sqlnews);
					While($row = mysqli_fetch_array($result))
					  {
					  echo "<a href='news_list.php?id=".$row['id']."' target='_blank'><h3>标题：".$row['title']."</h3></a>";	
					  echo mb_substr($row['neirong'], 0, 35,'utf-8');
					  echo "......";
					  }
				?>
				
			</div>
			<div id="two_right">
				<h2>政策发布</h2>
				<?php
					include_once("connect.php");
					$sqlnews="SELECT * FROM xh_news where type=2 ORDER BY time DESC LIMIT 2";
					$result = mysqli_query($con,$sqlnews);
					While($row = mysqli_fetch_array($result))
					  {
					  echo "<a href='news_list.php?id=".$row['id']."' target='_blank'><h3>标题：".$row['title']."</h3></a>";	
					  echo mb_substr($row['neirong'], 0, 35,'utf-8');
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