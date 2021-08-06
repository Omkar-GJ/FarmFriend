package com.farmfriend.farmer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.farmfriend.db.DatabaseConnection;

public class FarmerRepository {
	
	public void saveFarmerDetails(Farmer farmer){
		
		DatabaseConnection dbconnection=new DatabaseConnection();
		Connection conn = dbconnection.getDatabaseConnection();

		PreparedStatement statement;
		try {
			
			statement = (PreparedStatement) conn.
			prepareStatement("INSERT INTO farmer(firstname,lastname,contactnumber,locationid,username,password,mail) values(?,?,?,?,?,?,?)");
			

			statement.setString(1, farmer.getFname());
			statement.setString(2, farmer.getLname());
			statement.setLong(3, farmer.getPhoneNo());
			statement.setInt(4, farmer.getLocationid());
			statement.setString(5, farmer.getUsername());
			statement.setString(6, farmer.getPassword());
			statement.setString(7, farmer.getEmail());
			
			statement.executeUpdate();
			statement.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("data added ib db...........");
	}
	
	
	
	public boolean verifyCredentials(String username, String password){
		DatabaseConnection dbconnection=new DatabaseConnection();
		Connection conn = dbconnection.getDatabaseConnection();
		
		//verify credenitials in DB
		boolean loginResult=false;
		String query="select id from farmer where username='"+username+"' and password='"+password+"';";
		
		
		//How Is It Verift*******
		Statement statement;
		try {
			statement = conn.createStatement();
			ResultSet rs=statement.executeQuery(query);

			while (rs.next ()){
				 String id=rs.getString("id");
				 if(id!=null){
					 if(id.length()>0) {
						 loginResult=true;
					}
				 }
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return loginResult;
	}
	
	
	
}
