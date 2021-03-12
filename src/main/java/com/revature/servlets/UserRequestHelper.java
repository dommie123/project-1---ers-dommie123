package com.revature.servlets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.controllers.HomeController;
import com.revature.controllers.LoginController;
import com.revature.controllers.RegisterController;
import com.revature.controllers.RegisteredController;

public class UserRequestHelper {
	public static String process(HttpServletRequest req, HttpServletResponse res) {
		System.out.println(req.getRequestURI());
		switch (req.getRequestURI()) {
		case "/project1/resources/html/login.ers": 
			System.out.println("In login.change rhelper");
			return LoginController.login(req);
		case "/project1/resources/html/home.ers":
			System.out.println("In home.change rhelper");
			return HomeController.home(req);
		case "/project1/resources/html/register.ers":
			System.out.println("In register.ers rhelper");
			return RegisterController.register(req, res);
		case "/project1/resources/html/registered.ers":
			return RegisteredController.printRegistered(res);
		default: 
			System.out.println("In default");
			return "error.html";
		}
	}
}
