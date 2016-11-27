package com.gxa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gxa.bean.TestPaper;
import com.gxa.bean.TestResult;
import com.gxa.bean.UserInfo;
import com.gxa.common.DBManager;

public class TestResultDao {

	/**
	 * ����
	 * @param testResult
	 */
	public void add(TestResult testResult)throws Exception{
Connection conn = null;
		
		PreparedStatement pst = null;
		try{
			conn = DBManager.getConnection();
			//sql���
			String sql = "insert into TEST_RESULT (tId,userId,mark) values"
					+ "(?, ? ,?)";
			pst = conn.prepareStatement(sql);
			//���ò���
			pst.setInt(1, testResult.getTestPaper().getId());
			pst.setInt(2, testResult.getUserInfo().getId());
			pst.setFloat(3, testResult.getMark());
			
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
	 * ��ѯ
	 * @param tId
	 * @param userId
	 */
	public List getReusltList(int tId,int userId){
		Connection conn = null;

		List list = new ArrayList();

		PreparedStatement pst = null;
		try {
			// ��ȡ����
			conn = DBManager.getConnection();
			// sql���
			String whereSql = " 1=1 ";
			if(tId!=0){
				whereSql += " and tId="+tId;
			}
			if(userId!=0){
				whereSql += " and userId="+userId;
			}
			//-------------------------------��ѯ��¼�б�-------------------------------------
			String sql = "select tr.*,tp.tName,tp.startTime,ui.name from TEST_RESULT tr left join TEST_PAPER tp on tp.id=tr.tId " +
					" left join USER_INFO ui on tr.userId = ui.id  where "+whereSql+" order by mark desc";
			pst = conn.prepareStatement(sql);
		
			ResultSet rs = pst.executeQuery();
			//ȡ�ü�¼
			while (rs.next()) {
				TestResult testResult = new TestResult();
				//----------------------���Խ��------------------
				testResult.setId(rs.getInt("id"));
				testResult.setMark(rs.getFloat("mark"));
				
				//------------------------�Ծ���Ϣ-----------------
				TestPaper testPaper = new TestPaper(rs.getInt("tId"));
				testPaper.settName(rs.getString("tName"));
				testPaper.setStartTime(rs.getTimestamp("startTime"));
				testResult.setTestPaper(testPaper);
				
				//-----------------------�û� ��Ϣ
				UserInfo userInfo = new UserInfo(rs.getInt("userId"));
				userInfo.setName(rs.getString("name"));
				testResult.setUserInfo(userInfo);
				
				
				list.add(testResult);//��userINfo��ӵ�list��ȥ
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
}
