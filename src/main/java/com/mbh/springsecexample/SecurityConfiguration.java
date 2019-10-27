/**
 * 
 */
package com.mbh.springsecexample;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Class that activates Spring Security. In this class the configuration is override.
 * 
 * @author amineboufatah
 *
 */
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  /** Admin ROLE **/
  private static final String ROLE_ADMIN = "ADMIN";

  /** User ROLE **/
  private static final String ROLE_USER = "USER";

  /**
   * Configure the Authentication mechanism. We use an in memory authentication.
   */
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication() //
        .withUser("user").password("user").roles(ROLE_USER)//
        .and()//
        .withUser("admin").password("admin").roles(ROLE_ADMIN);
  }

  /**
   * For simplicity sake, we use a NoOpPasswordEncoder.
   * 
   * @return
   */
  @Bean
  public PasswordEncoder getPasswordEncoder() {
    return NoOpPasswordEncoder.getInstance();
  }

  /**
   * Configure the authorization mechanism.
   */
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()//
        .antMatchers("/admin").hasRole("ADMIN")//
        .antMatchers("/user").hasAnyRole("ADMIN", "USER")//
        .antMatchers("/").permitAll()//
        .and().formLogin();
  }



}
