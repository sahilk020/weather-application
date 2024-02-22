package com.jay.authservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jay.authservice.entity.LoginDetails;
import com.jay.authservice.entity.SuccessResponse;
import com.jay.authservice.exception.LoginException;
import com.jay.authservice.jwt.JWTGenerator;
import com.jay.authservice.service.AuthServiceImpl;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/auth")
@Slf4j
public class AuthController {
	private AuthServiceImpl authService;
	private JWTGenerator jwtGenerator;
	private AuthenticationManager authenticationManager;

	public AuthController(AuthenticationManager authenticationManager, JWTGenerator jwtGenerator, AuthServiceImpl authService) {
		this.authService = authService;
		this.authenticationManager = authenticationManager;
		this.jwtGenerator = jwtGenerator;
	}

	@PostMapping("/login")
	public ResponseEntity<SuccessResponse> login(@RequestBody LoginDetails loginDetails) throws LoginException {
		try {
			log.info("performing login");
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginDetails.username(), loginDetails.password()));
		} catch (Exception exception) {
			log.info("login failed");
			throw new LoginException("Enter correct username or password");
		}
		UserDetails userDetails = authService.loadUserByUsername(loginDetails.username());
		String token = jwtGenerator.generateToken(userDetails.getUsername());
		log.info("token generated {}",token);
		return ResponseEntity.ok(new SuccessResponse(userDetails.getUsername(),token));
	}
}
