package com.cyg.dao;

import com.cyg.models.Admin;

public interface AdminDao {
	Admin getByUserName(String userName);
}
