package com.herokuapp.bookmemo4444.security;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class GenerateBcrypt {

	@Test
	public void encrypt_password() {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		String encodedpassword = passwordEncoder.encode("aaaaaaaa");

		System.out.println(encodedpassword);
	}

}
