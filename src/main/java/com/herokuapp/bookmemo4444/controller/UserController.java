package com.herokuapp.bookmemo4444.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

	@GetMapping("/")
	public String getTopPage() {
		// TODO Change toppage
		return "maintenance";
	}

	@GetMapping("/login")
	public String getLoginPage() {
		return "user/login";
	}

	// @GetMapping("/signup")
	// TODO public String getSignupPage(SignupForm signupForm, Model model) {

	// @PostMapping("/signup")
	// TODO public String postSignup(@Validated SignupForm signupForm, BindingResult
	// result, Model model,RedirectAttributes redirectAttributes) {

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
