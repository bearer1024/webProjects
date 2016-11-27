package com.gxa.bean;

public class Options implements Comparable<Options> {
    private int id;
	
	private String oNo;
	
	private String oName;
	
	private Questions questions;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getoNo() {
		return oNo;
	}

	public void setoNo(String oNo) {
		this.oNo = oNo;
	}

	public String getoName() {
		return oName;
	}

	public void setoName(String oName) {
		this.oName = oName;
	}

	public Questions getQuestions() {
		return questions;
	}

	public void setQuestions(Questions questions) {
		this.questions = questions;
	}

	@Override
	public int compareTo(Options o) {
		// TODO Auto-generated method stub
		if( oNo.charAt(0)>o.getoNo().charAt(0)){
			return 1;
		}
		else if( oNo.charAt(0)<o.getoNo().charAt(0)){
			return -1;
		}
		
		return 0;
	}
}
