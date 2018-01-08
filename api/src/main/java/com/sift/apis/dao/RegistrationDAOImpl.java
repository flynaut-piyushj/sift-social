package com.sift.apis.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.sift.apis.beans.entity.UserMasterDtl;

@Repository
public class RegistrationDAOImpl extends BaseDAOImpl  implements RegistrationDAO {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public int insertUserDetails(String userMasterDtlsId, UserMasterDtl userMasterDtl,String userLoginDtls) {
		
		if(jdbcTemplate.query("select user_login_dtls.EMAIL_ID from user_login_dtls where user_login_dtls.EMAIL_ID = ?", new Object[] { userMasterDtl.getEmailId() }, new ResultSetExtractor<String>() {
			@Override
			public String extractData(ResultSet rs) throws SQLException, DataAccessException {
				if(rs.next())
					rs.getString(1);
				return null;
			}
		})==null)
		{
			int cnt = jdbcTemplate.update(sqlProperties.getProperty("insert.usermasterdtls"),new Object[]{userMasterDtlsId,
					userMasterDtl.getFirstName(),userMasterDtl.getLastName(),
					userMasterDtl.getEmailId(),userMasterDtl.getPassword(),userMasterDtl.getGender(),userMasterDtl.getDateOfBirth(),userMasterDtl.getOccupation(),userMasterDtl.getCity()});
			cnt += jdbcTemplate.update(sqlProperties.getProperty("insert.userlogindtls"),new Object[] { userLoginDtls,userMasterDtlsId,passwordEncoder.encode(userMasterDtl.getPassword()),userMasterDtl.getEmailId() });
			cnt += jdbcTemplate.update("insert into email_dtls(email_dtls.EMAIL,email_dtls.EMAIL_TYPE) values(?,'REG')",userMasterDtl.getEmailId());
			return cnt;
		}else
			return -1;
	}
}
