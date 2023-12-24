package com.jay.userservice.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.jay.userservice.entity.UserDto;
import com.jay.userservice.exception.UserAlreadyRegisteredException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.jay.userservice.entity.User;
import com.jay.userservice.service.RegisterServiceImpl;
@ExtendWith(MockitoExtension.class)
class RegistrationControllerTest {

	
	@Mock
	private RegisterServiceImpl registerServiceImpl;
	
	@InjectMocks
	private RegistrationController registrationController;
	
	private UserDto user;
	private User user1;
	@BeforeEach
	void setUp() throws Exception {
		user = new UserDto();
		user.setContactNumber("1234567890");
		user.setEmail("abcd@fg.com");
		user.setFirstName("firstName");
		user.setLastName("lastName");
		user.setPassword("Abcd@1234");
		user.setUsername("username");
		user1 =  new User(user.getUsername(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getContactNumber(), user.getPassword());
		System.out.println("setup done");
	}

	@AfterEach
	void tearDown() throws Exception {
		user = null;
	}

	@Test
	void testRegister() throws UserAlreadyRegisteredException {
		when(registerServiceImpl.register(user)).thenReturn(user1);
		assertEquals(user1, registrationController.register(user).getBody());
		verify(registerServiceImpl).register(user);
	}

	@Test
	void testRegisterThrowsException() throws UserAlreadyRegisteredException {
		when(registerServiceImpl.register(user)).thenThrow(UserAlreadyRegisteredException.class);
		assertThrows(UserAlreadyRegisteredException.class,()->registrationController.register(user));
		verify(registerServiceImpl).register(user);
	}

}
