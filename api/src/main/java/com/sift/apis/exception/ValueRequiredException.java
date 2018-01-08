package com.sift.apis.exception;

public class ValueRequiredException extends RuntimeException
{
	private static final long serialVersionUID=1L;
	
	public ValueRequiredException(String whatsIdentifier)
	{
		super(""+whatsIdentifier);
	}
}