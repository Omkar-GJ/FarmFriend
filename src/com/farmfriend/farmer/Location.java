package com.farmfriend.farmer;

public class Location {

	private int id;
	private String village;
	private String taluka;
	private String district;
	private int pincode;
	
	public String getVillage() {
		return village;
	}
	public void setVillage(String village) {
		this.village = village;
	}
	public String getTaluka() {
		return taluka;
	}
	public void setTaluka(String taluka) {
		this.taluka = taluka;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public int getPincode() {
		return pincode;
	}
	public void setPincode(int pincode) {
		this.pincode = pincode;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Location(String village, String taluka, String district, int pincode) {
		super();
		this.village = village;
		this.taluka = taluka;
		this.district = district;
		this.pincode = pincode;
	}
	public Location() {
		super();
	}
	
	
}
