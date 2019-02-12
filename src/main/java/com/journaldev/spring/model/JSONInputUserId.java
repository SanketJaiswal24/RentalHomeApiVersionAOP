package com.journaldev.spring.model;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;

@ApiModel(description="JSONInputUserId")
public class JSONInputUserId implements Serializable 
{
 private String userId;

	public JSONInputUserId(String userId) {
		super();
		this.userId = userId;
	}

	public JSONInputUserId() {
		super();
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	
}
