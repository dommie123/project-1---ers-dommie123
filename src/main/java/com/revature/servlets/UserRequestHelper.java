package com.revature.servlets;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.apache.log4j.Level;
//import org.apache.log4j.Logger;
//import org.apache.log4j.Priority;

//import com.fasterxml.jackson.core.JsonProcessingException;
import com.revature.controllers.GetReimbursementController;
import com.revature.controllers.HomeController;
import com.revature.controllers.LoginController;
import com.revature.controllers.LogoutController;
import com.revature.controllers.RegisterController;
import com.revature.controllers.RegisteredController;
import com.revature.controllers.ReimbursementsController;

public class UserRequestHelper {
	
//	private static final Logger logger = Logger.getLogger(UserRequestHelper.class);
	
	public static String process(HttpServletRequest req, HttpServletResponse res) throws IOException {
//		logger.log(Level.INFO, req.getRequestURI());
//		logger.log(Level.INFO, req.getMethod());
		if (!req.getMethod().equals("POST")) {
			if (req.getRequestURI().equals("/project1/resources/html/logout.ers")) {
//				logger.log(Level.INFO, "In logout.ers rhelper");
				return LogoutController.logout(req);
			}
			else 
				return "index.html";
		}
		
		switch (req.getRequestURI()) {
		case "/project1/resources/html/login.ers": 
//			logger.log(Level.INFO, "In login.change rhelper");
			return LoginController.login(req, res);
		case "/project1/resources/html/home.ers":
//			logger.log(Level.INFO, "In home.change rhelper");
			return HomeController.home(req);
		case "/project1/resources/html/register.ers":
//			logger.log(Level.INFO, "In register.ers rhelper");
			return RegisterController.register(req, res);
		case "/project1/resources/html/registered.ers":
//			logger.log(Level.INFO, "In registere[d].ers");
			return RegisteredController.printRegistered(req, res);
		case "/project1/resources/html/add-reimb.ers":
//			logger.log(Level.INFO, "In add-reimb.ers");
			return ReimbursementsController.addReimb(req, res);
		case "/project1/resources/html/logout.ers":
//			logger.log(Level.INFO, "In logout.ers rhelper");
			return LogoutController.logout(req);
		case "/project1/resources/html/get-reimb.ers":
//			logger.log(Level.INFO, "In get-reimb.ers rhelper");
			return GetReimbursementController.getReimbursement(req, res);
		case "/project1/resources/html/updateReimb.ers":
//			logger.log(Level.INFO, "In updateReimb.ers rhelper");
			return ReimbursementsController.resolveReimb(req, res);
		default: 
//			logger.log(Level.INFO, "In default");
			return "error.html";
		}
	}
}
