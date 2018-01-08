package com.sift.apis.restcontroller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sift.apis.beans.entity.UserMasterDtl;
import com.sift.apis.exception.ServicesException;
import com.sift.apis.service.ProfileService;

@RestController
public class ProfileController {
	
	@Autowired
	private ProfileService profileService;
	@RequestMapping(value="/profile", method=RequestMethod.GET)
	public Object doGetUserDetails(Authentication authentication) throws ServicesException {
		return profileService.getUserDetails(authentication);
	}
	@RequestMapping(value="/profile", method=RequestMethod.PUT)
	public Object doUpdateProfile(@RequestBody UserMasterDtl dtls,Authentication authentication)
	{
		return profileService.updateProfile(dtls,authentication);
	}
}