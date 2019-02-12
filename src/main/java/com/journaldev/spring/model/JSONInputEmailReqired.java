package com.journaldev.spring.model;

import io.swagger.annotations.ApiModel;

@ApiModel(description="JSONInputEmailRequired")
public class JSONInputEmailReqired {
	
 private String email;
 private String mobile;
	public JSONInputEmailReqired() {
		super();
	}
	public JSONInputEmailReqired(String email, String mobile) {
		super();
		this.email = email;
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	@Override
	public String toString() {
		return "JSONInputEmailReqired [email=" + email + ", mobile=" + mobile + "]";
	}
	
}
