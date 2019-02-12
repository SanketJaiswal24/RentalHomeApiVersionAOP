package com.journaldev.spring.model;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;

@ApiModel(description="JSONInputFilterSearch")
public class JSONInputFilterSearch implements Serializable 
{
	
 private String propertyType,locality,city;
int rentFrom,rentTo;
double minimumArea;
public JSONInputFilterSearch() {
	super();
}
public JSONInputFilterSearch(String propertyType, String locality, int rentFrom, int rentTo, double minimumArea,String city) {
	super();
	this.propertyType = propertyType;
	this.locality = locality;
	this.rentFrom = rentFrom;
	this.rentTo = rentTo;
	this.minimumArea = minimumArea;
	this.city = city;
}
public String getPropertyType() {
	return propertyType;
}
public void setPropertyType(String propertyType) {
	this.propertyType = propertyType;
}
public String getLocality() {
	return locality;
}
public void setLocality(String locality) {
	this.locality = locality;
}
public int getRentFrom() {
	return rentFrom;
}
public void setRentFrom(int rentFrom) {
	this.rentFrom = rentFrom;
}
public int getRentTo() {
	return rentTo;
}
public void setRentTo(int rentTo) {
	this.rentTo = rentTo;
}
public double getMinimumArea() {
	return minimumArea;
}
public void setMinimumArea(double minimumArea) {
	this.minimumArea = minimumArea;
}
public String getCity() {
	return city;
}
public void setCity(String city) {
	this.city = city;
}
}
