package com.journaldev.spring.controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import com.journaldev.spring.model.JSONInputLoginDetail;
import com.journaldev.spring.model.JSONResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.journaldev.spring.model.*;
import com.journaldev.spring.model.UserData;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/rental")
@Api("SHOW-PROFILE-CONTROLLER")
public class ShowProfileController {
	
	@RequestMapping(value = "/showPofile/v1.1", method = RequestMethod.POST, produces="application/json", consumes="application/json")
	@ResponseBody
	@ApiOperation(value = "QWNER CAN SHOW PROFILE",response =  JSONInputLoginDetail.class, tags = "ShowProfileController")
	public String showProfile(@RequestBody String json) throws JsonProcessingException
	{
		JSONResponse jResponse = new JSONResponse();
		String jsonResponse = JSONResponse.FAIL;
		ObjectMapper mapper=new ObjectMapper();
		UserData data = new UserData();
		String errorMessage = null;
			
		try {
        
        JSONInputLoginDetail detail =mapper.readValue(json, JSONInputLoginDetail.class);
        
         String userId = detail.getUserId();
         
        Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/rentalhomedemo","root","");
		PreparedStatement pst=con.prepareStatement("select * from surveyor where UserId= '"+userId+"'");
		ResultSet rs=pst.executeQuery();
		while(rs.next())
		{
			data.setName(rs.getString("Name"));
			data.setContact(rs.getString("Contact"));
			data.setEmail(rs.getString("Email"));
			data.setAddress(rs.getString("Address"));
			data.setDOB(rs.getString("DOB"));
			data.setGender(rs.getString("Gender"));
			data.setProfilePicture(rs.getString("ProfilePicture"));
			data.setUserId(rs.getString("UserId"));
			
			jsonResponse =JSONResponse.SUCCESS;
			
		}
		con.close();
		pst.close();
		rs.close();
        
		}
		catch(Exception e)
		{
			errorMessage = e.getMessage();
			jResponse.setResult(JSONResponse.ERROR);
			jResponse.setMessage("Problem "+errorMessage);
			e.printStackTrace();
			
		}
		jResponse.setResult(jsonResponse);
		jResponse.setData(data);
		
		ObjectMapper mapperBack = new ObjectMapper();
		
		return mapperBack.writeValueAsString(jResponse);
	}

}
