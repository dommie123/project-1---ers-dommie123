package com.revature.servlets;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.revature.controllers.GetReimbursementController;
import com.revature.controllers.HomeController;
import com.revature.controllers.LoginController;
import com.revature.controllers.LogoutController;
import com.revature.controllers.RegisterController;
import com.revature.controllers.RegisteredController;
import com.revature.controllers.ReimbursementsController;

public class UserRequestHelper {
	public static String process(HttpServletRequest req, HttpServletResponse res) throws IOException {
		System.out.println(req.getRequestURI());
		System.out.println(req.getMethod());
		if (!req.getMethod().equals("POST")) {
			if (req.getRequestURI().equals("/project1/resources/html/logout.ers")) {
				System.out.println("In logout.ers rhelper");
				return LogoutController.logout(req);
			}
			else 
				return "index.html";
		}
		
		switch (req.getRequestURI()) {
		case "/project1/resources/html/login.ers": 
			System.out.println("In login.change rhelper");
			return LoginController.login(req, res);
		case "/project1/resources/html/home.ers":
			System.out.println("In home.change rhelper");
			return HomeController.home(req);
		case "/project1/resources/html/register.ers":
			System.out.println("In register.ers rhelper");
			return RegisterController.register(req, res);
		case "/project1/resources/html/registered.ers":
			System.out.println("In registere[d].ers");
			return RegisteredController.printRegistered(req, res);
		case "/project1/resources/html/add-reimb.ers":
			System.out.println("In add-reimb.ers");
			return ReimbursementsController.addReimb(req, res);
		case "/project1/resources/html/logout.ers":
			System.out.println("In logout.ers rhelper");
			return LogoutController.logout(req);
		case "/project1/resources/html/get-reimb.ers":
			System.out.println("In get-reimb.ers rhelper");
			return GetReimbursementController.getReimbursement(req, res);
		case "/project1/resources/html/updateReimb.ers":
			System.out.println("In updateReimb.ers rhelper");
			return ReimbursementsController.resolveReimb(req, res);
		default: 
			System.out.println("In default");
			return "error.html";
		}
	}
}
