package com.revature.controllers;

import javax.servlet.http.HttpServletRequest;

import com.revature.utils.SessionCache;

public class HomeController {

	public static String home(HttpServletRequest req) {
		System.out.println(req.getSession().getAttribute("currentuser"));
		if (SessionCache.getCurrentUser().get() == null || req.getSession().getAttribute("currentuser") == null)
			return "index.html";
		return "home.html";

	}

}
