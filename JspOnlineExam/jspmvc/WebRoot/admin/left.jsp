<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link  rel="stylesheet" type="text/css" href="../css/page.css" />

</head>
 
<body>
<table width="144" border="0" cellpadding="4" cellspacing="0">
 <tr>
    <th class="menu_title" width="139" align="center">考试管理</th>
  </tr>
  <tr>
    <td align="center"><a href="questionAction.jsp" target="mainFrame">题库管理</a></td>
  </tr>
  <tr>
    <td align="center"><a href="testAction.jsp" target="mainFrame">试卷管理</a></td>
  </tr>
  <tr>
    <td align="center"><a href="testResultAction.jsp" target="mainFrame">考试结果管理</a></td>
  </tr>

   <tr>
    <th class="menu_title" width="139" align="center">基础信息</th>
  </tr>

  <tr>
    <td align="center" ><a href="userInfoServlet" target="mainFrame">注册用户管理</a></td>
  </tr>
  <tr>
    <th class="menu_title" width="139" align="center">系统管理</th>
  </tr>
  <tr>
    <td align="center"><a href="password.jsp" target="mainFrame">密码修改</a></td>
  </tr>
   <tr>
    <td align="center"><a href="../adminLogin.jsp" target="_top">退出</a></td>
  </tr>
</table>
</body>
</html>
