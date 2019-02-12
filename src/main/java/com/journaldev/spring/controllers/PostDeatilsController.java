package com.journaldev.spring.controllers;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.journaldev.spring.model.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/rental")
@Api("POST-DEATILS-CONTROLLER")
public class PostDeatilsController 
{
	@RequestMapping(value = "/PostDetails/v1.1", method = RequestMethod.POST, produces="application/json", consumes="application/json")
	@ResponseBody
	@ApiOperation(value = "PROPERTY OWN CAN POST THE PROPERTY DETAILS",tags="PostPropertyController")
	public String postDeatils(@RequestParam("propertyid") int propertyId) throws JsonProcessingException
	{
		ObjectMapper mapper=new ObjectMapper();
		RentalHome rentalHome=new RentalHome();
		JSONResponse jsonResponse = new JSONResponse();
		jsonResponse.setResult(JSONResponse.FAIL);
		jsonResponse.setMessage("Data not Found");
		String errorMessage = null;
		
		try {
        Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/RentalHomeDemo","root","");
		PreparedStatement pst=con.prepareStatement("select * from property where PropertyId= "+propertyId+"");
		
		ResultSet rs=pst.executeQuery();
	       if(rs.next())
		{
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
			jsonResponse.setResult(JSONResponse.SUCCESS);
			jsonResponse.setMessage("Data Found");
			jsonResponse.setData(rentalHome);
			
		}
		rs.close();
		pst.close();
		con.close();
		}
		catch(Exception e)
		{
			errorMessage = e.getMessage();
			jsonResponse.setResult(JSONResponse.ERROR);
			jsonResponse.setMessage("Data Not Found"+errorMessage);
			e.printStackTrace();
		}
		
		
		ObjectMapper mapperBack = new ObjectMapper();
		return mapperBack.writeValueAsString(jsonResponse);
	}
	
}
