package com.herokuapp.bookmemo4444.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.herokuapp.bookmemo4444.entity.User;
import com.herokuapp.bookmemo4444.repository.UserRepository;
import com.herokuapp.bookmemo4444.security.SecurityUser;

public class UserDetailsServiceImpl implements UserDetailsService {
	private UserRepository userRepository;

	@Autowired
	UserDetailsServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByUserEmail(email);

		if (user == null) {
			throw new UsernameNotFoundException("Username and or password was incorrect.");
		}

		return new SecurityUser(user);
	}
}
