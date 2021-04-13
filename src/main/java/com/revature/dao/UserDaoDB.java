package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.revature.beans.User;
import com.revature.beans.User.UserRole;
import com.revature.utils.ConnectionUtil;

public class UserDaoDB implements UserDao {

	private ConnectionUtil connUtil = ConnectionUtil.getConnectionUtil();
	private Connection conn = connUtil.getConnection();
	
	@Override
	public void addUser(User u) {
		if (conn == null) return;
		String query = "{call addUser(?, ?, ?, ?, ?, ?, ?)}";
		try (CallableStatement stmt = conn.prepareCall(query)) {
			//stmt.registerOutParameter(1, Types.VARCHAR);
			stmt.setInt(1, this.getAllUsers().size() + 1);
			stmt.setString(2, u.getUserName());
			stmt.setString(3, u.getPassword());
			stmt.setString(4, u.getFirstName());
			stmt.setString(5, u.getLastName());
			stmt.setString(6, u.getEmail());
			
			if (u.getRole().equals(UserRole.EMPLOYEE))
				stmt.setInt(7, 1);
			else if (u.getRole().equals(UserRole.MANAGER))
				stmt.setInt(7, 2);
			
			stmt.execute();
		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
		}
	}

	@Override
	public List<User> getAllUsers() {
		if (conn == null) return null;
		List<User> users = new ArrayList<>();
		String query = "SELECT * FROM ers_users;";
		try (Statement stmt = conn.createStatement()) {
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()) {
				User u = new User();
				
				u.setId(rs.getInt(1));
				u.setUserName(rs.getString(2));
				u.setPassword(rs.getString(3));
				u.setFirstName(rs.getString(4));
				u.setLastName(rs.getString(5));
				u.setEmail(rs.getString(6));
				
				if (rs.getInt(7) == 1)
					u.setRole(UserRole.EMPLOYEE);
				else if (rs.getInt(7) == 2)
					u.setRole(UserRole.MANAGER);
				
				users.add(u);
			}
		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
		}
		return users;
	}

	@Override
	public User getUserById(int userid) {
		if (conn == null) return null;
		String query = "SELECT * FROM ers_users WHERE ers_users_id = ?;";
		try (PreparedStatement stmt = conn.prepareStatement(query)) {
			stmt.setInt(1, userid);
			ResultSet rs = stmt.executeQuery();
			User u = new User();
			
			while (rs.next()) {
				u.setId(rs.getInt(1));
				u.setUserName(rs.getString(2));
				u.setPassword(rs.getString(3));
				u.setFirstName(rs.getString(4));
				u.setLastName(rs.getString(5));
				u.setEmail(rs.getString(6));
				
				if (rs.getInt(7) == 1)
					u.setRole(UserRole.EMPLOYEE);
				else if (rs.getInt(7) == 2)
					u.setRole(UserRole.MANAGER);
				
				if (u.getReimbursements() == null)
					u.setReimbursements(new ArrayList<>());
			}
			return u;
		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
		}
		return null;
	}

	@Override
	public User getUserByCredentials(String username, String password) {
		if (conn == null) return null;
		String query = "SELECT * FROM ers_users WHERE ers_username = ? AND ers_pass = ?;";
		try (PreparedStatement stmt = conn.prepareStatement(query)) {
			stmt.setString(1, username);
			stmt.setString(2, password);
			ResultSet rs = stmt.executeQuery();
			User u = new User();
			
			while (rs.next()) {
				u.setId(rs.getInt(1));
				u.setUserName(rs.getString(2));
				u.setPassword(rs.getString(3));
				u.setFirstName(rs.getString(4));
				u.setLastName(rs.getString(5));
				u.setEmail(rs.getString(6));
				
				if (rs.getInt(7) == 1)
					u.setRole(UserRole.EMPLOYEE);
				else if (rs.getInt(7) == 2)
					u.setRole(UserRole.MANAGER);
				
				if (u.getReimbursements() == null)
					u.setReimbursements(new ArrayList<>());
			}
			return u;
		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
		}
		return null;
	}

	@Override
	public void updateUser(User u) {
		if (conn == null) return;
		String query = "UPDATE ers_users SET ers_username = ?, ers_pass = ?, "
				+ "user_first_name = ?, user_last_name = ?, user_email = ?, user_role_id = ?"
				+ " WHERE ers_users_id = ?;";
		try (PreparedStatement stmt = conn.prepareStatement(query)) {
			stmt.setString(1, u.getUserName());
			stmt.setString(2, u.getPassword());
			stmt.setString(3, u.getFirstName());
			stmt.setString(4, u.getLastName());
			stmt.setString(5, u.getEmail());
			
			if (u.getRole().equals(UserRole.EMPLOYEE))
				stmt.setInt(6, 1);
			else if (u.getRole().equals(UserRole.MANAGER))
				stmt.setInt(6, 2);
			
			stmt.setInt(7, u.getId());
			stmt.execute();
			
		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
		}
	}

	@Override
	public boolean deleteUser(User u) {
		if (conn == null) return false;
		String query = "DELETE FROM ers_users WHERE ers_users_id = ?;";
		try (PreparedStatement stmt = conn.prepareStatement(query)) {
			stmt.setInt(1, u.getId());
			
			return stmt.execute() || stmt.getUpdateCount() > 0;
		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
		}
		return false;
	}
	
	// This is a temporary method for the purpose of debugging
	public void setConnection(Connection conn) {
		this.conn = conn;
	}

}
