package com.sift.apis.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class EmailSendingException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
