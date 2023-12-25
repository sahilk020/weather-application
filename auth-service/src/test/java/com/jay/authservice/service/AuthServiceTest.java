package com.jay.authservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import com.jay.authservice.entity.UserCredentials;
import com.jay.authservice.repository.AuthRepository;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

	@Mock
	private AuthRepository authRepository;

	@InjectMocks
	private AuthServiceImpl authServiceImpl;

	private UserDetails userDetails;
	private UserCredentials userCredentials;

	@BeforeEach
	void setUp() throws Exception {
		userDetails = new User("username", "password", new ArrayList<>());
		userCredentials = new UserCredentials("username", "password");
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testLoadUserByUsername() {
		when(authRepository.findById(anyString())).thenReturn(Optional.of(userCredentials));
		assertEquals(userDetails, authServiceImpl.loadUserByUsername(anyString()));
	}

}
