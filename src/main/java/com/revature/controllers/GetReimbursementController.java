package com.revature.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.revature.beans.Reimbursement;
import com.revature.beans.User;
import com.revature.beans.User.UserRole;
import com.revature.dao.ReimbursementDao;
import com.revature.utils.DaoUtil;
import com.revature.utils.SessionCache;

public class GetReimbursementController {
	
	private static ReimbursementDao reimbDao = DaoUtil.getReimbDao();

	public static String getReimbursement(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
		User current = SessionCache.getCurrentUser().get();
		if (current == null || req.getSession().getAttribute("currentuser") == null) return "index.html";
		System.out.println(req.getSession().getAttribute("currentreimb"));
		if (current.getRole().equals(UserRole.MANAGER)) {
			System.out.println(req.getParameter("reimbid"));
			Reimbursement reimb = reimbDao.getReimbursementById(Integer.parseInt(req.getParameter("reimbid")));
			req.getSession().setAttribute("currentreimb", reimb);
			System.out.println(req.getSession().getAttribute("currentreimb"));
		}
		return "updateReimb.html";
	}
	
}
