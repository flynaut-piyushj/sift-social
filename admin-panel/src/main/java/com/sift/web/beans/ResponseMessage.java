package com.sift.web.beans;

import org.springframework.stereotype.Component;

@Component
public class ResponseMessage {
	
	private String status;
	
	private String message;
	
	private String apiVersion;
	
	public ResponseMessage() {}
	
	public ResponseMessage(String status, String message, String apiVersion) {
		this.status = status;
		this.message = message;
		this.apiVersion=apiVersion;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getApiVersion() {
		return apiVersion;
	}

}
