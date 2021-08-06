package com.farmfriend.farmer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.farmfriend.db.DatabaseConnection;

public class LocationRespository {
	
	public int checkLocationExists(String village, String taluka, String district) {
		DatabaseConnection dbconnection=new DatabaseConnection();
		Connection conn = dbconnection.getDatabaseConnection();
		PreparedStatement statement;
		int locationid=0;
		try {
			statement = (PreparedStatement) conn.
					prepareStatement("select id from location where village=? and taluka=? and district=?");
			statement.setString(1, village);
			statement.setString(2, taluka);
			statement.setString(3, district);
			ResultSet result = statement.executeQuery();
			
			 if (result.next ()){ // *********
				 locationid=result.getInt("id");
				 System.out.println("Existing location found for :"+village+"\t"+taluka+"with id="+locationid);
			 }
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return locationid;
	}
	
	
	public int saveLocationDetails(Location location){
		
		DatabaseConnection dbconnection=new DatabaseConnection();
		Connection conn = dbconnection.getDatabaseConnection();
		PreparedStatement statementlocation;
		int locationId = 0;
		ResultSet rs = null;
		
		try {
			statementlocation = (PreparedStatement) conn
					.prepareStatement("INSERT INTO location (village,taluka,district,pincode) values(?,?,?,?)",
							Statement.RETURN_GENERATED_KEYS); // to return auto generated key, which is use at line no 60
			
			statementlocation.setString(1, location.getVillage());
			statementlocation.setString(2, location.getTaluka());
			statementlocation.setString(3, location.getDistrict());
			statementlocation.setInt(4, location.getPincode());
			int rowAffected=statementlocation.executeUpdate();

            if(rowAffected == 1)
            {
                rs = statementlocation.getGeneratedKeys();// if we return auto key at line no 50, then only we get key here
                if(rs.next())
                	locationId = rs.getInt(1);
            }
			statementlocation.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return locationId;
	}
	
	
	public Location getLocation(String username){
		Location location=null;
		
		DatabaseConnection dbconnection=new DatabaseConnection();
		Connection conn = dbconnection.getDatabaseConnection();
		
		String query="select locationid from farmer where username='"+username+"';";
		System.out.println("query:"+query);
		
		Statement statement;
		try {
			//get location id from farmer table
			statement = conn.createStatement();
			ResultSet rs=statement.executeQuery(query);
			int locationid=0;
			 
			while (rs.next ()){
				locationid=rs.getInt("locationid");
			}
			
			//fetch corresponding location from location table 
			if(locationid!=0) {
				 query="select * from location where id='"+locationid+"';";
				 System.out.println("query:"+query);
				 ResultSet locationResultSet=statement.executeQuery(query); 
				 
				 while (locationResultSet.next ()){
					  location =new Location();
					 
					  String village=locationResultSet.getString("village");
					  String taluka=locationResultSet.getString("taluka");
					  String district=locationResultSet.getString("district");	
					  int pincode=locationResultSet.getInt("pincode");
					 
					  location.setId(locationid);
					  location.setVillage(village);
					  location.setTaluka(taluka);
					  location.setDistrict(district);
					  location.setPincode(pincode);  
				 } 
			 }
			 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return location;
	}
	
	
}
