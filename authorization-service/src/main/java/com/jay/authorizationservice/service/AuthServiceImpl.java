package com.jay.authorizationservice.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jay.authorizationservice.entity.UserEntity;
import com.jay.authorizationservice.repository.AuthRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

	@Autowired
	private AuthRepository authRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		String methodName = "loadUserByUsername()";
		log.info("{} invoked", methodName);
		UserEntity user = authRepository.findById(username).get();
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

		
		return new User(user.getUsername(), user.getPassword(), List.of(new SimpleGrantedAuthority("user")));
	}

}
