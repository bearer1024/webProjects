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
	 * 添加
	 * @param questions
	 */
	public synchronized void add(Questions questions){
		
		Connection conn = null;

		PreparedStatement pst = null;
		boolean autoCommit = true;// 连接提交方式
		try {
			conn = DBManager.getConnection();
			autoCommit = conn.getAutoCommit();// 取得连接默认的提交模式
			conn.setAutoCommit(false);// 设置提交模式为false,手动提交
			//-------------------------插入题目表----------------
			String sql = "insert into QUESTIONS(qName,qAnswer) values( ?, ?)";
			pst = conn.prepareStatement(sql);
			pst.setString(1, questions.getqName());
			pst.setString(2, questions.getqAnwser());
			pst.executeUpdate();
			pst.close();
			
			//----------------------取得刚插入记录的id--------------
			sql = "select max(id) from QUESTIONS";
			pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			int qId = 0;
			if(rs.next()){
				qId = rs.getInt(1);
			}
			rs.close();
			pst.close();
			
			//-----------------------------循环插入选项-----------
			sql = "insert into OPTIONS(oNO,oName,qId) values(? ,? ,?)";
			pst =conn.prepareStatement(sql);
			for(Iterator<Options> it = questions.getOptions().iterator();it.hasNext();){
				Options options = it.next();
				pst.setString(1, options.getoNo());
				pst.setString(2, options.getoName());
				pst.setInt(3, qId);
				pst.addBatch();
			}
			
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
	 * @param questions
	 */
	public void update(Questions questions){
		
		Connection conn = null;

		PreparedStatement pst = null;
		boolean autoCommit = true;// 连接提交方式
		try {
			conn = DBManager.getConnection();
			autoCommit = conn.getAutoCommit();// 取得连接默认的提交模式
			conn.setAutoCommit(false);// 设置提交模式为false,手动提交
			//-------------------------插入题目表----------------
			String sql = "update QUESTIONS set qName =?,qAnswer =? where id = ?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, questions.getqName());
			pst.setString(2, questions.getqAnwser());
			pst.setInt(3, questions.getId());
			pst.executeUpdate();
			pst.close();
			
			//----------------------删除选项表中原有记录--------------
			sql = "delete from  OPTIONS where qId =?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, questions.getId());
			pst.executeUpdate();
			pst.close();
			
			//-----------------------------循环插入选项-----------
			sql = "insert into OPTIONS(oNO,oName,qId) values(? ,? ,?)";
			pst =conn.prepareStatement(sql);
			for(Iterator<Options> it = questions.getOptions().iterator();it.hasNext();){
				Options options = it.next();
				pst.setString(1, options.getoNo());
				pst.setString(2, options.getoName());
				pst.setInt(3, questions.getId());
				pst.addBatch();
			}
			
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
	 * 删除
	 * @param id
	 * @throws Exception
	 */
	public void delete(int id)throws Exception{
Connection conn = null;
		
		Statement stmt = null;
		
		boolean autoCommit = true;//默认提交方式 
		try{
			conn = DBManager.getConnection();
			autoCommit = conn.getAutoCommit();//取得默认提交方式
			conn.setAutoCommit(false);
			stmt = conn.createStatement();//
			stmt.executeUpdate("delete from OPTIONS where qId="+id);
			stmt.executeUpdate("delete from QUESTIONS where id="+id);
			
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
	 * 查询
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
				//获取连接
				conn = DBManager.getConnection();
				//连接查询，指定别名，相同字段名的注意指定所属别名
						String sql = "select * from QUESTIONS q left join OPTIONS o" +
								" on q.id=o.qId  where q.qName like ? ";
				pst = conn.prepareStatement(sql);
				pst.setString(1, "%"+qName+"%");
			//查询
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
			
		return list;
	}
	/**
	 * 通过id查询
	 * @param id
	 * @return
	 */
	public Questions getById(int id){
		 Connection conn = null;
			
			PreparedStatement pst = null;
			try{
				//获取连接
				conn = DBManager.getConnection();
				//连接查询，指定别名，相同字段名的注意指定所属别名
						String sql = "select * from QUESTIONS q left join OPTIONS o" +
								" on q.id=o.qId  where q.id ="+id;
				pst = conn.prepareStatement(sql);
			//查询
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
	 * 检查名称是否存在
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
				String sql = "select * from QUESTIONS where qName = '"+qName+"' and id <>"+id;
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
	
	public static void main(String[] args) {
		QuestionsDao questionsDao = new QuestionsDao();
		Questions questions = new Questions();
		questions.setqName("测试的题目");
		questions.setqAnwser("A");
		
		Options optionsA = new Options ();
		optionsA.setoNo("A");
		optionsA.setoName("测试的选项A");
		
		Options optionsB = new Options ();
		optionsB.setoNo("B");
		optionsB.setoName("测试的选项B");
		
		questions.getOptions().add(optionsA);
		questions.getOptions().add(optionsB);
		questionsDao.add(questions);
	}
}
