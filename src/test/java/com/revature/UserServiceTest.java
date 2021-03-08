package com.revature;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import com.revature.beans.User;
import com.revature.beans.User.UserRole;
import com.revature.dao.UserDao;
import com.revature.dao.UserDaoDB;
import com.revature.exceptions.InvalidCredentialsException;
import com.revature.exceptions.UsernameAlreadyExistsException;
import com.revature.services.UserService;
import com.revature.utils.SessionCache;

public class UserServiceTest {
	
	private static UserDao uDao;
	private UserService uServ = new UserService();
	private static User u;
	private static User m;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		uDao = new UserDaoDB();
		u = new User();
		m = new User();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		u.setId(1);
		u.setUserName("testuser");
		u.setPassword("password");
		u.setFirstName("testFirst");
		u.setLastName("testLast");
		u.setEmail("test@example.com");
		u.setRole(UserRole.EMPLOYEE);
		uDao.addUser(u);
		m.setId(2);
		m.setUserName("testman");
		m.setPassword("password");
		m.setFirstName("testFirstMan");
		m.setLastName("testLastMan");
		m.setEmail("manager@example.com");
		m.setRole(UserRole.MANAGER);
		uDao.addUser(m);
	}

	@After
	public void tearDown() throws Exception {
		uDao.deleteUser(u);
		uDao.deleteUser(m);
	}

	@Test
	public void testAddAndGetUser() {
		assertEquals(2, uDao.getAllUsers().size());
		assertEquals(u, uDao.getUserById(u.getId()));
		assertEquals(m, uDao.getUserById(m.getId()));
	}
	
	@Test
	public void testGetAllUsers() {
		assertEquals(2, uDao.getAllUsers().size());
		User u = new User();
		u.setUserName("testuser2");
		u.setPassword("password");
		u.setFirstName("testFirst2");
		u.setLastName("testLast2");
		u.setEmail("test2@example.com");
		u.setRole(UserRole.EMPLOYEE);
		uDao.addUser(u);
		assertEquals(3, uDao.getAllUsers().size());
	}
	
	// This test method must be executed by itself to pass.
	@Test
	public void testUpdateUser() {
		assertEquals("testuser", uDao.getUserById(u.getId()).getUserName());
		User u = uDao.getUserById(UserServiceTest.u.getId());
		u.setUserName("testuser3");
		u.setPassword("password");
		u.setFirstName("testFirst3");
		u.setLastName("testLast3");
		u.setEmail("test3@example.com");
		u.setRole(UserRole.EMPLOYEE);
		uDao.updateUser(u);
		assertEquals("testuser3", uDao.getUserById(UserServiceTest.u.getId()).getUserName());
	}
	
	// This test method must be executed along with the other test methods to pass.
	@Test
	public void testDeleteUser() {
		assertTrue(uDao.deleteUser(uDao.getUserByCredentials("testuser2", "password")));
	}
	
	@Test
	public void testLogin() {
		String username = "testuser";
		String password = "password";
		assertEquals(u, uDao.getUserByCredentials(username, password));
		uServ.login(username, password);
		assertEquals(u, SessionCache.getCurrentUser().get());
	}
	
	@Test(expected=InvalidCredentialsException.class)
	public void testInvalidLoginAttempt() {
		String username = "invalidName";
		String password = "notPassword";
		uServ.login(username, password);
	}
	
	// This test method must be executed by itself to pass.
	@Test
	public void testRegister() {
		assertEquals(2, uDao.getAllUsers().size());
		User test = new User();
		test.setUserName("testuser4");
		test.setPassword("password4");
		test.setFirstName("testFirst4");
		test.setLastName("testLast4");
		test.setEmail("test4@example.com");
		test.setRole(UserRole.EMPLOYEE);
		uServ.register(test);
		assertEquals(3, uDao.getAllUsers().size());
		uDao.deleteUser(test);
	}
	
	@Test(expected=UsernameAlreadyExistsException.class)
	public void testInvalidRegistrationAttempt() {
		User test = new User();
		test.setUserName("testuser");
		test.setPassword("password");
		test.setFirstName("testFirst");
		test.setLastName("testLast");
		test.setEmail("test@example.com");
		test.setRole(UserRole.EMPLOYEE);
		uServ.register(test);
	}

}
