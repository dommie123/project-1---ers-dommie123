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
import com.revature.dao.UserDao;
import com.revature.dao.UserDaoDB;
import com.revature.exceptions.UnauthorizedException;
import com.revature.services.ReimbService;
import com.revature.utils.SessionCache;
/**
 * This is the test case file for testing methods that deal with reimbursement
 * tickets. Keep in mind these tests will not run properly unless each test is 
 * ran individually one after the other.
 * @author dman9
 *
 */
public class ReimbServiceTest {

	private Reimbursement reimb;
	private Reimbursement reimb2;
	private Reimbursement reimb6;
	private static UserDao userDao;
	private static ReimbursementDao reimbDao;
	private static ReimbService rServ;
	private static User u1;
	private static User u2;
	private static User m;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		userDao = new UserDaoDB();
		reimbDao = new ReimbDaoDB();
		rServ = new ReimbService(reimbDao);
		u1 = new User(1, "testuser", "password", "testFirst", "testLast", "test@example.com", UserRole.EMPLOYEE);
		u2 = new User(1, "testuser2", "password", "testFirst2", "testLast2", "test2@example.com", UserRole.EMPLOYEE);
		m = new User(2, "testman", "pass", "manFirst", "manLast", "manager@example.com", UserRole.MANAGER);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		SessionCache.setCurrentUser(null);
	}

	@Before
	public void setUp() throws Exception {
		userDao.addUser(u1);
		userDao.addUser(u2);
		userDao.addUser(m);
		u1 = userDao.getUserByCredentials(u1.getUserName(), u1.getPassword());
		u2 = userDao.getUserByCredentials(u2.getUserName(), u2.getPassword());
		m = userDao.getUserByCredentials(m.getUserName(), m.getPassword());
		reimb = new Reimbursement();
		reimb.setId(reimbDao.getReimbursements().size() + 1);
		reimb.setAmount(300.50);
		reimb.setSubmitted();
		reimb.setResolved(LocalDateTime.now().plusDays(2L));
		reimb.setDescription("This is the description of the first reimbursement that is typically provided by the author. It has a 250 character limit.");
		reimb.setReceipt(null);
		reimb.setAuthor(u1);
		reimb.setResolver(m);
		reimb.setStatus(ReimbursementStatus.APPROVED);
		reimb.setType(ReimbursementType.FOOD);
		reimbDao.addReimbursement(reimb);
		reimb2 = new Reimbursement();
		reimb2.setId(reimbDao.getReimbursements().size() + 1);
		reimb2.setAmount(1020.70);
		reimb2.setSubmitted();
		reimb2.setResolved(LocalDateTime.now().plusDays(3L));
		reimb2.setDescription("This is the description of the second reimbursement that is typically provided by the author. It has a 250 character limit.");
		reimb2.setReceipt(null);
		reimb2.setAuthor(u1);
		reimb2.setResolver(m);
		reimb2.setStatus(ReimbursementStatus.DENIED);
		reimb2.setType(ReimbursementType.LODGING);
		reimbDao.addReimbursement(reimb2);
	}

	@After
	public void tearDown() throws Exception {
		reimbDao.deleteReimbursement(reimb);
		reimbDao.deleteReimbursement(reimb2);
		userDao.deleteUser(u1);
		userDao.deleteUser(u2);
		userDao.deleteUser(m);
	}

	@Test
	public void testAddAndGetReimbursement() {
		assertEquals(2, reimbDao.getReimbursements().size());
		assertEquals(reimb, reimbDao.getReimbursementById(1));
		assertEquals(reimb2, reimbDao.getReimbursementById(2));
	}
	
	@Test
	public void testGetReimbursementsByAuthor() {
		assertEquals(2, reimbDao.getReimbursementsByAuthor(u1).size());
		Reimbursement reimb3 = new Reimbursement();
		reimb3.setAmount(70.10);
		reimb3.setSubmitted();
		reimb3.setResolved(LocalDateTime.now().plusDays(3L));
		reimb3.setDescription("This is the description of the newly-added reimbursement that is typically provided by the author. It has a 250 character limit.");
		reimb3.setReceipt(null);
		reimb3.setAuthor(u2);
		reimb3.setResolver(m);
		reimb3.setStatus(ReimbursementStatus.APPROVED);
		reimb3.setType(ReimbursementType.TRAVEL);
		reimbDao.addReimbursement(reimb3);
		assertEquals(2, reimbDao.getReimbursementsByAuthor(u1).size());
		assertEquals(1, reimbDao.getReimbursementsByAuthor(u2).size());
		reimbDao.deleteReimbursement(reimb3);
	}
	
	@Test
	public void testGetReimbursements() {
		assertEquals(2, reimbDao.getReimbursementsByAuthor(u1).size());
		Reimbursement reimb4 = new Reimbursement();
		reimb4.setAmount(110.10);
		reimb4.setSubmitted();
		reimb4.setResolved(LocalDateTime.now().plusDays(3L));
		reimb4.setDescription("This is the description of the newly-added reimbursement that is typically provided by the author. It has a 250 character limit.");
		reimb4.setReceipt(null);
		reimb4.setAuthor(u1);
		reimb4.setResolver(m);
		reimb4.setStatus(ReimbursementStatus.DENIED);
		reimb4.setType(ReimbursementType.OTHER);
		reimbDao.addReimbursement(reimb4);
		assertEquals(3, reimbDao.getReimbursements().size());
		reimbDao.deleteReimbursement(reimb4);
	}
	
	@Test
	public void testUpdateReimbursement() {
		assertEquals(reimb, reimbDao.getReimbursementById(reimb.getId()));
		Reimbursement reimb5 = new Reimbursement();
		reimb5.setId(reimb.getId());
		reimb5.setAmount(110.10);
		reimb5.setSubmitted();
		reimb5.setResolved(LocalDateTime.now().plusDays(3L));
		reimb5.setDescription("This is the description of the newly-added reimbursement that is typically provided by the author. It has a 250 character limit.");
		reimb5.setReceipt(null);
		reimb5.setAuthor(u1);
		reimb5.setResolver(m);
		reimb5.setStatus(ReimbursementStatus.DENIED);
		reimb5.setType(ReimbursementType.TRAVEL);
		reimbDao.updateReimbursement(reimb5);
		assertEquals(reimb5, reimbDao.getReimbursementById(reimb.getId()));
	}
	
	@Test
	public void testDeleteReimbursement() {
		assertTrue(reimbDao.deleteReimbursement(reimb));
		reimbDao.addReimbursement(reimb);
	}
	
	@Test
	public void testSubmitReimbursement() {
		SessionCache.setCurrentUser(u1);
		reimb6 = new Reimbursement();
		reimb6.setAmount(900.15);
		reimb6.setType(ReimbursementType.TRAVEL);
		rServ.submitTicket(reimb6);
		reimb6 = reimbDao.getReimbursementById(reimb6.getId());
		assertEquals(ReimbursementStatus.PENDING, reimbDao.getReimbursementById(reimb6.getId()).getStatus());
		assertFalse(reimb6.getSubmitted().equals(null));
		assertEquals(u1, reimb6.getAuthor());
		assertEquals(3, reimbDao.getReimbursements().size());
	}
	
	@Test
	public void testResolveReimbursement() {
		SessionCache.setCurrentUser(u1);
		reimb6 = new Reimbursement();
		reimb6.setAmount(900.15);
		reimb6.setType(ReimbursementType.TRAVEL);
		rServ.submitTicket(reimb6);
		SessionCache.setCurrentUser(m);
		reimb6 = reimbDao.getReimbursementById(reimb6.getId());
		rServ.resolveTicket(reimb6, true);
		reimb6 = reimbDao.getReimbursementById(reimb6.getId());
		assertFalse(reimb6.getResolved().equals(null));
		assertEquals(m, reimb6.getResolver());
		assertEquals(ReimbursementStatus.APPROVED, reimb6.getStatus());
		rServ.resolveTicket(reimb, false);
		reimb = reimbDao.getReimbursementById(reimb.getId());
		assertFalse(reimb.getResolved().equals(null));
		assertEquals(m, reimb.getResolver());
		assertEquals(ReimbursementStatus.DENIED, reimb.getStatus());
	}
	
	@Test(expected=UnauthorizedException.class)
	public void testInvalidResolutionAttempt() {
		SessionCache.setCurrentUser(u2);
		rServ.resolveTicket(reimb2, true);	// Since u2 is not a financial manager, this should throw an exception.
	}
}
