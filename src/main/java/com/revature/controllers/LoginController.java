package com.revature.controllers;

import javax.servlet.http.HttpServletRequest;

import com.revature.services.UserService;
import com.revature.utils.SessionCache;

public class LoginController {
	
	private static UserService uServ;

	public static String login(HttpServletRequest req) {
		if (!req.getMethod().equals("POST")) {
			return "resources/html/index.html";
		}
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		if (!uServ.login(username, password)) {
			return "badcreds.ers";
		}	
		else {
			req.getSession().setAttribute("currentuser", SessionCache.getCurrentUser().get());
			return "home.ers";
		}
	}
	
}
