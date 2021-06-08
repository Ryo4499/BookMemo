package com.herokuapp.bookmemo4444.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.herokuapp.bookmemo4444.entity.Account;
import com.herokuapp.bookmemo4444.entity.Role;
import com.herokuapp.bookmemo4444.form.SignupForm;
import com.herokuapp.bookmemo4444.form.UpdateForm;
import com.herokuapp.bookmemo4444.repository.AccountRepository;
import com.herokuapp.bookmemo4444.repository.RoleRepository;
import com.herokuapp.bookmemo4444.security.CustomSecurityAccount;

@Controller
public class AccountController {

	private final AccountRepository accountRepository;

	private final RoleRepository roleRepository;

	private final PasswordEncoder passwordEncoder;

	@Autowired
	public AccountController(AccountRepository accountRepository, RoleRepository roleRepository,
			PasswordEncoder passwordEncoder) {
		this.accountRepository = accountRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@GetMapping("/")
	public String getTopPage() {
		return "top-page";
	}

	@GetMapping("/login")
	public String getLoginPage() {
		return "account/login";
	}

	@GetMapping("/signup")
	public String getSignupPage(Model model) {
		model.addAttribute("signupForm", new SignupForm());
		return "account/signup";
	}

	@PostMapping("/signup")
	public String postSignup(@Validated SignupForm signupForm, BindingResult result, Model model) {
		// TODO createAc
		signupForm.setPassword(passwordEncoder.encode(signupForm.getPassword()));
		if (!passwordEncoder.matches(signupForm.getRePassword(), signupForm.getPassword()) || result.hasErrors()) {
			signupForm.setPassword("");
			signupForm.setRePassword("");
			model.addAttribute("signupForm", signupForm);
			return "account/signup";
		}
		Account account = makeAccountSignUpForm(signupForm);
		accountRepository.save(account);
		return "account/signup-success";
	}

	@GetMapping("/profile")
	public String getProfile(@AuthenticationPrincipal CustomSecurityAccount customSecurityAccount, Model model) {
		// TODO findbyidAc
		UpdateForm updateForm = new UpdateForm();
		updateForm.setAccountName(customSecurityAccount.getAccountName());
		updateForm.setEmail(customSecurityAccount.getAccountEmail());
		updateForm.setOldPassword("");
		updateForm.setNewPassword("");
		updateForm.setRePassword("");
		model.addAttribute("updateForm", updateForm);
		return "account/profile";
	}

	@PostMapping("/profile/update")
	public String postProfile(@Validated UpdateForm updateForm,
			@AuthenticationPrincipal CustomSecurityAccount customSecurityAccount, BindingResult result, Model model) {
		updateForm.setNewPassword(passwordEncoder.encode(updateForm.getNewPassword()));
		if (!passwordEncoder.matches(updateForm.getOldPassword(), customSecurityAccount.getPassword())
				|| !passwordEncoder.matches(updateForm.getRePassword(), updateForm.getNewPassword())
				|| result.hasErrors()) {
			updateForm.setOldPassword("");
			updateForm.setNewPassword("");
			updateForm.setRePassword("");
			model.addAttribute("updateForm", updateForm);
			return "account/profile";
		}
		// TODO save
		Account account = makeAccountUpdateForm(updateForm);
		account.setMemos(customSecurityAccount.getMemos());
		account.setRoles(customSecurityAccount.getRoles());
		accountRepository.save(account);
		return "account/profile";
	}

	@GetMapping("/delete")
	public String getDeletePage(@AuthenticationPrincipal CustomSecurityAccount customSecurityAccount, Model model) {
		model.addAttribute("accountName", customSecurityAccount.getAccountName());
		model.addAttribute("email", customSecurityAccount.getAccountEmail());
		return "account/delete-confirm";
	}

	@PostMapping("/delete")
	public String postdeleteAccount(@AuthenticationPrincipal CustomSecurityAccount customSecurityAccount) {
		Account account = new Account();
		account.setId(customSecurityAccount.getId());
		account.setAccountEmail(customSecurityAccount.getAccountEmail());
		account.setAccountName(customSecurityAccount.getAccountName());
		account.setPassword(customSecurityAccount.getPassword());
		accountRepository.delete(account);
		return "redirect:/";
	}

	private Account makeAccountSignUpForm(SignupForm signupForm) {
		Account account = new Account();
		account.setAccountName(signupForm.getAccountName());
		account.setAccountEmail(signupForm.getEmail());
		account.setPassword(signupForm.getPassword());
		Role role = roleRepository.findByAuthority("ROLE_USER");
		Set<Role> roles = new HashSet<>();
		roles.add(role);
		account.setRoles(roles);
		return account;
	}

	private Account makeAccountUpdateForm(UpdateForm updateForm) {
		Account account = new Account();
		account.setAccountName(updateForm.getAccountName());
		account.setAccountEmail(updateForm.getEmail());
		account.setPassword(updateForm.getNewPassword());
		return account;
	}
}
