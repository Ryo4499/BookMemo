package com.herokuapp.bookmemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BookMemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(BookMemoApplication.class, args);
  }

  @Bean
  public ServletContextInitializer servletContextInitializer() {
    return servletContext -> servletContext.getSessionCookieConfig().setName("bookmemo.session");
  }

}
