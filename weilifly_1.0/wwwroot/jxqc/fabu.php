<?php 
if (isset($_POST['submit'])) {
	# code...
include_once('connect.php'); 
include_once('uploadclass.php'); 
$title=$_POST['title']; 
$miaoshu=$_POST['miaoshu'];
$phone=$_POST['phone'];
$price=$_POST['price'];
$time=date('Y-m-d H:i:s');
session_start();
$author=$_SESSION['name'];
$pic=$uploadfile; 
if($title == "") 
echo"<Script>window.alert('sorry');history.back()</Script>"; 
$sql="insert into xh_shangcheng_chanpin(title,pic,miaoshu,time,author,price,phone) values('$title','$pic','$miaoshu','$time','$author','$price','$phone')"; 
mysqli_query($con,$sql); 
header("refresh:0;url=xhshangcheng.php");
mysqli_close($con);
}

//echo"<Script>window.alert('信息添加成功');location.href='upload.php'</Script>"; 

?> 