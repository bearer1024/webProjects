package com.gxa.bean;

public class TestResult {

	private int id;
	
	private TestPaper testPaper;
	
	private UserInfo userInfo;
	
	private float mark;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public TestPaper getTestPaper() {
		return testPaper;
	}

	public void setTestPaper(TestPaper testPaper) {
		this.testPaper = testPaper;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public float getMark() {
		return mark;
	}

	public void setMark(float mark) {
		this.mark = mark;
	}
}
