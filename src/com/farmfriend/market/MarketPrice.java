package com.farmfriend.market;

import java.util.Date;

public class MarketPrice {

	private int id;
	private Date arrival_date;
	private String state;
	private String district;
	private String market;
	private String commodity;
	private String variety;
	private String min_price;
	private String max_price;
	private String modal_price;
	private String dataTime;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getArrival_date() {
		return arrival_date;
	}
	public void setArrival_date(Date arrival_date) {
		this.arrival_date = arrival_date;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getMarket() {
		return market;
	}
	public void setMarket(String market) {
		this.market = market;
	}
	public String getCommodity() {
		return commodity;
	}
	public void setCommodity(String commodity) {
		this.commodity = commodity;
	}
	public String getVariety() {
		return variety;
	}
	public void setVariety(String variety) {
		this.variety = variety;
	}
	public String getMin_price() {
		return min_price;
	}
	public void setMin_price(String min_price) {
		this.min_price = min_price;
	}
	public String getMax_price() {
		return max_price;
	}
	public void setMax_price(String max_price) {
		this.max_price = max_price;
	}
	public String getModal_price() {
		return modal_price;
	}
	public void setModal_price(String modal_price) {
		this.modal_price = modal_price;
	}
	
	
	public String getDataTime() {
		return dataTime;
	}
	public void setDataTime(String dataTime) {
		this.dataTime = dataTime;
	}
	@Override
	public String toString() {
		return "MarketPrice [id=" + id + ", arrival_date=" + arrival_date + ", state=" + state + ", district="
				+ district + ", market=" + market + ", commodity=" + commodity + ", variety=" + variety + ", min_price="
				+ min_price + ", max_price=" + max_price + ", modal_price=" + modal_price + "]";
	}
	
	
}
