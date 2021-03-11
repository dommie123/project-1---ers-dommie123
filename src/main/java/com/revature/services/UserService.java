package com.revature.services;

import com.revature.beans.User;
import com.revature.dao.UserDao;
import com.revature.dao.UserDaoDB;
import com.revature.exceptions.InvalidCredentialsException;
import com.revature.exceptions.UsernameAlreadyExistsException;
import com.revature.utils.*;

public class UserService {
	
	private UserDao uDao;
	
	public UserService() {
		uDao = DaoUtil.getUserDao();
	}
	
	public boolean login(String username, String password) throws InvalidCredentialsException {
		User current = uDao.getUserByCredentials(username, password);
		if (current == null || current.getId() == 0) {
			throw new InvalidCredentialsException();
		}
		else {
			SessionCache.setCurrentUser(uDao.getUserByCredentials(username, password));
			return true;
		}
		
	}
	
	public boolean register(User u) throws UsernameAlreadyExistsException {
		if (uDao.getUserByCredentials(u.getUserName(), u.getPassword()).getUserName() != null && u != null)
			throw new UsernameAlreadyExistsException();
		else {
			uDao.addUser(u);
			return true;
		}
	}
	
}
