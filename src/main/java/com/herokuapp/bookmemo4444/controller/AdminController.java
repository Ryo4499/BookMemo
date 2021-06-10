package com.herokuapp.bookmemo4444.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.herokuapp.bookmemo4444.entity.Account;
import com.herokuapp.bookmemo4444.repository.AccountRepository;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {
	private final AccountRepository accountRepository;

	@GetMapping("")
	public String getAdminPage() {
		List<Account> accounts = accountRepository.findAll();
		accounts.forEach(account -> {
			System.out.println(account.getAccountName());
			System.out.println(account.getEmail());
			System.out.println(account.getPassword());
			System.out.println(account.getRoles());
		});
		return "maintenance";
	}
}
