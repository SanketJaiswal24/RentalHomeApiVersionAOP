	package com.journaldev.spring.model;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiResponses;
import io.swagger.models.Response;

@ApiModel(description="RentalHome")
public class RentalHome implements Serializable
{
	 @Override
	public String toString() {
		return "RentalHome [submittedBy=" + submittedBy + ", propertyType=" + propertyType + ", status=" + status
				+ ", locality=" + locality + ", ownerName=" + ownerName + ", ownerContact=" + ownerContact + ", email="
				+ email + ", address=" + address + ", city=" + city + ", availableFrom=" + availableFrom
				+ ", waterSupply=" + waterSupply + ", location=" + location + ", imageUrl=" + imageUrl
				+ ", extraDetails=" + extraDetails + ", isParking=" + isParking + ", isAvailable=" + isAvailable
				+ ", propertyId=" + propertyId + ", rent=" + rent + ", room=" + room + ", area=" + area + "]";
	}
	@ApiModelProperty(dataType="string",value="SubmittedBy")
	 String submittedBy;
	 @ApiModelProperty(dataType="string",value="propertyType")
     String propertyType;
     String status;
     String locality;
     String ownerName;
     String ownerContact;
     String email;
     String address;
     String city;
     String availableFrom;
     String waterSupply;
     String location;
     String imageUrl;
     String extraDetails;
     String isParking;
     String isAvailable;
     int propertyId;
     int rent;
     int room;
     int area;
   
public RentalHome() {
	super();
}
public RentalHome(String propertyType, String status, int area, String locality, String ownerName, String ownerContact,
		String email, String address, String city, String availableFrom, String waterSupply, String location,
		String imageUrl, String extraDetails, String isParking, String isAvialable,String submittedBy, int propertyId, int rent,
		int room) {
	super();
	this.propertyType = propertyType;
	this.status = status;
	this.area = area;
	this.locality = locality;
	this.ownerName = ownerName;
	this.ownerContact = ownerContact;
	this.email = email;
	this.address = address;
	this.city = city;
	this.availableFrom = availableFrom;
	this.waterSupply = waterSupply;
	this.location = location;
	this.imageUrl = imageUrl;
	this.extraDetails = extraDetails;
	this.isParking = isParking;
	this.isAvailable = isAvialable;
	this.propertyId = propertyId;
	this.rent = rent;
	this.room = room;
	this.submittedBy = submittedBy;
}

public String getSubmittedBy() {
	return submittedBy;
}
public void setSubmittedBy(String submittedBy) {
	this.submittedBy = submittedBy;
}
public String getPropertyType() {
	return propertyType;
}
public void setPropertyType(String propertyType) {
	this.propertyType = propertyType;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public int getArea() {
	return area;
}
public void setArea(int area) {
	this.area = area;
}
public String getLocality() {
	return locality;
}
public void setLocality(String locality) {
	this.locality = locality;
}
public String getOwnerName() {
	return ownerName;
}
public void setOwnerName(String ownerName) {
	this.ownerName = ownerName;
}
public String getOwnerContact() {
	return ownerContact;
}
public void setOwnerContact(String ownerContact) {
	this.ownerContact = ownerContact;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public String getCity() {
	return city;
}
public void setCity(String city) {
	this.city = city;
}
public String getAvailableFrom() {
	return availableFrom;
}
public void setAvailableFrom(String availableFrom ){
	this.availableFrom = availableFrom;
}
public String getWaterSupply() {
	return waterSupply;
}
public void setWaterSupply(String waterSupply) {
	this.waterSupply = waterSupply;
}
public String getLocation() {
	return location;
}
public void setLocation(String location) {
	this.location = location;
}
public String getImageUrl() {
	return imageUrl;
}
public void setImageUrl(String imageUrl) {
	this.imageUrl = imageUrl;
}
public String getExtraDetails() {
	return extraDetails;
}
public void setExtraDetails(String extraDetails) {
	this.extraDetails = extraDetails;
}
public String getIsParking() {
	return isParking;
}
public void setIsParking(String isParking) {
	this.isParking = isParking;
}
public String getIsAvailable() {
	return isAvailable;
}
public void setIsAvailable(String isAvialable) {
	this.isAvailable = isAvialable;
}
public int getPropertyId() {
	return propertyId;
}
public void setPropertyId(int propertyId) {
	this.propertyId = propertyId;
}
public int getRent() {
	return rent;
}
public void setRent(int rent) {
	this.rent = rent;
}
public int getRoom() {
	return room;
}
public void setRoom(int room) {
	this.room = room;
}
}
