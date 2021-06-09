package com.herokuapp.bookmemo4444.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NonNull;

@Data
public class MemoForm {
	private String memoId;

	@NotEmpty(message = "Title is empty.")
	@Size(min = 1, max = 30, message = "At least 1 characters and up to 30 characters")
	@Pattern(regexp = "^[a-zA-Z0-9]([a-zA-Z0-9]?|[\\\\-]?([a-zA-Z0-9])){1,30}$")
	private String title;

	@NotEmpty(message = "Content is empty.")
	@Size(min = 1, max = 3000, message = "At least 1 characters and up to 3000 characters")
	private String content;

	@NotEmpty(message = "Category is empty.")
	@Size(min = 1, max = 30, message = "At least 1 characters and up to 30 characters")
	@Pattern(regexp = "^[a-zA-Z0-9]([a-zA-Z0-9]?|[\\\\-]?([a-zA-Z0-9])){1,30}$")
	private String category;

	@NotEmpty(message = "Book name is empty.")
	@Size(min = 1, max = 30, message = "At least 1 characters and up to 30 characters")
	@Pattern(regexp = "^[a-zA-Z0-9]([a-zA-Z0-9]?|[\\\\-]?([a-zA-Z0-9])){1,30}$")
	private String bookName;
}
