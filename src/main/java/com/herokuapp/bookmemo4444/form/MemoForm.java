package com.herokuapp.bookmemo4444.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Data;
import lombok.NonNull;

@Data
public class MemoForm {
	private String memoId;
	@NotNull
	@NotEmpty
	@Pattern(regexp = "^[a-zA-Z0-9]([a-zA-Z0-9]?|[\\\\-]?([a-zA-Z0-9])){1,30}$")
	private String title;
	@NotNull
	@NotEmpty
	private String content;
	@NonNull
	@NotEmpty
	@Pattern(regexp = "^[a-zA-Z0-9]([a-zA-Z0-9]?|[\\\\-]?([a-zA-Z0-9])){1,30}$")
	private String category;
	@NonNull
	@NotEmpty
	@Pattern(regexp = "^[a-zA-Z0-9]([a-zA-Z0-9]?|[\\\\-]?([a-zA-Z0-9])){1,30}$")
	private String bookName;
}
