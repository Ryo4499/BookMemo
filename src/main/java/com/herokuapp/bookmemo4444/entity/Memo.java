package com.herokuapp.bookmemo4444.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "memos")
public class Memo implements Serializable {
	private static final long serialVersionUID = -6320154827659426912L;
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
	@JoinColumn(name = "account_id")
	private Account account;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date",nullable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createdDate;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_date",nullable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date updatedDate;

	public void now() {
		setUpdatedDate(new Date());
	}
}
