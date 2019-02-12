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
@Api("UPDATE-PROPERTY-CONTROLLER")
public class UpdatePropertyController {

	@RequestMapping(value = "/updatePropery/v1.2", method = RequestMethod.POST, produces="application/json", consumes="application/json")
	@ResponseBody
	@ApiOperation(value = "PROPERTY QWNER CAN UPDATE POPERTY DETAILS",response =  RentalHome.class, tags = "UpdatePropertyContoller")
	public String updateProperty(@RequestBody String json) throws JsonProcessingException
	{
		 JSONResponse jsonResponse = new JSONResponse();
		 jsonResponse.setResult(JSONResponse.FAIL);
		 ObjectMapper mapper=new ObjectMapper();
	     RentalHome rentalHome = new RentalHome();
	     String errorMessage = null;
			
		try {
        
        rentalHome = mapper.readValue(json,RentalHome.class);
        
       String address = rentalHome.getAddress();
       int area = rentalHome.getArea();
       String ownerContact = rentalHome.getOwnerContact();
       String city = rentalHome.getCity();
       int propertyId = rentalHome.getPropertyId();
       String propertyType = rentalHome.getPropertyType();
       String locality = rentalHome.getLocality();
       String availableFrom = rentalHome.getAvailableFrom();
       int rent = rentalHome.getRent();
       String status = rentalHome.getStatus();
       String waterSupply = rentalHome.getWaterSupply();
       String location = rentalHome.getLocation();
       String imageUrl = rentalHome.getImageUrl();
       String extraDetail = rentalHome.getExtraDetails();
       String isParking = rentalHome.getIsParking();
       String isAvailable = rentalHome.getIsAvailable();
       int room = rentalHome.getRoom();
       String ownerName = rentalHome.getOwnerName();
       String email = rentalHome.getEmail();
       String submittedBy = rentalHome.getSubmittedBy();
       
       Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/rentalhomedemo","root","");
		PreparedStatement pst=con.prepareStatement("UPDATE property SET Address='"+address+"' , OwnerName='"+ownerName+"' , OwnerContact='"+ownerContact+"', Email='"+email+"', City='"+city+"' ,"
				+ " PropertyType='"+propertyType+"' , Locality='"+locality+"' , AvailableFrom= '"+availableFrom+"' , Area="+area+" , Rent="+rent+" , Status='"+status+"', WaterSupply='"+waterSupply+"', Location='"+location+"' ,"
						+ " ImageUrl='"+imageUrl+"' , ExtraDetails='"+extraDetail+"', IsParking='"+isParking+"' , IsAvailable='"+isAvailable+"' , Room="+room+", OwnerName='"+ownerName+"' WHERE PropertyId= "+propertyId);
       
		int count = pst.executeUpdate();
		
       if(count>0)
       {
       	jsonResponse.setResult(JSONResponse.SUCCESS);
       	jsonResponse.setMessage("Property Successfully Updated");
       }
       else
       {
       	jsonResponse.setResult(JSONResponse.FAIL);
       	jsonResponse.setMessage("Property Not Updated");
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
