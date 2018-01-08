package com.sift.apis.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sift.apis.beans.ChangePassword;
import com.sift.apis.beans.MasterDataWrapper;
import com.sift.apis.exception.ServicesException;
import com.sift.apis.service.UtilityService;

@RestController
public class UtilityController 
{
	@Autowired
	private UtilityService service;

	@ResponseBody
	@RequestMapping(value="/masterdata")
	public MasterDataWrapper getMasterData()
	{
		return service.getMasterData();
	}
	
	@RequestMapping(value="/forgotPassword",method=RequestMethod.POST)
	public void forgotPassword(@RequestParam String email)
	{
		service.forgotPassword(email);
	}
	
	@RequestMapping(value="/password",method=RequestMethod.PUT)
	public void changePassword(@RequestBody ChangePassword password,Authentication authentication)
	{
		if(authentication!=null)
			service.changePassword(password,authentication);
		else
			throw new ServicesException("616");
	}
	
	@RequestMapping(value="/resetpassword/{forgotPassCode}",method=RequestMethod.POST)
	public String resetPassword(@PathVariable("forgotPassCode") String forgotPassCode,RedirectAttributes attribute)
	{
		if(service.resetPassword(forgotPassCode) != null)
		{
			attribute.addFlashAttribute("passCode", forgotPassCode);
			return "change";
		}
		else
			return "redirect:error";
	}
}