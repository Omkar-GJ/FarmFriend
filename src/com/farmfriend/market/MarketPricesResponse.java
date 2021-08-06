package com.farmfriend.market;

import java.util.Date;
import java.util.List;

public class MarketPricesResponse {
//All variables here are same as JSON key only
	String index_name;
	String title;
	String  desc;
	String org_type;
	List<String> org;
	List<String> sector;
	String source;
	String catalog_uuid;
	String visualizable;
	String active;
	int created;
	int updated;
	Date created_date;
	Date updated_date;
	String message;
	String version;
	String status;
	int total;
	int count;
	String limit;
	String offset;
	Target_bucket target_bucket;
	List<Field> field;
	List<Records> records;
	public String getIndex_name() {
		return index_name;
	}
	public void setIndex_name(String index_name) {
		this.index_name = index_name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getOrg_type() {
		return org_type;
	}
	public void setOrg_type(String org_type) {
		this.org_type = org_type;
	}
	public List<String> getOrg() {
		return org;
	}
	public void setOrg(List<String> org) {
		this.org = org;
	}
	public List<String> getSector() {
		return sector;
	}
	public void setSector(List<String> sector) {
		this.sector = sector;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getCatalog_uuid() {
		return catalog_uuid;
	}
	public void setCatalog_uuid(String catalog_uuid) {
		this.catalog_uuid = catalog_uuid;
	}
	public String getVisualizable() {
		return visualizable;
	}
	public void setVisualizable(String visualizable) {
		this.visualizable = visualizable;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	public int getCreated() {
		return created;
	}
	public void setCreated(int created) {
		this.created = created;
	}
	public int getUpdated() {
		return updated;
	}
	public void setUpdated(int updated) {
		this.updated = updated;
	}
	public Date getCreated_date() {
		return created_date;
	}
	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}
	public Date getUpdated_date() {
		return updated_date;
	}
	public void setUpdated_date(Date updated_date) {
		this.updated_date = updated_date;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getLimit() {
		return limit;
	}
	public void setLimit(String limit) {
		this.limit = limit;
	}
	public String getOffset() {
		return offset;
	}
	public void setOffset(String offset) {
		this.offset = offset;
	}
	public Target_bucket getTarget_bucket() {
		return target_bucket;
	}
	public void setTarget_bucket(Target_bucket target_bucket) {
		this.target_bucket = target_bucket;
	}
	public List<Field> getField() {
		return field;
	}
	public void setField(List<Field> field) {
		this.field = field;
	}
	public List<Records> getRecords() {
		return records;
	}
	public void setRecords(List<Records> records) {
		this.records = records;
	}
	
	
}        
class Target_bucket{
		 String index;
		 String type;
		 String field;
	}

class Field{
		String id;
	    String name;
	    String type;      
	}

class Records{
	
	           String timestamp;
	            String state;
	            String district;
	            String market;
	            String commodity;
	            String variety;
	            String arrival_date;
	            String min_price;
	            String max_price;
	            String modal_price;
				@Override
				public String toString() {
					return "Records [timestamp=" + timestamp + ", state=" + state + ", district=" + district
							+ ", market=" + market + ", commodity=" + commodity + ", variety=" + variety
							+ ", arrival_date=" + arrival_date + ", min_price=" + min_price + ", max_price=" + max_price
							+ ", modal_price=" + modal_price + "]";
				}  
	            
	            
}
