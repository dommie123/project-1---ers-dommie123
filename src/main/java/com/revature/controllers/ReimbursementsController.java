package com.revature.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.beans.Reimbursement;
import com.revature.beans.User;
import com.revature.beans.User.UserRole;
import com.revature.dao.ReimbursementDao;
import com.revature.utils.DaoUtil;
import com.revature.utils.SessionCache;

public class ReimbursementsController {
	
	private static ReimbursementDao reimbDao = DaoUtil.getReimbDao();
	private static List<Reimbursement> reimbList;

	public static void viewReimbursements(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
		User current = SessionCache.getCurrentUser().get();
		if (current.getRole().equals(UserRole.EMPLOYEE)) {
			reimbList = reimbDao.getReimbursementsByAuthor(current);
			res.getWriter().write(new ObjectMapper().writeValueAsString(current));
		}
	}
	
}
