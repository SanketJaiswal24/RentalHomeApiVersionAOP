package com.journaldev.spring.model;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;

@ApiModel(description="RsponseType")
public class JSONResponse implements Serializable {
	
	public static String FAIL="FAIL";
	public static String ERROR = "ERROR";
	public static String SUCCESS="SUCCESS";
	String result,message;
	Object data;
	public JSONResponse() {
		super();
	}
	public JSONResponse(String result, String message, Object data) {
		super();
		this.result = result;
		this.message = message;
		this.data = data;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "JSONResponse [result=" + result + ", message=" + message + ", data=" + data + "]";
	}
	
	

}
