
package com.sift.web.dao;

import com.sift.web.beans.LoginDtlsTo;

public interface ChangePasswordDAO {
	public int changePassword(String newPassword, LoginDtlsTo loginDtlsTo);

}
