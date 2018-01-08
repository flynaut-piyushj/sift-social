package com.sift.apis.beans;

public class SingleResponce extends BaseWrapper
{
	private Object value;

	public SingleResponce(Object value,ResponseMessage response) {
		super(response);
		this.value = value;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}
	
}
