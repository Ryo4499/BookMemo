package com.herokuapp.bookmemo4444.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.herokuapp.bookmemo4444.entity.Account;
import com.herokuapp.bookmemo4444.entity.Role;
import com.herokuapp.bookmemo4444.repository.AccountRepository;
import com.herokuapp.bookmemo4444.security.CustomSecurityAccount;

@Transactional(readOnly = true)
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	private AccountRepository accountRepository;

	public UserDetailsServiceImpl(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Account account = accountRepository.findByEmail(email);
		if (account == null) {
			throw new UsernameNotFoundException("Username and or password was incorrect.");
		}
		System.out.println(account.getId());
		System.out.println(account.getEmail());
		account.getRoles().forEach(set -> {
			System.out.println(set.getId() + " " + set.getAuthority() + " " + set.getAccounts());
		});

		return new CustomSecurityAccount(account, getAuthorities(account));

	}
	
	private Set<GrantedAuthority> getAuthorities(Account account) {
		Set<GrantedAuthority> authorities = new HashSet<>();
		for (Role authrity : account.getRoles()) {
			GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(authrity.getAuthority());
			authorities.add(grantedAuthority);
		}
		return authorities;
	}

}
