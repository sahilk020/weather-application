package com.jay.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jay.userservice.entity.User;
import com.jay.userservice.repository.RegisterRepository;
@Service
public class RegisterServiceImpl implements RegisterService {

	@Autowired
	private RegisterRepository registerRepository;

	@Override
	public User register(User user) {
		return registerRepository.save(user);
	}

}
