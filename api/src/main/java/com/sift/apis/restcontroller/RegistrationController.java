package com.sift.apis.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sift.apis.beans.BaseWrapper;
import com.sift.apis.beans.entity.UserMasterDtl;
import com.sift.apis.exception.ServicesException;
import com.sift.apis.service.RegistrationService;


@RestController
public class RegistrationController {

	@Autowired
	private RegistrationService registrationService;
	
	@ResponseBody
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public BaseWrapper doPostUserDetails(@RequestBody UserMasterDtl userMasterDtl) throws ServicesException {
		return registrationService.postUserDetails(userMasterDtl);
	}
}
