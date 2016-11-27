package com.action;

import java.io.IOException;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
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
import com.orm.Tgongwen;
import com.orm.Yuangong;

public class gongwen_servlet extends HttpServlet
{
	public void service(HttpServletRequest req,HttpServletResponse res)throws ServletException, IOException 
	{
        String type=req.getParameter("type");
		
		
		if(type.endsWith("gongwenAdd"))
		{
			
			gongwenAdd(req, res);
			
		}
		if(type.endsWith("gongwenDel"))
		{
			gongwenDel(req, res);
		}
		
		if(type.endsWith("addbiao"))
		{
			Addbiao(req, res);
		}
		if(type.endsWith("gongwenMana1"))
		{
			gongwenMana1(req, res);//法律
		}
		if(type.endsWith("gongwenMana2"))
		{
			gongwenMana2(req, res);//行政
		}
		if(type.endsWith("gongwenMana3"))
		{
			gongwenMana3(req, res);//上级文件
		}
		if(type.endsWith("gongwenMana4"))
		{
			gongwenMana4(req, res);//运行手册
		}
		if(type.endsWith("gongwenMana5"))
		{
			gongwenMana5(req, res);//管理员获取所有表的信息
		}
		if(type.endsWith("gongwenMana51"))
		{
			gongwenMana51(req, res);//员工获取自己上报填表的信息
		}
		if(type.endsWith("gongwenMana6"))
		{
			gongwenMana6(req, res);//培训课件
		}
		if(type.endsWith("shebei1"))
		{
			shebei1(req, res);//设备资料
		}
		if(type.endsWith("shebei2"))
		{
			shebei2(req, res);//设备管理
		}
		if(type.endsWith("richang1"))
		{
			richang1(req, res);//设备资料
		}
		if(type.endsWith("richang2"))
		{
			richang2(req, res);//设备管理
		}
		if(type.endsWith("update"))
		{
			updategongwen(req, res);//更新公文
		}
		if(type.endsWith("getgongwen"))
		{
			getgongwenbyid(req, res);//根据id获取公文
		}
	}
	
