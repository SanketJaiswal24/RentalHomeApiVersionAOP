package com.journaldev.spring.model;


import java.io.Serializable;

import io.swagger.annotations.ApiModel;

@ApiModel(description="UserData")
public class UserData implements Serializable {
	
  private String name,contact,email,address,dob,gender,profilePicture,userId;

	public UserData() {
		super();
	}

	public UserData(String name, String contact, String email, String address, String dOB, String gender,
			String profilePicture, String userId) {
		super();
		this.name = name;
		this.contact = contact;
		this.email = email;
		this.address = address;
		this.dob = dOB;
		this.gender = gender;
		this.profilePicture = profilePicture;
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDOB() {
		return dob;
	}

	public void setDOB(String dOB) {
		this.dob = dOB;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}

