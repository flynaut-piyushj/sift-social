package com.sift.web.configuration;

import javax.servlet.Filter;

import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class BaseModuleInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{

	@Override
	protected Class<?>[] getRootConfigClasses() {
		
		return new Class[] { BaseModuleConfiguration.class};
	}
	
	@Override
	protected Class<?>[] getServletConfigClasses() {
		
		return null;
	}
	
	@Override
	protected String[] getServletMappings() {
		
		return new String[] { "/"};
	}

	
	@Override
	protected Filter[] getServletFilters() {
		 /*return new Filter[]{ 
			       new DelegatingFilterProxy("springSecurityFilterChain") };*/
		return null;
	}
}
