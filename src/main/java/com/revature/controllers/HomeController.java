package com.revature.controllers;

import javax.servlet.http.HttpServletRequest;

import com.revature.utils.SessionCache;

public class HomeController {

	public static String home(HttpServletRequest req) {
		if (req.getSession().getAttribute("currentuser") == null || SessionCache.getCurrentUser().get() == null) return "index.html";
		return "home.html";
	}

}
