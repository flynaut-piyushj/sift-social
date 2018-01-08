package com.sift.apis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

public class TestController 
{
	@Autowired
	private PasswordEncoder passwordEncoder;

	@ResponseBody
	@RequestMapping("/pass")
	public String getPass(@RequestParam String pass)
	{
		System.out.println("pass: "+pass);
		return passwordEncoder.encode(pass);
	}
}
