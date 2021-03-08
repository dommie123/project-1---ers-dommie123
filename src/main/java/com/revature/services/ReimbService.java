package com.revature.services;

import com.revature.beans.Reimbursement;
import com.revature.dao.ReimbDaoDB;
import com.revature.dao.ReimbursementDao;
import com.revature.exceptions.UnauthorizedException;
import com.revature.utils.SessionCache;

import com.revature.beans.User;
import com.revature.beans.Reimbursement.ReimbursementStatus;
import com.revature.beans.User.UserRole;

public class ReimbService {
	
	private ReimbursementDao rDao;
	
	public ReimbService() {
		rDao = new ReimbDaoDB();
	}
	
	public void submitTicket(Reimbursement reimb) {
		User current = SessionCache.getCurrentUser().get();
		if (current.getId() == 0) return;
		reimb.setAuthor(current);
		reimb.setStatus(ReimbursementStatus.PENDING);
		reimb.setSubmitted();
		rDao.addReimbursement(reimb);
		
	}
	
	public void resolveTicket(Reimbursement reimb, boolean approved) throws UnauthorizedException{
		User current = SessionCache.getCurrentUser().get();
		if (current.getId() == 0) return;
		else if (current.getRole().equals(UserRole.EMPLOYEE))
			throw new UnauthorizedException();
		
		if (approved)
			reimb.setStatus(ReimbursementStatus.APPROVED);
		else
			reimb.setStatus(ReimbursementStatus.DENIED);
		
		reimb.setResolver(current);
		reimb.setResolved();
		rDao.updateReimbursement(reimb);
	}
	
}
