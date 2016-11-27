<?php 
if (isset($_POST['submit'])) {
include_once('connect.php'); 
$password=$_POST['password1']; 
$name=$_POST['name1']; 
$qiye=$_POST['qiye']; 
$sql="insert into xh_shangcheng_user(name,password,qiye) values('$name','$password','$qiye')"; 
if (!mysqli_query($con,$sql)){
	  die('Error: ' . mysql_error());
	  header("refresh:0;url=xhshangcheng.php");
	  }else{
	  session_start();
	  $_SESSION['name']=$_POST['name1'];
	  header("refresh:0;url=xhshangcheng.php");
	  }
}
mysqli_close($con);
?> 