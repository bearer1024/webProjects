<?php 
if (isset($_POST['submit'])) {
	include_once('connect.php'); 
	$name=$_POST['name2']; 
	$pwd=$_POST['password2'];
	$sql="SELECT * FROM xh_shangcheng_user WHERE name='$name'"; 
	$result=mysqli_query($con,$sql);
	$row = mysqli_fetch_array($result);  
	if(empty($row)){
		  header("refresh:0;url=xhshangcheng.php?info=Please Register!");
	}else{
		$password=$row['password'];
		if($password==$pwd){
		//处理登陆
			session_start();
			$_SESSION['name']=$row['name'];
			header("refresh:0;url=xhshangcheng.php");
		}else{
		//密码不正确
			header("refresh:0;url=xhshangcheng.php?info=Password is Wrong");
		}
	}
}
?> 