package com.herokuapp.bookmemo4444.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;

public class AccountRole implements Serializable{

	private static final long serialVersionUID = 1017903874115899722L;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Account account;
	@ManyToOne(cascade = CascadeType.ALL)
	private Role role;
}
