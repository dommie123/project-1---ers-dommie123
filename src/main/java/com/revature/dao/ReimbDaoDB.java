package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.revature.beans.Reimbursement;
import com.revature.beans.Reimbursement.ReimbursementStatus;
import com.revature.beans.Reimbursement.ReimbursementType;
import com.revature.beans.User;
import com.revature.utils.ConnectionUtil;

public class ReimbDaoDB implements ReimbursementDao {
	
	private ConnectionUtil connUtil = ConnectionUtil.getConnectionUtil();
	private Connection conn = connUtil.getConnection();
	private UserDao uDao = new UserDaoDB();
	private static final Logger logger = Logger.getLogger(ReimbDaoDB.class);
	
	@Override
	public void addReimbursement(Reimbursement reimb) {
		if (conn == null) return;
		String query = "{? = call addReimbursement(?, ?, ?, ?, ?, ?, ?, ?, ?)};";
		try (CallableStatement stmt = conn.prepareCall(query)) {
			conn.setAutoCommit(false);
			stmt.registerOutParameter(1, Types.OTHER);
			stmt.setDouble(2, reimb.getAmount());
			
			Timestamp submitted = Timestamp.valueOf(reimb.getSubmitted());
			Timestamp resolved = Timestamp.valueOf(reimb.getResolved());
			
			stmt.setTimestamp(3, submitted);
			stmt.setTimestamp(4, resolved);
			stmt.setString(5, reimb.getDescription());
			stmt.setBytes(6, reimb.getReceipt());
			stmt.setInt(7, reimb.getAuthor().getId());
			stmt.setInt(8, reimb.getResolver().getId());
			
			if (reimb.getStatus().equals(ReimbursementStatus.PENDING))
				stmt.setInt(9, 0);
			else if (reimb.getStatus().equals(ReimbursementStatus.APPROVED))
				stmt.setInt(9, 1);
			else if (reimb.getStatus().equals(ReimbursementStatus.DENIED))
				stmt.setInt(9, 2);
			
			if (reimb.getType().equals(ReimbursementType.LODGING))
				stmt.setInt(10, 0);
			else if (reimb.getType().equals(ReimbursementType.TRAVEL))
				stmt.setInt(10, 1);
			else if (reimb.getType().equals(ReimbursementType.FOOD))
				stmt.setInt(10, 2);
			else if (reimb.getType().equals(ReimbursementType.OTHER))
				stmt.setInt(10, 3);
			
			stmt.execute();
			
		} catch (SQLException e) {
			logger.log(Level.ERROR, "Query failed to execute! Please fix this immediately!");
			e.printStackTrace();
		}
	}

	@Override
	public Reimbursement getReimbursementById(int reimbid) {
		if (conn == null) return null;
		String query = "SELECT * FROM ers_reimbursement WHERE reimb_id = ?;";
		try (PreparedStatement stmt = conn.prepareStatement(query)) {
			stmt.setInt(1, reimbid);
			ResultSet rs = stmt.executeQuery();
			Reimbursement reimb = new Reimbursement();

			while (rs.next()) {
				reimb.setId(rs.getInt(1));
				reimb.setAmount(rs.getDouble(2));
				
				LocalDateTime s = rs.getTimestamp(3).toLocalDateTime();
				LocalDateTime r = rs.getTimestamp(4).toLocalDateTime();
				
				reimb.setSubmitted(s);
				reimb.setResolved(r);
				reimb.setDescription(rs.getString(5));
				reimb.setReceipt(rs.getBytes(6));
				reimb.setAuthor(uDao.getUserById(rs.getInt(7)));
				reimb.setResolver(uDao.getUserById(rs.getInt(8)));
				
				if (rs.getInt(9) == 0)
					reimb.setStatus(ReimbursementStatus.PENDING);
				else if (rs.getInt(9) == 1)
					reimb.setStatus(ReimbursementStatus.APPROVED);
				else if (rs.getInt(9) == 2)
					reimb.setStatus(ReimbursementStatus.DENIED);
				
				if (rs.getInt(10) == 0)
					reimb.setType(ReimbursementType.LODGING);
				else if (rs.getInt(10) == 1)
					reimb.setType(ReimbursementType.TRAVEL);
				else if (rs.getInt(10) == 2)
					reimb.setType(ReimbursementType.FOOD);
				else if (rs.getInt(10) == 3)
					reimb.setType(ReimbursementType.OTHER);
			}
			
			return reimb;
		} catch (SQLException e) {
			logger.log(Level.ERROR, "Query failed to execute! Please fix this immediately!");
			e.getLocalizedMessage();
		}
		return null;
	}

	@Override
	public List<Reimbursement> getReimbursements() {
		if (conn == null) return null;
		List<Reimbursement> reimbList = new ArrayList<>();
		String query = "SELECT * FROM ers_reimbursement";
		try (Statement stmt = conn.createStatement()) {
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()) {
				Reimbursement reimb = new Reimbursement();
				reimb.setId(rs.getInt(1));
				reimb.setAmount(rs.getDouble(2));
				
				LocalDateTime s = rs.getTimestamp(3).toLocalDateTime();
				LocalDateTime r = rs.getTimestamp(4).toLocalDateTime();
				
				reimb.setSubmitted(s);
				reimb.setResolved(r);
				reimb.setDescription(rs.getString(5));
				reimb.setReceipt(rs.getBytes(6));
				reimb.setAuthor(uDao.getUserById(rs.getInt(7)));
				reimb.setResolver(uDao.getUserById(rs.getInt(8)));
				
				if (rs.getInt(9) == 0)
					reimb.setStatus(ReimbursementStatus.PENDING);
				else if (rs.getInt(9) == 1)
					reimb.setStatus(ReimbursementStatus.APPROVED);
				else if (rs.getInt(9) == 2)
					reimb.setStatus(ReimbursementStatus.DENIED);
				
				if (rs.getInt(10) == 0)
					reimb.setType(ReimbursementType.LODGING);
				else if (rs.getInt(10) == 1)
					reimb.setType(ReimbursementType.TRAVEL);
				else if (rs.getInt(10) == 2)
					reimb.setType(ReimbursementType.FOOD);
				else if (rs.getInt(10) == 3)
					reimb.setType(ReimbursementType.OTHER);
				
				reimbList.add(reimb);
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "Query failed to execute! Please fix this immediately!");
			e.getLocalizedMessage();
		}
		
