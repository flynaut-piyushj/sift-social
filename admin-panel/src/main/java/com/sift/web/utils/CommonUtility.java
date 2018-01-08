package com.sift.web.utils;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.sift.web.beans.LoginDtlsTo;

@Component
public class CommonUtility {

	public LoginDtlsTo getLoggedUser() {
		LoginDtlsTo loginDtlsTo = (LoginDtlsTo)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return loginDtlsTo;
    }
}
