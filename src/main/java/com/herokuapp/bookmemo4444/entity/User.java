package com.herokuapp.bookmemo4444.entity;

public class User {
	private int userId;
	private String userName;
	private String userEmail;
	private String userPassword;
	private String rememberUser;
	public User() {		
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getRememberUser() {
		return rememberUser;
	}
	public void setRememberUser(String rememberUser) {
		this.rememberUser = rememberUser;
	}
	
}
