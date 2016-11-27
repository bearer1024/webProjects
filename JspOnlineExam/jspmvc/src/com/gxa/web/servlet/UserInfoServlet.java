package com.gxa.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gxa.dao.UserInfoDao;

/**
 * Servlet implementation class UserInfoServlet
 */
public class UserInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		if (action == null) {
			query(request, response);
		}
		else if("del".equals(action)){
			del(request, response);
		}
	}

	protected void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置编码
				request.setCharacterEncoding("UTF-8");
				response.setCharacterEncoding("UTF-8");
				int pageLine = 20;//每页显示数
				int curPage = 1;//当前页
				try{//获取当前页
					if(request.getParameter("curPage")!=null){
						curPage = Integer.parseInt(request.getParameter("curPage"));	
					}
				}catch(Exception e){
					e.printStackTrace();
				}
				try{//获取每页显示数
					if(request.getParameter("pageLine")!=null){
						pageLine = Integer.parseInt(request.getParameter("pageLine"));	
					}
				}catch(Exception e){
					e.printStackTrace();
				}
				//将每页显示数，当前页存到curPage中
				request.setAttribute("pageLine", pageLine);
				request.setAttribute("curPage", curPage);

				String username = request.getParameter("username");
				String name = request.getParameter("name");
				//------------------------------------获取查询的数据--------------------------
				UserInfoDao userInfoDao = new UserInfoDao();
				List list = userInfoDao.getUserList(username, name, (curPage-1)*pageLine, pageLine);
				//总记录存到request中
				request.setAttribute("count", list.remove(list.size()-1));
				//查询结果存到request中
				request.setAttribute("list", list);
				
		        //用forward的方式转到用户 显示页
				request.getRequestDispatcher("/admin/user_view.jsp").forward(request, response);
				
	}
	
	protected void del(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] strIds = request.getParameterValues("id");// 获取要删除的id
		UserInfoDao userInfoDao = new UserInfoDao();
		try {
			for (int i = 0; i < strIds.length; i++) {
				userInfoDao.delete(Integer.parseInt(strIds[i]));
			}
		}  catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("error", "删除出错:"+e.getMessage());
		}
		
		query(request, response);
	}
}
