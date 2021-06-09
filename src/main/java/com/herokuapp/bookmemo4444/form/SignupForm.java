package com.herokuapp.bookmemo4444.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NonNull;

@Data
public class SignupForm {

	@NotEmpty(message = "Username is empty")
	@Size(min = 2, max = 30, message = "At least 2 characters and up to 30 characters")
	@Pattern(regexp = "^[a-zA-Z0-9]([a-zA-Z0-9]?|[\\-]?([a-zA-Z0-9])){2,30}$")
	private String accountName;

	@NotEmpty(message = "Email is empty")
	@Size(min = 2, max = 128, message = "At least 2 characters and up to 128 characters")
	@Pattern(regexp = "^[a-zA-Z0-9_.+-]+@([a-zA-Z0-9][a-zA-Z0-9-]*[a-zA-Z0-9]*\\.)+[a-zA-Z]{2,128}$ ")
	private String email;

	@NotEmpty(message = "Password is empty")
	@Size(min = 8, max = 50, message = "At least 8 characters and up to 50 characters")
	@Pattern(regexp = "/^(?=.*?[a-z])(?=.*?\\d)[a-z\\d]{8,50}$/i")
	private String password;

	@NotEmpty(message = "Confirm password is empty")
	@Size(min = 8, max = 50, message = "At least 8 characters and up to 50 characters")
	@Pattern(regexp = "/^(?=.*?[a-z])(?=.*?\\d)[a-z\\d]{8,50}$/i")
	private String rePassword;

	public void resetPassword() {
		this.password = "";
		this.rePassword = "";
	}
}
