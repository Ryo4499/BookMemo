package com.herokuapp.bookmemo4444.memo;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.NonNull;

public class MemoForm {
	private String memoId;
	@NotNull
	@NotEmpty(message = "empty title")
	@Pattern(regexp = "^[a-zA-Z0-9]([a-zA-Z0-9]?|[\\\\-]?([a-zA-Z0-9])){1,30}$")
	private String title;

	@NotNull
	@NotEmpty(message = "empty content")
	private String content;

	@NotNull
	@NotEmpty(message = "empty category")
	@Pattern(regexp = "^[a-zA-Z0-9]([a-zA-Z0-9]?|[\\\\-]?([a-zA-Z0-9])){1,30}$")
	private String category;

	@NotNull
	@NotEmpty(message = "empty book name")
	@Pattern(regexp = "^[a-zA-Z0-9]([a-zA-Z0-9]?|[\\\\-]?([a-zA-Z0-9])){1,30}$")
	private String bookName;

	private String userId;

	public MemoForm() {
	}

	public MemoForm(String memoId, @NonNull String title, @NonNull String content, @NonNull String category,
			@NonNull String bookName, String userId) {
		super();
		this.memoId = memoId;
		this.title = title;
		this.content = content;
		this.category = category;
		this.bookName = bookName;
		this.userId = userId;
	}

	public String getMemoId() {
		return memoId;
	}

	public void setMemoId(String memoId) {
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (bookName == null ? 0 : bookName.hashCode());
		result = prime * result + (category == null ? 0 : category.hashCode());
		result = prime * result + (content == null ? 0 : content.hashCode());
		result = prime * result + (memoId == null ? 0 : memoId.hashCode());
		result = prime * result + (title == null ? 0 : title.hashCode());
		result = prime * result + (userId == null ? 0 : userId.hashCode());
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
		MemoForm other = (MemoForm) obj;
		if (bookName == null) {
			if (other.bookName != null) {
				return false;
			}
		} else if (!bookName.equals(other.bookName)) {
			return false;
		}
		if (category == null) {
			if (other.category != null) {
				return false;
			}
		} else if (!category.equals(other.category)) {
			return false;
		}
		if (content == null) {
			if (other.content != null) {
				return false;
			}
		} else if (!content.equals(other.content)) {
			return false;
		}
		if (memoId == null) {
			if (other.memoId != null) {
				return false;
			}
		} else if (!memoId.equals(other.memoId)) {
			return false;
		}
		if (title == null) {
			if (other.title != null) {
				return false;
			}
		} else if (!title.equals(other.title)) {
			return false;
		}
		if (userId == null) {
			if (other.userId != null) {
				return false;
			}
		} else if (!userId.equals(other.userId)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "MemoForm [memoId=" + memoId + ", title=" + title + ", content=" + content + ", category=" + category
				+ ", bookName=" + bookName + ", userId=" + userId + "]";
	}

}
