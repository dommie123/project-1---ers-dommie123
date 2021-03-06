package com.revature.dao;

import java.util.List;

import com.revature.beans.Reimbursement;
import com.revature.beans.User;

public interface ReimbursementDao {
	
	public void addReimbursement(Reimbursement reimb);
	public Reimbursement getReimbursementById(int reimbid);
	public List<Reimbursement> getReimbursements();
	public List<Reimbursement> getReimbursementsByAuthor(User u);
	public void updateReimbursement(Reimbursement reimb);
	public boolean deleteReimbursement(Reimbursement reimb);
	
}
