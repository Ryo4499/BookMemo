package com.herokuapp.bookmemo4444.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.herokuapp.bookmemo4444.form.loginForm;
import com.herokuapp.bookmemo4444.form.signupForm;

@Controller
public class UserController {
	private final JdbcTemplate jdbcTemplate;
	
	@Autowired
	public UserController(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate=jdbcTemplate;
	}
	
	@GetMapping("/login")
	public String getLoginPage(loginForm loginForm,Model model) {
		return "user/login";
	}
	
	@GetMapping("/signup")
	public String getSignupPage(signupForm signupForm,Model model) {
		return "user/signup";
	}
	
	
	@PostMapping("/login")
	public String doLogin(@Validated loginForm loginForm,BindingResult result,Model model,RedirectAttributes redirectAttributes) {
		if(result.hasErrors()) {
			model.addAttribute("title","Login");
			return "/login";
		}
		redirectAttributes.addFlashAttribute("complete","Login!!!");
		return "redirect:memo-list";
	}
	
	@PostMapping("/signup")
	public String doSignup(@Validated signupForm signupForm,BindingResult result,Model model,RedirectAttributes redirectAttributes) {
		if(result.hasErrors()) {
			model.addAttribute("title","Signup");
			return "/signup";
		}
		redirectAttributes.addFlashAttribute("complete","Regester!");
		return "redirect:memo-list";
	}
}
