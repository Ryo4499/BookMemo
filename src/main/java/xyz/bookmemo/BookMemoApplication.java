package xyz.bookmemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;

/** It's a Spring Boot application that sets the session cookie name to "bookmemo.session" */
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
