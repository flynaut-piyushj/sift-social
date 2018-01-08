package com.sift.apis.exception;

public class InvalidValueException extends RuntimeException
{
	
	private static final long serialVersionUID = 1L;

	public InvalidValueException(String required)
	{
		super(required);
	}
}