		return reimbList;
	}

	@Override
	public List<Reimbursement> getReimbursementsByAuthor(User u) {
		List<Reimbursement> reimbList = new ArrayList<>();
		String query = "SELECT * FROM ers_reimbursement WHERE reimb_author = ?";
		try (PreparedStatement stmt = conn.prepareStatement(query)) {
			stmt.setInt(1, u.getId());
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				Reimbursement reimb = new Reimbursement();
				reimb.setId(rs.getInt(1));
				reimb.setAmount(rs.getDouble(2));
				
				LocalDateTime s = rs.getTimestamp(3).toLocalDateTime();
				LocalDateTime r = rs.getTimestamp(4).toLocalDateTime();
				
				reimb.setSubmitted(s);
				reimb.setResolved(r);
				reimb.setDescription(rs.getString(5));
				reimb.setReceipt(rs.getBytes(6));
				reimb.setAuthor(uDao.getUserById(rs.getInt(7)));
				reimb.setResolver(uDao.getUserById(rs.getInt(8)));
				
				if (rs.getInt(9) == 0)
					reimb.setStatus(ReimbursementStatus.PENDING);
				else if (rs.getInt(9) == 1)
					reimb.setStatus(ReimbursementStatus.APPROVED);
				else if (rs.getInt(9) == 2)
					reimb.setStatus(ReimbursementStatus.DENIED);
				
				if (rs.getInt(10) == 0)
					reimb.setType(ReimbursementType.LODGING);
				else if (rs.getInt(10) == 1)
					reimb.setType(ReimbursementType.TRAVEL);
				else if (rs.getInt(10) == 2)
					reimb.setType(ReimbursementType.FOOD);
				else if (rs.getInt(10) == 3)
					reimb.setType(ReimbursementType.OTHER);
				
				reimbList.add(reimb);
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "Query failed to execute! Please fix this immediately!");
			e.getLocalizedMessage();
		}
		
		return reimbList;
	}

	@Override
	public void updateReimbursement(Reimbursement reimb) {
		if (conn == null) return;
		String query = "UPDATE ers_reimbursements SET reimb_amount = ?,"
				+ " reimb_submitted = ?, reimb_resolved = ?, reimb_description = ?,"
				+ " reimb_receipt = ?, reimb_author = ?, reimb_resolver = ?, "
				+ "reimb_status_id = ?, reimb_type_id = ? WHERE reimb_id = ?;";
		try (PreparedStatement stmt = conn.prepareStatement(query)) {
			stmt.setDouble(1, reimb.getAmount());
			
			Timestamp submitted = Timestamp.valueOf(reimb.getSubmitted());
			Timestamp resolved = Timestamp.valueOf(reimb.getResolved());
			
			stmt.setTimestamp(2, submitted);
			stmt.setTimestamp(3, resolved);
			stmt.setString(4, reimb.getDescription());
			stmt.setBytes(5, reimb.getReceipt());
			stmt.setInt(6, reimb.getAuthor().getId());
			stmt.setInt(7, reimb.getResolver().getId());
			
			if (reimb.getStatus().equals(ReimbursementStatus.PENDING))
				stmt.setInt(8, 0);
			else if (reimb.getStatus().equals(ReimbursementStatus.APPROVED))
				stmt.setInt(8, 1);
			else if (reimb.getStatus().equals(ReimbursementStatus.DENIED))
				stmt.setInt(8, 2);
			
			if (reimb.getType().equals(ReimbursementType.LODGING))
				stmt.setInt(9, 0);
			else if (reimb.getType().equals(ReimbursementType.TRAVEL))
				stmt.setInt(9, 1);
			else if (reimb.getType().equals(ReimbursementType.FOOD))
				stmt.setInt(9, 2);
			else if (reimb.getType().equals(ReimbursementType.OTHER))
				stmt.setInt(9, 3);
			
			stmt.setInt(10, reimb.getId());
			
			stmt.execute();
		} catch (SQLException e) {
			logger.log(Level.ERROR, "Query failed to execute! Please fix this immediately!");
			e.getLocalizedMessage();
		}
	}

	@Override
	public boolean deleteReimbursement(Reimbursement reimb) {
		if (conn == null) return false;
		String query = "DELETE FROM ers_reimbursement WHERE id = ?;";
		try (PreparedStatement stmt = conn.prepareStatement(query)) {
			stmt.setInt(1, reimb.getId());
			return stmt.execute() || stmt.getUpdateCount() > 0;
		} catch (SQLException e) {
			logger.log(Level.ERROR, "Query failed to execute! Please fix this immediately!");
			e.printStackTrace();
		}
		return false;
	}

}
