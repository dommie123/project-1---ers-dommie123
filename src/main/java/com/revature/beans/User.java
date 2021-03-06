package com.revature.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.revature.beans.Reimbursement;

public class User implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8245591262053866756L;
	private int id;
	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	private String email;
	private List<Reimbursement> reimbursements;
	
	// All-Args Constructor
	public User(int id, String firstName, String lastName, String userName, String password, String email) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.reimbursements = new ArrayList<>();
	}
	
	// No-Args Constructor
	public User() {
		super();
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
}
