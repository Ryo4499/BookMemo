package com.herokuapp.bookmemo4444;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@Controller
public class BookMemo4444Application {

	public static void main(String[] args) {
		SpringApplication.run(BookMemo4444Application.class, args);
	}

	@GetMapping("/")
	public String getTopPage() {
		return "top-page";
	}
}
