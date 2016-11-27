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
	 * ���
	 * @param testPaper
	 */
	public synchronized void add(TestPaper testPaper){
		
		Connection conn = null;

		PreparedStatement pst = null;
		boolean autoCommit = true;// �����ύ��ʽ
		try {
			conn = DBManager.getConnection();
			autoCommit = conn.getAutoCommit();// ȡ������Ĭ�ϵ��ύģʽ
			conn.setAutoCommit(false);// �����ύģʽΪfalse,�ֶ��ύ
			//�����Ծ��
			String sql = " insert into TEST_PAPER(tName,startTime,endTime) values( ?, ?,?)";
			pst = conn.prepareStatement(sql);
			pst.setString(1, testPaper.gettName());
			pst.setTimestamp(2, new Timestamp(testPaper.getStartTime().getTime()));//��ʼʱ��
			pst.setTimestamp(3, new Timestamp(testPaper.getEndTime().getTime()));//��ʼʱ��
			pst.executeUpdate();
			pst.close();
			
			//----------------------ȡ�øղ����¼��id--------------
			sql = "select max(id) from TEST_PAPER";
			pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			int tId = 0;
			if(rs.next()){
				tId = rs.getInt(1);
			}
			rs.close();
			pst.close();
			
			//-----------------------------ѭ������ѡ��-----------
			sql = "insert into TEST_QUESTIONS(tId,qId) values(? ,?)";
			pst = conn.prepareStatement(sql);
			for (Iterator<Questions> it = testPaper.getQuestionsList().iterator(); it.hasNext();) {
				Questions questions = it.next();
				pst.setInt(1, tId);
				pst.setInt(2, questions.getId());
				pst.addBatch();
			}
			pst.executeBatch();// ��������
			
			pst.executeBatch();//ִ��������
			//--------------�ύ----------
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
	 * �޸�
	 * @param tesstPaper
	 */
	public void update(TestPaper testPaper){
		Connection conn = null;

		PreparedStatement pst = null;
		boolean autoCommit = true;// �����ύ��ʽ
		try {
			conn = DBManager.getConnection();
			autoCommit = conn.getAutoCommit();// ȡ������Ĭ�ϵ��ύģʽ
			conn.setAutoCommit(false);// �����ύģʽΪfalse,�ֶ��ύ
			// -------------------------�Ȳ������-----------------------
			String sql = " update  TEST_PAPER set tName=?, startTime=? ,endTime=? where id= ?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, testPaper.gettName());
			pst.setTimestamp(2, new Timestamp(testPaper.getStartTime().getTime()));//��ʼʱ��
			pst.setTimestamp(3, new Timestamp(testPaper.getEndTime().getTime()));//��ʼʱ��
			pst.setInt(4, testPaper.getId());
			pst.executeUpdate();
			pst.close();
			
			//------------------------��ɾ��֮ǰ��--------------------------
			pst = conn.prepareStatement("delete from TEST_QUESTIONS where tId="+testPaper.getId());
			pst.executeUpdate();

			// -----------------------�����м��-------------------------------
						sql = "insert into TEST_QUESTIONS(tId,qId) values(? ,?)";
						pst = conn.prepareStatement(sql);
						for (Iterator<Questions> it = testPaper.getQuestionsList().iterator(); it.hasNext();) {
							Questions questions = it.next();
							pst.setInt(1, testPaper.getId());
							pst.setInt(2, questions.getId());
							pst.addBatch();
						}
						pst.executeBatch();// ��������

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
	 * ɾ��
	 * @param id
	 * @throws Exception
	 */
	public void delete(int id)throws Exception{
		 Connection conn = null;
			
			Statement stmt = null;
			boolean autoCommit = true;//�����ύ��ʽ 
			try{
				conn = DBManager.getConnection();
				autoCommit = conn.getAutoCommit();//ȡ������Ĭ�ϵ��ύģʽ
				conn.setAutoCommit(false);//�����ύģʽΪfalse,�ֶ��ύ
				//sql���
				stmt = conn.createStatement();
				//ɾ����ؿ��Խ��
				stmt.executeUpdate("delete from TEST_QUESTIONS where tId="+id);
				//ɾ���û� ��Ϣ
				stmt.executeUpdate("delete from TEST_PAPER where id="+id);
	            
				conn.commit();//�ύ����
				
			}catch(Exception e){
				conn.rollback();//�����쳣���ع����񣬱�����Ŀ��ʹ�õ����
				e.printStackTrace();
				throw e;
				
			}
			//���ر���������
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
	 * ��ѯ�Ծ��б��˴�����ѯ��������Ŀ
	 * @param tName
	 * @return
	 */
	public List getPaperList(String tName){
		
		if (tName == null) {
			tName = "";
		}
		
			// ���Ӳ�ѯ��ָ����������ͬ�ֶ�����ע��ָ����������
			String sql = "select * from TEST_PAPER  where tName like '%"+tName+"%' ";
			
		return this.getPaperListBySQL(sql);
	}
	
	/**
	 * ��ѯ�Ծ��б���ѯ�û����Կ��Ե��Ծ�
	 * @param tName
	 * @return
	 */
	public List getPaperList(int userId){
		
		
		
			// ���Ӳ�ѯ��ָ����������ͬ�ֶ�����ע��ָ����������
			String sql = "select * from TEST_PAPER  where id not in (select tId from TEST_RESULT where userId="+userId+")" +
					"    and (startTime<now() and endTime>now())  ";
			//String sql = "select * from TEST_PAPER  ";
			
		return this.getPaperListBySQL(sql);
	}
	
	public List showPaperList(int userId){
		
		
		
		// ���Ӳ�ѯ��ָ����������ͬ�ֶ�����ע��ָ����������
		//String sql = "select * from TEST_PAPER  where id not in (select tId from TEST_RESULT where userId="+userId+")" +
		//		"    and (startTime<now() and endTime>now())  ";
		String sql = "select * from TEST_PAPER  ";
		
	return this.getPaperListBySQL(sql);
}
	
	/**
	 * ͨ��id��ѯ���޸ĺ������Ծ�ʱ�õ�
	 * @param id
	 * @return
	 */
	public TestPaper getById(int id){
		Connection conn = null;

		Statement stmt = null;
		try {
			// ��ȡ����
			conn = DBManager.getConnection();
			// ���Ӳ�ѯ��ָ����������ͬ�ֶ�����ע��ָ����������
			String sql = "select * from TEST_PAPER  where id=" + id;
			stmt = conn.createStatement();
			// ��ѯ
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
				// ���Ӳ�ѯ����ѯ��Ŀ��ϸ��Ϣ���������Ծ�
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
		// ���ر���������
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
	 * �Ƿ��������
	 * @param qName
	 * @param id
	 * @return
	 */
	public boolean isExist(String qName, int id){
		  Connection conn = null;
			
			PreparedStatement pst = null;
			try{
				conn = DBManager.getConnection();
				//sql���
				String sql = "select * from TEST_PAPER where tsName = '"+qName+"' and id <>"+id;
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
	 * ͨ����ѯ�Ծ� 
	 * @param sql
	 * @return
	 */
	private List getPaperListBySQL(String sql){
		List list = new ArrayList();
		Connection conn = null;

		PreparedStatement pst = null;
		try {
			// ��ȡ����
			conn = DBManager.getConnection();
			// ���Ӳ�ѯ��ָ����������ͬ�ֶ�����ע��ָ����������
			pst = conn.prepareStatement(sql);
			// ��ѯ
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
