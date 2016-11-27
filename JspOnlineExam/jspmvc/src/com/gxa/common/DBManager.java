package com.gxa.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBManager {

	static DBConfigUtil cu = new DBConfigUtil();
	
	public static Connection getConnection() throws Exception{
		Properties prt = cu.getProperties();
		
		String driver = prt.getProperty("driver");
		String url = prt.getProperty("url");
		String user = prt.getProperty("user");
		String pwd = prt.getProperty("pwd");
		
		Class.forName(driver);
		
		Connection conn = DriverManager.getConnection(url,user,pwd);
	    return conn;
	}
	
	public static void main(String[] args) {
		try {
			Connection conn = DBManager.getConnection();
		    System.out.println(conn);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
