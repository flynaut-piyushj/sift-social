package com.sift.apis.beans;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

@Component
public class BaseWrapper {
	
	@Override
	public String toString() {
		return "BaseWrapper [responseMessage=" + responseMessage + "]";
	}

	@JsonProperty("responseMessage")
	private ResponseMessage responseMessage;
	
	public BaseWrapper() {}

	public BaseWrapper(ResponseMessage responseMessage) {

		this.responseMessage = responseMessage;
	}

	public ResponseMessage getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(ResponseMessage responseMessage) {
		this.responseMessage = responseMessage;
	}	
	
}
