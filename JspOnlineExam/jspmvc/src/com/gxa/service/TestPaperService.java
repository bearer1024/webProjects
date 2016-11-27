package com.gxa.service;

import java.util.Iterator;

import com.gxa.bean.Questions;
import com.gxa.bean.TestPaper;
import com.gxa.dao.TestPaperDao;

public class TestPaperService {

	/**
	 * 计算分数
	 * @param testPaper 提交的试卷
	 * @return
	 */
	public float mark(TestPaper testPaper){
		float mark = 0;//得分
		int rightQuestions = 0;//正确题目数
		int id = testPaper.getId();
		TestPaperDao testPaperDao = new TestPaperDao();
		TestPaper testPaperTemp = testPaperDao.getById(id);//取得数据库中的试卷，里面有答案
		for(int i=0;i<testPaper.getQuestionsList().size();i++){
			Questions questions = (Questions)testPaper.getQuestionsList().get(i);
			String answer = questions.getqAnwser();//用户提交的答案
			for(Iterator<Questions> it = testPaperTemp.getQuestionsList().iterator();it.hasNext();){
				Questions questionsTemp = it.next();
				if(questionsTemp.getId()==questions.getId()){//找到数据库中题目，比较答案
					if(questionsTemp.getqAnwser().equals(answer)){
						rightQuestions++;//正确数加1
					}
					break;
				}
			}
		}
		mark = 100*((float)rightQuestions/testPaperTemp.getQuestionsList().size());
		
		return mark;
	}
}
