package com.sift.apis.configuration;

import javax.servlet.Filter;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class BaseModuleInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{

	@Override
	protected Class<?>[] getRootConfigClasses() {		
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { BaseModuleConfiguration.class };
	}
	
	@Override
	protected String[] getServletMappings() {
		
		return new String[] { "/v1/*"};
	}

	@Override
	protected Filter[] getServletFilters() {
		 /*return new Filter[]{ 
			       new DelegatingFilterProxy("springSecurityFilterChain") };*/
		return new Filter[]{};
	}
	
     
}
