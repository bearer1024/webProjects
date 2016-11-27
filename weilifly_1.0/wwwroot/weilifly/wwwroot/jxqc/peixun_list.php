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
	width: 600px;
	height: auto;
	margin-left: 16%;
}
#lanmu a{
	text-decoration: none;
	font-size: 18px;
	margin: 20px;
}
#right_div{
	position: fixed;
	top: 280px;
	left: 66%;
	margin-left: 20px;
}
</style>

</head>
<body>
	<?php include_once("head.php");?>
	<div id="content">
				<?php
					include_once("connect.php");
					if(isset($_GET['id'])){
						$id=$_GET['id'];
						$sql="SELECT * FROM xh_peixun where id='$id'";
						$result = mysqli_query($con,$sql);
						While($row = mysqli_fetch_array($result))
						  {
						  echo "<div class='newscontent'>";
						  echo "<h3>".$row['title']."</h3>";	
						  echo "<div id='showtime'>".$row['time']."</div>";
						  echo "<div id='showtime'>".$row['neirong']."</div>";
						  echo "<div id='showtime'>作者：".$row['author']."</div>";
						  echo "</div>";
						  }
					}
				?>
		
	</div>
	<div id="right">
			<div id="right_div"><img src="images/bgimages.jpg"></div>
		</div>
	<?php include_once("foot.php");?>
</body>
</html>