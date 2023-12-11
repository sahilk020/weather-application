package com.jay.authorizationservice.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.jay.authorizationservice.entity.PasswordResetRequest;

public interface AuthService extends UserDetailsService {
//	String forgotPassword(PasswordResetRequest passwordResetRequest);

	boolean verifyUser(String username, String email);
}
