package com.journaldev.spring.controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.journaldev.spring.Services.RentalHomeServices;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.journaldev.spring.model.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Controller
@RequestMapping("/rental")
@Api("ADD-PROPERTY-CONTROLLER")
public  class AddPropertyController
{	
	@RequestMapping(value = {"/AddProperty/v1.0", "/AddProperty/v1.1", } , method = RequestMethod.POST, produces="application/json", consumes="application/json")
	@ResponseBody
	@ApiOperation(value = "ADD THE PROPERTY IN PROPERTY DETAILS",response =RentalHome.class,tags="AddPropertyController") 
	public String addProperty(@RequestBody String json) throws JsonProcessingException 
	{
	
		ObjectMapper mapper = new ObjectMapper();
		JSONResponse jsonResponse = new JSONResponse();
		jsonResponse.setResult(JSONResponse.FAIL);
		jsonResponse.setMessage("Data not Inserted");
		String errorMessage = null;
		
		try
		{
			RentalHome rentalHome= mapper.readValue(json, RentalHome.class);
			
			 Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/RentalHomeDemo","root","");
				PreparedStatement pst=con.prepareStatement("INSERT INTO property ("
						+ "                 PropertyType, Address, City, Location,Rent, AvailableFrom,OwnerContact, "
						+ "                OwnerName, Area, Status,Room, Locality, WaterSupply, ImageURL,ExtraDetails,"
						+ "                IsParking,IsAvailable, SubmittedBy, Email) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
				
				pst.setString (1, rentalHome.getPropertyType());
				pst.setString (2, rentalHome.getAddress());
				pst.setString (3, rentalHome.getCity());
				pst.setString (4, rentalHome.getLocation());
				pst.setInt (5, rentalHome.getRent());
				pst.setString (6, rentalHome.getAvailableFrom());
				pst.setString (7, rentalHome.getOwnerContact());
				pst.setString (8, rentalHome.getOwnerName());
				pst.setInt (9, rentalHome.getArea());
				pst.setString (10, rentalHome.getStatus());
				pst.setInt (11, rentalHome.getRoom());
				pst.setString (12, rentalHome.getLocality());
				pst.setString (13, rentalHome.getWaterSupply());
				pst.setString (14, rentalHome.getImageUrl());
				pst.setString (15, rentalHome.getExtraDetails());
				pst.setString (16, rentalHome.getIsParking());
				pst.setString (17, rentalHome.getIsAvailable());
				pst.setString (18, rentalHome.getSubmittedBy());
				pst.setString (19, rentalHome.getEmail());
				
				int count = pst.executeUpdate();
				if(count>0)
				{
					jsonResponse.setResult(JSONResponse.SUCCESS);
					jsonResponse.setMessage("Data Submitted Successfully ");
				}else {
					jsonResponse.setResult(JSONResponse.FAIL);
					jsonResponse.setMessage("Data not Inserted");
				}
		}
		  catch(Exception e)
			{
			    errorMessage = e.getMessage();
				jsonResponse.setResult(JSONResponse.ERROR);
				jsonResponse.setMessage("Problem "+errorMessage);
				e.printStackTrace();
			}
		
		     ObjectMapper mapperBack = new ObjectMapper();
	        
		
	    return mapperBack.writeValueAsString(jsonResponse);
	}
	
}
