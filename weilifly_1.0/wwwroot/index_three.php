<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
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
.dot_table{
position:absolute;
left:43%;
top:94%;
}
.dot_table img{
margin-right:1.0em;
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
#foot{
	width: 1010px;
	height: 100px;
	background-color: #FFCCCC;
}
.banner a{
font-size: 200px;
}
.banner a:hover{
	background-color: #66CC00;
}
#foot{
	width: 100%;
	height: 100px;
	background-color: #999999;
	padding-top: 20px;
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
	if( i > 2)
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
						<td><a href="bbs" target="_blank"><img src="./images/wenzi_09.jpg"/></a></td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<div align="center" style="position:relative">
					<img id="background" src="images/1.jpg" style="z-index:-1;width:100%;height:100%"></img>
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
<script type="text/javascript">
var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://" : " http://");
document.write(unescape("%3Cscript src='" + _bdhmProtocol + "hm.baidu.com/h.js%3Fc7700af4e99fe594014ee20f00b5a448' type='text/javascript'%3E%3C/script%3E"));
</script>

</body>
</html>