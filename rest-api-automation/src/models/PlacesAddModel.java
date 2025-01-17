package models;

import java.util.List;
import java.util.Map;

public class PlacesAddModel {
	
	/**{
		"location": {
			"lat" : -38.383494,
			"lng" : 33.427362
		},
		
		"accuracy" : 50,
		"name" : "Frontline House",
		"phone_number" : "(+1) 757-846-6162",
		"address" : "1234 Main Street, Hampton, VA, 23664",
		"types" : ["Shoe Park", "Shop"],
		"website" : "http://google.com",
		"language" : "French-IN"
	}**/

	private Map<String, Double> location;
	private int accuracy;
	private String name;
	private String phone_number;
	private String address;
	private List<String> types;
	private String website;
	private String language;
	
	public Map<String, Double> getLocation() {
		return location;
	}
	public void setLocation(Map<String, Double> location) {
		this.location = location;
	}
	public int getAccuracy() {
		return accuracy;
	}
	public void setAccuracy(int accuracy) {
		this.accuracy = accuracy;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public List<String> getTypes() {
		return types;
	}
	public void setTypes(List<String> types) {
		this.types = types;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
}
