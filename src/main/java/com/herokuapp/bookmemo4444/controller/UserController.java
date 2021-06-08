package com.herokuapp.bookmemo4444.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.herokuapp.bookmemo4444.form.AccountForm;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class UserController {

	@GetMapping("/")
	public String getTopPage() {
		// TODO Change toppage
		return "maintenance";
	}

	@GetMapping("/login")
	public String getLoginPage() {
		log.debug("login");
		return "user/login";
	}

	@GetMapping("/signup")
	public String getSignupPage(Model model) {
		model.addAttribute("", new AccountForm());
		return "user/signup";
	}

	@PostMapping("/signup")
	public String postSignup(@Validated AccountForm accountForm, BindingResult result, Model model) {
		return "";
	}
	// @GetMapping("/profile")
	// TODO public String getProfile(SignupForm signupForm, Model model) {
	// @PostMapping("/profile/update")
	// TODO public String postProfile(@Validated SignupForm signupForm,
	// BindingResult result, Model model,
	// @GetMapping("/delete")
	// TODO public String deleteUser(@Validated SignupForm signupForm, BindingResult
	// result, Model model) {
	// TODO private User makeUser(SignupForm signupForm, Long userId, String
	// sessionId) {
}
