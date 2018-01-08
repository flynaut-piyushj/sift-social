package com.sift.web.service;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.sift.web.beans.LoginDtlsTo;
import com.sift.web.dao.ForgotPasswordDAOImpl;

@Repository
public interface ChangePasswordService {
	public static Logger logger = Logger.getLogger(ForgotPasswordDAOImpl.class);
	public int changePassword(String newPassword,String enterPassword,LoginDtlsTo loginDtlsTo);

}
