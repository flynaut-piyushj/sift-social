package com.sift.web.dao;

import org.springframework.dao.DataAccessException;

public interface BaseDAO {

	String getUUID() throws DataAccessException;
}
