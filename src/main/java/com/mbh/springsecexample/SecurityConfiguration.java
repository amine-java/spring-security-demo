package com.mbh.springsecexample;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Class that activates Spring Security. In this class the configuration is overriden.
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

  /** Inject Datasource **/
  @Autowired
  private DataSource dataSource;

  /** Customized query for retrieving UserDetails **/
  @Value("${query.sec.user}")
  private String retrieveUserByEmailQuery;

  /** Customized query for retrieving user's authorities **/
  @Value("${query.sec.authorities}")
  private String retrieveUserAuthorityQuery;

  /**
   * Configure the Authentication mechanism. We use an in memory authentication.
   */
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.jdbcAuthentication() //
        .dataSource(dataSource)//
        .usersByUsernameQuery(retrieveUserByEmailQuery)//
        .authoritiesByUsernameQuery(retrieveUserAuthorityQuery);
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
        .antMatchers("/admin").hasRole(ROLE_ADMIN)//
        .antMatchers("/user").hasAnyRole(ROLE_ADMIN, ROLE_USER)//
        .antMatchers("/").permitAll()//
        .and().formLogin();
  }



}
