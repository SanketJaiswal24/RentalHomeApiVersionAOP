package com.journaldev.spring.model;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;

@ApiModel(description="JSONInputNewPassword")
public class JSONInputNewPassword implements Serializable {

 private String userID,newPassword;

	public JSONInputNewPassword(String userID, String newPassword) {
		super();
		this.userID = userID;
		this.newPassword = newPassword;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public JSONInputNewPassword() {
		super();
	}
	
	
}
