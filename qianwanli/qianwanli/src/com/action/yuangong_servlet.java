package com.action;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.DB;
import com.orm.Yuangong;
import com.service.liuService;

public class yuangong_servlet extends HttpServlet
{ 
	public void service(HttpServletRequest req,HttpServletResponse res)throws ServletException, IOException 
	{
		String type=req.getParameter("type");
		
		if(type.endsWith("yuangongAdd"))
		{
			yuangongAdd(req, res);
		}
		if(type.endsWith("yuangongMana"))
		{
			yuangongMana(req, res);
		}
		if(type.endsWith("yuangongDel"))
		{
			yuangongDel(req, res);
		}
		if(type.endsWith("yuangongQuanxian"))
		{
			yuangongQuanxian(req, res);
		}
		
		if(type.endsWith("yuangongByOrg"))
		{
			yuangongByOrg(req, res);
		}
		if(type.endsWith("yuangongGet"))
		{
			yuangongGet(req, res);
		}
	}
	
	
	
	
	public void yuangongAdd(HttpServletRequest req,HttpServletResponse res)
	{
		String id=String.valueOf(new Date().getTime());
//		String org_id=req.getParameter("org_id");
		String bianhao=req.getParameter("bianhao");
		String name=req.getParameter("name");
		String sex=req.getParameter("sex");
//		String quanxian="��ͨԱ��";
		String loginname=req.getParameter("loginname");
		String loginpw=req.getParameter("loginpw");
		String del="no";
		
		String sql="insert into t_yuangong values(?,?,?,?,?,?,?)";
		Object[] params={id,bianhao,name,sex,loginname,loginpw,del};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		mydb.closed();
		
		req.setAttribute("message", "success");
		req.setAttribute("path", "yuangong?type=yuangongMana");
		
        String targetURL = "/common/success.jsp";
		dispatch(targetURL, req, res);
	}
	
	
	
	public void yuangongDel(HttpServletRequest req,HttpServletResponse res)
	{
		String sql="update t_yuangong set del=? where id=?";
		Object[] params={"yes",req.getParameter("id")};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		mydb.closed();
		
		req.setAttribute("message", "success");
		req.setAttribute("path", "yuangong?type=yuangongMana");
		
        String targetURL = "/common/success.jsp";
		dispatch(targetURL, req, res);
	}
	
	
	public void yuangongQuanxian(HttpServletRequest req,HttpServletResponse res)
	{
		String quanxian=req.getParameter("quanxian");
		
		String sql="update t_yuangong set quanxian=? where id=?";
		Object[] params={quanxian,req.getParameter("id")};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		mydb.closed();
		
		req.setAttribute("msg", "���óɹ�");
        String targetURL = "/common/msg.jsp";
		dispatch(targetURL, req, res);
	}
	
	
	
	public void yuangongMana(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		List yuangongList=new ArrayList();
		String sql="select * from t_yuangong where del='no'";
		Object[] params={};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
				Yuangong yuangong=new Yuangong();
				
				yuangong.setId(rs.getString("id"));
				yuangong.setBianhao(rs.getString("bianhao"));
				yuangong.setName(rs.getString("name"));
				yuangong.setSex(rs.getString("sex"));
//				yuangong.setQuanxian(rs.getString("quanxian"));
				yuangong.setLoginname(rs.getString("loginname"));
				yuangong.setLoginpw(rs.getString("loginpw"));
//				yuangong.setOrg_id(rs.getString("org_id"));
				yuangong.setDel(rs.getString("del"));
//				yuangong.setOrganization(liuService.getOrg(rs.getString("org_id")));
				yuangongList.add(yuangong);
			}
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		
		req.setAttribute("yuangongList", yuangongList);
		req.getRequestDispatcher("admin/yuangong/yuangongMana.jsp").forward(req, res);
	}
	
	
	public void yuangongByOrg(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		List yuangongList=new ArrayList();
		String sql="select * from t_yuangong where del='no' and org_id=? order by org_id desc";
		Object[] params={req.getParameter("org_id")};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
				Yuangong yuangong=new Yuangong();
				
				yuangong.setId(rs.getString("id"));
				yuangong.setBianhao(rs.getString("bianhao"));
				yuangong.setName(rs.getString("name"));
				yuangong.setSex(rs.getString("sex"));
				yuangong.setQuanxian(rs.getString("quanxian"));
				yuangong.setLoginname(rs.getString("loginname"));
				yuangong.setLoginpw(rs.getString("loginpw"));
				yuangong.setOrg_id(rs.getString("org_id"));
				yuangong.setDel(rs.getString("del"));
				yuangong.setOrganization(liuService.getOrg(rs.getString("org_id")));
				yuangongList.add(yuangong);
			}
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		
		req.setAttribute("yuangongList", yuangongList);
		req.getRequestDispatcher("admin/yuangong/yuangongByOrg.jsp").forward(req, res);
	}
	
	
	public void yuangongGet(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		Yuangong yuangong=liuService.getYuangong(req.getParameter("yuangong_id"));
		String s=yuangong.getName()+"###"+yuangong.getOrganization().getName()+"###"+yuangong.getBianhao();
		res.getWriter().print(s);
	}
	
	
	/*public void yuangongEdit(HttpServletRequest req,HttpServletResponse res)
	{
		int id=Integer.parseInt(req.getParameter("id"));
		String name=req.getParameter("name");
		String sex=req.getParameter("sex");
		String age=req.getParameter("age");
		String tel=req.getParameter("tel");
		String address=req.getParameter("address");
		String zhiwei=req.getParameter("zhiwei");
		
		
		String sql="update t_yuangong set name=?,sex=?,age=?,tel=?,address=?,zhiwei=? where id="+id;
		Object[] params={name,sex,age,tel,address,zhiwei};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		mydb.closed();
		
		req.setAttribute("message", "�����ɹ�");
		req.setAttribute("path", "yuangong?type=yuangongMana");
		
        String targetURL = "/common/success.jsp";
		dispatch(targetURL, req, res);
	}*/
	
	
	
	public void dispatch(String targetURI,HttpServletRequest request,HttpServletResponse response) 
	{
		RequestDispatcher dispatch = getServletContext().getRequestDispatcher(targetURI);
		try 
		{
		    dispatch.forward(request, response);
		    return;
		} 
		catch (ServletException e) 
		{
                    e.printStackTrace();
		} 
		catch (IOException e) 
		{
			
		    e.printStackTrace();
		}
	}
	public void init(ServletConfig config) throws ServletException 
	{
		super.init(config);
	}
	
	public void destroy() 
	{
		
	}
}
