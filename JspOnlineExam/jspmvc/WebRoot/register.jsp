<%@ page contentType="text/html; charset=utf-8" language="java" import="java.util.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link  rel="stylesheet" type="text/css" href="css/page.css" />
</head>

<body>
<!--错误显示区域-->
 <font color="red">
 <%=request.getAttribute("error")==null?"": request.getAttribute("error")%>
 </font>
<form name="" action="register" method="post" onsubmit="return checkForm();">
<table width="800"  border="0" align="center" cellpadding="2" cellspacing="0">
  <tr align="right">
    <th colspan="4" align="center">用户注册</th>
  </tr>
  <tr>
    <td width="98" align="right">*用户名：</td>
    <td width="292" align="left"><input type="text" name="username" value="" /></td>
    <td width="94" align="right">*姓名：</td>
    <td width="299" align="left"><input type="text" name="name" value="" /></td>
  </tr>
  <tr>
    <td align="right">*密码：</td>
    <td align="left"><input type="password" name="pwd" value="" size="22" maxlength="16" /></td>
    <td align="right">*重复密码：</td>
    <td align="left"><input name="repwd" type="password" id="repwd" size="22" maxlength="16" /></td>
  </tr>
  <tr>
    <td align="right" >*性别：</td>
    <td align="left"><select  name="sex">
	                      <option value="男">男</option>
						  <option value="女">女</option>
	                 </select>  </td>
    <td align="right" >电话：</td>
    <td align="left"><input type="text" name="telephone" value="" /></td>
  </tr>
  <tr align="right">
   <td align="right">*邮箱地址：</td>
    <td colspan="3" align="left"><input type="text" name="email" value="" /></td>
  </tr>
  <tr align="right">
   <td align="right">备注：</td>
    <td colspan="3" align="left"><input type="text" name="remark" value="" style="width:98%" /></td>
  </tr>
   <tr align="right">
    <td colspan="4" align="center"><input type="submit" value="提 交"/>&nbsp;&nbsp;&nbsp;&nbsp;
      <input type="reset" value="重 置"/> &nbsp;&nbsp;&nbsp;&nbsp;<a href="login.jsp">登 录</a></td>
  </tr>
</table>
</form>


<script language="javascript">

   function checkForm(){
       if(document.forms[0].username.value==""){
	       alert("用户名不能为空");
		   return false;
	   }
	   
	    if(document.forms[0].name.value==""){
	       alert("姓名不能为空");
		   return false;
	   }
	   
	    if(document.forms[0].pwd.value==""){
	       alert("密码不能为空");
		   return false;
	   }
	   
	    if(document.forms[0].pwd.value!=document.forms[0].repwd.value){
            alert("两次密码输入不一致");
            return false;
        }
		 if(document.forms[0].name.value==""){
	       alert("邮箱不能为空");
		   return false;
	   }
	return true;
	   
   }
</script>
</body>
</html>
