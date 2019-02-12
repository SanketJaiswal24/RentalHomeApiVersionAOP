package com.journaldev.spring.model;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;

@ApiModel(description="JSONInputLoginDetail")
public class JSONInputLoginDetail implements Serializable {
	
 private String userId, password;

	public JSONInputLoginDetail() {
		super();
	}

	public JSONInputLoginDetail(String userId, String password) {
		super();
		this.userId = userId;
		this.password = password;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	

	
}
