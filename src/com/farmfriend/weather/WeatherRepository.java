package com.farmfriend.weather;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import com.farmfriend.db.DatabaseConnection;

public class WeatherRepository {

	public void saveWeather(WeatherResponse weatherResponse) {
		
		DatabaseConnection dbconnection=new DatabaseConnection();
		Connection conn = dbconnection.getDatabaseConnection();
		
		//save in db table :weather
		String description="";
		String[] des = weatherResponse.current.getWeather_descriptions();
		if(des.length>=1) {
			for(int i=0;i<des.length;i++) {
				if(description=="") {
					description=des[i];
				}else {
					description=description+", "+ des[i];
				}
			}
		}
		
		String insertQuery="insert into weather (description,temperature,windspeed,winddirection,humidity,visibility,location)"
				+ " values('"+description+
				"',"+weatherResponse.getCurrent().getTemperature()+","+weatherResponse.getCurrent().getWind_speed()
				+",'"+weatherResponse.getCurrent().getWind_dir()+"',"+weatherResponse.getCurrent().getHumidity()+
				","+weatherResponse.getCurrent().getVisibility()+",'"+weatherResponse.getLocation().getName()+"');";
		
		System.out.println("query:"+insertQuery);
		
		Statement statement;
		try {
			statement = conn.createStatement();
			statement.executeUpdate(insertQuery);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		System.out.println("data added ib db...........");
		
	}
	
	public Map<String,String> getWeather(String location) {
		
		DatabaseConnection dbconnection=new DatabaseConnection();
		Statement stmt;
		Map<String,String> map=new HashMap<String,String>();
		try {
			Connection conn = dbconnection.getDatabaseConnection();
			stmt = conn.createStatement();
			
			ResultSet rs=stmt.executeQuery("select * from weather where location='"+location+"' limit 1;");

		
			while (rs.next ()){
				  String description=rs.getString("description");
				  String temperature=Integer.toString(rs.getInt("temperature"));
				  String winddirection=rs.getString("winddirection");
				  String windspeed=Integer.toString(rs.getInt("windspeed"));	
				  String humidity=Integer.toString(rs.getInt("humidity"));
				
				  
				  map.put("temperature", temperature);
				  map.put("windspeed", windspeed);
				  map.put("humidity", humidity);
				  map.put("description", description);
				  map.put("winddirection", winddirection);
				  
				  
				  
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	return map;
		
	}
}
