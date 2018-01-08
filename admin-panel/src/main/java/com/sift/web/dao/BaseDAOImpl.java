package com.sift.web.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.sift.web.exception.ServicesException;


@Repository
public class BaseDAOImpl implements BaseDAO{

	@Autowired
	protected JdbcTemplate jdbcTemplate;

	@Autowired
	protected Properties sqlProperties;
	
	private static final Logger logger=LoggerFactory.getLogger(BaseDAOImpl.class);
	
	public String getUUID() throws DataAccessException {
		
		String uuid = jdbcTemplate.query("select uuid()", new ResultSetExtractor<String>() {
			@Override
			public String extractData(ResultSet rs) throws SQLException, DataAccessException {
				rs.next();
				return rs.getString(1);
			}
		});
		
		return uuid;
	}
	
	public Date convertToSqlDate(String date) throws ServicesException{
		  String pattern = "yyyy-MM-dd";
		    SimpleDateFormat format = new SimpleDateFormat(pattern);
		try {
			return  format.parse(date);
		} catch (ParseException e) {
			throw new ServicesException("611");
		}
	}
	
	/*public UserLoginDtl getloggedUser() {
		try{
		logger.info(" spring security "+SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal() );
		
		return (UserLoginDtl) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	
	}
	
	public String getLoggedInUserId() {
		UserLoginDtl userDetails = (UserLoginDtl) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		return userDetails.getTrackId();
	} */
	
}
