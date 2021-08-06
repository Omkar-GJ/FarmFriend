package com.farmfriend.market;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import com.farmfriend.db.DatabaseConnection;

public class MarketPricesRepositoy {


	public void saveMarketPrices(MarketPricesResponse marketPricesResponse) {

		DatabaseConnection dbconnection = new DatabaseConnection();
		Connection conn = dbconnection.getDatabaseConnection();
		
			//Delete existing data to save latest market prices
			String query="truncate marketprice";
			Statement stmt;
			try {
				stmt = conn.createStatement();
				stmt.execute(query);
				
				List<Records> records = marketPricesResponse.getRecords();
				for(Records record:records) {
					query = "INSERT INTO marketprice (arrivalDate,state,district,commodity,variety,minPrice,maxPrice,modalPrice,datatime, market) VALUES "
						+ "(?,?,?,?,?,?,?,?,?,?)";
					
					
						String arrivalDate = record.arrival_date;
						SimpleDateFormat sdf1 = new SimpleDateFormat("dd/mm/yyyy");
						java.util.Date date = sdf1.parse(arrivalDate);
						java.sql.Date sqlarrivalDate = new java.sql.Date(date.getTime());
					 	
						PreparedStatement pstmt = conn.prepareStatement(query);
	
						pstmt.setDate(1, sqlarrivalDate);
						pstmt.setString(2,record.state);
						pstmt.setString(3,record.district);
						pstmt.setString(4,record.commodity);
						pstmt.setString(5,record.variety); 
						pstmt.setString(6,record.min_price);
						pstmt.setString(7,record.max_price);
						pstmt.setString(8,record.modal_price);
						pstmt.setString(9,record.timestamp);
						pstmt.setString(10,record.market);
						pstmt.execute();
				}	
				
			} catch (SQLException e1) {
				e1.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}

	}
	
	public List<MarketPrice> getMarketPriceByCommodity( String commodity) {
		DatabaseConnection dbconnection = new DatabaseConnection();
		Connection conn = dbconnection.getDatabaseConnection();
		List<MarketPrice> prices=new ArrayList<MarketPrice>();
		Statement stmt;
		try {
			
			stmt = conn.createStatement();
			String query="select * from marketprice where upper(commodity) like upper('"+commodity+"');";
			ResultSet rs=stmt.executeQuery(query);

			while (rs.next ()){
				
				 Date arrivalDate=rs.getDate("arrivalDate");
				  String market=rs.getString("market");
				  String variety=rs.getString("variety");
				  String minPrice=rs.getString("minPrice");
				  String maxPrice=rs.getString("maxPrice");
				  String modalPrice=rs.getString("modalPrice");
 
				  MarketPrice marketPrice=new MarketPrice();
				  marketPrice.setMarket(market);
				  marketPrice.setArrival_date(arrivalDate);
				  marketPrice.setCommodity(commodity);
				  marketPrice.setVariety(variety);
				  marketPrice.setMin_price(minPrice);
				  marketPrice.setMax_price(maxPrice);
				  marketPrice.setModal_price(modalPrice);
			
				  prices.add(marketPrice);
			 }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return prices;
	}
	
	
	List<MarketPrice> getAllMarketPrices() {
		
		System.out.println("start: getAllMarketPrices");
		List<MarketPrice> marketPrices=new ArrayList<MarketPrice>();
		DatabaseConnection dbconnection = new DatabaseConnection();
		Connection conn = dbconnection.getDatabaseConnection();
		Statement stmt;
		try {
			
			stmt = conn.createStatement();
			String query="select * from marketprice;";
			ResultSet rs=stmt.executeQuery(query);

			while (rs.next ()){
				
				  Date arrivalDate=rs.getDate("arrivalDate");
				  String market=rs.getString("market");
				  String district=rs.getString("district");
				  String commodity=rs.getString("commodity");
				  String variety=rs.getString("variety");
				  String minPrice=rs.getString("minPrice");
				  String maxPrice=rs.getString("maxPrice");
				  String modalPrice=rs.getString("modalPrice");
				  
				  MarketPrice marketPrice=new MarketPrice();
				  marketPrice.setMarket(market);
				  marketPrice.setDistrict(district);
				  marketPrice.setArrival_date(arrivalDate);
				  marketPrice.setCommodity(commodity);
				  marketPrice.setVariety(variety);
				  marketPrice.setMin_price(minPrice);
				  marketPrice.setMax_price(maxPrice);
				  marketPrice.setModal_price(modalPrice);
				  
				  marketPrices.add(marketPrice);
				  
			 }
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("list of size:"+marketPrices.size());
		
		return marketPrices;
	}
	
}
