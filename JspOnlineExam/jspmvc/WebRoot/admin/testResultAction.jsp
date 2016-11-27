<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.*,com.gxa.bean.*,com.gxa.dao.*,com.gxa.util.*,com.gxa.service.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
//用户 查询自己的考试 信息
	TestResultDao testResultDao = new TestResultDao();
    int tId = 0;
    if(request.getParameter("tId")!=null){
    	tId = Integer.parseInt(request.getParameter("tId"));
    }else{//默认查最新考试
    	
    }
    int userId = 0;
    List list = testResultDao.getReusltList(tId, userId);
    request.setAttribute("list", list);//结果
  //----------------------所有考试试卷----------------------------------
    TestPaperDao testPaperDao = new TestPaperDao();
    request.setAttribute("testPaperList", testPaperDao.getPaperList(""));
    
    request.getRequestDispatcher("/admin/result_view.jsp").forward(request, response);
    
%>
</body>
</html>