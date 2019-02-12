package com.journaldev.spring.controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;

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
@Api("VERFIY-EMAIL-CONTROLLER")
public class VerifyEmailController {

	@RequestMapping(value = "/VerifyEmail/v1.0", method = RequestMethod.POST, produces="application/json", consumes="application/json")
	@ResponseBody
	@ApiOperation(value = "VERIFY EMAIL TO SENT BY USER",response =  JSONInputEmailReqired.class, tags = "VerifyEmailController")
	public String verifyEmail(@RequestBody String json) throws JsonProcessingException
	{
		ObjectMapper mapper = new ObjectMapper();
		JSONResponse jsonResponse = new JSONResponse();
		jsonResponse.setResult(JSONResponse.FAIL);
		String errorMessage = null;
		
		try {
		
	JSONInputEmailReqired JSONInputEmailReqired = mapper.readValue(json, JSONInputEmailReqired.class);
		
		String email = JSONInputEmailReqired.getEmail();
		String mobile = JSONInputEmailReqired.getMobile();
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/RentalHomeDemo","root","");
		PreparedStatement pst = con.prepareStatement("select Email , Contact from surveyor where Email='"+email+"'");
		
		ResultSet  rs = pst.executeQuery();
	
		if(rs.next()) {
			
			String em = rs.getString("Email");
			String mo = rs.getString("Contact");
			if(em.equals(email) && mo.equals(mobile)) {
				
				
				Random random = new Random();
				
				int randno = random.nextInt(1000000);
				
				insertOTP(em,mo,randno);
				
				
				JSONInputEmailMobile jsonInputEmailMobile = new JSONInputEmailMobile() ;
				
				jsonInputEmailMobile.setEmail(em);
				jsonInputEmailMobile.setOtp(randno);
				jsonResponse.setResult(JSONResponse.SUCCESS);
				
				jsonResponse.setData(jsonInputEmailMobile);
				
				jsonResponse.setMessage("Email and Contact Verified");
			}else {
				jsonResponse.setResult(JSONResponse.FAIL);
				jsonResponse.setMessage("Email and Contact Not Verified");
			}
		}
		
		pst.close();
		con.close();
		
		}catch(Exception e) {
			errorMessage = e.getMessage();
			jsonResponse.setResult(JSONResponse.ERROR);
			jsonResponse.setMessage("Problem "+errorMessage);
			e.printStackTrace();
		}
		
		ObjectMapper mapperBack = new ObjectMapper();
		
		return mapperBack.writeValueAsString(jsonResponse);
	     
	}

	public void insertOTP(String email,String mobile,int random)
	{
		try
		{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rentalhomedemo","root","");
		
		PreparedStatement pst = con.prepareStatement("insert into otpdetail values(?,?,?)");
		
		pst.setString(1,email);
		pst.setInt(2, random);
		pst.setString(3,mobile);
		
		pst.executeUpdate();
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}	
	}

}
