package com.service;

import java.sql.ResultSet;

import com.dao.DB;
import com.orm.TOrganization;
import com.orm.Yuangong;

public class liuService
{

	public static TOrganization getOrg(String id)
	{
		TOrganization organization=new TOrganization();
		
		if(id.equals("0"))
		{
			organization.setId("0");
		}
		else
		{
			String sql="select * from t_organization where id=?";
			Object[] params={id};
			DB mydb=new DB();
			try
			{
				mydb.doPstm(sql, params);
				ResultSet rs=mydb.getRs();
				rs.next();
				organization.setId(rs.getString("id"));
				organization.setName(rs.getString("name"));
				organization.setDescription(rs.getString("description"));
				rs.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			mydb.closed();
		}
		
		return organization;
	}
	
	
	
	public static Yuangong getYuangong(String id)
	{
		    Yuangong yuangong=new Yuangong();
		    String sql="select * from t_yuangong where id=?";
			Object[] params={id};
			DB mydb=new DB();
			try
			{
				mydb.doPstm(sql, params);
				ResultSet rs=mydb.getRs();
				rs.next();
				
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
				
				rs.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			mydb.closed();
		
		return yuangong;
	}
	
	
	public static String getYuangong_id_list(String org_id)
	{
		    StringBuffer yuangong_id_list=new StringBuffer("");
		
		    String sql="select * from t_yuangong where org_id=?";
			Object[] params={org_id};
			DB mydb=new DB();
			try
			{
				mydb.doPstm(sql, params);
				ResultSet rs=mydb.getRs();
				while(rs.next())
				{
					yuangong_id_list.append(rs.getInt("id"));
					yuangong_id_list.append(",");
				}
				
				rs.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			mydb.closed();
			
		    return yuangong_id_list.substring(0,yuangong_id_list.length()-1);
	}
}
