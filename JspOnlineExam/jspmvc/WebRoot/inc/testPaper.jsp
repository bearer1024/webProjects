<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.*,com.gxa.bean.*,com.gxa.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link  rel="stylesheet" type="text/css" href="../css/page.css" />
<title>Insert title here</title>
</head>
<body>
<%
    TestPaper testPaper = (TestPaper)request.getAttribute("testPaper");
	DateTools dateTools = new DateTools();
	if(testPaper==null){
		out.close();
	}
%>
<form action="testAction.jsp?action=submitTest" method="post">
    <input type="hidden" name="id" value="<%=request.getParameter("id")%>">
    <div align="center" style="color: #22536A;font-weight: bold;font-size: 20px;margin-bottom: 10px;" ><%=testPaper.gettName() %>
&nbsp;&nbsp;<span style="font-size: 16px;color:000000;">(考试时间:
<%=dateTools.convertDateToString(testPaper.getStartTime(), "HH:mm") %>
 -<%=dateTools.convertDateToString(testPaper.getEndTime(), "HH:mm") %>)
 </span></div>
    <%for(int i=0;i<testPaper.getQuestionsList().size();i++){
    	Questions questions = (Questions)testPaper.getQuestionsList().get(i);
    	%>
    <div class="<%="testPaper"+(i%2)%>">
       <span style="color: #22536A;font-weight: bold;font-size: 16px;"> <%=i+1 %>、<%=questions.getqName() %></span>
        <ul style="list-style: none;margin-left: 0px">
        <%for(Iterator it = questions.getOptions().iterator();it.hasNext();){
        	Options options = (Options)it.next();
        	%>
        <li><input type="radio" name="qId<%=questions.getId() %>" value="<%=options.getoNo() %>" /><%=options.getoNo() %>、<%=options.getoName() %></li>
       <%} %>
        </ul>
    </div>
    <%} %>
    <%if(session.getAttribute("user")!=null){ %>
    <input type="submit" value="提交试卷" >
    <%} %>
</form>
</body>
</html>