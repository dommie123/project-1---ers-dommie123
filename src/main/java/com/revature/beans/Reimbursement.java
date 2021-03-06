package com.revature.beans;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Reimbursement implements Serializable {

	public static enum ReimbursementType {
		LODGING,
		TRAVEL,
		FOOD,
		OTHER
	}
	
	public static enum ReimbursementStatus {
		PENDING, 
		APPROVED,
		DENIED
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5336707154281634521L;
	private int id;								// primary key
	private double amount;
	private LocalDateTime submitted;			// The time a user submitted a reimbursement
	private LocalDateTime resolved;				// The time a manager resolved this reimbursement
	private String description;
	private byte[] receipt;
	private User author;
	private User resolver;
	private ReimbursementStatus status;			// The status of the reimbursement ticket
	private ReimbursementType type;				// The type of reimbursement
	
	// All-Args Constructor
	public Reimbursement(int id, double amount, LocalDateTime submitted, LocalDateTime resolved, String description,
			byte[] receipt, User author, User resolver, ReimbursementStatus status, ReimbursementType type) {
		super();
		this.id = id;
		this.amount = amount;
		this.submitted = submitted;
		this.resolved = resolved;
		this.description = description;
		this.receipt = receipt;
		this.author = author;
		this.resolver = resolver;
		this.status = status;
		this.type = type;
	}
	
	// Constructor used when user submits a ticket and adds a description.
	public Reimbursement(int id, double amount, LocalDateTime submitted, String description, byte[] receipt,
			User author, ReimbursementStatus status, ReimbursementType type) {
		super();
		this.id = id;
		this.amount = amount;
		this.submitted = submitted;
		this.description = description;
		this.receipt = receipt;
		this.author = author;
		this.status = status;
		this.type = type;
	}
	
	// Constructor used when user submits a ticket without adding a description
	public Reimbursement(int id, double amount, LocalDateTime submitted, byte[] receipt, User author,
			ReimbursementStatus status, ReimbursementType type) {
		super();
		this.id = id;
		this.amount = amount;
		this.submitted = submitted;
		this.receipt = receipt;
		this.author = author;
		this.status = status;
		this.type = type;
	}
	
	// No-args Constructor
	public Reimbursement() {
		super();
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public double getAmount() {
		return amount;
	}
	
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public LocalDateTime getSubmitted() {
		return submitted;
	}
	
	public void setSubmitted(LocalDateTime submitted) {
		this.submitted = submitted;
	}
	
	public LocalDateTime getResolved() {
		return resolved;
	}
	
	public void setResolved(LocalDateTime resolved) {
		this.resolved = resolved;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public byte[] getReceipt() {
		return receipt;
	}
	
	public void setReceipt(byte[] receipt) {
		this.receipt = receipt;
	}
	
	public User getAuthor() {
		return author;
	}
	
	public void setAuthor(User author) {
		this.author = author;
	}
	
	public User getResolver() {
		return resolver;
	}
	
	public void setResolver(User resolver) {
		this.resolver = resolver;
	}
	
	public ReimbursementStatus getStatus() {
		return status;
	}
	
	public void setStatus(ReimbursementStatus status) {
		this.status = status;
	}
	
	public ReimbursementType getType() {
		return type;
	}
	
	public void setType(ReimbursementType type) {
		this.type = type;
	}
	
	
}
