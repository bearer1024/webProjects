package com.gxa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.gxa.bean.Options;
import com.gxa.bean.Questions;
import com.gxa.bean.TestPaper;
import com.gxa.common.DBManager;

public class TestPaperDao {
	/**
	 * 添加
	 * @param testPaper
	 */
	public synchronized void add(TestPaper testPaper){
		
		Connection conn = null;

		PreparedStatement pst = null;
		boolean autoCommit = true;// 连接提交方式
		try {
			conn = DBManager.getConnection();
			autoCommit = conn.getAutoCommit();// 取得连接默认的提交模式
			conn.setAutoCommit(false);// 设置提交模式为false,手动提交
			//插入试卷表
			String sql = " insert into TEST_PAPER(tName,startTime,endTime) values( ?, ?,?)";
			pst = conn.prepareStatement(sql);
			pst.setString(1, testPaper.gettName());
			pst.setTimestamp(2, new Timestamp(testPaper.getStartTime().getTime()));//开始时间
			pst.setTimestamp(3, new Timestamp(testPaper.getEndTime().getTime()));//开始时间
			pst.executeUpdate();
			pst.close();
			
			//----------------------取得刚插入记录的id--------------
			sql = "select max(id) from TEST_PAPER";
			pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			int tId = 0;
			if(rs.next()){
				tId = rs.getInt(1);
			}
			rs.close();
			pst.close();
			
			//-----------------------------循环插入选项-----------
			sql = "insert into TEST_QUESTIONS(tId,qId) values(? ,?)";
			pst = conn.prepareStatement(sql);
			for (Iterator<Questions> it = testPaper.getQuestionsList().iterator(); it.hasNext();) {
				Questions questions = it.next();
				pst.setInt(1, tId);
				pst.setInt(2, questions.getId());
				pst.addBatch();
			}
			pst.executeBatch();// 批量处理
			
			pst.executeBatch();//执行批处理
			//--------------提交----------
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
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
					conn.setAutoCommit(autoCommit);
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
	}
	
	/**
	 * 修改
	 * @param tesstPaper
	 */
	public void update(TestPaper testPaper){
		Connection conn = null;

		PreparedStatement pst = null;
		boolean autoCommit = true;// 连接提交方式
		try {
			conn = DBManager.getConnection();
			autoCommit = conn.getAutoCommit();// 取得连接默认的提交模式
			conn.setAutoCommit(false);// 设置提交模式为false,手动提交
			// -------------------------先插试题表-----------------------
			String sql = " update  TEST_PAPER set tName=?, startTime=? ,endTime=? where id= ?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, testPaper.gettName());
			pst.setTimestamp(2, new Timestamp(testPaper.getStartTime().getTime()));//开始时间
			pst.setTimestamp(3, new Timestamp(testPaper.getEndTime().getTime()));//开始时间
			pst.setInt(4, testPaper.getId());
			pst.executeUpdate();
			pst.close();
			
			//------------------------先删除之前的--------------------------
			pst = conn.prepareStatement("delete from TEST_QUESTIONS where tId="+testPaper.getId());
			pst.executeUpdate();

			// -----------------------插入中间表-------------------------------
						sql = "insert into TEST_QUESTIONS(tId,qId) values(? ,?)";
						pst = conn.prepareStatement(sql);
						for (Iterator<Questions> it = testPaper.getQuestionsList().iterator(); it.hasNext();) {
							Questions questions = it.next();
							pst.setInt(1, testPaper.getId());
							pst.setInt(2, questions.getId());
							pst.addBatch();
						}
						pst.executeBatch();// 批量处理

			conn.commit();

		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
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
					conn.setAutoCommit(autoCommit);
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
	}
	
	
	/**
	 * 删除
	 * @param id
	 * @throws Exception
	 */
	public void delete(int id)throws Exception{
		 Connection conn = null;
			
			Statement stmt = null;
			boolean autoCommit = true;//连接提交方式 
			try{
				conn = DBManager.getConnection();
				autoCommit = conn.getAutoCommit();//取得连接默认的提交模式
				conn.setAutoCommit(false);//设置提交模式为false,手动提交
				//sql语句
				stmt = conn.createStatement();
				//删除相关考试结果
				stmt.executeUpdate("delete from TEST_QUESTIONS where tId="+id);
				//删除用户 信息
				stmt.executeUpdate("delete from TEST_PAPER where id="+id);
	            
				conn.commit();//提交事务
				
			}catch(Exception e){
				conn.rollback();//出现异常，回滚事务，比如题目被使用的情况
				e.printStackTrace();
				throw e;
				
			}
			//最后关闭语句和连接
			finally{
				if(stmt!=null){
					try {
						stmt.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(conn != null){
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
	 * 查询试卷列表，此处不查询其所有题目
	 * @param tName
	 * @return
	 */
	public List getPaperList(String tName){
		
		if (tName == null) {
			tName = "";
		}
		
			// 连接查询，指定别名，相同字段名的注意指定所属别名
			String sql = "select * from TEST_PAPER  where tName like '%"+tName+"%' ";
			
		return this.getPaperListBySQL(sql);
	}
	
	/**
	 * 查询试卷列表，查询用户可以考试的试卷
	 * @param tName
	 * @return
	 */
	public List getPaperList(int userId){
		
		
		
			// 连接查询，指定别名，相同字段名的注意指定所属别名
			String sql = "select * from TEST_PAPER  where id not in (select tId from TEST_RESULT where userId="+userId+")" +
					"    and (startTime<now() and endTime>now())  ";
			//String sql = "select * from TEST_PAPER  ";
			
		return this.getPaperListBySQL(sql);
	}
	
	public List showPaperList(int userId){
		
		
		
		// 连接查询，指定别名，相同字段名的注意指定所属别名
		//String sql = "select * from TEST_PAPER  where id not in (select tId from TEST_RESULT where userId="+userId+")" +
		//		"    and (startTime<now() and endTime>now())  ";
		String sql = "select * from TEST_PAPER  ";
		
	return this.getPaperListBySQL(sql);
}
	
	/**
	 * 通过id查询，修改和生成试卷时用到
	 * @param id
	 * @return
	 */
	public TestPaper getById(int id){
		Connection conn = null;

		Statement stmt = null;
		try {
			// 获取连接
			conn = DBManager.getConnection();
			// 连接查询，指定别名，相同字段名的注意指定所属别名
			String sql = "select * from TEST_PAPER  where id=" + id;
			stmt = conn.createStatement();
			// 查询
			ResultSet rs = stmt.executeQuery(sql);
			TestPaper testPaper = null;
			if (rs.next()) {

				testPaper = new TestPaper();
				testPaper.setId(rs.getInt("id"));
				testPaper.settName(rs.getString("tName"));
				testPaper.setStartTime(rs.getTimestamp("startTime"));
				testPaper.setEndTime(rs.getTimestamp("endTime"));

			}
			rs.close();
			if (testPaper != null) {
				// 连接查询，查询题目详细信息，以生成试卷
				sql = "select * from QUESTIONS q left join OPTIONS o"
						+ " on q.id=o.qId  where q.id in (select tq.qId from TEST_QUESTIONS tq where tq.tid="
						+ id + ") ";

				rs = stmt.executeQuery(sql);
				Questions questions = null;
				while (rs.next()) {
					int qId = rs.getInt("q.id");
					if (questions == null || questions.getId() != qId) {
						questions = new Questions();
						questions.setId(qId);
						questions.setqName(rs.getString("qName"));
						questions.setqAnwser(rs.getString("qAnswer"));
						testPaper.getQuestionsList().add(questions);
					}
					Options options = new Options();
					options.setId(rs.getInt("o.id"));
					options.setoName(rs.getString("oName"));
					options.setoNo(rs.getString("oNO"));
					options.setQuestions(questions);
					questions.getOptions().add(options);

				}

			}
			
			return testPaper;
		} catch (Exception e) {
			e.printStackTrace();

		}
		// 最后关闭语句和连接
		finally {
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
		
		return null;
	}
	/**
	 * 是否存在重名
	 * @param qName
	 * @param id
	 * @return
	 */
	public boolean isExist(String qName, int id){
		  Connection conn = null;
			
			PreparedStatement pst = null;
			try{
				conn = DBManager.getConnection();
				//sql语句
				String sql = "select * from TEST_PAPER where tsName = '"+qName+"' and id <>"+id;
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
	 * 通过查询试卷 
	 * @param sql
	 * @return
	 */
	private List getPaperListBySQL(String sql){
		List list = new ArrayList();
		Connection conn = null;

		PreparedStatement pst = null;
		try {
			// 获取连接
			conn = DBManager.getConnection();
			// 连接查询，指定别名，相同字段名的注意指定所属别名
			pst = conn.prepareStatement(sql);
			// 查询
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {

				TestPaper testPaper = new TestPaper();
				testPaper.setId(rs.getInt("id"));
				testPaper.settName(rs.getString("tName"));
				testPaper.setStartTime(rs.getTimestamp("startTime"));
				testPaper.setEndTime(rs.getTimestamp("endTime"));
				list.add(testPaper);

			}

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
