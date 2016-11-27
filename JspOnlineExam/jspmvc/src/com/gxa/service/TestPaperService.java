package com.gxa.service;

import java.util.Iterator;

import com.gxa.bean.Questions;
import com.gxa.bean.TestPaper;
import com.gxa.dao.TestPaperDao;

public class TestPaperService {

	/**
	 * �������
	 * @param testPaper �ύ���Ծ�
	 * @return
	 */
	public float mark(TestPaper testPaper){
		float mark = 0;//�÷�
		int rightQuestions = 0;//��ȷ��Ŀ��
		int id = testPaper.getId();
		TestPaperDao testPaperDao = new TestPaperDao();
		TestPaper testPaperTemp = testPaperDao.getById(id);//ȡ�����ݿ��е��Ծ������д�
		for(int i=0;i<testPaper.getQuestionsList().size();i++){
			Questions questions = (Questions)testPaper.getQuestionsList().get(i);
			String answer = questions.getqAnwser();//�û��ύ�Ĵ�
			for(Iterator<Questions> it = testPaperTemp.getQuestionsList().iterator();it.hasNext();){
				Questions questionsTemp = it.next();
				if(questionsTemp.getId()==questions.getId()){//�ҵ����ݿ�����Ŀ���Ƚϴ�
					if(questionsTemp.getqAnwser().equals(answer)){
						rightQuestions++;//��ȷ����1
					}
					break;
				}
			}
		}
		mark = 100*((float)rightQuestions/testPaperTemp.getQuestionsList().size());
		
		return mark;
	}
}
