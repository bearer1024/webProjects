package com.gxa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.gxa.bean.Options;
import com.gxa.bean.Questions;
import com.gxa.common.DBManager;

public class QuestionsDao {

	/**
	 * ���
	 * @param questions
	 */
	public synchronized void add(Questions questions){
		
		Connection conn = null;

		PreparedStatement pst = null;
		boolean autoCommit = true;// �����ύ��ʽ
		try {
			conn = DBManager.getConnection();
			autoCommit = conn.getAutoCommit();// ȡ������Ĭ�ϵ��ύģʽ
			conn.setAutoCommit(false);// �����ύģʽΪfalse,�ֶ��ύ
			//-------------------------������Ŀ��----------------
			String sql = "insert into QUESTIONS(qName,qAnswer) values( ?, ?)";
			pst = conn.prepareStatement(sql);
			pst.setString(1, questions.getqName());
			pst.setString(2, questions.getqAnwser());
			pst.executeUpdate();
			pst.close();
			
			//----------------------ȡ�øղ����¼��id--------------
			sql = "select max(id) from QUESTIONS";
			pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			int qId = 0;
			if(rs.next()){
				qId = rs.getInt(1);
			}
			rs.close();
			pst.close();
			
			//-----------------------------ѭ������ѡ��-----------
			sql = "insert into OPTIONS(oNO,oName,qId) values(? ,? ,?)";
			pst =conn.prepareStatement(sql);
			for(Iterator<Options> it = questions.getOptions().iterator();it.hasNext();){
				Options options = it.next();
				pst.setString(1, options.getoNo());
				pst.setString(2, options.getoName());
				pst.setInt(3, qId);
				pst.addBatch();
			}
			
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
	 * @param questions
	 */
	public void update(Questions questions){
		
		Connection conn = null;

		PreparedStatement pst = null;
		boolean autoCommit = true;// �����ύ��ʽ
		try {
			conn = DBManager.getConnection();
			autoCommit = conn.getAutoCommit();// ȡ������Ĭ�ϵ��ύģʽ
			conn.setAutoCommit(false);// �����ύģʽΪfalse,�ֶ��ύ
			//-------------------------������Ŀ��----------------
			String sql = "update QUESTIONS set qName =?,qAnswer =? where id = ?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, questions.getqName());
			pst.setString(2, questions.getqAnwser());
			pst.setInt(3, questions.getId());
			pst.executeUpdate();
			pst.close();
			
			//----------------------ɾ��ѡ�����ԭ�м�¼--------------
			sql = "delete from  OPTIONS where qId =?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, questions.getId());
			pst.executeUpdate();
			pst.close();
			
			//-----------------------------ѭ������ѡ��-----------
			sql = "insert into OPTIONS(oNO,oName,qId) values(? ,? ,?)";
			pst =conn.prepareStatement(sql);
			for(Iterator<Options> it = questions.getOptions().iterator();it.hasNext();){
				Options options = it.next();
				pst.setString(1, options.getoNo());
				pst.setString(2, options.getoName());
				pst.setInt(3, questions.getId());
				pst.addBatch();
			}
			
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
	 * ɾ��
	 * @param id
	 * @throws Exception
	 */
	public void delete(int id)throws Exception{
Connection conn = null;
		
		Statement stmt = null;
		
		boolean autoCommit = true;//Ĭ���ύ��ʽ 
		try{
			conn = DBManager.getConnection();
			autoCommit = conn.getAutoCommit();//ȡ��Ĭ���ύ��ʽ
			conn.setAutoCommit(false);
			stmt = conn.createStatement();//
			stmt.executeUpdate("delete from OPTIONS where qId="+id);
			stmt.executeUpdate("delete from QUESTIONS where id="+id);
			
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
	 * ��ѯ
	 * @param qName
	 * @return
	 */
	public List getQuestionsList(String qName ){
		if(qName == null){
			qName = "";
		}
		List list = new ArrayList();
		 Connection conn = null;
			
			PreparedStatement pst = null;
			try{
				//��ȡ����
				conn = DBManager.getConnection();
				//���Ӳ�ѯ��ָ����������ͬ�ֶ�����ע��ָ����������
						String sql = "select * from QUESTIONS q left join OPTIONS o" +
								" on q.id=o.qId  where q.qName like ? ";
				pst = conn.prepareStatement(sql);
				pst.setString(1, "%"+qName+"%");
			//��ѯ
				ResultSet rs = pst.executeQuery();
				Questions questions = null;
				while(rs.next()){
					int qId = rs.getInt("q.id");
					if(questions==null||questions.getId()!=qId){
						questions = new Questions();
						questions.setId(qId);
						questions.setqName(rs.getString("qName"));
						questions.setqAnwser(rs.getString("qAnswer"));
						list.add(questions);
					}
					Options options = new Options();
					options.setId(rs.getInt("o.id"));
					options.setoName(rs.getString("oName"));
					options.setoNo(rs.getString("oNO"));
					options.setQuestions(questions);
					questions.getOptions().add(options);
					
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
			
		return list;
	}
	/**
	 * ͨ��id��ѯ
	 * @param id
	 * @return
	 */
	public Questions getById(int id){
		 Connection conn = null;
			
			PreparedStatement pst = null;
			try{
				//��ȡ����
				conn = DBManager.getConnection();
				//���Ӳ�ѯ��ָ����������ͬ�ֶ�����ע��ָ����������
						String sql = "select * from QUESTIONS q left join OPTIONS o" +
								" on q.id=o.qId  where q.id ="+id;
				pst = conn.prepareStatement(sql);
			//��ѯ
				ResultSet rs = pst.executeQuery();
				Questions questions = null;
				while(rs.next()){
					int qId = rs.getInt("q.id");
					if(questions==null||questions.getId()!=qId){
						questions = new Questions();
						questions.setId(qId);
						questions.setqName(rs.getString("qName"));
						questions.setqAnwser(rs.getString("qAnswer"));
					}
					Options options = new Options();
					options.setId(rs.getInt("o.id"));
					options.setoName(rs.getString("oName"));
					options.setoNo(rs.getString("oNO"));
					options.setQuestions(questions);
					questions.getOptions().add(options);
					
				}
				   return questions;
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
	 * ��������Ƿ����
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
				String sql = "select * from QUESTIONS where qName = '"+qName+"' and id <>"+id;
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
	
	public static void main(String[] args) {
		QuestionsDao questionsDao = new QuestionsDao();
		Questions questions = new Questions();
		questions.setqName("���Ե���Ŀ");
		questions.setqAnwser("A");
		
		Options optionsA = new Options ();
		optionsA.setoNo("A");
		optionsA.setoName("���Ե�ѡ��A");
		
		Options optionsB = new Options ();
		optionsB.setoNo("B");
		optionsB.setoName("���Ե�ѡ��B");
		
		questions.getOptions().add(optionsA);
		questions.getOptions().add(optionsB);
		questionsDao.add(questions);
	}
}
