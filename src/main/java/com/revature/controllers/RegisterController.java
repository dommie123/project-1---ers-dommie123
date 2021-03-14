package com.revature.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.beans.User;
import com.revature.beans.User.UserRole;
import com.revature.exceptions.UsernameAlreadyExistsException;
import com.revature.services.UserService;
import com.revature.utils.DaoUtil;

public class RegisterController {
	
	private static UserService uServ = new UserService();
	private static final Logger logger = Logger.getLogger(RegisterController.class);
	
	public static String register(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException{
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
		
		try {
			if (uServ.register(newUser)) {
				res.setContentType("application/json");
				PrintWriter out = res.getWriter();
				out.write(new ObjectMapper().writeValueAsString(newUser));
				req.getSession().setAttribute("currentuser", newUser);
				req.getSession().setAttribute("nameofuser", newUser.getFirstName());
				return "registered.ers";
			}
			else {
				System.out.println("Failed to register user! Redirecting to login...");
				return "index.html";
			}
		} catch (UsernameAlreadyExistsException e) {
			return "error.html";		
		} catch (IOException e) {
			return "error.html";
		}
	}
}
