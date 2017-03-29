package com.cyg.dao;

import java.util.List;

import com.cyg.models.User;
import com.cyg.utils.DataWrapper;

public interface UserDao {
	User getByUserName(String userName);
	boolean addUser(User user);
	DataWrapper<List<User>> getUserList(Integer identity, String phone, String email, String companyName,
			String name, Integer numPerPage, Integer pageNum);
	
	User getById(Long id);
}
