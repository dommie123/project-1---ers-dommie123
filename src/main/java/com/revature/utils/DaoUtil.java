package com.revature.utils;

import com.revature.dao.ReimbDaoDB;
import com.revature.dao.ReimbursementDao;
import com.revature.dao.UserDao;
import com.revature.dao.UserDaoDB;

public class DaoUtil {
	
	private static UserDao uDao;
	private static ReimbursementDao reimbDao;
	
	public static UserDao getUserDao() {
		if (uDao == null)
			uDao = new UserDaoDB();
		return uDao;
	}

	public static ReimbursementDao getReimbDao() {
		if (reimbDao == null)
			reimbDao = new ReimbDaoDB();
		return reimbDao;
	}
}
