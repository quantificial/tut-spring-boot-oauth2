package com.example;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

//@Configuration
//@Order(120)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	  @Override
	    protected void configure(HttpSecurity http) throws Exception {
	      http
	        .csrf().disable()
	        .antMatcher("/**")
	        .authorizeRequests()
	          .antMatchers("/", "/login**", "/webjars/**")
	          .permitAll()
	        .anyRequest()
	          .authenticated()
	        .and().logout().logoutSuccessUrl("/").permitAll();
	  }
	
}
