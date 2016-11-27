<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.util.*" errorPage="" %>
<%@ page isELIgnored ="false"%> 
  <%@ page import="com.gxa.bean.*,com.gxa.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title></title>
<link  rel="stylesheet" type="text/css" href="../css/page.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/select.js"></script>
</head>

<body>
<form name="" action="testResultAction.jsp" method="post" >
<table width="98%" border="0" align="center" cellpadding="2" cellspacing="0">
<tr>
    <td  colspan="3" align="left"  >考试排名</td>
  </tr>

   <tr>
    <td  colspan="3" align="left">
    <select name="tId">
    <%
    List testPaperList = (List)request.getAttribute("testPaperList");
    for(int i=0;i<testPaperList.size();i++){
    	TestPaper testPaper = (TestPaper)testPaperList.get(i);
    	%>
  
	 <option value="<%=testPaper.getId()%>"><%=testPaper.gettName() %></option>
	  <%  } %>
	</select>   <input type="submit" value="查  询 "/> </td>
  </tr>
 
  <tr>
    <th width="180" align="center">考试人员</th>
    <th width="160" align="center">分数</th>
    <th width="130" align="center">排名<!--  ↓↑--></th>
    </tr>
  <%
  List list = (List)request.getAttribute("list");
    DateTools dateTools = new DateTools();
  for(int i=0;i<list.size();i++){
	  TestResult testResult = (TestResult)list.get(i);
  %>
  <tr>
    <td align="center"><%=testResult.getUserInfo().getName() %></td>
    <td align="center"><%=testResult.getMark() %></td>
    <td align="center"><%=i+1 %></td>
    </tr>
    <%} %>

</table>


</form>
<script language="JavaScript" type="text/JavaScript">
SelectInd(document.forms[0].tId,<%=request.getParameter("tId")%>);
</script>
</body>
</html>
