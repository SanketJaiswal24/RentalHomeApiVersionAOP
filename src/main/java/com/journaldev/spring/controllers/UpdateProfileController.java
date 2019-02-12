package com.journaldev.spring.controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.journaldev.spring.model.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/rental")
@Api("UPDATE-PROFILE-CONTROLLER")
public class UpdateProfileController {

	@RequestMapping(value = "/updateProfie/v1.2", method = RequestMethod.POST, produces="application/json", consumes="application/json")
	@ResponseBody
	@ApiOperation(value = "Update PROFILE",response =  UserData.class, tags = "UpdateProfileController")
	public String updateProfile(@RequestBody String json) throws JsonProcessingException
	{
		JSONResponse jsonResponse = new JSONResponse();
		jsonResponse.setResult(JSONResponse.FAIL);
		ObjectMapper mapper=new ObjectMapper();
		UserData data = new UserData();
		String errorMessage = null;
			
		try {
           
         data = mapper.readValue(json,UserData.class);
         
         String address = data.getAddress();
         String  name = data.getName();
         String  contact = data.getContact();
         String email = data.getEmail();
         String dob = data.getDOB();
         String gender = data.getGender();
         String pic = data.getProfilePicture();
         String userId = data.getUserId();
         
         Class.forName("com.mysql.jdbc.Driver");
 		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/rentalhomedemo","root","");
 		PreparedStatement pst=con.prepareStatement("update surveyor set Address='"+address+"' , Name='"+name+"' , Contact='"+contact+"', Email='"+email+"', DOB='"+dob+"' ,"
 				+ " Gender='"+gender+"' , ProfilePicture='"+pic+"' where UserId= '"+userId+"'");
        
 		int count = pst.executeUpdate();
 		
        if(count>0)
        {
        	jsonResponse.setResult(JSONResponse.SUCCESS);
        	jsonResponse.setMessage("Profile Successfully Updated");
        }
        else
        {
        	jsonResponse.setResult(JSONResponse.FAIL);
        	jsonResponse.setMessage("Profile Not Updated");
        }
        
		}catch (Exception e) {
			errorMessage = e.getMessage();
			jsonResponse.setResult(JSONResponse.ERROR);
			jsonResponse.setMessage("Problem "+errorMessage);
			e.printStackTrace();
		}

		
		   ObjectMapper mapperBack =new ObjectMapper();
           
		
		return mapperBack.writeValueAsString(jsonResponse);
	}
	
}
