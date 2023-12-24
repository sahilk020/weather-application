package com.jay.userservice.service;

import com.jay.userservice.entity.User;
import com.jay.userservice.entity.UserDto;
import com.jay.userservice.exception.UserAlreadyRegisteredException;

public interface RegisterService {
	public User register(UserDto user) throws UserAlreadyRegisteredException;
}
