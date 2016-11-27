<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*" errorPage="" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title></title>
<link  rel="stylesheet" type="text/css" href="css/page.css" />
</head>
<div class="backdoor">
<body>
<%session.invalidate(); %>
<br />
<br />
<br />
<br />
<br />

<form name="" action="loginAction.jsp" method="post" >
<div class="tablecss">
<table width="300" border="0" align="center" cellpadding="2" cellspacing="0">
  <tr>
    <th height="80px" colspan="2" align="center">管理员登录 </th>
  </tr>
<tr>
    <td height="50px" width="73" align="right">用户名：</td>
    <td width="218"><input class="inputcss"type="text" id="username" name="username" /></td>
  </tr>
  <tr>
    <td height="50px" align="right">密码：</td>
    <td> <input class="inputcss" type="password" name="password"  id="password" size="22" maxlength="16"/></td>
  </tr>
  <tr>
    <td height="50px" colspan="2" align="center"><input onclick="return check();" type="submit" name="Submit" value="登 录" />
      &nbsp;&nbsp;&nbsp;&nbsp;
      <input name="Submit2" type="reset" value="重 置" /></td>
  </tr>
</table>
</div>
</form>
<script language="javascript">
    function check(){
        if(document.forms[0].username.value==""|| document.forms[0].password.value==""){
            alert("请输入用户名密码");
            return false;
        }
        return true;
    }
</script>
</body>
</div>
</html>
