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
@Api("FILTER-SEARCH-CONTROLLER")
public class FilterSearchController 
{
	@RequestMapping(value = "/filterSearch/v1.1", method = RequestMethod.POST, produces="application/json", consumes="application/json")
	@ResponseBody
	@ApiOperation(value = "USER CAN FILTER PROPERTY DETAILS",response=JSONInputFilterSearch.class,tags="FilterSearchController")
	@Deprecated
	 public String filterSearch(@RequestBody String json) throws JsonProcessingException
	 {
		
		ObjectMapper mapper=new ObjectMapper();
		JSONResponse jsonResponse = new JSONResponse();
		jsonResponse.setResult(JSONResponse.FAIL);
		jsonResponse.setMessage("Data not Found");
		String errorMessage = null;
		
		
		String cityQuery="";
		String areaQuery="";
		String localityQuery="";
		String rentQuery="";
		String propertyTypeQuery="";
		
		try {
			
        JSONInputFilterSearch search =mapper.readValue(json, JSONInputFilterSearch.class);
        
        String propertyType =search.getPropertyType();
        String city = search.getCity();
        String locality = search.getLocality();
        int rentFrom = search.getRentFrom();
        int rentTo = search.getRentTo();
        double minimumArea = search.getMinimumArea();
        
        if(!(city.equals("")))
        {
        		cityQuery=" City = '"+city+"' and ";
        }
        if(!(minimumArea == 0.000))
        {
        	    areaQuery= "  Area >= "+minimumArea+" and ";
        }
        if(!(locality.equals("")))
        {
        		localityQuery=" Locality = '"+locality+"' and ";
        }
        if(!(propertyType.equals("")))
        {
        		propertyTypeQuery="PropertyType = '"+propertyType+"' and ";
        }
        if(!((rentFrom == 0)||(rentTo==0)))
        {
        	    rentQuery= " Rent between "+rentFrom+" and "+rentTo;
        }
        
        Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/RentalHomeDemo","root","");
		PreparedStatement pst=con.prepareStatement("select * from property where "
				+ propertyTypeQuery
				+ cityQuery
				+ localityQuery
				+ areaQuery
				+ rentQuery+"");
		
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
			rentalHome.setSubmittedBy(rs.getString("SubmittedBy"));

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
				 jsonResponse.setMessage("Problem "+errorMessage);
			     e.printStackTrace();
		}
        
		ObjectMapper mapperBack =new ObjectMapper();
		
	     
		
		 return  mapperBack.writeValueAsString(jsonResponse);
	 }

}
