package com.jay.authservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jay.authservice.entity.LoginDetails;
import com.jay.authservice.entity.SuccessResponse;
import com.jay.authservice.exception.LoginException;
import com.jay.authservice.jwt.JWTGenerator;
import com.jay.authservice.service.AuthServiceImpl;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	private AuthServiceImpl authService;
	private JWTGenerator jwtGenerator;
	private AuthenticationManager authenticationManager;

	public AuthController(AuthenticationManager authenticationManager, JWTGenerator jwtGenerator, AuthServiceImpl authService) {
		this.authService = authService;
		this.authenticationManager = authenticationManager;
		this.jwtGenerator = jwtGenerator;
	}

	@GetMapping("/login")
	public ResponseEntity<SuccessResponse> login(@RequestBody LoginDetails loginDetails) throws LoginException {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginDetails.getUsername(), loginDetails.getPassword()));
		} catch (UsernameNotFoundException exception) {
			throw new LoginException("Enter correct username or password");
		}
		UserDetails userDetails = authService.loadUserByUsername(loginDetails.getUsername());
		String token = jwtGenerator.generateToken(userDetails.getUsername());
		return ResponseEntity.ok(new SuccessResponse(userDetails.getUsername(),token));
	}
}
