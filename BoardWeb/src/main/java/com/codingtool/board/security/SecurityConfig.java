package com.codingtool.board.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig {
	@Autowired
	private SecurityUserDetailsService userDetailsService;
	
	@Bean
	@Order(SecurityProperties.BASIC_AUTH_ORDER)
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		http

		.csrf().disable()
		.formLogin().loginPage("/system/login").defaultSuccessUrl("/board/getBoardList", true).and()
		.exceptionHandling().accessDeniedPage("/system/accessDenied").and()
		.logout().logoutUrl("/system/logout").invalidateHttpSession(true).logoutSuccessUrl("/").and()
		.userDetailsService(userDetailsService)
		.authorizeRequests().antMatchers("/", "/system/**").permitAll().and()
		.authorizeRequests().antMatchers("/board/**").authenticated().and()
		.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN");
		
		return http.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

}