	public void getgongwenbyid(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		String id=req.getParameter("id");
		Tgongwen gongwen=new Tgongwen();
		String sql="select * from t_gongwen where id=?";//要根据type区分1，法律
		Object[] params={id};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
				
				gongwen.setId(rs.getString("id"));
				gongwen.setWenhao(rs.getString("wenhao"));
				gongwen.setBumen(rs.getString("bumen"));
				gongwen.setTitle(rs.getString("title"));
				
				gongwen.setFujian(rs.getString("fujian"));
				gongwen.setBeizhu(rs.getString("beizhu"));
				gongwen.setTianjiashi(rs.getString("tianjiashi"));
				gongwen.setFujianswfpath(rs.getString("fujianswfpath"));
				//gongwenList.add(gongwen);
		    }
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		req.setAttribute("gongwenbyid", gongwen);
		req.getRequestDispatcher("admin/gongwen/gongwenupdate.jsp").forward(req, res);
	}
	public void gongwenAdd(HttpServletRequest req,HttpServletResponse res)
	{
		
		String id=String.valueOf(new Date().getTime());
		String wenhao=req.getParameter("wenhao");
		//System.out.println(wenhao);
		String addtype=req.getParameter("addtype");
		//System.out.println("type:"+addtype);
		String bumen=req.getParameter("bumen");
		String title=req.getParameter("title");
		
		String fujian=req.getParameter("fujian");
		String beizhu=req.getParameter("beizhu");
		String fujianYuanshiming=req.getParameter("fujianYuanshiming");
		System.out.println(fujianYuanshiming);
		String tianjiashi=new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date());
		//这边也要用不同的type区分添加的时候
		String sql="insert into t_gongwen(id,wenhao,bumen,title,fujian,fujianswfpath,beizhu,tianjiashi,type) values(?,?,?,?,?,?,?,?,?)";
		Object[] params={id,wenhao,bumen,title,fujian,fujianYuanshiming,beizhu,tianjiashi,addtype};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		mydb.closed();
		
		req.setAttribute("msg", "success!");
		
        String targetURL = "/common/msg.jsp";
		dispatch(targetURL, req, res);
        
	}
	public void updategongwen(HttpServletRequest req,HttpServletResponse res)
	{
		String id=req.getParameter("gongwenid");
		String wenhao=req.getParameter("wenhao");
		//System.out.println(wenhao);
		String bumen=req.getParameter("bumen");
		String title=req.getParameter("title");
		String beizhu=req.getParameter("beizhu");
		String sql="update t_gongwen set wenhao=?,bumen=?,title=?,beizhu=? where id=?";
		Object[] params={wenhao,bumen,title,beizhu,id};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		mydb.closed();
		
		req.setAttribute("msg", "success");
		
        String targetURL = "/common/msg.jsp";
		dispatch(targetURL, req, res);
	}
	public void Addbiao(HttpServletRequest req,HttpServletResponse res)
	{
		
		String id=String.valueOf(new Date().getTime());
		String wenhao=req.getParameter("wenhao");
		//System.out.println(wenhao);
		String bumen=req.getParameter("bumen");
		String title=req.getParameter("title");
		
		String fujian=req.getParameter("fujian");
		String beizhu=req.getParameter("beizhu");
		String fujianYuanshiming=req.getParameter("fujianYuanshiming");
		Yuangong yuangong=(Yuangong)req.getSession().getAttribute("yuangong");
		String userid=yuangong.getBianhao();
		//System.out.println(fujianYuanshiming);
		String tianjiashi=new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date());
		
		String sql="insert into t_gongwen(id,wenhao,bumen,title,fujian,fujianswfpath,beizhu,tianjiashi,type,whois) values(?,?,?,?,?,?,?,?,5,?)";
		Object[] params={id,wenhao,bumen,title,fujian,fujianYuanshiming,beizhu,tianjiashi,userid};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		mydb.closed();
		
		req.setAttribute("msg", "success!");
		
        String targetURL = "/common/msg.jsp";
		dispatch(targetURL, req, res);   
	}
	
	public void gongwenDel(HttpServletRequest req,HttpServletResponse res)
	{
		String id=req.getParameter("id");
		
		String sql="delete from t_gongwen where id=?";
		Object[] params={id};
		DB mydb=new DB();
		mydb.doPstm(sql, params);
		mydb.closed();
		
        req.setAttribute("msg", "success");
		
        String targetURL = "/common/msg.jsp";
		dispatch(targetURL, req, res);
	}
	public void gongwenMana1(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		List<Tgongwen> gongwenList=new ArrayList();
		String sql="select * from t_gongwen where type=1 order by tianjiashi desc";//要根据type区分1，法律
		Object[] params={};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
				Tgongwen gongwen=new Tgongwen();
				
				gongwen.setId(rs.getString("id"));
				gongwen.setWenhao(rs.getString("wenhao"));
				gongwen.setBumen(rs.getString("bumen"));
				gongwen.setTitle(rs.getString("title"));
				
				gongwen.setFujian(rs.getString("fujian"));
				gongwen.setBeizhu(rs.getString("beizhu"));
				gongwen.setTianjiashi(rs.getString("tianjiashi"));
				gongwen.setFujianswfpath(rs.getString("fujianswfpath"));
				gongwenList.add(gongwen);
		    }
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		
		req.setAttribute("gongwenList", gongwenList);
		req.getRequestDispatcher("admin/gongwen/gongwenMana.jsp").forward(req, res);
	}
	public void gongwenMana2(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		List<Tgongwen> gongwenList=new ArrayList();
		String sql="select * from t_gongwen where type=2 order by tianjiashi desc";//要根据type区分2，行政
		Object[] params={};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
				Tgongwen gongwen=new Tgongwen();
				
				gongwen.setId(rs.getString("id"));
				gongwen.setWenhao(rs.getString("wenhao"));
				gongwen.setBumen(rs.getString("bumen"));
				gongwen.setTitle(rs.getString("title"));
				
				gongwen.setFujian(rs.getString("fujian"));
				gongwen.setBeizhu(rs.getString("beizhu"));
				gongwen.setTianjiashi(rs.getString("tianjiashi"));
				gongwen.setFujianswfpath(rs.getString("fujianswfpath"));
				gongwenList.add(gongwen);
		    }
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		
		req.setAttribute("gongwenList", gongwenList);
		req.getRequestDispatcher("admin/gongwen/xingzheng.jsp").forward(req, res);
	}
	public void gongwenMana3(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		List<Tgongwen> gongwenList=new ArrayList();
		String sql="select * from t_gongwen where type=3 order by tianjiashi desc";//要根据type区分2，行政
		Object[] params={};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
				Tgongwen gongwen=new Tgongwen();
				
				gongwen.setId(rs.getString("id"));
				gongwen.setWenhao(rs.getString("wenhao"));
				gongwen.setBumen(rs.getString("bumen"));
				gongwen.setTitle(rs.getString("title"));
				
				gongwen.setFujian(rs.getString("fujian"));
				gongwen.setBeizhu(rs.getString("beizhu"));
				gongwen.setTianjiashi(rs.getString("tianjiashi"));
				gongwen.setFujianswfpath(rs.getString("fujianswfpath"));
				gongwenList.add(gongwen);
		    }
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		
		req.setAttribute("gongwenList", gongwenList);
		req.getRequestDispatcher("admin/gongwen/shangji.jsp").forward(req, res);
	}
	
	public void gongwenMana4(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		List<Tgongwen> gongwenList=new ArrayList();
		String sql="select * from t_gongwen where type=4 order by tianjiashi desc";//要根据type区分2，行政
		Object[] params={};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
				Tgongwen gongwen=new Tgongwen();
				
				gongwen.setId(rs.getString("id"));
				gongwen.setWenhao(rs.getString("wenhao"));
				gongwen.setBumen(rs.getString("bumen"));
				gongwen.setTitle(rs.getString("title"));
				
				gongwen.setFujian(rs.getString("fujian"));
				gongwen.setBeizhu(rs.getString("beizhu"));
				gongwen.setTianjiashi(rs.getString("tianjiashi"));
				gongwen.setFujianswfpath(rs.getString("fujianswfpath"));
				gongwenList.add(gongwen);
		    }
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		
		req.setAttribute("gongwenList", gongwenList);
		req.getRequestDispatcher("admin/gongwen/shouce.jsp").forward(req, res);
	}
	public void gongwenMana5(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		List<Tgongwen> gongwenList=new ArrayList();
		String sql="select * from t_gongwen where type=5 order by tianjiashi desc";//要根据type区分
		Object[] params={};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
				Tgongwen gongwen=new Tgongwen();
				
				gongwen.setId(rs.getString("id"));
				gongwen.setWenhao(rs.getString("wenhao"));
				gongwen.setBumen(rs.getString("bumen"));
				gongwen.setTitle(rs.getString("title"));
				
				gongwen.setFujian(rs.getString("fujian"));
				gongwen.setBeizhu(rs.getString("beizhu"));
				gongwen.setTianjiashi(rs.getString("tianjiashi"));
				gongwen.setFujianswfpath(rs.getString("fujianswfpath"));
				gongwenList.add(gongwen);
		    }
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		
		req.setAttribute("gongwenList", gongwenList);
		req.getRequestDispatcher("admin/gongwen/shangbao.jsp").forward(req, res);
	}
	public void gongwenMana51(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		Yuangong yuangong=(Yuangong)req.getSession().getAttribute("yuangong");
		String userid=yuangong.getBianhao();
		System.out.println(userid);
		List<Tgongwen> gongwenList=new ArrayList();
		String sql="select * from t_gongwen where type=5 and whois=? order by tianjiashi desc";//要根据type区分
		Object[] params={userid};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
				Tgongwen gongwen=new Tgongwen();
				
				gongwen.setId(rs.getString("id"));
				gongwen.setWenhao(rs.getString("wenhao"));
				gongwen.setBumen(rs.getString("bumen"));
				gongwen.setTitle(rs.getString("title"));
				
				gongwen.setFujian(rs.getString("fujian"));
				gongwen.setBeizhu(rs.getString("beizhu"));
				gongwen.setTianjiashi(rs.getString("tianjiashi"));
				gongwen.setFujianswfpath(rs.getString("fujianswfpath"));
				gongwenList.add(gongwen);
		    }
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		
		req.setAttribute("gongwenList", gongwenList);
		req.getRequestDispatcher("admin/gongwen/shangbao.jsp").forward(req, res);
	}
	public void gongwenMana6(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		List<Tgongwen> gongwenList=new ArrayList();
		String sql="select * from t_gongwen where type=6 order by tianjiashi desc";//要根据type区分2，行政
		Object[] params={};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
				Tgongwen gongwen=new Tgongwen();
				
				gongwen.setId(rs.getString("id"));
				gongwen.setWenhao(rs.getString("wenhao"));
				gongwen.setBumen(rs.getString("bumen"));
				gongwen.setTitle(rs.getString("title"));
				
				gongwen.setFujian(rs.getString("fujian"));
				gongwen.setBeizhu(rs.getString("beizhu"));
				gongwen.setTianjiashi(rs.getString("tianjiashi"));
				gongwen.setFujianswfpath(rs.getString("fujianswfpath"));
				gongwenList.add(gongwen);
		    }
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		
		req.setAttribute("gongwenList", gongwenList);
		req.getRequestDispatcher("admin/gongwen/kejian.jsp").forward(req, res);
	}
	public void shebei1(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		List<Tgongwen> gongwenList=new ArrayList();
		String sql="select * from t_gongwen where type=7 order by tianjiashi desc";//要根据type区分2，行政
		Object[] params={};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
				Tgongwen gongwen=new Tgongwen();
				
				gongwen.setId(rs.getString("id"));
				gongwen.setWenhao(rs.getString("wenhao"));
				gongwen.setBumen(rs.getString("bumen"));
				gongwen.setTitle(rs.getString("title"));
				
				gongwen.setFujian(rs.getString("fujian"));
				gongwen.setBeizhu(rs.getString("beizhu"));
				gongwen.setTianjiashi(rs.getString("tianjiashi"));
				gongwen.setFujianswfpath(rs.getString("fujianswfpath"));
				gongwenList.add(gongwen);
		    }
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		
		req.setAttribute("gongwenList", gongwenList);
		req.getRequestDispatcher("admin/shebei/ziliao.jsp").forward(req, res);
	}
	public void shebei2(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		List<Tgongwen> gongwenList=new ArrayList();
		String sql="select * from t_gongwen where type=8 order by tianjiashi desc";//要根据type区分2，行政
		Object[] params={};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
				Tgongwen gongwen=new Tgongwen();
				
				gongwen.setId(rs.getString("id"));
				gongwen.setWenhao(rs.getString("wenhao"));
				gongwen.setBumen(rs.getString("bumen"));
				gongwen.setTitle(rs.getString("title"));
				
				gongwen.setFujian(rs.getString("fujian"));
				gongwen.setBeizhu(rs.getString("beizhu"));
				gongwen.setTianjiashi(rs.getString("tianjiashi"));
				gongwen.setFujianswfpath(rs.getString("fujianswfpath"));
				gongwenList.add(gongwen);
		    }
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		
		req.setAttribute("gongwenList", gongwenList);
		req.getRequestDispatcher("admin/shebei/beijian.jsp").forward(req, res);
	}
	public void richang1(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		List<Tgongwen> gongwenList=new ArrayList();
		String sql="select * from t_gongwen where type=9 order by tianjiashi desc";//要根据type区分2，行政
		Object[] params={};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
				Tgongwen gongwen=new Tgongwen();
				
				gongwen.setId(rs.getString("id"));
				gongwen.setWenhao(rs.getString("wenhao"));
				gongwen.setBumen(rs.getString("bumen"));
				gongwen.setTitle(rs.getString("title"));
				
				gongwen.setFujian(rs.getString("fujian"));
				gongwen.setBeizhu(rs.getString("beizhu"));
				gongwen.setTianjiashi(rs.getString("tianjiashi"));
				gongwen.setFujianswfpath(rs.getString("fujianswfpath"));
				gongwenList.add(gongwen);
		    }
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		
		req.setAttribute("gongwenList", gongwenList);
		req.getRequestDispatcher("admin/yunxing/guifan.jsp").forward(req, res);
	}
	public void richang2(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
	{
		List<Tgongwen> gongwenList=new ArrayList();
		String sql="select * from t_gongwen where type=10 order by tianjiashi desc";//要根据type区分2，行政
		Object[] params={};
		DB mydb=new DB();
		try
		{
			mydb.doPstm(sql, params);
			ResultSet rs=mydb.getRs();
			while(rs.next())
			{
				Tgongwen gongwen=new Tgongwen();
				
				gongwen.setId(rs.getString("id"));
				gongwen.setWenhao(rs.getString("wenhao"));
				gongwen.setBumen(rs.getString("bumen"));
				gongwen.setTitle(rs.getString("title"));
				
				gongwen.setFujian(rs.getString("fujian"));
				gongwen.setBeizhu(rs.getString("beizhu"));
				gongwen.setTianjiashi(rs.getString("tianjiashi"));
				gongwen.setFujianswfpath(rs.getString("fujianswfpath"));
				gongwenList.add(gongwen);
		    }
			rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		mydb.closed();
		
		req.setAttribute("gongwenList", gongwenList);
		req.getRequestDispatcher("admin/yunxing/weihu.jsp").forward(req, res);
	}
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
