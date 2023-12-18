package com.jay.authservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.jay.authservice.service.AuthService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Autowired
	private AuthService authService;

//	@Autowired
//	private JWTGenerator jwtRequestFilter;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable().authorizeRequests().requestMatchers("/**").permitAll().anyRequest()
				.authenticated().and().exceptionHandling().and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.headers().frameOptions().disable();
//		http.addFilterBefore( jwtRequestFilter,UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}
	
	@Bean
	public AuthenticationManager custAuthenticationManager(HttpSecurity http) throws Exception{
		AuthenticationManagerBuilder auth =  http.getSharedObject(AuthenticationManagerBuilder.class);
		auth.userDetailsService(authService).passwordEncoder(customPasswordEncoder());
		return auth.build();
		
	}
	
	@Bean
	public PasswordEncoder customPasswordEncoder(){
		return NoOpPasswordEncoder.getInstance();
	}


}
