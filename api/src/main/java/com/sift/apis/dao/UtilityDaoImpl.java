package com.sift.apis.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.sift.apis.beans.Category;
import com.sift.apis.beans.City;
import com.sift.apis.beans.Country;
import com.sift.apis.beans.State;

@Repository
public class UtilityDaoImpl extends BaseDAOImpl implements UtilityDao
{
	@Autowired
	private PasswordEncoder encoder;	
	@Override
	public Object[] getMasterData()
	{
		List<Country> country = jdbcTemplate.query("select country_dtls.COUNTRY_DTLS_ID,country_dtls.COUNTRY_NM from country_dtls where country_dtls.IS_ACTIVE = 'Y'", new RowMapper<Country>() {
			@Override
			public Country mapRow(ResultSet rs, int rowNum) throws SQLException 
			{
				Country country = new Country();
				country.setId(rs.getString(1));
				country.setName(rs.getString(2));
				return country;
			}
			});
		List<State> state = jdbcTemplate.query("select state_dtls.STATE_DTLS_ID,state_dtls.STATE_NM,COUNTRY_DTLS_ID from state_dtls",  new RowMapper<State>() {
			@Override
			public State mapRow(ResultSet rs, int rowNum) throws SQLException {
				State state = new State();
				state.setId(rs.getString(1));
				state.setName(rs.getString(2));
				state.setCountryDtlId(rs.getString(3));
				return state;
			}
		});
		List<City> cities = jdbcTemplate.query("select city_dtls.CITY_DTLS_ID,city_dtls.CITY_NM,STATE_DTLS_ID from city_dtls", new RowMapper<City>()
		{
			@Override
			public City mapRow(ResultSet rs, int rowNum) throws SQLException {
				City city = new City();
				city.setId(rs.getString(1));
				city.setName(rs.getString(2));
				city.setStateDtlId(rs.getString(3));
				return city;
			}
		});
		List<Category> categories = jdbcTemplate.query("SELECT master_category_dtls.MASTER_CATEGORY_DTLS_ID,master_category_dtls.CATEGORY_NAME FROM sift.master_category_dtls", new RowMapper<Category>()
		{
			@Override
			public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
				Category category = new Category();
				category.setId(rs.getString(1));
				category.setName(rs.getString(2));
				return category;
			}
		});		
		
		return new Object[] { country,state,cities,categories };
	}
	@Override
	public String forgotPassword(String email) 
	{
		if(jdbcTemplate.query("select user_master_dtls.EMAIL_ID from user_master_dtls where user_master_dtls.EMAIL_ID = ?", new Object[] { email },new ResultSetExtractor<String>()
		{
			@Override
			public String extractData(ResultSet rs) throws SQLException, DataAccessException {
				if(rs.next())
					return rs.getString(1);
				else
					return null;
			}
		}) != null)
		{
			String uuid = getUUID();
			int cnt = jdbcTemplate.update("update user_login_dtls set FORGOT_PASS_CODE = ? where EMAIL_ID = ?",uuid,email);			
			cnt += jdbcTemplate.update("insert into email_dtls(email_dtls.EMAIL,email_dtls.EMAIL_TYPE) values(?,'FP')", email);
			if(cnt==2)
				return "1";
			else
				return "608";
		}
		return "903";
	}
	@Override
	public int changePassword(String oldPassword, String newPassword, String emailId) {
		if(jdbcTemplate.query("select user_master_dtls.EMAIL_ID from user_master_dtls where user_master_dtls.EMAIL_ID = ? and user_master_dtls.PASSWORD = ?", new Object[] { emailId,oldPassword }, new ResultSetExtractor<String>()
				{
					@Override
					public String extractData(ResultSet rs) throws SQLException, DataAccessException {
						if(rs.next())
							return rs.getString(1);
						else
							return null;
					}
		})!=null)
			return jdbcTemplate.update("update user_master_dtls inner join user_login_dtls on user_master_dtls.USER_MASTER_DTLS_ID = user_login_dtls.USER_MASTER_DTLS_ID set user_master_dtls.PASSWORD = ?,user_login_dtls.PASSWORD=? where user_master_dtls.EMAIL_ID = ?", newPassword,encoder.encode(newPassword),emailId);
		return -1;
	}
	@Override
	public int resetPassword(String forgotPassCode) {
		return jdbcTemplate.query("select user_login_dtls.EMAIL_ID from user_login_dtls where user_login_dtls.FORGOT_PASS_CODE = ?", new Object[] { forgotPassCode }, new ResultSetExtractor<String>(){
			@Override
			public String extractData(ResultSet rs) throws SQLException, DataAccessException {
				if(rs.next())
					return rs.getString(1);
				return null;
			}
		})!=null?1:0;
	}
}