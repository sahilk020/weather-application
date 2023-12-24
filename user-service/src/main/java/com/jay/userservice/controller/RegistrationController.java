package com.jay.userservice.controller;

import com.jay.userservice.entity.UserDto;
import com.jay.userservice.exception.UserAlreadyRegisteredException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jay.userservice.entity.User;
import com.jay.userservice.service.RegisterService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/users")
@Validated
@Slf4j
public class RegistrationController {
	@Autowired
	private RegisterService registerService;

	@PostMapping("/register")
	public ResponseEntity<User> register(@RequestBody @Valid UserDto user) throws UserAlreadyRegisteredException {
		log.info("Inside method register()");
		return ResponseEntity.ok(registerService.register(user));
	}
}
