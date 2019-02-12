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
@Api("SET-PASSWORD-CONTROLLER")
public class SetPasswordController {

	@RequestMapping(value = "/setPassword/v1.0", method = RequestMethod.POST, produces="application/json", consumes="application/json")
	@ResponseBody
	@ApiOperation(value = "USER CAN SET NEW PASSWORD IN ACCOUNT",response = JSONInputNewPassword.class, tags = "SetPasswordController")
	public String setPassword(@RequestBody String json) throws JsonProcessingException
	{

        ObjectMapper mapper = new ObjectMapper();
        JSONResponse jsonResponse = new JSONResponse();
		jsonResponse.setResult(JSONResponse.FAIL);
		jsonResponse.setMessage("Password not Set");
		try {
		
		JSONInputNewPassword jsonInputNewPassword = mapper.readValue(json, JSONInputNewPassword.class);
		
		String userId = jsonInputNewPassword.getUserID();
		String newPassword = jsonInputNewPassword.getNewPassword();
		
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rentalhomedemo","root","");
		
		PreparedStatement pst = con.prepareStatement("UPDATE surveyor SET Password='"+newPassword+"' WHERE UserId='"+userId+"'");
	
		
		int count = pst.executeUpdate();
		
		if(count>0) {
			jsonResponse.setResult(JSONResponse.SUCCESS);
			jsonResponse.setMessage("Password Set");

		}else {
			jsonResponse.setResult(JSONResponse.FAIL);
			jsonResponse.setMessage("Password not Set");
 
		}
		
		pst.close();
		con.close();
		
		}catch(Exception e) {
			jsonResponse.setResult(JSONResponse.ERROR);
			jsonResponse.setMessage("Contact to server");

			e.printStackTrace();
		}
		
		ObjectMapper mapperBack = new ObjectMapper();	
		
		return mapperBack.writeValueAsString(jsonResponse);
	}
	
	
}
