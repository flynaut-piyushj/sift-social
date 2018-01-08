package com.sift.apis.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.sift.apis.beans.entity.UserMasterDtl;

@Repository
public class ProfileDAOImpl extends BaseDAOImpl implements ProfileDAO {

	@Override
	public UserMasterDtl getUserDetails(String username)
	{
		return jdbcTemplate.query(sqlProperties.getProperty("select.usermasterdtls.user"),new Object[]{username}, new ResultSetExtractor<UserMasterDtl>()
				{
					@Override
					public UserMasterDtl extractData(ResultSet rs) throws SQLException, DataAccessException {
						UserMasterDtl master = null;
						if(rs.next())
						{
							master = new UserMasterDtl();
							master.setFirstName(rs.getString(1));
							master.setLastName(rs.getString(2));
							master.setEmailId(rs.getString(3));
							master.setGender(rs.getString(4));
							master.setOccupation(rs.getString(5));
							master.setDateOfBirth(rs.getString(6));
							master.setUserProfilePhoto(rs.getString(7));
							master.setPassword(rs.getString(8));
							master.setCity(rs.getString(9));
							master.setState(rs.getString(10));
							master.setCountry(rs.getString(11));
						}
						return master;
					}
				});
	}
	@Override
	public int updateProfile(String username, UserMasterDtl dtls)
	{
/*		user_master_dtls.FIRST_NAME=?,user_master_dtls.LAST_NAME=?,user_master_dtls.USER_PROFILE_PHOTO=?,user_master_dtls.EMAIL_ID=?,user_master_dtls.GENDER=?,
		user_master_dtls.city_id=?,user_master_dtls.OCCUPATION=?,user_master_dtls.MODIFIED_TS=NOW(),user_login_dtls.EMAIL_ID=? where user_master_dtls.USER_MASTER_DTLS_ID=?
*/		String query = "update user_master_dtls inner join user_login_dtls on user_master_dtls.USER_MASTER_DTLS_ID = user_login_dtls.USER_MASTER_DTLS_ID set user_master_dtls.FIRST_NAME=?,user_master_dtls.LAST_NAME=?,";
		Object [] param = new Object [6+(dtls.getUserProfilePhoto()!=null?1:0)+(dtls.getEmailId()!=null?2:0)];
		int[] argTypes = new int[param.length];
		argTypes[0] = Types.VARCHAR;
		param[0] = dtls.getFirstName();
		argTypes[1] = Types.VARCHAR;
		param[1] = dtls.getLastName();
		byte check = 0;
		if(dtls.getUserProfilePhoto()!=null)
		{
			query += "user_master_dtls.USER_PROFILE_PHOTO=?,";
			argTypes[2] = Types.VARCHAR;
			param[2] = dtls.getUserProfilePhoto();
			check++;
		}
		if(dtls.getEmailId() != null)
		{
			argTypes[2+check] = Types.VARCHAR;
			param[2+check] = dtls.getEmailId();
			argTypes[6+check] = Types.VARCHAR;
			param[6+check] = dtls.getEmailId();
			query += "user_master_dtls.EMAIL_ID=?,";
			check += 2;
		}
		if(check == 0)
		{
			argTypes[2] = Types.VARCHAR;
			argTypes[3] = Types.INTEGER;
			argTypes[4] = Types.VARCHAR;
			argTypes[5] = Types.VARCHAR;
			param[2] = dtls.getGender();
			param[3] = dtls.getCity();
			param[4] = dtls.getOccupation();
			param[5] = username;
		}
		else if(check == 1 || check == 2)
		{
			int lastCheck = 6 + (check == 2 ? 1:0);
			argTypes[3] = Types.VARCHAR;
			argTypes[4] = Types.INTEGER;
			argTypes[5] = Types.VARCHAR;
			argTypes[ lastCheck ] = Types.VARCHAR;
			param[3] = dtls.getGender();
			param[4] = dtls.getCity();
			param[5] = dtls.getOccupation();
			param[ lastCheck ] = username;
		}else if(check == 2)
		{
			argTypes[3] = Types.VARCHAR;
			argTypes[4] = Types.INTEGER;
			argTypes[5] = Types.VARCHAR;
			argTypes[7] = Types.VARCHAR;
			param[3] = dtls.getGender();
			param[4] = dtls.getCity();
			param[5] = dtls.getOccupation();
			param[7] = username;
		}else
		{
			argTypes[4] = Types.VARCHAR;
			argTypes[5] = Types.INTEGER;
			argTypes[6] = Types.VARCHAR;
			argTypes[8] = Types.VARCHAR;
			param[4] = dtls.getGender();
			param[5] = dtls.getCity();
			param[6] = dtls.getOccupation();
			param[8] = username;
		}
		query += "user_master_dtls.GENDER=?,user_master_dtls.city_id=?,user_master_dtls.OCCUPATION=?,user_master_dtls.MODIFIED_TS=NOW(),user_login_dtls.EMAIL_ID=? where user_master_dtls.USER_MASTER_DTLS_ID=?";
		return jdbcTemplate.update(query, param, argTypes);
	}
}