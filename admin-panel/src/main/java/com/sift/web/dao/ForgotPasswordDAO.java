package com.sift.web.dao;

public interface ForgotPasswordDAO {

public int saveData(String userDtlsId, String emailTemplateId);
public int resetPassword( String password, String forgotPassCode);

}
