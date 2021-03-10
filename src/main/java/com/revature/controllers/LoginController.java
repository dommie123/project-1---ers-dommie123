package com.revature.controllers;

import javax.servlet.http.HttpServletRequest;

import com.revature.dao.UserDao;
import com.revature.utils.SessionCache;

public class LoginController {

	public static String login(HttpServletRequest req) {
		if (!req.getMethod().equals("POST")) {
			return "resources/html/index.html";
		}
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		if (!(username.equals(SessionCache.getCurrentUser().get().getUserName()) && password.equals(SessionCache.getCurrentUser().get().getPassword()))) {
			return "wrongcreds.ers";
		}
		else {
			req.getSession().setAttribute("loggedusername", username);
			req.getSession().setAttribute("loggedpass", password);
			return "home.ers";
		}
	}
	
}
