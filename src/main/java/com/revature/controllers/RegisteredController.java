package com.revature.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.beans.User;
import com.revature.dao.UserDao;
import com.revature.utils.DaoUtil;
import com.revature.utils.SessionCache;

public class RegisteredController {
	
	private static UserDao uDao = DaoUtil.getUserDao();

	public static String printRegistered(HttpServletRequest req, HttpServletResponse res) {
		if (SessionCache.getCurrentUser().get() == null) return "index.html";
		res.setContentType("text/html");
		return "registered.html";
	}
	
	public static void printUser(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
		if (SessionCache.getCurrentUser().get() == null) return;
		res.setContentType("text/json");
		User u = uDao.getUserByCredentials(SessionCache.getCurrentUser().get().getUserName(), SessionCache.getCurrentUser().get().getPassword());
		if (u != null)
			res.getWriter().write(new ObjectMapper().writeValueAsString(u));
		else {
			User empty = new User();
			empty.setId(-1);
			res.getWriter().write(new ObjectMapper().writeValueAsString(empty));
		}
	}
	
}
