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
		if (!req.getMethod().equals("POST")) {
			if (req.getRequestURI().equals("/project1/resources/html/logout.ers")) {
				return LogoutController.logout(req);
			}
			else 
				return "index.html";
		}
		
		switch (req.getRequestURI()) {
		case "/project1/resources/html/login.ers": 
			return LoginController.login(req, res);
		case "/project1/resources/html/home.ers":
			return HomeController.home(req);
		case "/project1/resources/html/register.ers":
			return RegisterController.register(req, res);
		case "/project1/resources/html/registered.ers":
			return RegisteredController.printRegistered(req, res);
		case "/project1/resources/html/add-reimb.ers":
			return ReimbursementsController.addReimb(req, res);
		case "/project1/resources/html/logout.ers":
			return LogoutController.logout(req);
		case "/project1/resources/html/get-reimb.ers":
			return GetReimbursementController.getReimbursement(req, res);
		case "/project1/resources/html/updateReimb.ers":
			return ReimbursementsController.resolveReimb(req, res);
		default: 
			return "error.html";
		}
	}
}
