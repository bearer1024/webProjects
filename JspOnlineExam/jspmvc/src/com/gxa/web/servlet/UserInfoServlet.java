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
		//���ñ���
				request.setCharacterEncoding("UTF-8");
				response.setCharacterEncoding("UTF-8");
				int pageLine = 20;//ÿҳ��ʾ��
				int curPage = 1;//��ǰҳ
				try{//��ȡ��ǰҳ
					if(request.getParameter("curPage")!=null){
						curPage = Integer.parseInt(request.getParameter("curPage"));	
					}
				}catch(Exception e){
					e.printStackTrace();
				}
				try{//��ȡÿҳ��ʾ��
					if(request.getParameter("pageLine")!=null){
						pageLine = Integer.parseInt(request.getParameter("pageLine"));	
					}
				}catch(Exception e){
					e.printStackTrace();
				}
				//��ÿҳ��ʾ������ǰҳ�浽curPage��
				request.setAttribute("pageLine", pageLine);
				request.setAttribute("curPage", curPage);

				String username = request.getParameter("username");
				String name = request.getParameter("name");
				//------------------------------------��ȡ��ѯ������--------------------------
				UserInfoDao userInfoDao = new UserInfoDao();
				List list = userInfoDao.getUserList(username, name, (curPage-1)*pageLine, pageLine);
				//�ܼ�¼�浽request��
				request.setAttribute("count", list.remove(list.size()-1));
				//��ѯ����浽request��
				request.setAttribute("list", list);
				
		        //��forward�ķ�ʽת���û� ��ʾҳ
				request.getRequestDispatcher("/admin/user_view.jsp").forward(request, response);
				
	}
	
	protected void del(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] strIds = request.getParameterValues("id");// ��ȡҪɾ����id
		UserInfoDao userInfoDao = new UserInfoDao();
		try {
			for (int i = 0; i < strIds.length; i++) {
				userInfoDao.delete(Integer.parseInt(strIds[i]));
			}
		}  catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("error", "ɾ������:"+e.getMessage());
		}
		
		query(request, response);
	}
}
