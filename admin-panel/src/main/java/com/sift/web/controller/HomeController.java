package com.sift.web.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
	
	public static Logger logger = Logger.getLogger(HomeController.class); 
	
	@RequestMapping(value="dashboard", method=RequestMethod.GET)
	public ModelAndView getDashboard()
	{
		try {
			logger.debug("Called Dashboard");
			ModelAndView model = new ModelAndView();
			model.setViewName("dashboard/dashboard");
			logger.debug("Returning Dashboard");
			return model;
		} catch (Exception e) {
			logger.error("Exception Occured", e);
			throw e;
		}
	}
	
	@RequestMapping(value="openpage", method=RequestMethod.GET)
	public ModelAndView doDashboard(@RequestParam("pageName") String pageName)
	{
		try {
			logger.debug("Called: " + pageName);
			ModelAndView model = new ModelAndView();
			model.setViewName(pageName);
			logger.debug("Returning: " + pageName);
			return model;
		} catch (Exception e) {
			logger.error("Exception Occured", e);
			throw e;
		}
	}
	
	
}
