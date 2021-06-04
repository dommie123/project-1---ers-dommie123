package com.revature.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.postgresql.Driver;

public class ConnectionUtil {
	
	//private String driver;
	private String url;
	private String user;
	private String password;
	
	private static ConnectionUtil util = null;
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
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
