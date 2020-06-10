package com.piggymetrics.auth;

import com.piggymetrics.auth.service.security.MongoUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
/**
 * @EnableWebSecurity The configuration creates a Servlet Filter known as the
 * springSecurityFilterChain which is responsible for all the security WebSecurityConfig only
 * contains information about how to authenticate our users.
 */
@EnableWebSecurity
/**
 * WebSecurityConfigurerAdapter How does Spring Security know that we want to require all users to
 * be authenticated? How does Spring Security know we want to support form based authentication
 */
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired private PasswordEncoder passwordEncoder;

  @Autowired private MongoUserDetailsService userDetailsService;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .antMatchers("/uaa/oauth/**")
        .permitAll()
        .anyRequest()
        .authenticated()
        .and()
        .csrf()
        .disable();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
  }

  // for @Autowired private AuthenticationManager authenticationManager;
  // Caused by: org.springframework.beans.factory.NoSuchBeanDefinitionException: No qualifying
  // bean of type 'org.springframework.security.authentication.AuthenticationManager' available:
  // expected at least 1 bean which qualifies as autowire candidate. Dependency annotations:
  // {@org.springframework.beans.factory.annotation.Autowired(required=true)}
  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }
}
