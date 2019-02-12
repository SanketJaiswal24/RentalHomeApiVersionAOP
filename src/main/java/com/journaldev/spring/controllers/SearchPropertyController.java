package com.journaldev.spring.controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.journaldev.spring.model.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/rental")
@Api("SEARCH-PROPERTY-CONTROLLER")
public class SearchPropertyController {
	
	  @RequestMapping(value = "/SearchProperty/v1.1", method = RequestMethod.POST,produces="application/json",consumes="application/json") 
	  @ResponseBody
	  @ApiOperation(value = "USER CAN SEARCH THE PROPERTY DETAILS",response =  JSONInputUserId.class, tags = "SeacrhPropertyController")
	  public String searchPropertyForm(@RequestBody String json) throws JsonGenerationException, JsonMappingException, IOException 
	  {
		
		ObjectMapper mapper = new ObjectMapper();
		JSONResponse jsonResponse = new JSONResponse();
		jsonResponse.setResult(JSONResponse.FAIL);
		jsonResponse.setMessage("Data not Found");
		String errorMessage = null;
		
	     ArrayList<RentalHome> arrayList = new ArrayList<RentalHome>();
		
		try {
		        JSONInputUserId data= mapper.readValue(json,JSONInputUserId.class);
		        String userId = data.getUserId();
		        
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/RentalHomeDemo","root","");
			PreparedStatement pst=con.prepareStatement("select * from property where SubmittedBy='"+userId+"' ");
			ResultSet rs=pst.executeQuery();
			
			while(rs.next())
			{
				RentalHome rentalHome=new RentalHome();
				rentalHome.setAddress(rs.getString("Address"));
				rentalHome.setArea(rs.getInt("Area"));
				rentalHome.setAvailableFrom(rs.getString("AvailableFrom"));
				rentalHome.setCity(rs.getString("City"));
				rentalHome.setOwnerName(rs.getString("OwnerName"));
				rentalHome.setPropertyType(rs.getString("PropertyType"));
				rentalHome.setRent(rs.getInt("Rent"));
				rentalHome.setPropertyId(rs.getInt("PropertyId"));
				rentalHome.setStatus(rs.getString("Status"));
				rentalHome.setLocality(rs.getString("Locality"));
				rentalHome.setWaterSupply(rs.getString("WaterSupply"));
				rentalHome.setImageUrl(rs.getString("ImageUrl"));
				rentalHome.setExtraDetails(rs.getString("ExtraDetails"));
				rentalHome.setIsParking(rs.getString("IsParking"));
				rentalHome.setIsAvailable(rs.getString("IsAvailable"));
				rentalHome.setOwnerContact(rs.getString("OwnerContact"));
				rentalHome.setRoom(rs.getInt("Room"));
				rentalHome.setEmail(rs.getString("Email"));
				rentalHome.setLocation(rs.getString("Location"));
				rentalHome.setSubmittedBy(userId);
				
				jsonResponse.setResult(JSONResponse.SUCCESS);
				jsonResponse.setMessage("Data Found");
				arrayList.add(rentalHome);
				
			}
			jsonResponse.setData(arrayList);
			rs.close();
			pst.close();
			con.close();
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
