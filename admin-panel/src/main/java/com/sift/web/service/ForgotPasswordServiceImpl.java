package com.sift.web.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sift.web.dao.BaseDAOImpl;
import com.sift.web.dao.ForgotPasswordDAO;
import com.sift.web.dao.UserDAO;


@Service
@Transactional
public class ForgotPasswordServiceImpl implements ForgotPasswordService{
	
	public static Logger logger = Logger.getLogger(ForgotPasswordServiceImpl.class);

	@Autowired
	private UserDAO userDAO;
	@Autowired 
	ForgotPasswordDAO forgotPasswordDAO;
	@Autowired
	private BaseDAOImpl baseDAOImpl;
	@Override
	
	// save data to emai_dtls table for cron job 
	public int saveData(String userEmailId, String emailTemplateType) {
		int flag=0;
		String userDtlsId;
		String emailTemplateId="";
		try
		{
			userDtlsId=userDAO.getUserDtlsId(userEmailId);
			emailTemplateId=userDAO.getEmailTemplateId(emailTemplateType);
			flag=forgotPasswordDAO.saveData(userDtlsId,emailTemplateId);
			return flag;
		}
		catch(Exception exception)
		{
		exception.printStackTrace();
			return flag;

			
		}
		
		
	}
	
	@Override
	public int resetPassword( String password, String forgotPassCode)
	{
		int flag=0;		
		forgotPasswordDAO.resetPassword(password, forgotPassCode);
		return flag;
	}

}
