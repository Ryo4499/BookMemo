package xyz.bookmemo.security;

import java.net.URLDecoder;
import javax.sql.DataSource;
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
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import xyz.bookmemo.repository.AccountRepository;
import xyz.bookmemo.service.UserDetailsServiceImpl;

/**
 * "This class configures the security of the application."
 *
 * The @EnableWebSecurity annotation enables Spring Security's web security support and provides the
 * Spring MVC integration. It also extends the WebSecurityConfigurerAdapter class and overrides a
 * couple of its methods to set some specifics of the web security configuration
 */
@EnableWebSecurity(debug = false)
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  private final AccountRepository accountRepository;
  private final DataSource dataSource;

  @Autowired
  SecurityConfiguration(
    AccountRepository accountRepository,
    DataSource dataSource
  ) {
    this.accountRepository = accountRepository;
    this.dataSource = dataSource;
  }

  public PersistentTokenRepository createTokenRepository() {
    JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
    tokenRepository.setDataSource(dataSource);
    return tokenRepository;
  }

  /**
   * It returns a new instance of BCryptPasswordEncoder.
   *
   * @return A PasswordEncoder object.
   */
  @Bean
  public PasswordEncoder getPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }

  /**
   * This function returns a new instance of the URLDecoder class.
   *
   * @return A new instance of the URLDecoder class.
   */
  @Bean
  public URLDecoder getUrlDecoder() {
    return new URLDecoder();
  }

  /**
   * This function creates a new DaoAuthenticationProvider object, sets the userDetailsService to the
   * userDetailsService() function, and sets the passwordEncoder to the getPasswordEncoder() function
   *
   * @return A DaoAuthenticationProvider object.
   */
  @Bean
  public DaoAuthenticationProvider authenticationProvider() {
    final DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
    authenticationProvider.setUserDetailsService(userDetailsService());
    authenticationProvider.setPasswordEncoder(getPasswordEncoder());
    return authenticationProvider;
  }

  /**
   * "This function returns a UserDetailsService object that is used to authenticate users."
   *
   * The UserDetailsService interface is a Spring Security interface that is used to retrieve
   * user-related data. It has one method, loadUserByUsername(), which finds a user entity based on the
   * username and returns a UserDetails object that Spring Security can use for authentication and
   * authorization
   *
   * @return A UserDetailsService object.
   */
  @Override
  public UserDetailsService userDetailsServiceBean() throws Exception {
    return new UserDetailsServiceImpl(accountRepository);
  }

  /**
   * "Configure the AuthenticationManagerBuilder with a UserDetailsService and a PasswordEncoder."
   *
   * The AuthenticationManagerBuilder is a helper class that allows easy creation of an
   * AuthenticationManager
   *
   * @param auth This is the authentication manager builder.
   */
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth
      .userDetailsService(userDetailsServiceBean())
      .passwordEncoder(getPasswordEncoder());
  }

  /**
   * Ignore all requests that start with /images/, /js/, or /css/
   *
   * @param web This is the WebSecurity object.
   */
  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers("/images/**", "/js/**", "/css/**");
  }

  /**
   * This function configures the security of the application
   *
   * @param http This is the main object that is used to configure the security of the application.
   */
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
      .authorizeRequests()
      .antMatchers("/", "/signup", "/signupsuccess", "/thanks")
      .permitAll()
      .antMatchers("/admin**/**")
      .hasRole("ADMIN")
      .antMatchers("/memo**/**")
      .hasAnyRole("ADMIN", "USER")
      .anyRequest()
      .authenticated();
    http
      .formLogin()
      .loginPage("/login")
      .defaultSuccessUrl("/memo", true)
      .usernameParameter("email")
      .passwordParameter("password")
      .permitAll()
      .and()
      .rememberMe()
      .tokenRepository(createTokenRepository())
      .useSecureCookie(true)
      .key("uniqueAndSecret")
      .userDetailsService(userDetailsServiceBean());
    http
      .logout()
      .logoutUrl("/logout")
      .invalidateHttpSession(true)
      .deleteCookies("JSESSIONID", "SESSION", "remember-me")
      .logoutSuccessUrl("/")
      .permitAll();
    http
      .sessionManagement()
      .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
      .sessionFixation()
      .changeSessionId()
      .maximumSessions(1)
      .maxSessionsPreventsLogin(false);

    http.headers().frameOptions().disable();
  }
}
