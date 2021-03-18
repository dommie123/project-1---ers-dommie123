package com.revature.controllers;

import javax.servlet.http.HttpServletRequest;

import com.revature.utils.SessionCache;

public class LogoutController {
	public static String logout(HttpServletRequest req) {
		req.getSession().invalidate();
		SessionCache.setCurrentUser(null);
		return "logout.html";
	}
}
