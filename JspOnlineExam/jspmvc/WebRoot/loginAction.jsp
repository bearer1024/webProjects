<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.gxa.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<jsp:useBean id="logon" class="com.gxa.bean.Logon" scope="request">
<jsp:setProperty name="logon" property="*"/>
</jsp:useBean>
<body>
<%
//获取用户密码
String username = logon.getUsername();
String password = logon.getPassword();

//检查用户密码
MD5 m = new MD5();
password = m.getMD5ofStr(password);
if(AdminInfoUtil.checkAdmin(username, password)){
	logon.setPassword(password);
    session.setAttribute("admin",logon);
//跳转
    response.sendRedirect("admin/");//成功
}
else{
    out.println("<script language=\"javascript\">");
				 out.println("alert(\"用户名或密码不对,您不能登陆!\");");
				 out.println("window.location='adminLogin.jsp'");
				  out.println("</script>");
}

%>
</body>
</html>