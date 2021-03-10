package com.revature.servlets;

import javax.servlet.http.HttpServletRequest;

import com.revature.controllers.HomeController;
import com.revature.controllers.LoginController;

public class UserRequestHelper {
	public static String process(HttpServletRequest req) {
		System.out.println(req.getRequestURI());
		switch (req.getRequestURI()) {
		case "/project1/login.ers": 
			System.out.println("In login.change rhelper");
			return LoginController.login(req);
		case "/project1/home.ers":
			System.out.println("In home.change rhelper");
			return HomeController.home(req);
		default: 
			System.out.println("In default");
			return "resources/html/unsuccessfullogin.html";
		}
	}
}
