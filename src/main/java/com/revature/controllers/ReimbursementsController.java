package com.revature.controllers;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.beans.Reimbursement;
import com.revature.beans.Reimbursement.ReimbursementType;
import com.revature.beans.User;
import com.revature.beans.User.UserRole;
import com.revature.dao.ReimbursementDao;
import com.revature.services.ReimbService;
import com.revature.utils.DaoUtil;
import com.revature.utils.SessionCache;

public class ReimbursementsController {
	
	private static ReimbursementDao reimbDao = DaoUtil.getReimbDao();
	private static ReimbService rServ = new ReimbService();
	private static List<Reimbursement> reimbList;

	public static void viewReimbursements(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
		if (SessionCache.getCurrentUser().get() == null) return;
		User current = SessionCache.getCurrentUser().get();
		if (current.getRole().equals(UserRole.EMPLOYEE)) {
			reimbList = reimbDao.getReimbursementsByAuthor(current);
			if (reimbList != null)
				res.getWriter().write(new ObjectMapper().writeValueAsString(reimbList));
		}
		else if (current.getRole().equals(UserRole.MANAGER)) {
			reimbList = reimbDao.getReimbursements();
			if (reimbList != null)
				res.getWriter().write(new ObjectMapper().writeValueAsString(reimbList));
		}
	}
	
	public static void getReimbursement(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
		if (SessionCache.getCurrentUser().get() == null) return;
		User current = SessionCache.getCurrentUser().get();
		if (current.getRole().equals(UserRole.MANAGER)) {
			System.out.println(req.getSession().getAttribute("currentreimb"));
			Reimbursement reimb = (Reimbursement) req.getSession().getAttribute("currentreimb");
			if (reimb != null) 
				res.getWriter().write(new ObjectMapper().writeValueAsString(reimb));
		}
	}

	public static String addReimb(HttpServletRequest req, HttpServletResponse res) {
		if (SessionCache.getCurrentUser().get() == null) return "index.html";
		Reimbursement reimb = new Reimbursement();

		if (req.getParameter("type").equals("LODGING"))
			reimb.setType(ReimbursementType.LODGING);
		else if (req.getParameter("type").equals("TRAVEL"))
			reimb.setType(ReimbursementType.TRAVEL);
		else if (req.getParameter("type").equals("FOOD"))
			reimb.setType(ReimbursementType.FOOD);
		else if (req.getParameter("type").equals("OTHER"))
			reimb.setType(ReimbursementType.OTHER);
		
		reimb.setAmount(Double.parseDouble(req.getParameter("amount")));
		reimb.setDescription(req.getParameter("description"));
		
		rServ.submitTicket(reimb);
		System.out.println("Success!");
		return "home.ers";
	}
	
	public static String resolveReimb(HttpServletRequest req, HttpServletResponse res) throws JsonParseException, JsonMappingException, IOException {
		
		// If the current user is not a financial manager, redirect them to the error page.
		User current = SessionCache.getCurrentUser().get();
		if (!current.getRole().equals(UserRole.MANAGER)) {
			return "unauthorized.ers";
		}
		String jsonString = req.getReader().lines().collect(Collectors.joining());
		String[] kvp = jsonString.split("=");
		String value = kvp[1];
		System.out.println(jsonString);
		Reimbursement reimb = (Reimbursement) req.getSession().getAttribute("currentreimb");
		if (reimb != null) {
			rServ.resolveTicket(reimb, value.equals("APPROVED"));
			System.out.println("Success");
		}
		else {
			System.out.println("Failure! The reimbursement was null!");
		}
		
		return "home.ers";
	}
	
}
