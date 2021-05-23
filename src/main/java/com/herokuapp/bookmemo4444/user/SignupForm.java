package com.herokuapp.bookmemo4444.user;

import javax.validation.constraints.Pattern;

import lombok.NonNull;

public class SignupForm {
	@NonNull
	@Pattern(regexp = "^[a-zA-Z0-9]([a-zA-Z0-9]?|[\\-]?([a-zA-Z0-9])){1,29}$")
	private String userName;
	@NonNull
	@Pattern(regexp = "^[a-zA-Z0-9_.+-]+@([a-zA-Z0-9][a-zA-Z0-9-]*[a-zA-Z0-9]*\\.)+[a-zA-Z]{2,127}$ ")
	private String email;
	@NonNull
	@Pattern(regexp = "/^(?=.*?[a-z])(?=.*?\\d)[a-z\\d]{8,50}$/i")
	private String password;

	public SignupForm() {
	}

	public SignupForm(@NonNull String userName, @NonNull String email, @NonNull String password) {
		super();
		this.userName = userName;
		this.email = email;
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (email == null ? 0 : email.hashCode());
		result = prime * result + (password == null ? 0 : password.hashCode());
		result = prime * result + (userName == null ? 0 : userName.hashCode());
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
		SignupForm other = (SignupForm) obj;
		if (email == null) {
			if (other.email != null) {
				return false;
			}
		} else if (!email.equals(other.email)) {
			return false;
		}
		if (password == null) {
			if (other.password != null) {
				return false;
			}
		} else if (!password.equals(other.password)) {
			return false;
		}
		if (userName == null) {
			if (other.userName != null) {
				return false;
			}
		} else if (!userName.equals(other.userName)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "SignupForm [userName=" + userName + ", email=" + email + ", password=" + password + "]";
	}

}
