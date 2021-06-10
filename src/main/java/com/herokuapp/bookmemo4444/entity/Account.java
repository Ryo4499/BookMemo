package com.herokuapp.bookmemo4444.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "accounts")
public class Account implements Serializable {
	private static final long serialVersionUID = -9213765435734751506L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "account_id", nullable = false)
	private long id;
	@Column(name = "account_name", unique = true, nullable = false)
	private String accountName;
	@Column(name = "account_email", unique = true, nullable = false)
	private String email;
	@Column(name = "account_password", nullable = false)
	private String password;
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "account")
	private List<Memo> memos;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "account_role", joinColumns = @JoinColumn(name = "account_id", referencedColumnName = "account_id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
	private Set<Role> roles;

}
