package com.jay.authorizationservice.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.jay.authorizationservice.entity.LoginDetails;
import com.jay.authorizationservice.entity.SuccessResponse;
import com.jay.authorizationservice.exception.NoSuchUserFoundException;

public interface AuthService extends UserDetailsService {
//	SuccessResponse login(LoginDetails loginDetails) throws NoSuchUserFoundException;
}
