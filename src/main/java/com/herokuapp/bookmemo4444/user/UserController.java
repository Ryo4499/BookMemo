package com.herokuapp.bookmemo4444.user;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.herokuapp.bookmemo4444.entity.User;
import com.herokuapp.bookmemo4444.service.UserService;

@Controller
public class UserController {

	@GetMapping("/")
	public String getTopPage() {
		// TODO Change toppage
		return "top-page";
	}

	// @GetMapping("/login")
	// TODO public String getLoginPage() {

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

	//@GetMapping("/delete")
	//TODO	public String deleteUser(@Validated SignupForm signupForm, BindingResult result, Model model) {
	
	//TODO	private User makeUser(SignupForm signupForm, Long userId, String sessionId) {
}
