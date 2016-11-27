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
	 * 保存
	 * @param testResult
	 */
	public void add(TestResult testResult)throws Exception{
Connection conn = null;
		
		PreparedStatement pst = null;
		try{
			conn = DBManager.getConnection();
			//sql语句
			String sql = "insert into TEST_RESULT (tId,userId,mark) values"
					+ "(?, ? ,?)";
			pst = conn.prepareStatement(sql);
			//设置参数
			pst.setInt(1, testResult.getTestPaper().getId());
			pst.setInt(2, testResult.getUserInfo().getId());
			pst.setFloat(3, testResult.getMark());
			
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
	 * 查询
	 * @param tId
	 * @param userId
	 */
	public List getReusltList(int tId,int userId){
		Connection conn = null;

		List list = new ArrayList();

		PreparedStatement pst = null;
		try {
			// 获取连接
			conn = DBManager.getConnection();
			// sql语句
			String whereSql = " 1=1 ";
			if(tId!=0){
				whereSql += " and tId="+tId;
			}
			if(userId!=0){
				whereSql += " and userId="+userId;
			}
			//-------------------------------查询记录列表-------------------------------------
			String sql = "select tr.*,tp.tName,tp.startTime,ui.name from TEST_RESULT tr left join TEST_PAPER tp on tp.id=tr.tId " +
					" left join USER_INFO ui on tr.userId = ui.id  where "+whereSql+" order by mark desc";
			pst = conn.prepareStatement(sql);
		
			ResultSet rs = pst.executeQuery();
			//取得记录
			while (rs.next()) {
				TestResult testResult = new TestResult();
				//----------------------考试结果------------------
				testResult.setId(rs.getInt("id"));
				testResult.setMark(rs.getFloat("mark"));
				
				//------------------------试卷信息-----------------
				TestPaper testPaper = new TestPaper(rs.getInt("tId"));
				testPaper.settName(rs.getString("tName"));
				testPaper.setStartTime(rs.getTimestamp("startTime"));
				testResult.setTestPaper(testPaper);
				
				//-----------------------用户 信息
				UserInfo userInfo = new UserInfo(rs.getInt("userId"));
				userInfo.setName(rs.getString("name"));
				testResult.setUserInfo(userInfo);
				
				
				list.add(testResult);//将userINfo添加到list中去
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
}
