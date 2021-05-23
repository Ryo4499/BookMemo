package com.herokuapp.bookmemo4444.memo;

import javax.validation.constraints.Pattern;

import lombok.NonNull;

public class MemoForm {
	private int memoId;
	@NonNull
	@Pattern(regexp = "^[a-zA-Z0-9]([a-zA-Z0-9]?|[\\\\-]?([a-zA-Z0-9])){1,29}$")
	private String title;
	@NonNull
	private String content;
	@NonNull
	@Pattern(regexp = "^[a-zA-Z0-9]([a-zA-Z0-9]?|[\\\\-]?([a-zA-Z0-9])){1,29}$")
	private String category;
	@NonNull
	@Pattern(regexp = "^[a-zA-Z0-9]([a-zA-Z0-9]?|[\\\\-]?([a-zA-Z0-9])){1,29}$")
	private String bookName;

	private int userId;

	public MemoForm() {
	}

	public MemoForm(int memoId, @NonNull String title, @NonNull String content, @NonNull String category,
			@NonNull String bookName, int userId) {
		super();
		this.memoId = memoId;
		this.title = title;
		this.content = content;
		this.category = category;
		this.bookName = bookName;
		this.userId = userId;
	}

	public int getMemoId() {
		return memoId;
	}

	public void setMemoId(int memoId) {
		this.memoId = memoId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bookName == null) ? 0 : bookName.hashCode());
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + memoId;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + userId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MemoForm other = (MemoForm) obj;
		if (bookName == null) {
			if (other.bookName != null)
				return false;
		} else if (!bookName.equals(other.bookName))
			return false;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (memoId != other.memoId)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MemoForm [memoId=" + memoId + ", title=" + title + ", content=" + content + ", category=" + category
				+ ", bookName=" + bookName + ", userId=" + userId + "]";
	}

}
