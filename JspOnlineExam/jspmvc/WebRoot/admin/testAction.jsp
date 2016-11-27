<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.*,com.gxa.bean.*,com.gxa.dao.*,com.gxa.util.*" %>
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
	TestPaperDao tpDao = new TestPaperDao();
	if (action == null||"query".equals(action)) {//查询
		String tName = request.getParameter("tName");
		List list = tpDao.getPaperList(tName);
		request.setAttribute("list", list);
%>
<!-- 采用标签forward -->
    <jsp:forward page="test_view.jsp"></jsp:forward>
<%
	} else if ("del".equals(action)) {//删除
		String[] idsStr = request.getParameterValues("id");
		for (int i = 0; i < idsStr.length; i++) {
			try {
				//调用删除方法
				tpDao.delete(Integer.parseInt(idsStr[i]));

			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//出错处理
				request.setAttribute("error", "删除失败:" + e.getMessage());
				request.getRequestDispatcher("/admin/testAction.jsp?actoin=query")
						.forward(request, response);
				break;
			}
		}
		response.sendRedirect("testAction.jsp");//跳转到查询

	}
	
	//进入编辑界面 
	else if ("preEdit".equals(action)){
		int id = 0;
		  try{
			  id = Integer.parseInt(request.getParameter("id"));
		  }catch(Exception e){
			  out.println(e);
		  }
		  TestPaper testPaper = tpDao.getById(id);
		  request.setAttribute("id", id);
		  request.setAttribute("testPaper", testPaper);
		  request.getRequestDispatcher("/admin/test_edit.jsp").forward(request, response);
		  
	}
	else if("preView".equals(action)){
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
	else {//编辑(添加或者删除)
			//取得表单数据并封装
		TestPaper testPaper = new TestPaper();
		int id = Integer.parseInt(request.getParameter("id"));
		String tName = request.getParameter("tName");
		DateTools dateTools = new DateTools();
		Date startTime = dateTools.convertStringtoDate(request.getParameter("startTime"),"yyyy-MM-dd HH:mm");
		Date endTime = dateTools.convertStringtoDate(request.getParameter("endTime"),"yyyy-MM-dd HH:mm");
		
		//封装试卷数据
		testPaper.setId(id);
		testPaper.settName(tName);
		testPaper.setStartTime(startTime);
		testPaper.setEndTime(endTime);
		//封装选项数据
		String[] qIds = request.getParameterValues("qId");
		for (int i = 0; i < qIds.length; i++) {
			Questions questsions = new Questions();
			questsions.setId(Integer.parseInt(qIds[i]));
			testPaper.getQuestionsList().add(questsions);
		}
		//如果添加失败，会forward到修改页面，那么编辑前的数据会保存
		request.setAttribute("id", id);
		request.setAttribute("testPaper", testPaper);
		
		/**
		时间检验代码请在这编写
		*/
		if (tpDao.isExist(tName, id)) {
			request.setAttribute("error", "试卷名已存在");
			request.getRequestDispatcher("/admin/test_edit.jsp").forward(request, response);
		}else if(endTime.getTime()<startTime.getTime()||startTime.getTime()<System.currentTimeMillis()){
			request.setAttribute("error", "时间设置有问题,结束时间必须大于开始时间，并且开始时间必须大于 当前时间");
			request.getRequestDispatcher("/admin/test_edit.jsp").forward(request, response);
		}
		else {

			
			if ("add".equals(action)) {//添加
				tpDao.add(testPaper);
			} else if ("update".equals(action)) {
				tpDao.update(testPaper);
			}
			response.sendRedirect("testAction.jsp");//跳转到查询
		}
	}
%>
</body>
</html>