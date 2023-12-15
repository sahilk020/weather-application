package com.jay.authorizationservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jay.authorizationservice.entity.LoginDetails;
import com.jay.authorizationservice.entity.SuccessResponse;
import com.jay.authorizationservice.exception.LoginException;
import com.jay.authorizationservice.jwt.JwtUtil;
import com.jay.authorizationservice.service.AuthService;

@RestController
public class AuthController {
	private AuthService authService;
	private JwtUtil jwtUtil;
	private AuthenticationManager authenticationManager;

	public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, AuthService authService) {
		this.authService = authService;
		this.authenticationManager = authenticationManager;
		this.jwtUtil = jwtUtil;
	}

	@GetMapping("/login")
	public ResponseEntity<SuccessResponse> login(@RequestBody LoginDetails loginDetails) throws LoginException {
		UserDetails userDetails;
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginDetails.getUsername(), loginDetails.getPassword()));
		} catch (UsernameNotFoundException exception) {
			throw new LoginException("Enter correct username or password");
		}
		userDetails = authService.loadUserByUsername(loginDetails.getUsername());
		String token = jwtUtil.generateToken(userDetails);
		return ResponseEntity.ok(new SuccessResponse(userDetails.getUsername(),token));
	}
	
//	@GetMapping("/forgotPassword")
//	public ResponseEntity<String> forgotPassword(@RequestBody PasswordResetRequest){
//		return ResponseEntity.ok(authService.forgotPassword(newPassword));
//	}
}
