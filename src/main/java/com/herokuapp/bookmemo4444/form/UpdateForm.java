package com.herokuapp.bookmemo4444.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class UpdateForm {
	@NotNull
	@NotEmpty(message = "empty username")
	@Pattern(regexp = "^[a-zA-Z0-9]([a-zA-Z0-9]?|[\\-]?([a-zA-Z0-9])){2,30}$")
	private String accountName;

	@NotNull
	@NotEmpty(message = "empty email")
	@Pattern(regexp = "^[a-zA-Z0-9_.+-]+@([a-zA-Z0-9][a-zA-Z0-9-]*[a-zA-Z0-9]*\\.)+[a-zA-Z]{2,128}$ ")
	private String email;

	@NotNull
	@NotEmpty(message = "empty old password")
	@Pattern(regexp = "/^(?=.*?[a-z])(?=.*?\\d)[a-z\\d]{8,50}$/i")
	private String oldPassword;
	
	@NotNull
	@NotEmpty(message = "empty new password")
	@Pattern(regexp = "/^(?=.*?[a-z])(?=.*?\\d)[a-z\\d]{8,50}$/i")	
	private String newPassword;
	
	@NotNull
	@NotEmpty(message = "empty retype password")
	@Pattern(regexp = "/^(?=.*?[a-z])(?=.*?\\d)[a-z\\d]{8,50}$/i")	
	private String rePassword;
}
