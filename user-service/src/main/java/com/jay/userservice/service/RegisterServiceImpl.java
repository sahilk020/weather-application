package com.jay.userservice.service;

import com.jay.userservice.entity.UserDto;
import com.jay.userservice.exception.UserAlreadyRegisteredException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jay.userservice.entity.User;
import com.jay.userservice.entity.UserCredentials;
import com.jay.userservice.repository.RegisterRepository;

import java.util.Optional;

@Service
public class RegisterServiceImpl implements RegisterService {

	@Autowired
	private RegisterRepository registerRepository;
	@Autowired
	private KafkaPublisherInterface kafkaMessagePublisher;
	@Override
	public User register(UserDto userDto) throws UserAlreadyRegisteredException {
		Optional<User> registeredUser = registerRepository.findById(userDto.getUsername());
		if (registeredUser.isPresent()){
			throw new UserAlreadyRegisteredException("user with same username is already registered");
		}
		User user = new User(userDto.getUsername(), userDto.getFirstName(), userDto.getLastName(), userDto.getEmail(), userDto.getContactNumber(), userDto.getPassword());
		UserCredentials userCredentials = new UserCredentials(userDto.getUsername(),userDto.getPassword());
		kafkaMessagePublisher.sendEventsToTopic(userCredentials);
		return registerRepository.save(user);
	}

}
