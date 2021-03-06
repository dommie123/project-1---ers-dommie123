package com.revature.dao;

import java.util.List;

import com.revature.beans.User;

public interface UserDao {
	
	public void addUser(User u);
	public List<User> getAllUsers();
	public User getUserById(int userid);
	public User getUserByCredentials(String username, String password);
	public void updateUser(User u);
	public boolean deleteUser(User u);
	
}
