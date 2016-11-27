package com.orm;

import java.util.HashSet;
import java.util.Set;



public class TOrganization 
{


	private String id;
	private String name;
	private String description;
	private String p_id;
	
	private String del;
	private TOrganization parenOrganization;
	private Set childOrganization = new HashSet(0);
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getP_id() {
		return p_id;
	}
	public void setP_id(String p_id) {
		this.p_id = p_id;
	}
	public String getDel() {
		return del;
	}
	public void setDel(String del) {
		this.del = del;
	}
	public TOrganization getParenOrganization() {
		return parenOrganization;
	}
	public void setParenOrganization(TOrganization parenOrganization) {
		this.parenOrganization = parenOrganization;
	}
	public Set getChildOrganization() {
		return childOrganization;
	}
	public void setChildOrganization(Set childOrganization) {
		this.childOrganization = childOrganization;
	}
}