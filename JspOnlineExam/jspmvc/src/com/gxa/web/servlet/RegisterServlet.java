package com.gxa.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gxa.bean.UserInfo;
import com.gxa.dao.UserInfoDao;
import com.gxa.util.MD5;

/**
 * Servlet implementation class RegisterServlet
 */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String username = request.getParameter("username");
		String pwd = request.getParameter("pwd");
		MD5 md5 = new MD5();
		pwd = md5.getMD5ofStr(pwd);
		String name =request.getParameter("name");
		String sex = request.getParameter("sex");
		String telephone = request.getParameter("telephone");
		String email = request.getParameter("email");
		String remark = request.getParameter("remark");
		UserInfo userInfo = new UserInfo();
		userInfo.setUsername(username);
		userInfo.setPwd(pwd);
		userInfo.setName(name);
		userInfo.setSex(sex);
		userInfo.setTelephone(telephone);
		userInfo.setEmail(email);
		userInfo.setRemark(remark);
		
		UserInfoDao userInfoDao = new UserInfoDao();
		if(userInfoDao.isExistUsername(username, 0)){
			request.setAttribute("error", "ע��ʧ��:�û��Ѵ���");
			request.getRequestDispatcher("/register.jsp").forward(request,response);
		}else{
			try {
				userInfoDao.add(userInfo);
				response.setContentType("text/html; charset=UTF-8"); 
				PrintWriter out = response.getWriter();
				out.println("<html><head></head><body>");
				out.print("<script type=\"text/javascript\" language=\"javascript\">");
				out.print("alert('ע��ɹ�');");
				out.print("window.location='login.jsp';");
				out.print("</script>");
				out.print("</body></html>");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				request.setAttribute("error", e.getMessage());
				request.getRequestDispatcher("/register.jsp").forward(request,response);
		
			}
		}
	}

}
