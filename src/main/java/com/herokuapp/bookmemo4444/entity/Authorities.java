package com.herokuapp.bookmemo4444.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name = "authorities")
public class Authorities implements GrantedAuthority {
	private static final long serialVersionUID = -9193227690732390344L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "authority", length = 10)
	private String authority;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
}
