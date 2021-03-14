package com.revature.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.exceptions.InvalidCredentialsException;
import com.revature.services.UserService;
import com.revature.utils.DaoUtil;
import com.revature.utils.SessionCache;

public class LoginController {
	
	private static UserService uServ = new UserService();

	public static String login(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
		if (!req.getMethod().equals("POST")) {
			return "index.html";
		}
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		try {
			if (!uServ.login(username, password)) {
				return "badcreds.ers";
			}	
			else {
				res.setContentType("application/json");
				res.getWriter().write(new ObjectMapper().writeValueAsString(DaoUtil.getUserDao().getUserByCredentials(username, password)));
				req.getSession().setAttribute("currentuser", SessionCache.getCurrentUser().get());
				return "home.ers";
			}
		} catch (InvalidCredentialsException e) {
			System.out.println("Caught InvalidCredentialsException");
			return "badcreds.ers";
		}
	}
	
}
