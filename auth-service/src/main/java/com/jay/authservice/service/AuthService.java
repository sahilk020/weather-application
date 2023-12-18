package com.jay.authservice.service;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthService extends UserDetailsService {
//	String forgotPassword(PasswordResetRequest passwordResetRequest);

	boolean verifyUser(String username, String email);
}
