package com.herokuapp.bookmemo4444;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BookMemo4444Application {

	public static void main(String[] args) {
		SpringApplication.run(BookMemo4444Application.class, args);
	}
	
	@Bean
	public ServletContextInitializer servletContextInitializer() {
		return servletContext -> servletContext.getSessionCookieConfig().setName("bookmemo.session");
	}

}
