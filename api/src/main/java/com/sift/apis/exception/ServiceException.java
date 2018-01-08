package com.sift.apis.exception;

public class ServiceException extends RuntimeException
{
	public static final long serialVersionUID = 1L;
	public ServiceException(String message) {
		super(message);
	}
}
