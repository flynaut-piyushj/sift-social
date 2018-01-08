package com.sift.web.dao;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

@Repository
public class ForgotPasswordDAOImpl extends BaseDAOImpl implements ForgotPasswordDAO{
//TODO:Cron Jobs
	public static Logger logger = Logger.getLogger(ForgotPasswordDAOImpl.class);
	
	

	@Override
	public int saveData(String userDtlsId, String emailTemplateId)
	{
		int flag=0;		
		flag=jdbcTemplate.update(sqlProperties.getProperty("insert.email_dtls"),new Object[] {userDtlsId,emailTemplateId});
		flag=jdbcTemplate.update(sqlProperties.getProperty("update.user.forgot_pass_code"),new Object[] {getUUID(),userDtlsId});
		return flag;
	}
	
	public int resetPassword( String password, String forgotPassCode)
	{
		int flag=0;		
		flag=jdbcTemplate.update(sqlProperties.getProperty("update.user.password"),new Object[] {password,forgotPassCode});
		return flag;
	}

}
