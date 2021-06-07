package com.herokuapp.bookmemo4444.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name = "memos")
public class Memo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "memo_id")
	private Long memoId;
	@Column(name = "title", length = 30, nullable = false)
	private String title;
	@Column(name = "content", length = 3000, nullable = false)
	private String content;
	@Column(name = "category", length = 30, nullable = false)
	private String category;
	@Column(name = "book_name", length = 30, nullable = false)
	private String bookName;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	@Column(name = "created_date", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private String createdDate;
	@Column(name = "updated_date", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private String updatedDate;

}
