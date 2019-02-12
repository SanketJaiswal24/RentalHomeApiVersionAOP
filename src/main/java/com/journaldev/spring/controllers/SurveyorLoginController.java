package com.journaldev.spring.controllers;

import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
@Api("SURVEYOR-LOGIN-CONTROLLER")
public class SurveyorLoginController {

	@RequestMapping(value = "/SurveyorLogin/v1.2", method = RequestMethod.POST, produces="application/json", consumes="application/json")
	@ResponseBody
	@ApiOperation(value = "SurveyorLogin CAN LOGIN",response =  JSONInputLoginDetail.class, tags = "SurveyorLoginController")
	public String surveyorLogin(@RequestBody String json) throws JsonProcessingException
	{
		JSONResponse jResponse = new JSONResponse();
		String jsonResponse=jResponse.FAIL;
		jResponse.setMessage("Problem");
		ObjectMapper mapper=new ObjectMapper();
		UserData data = new UserData();
		String errorMessage = null;
				
		try {
			
        JSONInputLoginDetail detail =mapper.readValue(json, JSONInputLoginDetail.class);
                
         String userId = detail.getUserId();
         String password = detail.getPassword();
         
        Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/RentalHomeDemo","root","");
		PreparedStatement pst=con.prepareStatement("select * from surveyor where userId= '"+userId+"' and Password = '"+password+"'");
		ResultSet rs=pst.executeQuery();
		if(rs.next())
		{
			
			data.setName(rs.getString("Name"));
			data.setContact(rs.getString("Contact"));
			data.setEmail(rs.getString("Email"));
			data.setAddress(rs.getString("Address"));
			data.setDOB(rs.getString("DOB"));
			data.setGender(rs.getString("Gender"));
			data.setProfilePicture(rs.getString("ProfilePicture"));
			data.setUserId(rs.getString("userId"));
			
			jsonResponse =jResponse.SUCCESS;
			jResponse.setMessage("Match");
		}else {
			jsonResponse =jResponse.FAIL;
			jResponse.setMessage(" Not Match");
		}
		
		rs.close();
		pst.close();
		con.close();
        
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
		
		ObjectMapper mapperBack=new ObjectMapper();
		
		return mapperBack.writeValueAsString(jResponse);
	}
	
}
