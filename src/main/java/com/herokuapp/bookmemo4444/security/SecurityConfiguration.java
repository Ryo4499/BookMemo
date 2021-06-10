package com.herokuapp.bookmemo4444.security;

import java.net.URLDecoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.herokuapp.bookmemo4444.repository.AccountRepository;
import com.herokuapp.bookmemo4444.service.UserDetailsServiceImpl;

@EnableWebSecurity(debug = true)
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired
	private AccountRepository accountRepository;

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public URLDecoder getUrlDecoder() {
		return new URLDecoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService());
		authenticationProvider.setPasswordEncoder(getPasswordEncoder());
		return authenticationProvider;
	}

	@Override
	public UserDetailsService userDetailsServiceBean() throws Exception {
		return new UserDetailsServiceImpl(accountRepository);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsServiceBean()).passwordEncoder(getPasswordEncoder());
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/images/**", "/js/**", "/css/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/", "/signup", "/signupsuccess", "/thanks").permitAll()
				.antMatchers("/admin**/**").hasRole("ADMIN").antMatchers("/memo**/**").hasAnyRole("ADMIN", "USER")
				.anyRequest().authenticated();
		http.formLogin().loginPage("/login").defaultSuccessUrl("/memo", true).usernameParameter("email")
				.passwordParameter("password").permitAll().and().rememberMe().key("uniqueAndSecret")
				.userDetailsService(userDetailsServiceBean());
		http.logout().logoutUrl("/logout").invalidateHttpSession(true)
				.deleteCookies("JSESSIONID", "SESSION", "remember-me").logoutSuccessUrl("/").permitAll();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED).sessionFixation()
				.changeSessionId().maximumSessions(1).maxSessionsPreventsLogin(false);
		http.headers().frameOptions().disable();

	}

}
