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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.journaldev.spring.model.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/rental")
@Api("VERFIY-OTP-CONTROLLER")
public class VerifyOtpController {

	@RequestMapping(value = "/VerifyOtp/v1.0", method = RequestMethod.POST, produces="application/json", consumes="application/json")
	@ResponseBody
	@ApiOperation(value = "VERIFY OTP TO SENDED BY SERVER SIDE TO USER",response =  JSONInputEmailMobile.class, tags = "VerifyOtpController")
	public String verifyOtp(@RequestBody String json) throws JsonProcessingException
	{
		JSONResponse jsonResponse = new JSONResponse();
		jsonResponse.setResult(JSONResponse.FAIL);
		UserData userData = new UserData(); 
		ObjectMapper mapper = new ObjectMapper();
		String errorMessage = null;
		
		try
		{
			JSONInputEmailMobile jsonInputEmail = mapper.readValue(json, JSONInputEmailMobile.class);
			
			String email = jsonInputEmail.getEmail();
			int otp = 	jsonInputEmail.getOtp();
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rentalhomedemo","root","");
			
			PreparedStatement pst = con.prepareStatement("select Email,Otp from otpdetail where Email='"+email+"'");
			
			ResultSet rs=pst.executeQuery();
			
			while(rs.next())
			{
				
				int o = rs.getInt("Otp");
				
				if(o == otp)
				{
					
					PreparedStatement pst2 = con.prepareStatement("select * from surveyor where Email='"+email+"'");
					
			        ResultSet rs2 = pst2.executeQuery();
			        
			        
			        while(rs2.next())
			        {
			        	userData.setAddress(rs2.getString("Address"));
			        	userData.setContact(rs2.getString("Contact"));
			        	userData.setDOB(rs2.getString("DOB"));
			        	userData.setEmail(rs2.getString("Email"));
			        	userData.setGender(rs2.getString("Gender"));
			        	userData.setName(rs2.getString("Name"));
			        	userData.setProfilePicture(rs2.getString("ProfilePicture"));
			        	userData.setUserId(rs2.getString("UserId"));
			        	
			        jsonResponse.setData(userData);
			        
			        jsonResponse.setResult(JSONResponse.SUCCESS);
			        jsonResponse.setMessage("OTP Matched");
			        	
			        }
					
				}
				else
				{
					jsonResponse.setResult(JSONResponse.FAIL);
					jsonResponse.setMessage("OTP Not Matched");
				}
				
			}
			
			pst.close();
			con.close();
		
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
