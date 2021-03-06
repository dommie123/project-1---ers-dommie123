package com.revature.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class ConnectionUtil {
	
	private String driver;
	private String url;
	private String user;
	private String pword;
	
	private static ConnectionUtil util = null;
	private static final Logger logger = Logger.getLogger(ConnectionUtil.class);
	
	private ConnectionUtil() {
		// Read database.properties file
		ClassLoader classLoader = getClass().getClassLoader();
		InputStream is = ClassLoader.getSystemResourceAsStream("database.properties");
		Properties p = new Properties();
		
		try {
			p.load(is);
			this.driver = p.getProperty("driver");
			this.url = p.getProperty("url");
			this.user = p.getProperty("usr");
			this.pword = p.getProperty("pswd");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static ConnectionUtil getConnectionUtil() {
		if (util == null)
			util = new ConnectionUtil();
		return util;
	}
	
	public Connection getConnection() {
		try {
			Connection conn = DriverManager.getConnection(null);
			return conn;
		} catch (SQLException e) {
			logger.log(Level.FATAL, "Failed to establish a connection to the database! Please fix this immediately!");
			return null;
		}
	}
}
