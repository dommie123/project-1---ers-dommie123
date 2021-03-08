package com.revature;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.InputStream;
import java.time.LocalDateTime;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.beans.Reimbursement;
import com.revature.beans.Reimbursement.ReimbursementStatus;
import com.revature.beans.Reimbursement.ReimbursementType;
import com.revature.beans.User;
import com.revature.beans.User.UserRole;
import com.revature.dao.ReimbDaoDB;
import com.revature.dao.ReimbursementDao;
import com.revature.exceptions.UnauthorizedException;
import com.revature.utils.SessionCache;

public class ReimbServiceTest {

	private static ReimbursementDao reimbDao;
	private Reimbursement reimb;
	private Reimbursement reimb2;
	private static User u;
	private static User m;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		reimbDao = new ReimbDaoDB();
		u = new User(1, "testuser", "password", "testFirst", "testLast", "test@example.com", UserRole.EMPLOYEE);
		m = new User(2, "testman", "pass", "manFirst", "manLast", "manager@example.com", UserRole.MANAGER);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		SessionCache.setCurrentUser(null);
	}

	@Before
	public void setUp() throws Exception {
		byte[] receipt = {(byte) 2, (byte) 3, (byte) 23, (byte) 7, (byte) 1, (byte) 8, (byte) 8, (byte) 8, (byte) 6, (byte) 4, (byte) 3, (byte) 2, (byte) 0, (byte) 100, (byte) 255, (byte) 77, (byte) 10, (byte) 77, (byte) 2};
		reimb = new Reimbursement();
		reimb.setAmount(300.50);
		reimb.setSubmitted();
		reimb.setResolved(LocalDateTime.now().plusDays(2L));
		reimb.setDescription("This is the description of the first reimbursement that is typically provided by the author. It has a 250 character limit.");
		reimb.setReceipt(receipt);
		reimb.setAuthor(u);
		reimb.setResolver(m);
		reimb.setStatus(ReimbursementStatus.APPROVED);
		reimb.setType(ReimbursementType.FOOD);
		reimbDao.addReimbursement(reimb);
		byte[] receipt2 = {(byte) 2, (byte) 3, (byte) 23, (byte) 7, (byte) 1, (byte) 8, (byte) 8, (byte) 8, (byte) 6, (byte) 4, (byte) 3, (byte) 2, (byte) 0, (byte) 100, (byte) 255, (byte) 77, (byte) 10, (byte) 77, (byte) 2};
		reimb2 = new Reimbursement();
		reimb2.setAmount(1020.70);
		reimb2.setSubmitted();
		reimb2.setResolved(LocalDateTime.now().plusDays(3L));
		reimb2.setDescription("This is the description of the second reimbursement that is typically provided by the author. It has a 250 character limit.");
		reimb2.setReceipt(receipt2);
		reimb2.setAuthor(u);
		reimb2.setResolver(m);
		reimb2.setStatus(ReimbursementStatus.DENIED);
		reimb2.setType(ReimbursementType.LODGING);
		reimbDao.addReimbursement(reimb2);
	}

	@After
	public void tearDown() throws Exception {
		reimbDao.deleteReimbursement(reimb);
		reimbDao.deleteReimbursement(reimb2);
	}

	@Test
	public void testAddAndGetReimbursement() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testGetReimbursementsByAuthor() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testGetReimbursementById() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testGetReimbursements() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testUpdateReimbursement() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testDeleteReimbursement() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testSubmitReimbursement() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testResolveReimbursement() {
		fail("Not yet implemented");
	}
	
	@Test(expected=UnauthorizedException.class)
	public void testInvalidResolutionAttempt() {
		fail("Not yet implemented");
	}
}
