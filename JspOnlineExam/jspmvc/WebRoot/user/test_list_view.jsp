<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*" errorPage="" %>
    <%@ page import="java.util.*,java.text.*,java.util.Date,com.gxa.bean.*,com.gxa.dao.*,com.gxa.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title></title>
<link  rel="stylesheet" type="text/css" href="../css/page.css" />
</head>

<body>
<table width="800" border="0" align="center" cellpadding="2" cellspacing="0">
<tr>
    <th  colspan="3"  >考试列表</th>
  </tr>
  <tr>
    <th>考试名称</th>
    <th>开始时间</th>
    <th>结束时间</th>
  </tr>
 <%List list = (List)request.getAttribute("list");
  DateTools dateTools = new DateTools();
  for(int i=0;i<list.size();i++){
	  TestPaper testPaper = (TestPaper)list.get(i);
	
%>
<% 
String datetime=new SimpleDateFormat("yyyy-MM-dd HH:mm").format(Calendar.getInstance().getTime()); //获取系统时间 
DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
Date realdate = dateFormat.parse(datetime);
int comparestart = testPaper.getStartTime().compareTo(realdate);
int compareend = testPaper.getEndTime().compareTo(realdate);
%>
  <tr>
    <td align="center">
     
    	<% if(comparestart<=0 && compareend>=0)
    	{%>
        	<a href="testAction.jsp?action=doTest&id=<%=testPaper.getId() %>">
        <% }%>
        <%=testPaper.gettName() %></a>
                       </td>
    <td align="center"><%=dateTools.convertDateToString(testPaper.getStartTime(), "yyyy-MM-dd HH:mm") %></td>
    <td align="center"><%=dateTools.convertDateToString(testPaper.getEndTime(), "yyyy-MM-dd HH:mm") %></td>
  </tr>
  <%} %>
</table>
</body>
</html>
