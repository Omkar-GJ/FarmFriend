package com.farmfriend.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

	public Connection getDatabaseConnection(){
		
		Connection conn=null;
		String host = "jdbc:mysql://localhost:3306/sys"; 
		String user = "root";
		String password ="root";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn=DriverManager.getConnection(host,user, password);  
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
		catch (SQLException e) 
		{
			
			e.printStackTrace();
			
		}  
		
		return conn;
		
	}
}
