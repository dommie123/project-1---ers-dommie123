package com.revature.controllers;

import javax.servlet.http.HttpServletRequest;

import com.revature.utils.SessionCache;

public class LogoutController {
	public static String logout(HttpServletRequest req) {
		if (SessionCache.getCurrentUser().get() == null)
			return "index.html";
		
		req.getSession().invalidate();
		SessionCache.setCurrentUser(null);
		return "logout.html";
	}
}
