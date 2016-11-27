<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>CX在线考试系统</title>
<link  rel="stylesheet" type="text/css" href="../css/page.css" />

</head>

<body >
<table width="100%" border="0" cellpadding="2" cellspacing="0">
  <tr>
    <th width="100%"  align="center">功能列表</th>
  </tr>
  <tr>
    <td align="center"><a href="testAction.jsp?action=query" target="mainFrame">考试</a></td>
  </tr>
  <tr>
    <td align="center"><a href="testResultAction.jsp" target="mainFrame">分数查看</a></td>
  </tr>
  <tr>
    <td align="center"><a href="password.jsp" target="mainFrame">密码修改</a></td>
  </tr>
   <tr>
    <td align="center"><a href="../login.jsp" target="_top">退出</a></td>
  </tr>
</table>
</body>
</html>
