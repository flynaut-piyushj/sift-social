package com.sift.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sift.web.beans.LoginDtlsTo;
import com.sift.web.dao.ChangePasswordDAO;

@Service
@Transactional
public class ChangePasswordServiceImpl implements ChangePasswordService{

	@Autowired ChangePasswordDAO ChangePasswordDAO;
	 
	@Autowired
	    private PasswordEncoder passwordEncoder;
	 
	@Override
	public int changePassword(String newPassword,String enterPassword,LoginDtlsTo loginDtlsTo) {
		System.out.println("Enter Password: "+enterPassword);
		boolean flag=passwordEncoder.matches(enterPassword, loginDtlsTo.getPassword());
		System.out.println("Password Match: "+flag);
		
		if(flag==true&&newPassword.equals(loginDtlsTo.getPassword()))
		{
			return 2;
		}
		
		else  if(flag==true)
		{
			ChangePasswordDAO.changePassword(newPassword, loginDtlsTo);
			return 1;
		}
		
		return 0;
	}

	

}
