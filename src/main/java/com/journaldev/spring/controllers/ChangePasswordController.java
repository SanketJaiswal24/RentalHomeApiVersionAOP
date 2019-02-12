package com.journaldev.spring.controllers;

import java.io.InputStream;
import java.io.PrintWriter;
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
@Api("CHANGE-PASSWORD-CONTROLLER")
public class ChangePasswordController {

	@RequestMapping(value = "/ChangePassword/v1.1", method = RequestMethod.POST, produces="application/json", consumes="application/json")
	@ResponseBody
	@ApiOperation(value = "CHANGE PASSWORD IN USER LOGIN",response =  JSONInputChangePassword.class, tags = "ChangePasswordContoller")
	@Deprecated
	public String changePassword(@RequestBody String json) throws JsonProcessingException
	{
		JSONResponse jsonResponse = new JSONResponse();
		jsonResponse.setResult(JSONResponse.FAIL);
		ObjectMapper mapper = new ObjectMapper();
		String errorMessage = null;
		
		
		try {
			
			JSONInputChangePassword changePassword = mapper.readValue(json, JSONInputChangePassword.class);
			
			String userId = changePassword.getUserId();
			String oldPassword = changePassword.getOldPassword();
			String newPassword = changePassword.getNewPassword();
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rentalhomedemo","root","");
			
			PreparedStatement pst = con.prepareStatement("update surveyor set Password='"+newPassword+"' where userId='"+userId+"' and Password='"+oldPassword+"'"); 
		
			int count = pst.executeUpdate();
			if(count>0) {
				jsonResponse.setResult(JSONResponse.SUCCESS);
				jsonResponse.setMessage("Your Password Updated");
			}else {
				jsonResponse.setResult(JSONResponse.FAIL);
				jsonResponse.setMessage("UserId or Password Not Matched");
			}
			
		}catch(Exception e) {
			    e.printStackTrace();
			    errorMessage = e.getMessage();
				jsonResponse.setResult(JSONResponse.ERROR);
				jsonResponse.setMessage("Problem "+errorMessage);
		}
		
		ObjectMapper mapperBack = new ObjectMapper();
		
	    return mapperBack.writeValueAsString(jsonResponse);
	}
	
}
