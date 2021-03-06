package com.cyg.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.cyg.dao.AdminDao;
import com.cyg.dao.UserDao;
import com.cyg.enums.ErrorCodeEnum;
import com.cyg.models.Admin;
import com.cyg.models.User;
import com.cyg.service.UserService;
import com.cyg.utils.DataWrapper;
import com.cyg.utils.MD5Util;
import com.cyg.utils.SessionManager;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	AdminDao adminDao;

	@Override
	public DataWrapper<User> login(String userName, String password, Integer type) {
		// TODO Auto-generated method stub
		DataWrapper<User> dataWrapper = new DataWrapper<User>();
		if (userName == null || type == null) {
			dataWrapper.setErrorCode(ErrorCodeEnum.Error);
			return dataWrapper;
		}
		if (type == 0) { //login as admin
			Admin admin = adminDao.getByUserName(userName);
			if (admin != null && admin.getPassword().equals(MD5Util.getMD5String(password))) {
				
				User user = new User();
				user.setId(admin.getId()-2000);
				user.setLoginName(userName);
				
				
				SessionManager.removeSessionByUserId(user.getId());
				String token = SessionManager.newSession(user);
				dataWrapper.setToken(token);
			} else {
				dataWrapper.setErrorCode(ErrorCodeEnum.Error);
			}
		} else if(type == 1 || type == 2) { //login as user,1-internal user,2-external user
			User user = userDao.getByUserName(userName);
			if (user != null) {
				if (type == 1 && (user.getIdentity() == 3 || user.getIdentity() == 4)) { //student don't need password
					SessionManager.removeSessionByUserId(user.getId());
					String token = SessionManager.newSession(user);
					dataWrapper.setToken(token);
					user.setPassword(null);
					dataWrapper.setData(user);
				} else if (type == 2 && user.getPassword() != null && user.getPassword().equals(MD5Util.getMD5String(password))) { //external user need password
					SessionManager.removeSessionByUserId(user.getId());
					String token = SessionManager.newSession(user);
					dataWrapper.setToken(token);
					user.setPassword(null);
					dataWrapper.setData(user);
				} else {
					dataWrapper.setErrorCode(ErrorCodeEnum.Error);
				}
			} else {
				dataWrapper.setErrorCode(ErrorCodeEnum.Error);
			}
		} else {
			dataWrapper.setErrorCode(ErrorCodeEnum.Error);
		}
		return dataWrapper;
	}

	@Override
	public DataWrapper<Void> register(User user) {
		// TODO Auto-generated method stub
		DataWrapper<Void> dataWrapper = new DataWrapper<Void>();
		if (user.getLoginName() != null && !user.getLoginName().trim().equals("") && user.getLoginName().length() >=4 && user.getLoginName().length() <= 31) {
			User existedUser = userDao.getByUserName(user.getLoginName());
			if (existedUser != null) {
				dataWrapper.setErrorCode(ErrorCodeEnum.Error);
				return dataWrapper;
			}
			
			user.setRegistTime(new Date());
			if (user.getIdentity() == 3 || user.getIdentity() == 4) {
				user.setPassword(null);
				if (!userDao.addUser(user)) {
					dataWrapper.setErrorCode(ErrorCodeEnum.Error);
				}
			} else if (user.getIdentity() == 1 || user.getIdentity() == 2) {
				if (user.getPassword() != null && user.getPassword().length() > 0) {
					user.setPassword(MD5Util.getMD5String(user.getPassword()));
					if (!userDao.addUser(user)) {
						dataWrapper.setErrorCode(ErrorCodeEnum.Error);
					}
				} else {
					dataWrapper.setErrorCode(ErrorCodeEnum.Error);
				}
			} else {
				dataWrapper.setErrorCode(ErrorCodeEnum.Error);
			}
		} else {
			dataWrapper.setErrorCode(ErrorCodeEnum.Error);
		}
		return dataWrapper;
	}

	@Override
	public DataWrapper<List<User>> getUserList(Integer identity, String phone, String email, String companyName,
			String name, Integer numPerPage, Integer pageNum, String token) {
		// TODO Auto-generated method stub
		DataWrapper<List<User>> dataWrapper = null;
		User user = SessionManager.getSession(token);
		if (user != null && user.getId() < 0) {
			dataWrapper = userDao.getUserList(identity, phone, email, companyName, name, numPerPage, pageNum);
			for(User u: dataWrapper.getData()) {
				u.setPassword(null);
			}
		} else {
			dataWrapper = new DataWrapper<List<User>>();
			dataWrapper.setErrorCode(ErrorCodeEnum.Error);
		}
		return dataWrapper;
	}

	@Override
	public DataWrapper<User> getById(Long userId, String token) {
		// TODO Auto-generated method stub
		DataWrapper<User> dataWrapper = new DataWrapper<User>();
		User admin = SessionManager.getSession(token);
		if (admin != null && admin.getId() < 0) {
			User user = userDao.getById(userId);
			user.setPassword(null);
			dataWrapper.setData(user);
		} else {
			dataWrapper.setErrorCode(ErrorCodeEnum.Error);
		}
		return dataWrapper;
	}

	@Override
	public DataWrapper<Void> updateUser(User user, String token) {
		// TODO Auto-generated method stub
		DataWrapper<Void> dataWrapper = new DataWrapper<Void>();
		User userInMemory = SessionManager.getSession(token);
		if (userInMemory != null && userInMemory.getId() > 0) {
			User userInDB = userDao.getById(userInMemory.getId());
			if (userInDB != null) {
				if (user.getPhone() != null && !user.getPhone().equals("")) {
					userInDB.setPhone(user.getPhone());
				}
				
				if (user.getEmail() != null && !user.getEmail().equals("")) {
					userInDB.setEmail(user.getEmail());
				}
				
				if (user.getIntention() != null && !user.getIntention().equals("")) {
					userInDB.setIntention(user.getIntention());
				}
				if (user.getCompanyName() != null && !user.getCompanyName().equals("")) {
					userInDB.setCompanyName(user.getCompanyName());
				}
				
				if (user.getCompanyType() != null && !user.getCompanyType().equals("")) {
					userInDB.setCompanyType(user.getCompanyType());
				}
				if (user.getContactName() != null && !user.getContactName().equals("")) {
					userInDB.setContactName(user.getContactName());
				}
				if (user.getName() != null && !user.getName().equals("")) {
					userInDB.setName(user.getName());
				}
				if (user.getIdNumber() != null && !user.getIdNumber().equals("")) {
					userInDB.setIdNumber(user.getIdNumber());
				}
				if (user.getMajor() != null && !user.getMajor().equals("")) {
					userInDB.setMajor(user.getMajor());
				}
				if (user.getCollege() != null && !user.getCollege().equals("")) {
					userInDB.setCollege(user.getCollege());
				}
				if (user.getTeacherTitle() != null && !user.getTeacherTitle().equals("")) {
					userInDB.setTeacherTitle(user.getTeacherTitle());
				}
				if (user.getWorkUnit() != null && !user.getWorkUnit().equals("")) {
					userInDB.setWorkUnit(user.getWorkUnit());
				}
				if (user.getOfficeSector() != null && !user.getOfficeSector().equals("")) {
					userInDB.setOfficeSector(user.getOfficeSector());
				}
				
				if (!userDao.updateUser(userInDB)) {
					dataWrapper.setErrorCode(ErrorCodeEnum.Error);
				}
			} else {
				dataWrapper.setErrorCode(ErrorCodeEnum.Error);
			}
		} else {
			dataWrapper.setErrorCode(ErrorCodeEnum.Error);
		}
		return dataWrapper;
	}

	@Override
	public DataWrapper<Void> deleteUser(Long userId, String token) {
		// TODO Auto-generated method stub
		DataWrapper<Void> dataWrapper = new DataWrapper<Void>();
		User user = SessionManager.getSession(token);
		if (user != null && user.getId() < 0) {
			if (!userDao.deleteUser(userId)) {
				dataWrapper.setErrorCode(ErrorCodeEnum.Error);
			}
		} else {
			dataWrapper.setErrorCode(ErrorCodeEnum.Error);
		}
		return dataWrapper;
	}

	@Override
	public DataWrapper<User> schoolLogin(User user) {
		// TODO Auto-generated method stub
		DataWrapper<User> dataWrapper = new DataWrapper<User>();
		if (user.getLoginName() != null && user.getIdentity() != null) {
			User userInDB = userDao.getByUserName(user.getLoginName());
		
			if (userInDB == null) {
				userInDB = new User();
				userInDB.setId(null);
				userInDB.setLoginName(user.getLoginName());
				userInDB.setIdentity(user.getIdentity());
				userDao.addUser(userInDB);
				
			}
			
			if (userInDB != null && userInDB.getId() != null) {
				SessionManager.removeSessionByUserId(userInDB.getId());
				String token = SessionManager.newSession(userInDB);
				dataWrapper.setToken(token);
				userInDB.setPassword(null);
				dataWrapper.setData(userInDB);
			} else {
				dataWrapper.setErrorCode(ErrorCodeEnum.Error);
			}
			
			
		} else {
			dataWrapper.setErrorCode(ErrorCodeEnum.Error);
		}
		return dataWrapper;
	}


}
