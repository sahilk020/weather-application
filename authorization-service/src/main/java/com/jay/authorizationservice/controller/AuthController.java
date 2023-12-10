package com.jay.authorizationservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jay.authorizationservice.entity.LoginDetails;
import com.jay.authorizationservice.entity.SuccessResponse;

@RestController
public class AuthController {
	@GetMapping("/login")
	public ResponseEntity<SuccessResponse> login(@RequestBody LoginDetails loginDetails){
		
	}
}
