package com.sift.web.test;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.sift.web.dao.BaseDAOImpl;

public class UUIDGeneartor extends BaseDAOImpl{


	@Autowired
	protected JdbcTemplate jdbcTemplate;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UUIDGeneartor geneartor=new UUIDGeneartor();
		String uniqueID = UUID.randomUUID().toString();
		System.out.println("UUID"+uniqueID);
		
	}

}
