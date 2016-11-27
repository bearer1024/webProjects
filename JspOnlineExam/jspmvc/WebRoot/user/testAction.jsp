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
	request.setCharacterEncoding("UTF-8");
    UserInfo user = (UserInfo)session.getAttribute("user");//取得用户
	String action = request.getParameter("action");

	TestPaperDao tpDao = new TestPaperDao();
	if (action == null||"query".equals(action)) {//查询
		List list = tpDao.showPaperList(user.getId());
		request.setAttribute("list",list);
%>
<!-- 采用标签forward -->
    <jsp:forward page="test_list_view.jsp"></jsp:forward>
<%
	} 
	else if("doTest".equals(action)){
		int id = 0;
		  try{
			  id = Integer.parseInt(request.getParameter("id"));
		  }catch(Exception e){
			  out.println(e);
		  }
		  TestPaper testPaper = tpDao.getById(id);
		  request.setAttribute("testPaper", testPaper);
		  request.getRequestDispatcher("/inc/testPaper.jsp").forward(request, response);
	}
	else if("submitTest".equals(action)){
		TestPaper testPaper = new TestPaper(); 
		int id = Integer.parseInt(request.getParameter("id"));
		testPaper.setId(id);
		
		Enumeration et = request.getParameterNames();//取得所有提交的parameter参数
		while(et.hasMoreElements()){//取得下一个参数
			String pName = (String)et.nextElement();//参数名
			if(pName.startsWith("qId")){
				String value = request.getParameter(pName);
				int qId = Integer.parseInt(pName.substring(3));
				//构建，获取提交的答案,并封装为试卷
				Questions questions = new Questions();
				questions.setId(qId);
				questions.setqAnwser(value);
				testPaper.getQuestionsList().add(questions);
				
			}
		}
		
		TestPaperService ts = new TestPaperService();
		float mark = ts.mark(testPaper);//得到分数
		
		TestResult testResult = new TestResult();
		testResult.setTestPaper(new TestPaper(id));//试卷id
		
		testResult.setUserInfo(user);//用户id
		testResult.setMark(mark);//分数
		//保存
		new TestResultDao().add(testResult);
		//跳转到分数查询页面
	    out.println("<script language=\"javascript\">");
		 out.println("alert(\"试卷提交完成!\");");
		 out.println("window.location='testResultAction.jsp'");
		  out.println("</script>");
	
		
		
		
	}
%>
</body>
</html>