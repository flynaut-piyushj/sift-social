package com.sift.web.dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.sift.web.beans.LoginDtlsTo;


@Repository
@Qualifier("userService")
public class UserDAOImpl extends BaseDAOImpl implements UserDAO {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		System.out.println("Username: " + username);
		try {
			String sql = "SELECT USER_ID, USER_NAME, EMAIL_ID, PASSWORD, USER_TYPE, USER_ROLE, CREATED_TS, MODIFIED_TS, IS_ACTIVE,IS_EMAIL_VERIFIED "
					+ "from user u where u.IS_ACTIVE=1 and u.IS_EMAIL_VERIFIED=1 and u.EMAIL_ID=? or u.USER_NAME=?";
			List<LoginDtlsTo> userLogin=jdbcTemplate.query(sql, new Object[]{username, username},new BeanPropertyRowMapper<LoginDtlsTo>(LoginDtlsTo.class));
			System.out.println(userLogin.size());
			if (userLogin.isEmpty()) {
				throw new UsernameNotFoundException("The user with name "
						+ username + " was not found");	
			}else{
				LoginDtlsTo loggedInUserDetails = userLogin.get(0);
				System.out.println("Logging user: " + loggedInUserDetails.toString());
				Set<String> roles = new HashSet<String>(getRoles(loggedInUserDetails.getUsername()));
				loggedInUserDetails.setRoles(roles);
				
				System.out.println("Returning user: " + loggedInUserDetails.toString());
		       	return loggedInUserDetails;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new UsernameNotFoundException("The user with name "
					+ username + " was not found");	
		}
	
	}
	
	private List<String> getRoles(String loginDtlsId){
		String sql="select ROLE_ID From user_roles where USER_LOGIN_DTLS_ID=?";
		System.out.println("Inside Get Roles");
		return jdbcTemplate.query(sql,new Object[] {loginDtlsId}, new RowMapper<String>() {
					@Override
					public String mapRow(ResultSet rs, int rowNum) throws SQLException {
						return rs.getString("ROLE_ID");
					}
				});
	
	}
	
	@Override
	public String getEmailTemplateId(String emailTemplateType) {
		
		String emailTemplateId= jdbcTemplate.queryForObject(sqlProperties.getProperty("select.email_template.email_template_id"), new Object[] {emailTemplateType}, String.class);
		return emailTemplateId;
	}

	@Override
	public String getUserDtlsId(String userEmailId) {
		
		String userDtlsId= jdbcTemplate.queryForObject(sqlProperties.getProperty("select.user.user_id"), new Object[] {userEmailId}, String.class);
		return userDtlsId;
	}
	

}
