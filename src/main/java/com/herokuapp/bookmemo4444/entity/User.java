package com.herokuapp.bookmemo4444.entity;

public class User {
	private int userId;
	private String userName;
	private String userEmail;
	private String userPassword;
	private String rememberUser;

	public User() {
	}

	public User(int userId) {
		this.userId = userId;
	}

	public User(int userId, String userName, String userEmail, String userPassword, String rememberUser) {
		this.userId = userId;
		this.userName = userName;
		this.userEmail = userEmail;
		this.userPassword = userPassword;
		this.rememberUser = rememberUser;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (rememberUser == null ? 0 : rememberUser.hashCode());
		result = prime * result + (userEmail == null ? 0 : userEmail.hashCode());
		result = prime * result + userId;
		result = prime * result + (userName == null ? 0 : userName.hashCode());
		result = prime * result + (userPassword == null ? 0 : userPassword.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		User other = (User) obj;
		if (rememberUser == null) {
			if (other.rememberUser != null) {
				return false;
			}
		} else if (!rememberUser.equals(other.rememberUser)) {
			return false;
		}
		if (userEmail == null) {
			if (other.userEmail != null) {
				return false;
			}
		} else if (!userEmail.equals(other.userEmail)) {
			return false;
		}
		if (userId != other.userId) {
			return false;
		}
		if (userName == null) {
			if (other.userName != null) {
				return false;
			}
		} else if (!userName.equals(other.userName)) {
			return false;
		}
		if (userPassword == null) {
			if (other.userPassword != null) {
				return false;
			}
		} else if (!userPassword.equals(other.userPassword)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", userEmail=" + userEmail + ", userPassword="
				+ userPassword + ", rememberUser=" + rememberUser + "]";
	}

}
