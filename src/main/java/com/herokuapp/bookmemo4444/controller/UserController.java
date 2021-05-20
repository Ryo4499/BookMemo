package com.herokuapp.bookmemo4444.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
	private final JdbcTemplate jdbcTemplate;
	
	@Autowired
	public UserController(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate=jdbcTemplate;
	}
	
	@GetMapping("/login")
	public String getLogin(Model model) {
		model.addAttribute("");
		return "login";
	}
}
