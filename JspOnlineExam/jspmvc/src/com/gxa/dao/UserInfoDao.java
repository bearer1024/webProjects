package com.gxa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.gxa.bean.UserInfo;
import com.gxa.common.DBManager;

public class UserInfoDao {

	/**
	 * 保存注册信息
	 * @param userInfo
	 * @throws Exception 
	 */
	public void add(UserInfo userInfo) throws Exception{
		Connection conn = null;
		
		PreparedStatement pst = null;
		try{
			conn = DBManager.getConnection();
			//sql语句
			String sql = "insert into USER_INFO (username, pwd, name, sex, telephone, email ,remark) values" +
					"(?, ? ,?, ?,?, ? ,?)";
			pst = conn.prepareStatement(sql);
			//设置参数
			pst.setString(1, userInfo.getUsername());
			pst.setString(2, userInfo.getPwd());
			pst.setString(3, userInfo.getName());
			pst.setString(4, userInfo.getSex());
			pst.setString(5, userInfo.getTelephone());
			pst.setString(6, userInfo.getEmail());
			pst.setString(7, userInfo.getRemark());
			
			pst.executeUpdate();//执行
		}catch(Exception e){
			e.printStackTrace();
			throw e;
			
		}
		//最后关闭语句和连接
		finally{
			if(pst!=null){
				try {
					pst.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(conn != null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
	}
	
	/**
	 * 检查用户名是否存在 
	 * @param username
	 * @param id
	 * @return true 存在  false 不存在
	 */
	public boolean isExistUsername(String username,int id){
        Connection conn = null;
		
		PreparedStatement pst = null;
		try{
			conn = DBManager.getConnection();
			//sql语句
			String sql = "select * from USER_INFO where username = '"+username+"' and id <>"+id;
			pst = conn.prepareStatement(sql);
			//设置参数
		
			
			ResultSet rs = pst.executeQuery();
			if(rs.next()){
				return true;
			}
			
		}catch(Exception e){
			e.printStackTrace();
			
		}
		//最后关闭语句和连接
		finally{
			if(pst!=null){
				try {
					pst.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(conn != null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
		return false;
	}
	
	
	

	/**
	 * 登陆方法
	 * @param username
	 * @param pwd
	 * @return
	 */
	public UserInfo login(String username ,String pwd){
		  Connection conn = null;
			
			PreparedStatement pst = null;
			try{
				//获取连接
				conn = DBManager.getConnection();
				//sql语句
				/*这里最好使用占位符?设参数的方式查询，避免sql注入，比如这里我们如果写成
				 * sql = "select * from USER_INFO where username = '"+username+"' and pwd= '"+pwd+"'";
				 * 那么登陆的时候username传值为a' or '1'='1,pwd传值为a' or '1'='1,sql连接后语句变为
				 * "select * from USER_INFO where username='a' or '1'='1' and pwd='a' or '1'='1'，那么这条sql语句一定成立的
				 * 这样用户 就能登陆系统，这是我们要避免的
				 * */
				String sql = "select * from USER_INFO where username = ? and pwd= ?";
				pst = conn.prepareStatement(sql);
				pst.setString(1, username);
				pst.setString(2, pwd);
				//设置参数
			
				
				ResultSet rs = pst.executeQuery();
				if(rs.next()){
					UserInfo userInfo = new UserInfo();
					userInfo.setId(rs.getInt("id"));
					userInfo.setUsername(rs.getString("username"));
					userInfo.setPwd(rs.getString("pwd"));
					userInfo.setName(rs.getString("name"));
					userInfo.setSex(rs.getString("sex"));
					userInfo.setEmail(rs.getString("email"));
					userInfo.setTelephone(rs.getString("telephone"));
					userInfo.setRemark(rs.getString("remark"));
					return userInfo;
				}
				
			}catch(Exception e){
				e.printStackTrace();
				
			}
			//最后关闭语句和连接
			finally{
				if(pst!=null){
					try {
						pst.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(conn != null){
					try {
						conn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
			
			return null;
	}
	
	/**
	 * 查询方法
	 * @param username 用户名
	 * @param name  姓名 
	 * @param start 开始记录数
	 * @param pageLine 每页显示数
	 * @return
	 */
	public List getUserList(String username,String name ,int start ,int pageLine){
		Connection conn = null;
		if (username == null) {// 为null的情况要考虑不然在拼接sql的时候会出问题
			username = "";
		}
		if (name == null) {
			name = "";
		}

		List list = new ArrayList();

		PreparedStatement pst = null;
		try {
			// 获取连接
			conn = DBManager.getConnection();
			// sql语句
			
			//-------------------------------查询记录列表-------------------------------------
			String sql = "select * from USER_INFO where username like ? and name like ? limit "
					+ start + "," + pageLine;
			pst = conn.prepareStatement(sql);
			pst.setString(1, "%"+username+"%");
			pst.setString(2, "%"+name+"%");
			// 设置参数

			ResultSet rs = pst.executeQuery();
			//取得记录
			while (rs.next()) {
				UserInfo userInfo = new UserInfo();
				userInfo.setId(rs.getInt("id"));
				userInfo.setUsername(rs.getString("username"));
				userInfo.setPwd(rs.getString("pwd"));
				userInfo.setName(rs.getString("name"));
				userInfo.setSex(rs.getString("sex"));
				userInfo.setEmail(rs.getString("email"));
				userInfo.setTelephone(rs.getString("telephone"));
				userInfo.setRemark(rs.getString("remark"));
				list.add(userInfo);//将userINfo添加到list中去
			}
			rs.close();
			pst.close();
			
			//----------------------------------------查询总记录数----------------------------------
			pst = conn.prepareStatement("select count(*) from USER_INFO where username like ? and name like ?");
			pst.setString(1, "%"+username+"%");
			pst.setString(2, "%"+name+"%");
			rs = pst.executeQuery();
            if(rs.next()){
            	//只有一列，可以直接用序号取得,将总记录数加到list的最后一条记录,可用list.remove(list.size()-1)方法获得并删除
            	list.add(rs.getInt(1));
            }
            rs.close();
		} catch (Exception e) {
			e.printStackTrace();

		}
		// 最后关闭语句和连接
		finally {
			if (pst != null) {
				try {
					pst.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

		return list;
	}
	
	/**
	 * 删除方法
	 * @param userId
	 * @throws Exception
	 */
	public void delete(int userId)throws Exception{
		Connection conn = null;
		
		Statement stmt = null;
		
		boolean autoCommit = true;//默认提交方式 
		try{
			conn = DBManager.getConnection();
			autoCommit = conn.getAutoCommit();//取得默认提交方式
			conn.setAutoCommit(false);
			stmt = conn.createStatement();//
			stmt.executeUpdate("delete from TEST_RESULT where userId="+userId);
			stmt.executeUpdate("delete from USER_INFO where id="+userId);
			
			conn.commit();//提交事务
		}
		catch(Exception e){
			conn.rollback();//回滚
			e.printStackTrace();
			throw e;
		}
		finally{
			if(stmt!=null){
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(conn!=null){
				conn.setAutoCommit(autoCommit);
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 修改密码
	 * @param pwd
	 */
	public void updatePwd(String pwd, int userId) {
		Connection conn = null;

		Statement stmt = null;

		try {
			conn = DBManager.getConnection();
			stmt = conn.createStatement();//
			stmt.executeUpdate("update USER_INFO where pwd='" + pwd
					+ "' where id=" + userId);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
}
