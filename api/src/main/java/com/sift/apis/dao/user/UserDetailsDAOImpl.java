package com.sift.apis.dao.user;

import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.sift.apis.beans.UserLoginDtlTo;
import com.sift.apis.beans.entity.UserLoginDtl;

@Component("userDetailsService")
public class UserDetailsDAOImpl implements UserDetailsDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private Logger logger = Logger.getLogger(UserDetailsDAOImpl.class);
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
	{
		logger.info("Login Username: " + username);
		List<UserLoginDtl> userDtlsList = jdbcTemplate.query("select * from user_login_dtls where EMAIL_ID=?", new Object[] {username}, 
				new BeanPropertyRowMapper<UserLoginDtl>(UserLoginDtl.class));
		
		if (null == userDtlsList || userDtlsList.size() <= 0) 
		{
			throw new UsernameNotFoundException("The user with name " + username + " was not found");
		}else
		{
			UserLoginDtlTo userLoginDtlTo = new UserLoginDtlTo();

			try {
				
			UserLoginDtl userLoginDtls = userDtlsList.get(0);
			logger.info(userLoginDtls.toString());
			String userLoginDtlsId = userLoginDtls.getUserLoginDtlsId();
			List<String> authoritiesList = jdbcTemplate.query("select ROLE_NAME from user_role_dtls as urd "
					+ "inner join role_master_dtls as rmd on urd.ROLES_MASTER_DTLS_ID=rmd.ROLES_MASTER_DTLS_ID "
					+ "where urd.USER_LOGIN_DTLS_ID=?", new Object[] {userLoginDtlsId.trim()}, 
					new RowMapper<String>() {
						@Override
						public String mapRow(ResultSet rs, int rowNum) throws SQLException {
							return rs.getString("ROLE_NAME");
						}
			});
			
			List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
			
			int authoritiesListSize = authoritiesList.size();
			
			if (authoritiesListSize > 0) {
				for(int i=0; i<authoritiesListSize; i++) {
					String grantedAuthorityName = authoritiesList.get(i);
					logger.info(grantedAuthorityName);
					GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(grantedAuthorityName);
					grantedAuthorities.add(grantedAuthority);
				}
			} 
			
			userLoginDtlTo.setGrantedAuthorities(grantedAuthorities);
			
			try {
				BeanUtils.copyProperties(userLoginDtlTo, userLoginDtls);
			} catch (IllegalAccessException e) {
				// TODO Use logger to log error
				System.out.println("here 74");
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Use logger to log error
				System.out.println("here 77");
				e.printStackTrace();
			}catch(Exception i)
			{
				System.out.println("all exe");
			}
			}catch (Exception e) {
				System.out.println("here 91");
			}
			return userLoginDtlTo;
		}
	}

}
