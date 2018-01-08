package com.sift.web.beans;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class UserLoginDetails {

	@NotNull(message="Username is a required field")
	@NotEmpty(message="Username is a required field")
	private String username;
	
	@NotNull(message="Password is a required field")
	@NotEmpty(message="Password is a required field")
	private String password;
	
	public UserLoginDetails() {}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
