package com.revature.controllers;

import javax.servlet.http.HttpServletRequest;

import com.revature.beans.User;
import com.revature.services.UserService;

public class RegisterController {
	
	private static UserService uServ;
	
	public static String register(HttpServletRequest req) {
		if (!req.getMethod().equals("POST"))
			return "resources/html/index.html";
		
		User newUser = new User();
		newUser.setFirstName(req.getParameter("firstname"));
		newUser.setLastName(req.getParameter("lastname"));
		newUser.setEmail(req.getParameter("email"));
		newUser.setUserName(req.getParameter("username"));
		
		if (req.getParameter("password").equals(req.getParameter("confirmpassword")))
			newUser.setPassword(req.getParameter("firstname"));
		else {
			System.out.println("Passwords do not match! Redirectimg to registration");
			return "resources/html/register.html";
		}
		
		if (uServ.register(newUser)) {
			req.getSession().setAttribute("currentuser", newUser);
			return "home.ers";
		}
		else {
			System.out.println("Failed to register user! Redirecting to login...");
			return "resources/html/index.html";
		}
		
	}
	
}
