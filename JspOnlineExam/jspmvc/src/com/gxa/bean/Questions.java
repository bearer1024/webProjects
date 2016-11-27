package com.gxa.bean;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Questions {
	private int id;
	
	private String qName;
	
	private String qAnwser;
	//·ºÐÍ
	private Set<Options> options = new TreeSet<Options>();
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getqName() {
		return qName;
	}
	public void setqName(String qName) {
		this.qName = qName;
	}
	public String getqAnwser() {
		return qAnwser;
	}
	public void setqAnwser(String qAnwser) {
		this.qAnwser = qAnwser;
	}
	public Set<Options> getOptions() {
		return options;
	}
	public void setOptions(Set<Options> options) {
		this.options = options;
	}
}
