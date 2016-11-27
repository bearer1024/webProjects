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
	 * ����ע����Ϣ
	 * @param userInfo
	 * @throws Exception 
	 */
	public void add(UserInfo userInfo) throws Exception{
		Connection conn = null;
		
		PreparedStatement pst = null;
		try{
			conn = DBManager.getConnection();
			//sql���
			String sql = "insert into USER_INFO (username, pwd, name, sex, telephone, email ,remark) values" +
					"(?, ? ,?, ?,?, ? ,?)";
			pst = conn.prepareStatement(sql);
			//���ò���
			pst.setString(1, userInfo.getUsername());
			pst.setString(2, userInfo.getPwd());
			pst.setString(3, userInfo.getName());
			pst.setString(4, userInfo.getSex());
			pst.setString(5, userInfo.getTelephone());
			pst.setString(6, userInfo.getEmail());
			pst.setString(7, userInfo.getRemark());
			
			pst.executeUpdate();//ִ��
		}catch(Exception e){
			e.printStackTrace();
			throw e;
			
		}
		//���ر���������
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
	 * ����û����Ƿ���� 
	 * @param username
	 * @param id
	 * @return true ����  false ������
	 */
	public boolean isExistUsername(String username,int id){
        Connection conn = null;
		
		PreparedStatement pst = null;
		try{
			conn = DBManager.getConnection();
			//sql���
			String sql = "select * from USER_INFO where username = '"+username+"' and id <>"+id;
			pst = conn.prepareStatement(sql);
			//���ò���
		
			
			ResultSet rs = pst.executeQuery();
			if(rs.next()){
				return true;
			}
			
		}catch(Exception e){
			e.printStackTrace();
			
		}
		//���ر���������
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
	 * ��½����
	 * @param username
	 * @param pwd
	 * @return
	 */
	public UserInfo login(String username ,String pwd){
		  Connection conn = null;
			
			PreparedStatement pst = null;
			try{
				//��ȡ����
				conn = DBManager.getConnection();
				//sql���
				/*�������ʹ��ռλ��?������ķ�ʽ��ѯ������sqlע�룬���������������д��
				 * sql = "select * from USER_INFO where username = '"+username+"' and pwd= '"+pwd+"'";
				 * ��ô��½��ʱ��username��ֵΪa' or '1'='1,pwd��ֵΪa' or '1'='1,sql���Ӻ�����Ϊ
				 * "select * from USER_INFO where username='a' or '1'='1' and pwd='a' or '1'='1'����ô����sql���һ��������
				 * �����û� ���ܵ�½ϵͳ����������Ҫ�����
				 * */
				String sql = "select * from USER_INFO where username = ? and pwd= ?";
				pst = conn.prepareStatement(sql);
				pst.setString(1, username);
				pst.setString(2, pwd);
				//���ò���
			
				
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
			//���ر���������
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
	 * ��ѯ����
	 * @param username �û���
	 * @param name  ���� 
	 * @param start ��ʼ��¼��
	 * @param pageLine ÿҳ��ʾ��
	 * @return
	 */
	public List getUserList(String username,String name ,int start ,int pageLine){
		Connection conn = null;
		if (username == null) {// Ϊnull�����Ҫ���ǲ�Ȼ��ƴ��sql��ʱ��������
			username = "";
		}
		if (name == null) {
			name = "";
		}

		List list = new ArrayList();

		PreparedStatement pst = null;
		try {
			// ��ȡ����
			conn = DBManager.getConnection();
			// sql���
			
			//-------------------------------��ѯ��¼�б�-------------------------------------
			String sql = "select * from USER_INFO where username like ? and name like ? limit "
					+ start + "," + pageLine;
			pst = conn.prepareStatement(sql);
			pst.setString(1, "%"+username+"%");
			pst.setString(2, "%"+name+"%");
			// ���ò���

			ResultSet rs = pst.executeQuery();
			//ȡ�ü�¼
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
				list.add(userInfo);//��userINfo��ӵ�list��ȥ
			}
			rs.close();
			pst.close();
			
			//----------------------------------------��ѯ�ܼ�¼��----------------------------------
			pst = conn.prepareStatement("select count(*) from USER_INFO where username like ? and name like ?");
			pst.setString(1, "%"+username+"%");
			pst.setString(2, "%"+name+"%");
			rs = pst.executeQuery();
            if(rs.next()){
            	//ֻ��һ�У�����ֱ�������ȡ��,���ܼ�¼���ӵ�list�����һ����¼,����list.remove(list.size()-1)������ò�ɾ��
            	list.add(rs.getInt(1));
            }
            rs.close();
		} catch (Exception e) {
			e.printStackTrace();

		}
		// ���ر���������
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
	 * ɾ������
	 * @param userId
	 * @throws Exception
	 */
	public void delete(int userId)throws Exception{
		Connection conn = null;
		
		Statement stmt = null;
		
		boolean autoCommit = true;//Ĭ���ύ��ʽ 
		try{
			conn = DBManager.getConnection();
			autoCommit = conn.getAutoCommit();//ȡ��Ĭ���ύ��ʽ
			conn.setAutoCommit(false);
			stmt = conn.createStatement();//
			stmt.executeUpdate("delete from TEST_RESULT where userId="+userId);
			stmt.executeUpdate("delete from USER_INFO where id="+userId);
			
			conn.commit();//�ύ����
		}
		catch(Exception e){
			conn.rollback();//�ع�
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
	 * �޸�����
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
