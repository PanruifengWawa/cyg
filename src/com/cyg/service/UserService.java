package com.cyg.service;



import java.util.List;

import com.cyg.models.User;
import com.cyg.utils.DataWrapper;

public interface UserService {
	DataWrapper<User> login(String userName,String password,Integer type);
	DataWrapper<Void> register(User user);
	
	DataWrapper<List<User>> getUserList(Integer identity,String phone,String email,String companyName,String name,Integer numPerPage,Integer pageNum,String token);

	DataWrapper<User> getById(Long userId,String token);
//	DataWrapper<User> updateUser(User user,String token); 
}
