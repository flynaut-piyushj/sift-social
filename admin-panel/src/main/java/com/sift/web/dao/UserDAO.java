package com.sift.web.dao;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserDAO  extends UserDetailsService {
	
	String getEmailTemplateId(String emailTemplateType);
	
	String getUserDtlsId(String userEmailId);
}
