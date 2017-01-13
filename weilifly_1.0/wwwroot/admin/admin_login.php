<?php
 if (isset($_POST['submit'])){
	$con = mysql_connect("localhost","weilifly","szwlkj2013");
	if (!$con)
	  {
	  die('Could not connect: ' . mysql_error());
	  }
	mysql_select_db("weilifly", $con);
	mysql_query("set names utf8"); 
	//获取跳转的上级页面
	//$prepage=$_SERVER['HTTP_REFERER'];
	$name=$_POST['name'];
	$pwd=$_POST['pwd'];
	$sql="SELECT * FROM admin where user_name='$name'";
	$result=mysql_query($sql);
	$row = mysql_fetch_array($result);  
	if(empty($row)){
		header("refresh:0;url=admin_login.php?info=Only Admin Can Login!");
	}else{
		$password=$row['pwd'];
		if($pwd==$password){
		//处理登陆
		session_start();
		$_SESSION['username']=$row['user_name'];
		header("refresh:0;url=admin_index.php");//跳转页面，注意路径
		}else{
			//密码不正确
			header("refresh:0;url=admin_login.php?info=Password is incorrect");
		}
	}

	MYSQL_CLOSE();
	}
	if (isset($_GET['info'])) {
        echo "<p align='center'>".$_GET['info']."</p>";
    }
?>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>管理员登录</title>
     <style type="text/css"> 
            body{
            background-color: #FFFFFF;
            }
            p{
                font-size: 25px;
                color: red;
            }
            #register{
                width: 450px;
                height: 220px;
                background-color: #FFFFAA;
                position:absolute;
                top:50%;
                left:50%;
                margin:-150px 0 0 -200px;
            }
            .table { 
                
                border-collapse:collapse;    
                font-size:20px; 
            } 
            .input { 
                width:210px; 
                height:20px; 
            } 
            .stats1 { 
                color:#aaa;                    
            } 
            .stats2 { 
                color:#000; 
            } 
            .stats3 { 
                color:red; 
            } 
            .stats4 { 
                color:green; 
            }
            span{
            	width: 200px;
            	font-size: 10px;
            } 
            #namerepeat{
            	color:red;
            	font-size: 8px;
            }
        </style> 
</head>
<body>
	 <div id="register"> 
        <form action="admin_login.php" method="post" onSubmit="return regCheck('click')"> 
        <table border="0" align="center" width="500" class="table"> 
            <tr>
                <p align="center">管理员请登录</p>
            </tr>
            <tr> 
                <td width="120" height="25" align="right">管理员账号：</td><td><input type="text" name="name" class="input" /><span class="stats1"></span></td> 
            </tr> 
            <tr> 
                <td width="120" height="25" align="right">密码：</td><td><input type="password" name="pwd" class="input" /> <span class="stats1"></span></td> 
            </tr> 
            <tr> 
                <td height="30"></td><td align="left"><input type="submit" name="submit" value="提交" /> <input type="reset" value="重置" /></td> 
            </tr> 
        </table> 
        </form> 
        </div> 
</body>
</html>
<script language="javascript"> 
// 找到 input 后面的 SPAN 标签 
function gspan(cobj){ 
    while(true){ 
        if(cobj.nextSibling.nodeName!="SPAN") 
            cobj=cobj.nextSibling; 
        else 
            return cobj.nextSibling;     
    } 
} 
 
function check(obj, info, fun, click){ 
    var sp=gspan(obj); 
    obj.onfocus=function(){  
        sp.innerHTML=info; 
        sp.className="stats2"; 
         
    } 
 
    obj.onblur=function(){ 
        if(fun(this.value)){ 
            sp.innerHTML="输入正确√"; 
            sp.className="stats4"; 
             
        }else{ 
            sp.innerHTML=info; 
            sp.className="stats3"; 
        } 
    } 
 
    if(click=="click") 
        obj.onblur(); 
} 
 
//页面加载完自动调用 
onload=regCheck 
function regCheck(click){ 
 
var stat=true; 
    var username=document.getElementsByName("name")[0]; 
    var password=document.getElementsByName("pwd")[0]; 
 
        // 验证用户名 
    check(username, "用户名不能为空！", function(val){ 
 
        if(val.match(/^\S+$/) && val.length >=1 && val.length <=20){ 
            return true; 
        }else{ 
            stat=false; 
            return false; 
        } 
    }, click); 
         
        // 验证密码 
    check(password, "用户密码必须在6-20位之间！", function(val){ 
        if(val.match(/^\S+$/) && val.length >=6 && val.length <=20){ 
            return true; 
        }else{ 
            stat=false; 
            return false; 
        } 
    }, click);    
 
    return stat; 
} 
</script>
