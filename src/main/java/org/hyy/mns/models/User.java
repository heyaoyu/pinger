package org.hyy.mns.models;

import java.util.HashSet;
import java.util.Set;

import org.hyy.mns.daos.UserDAO;

/**
 * @author yaoyu.he
 * 
 * User Class for Monitor Notification System
 * 
 * */

public class User {
	
	private long uid;
	private String username;
	private String password;
	private String email;
	private Set<Check> urlChecks = new HashSet<Check>();
	
	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Check> getUrlChecks() {
		return urlChecks;
	}

	public void setUrlChecks(Set<Check> urlChecks) {
		this.urlChecks = urlChecks;
	}
	
}
