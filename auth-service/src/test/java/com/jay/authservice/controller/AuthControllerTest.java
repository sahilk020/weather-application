package com.jay.authservice.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.jay.authservice.entity.LoginDetails;
import com.jay.authservice.entity.SuccessResponse;
import com.jay.authservice.exception.LoginException;
import com.jay.authservice.jwt.JWTGenerator;
import com.jay.authservice.service.AuthServiceImpl;
@ExtendWith(MockitoExtension.class)
class AuthControllerTest {
	
	@Mock
	private AuthServiceImpl authServiceImpl;
	@Mock
	private JWTGenerator jwtGenerator;
	@Mock
	private AuthenticationManager authenticationManager;
	@Mock
	private Authentication authentication;
	@InjectMocks
	private AuthController authController;
	
	private LoginDetails loginDetails;
	private SuccessResponse successResponse;
	
	@BeforeEach
	void setUp() throws Exception {
		loginDetails = new LoginDetails("username", "password");
		successResponse = new SuccessResponse("username", "randomTokenFoundHere");
	}

	@AfterEach
	void tearDown() throws Exception {
		loginDetails =null;
		successResponse = null;
	}

	@Test
	void testLogin() throws LoginException {
		UserDetails userDetails = new User(loginDetails.getUsername(), loginDetails.getPassword(),new ArrayList<>());
		when(authenticationManager.authenticate(any())).thenReturn(authentication);
		when(authServiceImpl.loadUserByUsername(loginDetails.getUsername())).thenReturn(userDetails);
		when(jwtGenerator.generateToken(anyString())).thenReturn(successResponse.getToken());
		ResponseEntity<SuccessResponse> response = authController.login(loginDetails);
		assertEquals(successResponse, response.getBody());
		assertTrue(response.getStatusCode().is2xxSuccessful());
	}
	
	@Test
	void testLoginThrowsLoginException() throws LoginException {
		when(authenticationManager.authenticate(any())).thenThrow(UsernameNotFoundException.class);
		assertThrows(LoginException.class, ()->authController.login(loginDetails));
	}

}
