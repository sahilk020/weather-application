package com.jay.authservice.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jay.authservice.entity.UserCredentials;
import com.jay.authservice.repository.AuthRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AuthServiceImpl implements UserDetailsService {

	@Autowired
	private AuthRepository authRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		String methodName = "loadUserByUsername()";
		log.info("{} invoked", methodName);
		UserCredentials user = authRepository.findById(username).get();		
		return new User(user.getUsername(), user.getPassword(), new ArrayList<>());
	}
//	@Override
//	public boolean verifyUser(String username,String email) {
//		Optional<UserCredentials> userDetails = authRepository.findById(username);
//		return (userDetails.isPresent() && userDetails.get().getEmail().equalsIgnoreCase(email));
//	}
//	@Override
//	public String forgotPassword(PasswordResetRequest passwordResetRequest) {
//		if ( verifyUser(passwordResetRequest.getUsername(), passwordResetRequest.getEmail()))
//			authRepository.
//		return null;
//	}

}
