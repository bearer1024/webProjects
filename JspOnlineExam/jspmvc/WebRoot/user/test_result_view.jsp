<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.util.*" errorPage="" %>
<%@ page isELIgnored ="false"%> 
  <%@ page import="com.gxa.bean.*,com.gxa.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title></title>
<link  rel="stylesheet" type="text/css" href="../css/page.css" />
</head>

<body>
<form action=""  method="post">
<table width="800" border="0" align="center" cellpadding="2" cellspacing="0">
 <tr>
     <td  colspan="4" align="left" >已考试卷</td>
  </tr>

  
  <tr>
    <th>考试科目</th>
    <th>考试日期</th>
    <th>考试分数</th>
  </tr>
    <%
  List list = (List)request.getAttribute("list");
    DateTools dateTools = new DateTools();
  for(int i=0;i<list.size();i++){
	  TestResult testResult = (TestResult)list.get(i);
  %>
  <tr>
    <td align="center"><%=testResult.getTestPaper().gettName() %></td>
    <td align="center"><%=dateTools.convertDateToString(testResult.getTestPaper().getStartTime(), "yyyy-MM-dd HH:mm") %></td>
     <td align="center"><%=testResult.getMark() %></td>
  </tr>
  <%} %>
</table>
</form>
</body>
</html>
