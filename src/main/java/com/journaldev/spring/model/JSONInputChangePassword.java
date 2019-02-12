package com.journaldev.spring.model;

import java.io.Serializable;

public class JSONInputChangePassword implements Serializable{
	
   private String userId,oldPassword,newPassword;

	public JSONInputChangePassword() {
		super();
	}

	public JSONInputChangePassword(String userId, String oldPassword, String newPassword) {
		super();
		this.userId = userId;
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
	

}
