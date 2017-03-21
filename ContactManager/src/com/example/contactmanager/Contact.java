package com.example.contactmanager;

public class Contact {
	int id;
	String username;
	String emailid;
	String phoneNumber;
	public Contact(){}
	public Contact(String username, String emailid, String phoneNumber) {
		super();
		this.username = username;
		this.emailid = emailid;
		this.phoneNumber = phoneNumber;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	

}
