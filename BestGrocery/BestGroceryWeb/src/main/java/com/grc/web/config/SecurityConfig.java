package com.grc.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
		.authorizeHttpRequests()
		.requestMatchers("/resources/**", "/auth/**").permitAll()
		.anyRequest()
		.authenticated()
		.and()
		.formLogin()
		.loginPage("/auth/login")
		.loginProcessingUrl("/authenticate")
		.failureForwardUrl("/auth/login?err=true")
		.defaultSuccessUrl("/home")
		.permitAll()
		.and()
		.logout()
		.permitAll();
		
		
		return http.build();
	}
}
