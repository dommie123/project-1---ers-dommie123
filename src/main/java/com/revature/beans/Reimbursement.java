package com.revature.beans;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

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
	
	public void setSubmitted() {
		this.setSubmitted(LocalDateTime.now());
	}
	
	public void setSubmitted(LocalDateTime submitted) {
		this.submitted = submitted.truncatedTo(ChronoUnit.SECONDS);
	}
	
	public LocalDateTime getResolved() {
		return resolved;
	}
	
	public void setResolved() {
		this.setResolved(LocalDateTime.now());
	}
	
	public void setResolved(LocalDateTime resolved) {
		this.resolved = resolved.truncatedTo(ChronoUnit.SECONDS);
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + id;
		result = prime * result + Arrays.hashCode(receipt);
		result = prime * result + ((resolved == null) ? 0 : resolved.hashCode());
		result = prime * result + ((resolver == null) ? 0 : resolver.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((submitted == null) ? 0 : submitted.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reimbursement other = (Reimbursement) obj;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (!Arrays.equals(receipt, other.receipt))
			return false;
		if (resolved == null) {
			if (other.resolved != null)
				return false;
		} else if (!resolved.equals(other.resolved))
			return false;
		if (resolver == null) {
			if (other.resolver != null)
				return false;
		} else if (!resolver.equals(other.resolver))
			return false;
		if (status != other.status)
			return false;
		if (submitted == null) {
			if (other.submitted != null)
				return false;
		} else if (!submitted.equals(other.submitted))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Reimbursement [id=" + id + ", amount=" + amount + ", submitted=" + submitted + ", resolved=" + resolved
				+ ", description=" + description + ", receipt=" + Arrays.toString(receipt) + ", author=" + author
				+ ", resolver=" + resolver + ", status=" + status + ", type=" + type + "]";
	}
}
