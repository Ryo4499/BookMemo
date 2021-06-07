package com.herokuapp.bookmemo4444.security;

import org.springframework.security.core.userdetails.UserDetails;

import com.herokuapp.bookmemo4444.entity.User;

public class SecurityUser extends User implements UserDetails {

	private static final long serialVersionUID = 4171675099225880544L;

	public SecurityUser() {
	}

	public SecurityUser(User user) {
		setAuthorities(user.getAuthorities());
		setUserId(user.getUserId());
		setUserEmail(user.getUserEmail());
		setUserPassword(user.getUserPassword());
		setMemos(user.getMemos());
	}

	@Override
	public boolean isAccountNonExpired() {

		return true;
	}

	@Override
	public boolean isAccountNonLocked() {

		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {

		return true;
	}

	@Override
	public boolean isEnabled() {

		return true;
	}

	@Override
	public String getPassword() {
		return getPassword();
	}

	@Override
	public String getUsername() {
		return getUserEmail();
	}

}
