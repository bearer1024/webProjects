<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*" errorPage="" %>
<%@ page import="com.gxa.util.*"%>
<%@ page import="com.gxa.bean.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title></title>
<link  rel="stylesheet" type="text/css" href="../css/page.css" />
</head>

<body>
<%
Logon logon = (Logon)session.getAttribute("admin");

//update
String password = request.getParameter("password");
if(password!=null){
    if(logon.getPassword().equals(password)){
        //没有修改
    }else{
        MD5 m = new MD5();
        password = m.getMD5ofStr(password);
        logon.setPassword(password);
            AdminInfoUtil.setAdmin(logon.getUsername(), logon.getPassword());
            session.setAttribute("admin", logon);
    }
    %>
    <script language="javascript">
        alert("修改成功");
    </script>
    <%
}

 %>
<form name="" action="" method="post">
<table width="300" border="0" align="center" cellpadding="2" cellspacing="0">
  <tr>
    <th colspan="2" align="center">密码修改</th>
  </tr>
  <tr>
    <td width="73" align="right">用户名：</td>
    <td width="218"><%=logon.getUsername() %></td>
  </tr>
  <tr>
    <td align="right">密码：</td>
    <td><input name="password" type="password" id="password" value="<%=logon.getPassword() %>" ></td>
  </tr>
   <tr>
    <td align="right">重复密码：</td>
    <td><input name="confirmPwd" type="password" id="confirmPwd" value="<%=logon.getPassword() %>" ></td>
  </tr>
  <tr>
    <td colspan="2" align="center"><input name="BtnSave" type="button" id="BtnSave" onClick="check()" value="　保存　">
      &nbsp;&nbsp;&nbsp;&nbsp;
      <input name="Submit2" type="reset" value="重 置" /></td>
  </tr>
</table>
</form>
<script language="javascript">

function check(){
    if(document.forms[0].password.value!=document.forms[0].confirmPwd.value){
        alert("两次密码输入不一致");
        return false;
    }
    document.forms[0].submit();
}
</script>
</body>
</html>

