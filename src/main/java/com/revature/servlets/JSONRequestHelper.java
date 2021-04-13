package com.revature.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.controllers.GetReimbursementController;
import com.revature.controllers.RegisteredController;
import com.revature.controllers.ReimbursementsController;

public class JSONRequestHelper {

	public static void process(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		if (!req.getMethod().equals("POST")) {
			req.getRequestDispatcher("index.html").forward(req, res);
		}

		switch (req.getRequestURI()) {
		case "/project1/resources/html/login.json":
			RegisteredController.printUser(req, res);
			break;
		case "/project1/resources/html/reimb.json":
			ReimbursementsController.viewReimbursements(req, res);
			break;
		case "/project1/resources/html/update.json":
			ReimbursementsController.resolveReimb(req, res);
			break;
		case "/project1/resources/html/getReimb.json":
			ReimbursementsController.getReimbursement(req, res);
			break;
		default:
			req.getRequestDispatcher("error.html").forward(req, res);
		}
	}

}
