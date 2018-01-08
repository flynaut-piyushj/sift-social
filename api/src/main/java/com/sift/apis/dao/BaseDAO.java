package com.sift.apis.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.sift.apis.beans.entity.OtpDetails;
import com.sift.apis.beans.entity.UserLoginDtl;
import com.sift.apis.exception.ServicesException;

public interface BaseDAO {

	String getUUID() throws DataAccessException;

	boolean sendOTP(String cntcNum, String otp, String deviceInfo) throws Exception;

	List<OtpDetails> selectOTPDetailsByCntcNum(String cntcNum) throws ServicesException;

	int insertOTPRecord(String cntcNum, String otp, String deviceInfo);

	int updateOTPRecordById(int otpId, String otp, int noOfAttempts);

	boolean verifyOTP(String cntcNum, String otp, String deviceInfo) throws ServicesException, Exception;
	
	UserLoginDtl getloggedUser();
	
	String getLoggedInUserId();
}
