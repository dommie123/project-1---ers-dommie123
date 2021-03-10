package com.revature.servlets;

import javax.servlet.http.HttpServletRequest;

import com.revature.controllers.HomeController;
import com.revature.controllers.LoginController;
import com.revature.controllers.RegisterController;

public class UserRequestHelper {
	public static String process(HttpServletRequest req) {
		System.out.println(req.getRequestURI());
		switch (req.getRequestURI()) {
		case "login.ers": 
			System.out.println("In login.change rhelper");
			return LoginController.login(req);
		case "home.ers":
			System.out.println("In home.change rhelper");
			return HomeController.home(req);
		case "register.ers":
			System.out.println("In register.ers rhelper");
			return RegisterController.register(req);
		default: 
			System.out.println("In default");
			return "error.html";
		}
	}
}
