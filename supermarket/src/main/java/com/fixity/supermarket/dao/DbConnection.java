package com.fixity.supermarket.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {
	
	static Connection con;
	
	
	public static Connection getConnection() {
		
		if (con == null){
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				con= DriverManager.getConnection("jdbc:mysql://localhost:3306/shopmarket","root","sunnyramu24");
			}
			catch (Exception e) {
			e.printStackTrace();
				// TODO: handle exception
			}
		}
	return con;
	}

}

