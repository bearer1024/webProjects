<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.*,com.gxa.bean.*,com.gxa.dao.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	request.setCharacterEncoding("UTF-8");
	String action = request.getParameter("action");
	QuestionsDao questionsDao = new QuestionsDao();
	if (action == null||"query".equals(action)) {//查询
		String qName = request.getParameter("qName");
		List list = questionsDao.getQuestionsList(qName);
		request.setAttribute("list", list);
%>
<!-- 采用标签forward -->
    <jsp:forward page="question_view.jsp"></jsp:forward>
<%
	} else if ("del".equals(action)) {
		String[] idsStr = request.getParameterValues("id");
		for (int i = 0; i < idsStr.length; i++) {
			try {
				//调用删除方法
				questionsDao.delete(Integer.parseInt(idsStr[i]));

			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//出错处理
				
				request.setAttribute("error", "删除失败:该题目正在被使用，不能删除 ");
				
				request.getRequestDispatcher("/admin/questionAction.jsp?action=query")
						.forward(request, response);
				return;
			}
		}
		response.sendRedirect("questionAction.jsp");//跳转到查询

	} 
	else if ("preEdit".equals(action)){
		int id = 0;
		  try{
			  id = Integer.parseInt(request.getParameter("id"));
		  }catch(Exception e){
			  out.println(e);
		  }
		  Questions questions = questionsDao.getById(id);
		  request.setAttribute("id", id);
		  request.setAttribute("questions", questions);
		  request.getRequestDispatcher("/admin/question_edit.jsp").forward(request, response);
	}
	else {//编辑(添加或者删除)
			//取得表单数据并封装
		Questions questions = new Questions();
	//封装数据
		int id = Integer.parseInt(request.getParameter("id"));
		String qName = request.getParameter("qName");//题目名称
		String qAnwser = request.getParameter("qAnwser");//题目答案
		if (questionsDao.isExist(qName, id)) {
			request.setAttribute("error", "题目名已存在");
			request.getRequestDispatcher("/admin/question_edit.jsp")
					.forward(request, response);
		} else {

			questions.setId(id);
			questions.setqName(qName);
			questions.setqAnwser(qAnwser);
			//封装选项数据
			String[] oNos = request.getParameterValues("oNo");
			String[] oNames = request.getParameterValues("oName");
			for (int i = 0; i < oNos.length; i++) {
				if ("".equals(oNos[i])) {//去除空选项
					continue;
				}
				Options options = new Options();
				options.setoNo(oNos[i]);
				options.setoName(oNames[i]);
				questions.getOptions().add(options);
			}
			if ("add".equals(action)) {//添加
				questionsDao.add(questions);
			} else if ("update".equals(action)) {
				questionsDao.update(questions);
			}
			response.sendRedirect("questionAction.jsp");//跳转到查询
		}
	}
%>
</body>
</html>