package com.journaldev.spring.model;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import springfox.documentation.annotations.ApiIgnore;

@ApiModel(description="JSONInputEmailMobile")
public class JSONInputEmailMobile implements Serializable {

  private String email, mobile;
	int otp;

	public JSONInputEmailMobile() {
		super();
	}

	public JSONInputEmailMobile(String email,int otp,String mobile) {
		super();
		this.email = email;
		this.otp = otp;
		this.mobile= mobile;
	}

	public int getOtp() {
		return otp;
	}

	public void setOtp(int otp) {
		this.otp = otp;
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
	
}
