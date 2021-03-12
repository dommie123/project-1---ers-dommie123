package com.revature.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.revature.beans.User;
import com.revature.beans.User.UserRole;
import com.revature.services.UserService;
import com.revature.utils.DaoUtil;

public class RegisterController {
	
	private static UserService uServ = new UserService();
	
	public static String register(HttpServletRequest req) {
		if (!req.getMethod().equals("POST"))
			return "index.html";
		
		List<User> users = DaoUtil.getUserDao().getAllUsers();
		User newUser = new User();
		
		if (users != null)
			newUser.setId(DaoUtil.getUserDao().getAllUsers().size() + 1);
		else
			newUser.setId(1);
		
		newUser.setFirstName(req.getParameter("firstname"));
		newUser.setLastName(req.getParameter("lastname"));
		newUser.setEmail(req.getParameter("email"));
		newUser.setUserName(req.getParameter("username"));
		
		if (req.getParameter("password").equals(req.getParameter("confirmpass")))
			newUser.setPassword(req.getParameter("password"));
		else {
			System.out.println("Passwords do not match! Redirectimg to registration");
			return "register.html";
		}
		
		if (req.getParameter("role").equalsIgnoreCase("employee"))
			newUser.setRole(UserRole.EMPLOYEE);
		else if (req.getParameter("role").equalsIgnoreCase("manager"))
			newUser.setRole(UserRole.MANAGER);
		
		if (uServ.register(newUser)) {
			req.getSession().setAttribute("currentuser", newUser);
			return "home.ers";
		}
		else {
			System.out.println("Failed to register user! Redirecting to login...");
			return "index.html";
		}
	}
}
