package com.revature.controllers;

import javax.servlet.http.HttpServletRequest;

import com.revature.exceptions.InvalidCredentialsException;
import com.revature.services.UserService;
import com.revature.utils.SessionCache;

public class LoginController {
	
	private static UserService uServ = new UserService();

	public static String login(HttpServletRequest req) {
		if (!req.getMethod().equals("POST")) {
			return "index.html";
		}
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		try {
			if (!uServ.login(username, password)) {
				return "/resources/html/badcreds.ers";
			}	
			else {
				req.getSession().setAttribute("currentuser", SessionCache.getCurrentUser().get());
				return "/resources/html/home.ers";
			}
		} catch (InvalidCredentialsException e) {
			System.out.println("Caught InvalidCredentialsException");
			return "/resources/html/badcreds.ers";
		}
	}
	
}
