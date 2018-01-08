package com.sift.web.service;

import org.springframework.stereotype.Component;

@Component
public interface ForgotPasswordService {
	
	int saveData(String userEmailId, String emailTemplateType);
	public int resetPassword( String password, String forgotPassCode);

}
