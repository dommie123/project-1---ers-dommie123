package com.revature.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.postgresql.Driver;

public class ConnectionUtil {
	
	//private String driver;
	private String url;
	private String user;
	private String password;
	
	private static ConnectionUtil util = null;
	private static final Logger logger = Logger.getLogger(ConnectionUtil.class);
	//Properties p = new Properties();
	
	private ConnectionUtil() {
		// Read database.properties file
		ClassLoader classLoader = getClass().getClassLoader();
		InputStream is = classLoader.getResourceAsStream("database.properties");
		//logger.log(Level.INFO, is.toString());
		this.url = "jdbc:postgresql://domdb1.ch05h4i2oiwh.us-east-2.rds.amazonaws.com:5432/reimb";
		this.user = "ruser";
		this.password = "d@tb0i77";
		
//		try {
//			//p.load(is);
//			//this.driver = p.getProperty("driver");
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}
	
	public static synchronized ConnectionUtil getConnectionUtil() {
		if (util == null)
			util = new ConnectionUtil();
		return util;
	}
	
	public Connection getConnection() {
		try {
			Class.forName("org.postgresql.Driver");
			Connection conn = DriverManager.getConnection(url, user, password);
			return conn;
		} catch (SQLException e) {
			logger.log(Level.FATAL, "Failed to establish a connection to the database! \nError Message: " + e.getLocalizedMessage());
			
		} catch (ClassNotFoundException e) {
			logger.log(Level.FATAL, "Failed to read the Driver class! \nError Message: " + e.getLocalizedMessage());
			e.printStackTrace();
		}
		return null;
	}
}
